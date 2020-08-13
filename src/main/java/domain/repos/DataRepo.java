package domain.repos;

import domain.interfaces.DataRepository;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

@SuppressWarnings("unchecked")
public class DataRepo implements DataRepository {

	private DataRepo() {}

	private URL getFilePathByClassType(Class<?> classType) {

		String fileName;

		if (classType == ArrayList.class)
			fileName = "currencies";

		else if (classType == Date.class)
			fileName = "date_of_update";

		else {
			fileName = classType.toString().toLowerCase();
			fileName = fileName.substring(fileName.lastIndexOf(".") + 1);
		}

		return this.getClass().getResource("/" + fileName + ".bin");

	}

	@Override
	public <T> void save(T entity) {

		try {

			File file = new File(getFilePathByClassType(entity.getClass()).toURI());
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));

			out.writeObject(entity);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public <T> T load(Class<?> classType) {

		T entity = null;

		try {
			File file = new File(getFilePathByClassType(classType).toURI());
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));

			entity = (T)in.readObject();
			in.close();

		}catch (Exception e) {
			e.printStackTrace();
		}
		
		if (entity == null) System.err.println("The file is empty!");

		return entity;
	}

	private static class SingletonHolder {
		private final static DataRepo INSTANCE = new DataRepo();
	}

	public static DataRepo getInstance(){
		return SingletonHolder.INSTANCE;
	}
}
