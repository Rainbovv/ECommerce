package domain.repos;

import domain.interfaces.DataRepository;

import java.io.*;

public class DataRepo implements DataRepository {

	private DataRepo() {}

	@Override
	public <T> void save(T entity) {
		String objectsClass = entity.getClass().toString().toLowerCase();
		objectsClass = objectsClass.substring(objectsClass.lastIndexOf(".") + 1);

		try {

			File file = new File(this.getClass().getResource("/" + objectsClass + ".bin").toURI());
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));

			out.writeObject(entity);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public <T> T load(Class<?> classType) {

		String objectsClass = classType.toString().toLowerCase();
		objectsClass = objectsClass.substring(objectsClass.lastIndexOf(".") + 1);

		T t = null;

		try {
			File file = new File(this.getClass().getResource("/" + objectsClass + ".bin").toURI());
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));

			t = (T) in.readObject();

		}catch (Exception e) {
			e.printStackTrace();
		}
		if (t == null) System.err.println("The file is empty!");

		return t;
	}

	private static class SingletonHolder {
		private final static DataRepo INSTANCE = new DataRepo();
	}

	public static DataRepo getInstance(){
		return SingletonHolder.INSTANCE;
	}
}
