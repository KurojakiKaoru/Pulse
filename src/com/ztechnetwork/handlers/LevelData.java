/**
 * This class contains all the level information
 * and which level is currently being played.
 * Level information includes:
 * 	- player start position
 * 	- goal position
 * 	- number of hits allowed
 * 	- any GameObjects
 * 	- level title
 * 	- description of the level
 */

package com.ztechnetwork.handlers;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import com.ztechnetwork.entity.Bouncer;
import com.ztechnetwork.entity.Goal;
import com.ztechnetwork.entity.Hole;
import com.ztechnetwork.entity.Player;
import com.ztechnetwork.entity.Spawner;

public class LevelData {
	
	private static String currentLevel;
	
	private static HashMap<String, Point> playerList;
	private static HashMap<String, Point> goalList;
	private static HashMap<String, Integer> limitList;
	private static HashMap<String, ArrayList<Bouncer>> bouncerList;
	private static HashMap<String, ArrayList<Hole>> holeList;
	private static HashMap<String, ArrayList<Spawner>> spawnerList;
	
	private static ArrayList<String> levelList;
	private static HashMap<String, String[]> levelDescriptions;
	private static int levelIndex;
	
	public static final int MAX_LEVELS = 10;
	public static final String LEVEL1_1 = "Basic   1";
	public static final String LEVEL1_2 = "Basic   2";
	public static final String LEVEL1_3 = "Basic   3";
	public static final String LEVEL1_4 = "Basic   4";
	public static final String LEVEL2_1 = "Needle   1";
	public static final String LEVEL2_2 = "Needle   2";
	public static final String LEVEL2_3 = "Needle   3";
	public static final String LEVEL2_4 = "Needle   4";
	public static final String LEVEL3_1 = "Inertia   1";
	public static final String LEVEL3_2 = "Inertia   2";
	public static final String LEVEL3_3 = "Inertia   3";
	public static final String LEVEL3_4 = "Inertia   4";
	public static final String LEVEL4_1 = "Path   1";
	public static final String LEVEL4_2 = "Path   2";
	public static final String LEVEL4_3 = "Path   3";
	public static final String LEVEL4_4 = "Path   4";
	public static final String LEVEL5_1 = "Speed   1";
	public static final String LEVEL5_2 = "Speed   2";
	public static final String LEVEL5_3 = "Speed   3";
	public static final String LEVEL5_4 = "Speed   4";
	public static final String LEVEL6_1 = "Foe   1";
	public static final String LEVEL6_2 = "Foe   2";
	public static final String LEVEL6_3 = "Foe   3";
	public static final String LEVEL6_4 = "Foe   4";
	public static final String LEVEL7_1 = "Force   1";
	public static final String LEVEL7_2 = "Force   2";
	public static final String LEVEL7_3 = "Force   3";
	public static final String LEVEL7_4 = "Force   4";
	public static final String LEVEL8_1 = "Mastery   1";
	public static final String LEVEL8_2 = "Mastery   2";
	public static final String LEVEL8_3 = "Mastery   3";
	public static final String LEVEL8_4 = "Mastery   4";
	public static final String LEVEL9_1 = "Expert   1";
	public static final String LEVEL9_2 = "Expert   2";
	public static final String LEVEL9_3 = "Expert   3";
	public static final String LEVEL9_4 = "Expert   4";
	public static final String LEVEL10_1 = "Demon   1";
	public static final String LEVEL10_2 = "Demon   2";
	public static final String LEVEL10_3 = "Demon   3";
	public static final String LEVEL10_4 = "Demon   4";
	
	
	public static final BasicStroke STROKE_1 = new BasicStroke(1);
	public static final BasicStroke STROKE_2 = new BasicStroke(2);
	public static final BasicStroke STROKE_3 = new BasicStroke(3);
	
	public static Font SC_FONT;
	public static Font LEVEL_INFO_FONT;
	
	public static void init() {
		
		try {
			SC_FONT = Font.createFont(
					Font.TRUETYPE_FONT,
					LevelData.class.getResourceAsStream("/fonts/zorque.ttf"));
			//HUD_FONT = SC_FONT.deriveFont(Font.PLAIN, 20f);
			LEVEL_INFO_FONT = Font.createFont(
					Font.TRUETYPE_FONT,
					LevelData.class.getResourceAsStream("/fonts/neuropolxrg.ttf"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		levelList = new ArrayList<String>();
		levelList.add(LEVEL1_1);
		levelList.add(LEVEL1_2);
		levelList.add(LEVEL1_3);
		levelList.add(LEVEL1_4);
		levelList.add(LEVEL2_1);
		levelList.add(LEVEL2_2);
		levelList.add(LEVEL2_3);
		levelList.add(LEVEL2_4);
		levelList.add(LEVEL3_1);
		levelList.add(LEVEL3_2);
		levelList.add(LEVEL3_3);
		levelList.add(LEVEL3_4);
		levelList.add(LEVEL4_1);
		levelList.add(LEVEL4_2);
		levelList.add(LEVEL4_3);
		levelList.add(LEVEL4_4);
		levelList.add(LEVEL5_1);
		levelList.add(LEVEL5_2);
		levelList.add(LEVEL5_3);
		levelList.add(LEVEL5_4);
		levelList.add(LEVEL6_1);
		levelList.add(LEVEL6_2);
		levelList.add(LEVEL6_3);
		levelList.add(LEVEL6_4);
		levelList.add(LEVEL7_1);
		levelList.add(LEVEL7_2);
		levelList.add(LEVEL7_3);
		levelList.add(LEVEL7_4);
		levelList.add(LEVEL8_1);
		levelList.add(LEVEL8_2);
		levelList.add(LEVEL8_3);
		levelList.add(LEVEL8_4);
		levelList.add(LEVEL9_1);
		levelList.add(LEVEL9_2);
		levelList.add(LEVEL9_3);
		levelList.add(LEVEL9_4);
		levelList.add(LEVEL10_1);
		levelList.add(LEVEL10_2);
		levelList.add(LEVEL10_3);
		levelList.add(LEVEL10_4);
		
		playerList = new HashMap<String, Point>();
		goalList = new HashMap<String, Point>();
		limitList = new HashMap<String, Integer>();
		bouncerList = new HashMap<String, ArrayList<Bouncer>>();
		holeList = new HashMap<String, ArrayList<Hole>>();
		spawnerList = new HashMap<String, ArrayList<Spawner>>();
		levelDescriptions = new HashMap<String, String[]>();
		
		ArrayList<Bouncer> bouncers;
		ArrayList<Hole> holes;
		ArrayList<Spawner> spawners;
		String[] descriptions;
		
		// basic 1
		playerList.put(LEVEL1_1, new Point(100, 100));
		goalList.put(LEVEL1_1, new Point(500, 380));
		limitList.put(LEVEL1_1, 14);
		bouncers = new ArrayList<Bouncer>();
		bouncers.add(new Bouncer(100, 400, 200, 200));
		bouncers.add(new Bouncer(240, 100, 120, 120));
		bouncers.add(new Bouncer(340, 340, 170, 170));
		bouncers.add(new Bouncer(470, 180, 140, 140));
		bouncerList.put(LEVEL1_1, bouncers);
		
		// basic 2
		playerList.put(LEVEL1_2, new Point(80, 80));
		goalList.put(LEVEL1_2, new Point(550, 410));
		limitList.put(LEVEL1_2, 20);
		bouncers = new ArrayList<Bouncer>();
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 2; j++) {
				bouncers.add(new Bouncer(160 + 100 * i, 160 + 160 * j, 70, 70));
			}
		}
		bouncers.add(new Bouncer(60, 160, 70, 70));
		bouncers.add(new Bouncer(560, 320, 70, 70));
		bouncerList.put(LEVEL1_2, bouncers);
		
		// basic 3
		playerList.put(LEVEL1_3, new Point(70, 100));
		goalList.put(LEVEL1_3, new Point(500, 400));
		limitList.put(LEVEL1_3, 9);
		bouncers = new ArrayList<Bouncer>();
		bouncers.add(new Bouncer(80, 280, 150, 150));
		bouncers.add(new Bouncer(270, 400, 130, 130));
		bouncers.add(new Bouncer(400, 80, 150, 150));
		bouncers.add(new Bouncer(560, 280, 150, 150));
		bouncerList.put(LEVEL1_3, bouncers);
		holes = new ArrayList<Hole>();
		holes.add(new Hole(200, 100, 150, 150));
		holes.add(new Hole(340, 270, 150, 150));
		holeList.put(LEVEL1_3, holes);
		
		// basic 4
		playerList.put(LEVEL1_4, new Point(70, 100));
		goalList.put(LEVEL1_4, new Point(560, 100));
		limitList.put(LEVEL1_4, 10);
		bouncers = new ArrayList<Bouncer>();
		bouncers.add(new Bouncer(60, 280, 100, 100));
		bouncers.add(new Bouncer(190, 420, 100, 100));
		bouncers.add(new Bouncer(450, 420, 100, 100));
		bouncers.add(new Bouncer(580, 280, 100, 100));
		bouncerList.put(LEVEL1_4, bouncers);
		holes = new ArrayList<Hole>();
		holes.add(new Hole(320, 180, 340, 340));
		holeList.put(LEVEL1_4, holes);
		
		// marksman 1
		playerList.put(LEVEL2_1, new Point(100, 240));
		goalList.put(LEVEL2_1, new Point(540, 240));
		limitList.put(LEVEL2_1, 8);
		bouncers = new ArrayList<Bouncer>();
		for(int i = 0; i < 7; i++) {
			if(i == 3) continue;
			bouncers.add(new Bouncer(320, 30 + 71 * i, 71, 71));
		}
		bouncerList.put(LEVEL2_1, bouncers);
		
		// marksman 2
		playerList.put(LEVEL2_2, new Point(100, 380));
		goalList.put(LEVEL2_2, new Point(540, 380));
		limitList.put(LEVEL2_2, 10);
		bouncers = new ArrayList<Bouncer>();
		bouncers.add(new Bouncer(320, 240, 100, 100));
		bouncers.add(new Bouncer(160, 80, 200, 200));
		bouncers.add(new Bouncer(480, 80, 200, 200));
		bouncerList.put(LEVEL2_2, bouncers);
		holes = new ArrayList<Hole>();
		holes.add(new Hole(320, 380, 250, 250));
		holeList.put(LEVEL2_2, holes);
		
		// marksman 3
		playerList.put(LEVEL2_3, new Point(100, 100));
		goalList.put(LEVEL2_3, new Point(560, 400));
		limitList.put(LEVEL2_3, 10);
		bouncers = new ArrayList<Bouncer>();
		bouncers.add(new Bouncer(80, 240, 100, 100));
		bouncers.add(new Bouncer(210, 60, 100, 100));
		bouncers.add(new Bouncer(330, 100, 100, 100));
		bouncers.add(new Bouncer(270, 300, 100, 100));
		bouncers.add(new Bouncer(410, 390, 130, 120));
		bouncers.add(new Bouncer(550, 250, 130, 120));
		bouncerList.put(LEVEL2_3, bouncers);
		holes = new ArrayList<Hole>();
		holes.add(new Hole(160, 140, 100, 100));
		holes.add(new Hole(300, 180, 100, 100));
		holes.add(new Hole(640, 480, 250, 250));
		holeList.put(LEVEL2_3, holes);
		
		// marksman 4
		playerList.put(LEVEL2_4, new Point(320, 50));
		goalList.put(LEVEL2_4, new Point(320, 400));
		limitList.put(LEVEL2_4, 5);
		bouncers = new ArrayList<Bouncer>();
		for(int i = 0; i < 11; i++) {
			for(int j = 0; j < 6; j++) {
				if(j % 2 != 0 || i == 5) continue;
				bouncers.add(new Bouncer(64 * i, 104 + 64 * j, 64, 64));
			}
		}
		bouncerList.put(LEVEL2_4, bouncers);
		
		// inertia 1
		playerList.put(LEVEL3_1, new Point(80, 80));
		goalList.put(LEVEL3_1, new Point(560, 80));
		limitList.put(LEVEL3_1, 7);
		descriptions = new String[3];
		descriptions[0] = "- Frictionless";
		descriptions[1] = "- 2 second delay";
		descriptions[2] = "- Weak push";
		levelDescriptions.put(LEVEL3_1, descriptions);
		bouncers = new ArrayList<Bouncer>();
		bouncers.add(new Bouncer(320, 140, 300, 300));
		bouncerList.put(LEVEL3_1, bouncers);
		holes = new ArrayList<Hole>();
		holes.add(new Hole(106, 440, 150, 150));
		holes.add(new Hole(212, 440, 150, 150));
		holes.add(new Hole(320, 440, 150, 150));
		holes.add(new Hole(426, 440, 150, 150));
		holes.add(new Hole(542, 440, 150, 150));
		holeList.put(LEVEL3_1, holes);
		
		// inertia 2
		playerList.put(LEVEL3_2, new Point(80, 380));
		goalList.put(LEVEL3_2, new Point(560, 80));
		limitList.put(LEVEL3_2, 9);
		descriptions = new String[2];
		descriptions[0] = "- Frictionless";
		descriptions[1] = "- Timed: 20 sec";
		levelDescriptions.put(LEVEL3_2, descriptions);
		bouncers = new ArrayList<Bouncer>();
		bouncers.add(new Bouncer(220, 180, 100, 100));
		bouncers.add(new Bouncer(380, 240, 100, 100));
		bouncers.add(new Bouncer(200, 400, 100, 100));
		bouncers.add(new Bouncer(350, 100, 100, 100));
		bouncers.add(new Bouncer(550, 400, 100, 100));
		bouncerList.put(LEVEL3_2, bouncers);
		
		// inertia 3
		playerList.put(LEVEL3_3, new Point(80, 380));
		goalList.put(LEVEL3_3, new Point(560, 80));
		limitList.put(LEVEL3_3, 14);
		descriptions = new String[3];
		descriptions[0] = "- Frictionless";
		descriptions[1] = "- Timed: 20 sec";
		descriptions[2] = "- Strong push";
		levelDescriptions.put(LEVEL3_3, descriptions);
		bouncers = new ArrayList<Bouncer>();
		bouncers.add(new Bouncer(320, 240, 50, 50));
		bouncers.add(new Bouncer(106, 100, 70, 70));
		bouncers.add(new Bouncer(212, 200, 80, 80));
		bouncers.add(new Bouncer(320, 410, 60, 60));
		bouncers.add(new Bouncer(542, 200, 70, 70));
		bouncerList.put(LEVEL3_3, bouncers);
		
		// inertia 4
		playerList.put(LEVEL3_4, new Point(150, 240));
		goalList.put(LEVEL3_4, new Point(490, 240));
		limitList.put(LEVEL3_4, 1);
		descriptions = new String[3];
		descriptions[0] = "- Frictionless";
		descriptions[1] = "- Timed: 15 sec";
		descriptions[2] = "- One chance";
		levelDescriptions.put(LEVEL3_4, descriptions);
		bouncers = new ArrayList<Bouncer>();
		bouncers.add(new Bouncer(320, 240, 100, 100));
		bouncerList.put(LEVEL3_4, bouncers);
		
		// path 1
		playerList.put(LEVEL4_1, new Point(230, 60));
		goalList.put(LEVEL4_1, new Point(410, 60));
		limitList.put(LEVEL4_1, 12);
		bouncers = new ArrayList<Bouncer>();
		bouncers.add(new Bouncer(320, 240, 300, 300));
		for(int i = 0; i < 7; i++) {
			double radians = Math.toRadians(-135 - 45 * i);
			int x = (int) (Math.cos(radians) * 280 + 320);
			int y = (int) (Math.sin(radians) * 280 + 240);
			bouncers.add(new Bouncer(x, y, 100, 100));
		}
		bouncerList.put(LEVEL4_1, bouncers);
		holes = new ArrayList<Hole>();
		holes.add(new Hole(320, 40, 100, 100));
		holeList.put(LEVEL4_1, holes);
		
		// path 2
		playerList.put(LEVEL4_2, new Point(80, 100));
		goalList.put(LEVEL4_2, new Point(560, 240));
		limitList.put(LEVEL4_2, 10);
		bouncers = new ArrayList<Bouncer>();
		bouncers.add(new Bouncer(320, 240, 100, 100, 0, 3));
		bouncerList.put(LEVEL4_2, bouncers);
		holes = new ArrayList<Hole>();
		holes.add(new Hole(320, 0, 450, 450));
		holes.add(new Hole(320, 480, 450, 450));
		holeList.put(LEVEL4_2, holes);
		
		// path 3
		playerList.put(LEVEL4_3, new Point(80, 300));
		goalList.put(LEVEL4_3, new Point(560, 140));
		limitList.put(LEVEL4_3, 12);
		bouncers = new ArrayList<Bouncer>();
		for(int i = 0; i < 7; i++) {
			double PI2 = Math.PI * 2;
			int x = 40 + 80 * i;
			int y = (int) (100.0 * Math.sin((1.0 * x / 640) * PI2));
			bouncers.add(new Bouncer(x, y + 120, 100, 100));
			bouncers.add(new Bouncer(x, y + 340, 100, 100));
		}
		bouncerList.put(LEVEL4_3, bouncers);
		
		// path 4
		playerList.put(LEVEL4_4, new Point(105, 112));
		goalList.put(LEVEL4_4, new Point(416, 240));
		limitList.put(LEVEL4_4, 24);
		bouncers = new ArrayList<Bouncer>();
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 7; j++) {
				if((j == 1 && i > 0 && i < 9) ||
					(i == 8 && j > 0 && j < 6) ||
					(j == 5 && i > 0 && i < 9) ||
					(i == 1 && j == 4) ||
					(j == 3 && i > 0 && i < 7) ||
					(j == 0) ||
					(j == 1)) continue;
				bouncers.add(new Bouncer(32 + 64 * i, 48 + 64 * j, 64, 64));
			}
		}
		bouncerList.put(LEVEL4_4, bouncers);
		
		// speed 1
		playerList.put(LEVEL5_1, new Point(80, 80));
		goalList.put(LEVEL5_1, new Point(560, 400));
		limitList.put(LEVEL5_1, 15);
		descriptions = new String[1];
		descriptions[0] = "- Timed: 15 sec";
		levelDescriptions.put(LEVEL5_1, descriptions);
		bouncers = new ArrayList<Bouncer>();
		bouncers.add(new Bouncer(200, 100, 100, 100));
		bouncers.add(new Bouncer(200, 250, 100, 100));
		bouncers.add(new Bouncer(440, 380, 100, 100));
		bouncers.add(new Bouncer(440, 230, 100, 100));
		bouncerList.put(LEVEL5_1, bouncers);
		
		// speed 2
		playerList.put(LEVEL5_2, new Point(80, 240));
		goalList.put(LEVEL5_2, new Point(560, 240));
		limitList.put(LEVEL5_2, 12);
		descriptions = new String[2];
		descriptions[0] = "- Timed: 12 sec";
		descriptions[1] = "- Strong push";
		levelDescriptions.put(LEVEL5_2, descriptions);
		bouncers = new ArrayList<Bouncer>();
		bouncers.add(new Bouncer(230, 180, 20, 20));
		bouncers.add(new Bouncer(160, 250, 20, 20));
		bouncers.add(new Bouncer(380, 340, 20, 20));
		bouncers.add(new Bouncer(440, 120, 20, 20));
		bouncers.add(new Bouncer(140, 400, 20, 20));
		bouncers.add(new Bouncer(310, 300, 20, 20));
		bouncers.add(new Bouncer(80, 100, 20, 20));
		bouncers.add(new Bouncer(320, 80, 20, 20));
		bouncers.add(new Bouncer(420, 240, 20, 20));
		bouncers.add(new Bouncer(520, 400, 20, 20));
		bouncers.add(new Bouncer(260, 390, 20, 20));
		bouncerList.put(LEVEL5_2, bouncers); 
		
		// speed 3
		playerList.put(LEVEL5_3, new Point(80, 400));
		goalList.put(LEVEL5_3, new Point(560, 400));
		limitList.put(LEVEL5_3, 20);
		descriptions = new String[1];
		descriptions[0] = "- Timed: 15 sec";
		levelDescriptions.put(LEVEL5_3, descriptions);
		bouncers = new ArrayList<Bouncer>();
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 7; j++) {
				if((i == 1 && j >= 1 && j <= 5) ||
					((i == 2 || i == 3) && (j == 1 || j == 5)) ||
					(i == 4 && (j <= 1 || j >= 5))) continue;
				bouncers.add(new Bouncer(20 + j * 100, i * 100, 100, 100));
			}
		}
		bouncers.add(new Bouncer(320, 240, 20, 20, 4, 0));
		bouncers.add(new Bouncer(320, 240, 20, 20, 0, 4));
		bouncerList.put(LEVEL5_3, bouncers);
		
		// speed 4
		playerList.put(LEVEL5_4, new Point(560, 80));
		goalList.put(LEVEL5_4, new Point(560, 400));
		limitList.put(LEVEL5_4, 20);
		descriptions = new String[1];
		descriptions[0] = "- Timed: 15 sec";
		levelDescriptions.put(LEVEL5_4, descriptions);
		bouncers = new ArrayList<Bouncer>();
		for(int i = 0; i < 6; i++) {
			bouncers.add(new Bouncer(600 - 80 * i, 240, 80, 80));
		}
		bouncers.add(new Bouncer(320, 240, 20, 20, 3, 1));
		bouncers.add(new Bouncer(320, 240, 20, 20, -1, -3));
		bouncers.add(new Bouncer(320, 240, 20, 20, 2, -2));
		bouncers.add(new Bouncer(320, 240, 20, 20, -2, 2));
		bouncerList.put(LEVEL5_4, bouncers);
		
		// foe 1
		playerList.put(LEVEL6_1, new Point(80, 80));
		goalList.put(LEVEL6_1, new Point(80, 400));
		limitList.put(LEVEL6_1, 14);
		bouncers = new ArrayList<Bouncer>();
		for(int i = 0; i < 6; i++) {
			bouncers.add(new Bouncer(40 + 80 * i, 240, 80, 80));
		}
		bouncerList.put(LEVEL6_1, bouncers);
		spawners = new ArrayList<Spawner>();
		spawners.add(new Spawner(320, 240, 100, 100, 120, 1.8));
		spawnerList.put(LEVEL6_1, spawners);
		
		// foe 2
		playerList.put(LEVEL6_2, new Point(80, 240));
		goalList.put(LEVEL6_2, new Point(560, 240));
		limitList.put(LEVEL6_2, 10);
		spawners = new ArrayList<Spawner>();
		spawners.add(new Spawner(320, 50, 80, 80, 120, 1.8));
		spawners.add(new Spawner(320, 430, 80, 80, 120, 1.8));
		spawnerList.put(LEVEL6_2, spawners);
		bouncers = new ArrayList<Bouncer>();
		bouncers.add(new Bouncer(320, 50, 100, 100));
		bouncers.add(new Bouncer(320, 430, 100, 100));
		bouncers.add(new Bouncer(320, 240, 30, 30));
		bouncerList.put(LEVEL6_2, bouncers);
		holes = new ArrayList<Hole>();
		holeList.put(LEVEL6_2, holes);
		
		// foe 3
		playerList.put(LEVEL6_3, new Point(80, 240));
		goalList.put(LEVEL6_3, new Point(560, 240));
		limitList.put(LEVEL6_3, 12);
		spawners = new ArrayList<Spawner>();
		spawners.add(new Spawner(320, 240, 80, 80, 90, 2));
		spawnerList.put(LEVEL6_3, spawners);
		bouncers = new ArrayList<Bouncer>();
		bouncers.add(new Bouncer(320, 240, 100, 100));
		bouncers.add(new Bouncer(320, 160, 30, 30, 2, 0));
		bouncers.add(new Bouncer(320, 320, 30, 30, -2, 0));
		bouncers.add(new Bouncer(213, 240, 30, 30, 0, 2));
		bouncers.add(new Bouncer(416, 240, 30, 30, 0, -2));
		bouncers.add(new Bouncer(320, 120, 30, 30));
		bouncers.add(new Bouncer(320, 360, 30, 30));
		bouncerList.put(LEVEL6_3, bouncers);
		
		// foe 4
		playerList.put(LEVEL6_4, new Point(80, 240));
		goalList.put(LEVEL6_4, new Point(560, 240));
		limitList.put(LEVEL6_4, 20);
		spawners = new ArrayList<Spawner>();
		bouncers = new ArrayList<Bouncer>();
		bouncers.add(new Bouncer(320, 240, 100, 100));
		bouncerList.put(LEVEL6_4, bouncers);
		spawners.add(new Spawner(80, 80, 80, 80, 60, 2));
		spawners.add(new Spawner(80, 400, 80, 80, 60, 2));
		spawners.add(new Spawner(560, 80, 80, 80, 60, 2));
		spawners.add(new Spawner(560, 400, 80, 80, 60, 2));
		spawnerList.put(LEVEL6_4, spawners);
		
		// force 1
		playerList.put(LEVEL7_1, new Point(80, 400));
		goalList.put(LEVEL7_1, new Point(560, 80));
		limitList.put(LEVEL7_1, 14);
		descriptions = new String[1];
		descriptions[0] = "- Pull down";
		levelDescriptions.put(LEVEL7_1, descriptions);
		bouncers = new ArrayList<Bouncer>();
		bouncers.add(new Bouncer(320, 240, 120, 120));
		bouncers.add(new Bouncer(220, 390, 100, 100));
		bouncers.add(new Bouncer(90, 80, 100, 100));
		bouncers.add(new Bouncer(340, 50, 100, 100));
		bouncers.add(new Bouncer(540, 380, 100, 100));
		bouncers.add(new Bouncer(500, 210, 100, 100));
		bouncers.add(new Bouncer(80, 270, 100, 100));
		bouncerList.put(LEVEL7_1, bouncers);
		
		// force 2
		playerList.put(LEVEL7_2, new Point(80, 80));
		goalList.put(LEVEL7_2, new Point(560, 400));
		limitList.put(LEVEL7_2, 20);
		descriptions = new String[2];
		descriptions[0] = "- Pull down";
		descriptions[1] = "- Timed: 30 sec";
		levelDescriptions.put(LEVEL7_2, descriptions);
		bouncers = new ArrayList<Bouncer>();
		for(int i = 1; i < 6; i++) {
			for(int j = 0; j < 8; j++) {
				if((i + j) % 2 == 0) continue;
				bouncers.add(new Bouncer(80 * j, 80 * i, 30, 30));
			}
		}
		bouncerList.put(LEVEL7_2, bouncers);
		holes = new ArrayList<Hole>();
		for(int i = 0; i < 3; i++) {
			holes.add(new Hole(80 + 160 * i, 400, 130, 130));
		}
		holeList.put(LEVEL7_2, holes);
		
		// force 3
		playerList.put(LEVEL7_3, new Point(80, 400));
		goalList.put(LEVEL7_3, new Point(560, 100));
		limitList.put(LEVEL7_3, 10);
		descriptions = new String[1];
		descriptions[0] = "- Pull up";
		levelDescriptions.put(LEVEL7_3, descriptions);
		bouncers = new ArrayList<Bouncer>();
		for(int i = 0; i < 7; i++) {
			bouncers.add(new Bouncer(120 + 80 * i, 0, 100, 100));
		}
		bouncerList.put(LEVEL7_3, bouncers);
		holes = new ArrayList<Hole>();
		for(int i = 0; i < 7; i++) {
			holes.add(new Hole(120 + 80 * i, 140, 100, 100));
		}
		holeList.put(LEVEL7_3, holes);
		
		// force 4
		playerList.put(LEVEL7_4, new Point(80, 240));
		goalList.put(LEVEL7_4, new Point(560, 240));
		limitList.put(LEVEL7_4, 20);
		descriptions = new String[1];
		descriptions[0] = "- Pull left";
		levelDescriptions.put(LEVEL7_4, descriptions);
		spawners = new ArrayList<Spawner>();
		spawners.add(new Spawner(560, 120, 100, 100, 120, 2));
		spawners.add(new Spawner(560, 360, 100, 100, 120, 2));
		spawnerList.put(LEVEL7_4, spawners);
		
		// mastery 1
		playerList.put(LEVEL8_1, new Point (80,240));
		goalList.put(LEVEL8_1, new Point(560, 240));
		limitList.put(LEVEL8_1, 1);
		descriptions = new String[4];
		descriptions[0] = "- Frictionless";
		descriptions[1] = "- Timed: 5 sec";
		descriptions[2] = "- Strong push";
		descriptions[3] = "- One chance";
		levelDescriptions.put(LEVEL8_1, descriptions);
		bouncers = new ArrayList<Bouncer>();
		for(int i = 0; i < 7; i++) {
			if(i == 3) continue;
			bouncers.add(new Bouncer(320, 30 + 71 * i, 85, 85));
		}
		bouncerList.put(LEVEL8_1, bouncers);
		
		// mastery 2
		playerList.put(LEVEL8_2, new Point (80,240));
		goalList.put(LEVEL8_2, new Point(560, 240));
		limitList.put(LEVEL8_2, 5);
		descriptions = new String[4];
		descriptions[0] = "- Frictionless";
		descriptions[1] = "- Timed: 10 sec";
		descriptions[2]	= "-Strong Push";
		descriptions[3]	= "-5 chances";
		levelDescriptions.put(LEVEL8_2, descriptions);
		bouncers = new ArrayList<Bouncer>();
		for(int i = 1; i < 6; i++) {
			for(int j = 0; j < 8; j++) {
				if((i + j) % 2 == 0) continue;
				bouncers.add(new Bouncer(80 * j, 80 * i, 30, 30));
			}
		}
		bouncerList.put(LEVEL8_2, bouncers);
		
		// mastery 3
		playerList.put(LEVEL8_3, new Point (80,240));
		goalList.put(LEVEL8_3, new Point(560, 240));
		limitList.put(LEVEL8_3, 7);
		descriptions = new String[4];
		descriptions[0] = "- Gravity";
		descriptions[1] = "- 10 sec";
		descriptions[2] = "- 7 chances";
		descriptions[3] = "- Harder Path Level";
		levelDescriptions.put(LEVEL8_3, descriptions);
		bouncers = new ArrayList<Bouncer>();
		bouncers.add(new Bouncer(320, 240, 100, 100, 0, 3));
		bouncerList.put(LEVEL8_3, bouncers);
		holes = new ArrayList<Hole>();
		holes.add(new Hole(320, 0, 450, 450));
		holes.add(new Hole(320, 480, 450, 450));
		holeList.put(LEVEL8_3, holes);
		
		
		// mastery 4
		playerList.put(LEVEL8_4, new Point (320, 50));
		goalList.put(LEVEL8_4, new Point(320, 400));
		limitList.put(LEVEL8_4, 25);
		descriptions = new String[3];
		descriptions[0] = "- Spawners";
		descriptions[1] = "- A lot of tries";
		descriptions[2] = "- If you complete this you are a Master at this game";
		levelDescriptions.put(LEVEL8_4, descriptions);
		
	}
	
	public static void setLevel(String s) {
		currentLevel = s;
		for(int i = 0; i < levelList.size(); i++) {
			if(levelList.get(i).equals(s)) {
				levelIndex = i;
				break;
			}
		}
	}
	
	public static String getLevel() {
		return currentLevel;
	}
	
	public static String getLevel(int i) {
		if(i >= levelList.size()) return "--   ";
		return levelList.get(i);
	}
	
	public static int getLevelIndex() {
		for(int i = 0; i < levelList.size(); i++) {
			if(levelList.get(i).equals(currentLevel)) {
				return i;
			}
		}
		return -1;
	}
	
	public static void nextLevel() {
		if(levelIndex < levelList.size() - 1) {
			levelIndex++;
		}
		currentLevel = levelList.get(levelIndex);
	}
	
	public static String getList(int i) {
		return levelList.get(i);
	}
	
	public static ArrayList<Bouncer> getBouncers() {
		if(bouncerList.get(currentLevel) == null) return new ArrayList<Bouncer>();
		return bouncerList.get(currentLevel);
	}
	
	public static ArrayList<Hole> getHoles() {
		if(holeList.get(currentLevel) == null) return new ArrayList<Hole>();
		return holeList.get(currentLevel);
	}
	
	public static ArrayList<Spawner> getSpawners() {
		if(spawnerList.get(currentLevel) == null) return new ArrayList<Spawner>();
		return spawnerList.get(currentLevel);
	}
	
	public static String[] getDescription() {
		if(levelDescriptions.get(currentLevel) == null) return new String[0];
		return levelDescriptions.get(currentLevel);
	}
	
	public static int getLimit() {
		return limitList.get(currentLevel);
	}
	
	public static void setPlayer(Player player) {
		Point p = playerList.get(currentLevel);
		player.setPosition(p.x, p.y);
		if(currentLevel.equals(LEVEL2_4)) {
			player.setDimensions(58, 58);
		}
		if(currentLevel.equals(LEVEL3_1)) {
			player.setStopSpeed(1);
			player.setHitDelay(120);
			player.setLaunchSpeed(1.5);
		}
		if(currentLevel.equals(LEVEL3_2)) {
			player.setStopSpeed(1);
		}
		if(currentLevel.equals(LEVEL3_3)) {
			player.setStopSpeed(1);
			player.setLaunchSpeed(6);
		}
		if(currentLevel.equals(LEVEL3_4)) {
			player.setStopSpeed(1);
		}
		if(currentLevel.equals(LEVEL4_4)) {
			player.setDimensions(40, 40);
		}
		if(currentLevel.equals(LEVEL5_2)) {
			player.setLaunchSpeed(6);
		}
		if(currentLevel.equals(LEVEL7_1)) {
			player.setPull(0, 0.04);
		}
		if(currentLevel.equals(LEVEL7_2)) {
			player.setPull(0, 0.04);
		}
		if(currentLevel.equals(LEVEL7_3)) {
			player.setPull(0, -0.04);
		}
		if(currentLevel.equals(LEVEL7_4)) {
			player.setPull(-0.05, 0);
		}
		if(currentLevel.equals(LEVEL8_1)) {
			player.setStopSpeed(1);
			player.setLaunchSpeed(6);
		}
		if(currentLevel.equals(LEVEL8_2)) {
			player.setStopSpeed(1);
			player.setLaunchSpeed(3);
		}
		if(currentLevel.equals(LEVEL8_3)) {
			player.setPull(0, 0.04);
		}
	}

	public static void setGoal(Goal goal) {
		Point p = goalList.get(currentLevel);
		goal.setPosition(p.x, p.y);
	}
	
	public static int getTime() {
		if(currentLevel.equals(LEVEL3_2)) return 60 * 20;
		if(currentLevel.equals(LEVEL3_3)) return 60 * 20;
		if(currentLevel.equals(LEVEL3_4)) return 60 * 15;
		if(currentLevel.equals(LEVEL5_1)) return 60 * 15;
		if(currentLevel.equals(LEVEL5_2)) return 60 * 10;
		if(currentLevel.equals(LEVEL5_3)) return 60 * 15;
		if(currentLevel.equals(LEVEL5_4)) return 60 * 15;
		if(currentLevel.equals(LEVEL7_2)) return 60 * 30;
		if(currentLevel.equals(LEVEL8_1)) return 60 * 7 ;
		if(currentLevel.equals(LEVEL8_2)) return 60 * 12;
		if(currentLevel.equals(LEVEL8_3)) return 60 * 12;
		return -1;
	}
	
	public static int[] getStars() {
		int[] stars = new int[MAX_LEVELS];
		int[] star3 = new int[] {22, 14, 17, 15, 25, 14, 16, 10, 100, 100};
		int[] star2 = new int[] {15, 11, 11, 10, 18, 10, 12, 5, 100, 100};
		int[] star1 = new int[] {8, 6, 6, 5, 10, 5, 6, 1, 100, 100};
		for(int i = 0; i < MAX_LEVELS; i++) {
			int score = GameData.getScore(i);
			if(score >= star3[i]) {
				stars[i] = 3;
			}
			else if(score >= star2[i]) {
				stars[i] = 2;
			}
			else if(score >= star1[i]) {
				stars[i] = 1;
			}
			else {
				stars[i] = 0;
			}
		}
		return stars;
	}
	
}