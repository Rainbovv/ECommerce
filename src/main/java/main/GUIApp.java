package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GUIApp extends Application {

	static Stage stage;

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		GUIApp.stage = primaryStage;

		Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/fxml/product-full.fxml")));

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void changeScene(Scene scene) {
		GUIApp.stage.setScene(scene);
	}
}
