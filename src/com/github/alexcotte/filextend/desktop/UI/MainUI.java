package com.github.alexcotte.filextend.desktop.UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainUI extends Application{

	@Override
	public void start(Stage mainStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/resources/ui/App.fxml"));
		Scene scene = new Scene(root, 600, 400);
		mainStage.setMaximized(Boolean.FALSE);
		mainStage.setResizable(false);
		mainStage.setTitle("File Extend");
		mainStage.setScene(scene);
		mainStage.show();
	}
	public void run(){
		launch();
	}
}
