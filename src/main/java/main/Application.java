package main;


import domain.repos.Cart;
import domain.product.*;

import static domain.providers.CurrencyProvider.BASE_CURRENCY;


public class Application {

    public static void main(String[] args) {


        Product product = (Product)ProductFactory.getInstance()
                                                .getProduct("Плюшевый Шмель",  15.99f, 125,
                                                            156,10,25,
                                                            "China", "Concrete Product");


        Product fakeProduct = (Product)ProductFactory.getInstance()
                                                     .getFakeProduct();

        System.out.println(fakeProduct);

        fakeProduct.setPrice(fakeProduct.getPrice().toCurrency("RON"));
        System.out.println(fakeProduct);
//        System.out.println(fakeProduct.getPrice().getCurrency().getRate());
//        System.out.println(fakeProduct);

        fakeProduct.setPrice(fakeProduct.getPrice().toCurrency("MDL"));
        System.out.println(fakeProduct);


//        Cart.getInstance().add(product);
//        Cart.getInstance().add(fakeProduct);

//        System.out.println(Cart.getInstance().getTotal());
//        System.out.println(Cart.getInstance().getTotal("RUB"));

//        Cart.getInstance().findAll().forEach(System.out::println);

    }
}
