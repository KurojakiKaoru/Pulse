package com.ztechnetwork.handlers;

import com.ztechnetwork.gamestates.CustomLevelState;
import com.ztechnetwork.gamestates.GameState;
import com.ztechnetwork.gamestates.HowToPlayState;
import com.ztechnetwork.gamestates.IntroState;
import com.ztechnetwork.gamestates.LevelInfoState;
import com.ztechnetwork.gamestates.LevelSelectState;
import com.ztechnetwork.gamestates.MenuState;
import com.ztechnetwork.gamestates.PauseGameState;
import com.ztechnetwork.gamestates.PlayingState;
import com.ztechnetwork.main.GamePanel;

public class GameStateManager {
	
	private GameState gameState;
	private int currentState;
	
	public static final int INTRO_STATE = -1;
	public static final int MENU_STATE = 0;
	public static final int LEVEL_INFO_STATE = 1;
	public static final int PLAYING_STATE = 2;
	public static final int LEVEL_SELECT_STATE = 3;
	public static final int HOW_TO_PLAY_STATE = 4;
	public static final int CUSTOM_LEVEL_STATE = 5;
	public static final int PAUSE_GAME_STATE = 6;
	
	public GameStateManager() {
		
		currentState = INTRO_STATE;
		loadState(currentState);
		
	}
	
	private void loadState(int state) {
		if(state == INTRO_STATE)
			gameState = new IntroState(this);
		else if(state == MENU_STATE)
			gameState = new MenuState(this);
		else if(state == LEVEL_INFO_STATE)
			gameState = new LevelInfoState(this);
		else if(state == PLAYING_STATE)
			gameState = new PlayingState(this);
		else if(state == LEVEL_SELECT_STATE)
			gameState = new LevelSelectState(this);
		else if(state == HOW_TO_PLAY_STATE)
			gameState = new HowToPlayState(this);
		else if(state == CUSTOM_LEVEL_STATE)
			gameState = new CustomLevelState(this);
		else if(state == PAUSE_GAME_STATE)
			gameState = new PauseGameState(this);
	}
	
	public void setState(int state) {
		currentState = state;
		loadState(currentState);
	}
	
	public void update() {
		if(gameState != null) gameState.update();
	}
	
	public void draw(java.awt.Graphics2D g) {
		if(gameState != null) gameState.draw(g);
		else {
			g.setColor(java.awt.Color.BLACK);
			g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		}
	}
	
}