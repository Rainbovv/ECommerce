package domain.product;

import domain.properties.Category;
import domain.properties.Money;

import java.io.Serializable;
import java.sql.Date;


@SuppressWarnings("deprecation")
public class Product extends AbstractProduct implements Serializable {

    private int id;
    private String name;
    private Money price;
    private Integer quantity;
    private Date expirationDate;
    private String manufacturer;
    private Category category;
    private String imagePath;


    public Product(String name, Money price, Integer quantity, java.util.Date date, String manufacturer, Category category, String imagePath) {

        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expirationDate = new Date(date.getTime());
        this.manufacturer = manufacturer;
        this.category = category;
        this.imagePath = imagePath;
    }


    /** /////////////////////////// Getters /////////////////////////// **/


    public int getId() {
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
        return this.expirationDate;
    }

    @Override
    public String getManufacturer() {
        return this.manufacturer;
    }

    @Override
    public Category getCategory() {
        return this.category;
    }



    /** /////////////////////////// Setters /////////////////////////// **/

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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
        this.expirationDate = new Date(expirationYear, expirationMonth, expirationDay);
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
    public String getImagePath() {
        return imagePath;
    }

    @Override
    public String toString() {
        return String.format("Product [Name = %s," +
                            "%n         Price = %s," +
                            "%n         Quantity = %d," +
                            "%n         Expiration date = %s," +
                            "%n         Manufacturer = %s,"+
                            "%n         Category = %s,"+
                            "%n         Image Path = %s]%n", name, price, quantity,
                                                         expirationDate.toGMTString(), manufacturer,
                                                        category, imagePath);
    }
}
