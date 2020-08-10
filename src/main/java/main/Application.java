package main;


import domain.properties.Money;
import domain.providers.MoneyProvider;
import domain.repos.Cart;
import domain.product.*;
import domain.repos.DataRepo;

import java.net.URI;
import java.net.URISyntaxException;

import static domain.providers.CurrencyProvider.BASE_CURRENCY;


public class Application {

    public static void main(String[] args) {


        Product product = (Product)ProductFactory.getInstance()
                                                .getProduct("Плюшевый Шмель",  15.99f, 125,
                                                            156,10,25,
                                                            "China", "Concrete Product");


        Money money = MoneyProvider.getInstance().getMoney(1000);

        DataRepo.getInstance().save(money);

        money = DataRepo.getInstance().load(Money.class);

        System.out.println(money);

        DataRepo.getInstance().save(product);
        product = DataRepo.getInstance().load(product.getClass());
        System.out.println(product);

//        Product fakeProduct = (Product)ProductFactory.getInstance()
//                                                     .getFakeProduct();
//
//        System.out.println(fakeProduct);
//
//        fakeProduct.setPrice(fakeProduct.getPrice().toCurrency("RON"));
//        System.out.println(fakeProduct);
//        System.out.println(fakeProduct.getPrice().getCurrency().getRate());
//        System.out.println(fakeProduct);
//
//        fakeProduct.setPrice(fakeProduct.getPrice().toCurrency("MDL"));
//        System.out.println(fakeProduct);


//        Cart.getInstance().add(product);
//        Cart.getInstance().add(fakeProduct);

//        System.out.println(Cart.getInstance().getTotal());
//        System.out.println(Cart.getInstance().getTotal("RUB"));

//        Cart.getInstance().findAll().forEach(System.out::println);

    }
}
