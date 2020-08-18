package main;

import domain.product.*;
import domain.repos.DataRepo;
import java.io.IOException;


public class Application {

    public static void main(String[] args) {


        Product product = (Product) ProductFactory.getInstance()
                .getProduct("Плюшевый Шмель", 15.99f, 125, 156,
                        10, 25, "China", "Concrete Product", "/images/bumblebee.jpg");

        DataRepo.getInstance().save(product);


        GUIApp.main(args);


    }
}
