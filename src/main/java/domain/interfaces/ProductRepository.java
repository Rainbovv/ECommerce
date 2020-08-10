package domain.interfaces;

import domain.product.Product;

import java.util.Date;
import java.util.List;

public interface ProductRepository {

    void add(Product product);
    void delete(Integer productId);
    void updateQuantity(Integer productId, Integer newQuantity);
    List<Product> findAll();
    Product findById(Integer productId);
    List<Product> findByName(String productName);
    List<Product> findByManufacturer(String manufacturer);
    List<Product> findByCategory(String category);
    List<Product> findByExpirationDate(Date expDate);
    List<Product> findByExpirationDateFrom(Date expDate);
    List<Product> findByExpirationDateTo(Date expDate);
    List<Product> findByExpirationDateBetween(Date expDateFrom, Date expDateTo);

}
