/**
 * The playing screen.
 * All level information is obtained from LevelData.
 */

package com.ztechnetwork.gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.ztechnetwork.entity.Bouncer;
import com.ztechnetwork.entity.Bubble;
import com.ztechnetwork.entity.Cursor;
import com.ztechnetwork.entity.GameButton;
import com.ztechnetwork.entity.Goal;
import com.ztechnetwork.entity.HitBall;
import com.ztechnetwork.entity.Hole;
import com.ztechnetwork.entity.Particle;
import com.ztechnetwork.entity.Player;
import com.ztechnetwork.entity.PushBall;
import com.ztechnetwork.entity.Spawner;
import com.ztechnetwork.handlers.EnemyFactory;
import com.ztechnetwork.handlers.GameData;
import com.ztechnetwork.handlers.GameStateManager;
import com.ztechnetwork.handlers.ImageLoader;
import com.ztechnetwork.handlers.JukeBox;
import com.ztechnetwork.handlers.Keys;
import com.ztechnetwork.handlers.LevelData;
import com.ztechnetwork.handlers.Mouse;
import com.ztechnetwork.handlers.ParticleFactory;
import com.ztechnetwork.main.Game;
import com.ztechnetwork.main.GamePanel;

public class PlayingState extends GameState {
	
	// background image
	private BufferedImage bg;
	
	private Player player;
	private Cursor cursor;
	private Goal goal;
	
	// time limit
	private int timer;
	private int timeLimit;
	
	// maximum number of hits allowed
	private int hitLimit;
	
	// various game objects
	private ArrayList<HitBall> hitBalls;
	private ArrayList<Bouncer> bouncers;
	private ArrayList<Hole> holes;
	private ArrayList<Spawner> spawners;
	private ArrayList<PushBall> pushBalls;
	private ArrayList<Particle> particles;
	private ArrayList<Bubble> bubbles;
	
	// random bubble timer
	private int bubbleTimer;
	
	// map dimensions
	private int levelWidth;
	private int levelHeight;
	
	// fading
	private int fadeInTimer;
	private int fadeInDelay;
	private int fadeOutTimer;
	private int fadeOutDelay;
	private int alpha;
	
	// events
	private int eventCount;
	private boolean eventFail;
	private boolean eventFinish;
	
	// buttons
	private GameButton resetButton;
	private GameButton pauseButton;
	private GameButton backButton;
	
	// other
	private int nextState;
	
	public PlayingState(GameStateManager gsm) {
		super(gsm);
		init();
	}
	
	public void init() {
		
		// set up lists
		hitBalls = new ArrayList<HitBall>();
		bouncers = LevelData.getBouncers();
		holes = LevelData.getHoles();
		spawners = LevelData.getSpawners();
		pushBalls = new ArrayList<PushBall>();
		particles = new ArrayList<Particle>();
		bubbles = new ArrayList<Bubble>();
		
		// set up player
		player = new Player();
		LevelData.setPlayer(player);
		
		// set up factories
		EnemyFactory.init(pushBalls, player);
		ParticleFactory.init(particles);
		
		// set up goal
		goal = new Goal();
		LevelData.setGoal(goal);
		
		// use custom cursor
		Game.setCursor(Game.INVISIBLE);
		cursor = new Cursor();
		
		// set up hits
		hitLimit = LevelData.getLimit();
		for(int i = 0; i < hitLimit; i++) {
			hitBalls.add(new HitBall(14 + i * 16, 14));
		}
		
		// level dimensions
		levelWidth = 640;
		levelHeight = 480;
		
		// set up bg image
		bg = ImageLoader.BG;
		
		// fading
		fadeInTimer = 0;
		fadeInDelay = 60;
		fadeOutTimer = -1;
		fadeOutDelay = 60;
		
		// events
		eventCount = 0;
		eventFail = false;
		eventFinish = false;
		
		// other
		bubbleTimer = 0;
		timeLimit = LevelData.getTime();
		timer = 0;
		
		// music
		JukeBox.stop("menumusic");
		if(!JukeBox.isPlaying("bgmusic1")) {
			JukeBox.loop("bgmusic1");
		}
		
		// buttons
		resetButton = new GameButton(10, 450);
		resetButton.setType(GameButton.LEFT);
		resetButton.setFont(LevelData.SC_FONT.deriveFont(Font.BOLD, 22f));
		resetButton.setText("Reset");
		pauseButton = new GameButton(280, 450);
		pauseButton.setType(GameButton.LEFT);
		pauseButton.setFont(LevelData.SC_FONT.deriveFont(Font.BOLD, 22f));
		pauseButton.setText("Pause");
		backButton = new GameButton(580, 450);
		backButton.setType(GameButton.LEFT);
		backButton.setFont(LevelData.SC_FONT.deriveFont(Font.BOLD, 22f));
		backButton.setText("Quit");
		
	}
	
	private void setFade() {
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
	}
	
	private void drawFade(Graphics2D g) {
		if(fadeInTimer >= 0) {
			g.setColor(new Color(255, 255, 255, 255 - alpha));
			g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		}
		if(fadeOutTimer >= 0) {
			g.setColor(new Color(255, 255, 255, alpha));
			g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		}
	}
	
	public void update() {
		
		// check if reached hit limit
		if(player.isDead()) {
			eventFail = true;
		}
		
		// check if reached goal
		if(goal.containsCircle(player)) {
			player.setPosition(goal);
			player.setVector(0, 0);
			player.reachedGoal();
			eventFinish = true;
		}
		
		// play events
		if(eventFail) {
			eventFail();
		}
		if(eventFinish) {
			eventFinish();
		}
		
		// check timer
		if(timeLimit != -1 && !eventFinish) {
			timer++;
			if(timer > timeLimit) timer = timeLimit;
			if(timer == timeLimit) {
				player.setDead();
				eventFail = true;
			}
		}
		
		// check game input
		handleInput();
		
		// update player
		player.update();
		player.fixBounds(levelWidth, levelHeight);
		
		// check if mouse hits player
		if(fadeInTimer == -1 &&
			player.checkHit(player.getx() - Mouse.x, player.gety() - Mouse.y, 5)) {
			ParticleFactory.createSmallWave(
				14 + (hitLimit - player.getNumHits()) * 16,
				14,
				8
			);
		}
		
		// check num hits left
		for(int i = hitBalls.size(); i > hitLimit - player.getNumHits(); i--) {
			hitBalls.remove(i - 1);
		}
		
		// check if player hits bouncers
		for(int i = 0; i < bouncers.size(); i++) {
			Bouncer b = bouncers.get(i);
			b.update();
			if(b.intersectsCircle(player)) {
				player.reflect(b.getx(), b.gety(), b.getWidth(), b.getImpulse());
				break;
			}
		}
		
		// update spawner
		for(int i = 0; i < spawners.size(); i++) {
			spawners.get(i).update();
		}
		
		// update push balls
		for(int i = 0; i < pushBalls.size(); i++) {
			if(pushBalls.get(i).update()) {
				pushBalls.remove(i);
				i--;
			}
		}
		
		// update holes
		for(int i = 0; i < holes.size(); i++) {
			
			Hole h = holes.get(i);
			h.update();
			
			// player fell into hole
			if(h.containsCircle(player)) {
				player.setPosition(h);
				player.setVector(0, 0);
				player.fellInHole();
				eventFail = true;
				break;
			}
			
		}
		
		// update particles
		for(int i = 0; i < particles.size(); i++) {
			if(particles.get(i).update()) {
				particles.remove(i);
				i--;
			}
		}
		
		// update cursor
		cursor.update();
		
		// update goal
		goal.update();
		
		// check bubbles
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
		
		// fading
		setFade();
		
	}
	
	public void draw(Graphics2D g) {
		
		// draw bg
		g.drawImage(bg, 0, 0, null);
		
		// draw bubbles
		for(int i = 0; i < bubbles.size(); i++) {
			bubbles.get(i).draw(g);
		}
		
		// draw bouncers
		for(int i = 0; i < bouncers.size(); i++) {
			bouncers.get(i).draw(g);
		}
		
		// draw holes
		for(int i = 0; i < holes.size(); i++) {
			holes.get(i).draw(g);
		}
		
		// draw spawner
		for(int i = 0; i < spawners.size(); i++) {
			spawners.get(i).draw(g);
		}
		
		// draw push balls
		for(int i = 0; i < pushBalls.size(); i++) {
			pushBalls.get(i).draw(g);
		}
		
		// draw goal
		goal.draw(g);
		
		// draw player
		player.draw(g);
		
		// draw cursor
		cursor.draw(g);
		
		// draw particles
		for(int i = 0; i < particles.size(); i++) {
			particles.get(i).draw(g);
		}
		
		// draw hud
		for(int i = 0; i < hitBalls.size(); i++) {
			hitBalls.get(i).draw(g);
		}
		g.setColor(Color.WHITE);
		//g.drawString("(" + Mouse.x + ", " + Mouse.y + ")", 480, 430);
		g.setFont(LevelData.SC_FONT.deriveFont(Font.BOLD, 20f));
		g.drawString(Integer.toString(GameData.getCurrentScore()), 600, 22);
		if(timeLimit != -1) {
			int sec = (timeLimit - timer) / 60;
			int mil = (int) ((timeLimit - timer) % 60 * 100.0 / 60);
			String s = sec + ":";
			if(mil < 10) s += "0" + mil;
			else s += mil;
			g.drawString(s, 10, 42);
		}
		
		// draw buttons
		resetButton.draw(g);
		pauseButton.draw(g);
		backButton.draw(g);
		
		// draw fade
		drawFade(g);
		
	}
	
	public void handleInput() {
		
		// restart level
		if(Keys.isPressed(Keys.R)) {
			reset();
		}
		
		// go back to menu
		if(Keys.isPressed(Keys.ESCAPE) && fadeOutTimer == -1) {
			nextState = GameStateManager.LEVEL_SELECT_STATE;
			fadeInTimer = -1;
			fadeOutTimer = 0;
		}
		if(Keys.isPressed(Keys.W)) {JukeBox.play("windowsstart");
		
		}
		
		// button
		if(resetButton.isHovering(Mouse.x, Mouse.y)) {
			resetButton.setHover(true);
		}
		else {
			resetButton.setHover(false);
		}
		if(Mouse.isPressed() && resetButton.isHovered()) {
			reset();JukeBox.play("restart");
		}
		if(pauseButton.isHovering(Mouse.x, Mouse.y)) {
			pauseButton.setHover(true);
		}
		else {
			pauseButton.setHover(false);
		}
		if(Mouse.isPressed() && pauseButton.isHovered() && fadeOutTimer == -1) {JukeBox.play("menuclick");
			fadeInTimer = -1;
			fadeOutTimer = 0;
			nextState = GameStateManager.PAUSE_GAME_STATE;
			
		}
		if(backButton.isHovering(Mouse.x, Mouse.y)) {
			backButton.setHover(true);
		}
		else {
			backButton.setHover(false);
		}
		if(Mouse.isPressed() && backButton.isHovered() && fadeOutTimer == -1) {JukeBox.play("menuclick");
			fadeInTimer = -1;
			fadeOutTimer = 0;
			nextState = GameStateManager.LEVEL_SELECT_STATE;
		}
		
	}
	
	private void reset() {
		if(eventFinish) return;
		GameData.addScore(-1);
		init();
	}
	
	private void eventFail() {
		
		eventCount++;
		
		// restart level
		if(eventCount == 60) {
			reset();
		}
		
	}
	
	private void eventFinish() {
		
		eventCount++;
		
		if(eventCount == 1) {
			for(int i = 0; i < hitBalls.size(); i++) {
				hitBalls.get(i).setVector(5, 0);
			}
		}
		
		if(eventCount > 30 && hitBalls.size() > 0) {
			for(int i = 0; i < hitBalls.size(); i++) {
				HitBall hb = hitBalls.get(i);
				hb.update();
				if(hb.getx() > 600) {
					GameData.addScore(1);
					hitBalls.remove(i);
					i--;
					ParticleFactory.createSmallWave(hb.getx(), hb.gety(), hb.getWidth());
				}
			}
			eventCount--;
		}
		
		if(eventCount >= 60 && fadeOutTimer == -1) {
			
			fadeInTimer = -1;
			fadeOutTimer = 0;
			
			// finished level
			if((LevelData.getLevelIndex() + 1) % 4 == 0) {
				nextState = GameStateManager.LEVEL_SELECT_STATE;
				GameData.setFinalScore(LevelData.getLevelIndex() / 4);
				GameData.save();
			}
			
			// next part of level
			else {
				nextState = GameStateManager.LEVEL_INFO_STATE;
				LevelData.nextLevel();
			}
			
		}
		
	}
	
}
