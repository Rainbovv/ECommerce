package domain.repos;

import domain.interfaces.CategoryRepositoryInterface;
import domain.properties.Category;
import domain.properties.Currency;

import java.sql.*;
import java.util.List;

public class CategoryRepository extends DataRepository implements CategoryRepositoryInterface {

	private CategoryRepository() {}


	public Category convertToObject(ResultSet resultSet) {

		Category category = null;

		try {
			category = new Category(resultSet.getInt("id"),
					resultSet.getString("name"));
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		return category;
	}


	@Override
	public void save(Category category) {

		String query = String.format("INSERT INTO categories (name) VALUES('%s')", category.getName());

		super.executeQuery(query, false);
	}

	@Override
	public List<Currency> selectAll() {

		return selectAll("categories");
	}

	@Override
	public List<Category> select(String column, String value) {

		return super.select("categories", column +"="+ value);
	}

	@Override
	public Category selectById(int value) {


		return (Category)super.selectById("categories", value);
	}

	@Override
	public Category selectByName(String name) {

		return (Category)super.selectByName("categories", name);
	}

	@Override
	public void updateName(String name, String condition) {

		update("categories", "name", name, condition);
	}

	@Override
	public void deleteAll() {

		deleteAll("categories");
	}

	@Override
	public void delete(String condition) {

		delete("categories", condition);
	}


	private static class SingletonHolder {
		private final static CategoryRepository INSTANCE = new CategoryRepository();
	}

	public static CategoryRepository getInstance() {
		return CategoryRepository.SingletonHolder.INSTANCE;
	}
}
