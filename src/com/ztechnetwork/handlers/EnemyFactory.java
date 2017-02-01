/**
 * Handles the creation of PushBalls.
 */

package com.ztechnetwork.handlers;

import java.util.ArrayList;

import com.ztechnetwork.entity.Player;
import com.ztechnetwork.entity.PushBall;

public class EnemyFactory {
	
	private static ArrayList<PushBall> pushBalls;
	private static Player player;
	
	public static void init(ArrayList<PushBall> pb, Player p) {
		pushBalls = pb;
		player = p;
	}
	
	public static void spawn(double x, double y, double speed) {
		pushBalls.add(new PushBall(x, y, speed, player));
	}
	
}
