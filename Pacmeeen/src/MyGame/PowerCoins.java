package MyGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class PowerCoins extends GameObject {
	Random r = new Random();
	Handler handler;

	int row = 0;
	private int timerA = 0;

	//coordinates of the powerUp coins
	public static int[][] powerCoins = new int[4][2];
	{
		powerCoins[row][0] = 218;	powerCoins[row][1] = 587;	row++;
		powerCoins[row][0] = 704;	powerCoins[row][1] = 587;	row++;
		powerCoins[row][0] = 218;	powerCoins[row][1] = 75;	row++;
		powerCoins[row][0] = 704;	powerCoins[row][1] = 75;	row++;
	}

	public PowerCoins(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	public Rectangle getBounds() {
		return new Rectangle(442, 316, 5, 5);
	}

	public void render(Graphics g) {
		//creating a blinking effect
		if(timerA <= 0) {
			g.setColor(Color.white);
			timerA = 80;
		}
		else if(timerA >= 45){
			g.setColor(Color.white);
			timerA--;
		}
		else{
			g.setColor(Color.black);
			
			timerA--;
		}

		for (int a = 0; a < powerCoins.length; a++) {
			g.fillOval(powerCoins[a][0], powerCoins[a][1], 15, 15);
		}
	}

	public void tick() {
		collision();
	}
	
	//powerUp collected
	public void powerUp() {
		HUD.powerUp = true;
		HUD.powerUpTimer = 500;
	}

	private void collision() {

		for (int f = 0; f < handler.object.size(); f++) {
			GameObject tempObject = handler.object.get(f);

			if (tempObject.getId() == ID.Player || tempObject.getId() == ID.Player2) {

				for (int k = 0; k < powerCoins.length; k++) {
					for (int j = 0; j <= 32; j++) {

						if (tempObject.getX() + j > powerCoins[k][0] && tempObject.getX() < powerCoins[k][0] + 5) {
							if (tempObject.getY() + j > powerCoins[k][1] && tempObject.getY() < powerCoins[k][1] + 5) {
								// player collision with coins
								
								powerCoins[k][0]=990;
								HUD.score+= 50;
								
								powerUp();
							}
						}
					}
				}
			}
		}
	}

}
