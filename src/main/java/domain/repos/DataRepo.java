package domain.repos;

import domain.interfaces.DataRepository;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class DataRepo implements DataRepository {

	private DataRepo() {}

	@Override
	public <T> void save(T entity) {

		T object = entity;
		
		String fileName = entity.getClass().toString().toLowerCase();
		fileName = fileName.substring(fileName.lastIndexOf(".") + 1);

		if (entity.getClass() == ArrayList.class)
			fileName = "currencies";

		if (entity.getClass() == Date.class)
			fileName = "date_of_update";

		try {

			File file = new File(this.getClass().getResource("/" + fileName + ".bin").toURI());
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));


			out.writeObject(object);
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public <T> T load(Class<?> classType) {

		T t = null;

		String fileName = classType.toString().toLowerCase();
		fileName = fileName.substring(fileName.lastIndexOf(".") + 1);

		if (classType == ArrayList.class)
			fileName = "currencies";

		if (classType == Date.class)
			fileName = "date_of_update";

		try {
			File file = new File(this.getClass().getResource("/" + fileName + ".bin").toURI());
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));


			t = (T) in.readObject();
			in.close();

		}catch (Exception e) {
//			e.printStackTrace();
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
