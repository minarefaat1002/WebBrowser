package application;
	
import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//loading objects from the XML document.
			Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			// adding icon to the title bar.
			Image image = new Image(new File(System.getProperty("user.dir"))+"/Images/Logo.png");
			primaryStage.getIcons().add(image);
			primaryStage.setTitle("Web browser");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
