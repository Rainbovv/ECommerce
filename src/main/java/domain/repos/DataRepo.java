package domain.repos;

import domain.interfaces.DataRepository;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;

@SuppressWarnings("unchecked")
public class DataRepo implements DataRepository {

	private DataRepo() {}

	private URI getFilePathByClassType(Class<?> classType) {

		String fileName;

		if (classType == ArrayList.class)
			fileName = "currencies";

		else if (classType == Date.class)
			fileName = "date_of_update";

		else {
			fileName = classType.toString().toLowerCase();
			fileName = fileName.substring(fileName.lastIndexOf(".") + 1);
		}

		return Paths.get("data/" + fileName + ".bin").toUri();

	}

	@Override
	public <T> void save(T entity) {

		try {
			Files.createDirectories(Paths.get("data"));
			File file = new File(getFilePathByClassType(entity.getClass()));
			if(file.createNewFile()) {
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));

				out.writeObject(entity);
				out.close();
			}
		} catch (Exception e) {
//			e.printStackTrace();
		}
	}

	@Override
	public <T> T load(Class<?> classType) {

		T entity = null;

		try {
			File file = new File(getFilePathByClassType(classType));
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));

			entity = (T)in.readObject();
			in.close();

		}catch (Exception e) {
//			e.printStackTrace();
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
