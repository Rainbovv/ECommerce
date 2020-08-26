package main;

import domain.product.*;
import domain.repos.CurrencyRepository;
import domain.repos.DataRepository;
import domain.repos.ProductRepository;

import java.util.Date;
import java.util.List;


public class Application {

    public static void main(String[] args) {

//

        ProductRepository productRepository = ProductRepository.getInstance();
//
//        currencyRepository.updateRate("USD", 1.3);
//
//
//
        Product product = (Product) ProductFactory.getInstance().getProduct("Плюшевый Шмель", 15.99f, 125, 2055, 2, 28,
                "China", "soft toy", "/images/bumblebee.jpg");
//
        Product product1 = (Product) ProductFactory.getInstance().getProduct("Плюшевый Шмель", 15.99f, 125, 2025, 2, 28,
                "China", "soft toy", "/images/bumblebee.jpg");

        productRepository.updateQuantity(36,125);
        productRepository.updateManufacturer(36,"China");
        productRepository.updateCategory(36,"Мягкая игрушка");



//        List<Product> products = productRepository.selectByExpirationDateFrom(product1.getExpDate());
//


//        products.forEach(System.out::println);
        product = productRepository.selectByExpirationDateBetween(product1.getExpDate(),product.getExpDate()).get(0);
        System.out.println(product);
//        products.forEach(System.out::println);
//        product.setPrice(product.getPrice().toCurrency("RUB"));
//
//        System.out.println(product);




//        product1 = productRepository.selectById("products",product1.getId());
//        System.out.println(product1);
        productRepository.closeConnection();

    }

}
