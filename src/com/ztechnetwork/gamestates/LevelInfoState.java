/** The level loading screen.
 * Shows the level title as well as description.
 * All level information is obtained from LevelData.
 */

package com.ztechnetwork.gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.ztechnetwork.handlers.GameStateManager;
import com.ztechnetwork.handlers.ImageLoader;
import com.ztechnetwork.handlers.LevelData;
import com.ztechnetwork.handlers.Mouse;
import com.ztechnetwork.main.GamePanel;

public class LevelInfoState extends GameState {
	
	//BG Image
	private BufferedImage bg;
	// text color
	private Color color;
	
	// level description
	private String[] descriptions;
	private Font dFont;
	
	// fading
	private int fadeDelay;
	private int fadeInTimer;
	private int fadeInDelay;
	private int fadeOutTimer;
	private int fadeOutDelay;
	private int alpha;
	
	public LevelInfoState(GameStateManager gsm) {
		super(gsm);
		init();
	}
	
	public void init() {
		
		//Load Image
		bg = ImageLoader.BG;
		
		color = Color.GREEN;
		
		// fade timer
		fadeInTimer = 0;
		fadeInDelay = 60;
		fadeOutTimer = -1;
		fadeOutDelay = 60;
		
		// get level descriptions
		descriptions = LevelData.getDescription();
		dFont = LevelData.LEVEL_INFO_FONT.deriveFont(14f);
		fadeDelay = 90 + descriptions.length * 30;
		
	}
	
	public void update() {
		
		// check game input
		handleInput();
		
		// update fade
		if(fadeInTimer >= 0) {
			fadeInTimer++;
			alpha = (int) (255.0 * fadeInTimer / fadeInDelay);
			if(fadeInTimer == fadeDelay) {
				fadeInTimer = -1;
				fadeOutTimer = 0;
			}
		}
		if(fadeOutTimer >= 0) {
			fadeOutTimer++;
			alpha = (int) (255.0 * fadeOutTimer / fadeOutDelay);
			if(fadeOutTimer == fadeOutDelay) {
				gsm.setState(GameStateManager.PLAYING_STATE);
			}
		}
		if(alpha < 0) alpha = 0;
		if(alpha > 255) alpha = 255;
		
	}
	
	public void draw(Graphics2D g) {
		
		// draw bg
			g.drawImage(bg, 0, 0, null);
		// draw title
		g.setColor(color);
		g.setFont(LevelData.LEVEL_INFO_FONT.deriveFont(20f));
		g.drawString(LevelData.getLevel(), 20, 220);
		g.setStroke(LevelData.STROKE_1);
		g.drawLine(15, 225, 300, 225);
		
		// draw level descriptions
		g.setFont(dFont);
		for(int i = 0; i < descriptions.length; i++) {
			g.drawString(descriptions[i], 20, 250 + 20 * i);
		}
		
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
	
	public void handleInput() {
		// move it along
		if(Mouse.isPressed() && fadeOutTimer == -1) {
			fadeInTimer = -1;
			fadeOutTimer = 0;
		}
	}
	
}
