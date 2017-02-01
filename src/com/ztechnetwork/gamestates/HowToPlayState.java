/**
 * How to Play.
 */

package com.ztechnetwork.gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.ztechnetwork.entity.Bubble;
import com.ztechnetwork.entity.GameButton;
import com.ztechnetwork.handlers.GameStateManager;
import com.ztechnetwork.handlers.ImageLoader;
import com.ztechnetwork.handlers.JukeBox;
import com.ztechnetwork.handlers.Keys;
import com.ztechnetwork.handlers.Mouse;
import com.ztechnetwork.main.Game;
import com.ztechnetwork.main.GamePanel;

public class HowToPlayState extends GameState {
	
	// bg image
	private BufferedImage bg;
	
	// bubbles
	private ArrayList<Bubble> bubbles;
	private int bubbleTimer;
	
	// buttons
	private int currentChoice = 0;
	private GameButton[] options;
	
	// fonts and colors
	private Color titleColor;
	private Font titleFont;
	private Font font;
	private Font font2;
	
	// fading
	private int fadeInTimer;
	private int fadeInDelay;
	private int fadeOutTimer;
	private int fadeOutDelay;
	private int alpha;
	private int nextState;
	
	public HowToPlayState(GameStateManager gsm) {
		super(gsm);
		init();
	}
	
	public void init() {
		
		// load bg image
		bg = ImageLoader.BG;
		
		// load fonts
		try {
			Font scFont = Font.createFont(
					Font.TRUETYPE_FONT,
					getClass().getResourceAsStream("/fonts/zorque.ttf"));
			titleColor = Color.WHITE;
			titleFont = scFont.deriveFont(64f);
			font = scFont.deriveFont(23f);
			font2 = scFont.deriveFont(14f);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		// show mouse cursor
		Game.setCursor(Game.VISIBLE);
		
		// set up buttons
		options = new GameButton[1];
		options[0] = new GameButton(320, 430, 100, 50);
		options[0].setText("P L A Y", font);

		
		// fade timer
		fadeInTimer = 0;
		fadeInDelay = 60;
		fadeOutTimer = -1;
		fadeOutDelay = 60;
		
		// music
		if(!JukeBox.isPlaying("menumusic")) {
			JukeBox.loop("menumusic", 2000, JukeBox.getFrames("menumusic") - 2000);
		}
		JukeBox.stop("bgmusic1");
		
		// bubbles
		bubbles = new ArrayList<Bubble>();
		bubbleTimer = 0;
		
	}
	
	public void update() {
		
		// check keys
		handleInput();
		
		// check buttons for hover
		for(int i = 0; i < options.length; i++) {
			if(currentChoice == i) {
				options[i].setHover(true);
			}
			else {
				options[i].setHover(false);
			}
		}
		
		// update fade
		if(fadeInTimer >= 0) {
			fadeInTimer++;
			alpha = (int) (255.0 * fadeInTimer / fadeInDelay);
			if(fadeInTimer == fadeInDelay) {
				fadeInTimer = -1;
			}
		}
		if(fadeOutTimer >= 0) {
			fadeOutTimer++;
			alpha = (int) (255.0 * fadeOutTimer / fadeOutDelay);
			if(fadeOutTimer == fadeOutDelay) {
				gsm.setState(nextState);
			}
		}
		if(alpha < 0) alpha = 0;
		if(alpha > 255) alpha = 255;
		
		// bubbles
		bubbleTimer++;
		if(bubbleTimer == 60) {
			bubbles.add(new Bubble(Math.random() * 540 - 100, Math.random() * 100 + 480));
			bubbleTimer = 0;
		}
		for(int i = 0; i < bubbles.size(); i++) {
			if(bubbles.get(i).update()) {
				bubbles.remove(i);
				i--;
			}
		}
		
	}
	
	public void draw(Graphics2D g) {
		
		// draw bg
		g.drawImage(bg, 0, 0, null);
		
		// draw bubbles
		for(int i = 0; i < bubbles.size(); i++) {
			bubbles.get(i).draw(g);
		}
		
		// draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("HOW TO PLAY", 100, 110);
		
		// draw menu options
		g.setFont(font);
		g.setColor(Color.WHITE);
		
		// draw buttons
		for(int i = 0; i < options.length; i++) {
			options[i].draw(g);
		}
		
		// other
		g.setFont(font2);
		g.setColor(Color.CYAN);
		g.drawString("2016 Leron Hinds©                                                                                         					                                                         v2.0.0", 10, 470);
		
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString("The goal is to get the white circle to", 60, 200);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString("the red goal by pushing it with your MOUSE.", 20, 230);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString("Some Levels contains effects that may", 20, 260);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString("change the difficulty of the game. Watch out", 20, 290);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString("for black holes, and make sure to keep track", 20, 320);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString("of how many moves you have left. This game", 20, 350);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString("gets updated with new levels and features.", 20, 380);
		
		
		
		// draw fade
		if(fadeInTimer >= 0) {
			g.setColor(new Color(255, 255, 255, 255 - alpha));
			g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		}
		if(fadeOutTimer >= 0) {
			g.setColor(new Color(255, 255, 255, alpha));
			g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		}
		
	}
	
	private void select() {
		
		// already transitioning, cannot select
		if(fadeOutTimer != -1) return;
		
		// go to level select
		if(currentChoice == 0) {
			nextState = GameStateManager.LEVEL_SELECT_STATE;
			fadeInTimer = -1;
			fadeOutTimer = 0;
			JukeBox.play("menuclick");
		}

	}
	
	public void handleInput() {
		
			
		// see if button is clicked
		if(Mouse.isPressed()) {
			select();
		}
		
		
		// check if mouse is hovering over buttons
		boolean hit = false;
		for(int i = 0; i < options.length; i++) {
			if(options[i].isHovering(Mouse.x, Mouse.y)) {
				currentChoice = i;
				hit = true;
				break;
			}
		}
		if(!hit) currentChoice = -1;
		
		if(Keys.isPressed(Keys.ESCAPE)) {JukeBox.play("menuclick");
			nextState = GameStateManager.MENU_STATE;
			fadeInTimer = -1;
			fadeOutTimer = 0;
	}
	
}
}









