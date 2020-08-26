package domain.interfaces;


import java.util.List;

public interface DataRepositoryInterface {

	<T> void save(T entity);

	<T> List<T> selectAll(String table);

	<T,V> List<T> select(String table, String condition);

	<T> T selectById(String table, int id);

	<T> T selectByName(String table, String name);

	<T> void update(String table, String column, T value, String condition);

	void deleteAll(String table);

	void delete(String table, String condition);
}
