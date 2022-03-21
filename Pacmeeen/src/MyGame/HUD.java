package MyGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class HUD {
	
	
	public static int LIVES=3;
	public static int score;
	public static int level;
	public static boolean powerUp = false;
	public static int powerUpTimer = 500;
	
	public void tick() {
		//powerUpTimer
		if(powerUp == true) {
			if(powerUpTimer > 0)
				powerUpTimer--;
			else {
				powerUp = false;
				powerUpTimer = 500;
			}
		}
	}
	
	public void render(Graphics g) {
		//showcase life count on bottom left of screen
		for(int i = 1; i < LIVES+1; i++) {
			//base shape
			g.setColor(Color.yellow);
			g.fillOval(32 * i+20 * i, 635, 32, 32);
			
			//mouth
			g.setColor(Color.black);
			Polygon triangle = new Polygon();
			triangle.addPoint(32 * i+20 * i+19, 635+16); //right
			triangle.addPoint(32 * i+20 * i-7, 635); //top left
			triangle.addPoint(32 * i+20 * i-7, 635+32); //bottom left
			g.fillPolygon(triangle);
		}
		
		//rest of HUD
		g.setColor(Color.yellow);
		g.drawString("Score: " + score, Game.WIDTH/2-40, 40);
		g.drawString("Level: " + level, 10, 40);
		g.drawString("Power up: ", Game.WIDTH/2+40, Game.HEIGHT-60);
		//powerUpTimer showcased at bottom right of screen
		g.drawRect(Game.WIDTH/2+110, Game.HEIGHT-73, 200, 17);
		g.setColor(Color.green);
		if(powerUp == true)
			g.fillRect(Game.WIDTH/2+110, Game.HEIGHT-73, powerUpTimer*200/500, 17);
	}
	
	
	public void setScore(int score) {
		this.score = score;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getScore() {
		return score;
	}
	public int getLevel() {
		return level;
	}

}
