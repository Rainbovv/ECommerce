package domain.product;

import com.github.javafaker.Faker;
import domain.providers.CategoryProvider;
import domain.providers.MoneyProvider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@SuppressWarnings("deprecation")
public class ProductFactory {

    private final MoneyProvider moneyProvider = MoneyProvider.getInstance();
    private final CategoryProvider categoryProvider = CategoryProvider.getInstance();


    private Integer productCount = 0;

    private ProductFactory() {}

    public AbstractProduct getProduct(String name, float price, int quantity, int expirationYear,
                                      int expirationMonth, int expirationDay, String manufacturer,
                                      String categoryName, String imagePath) {

        return new Product(++productCount, name, moneyProvider.getMoney(price), quantity,
                            new Date(expirationYear, expirationMonth, expirationDay),
                            manufacturer, categoryProvider.getCategory(categoryName), imagePath);
    }


    public AbstractProduct getFakeProduct() {

        Faker faker = new Faker(new Locale("ru"));

        return new Product(++productCount, faker.commerce().productName(),
                            moneyProvider.getMoney(Float.parseFloat(faker.commerce()
                                                                         .price()
                                                                         .replaceAll("[,]", "." ))),
                            faker.random().nextInt(1, 10000), new Date(faker.random().nextInt(1, 10000),
                            faker.random().nextInt(1, 10000), faker.random().nextInt(1, 10000)),
                            faker.country().name(),
                            categoryProvider.getCategory("Fake Product"), faker.random().hex());
    }


    public List<AbstractProduct> getListOfFakeProducts(int size) {

        List<AbstractProduct> products = new ArrayList<>();

        while (products.size() < size) {
            products.add(getFakeProduct());
        }
        return products;
    }

    private static class SingletonHolder {
        private final static ProductFactory INSTANCE = new ProductFactory();
    }

    public static ProductFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
