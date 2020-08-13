package main;

import domain.product.*;
import domain.properties.Money;
import domain.providers.MoneyProvider;
import domain.repos.DataRepo;

public class Application {

    public static void main(String[] args) {


        Product product = (Product) ProductFactory.getInstance()
                .getProduct("Плюшевый Шмель", 15.99f, 125, 156,
                        10, 25, "China", "Concrete Product");
        DataRepo.getInstance().save(product);
        product = DataRepo.getInstance().load(product.getClass());
        System.out.println(product);



        Money money = MoneyProvider.getInstance().getMoney(1000);
        DataRepo.getInstance().save(money);
        money = DataRepo.getInstance().load(Money.class);
        System.out.println(money);



        Product fakeProduct = (Product)ProductFactory.getInstance().getFakeProduct();
        System.out.println(fakeProduct);

        fakeProduct.setPrice(fakeProduct.getPrice().toCurrency("RON"));
        System.out.println(fakeProduct);

        fakeProduct.setPrice(fakeProduct.getPrice().toCurrency("ML"));
        System.out.println(fakeProduct);

    }
}
