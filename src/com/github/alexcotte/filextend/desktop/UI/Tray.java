package com.github.alexcotte.filextend.desktop.UI;

import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import com.github.alexcotte.filextend.desktop.def.Conf;
import javafx.application.Platform;
import javafx.event.EventHandler;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Tray {
	
	
	public Tray(final Stage stage) {
		
		if(SystemTray.isSupported()){
			SystemTray tray = SystemTray.getSystemTray();
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent arg0) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							if(SystemTray.isSupported()){
								Platform.setImplicitExit( false );
								stage.hide();
							}else{
								System.exit(0);
							}
						}
					});
				}
			});
			try {
				tray.add(getTryIcon(stage));
			} catch (Exception e) {
				System.exit(0);
			}
		}
	}
	
	
	private TrayIcon getTryIcon(final Stage stage){
		Image image = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource(Conf.ICON_TRAY));
		
		
		ActionListener showItemListener = new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        stage.show();
                    }
                });
    		}
    	};
    	
    	
    	ActionListener closeItemLister = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		};
		
		PopupMenu popup = new PopupMenu();
        MenuItem showItem = new MenuItem(Conf.SHOW_TRAY);
        showItem.addActionListener(showItemListener);
        popup.add(showItem); 
        MenuItem closeItem = new MenuItem(Conf.EXIT_TRAY);
        closeItem.addActionListener(closeItemLister);
        popup.add(closeItem);
        TrayIcon trayIcon = new TrayIcon(image, Conf.TITLE_TRAY, popup);
        return trayIcon;
	}
	
	
		
}
