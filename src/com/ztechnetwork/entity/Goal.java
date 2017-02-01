/**
 * Player must reach goal to advance
 * to the next level.
 */

package com.ztechnetwork.entity;

import java.awt.Color;
import java.awt.Graphics2D;

import com.ztechnetwork.handlers.ParticleFactory;

public class Goal extends GameObject {
	
	private int tick;
	
	public Goal() {
		width = height = 100;
		color = new Color(255, 128, 128, 124);
		colorBorder = new Color(color.getRed(), color.getGreen(), color.getBlue());
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
