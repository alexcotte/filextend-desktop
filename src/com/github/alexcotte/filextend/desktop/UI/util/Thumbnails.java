package com.github.alexcotte.filextend.desktop.UI.util;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class Thumbnails {
	
	public static Image getThumbnails(String ext){
		ext = getExt(ext);
		Image jfImage = null;
		try {
		Thumbnails    t = new Thumbnails(); //--->> to access resources
		BufferedImage image;
		image = ImageIO.read(t.getClass().getClassLoader().getResource("resources/icons/tmb.jpg"));
		Graphics    g = image.getGraphics();
		Font font = g.getFont().deriveFont(20f);
		FontMetrics metrics = g.getFontMetrics(font);
		int w 	  = (60 - metrics.stringWidth(ext))/2;
		    g.setFont(font);
		    g.drawString(ext, w, 37);
		    g.dispose();	
		    
		    jfImage = SwingFXUtils.toFXImage(image, null);
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jfImage;
	}
	
	public static String getExt(String fileName){
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
		return ((ext.length()>=0)?"oth":ext);
	}
}
