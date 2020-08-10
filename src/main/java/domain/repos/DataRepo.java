package domain.repos;

import domain.interfaces.DataRepository;

public class DataRepo implements DataRepository {


	@Override
	public <T> void save(T entity) {

	}

	@Override
	public <T> T load(Class classType) {
		return null;
	}
}
