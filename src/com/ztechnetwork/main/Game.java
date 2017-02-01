package com.ztechnetwork.main;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game {
	
	public static JFrame window;
	
	public static final int VISIBLE = 0;
	public static final int INVISIBLE = 1;
	
	public static void main(String[] args) {	
	
		window = new JFrame("Pulse Beta v2.0.0 (Build 4)");
		window.add(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setUndecorated(false);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
	
		

	public static void setCursor(int i) {
		Cursor c = null;
		if(i == INVISIBLE) {
			BufferedImage bi = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
			c = Toolkit.getDefaultToolkit().createCustomCursor(bi, new Point(0, 0), ".");
			Toolkit.getDefaultToolkit().getImage("Image");
		}
		window.setCursor(c);
	}
}
