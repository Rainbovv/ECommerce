package domain.interfaces;


public interface DataRepository {

	<T> void save(T entity);

	<T> T load(Class<?> classType);
}
