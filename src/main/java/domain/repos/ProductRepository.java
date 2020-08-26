package domain.repos;

import domain.interfaces.ProductRepositoryInterface;
import domain.product.Product;
import domain.properties.Category;
import domain.properties.Money;

import java.sql.*;
import java.util.List;

public class ProductRepository extends DataRepository implements ProductRepositoryInterface {

	private ProductRepository() {}

 	public Product convertToObject(ResultSet resultSet) {

		Product product = null;
		try {
			product = new Product(resultSet.getString("name"),
					new Money(resultSet.getFloat("price"),
							resultSet.getString("currency_code")),
					resultSet.getInt("quantity"),
					resultSet.getDate("expiration_date"),
					resultSet.getString("manufacturer"),
					selectById("categories",
							resultSet.getInt("category_id")),
					resultSet.getString("image_path"));

			product.setId(resultSet.getInt("id"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}


	@Override
	public void save(Product product) {
		Category category = product.getCategory();
		save(category);
		category = selectByName("categories", category.getName());
		int categoryId = category.getId();


		String query = String.format("INSERT INTO products (name, price, currency_code, quantity, " +
						"expiration_date, manufacturer, category_id, image_path) " +
						"VALUES('%s', %s, '%s', %d, '%s', '%s', '%s', '%s')", product.getName(),
				String.valueOf(product.getPrice().getAmount()).replace(",","."),
				product.getPrice().getCurrency().getCode(), product.getQuantity(),
				product.getExpDate(),product.getManufacturer(),
				categoryId, product.getImagePath());

		executeQuery(query, false);
	}

	@Override
	public List<Product> selectAll() {

		return selectAll("products ");
	}

	@Override
	public <T> List<Product> selectBy(String column, T value) {

		return super.select("products", column +"="+ value);
	}

	@Override
	public Product selectById(int value) {

		return selectById("products", value);
	}

	@Override
	public Product selectByName(String name) {

		return selectByName("products", name);
	}

	@Override
	public List<Product> selectByManufacturer(String manufacturer) {
		return selectBy("manufacturer", manufacturer);
	}

	@Override
	public List<Product> selectByCategory(String categoryName) {

		Category category = selectByName("categories", categoryName);

		return selectBy("category_id", category.getId());
	}

	@Override
	public List<Product> selectByExpirationDate(Date date) {
		return selectBy("expiration_date", "'"+date+"'");
	}

	@Override
	public List<Product> selectByExpirationDateFrom(Date date) {
		return select("products" , "expiration_date >= '"+date+"'");
	}

	@Override
	public List<Product> selectByExpirationDateTo(Date date) {
		return select("products" , "expiration_date < '"+date+"'");
	}

	@Override
	public List<Product> selectByExpirationDateBetween(Date dateFrom, Date dateTo) {
		return select("products" , String.format("expiration_date >= '%s' " +
				"AND expiration_date <'%s'", dateFrom, dateTo));
	}

	@Override
	public void delete(Integer id) {

		delete("products","id="+id);
	}

	@Override
	public void updateQuantity(Integer id, Integer quantity) {

		update("products", "quantity", quantity,"id="+id);
	}

	@Override
	public void updateName(Integer id, String name) {

		update("products", "name", "'"+name+"'","id="+id);
	}

	@Override
	public void updateManufacturer(Integer id, String manufacturer) {

		update("products", "manufacturer", "'"+manufacturer+"'","id="+id);
	}

	@Override
	public void updateCategory(Integer id, String categoryName) {
		save(new Category(categoryName));
		Category category = selectByName("categories", categoryName);
		update("products", "category_id", category.getId(),"id="+id);
	}

	private static class SingletonHolder {
		private final static ProductRepository INSTANCE = new ProductRepository();
	}

	public static ProductRepository getInstance() {
		return ProductRepository.SingletonHolder.INSTANCE;
	}
}
