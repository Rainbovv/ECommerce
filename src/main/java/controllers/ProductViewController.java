package controllers;

import domain.product.Product;
import domain.repos.DataRepo;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import main.GUIApp;

import java.io.IOException;

public class ProductViewController {


	public void showProduct() {
		try {
			Pane pane = FXMLLoader.load(getClass().getResource("/fxml/product-template.fxml"));

			Product product = DataRepo.getInstance().load(Product.class);

			ImageView imageView = (ImageView)pane.getChildren().get(0);
			Image image = new Image(String.valueOf(getClass().getResource(product.getImagePath())));
			imageView.setImage(image);

			Label label = (Label)pane.getChildren().get(1);
			label.setText(product.getName());


			Scene scene = new Scene(pane);

			GUIApp.changeScene(scene);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
