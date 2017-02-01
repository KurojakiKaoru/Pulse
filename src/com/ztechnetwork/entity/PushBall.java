/**
 * Not yet implemented.
 * This class actively seeks out the player
 * and upon contact, pushes the player like the
 * cursor does.
 */

package com.ztechnetwork.entity;

import java.awt.Color;
import java.awt.Graphics2D;

import com.ztechnetwork.handlers.ParticleFactory;

public class PushBall extends GameObject {
	
	private Player player;
	private double speed;
	private int tick = 0;
	
	public PushBall(double x, double y, double speed, Player player) {
		this.x = x;
		this.y = y;
		this.player = player;
		width = height = 8;
		this.speed = speed;
		color = new Color(255, 0, 0, 64);
		colorBorder = Color.GREEN;
	}
	
	public boolean update() {
		tick++;
		if(tick == 10) {
			dx = player.getx() - x;
			dy = player.gety() - y;
			double dist = Math.sqrt(dx * dx + dy * dy);
			dx /= dist;
			dy /= dist;
			dx *= speed;
			dy *= speed;
			tick = 0;
		}
		x += dx;
		y += dy;
		if(player.isReady() && intersectsCircle(player)) {
			player.checkHit(player.getx() - x, player.gety() - y, width);
			ParticleFactory.createSmallWave(x, y, width, colorBorder);
			return true;
		}
		return false;
	}
	
	public void draw(Graphics2D g) {
		drawCircle(g);
	}
	
}
