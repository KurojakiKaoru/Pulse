/**
 * If the player falls into a hole,
 * then the level resets.
 */

package com.ztechnetwork.entity;

import java.awt.Color;
import java.awt.Graphics2D;

import com.ztechnetwork.handlers.ParticleFactory;

public class Hole extends GameObject {
	
	private int tick;
	
	public Hole() {
		width = height = 100;
		color = new Color(0, 0, 255, 64);
		colorBorder = Color.GRAY;
		tick = 0;
	}
	
	public Hole(double x, double y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		color = new Color(0, 0, 255, 64);
		colorBorder = Color.GRAY;
		tick = 0;
	}
	
	public void update() {
		tick++;
		if(tick == 60) {
			tick = 0;
			ParticleFactory.createWave(x, y, width, color);
		}
	}
	
	public void draw(Graphics2D g) {
		drawCircleNoBorder(g);
	}
	
}
