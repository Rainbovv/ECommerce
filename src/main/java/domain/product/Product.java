package domain.product;

import domain.properties.Category;
import domain.properties.Money;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("deprecation")
public class Product extends AbstractProduct implements Serializable {

    private Integer id;  // Unique, immutable
    private String name;
    private Money price;
    private Integer quantity;
    private Date expiration;
    private String manufacturer;
    private Category category;


    Product(Integer id, String name, Money price, Integer quantity, Date date,
                      String manufacturer, Category category) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expiration = date;
        this.manufacturer = manufacturer;
        this.category = category;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Money getPrice() {
        return this.price;
    }

    @Override
    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public Date getExpDate() {
        return this.expiration;
    }

    @Override
    public String getManufacturer() {
        return this.manufacturer;
    }

    @Override
    public Category getCategory() {
        return this.category;
    }

    @Override
    public void setPrice(float amount) {
        this.price = new Money(amount);
    }


    public void setPrice(Money money) {
        this.price = money;
    }

    @Override
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public void setExpDate(int expirationYear, int expirationMonth, int expirationDay) {
        this.expiration = new Date(expirationYear, expirationMonth, expirationDay);
    }

    @Override
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public void setCategory(String categoryName) {
        this.category = new Category(categoryName);
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Product [ID = %d" +
                           "%n         Name = %s," +
                           "%n         Price = %s," +
                           "%n         Quantity = %d," +
                           "%n         Expiration date = %s," +
                           "%n         Manufacturer = %s,"+
                           "%n         Category = %s]%n", id, name, price, quantity,
                                                         expiration.toGMTString(), manufacturer, category );
    }
}
