package br.com.objective.game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameApp extends Application {
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(final Stage primaryStage) throws Exception {
		final FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/RootView.fxml"));
		final Parent root = loader.load();
		
		final Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Gourmet Game");
		
		primaryStage.show();
	}
	
}
