package MyGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Random;

import MyGame.Game.STATE;

public class Player extends GameObject {
	
	Random r = new Random();
	Handler handler;
	
	private int timerA = 0;
	private static boolean fruitCollected = false;
	
	Color p1Color = Color.yellow;
	Color p2Color = new Color(23, 209, 85);
	Color pColor;

	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp((float)x, 122, 785);
		y = Game.clamp((float)y, 65, 580);
		
		collision();
		inLines();
		tp();
	}
	
	private void collision() {
		for(int i = 0; i < handler.object.size(); i ++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Blinky 
					|| tempObject.getId() == ID.Pinky 
					|| tempObject.getId() == ID.Blinky2 
					|| tempObject.getId() == ID.Blinky3) { //tempObject is Enemy
				if(getBounds().intersects(tempObject.getBounds())) {
					//collision code
					
					if(HUD.powerUp == false) {
						//respawn player
						setX(Game.WIDTH/2 - 32);
						setY(Game.HEIGHT/2+116);
						setVelX(0);
						setVelY(0);
						
						HUD.LIVES -= 1;
						
						//respawn enemies
						for(int k = 0; k < handler.object.size(); k++) {
							GameObject tempObject2 = handler.object.get(k);
							
							if(tempObject2.getId() == ID.Blinky) {
								tempObject2.setX(Game.WIDTH/2-8);
								tempObject2.setY(Game.HEIGHT/2-23);
							}
							if(tempObject2.getId() == ID.Blinky2) {
								tempObject2.setX(Game.WIDTH/2-33);
								tempObject2.setY(Game.HEIGHT/2-23);
							}
							if(tempObject2.getId() == ID.Blinky3) {
								tempObject2.setX(Game.WIDTH/2-58);
								tempObject2.setY(Game.HEIGHT/2-23);
							}
							if(tempObject2.getId() == ID.Pinky) {
								tempObject2.setX(Game.WIDTH/2+17);
								tempObject2.setY(Game.HEIGHT/2-23);
							}
						}
						
						Blinky.releaseTimer=250;
						Blinky2.releaseTimer=300;
						Blinky3.releaseTimer=350;
						Pinky.releaseTimer=750;
						
						//reset fruit's releaseTimer if the fruit was not collected by user
						if(isFruitCollected() == false) {
							Fruits.releaseTimer = 900;
						}
					}
					else {			//if powerUp is on
						HUD.score += 100;
						if(tempObject.getId() == ID.Blinky) {
							//send the enemy back to spawn
							tempObject.setX(Game.WIDTH/2-8);
							tempObject.setY(Game.HEIGHT/2-23);
							Blinky.releaseTimer=HUD.powerUpTimer;	//hold them in spawn until powerUp runs out
						}
						if(tempObject.getId() == ID.Blinky2) {
							tempObject.setX(Game.WIDTH/2-33);
							tempObject.setY(Game.HEIGHT/2-23);
							Blinky2.releaseTimer=HUD.powerUpTimer;
						}
						if(tempObject.getId() == ID.Blinky3) {
							tempObject.setX(Game.WIDTH/2-58);
							tempObject.setY(Game.HEIGHT/2-23);
							Blinky3.releaseTimer=HUD.powerUpTimer;
						}
						if(tempObject.getId() == ID.Pinky) {
							tempObject.setX(Game.WIDTH/2+17);
							tempObject.setY(Game.HEIGHT/2-23);
							Pinky.releaseTimer=HUD.powerUpTimer;
						}
					}
				}
			}
			//collision with fruit
			if(tempObject.getId() == ID.Apple) { //tempObject is the fruit
				if(getBounds().intersects(tempObject.getBounds())) {
					HUD.score += 500;
					setFruitCollected(true);
				}
			}
		}
	}
	
	public void inLines() {
		if(touchingLines((int)x,(int)y)==true) {
			//player collision with map lines
			if(velX==2) {
				velX=0;
				x-=2;
			}
			if(velX==-2) {
				velX=0;
				x+=2;
			}
			if(velY==2) {
				velY=0;
				y-=2;
			}
			if(velY==-2) {
				velY=0;
				y+=2;
			}	
		}
	}
	
	//checks collision with map lines
	public boolean touchingLines(int xPos,int yPos) {
		for(int k=0; k<Map.lines.length; k++) {
			for(int j=0; j<=32; j++) {
				
				if(getX()+j > Map.lines[k][0] && getX() < Map.lines[k][0] + Map.lines[k][2]) {
					if(getY()+j > Map.lines[k][1] && getY() < Map.lines[k][1] + Map.lines[k][3]) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	//teleport through warp tunnels
	public void tp() {
		if(getX() <= 127) {
			setX(775);
		}
		if(getX() >= 780) {
			setX(132);
		}
	}

	public void render(Graphics g) {
		//wait a second
			if(getId() == ID.Player) {
				pColor = p1Color;
			}
			else if(getId() == ID.Player2) {
				pColor = p2Color;
			}
		
		if(timerA <= 0) {
			//base shape of pacman
			g.setColor(pColor);
			g.fillOval((int)x, (int)y, 32, 32);
			timerA = 80;
		}
		else if(timerA >= 45){
			g.setColor(pColor);
			g.fillOval((int)x, (int)y, 32, 32);
			timerA--;
		}
		//creating an effect looking like the mouth is opening and closing
		else{
			g.setColor(pColor);
			g.fillOval((int)x, (int)y, 32, 32);
			
			if(getVelX() == 2) { //moving right
				g.setColor(Color.black);
				Polygon triangle = new Polygon();
				triangle.addPoint((int)x+13, (int)y+16); //left
				triangle.addPoint((int)x+32, (int)y); //top right
				triangle.addPoint((int)x+32, (int)y+32); //bottom right
				g.fillPolygon(triangle);
			}
			if(getVelX() == -2) { //moving left
				g.setColor(Color.black);
				Polygon triangle = new Polygon();
				triangle.addPoint((int)x+19, (int)y+16); //right
				triangle.addPoint((int)x, (int)y); //top left
				triangle.addPoint((int)x, (int)y+32); //bottom left
				g.fillPolygon(triangle);
			}
			if(getVelY() == -2) { //moving up
				g.setColor(Color.black);
				Polygon triangle = new Polygon();
				triangle.addPoint((int)x+16, (int)y+18); //bottom
				triangle.addPoint((int)x, (int)y); //top left
				triangle.addPoint((int)x+32, (int)y); //top right
				g.fillPolygon(triangle);
			}
			if(getVelY() == 2) { //moving down
				g.setColor(Color.black);
				Polygon triangle = new Polygon();
				triangle.addPoint((int)x+16, (int)y+14); //top
				triangle.addPoint((int)x, (int)y+32); //bottom left
				triangle.addPoint((int)x+32, (int)y+32); //bottom right
				g.fillPolygon(triangle);
			}
			timerA--;
		}
	}

	public static boolean isFruitCollected() {
		return fruitCollected;
	}

	public static void setFruitCollected(boolean newFruitCollected) {
		fruitCollected = newFruitCollected;
	}

}
