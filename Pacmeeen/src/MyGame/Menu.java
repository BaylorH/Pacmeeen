package MyGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import MyGame.Game.STATE;

public class Menu extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	private boolean playerExists;
	private boolean blinkyExists;
	private boolean blinky2Exists;
	private boolean blinky3Exists;
	private boolean pinkyExists;
	private static boolean firstRun = true;
	private static boolean secondPlayer = false;
	
	public Menu(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		//user presses 2Player
		if(mouseOver(mx, my, 315, 450, 300, 100) && game.gameState == STATE.Menu) {
			if(isSecondPlayer() == false) {
				setSecondPlayer(true);
			}
			else if(isSecondPlayer() == true) {
				setSecondPlayer(false);
			}
		}
		//user presses play
		if(mouseOver(mx, my, 210*3/2, 300, 200*3/2, 100) && game.gameState == STATE.Menu) {
			if(isFirstRun() == false) {
				handler.clearScreen();
			}
			
			game.gameState = STATE.Game;
			HUD.LIVES=3;
			HUD.score=0;
			HUD.level=1;
			
			Map map = new Map(Game.WIDTH/2-32, 32, ID.Map, handler);
			Coins coins = new Coins(Game.WIDTH/2-32, 32, ID.Coins, handler);
			PowerCoins powerCoins = new PowerCoins(Game.WIDTH/2-32, 32, ID.Coins, handler);
			Fruits fruit = new Fruits(Game.WIDTH+100,Game.HEIGHT+100,ID.Apple);
			
			//checks if game objects have already been instantiated in the game
			handler.addObject(map);
			handler.addObject(coins);
			handler.addObject(powerCoins);
			handler.addObject(fruit);
			
			playerExists = false;
			blinkyExists = false;
			blinky2Exists = false;
			blinky3Exists = false;
			pinkyExists = false;
			
			//attempting to debug. Removing the doubles of objects
			for(int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);
				
				if(tempObject.getId() == ID.Player) {
					playerExists = true;
				}
				if(tempObject.getId() == ID.Blinky) {
					blinkyExists = true;
				}
				if(tempObject.getId() == ID.Blinky2) {
					blinky2Exists = true;
					if(tempObject.getId() == ID.Blinky3) {
						blinky3Exists = true;
					}
				}
				if(tempObject.getId() == ID.Pinky) {
					pinkyExists = true;
				}
			}
			if(playerExists) {
				for(int i = 0; i < handler.object.size(); i++) {
					GameObject tempObject = handler.object.get(i);
					
					if(tempObject.getId() == ID.Player) {
						tempObject.setX(448);
						tempObject.setY(476);
						tempObject.setVelX(0);
						tempObject.setVelY(0);
					}
				}
			}
			else {
				handler.addObject(new Player(448,476, ID.Player, handler));
			}
			
			if(blinkyExists) {
				for(int i = 0; i < handler.object.size(); i++) {
					GameObject tempObject = handler.object.get(i);
					
					if(tempObject.getId() == ID.Blinky) {
						tempObject.setX(Game.WIDTH/2-8);
						tempObject.setY(Game.HEIGHT/2-23);
						tempObject.setVelX(0);
						tempObject.setVelY(0);
						Blinky.releaseTimer = 250;
					}
				}
			}
			else {
				handler.addObject(new Blinky(Game.WIDTH/2-8,Game.HEIGHT/2-23, ID.Blinky, handler));
			}
			
			if(blinky2Exists) {
				for(int i = 0; i < handler.object.size(); i++) {
					GameObject tempObject = handler.object.get(i);
					
					if(tempObject.getId() == ID.Blinky2) {
						tempObject.setX(Game.WIDTH/2-33);
						tempObject.setY(Game.HEIGHT/2-23);
						tempObject.setVelX(0);
						tempObject.setVelY(0);
						Blinky2.releaseTimer = 300;
					}
				}
			}
			else {
				handler.addObject(new Blinky2(Game.WIDTH/2-33,Game.HEIGHT/2-23, ID.Blinky2, handler));
			}
			
			if(blinky3Exists) {
				for(int i = 0; i < handler.object.size(); i++) {
					GameObject tempObject = handler.object.get(i);
					
					if(tempObject.getId() == ID.Blinky3) {
						tempObject.setX(Game.WIDTH/2-58);
						tempObject.setY(Game.HEIGHT/2-23);
						tempObject.setVelX(0);
						tempObject.setVelY(0);
						Blinky3.releaseTimer = 350;
					}
				}
			}
			else {
				handler.addObject(new Blinky3(Game.WIDTH/2-58,Game.HEIGHT/2-23, ID.Blinky3, handler));
			}
			
			if(pinkyExists) {
				for(int i = 0; i < handler.object.size(); i++) {
					GameObject tempObject = handler.object.get(i);
					
					if(tempObject.getId() == ID.Pinky) {
						tempObject.setX(Game.WIDTH/2+17);
						tempObject.setY(Game.HEIGHT/2-23);
						tempObject.setVelX(0);
						tempObject.setVelY(0);
						Pinky.releaseTimer = 1000;
					}
				}
			}
			else {
				handler.addObject(new Pinky(Game.WIDTH/2+17,Game.HEIGHT/2-23, ID.Pinky, handler));
			}
			
			if(isSecondPlayer()) {
				handler.addObject(new Player(500,476, ID.Player2, handler));
			}
			
			setFirstRun(false);
			
			//end of debugging
		}
	}
	
	public void mouseReleased(MouseEvent e) {
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			}else return false;
		}else return false;
	}
	
	public void tick() {
	}
	
	public void render(Graphics g) {
		Font fnt = new Font("arial", 1, 70);
		Font fnt2 = new Font("arial", 1, 50);
		Font fnt3 = new Font("arial", 1, 20);
		
		g.setFont(fnt);
		g.setColor(Color.yellow);
		g.drawString("PACMEEEN",  275, 215);
		
		g.setFont(fnt2);
		g.drawRect(210*3/2,300,200*3/2,100);
		g.drawString("Play",405,365);
		
		//checkbox for 2Player
		g.setColor(Color.white);
		g.drawOval(210*3/2, 300+150, 200*3/2, 100);
		if(isSecondPlayer() != true) {		//if checkbox not pressed
			g.drawOval(320, 485, 47, 30);
			g.fillOval(320, 485, 30, 30);
		}
		else {		//if checkbox pressed
			g.setColor(Color.green);
			g.fillOval(320, 485, 47, 30);
			g.setColor(Color.white);
			g.fillOval(337, 485, 30, 30);
		}
		g.setColor(Color.yellow);
		g.drawString("2 Player", 390, 365+150);
		
		g.setFont(fnt3);
		g.setColor(Color.white);
		g.drawString("Created by Baylor",  380, 255);
		
	}
	
	public static boolean getSecondPlayer() {
		return isSecondPlayer();
	}

	public static boolean isSecondPlayer() {
		return secondPlayer;
	}

	public static void setSecondPlayer(boolean secondPlayer) {
		Menu.secondPlayer = secondPlayer;
	}

	public boolean isFirstRun() {
		return firstRun;
	}

	public static void setFirstRun(boolean check) {
		firstRun = check;
	}

}
