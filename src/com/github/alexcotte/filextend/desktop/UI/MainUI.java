package com.github.alexcotte.filextend.desktop.UI;

import java.io.File;
import java.util.List;
import com.github.alexcotte.filextend.desktop.def.Conf;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class MainUI extends Application{

	
	
    public AnchorPane ancSend; //--->>> dragDrop element
	public ListView<Item>  listToSend;
	public final ObservableList<Item> items = FXCollections.observableArrayList();
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
		
			ancSend.setStyle(Conf.STYLE_EXIT);
			
		    final Dragboard db  = e.getDragboard();
	        if (db.hasFiles()) {
	        	List<File> files = db.getFiles();
	        	if(!files.isEmpty()){
	        		listToSend.setVisible(Boolean.TRUE);
	        		for(File file: files){
	        			items.add(new Item(file.getAbsolutePath()));
	        		}
	        		listToSend.setItems(items);
	        		listToSend.setCellFactory(new Callback<ListView<Item>, ListCell<Item>>() {

						@Override
						public ListCell<Item> call(ListView<Item> arg0) {
							
							ListCell<Item> cell = new ListCell<Item>(){
								@Override
								protected void updateItem(Item arg0,boolean arg1) {
									super.updateItem(arg0, arg1);
									if(arg0 !=null){
										setGraphic(arg0.hbox);	
									}
								}	
							};
							return cell;
						}
					});
	        	}
	        }
	}
}
