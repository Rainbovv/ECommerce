package domain.repos;

import domain.interfaces.DataRepositoryInterface;
import domain.product.Product;
import domain.properties.Category;
import domain.properties.Currency;
import domain.properties.Money;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class DataRepository implements DataRepositoryInterface {

	private Connection connection;
	private Statement statement;

	DataRepository() {}

	public void connect() {

		String URL = "jdbc:postgresql://localhost/ecommerce?user=postgres" +
				"&password=Rainbow@446571&ssl=false";
		try {

			connection = DriverManager.getConnection(URL);
			statement = connection.createStatement();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() {

		try {

			statement.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	private <T> T convertToObject(ResultSet resultSet, String table) {

			T entity = null;

				if (table.equals("currencies")) {
					entity = (T)CurrencyRepository.getInstance().convertToObject(resultSet);
				}
				if (table.equals("categories")) {
					entity = (T)CategoryRepository.getInstance().convertToObject(resultSet);
				}
				if (table.equals("products")) {
					entity = (T)ProductRepository.getInstance().convertToObject(resultSet);
				}

			return entity;
	}

	protected ResultSet executeQuery(String query, boolean resultSetNeeded) {

		System.out.println(query);
		connect();
		ResultSet resultSet = null;

		try {
			if (resultSetNeeded) {
				resultSet = statement.executeQuery(query);

			}
			else statement.executeUpdate(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}


	@Override
	public <T> void save(T entity) {

		if (entity.getClass() == Product.class) ProductRepository.getInstance().save((Product)entity);

		if (entity.getClass() == Currency.class) CurrencyRepository.getInstance().save((Currency)entity);

		if (entity.getClass() == Category.class) CategoryRepository.getInstance().save((Category)entity);

	}

	@Override
	public <T> List<T> selectAll(String table) {

		return select(table, null);
	}

	@Override
	public <T,V> List<T> select(String table, String condition) {

		List<T> list = new ArrayList<>();

		String query = "SELECT * FROM " + table;

		if (condition != null) query += " WHERE " + condition;

		ResultSet resultSet = executeQuery(query, true);

		try {
			while (resultSet.next()) {

				T entity = convertToObject(resultSet, table);
				list.add(entity);
			}
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public <T> T selectById(String table, int id) {

		return (T)select(table, "id="+ id).get(0);
	}

	@Override
	public <T> T selectByName(String table, String name) {

		return (T)select(table, "name='"+name+"'").get(0);
	}

	@Override
	public <T> void update(String table, String column,
	                         T value, String condition) {

		String query = String.format("UPDATE %s SET %s=%s",
				table, column, value);

		if (condition != null) query += " WHERE " + condition;

		executeQuery(query, false);
	}

	@Override
	public void deleteAll(String table) {
		delete(table, null);
	}

	@Override
	public void delete(String table, String condition) {

		String query = "DELETE FROM "+ table;

		if (condition != null) query += " WHERE " + condition;

		executeQuery(query, false);
	}


	private static class SingletonHolder {
		private final static DataRepository INSTANCE = new DataRepository();
	}

	public static DataRepository getInstance() {
		return SingletonHolder.INSTANCE;
	}
}
