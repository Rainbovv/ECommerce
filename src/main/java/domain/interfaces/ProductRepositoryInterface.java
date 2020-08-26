package domain.interfaces;

import domain.product.Product;
import domain.properties.Category;


import java.sql.Date;
import java.util.List;

public interface ProductRepositoryInterface {

    void save(Product product);
    void delete(Integer id);
    void updateName(Integer id, String name);
    void updateManufacturer(Integer id, String manufacturer);
    void updateCategory(Integer id, String categoryName);
    void updateQuantity(Integer id, Integer quantity);
    <T> List<Product> selectBy(String column, T value);
    List<Product> selectAll();
    Product selectByName(String name);
    List<Product> selectByManufacturer(String manufacturer);
    List<Product> selectByCategory(String categoryName);
    List<Product> selectByExpirationDate(Date date);
    List<Product> selectByExpirationDateFrom(Date date);
    List<Product> selectByExpirationDateTo(Date date);
    List<Product> selectByExpirationDateBetween(Date dateFrom, Date dateTo);
    Product selectById(int id);

}
