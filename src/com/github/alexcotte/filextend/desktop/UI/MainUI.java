package com.github.alexcotte.filextend.desktop.UI;

import java.io.File;
import java.util.List;

import com.github.alexcotte.filextend.desktop.def.Conf;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainUI extends Application{

	
	
    public AnchorPane ancSend; //--->>> dragDrop element
	public ListView listToSend;
    /*
     * start method Application JavaFX
     * */
	@Override
	public void start(Stage mainStage) throws Exception {
		//--->> 
		
		//--->>
		mainStage.getIcons().add(new Image(Conf.ICON_APP));
		Parent root = FXMLLoader.load(getClass().getResource("/resources/ui/App.fxml"));
		Scene scene = new Scene(root, 600, 400);
		mainStage.setMaximized(Boolean.FALSE);
		mainStage.setResizable(false);
		mainStage.setTitle("File Extend");
		mainStage.setScene(scene);
		mainStage.show();
		new Tray(mainStage);
	}
	
	
	/**
	 * Used for run the MainUI
	 * @author alexcotte
	 */
	public void run(){
		launch();
	}
	
	/**
	 * Method that perform Mouse Drag Exit
	 * @author alexcotte
	 */
	public void mouseDragExit(final DragEvent e){
		ancSend.setStyle(Conf.STYLE_EXIT);
	}
	
	/**
	 * Method that perform Mouse Drag Over
	 * @author alexcotte
	 */
	public void mouseDragOver(final DragEvent e ){
			final Dragboard db = e.getDragboard();
	        final boolean isAccepted = true; //--->>> check if is a file
	        if (db.hasFiles()) {
	            if (isAccepted) {
	            	ancSend.setStyle(Conf.STYLE_OVER);
	                e.acceptTransferModes(TransferMode.MOVE);
	            }
	        } else {
	            e.consume();
	        }
	} 
	
	/**
	 * Method that perform Mouse Drag Dropped
	 * @author alexcotte
	 */
	public void mouseDropped(final DragEvent e){
		    final Dragboard db  = e.getDragboard();
	        if (db.hasFiles()) {
	        	List<File> files = db.getFiles();
	        	if(!files.isEmpty()){
	        		listToSend.setVisible(Boolean.TRUE);
	        		for(File file: files){
	        			System.out.println(file.getAbsolutePath());
	        		}
	        	}
	        }
	}
}
