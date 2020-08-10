package domain.product;

import domain.properties.Category;
import domain.properties.Money;

import java.util.Date;

public abstract class AbstractProduct {

    abstract Integer getId();
    abstract String getName();
    abstract Money getPrice();
    abstract Integer getQuantity();
    abstract Date getExpDate();
    abstract String getManufacturer();
    abstract Category getCategory();

    abstract void setName(String name);
    abstract void setPrice(float amount);
    abstract void setQuantity(Integer quantity);
    abstract void setExpDate(int expirationYear, int expirationMonth, int expirationDay);
    abstract void setManufacturer(String manufacturer);
    abstract void setCategory(String categoryName);
}
