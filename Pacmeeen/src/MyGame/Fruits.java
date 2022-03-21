package MyGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;

public class Fruits extends GameObject {
	public static int releaseTimer = 900;
	private int pause = 0;
	public static int turn = 1;
	
	public Fruits(float x, float y, ID id) {
		super(x, y, id);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		//showcase the fruit on the right hand of the screen if collected by player
		if(Player.isFruitCollected() == true) {
			setX(Game.WIDTH - 80);
			setY(80);
			setVelX(0);
			setVelY(0);
		}
		
		//locate the fruit off the screen until released
		if(getReleaseTimer()>0) {
			setReleaseTimer(getReleaseTimer() - 1);
			setVelX(0);
			setVelY(0);
			setX(Game.WIDTH+100);
			setY(Game.HEIGHT+100);
		}
		//move fruit to map and wait
		else if (getReleaseTimer() == 0){
			setX(Game.WIDTH/2-32);
			setY(Game.HEIGHT/2+20);
			setReleaseTimer(-1);
			pause = 200;
		}
		if(pause>0) {
			pause--;
		}
		else if(pause == 0) {
			release();
		}
	}

	public void release() {
		if(turn == 1) {
			velX = 1;
		}
		if(x==535 && y==Game.HEIGHT/2+20) {
			velX = 0;
			turn++;
		}
		if(turn == 2) {
			velY = -1;
		}
		if(x==535 && y==70) {
			velY = 0;
			turn++;
		}
		if(turn == 3) {
			velX = 1;
		}
		if(x==585 && y==70) {
			velX = 0;
			turn++;
		}
		if(turn == 4) {
			velY = 1;
		}
		if(x==585 && y==125) {
			velY = 0;
			turn++;
		}
		if(turn == 5) {
			velX = 1;
		}
		if(x==640 && y==125) {
			velX = 0;
			turn++;
		}
		if(turn == 6) {
			velY = 1;
		}
		if(x==640 && y==175) {
			velY = 0;
			turn++;
		}
		if(turn == 7) {
			velX = 1;
		}
		if(x==780 && y==175) {
			x=130;
		}
		if(x==210 && y==175) {
			velX = 0;
			turn++;
		}
		if(turn == 8) {
			velY = 1;
		}
		if(x==210 && y==225) {
			velY = 0;
			turn++;
		}
		if(turn == 9) {
			velX = 1;
		}
		if(x==270 && y==225) {
			velX = 0;
			turn++;
		}
		if(turn == 10) {
			velY = 1;
		}
		if(x==270 && y==425) {
			velY = 0;
			turn++;
		}
		if(turn == 11) {
			velX = -1;
		}
		if(x==130 && y==425) {
			velX = 0;
			turn++;
			setX(Game.WIDTH+100);
		}
		
	}
	
	public void render(Graphics g) {
		if(getId() == ID.Apple) {
			//base shape
			g.setColor(Color.red);
			g.fillOval((int)x, (int)y, 30, 30);
			g.setColor(new Color(152,118,84));
			//stem
			g.fillRect((int)x+12, (int)y-8, 5, 15);
			//leaf
			g.setColor(Color.green);
			Polygon leaf = new Polygon();
			leaf.addPoint((int)x+17, (int)y-8+12); 	//bottom left
			leaf.addPoint((int)x+22, (int)y-13+12); 	//middle left
			leaf.addPoint((int)x+27, (int)y-20+12); 	//top
			leaf.addPoint((int)x+26, (int)y-10+12); 	//middle right
			g.fillPolygon(leaf);
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	public int getReleaseTimer() {
		return releaseTimer;
	}
	
	public static void setReleaseTimer(int n) {
		releaseTimer = n;
	}

}
