package com.github.alexcotte.filextend.desktop.UI;


import java.io.File;
import java.io.IOException;
import com.github.alexcotte.filextend.desktop.UI.util.Thumbnails;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Item extends VBox {
	
	public HBox 		hbox;
	public Button 		btnCancel;
	public ProgressBar 	progressBar;
	public Label 		progress;
	public ImageView 	image;	
	public boolean      isActive;
	public Label        filePath;
	
	
	public Item(String path) 
	{
		File file = new File(path);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/ui/item.fxml"));
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
			filePath.setText(path);
			//http://stackoverflow.com/questions/1144775/access-file-type-icons-mac-osx
			
          image.setImage(Thumbnails.getThumbnails(file.getAbsolutePath()));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
