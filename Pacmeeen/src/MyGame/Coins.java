package MyGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Coins extends GameObject {
	Random r = new Random();
	Handler handler;

	int row = 0;

	//coordinates of the coins
	public static int[][] coins = new int[335][2];
	{
		//bottom left of map
		for(int i=241; i<=700; i+=18) {	coins[row][0] = i;	coins[row][1] = 591;	row++;}
		for(int i=488; i<580; i+=17) {	coins[row][0] = 223;	coins[row][1] = i;	row++;}
		for(int i=241; i<=320; i+=18) {	coins[row][0] = i;	coins[row][1] = 488;	row++;}
		for(int i=277; i<=450; i+=18) {	coins[row][0] = i;	coins[row][1] = 542;	row++;}
		for(int i=557; i<=580; i+=17) {	coins[row][0] = 277;	coins[row][1] = i;	row++;}
		for(int i=557; i<=580; i+=17) {	coins[row][0] = 385;	coins[row][1] = i;	row++;}
		for(int i=454; i<=500; i+=17) {	coins[row][0] = 385;	coins[row][1] = i;	row++;}
		for(int i=402; i<=430; i+=18) {	coins[row][0] = i;	coins[row][1] = 488;	row++;}
		for(int i=438; i<=530; i+=17) {	coins[row][0] = 331;	coins[row][1] = i;	row++;}
		for(int i=349; i<=450; i+=18) {	coins[row][0] = i;	coins[row][1] = 437;	row++;}
		for(int i=524; i<=530; i+=18) {	coins[row][0] = 439;	coins[row][1] = i;	row++;}
		for(int i=403; i<443; i+=17) {	coins[row][0] = 439;	coins[row][1] = i;	row++;}
		//bottom right of map
		for(int i=557; i<=580; i+=17) {	coins[row][0] = 547;	coins[row][1] = i;	row++;}
		for(int i=557; i<=580; i+=17) {	coins[row][0] = 655;	coins[row][1] = i;	row++;}
		for(int i=493; i<=660; i+=18) {	coins[row][0] = i;	coins[row][1] = 542;	row++;}
		coins[row][0] = 493;	coins[row][1] = 525;	row++;
		for(int i=511; i<=555; i+=18) {	coins[row][0] = i;	coins[row][1] = 491;	row++;}
		for(int i=493; i<=605; i+=18) {	coins[row][0] = i;	coins[row][1] = 437;	row++;}
		for(int i=454; i<=480; i+=18) {	coins[row][0] = 547;	coins[row][1] = i;	row++;}
		for(int i=454; i<=535; i+=18) {	coins[row][0] = 601;	coins[row][1] = i;	row++;}
		for(int i=619; i<=705; i+=18) {	coins[row][0] = i;	coins[row][1] = 488;	row++;}
		for(int i=488; i<=580; i+=17) {	coins[row][0] = 709;	coins[row][1] = i;	row++;}
		for(int i=403; i<443; i+=17) {	coins[row][0] = 493;	coins[row][1] = i;	row++;}
		//top left of map
		for(int i=471; i>220; i-=17) {	coins[row][0] = 277;	coins[row][1] = i;	row++;}
		for(int i=241; i<270; i+=18) {	coins[row][0] = i;	coins[row][1] = 233;	row++;}
		for(int i=233; i>90; i-=17) {	coins[row][0] = 223;	coins[row][1] = i;	row++;}
		for(int i=241; i<320; i+=18) {	coins[row][0] = i;	coins[row][1] = 182;	row++;}
		for(int i=241; i<700; i+=18) {	coins[row][0] = i;	coins[row][1] = 80;		row++;}
		for(int i=277; i<320; i+=18) {	coins[row][0] = i;	coins[row][1] = 131;	row++;}
		for(int i=148; i<170; i+=17) {	coins[row][0] = 277;	coins[row][1] = i;	row++;}
		for(int i=97; i<135; i+=17) {	coins[row][0] = 331;	coins[row][1] = i;	row++;}
		for(int i=182; i<340; i+=17) {	coins[row][0] = 331;	coins[row][1] = i;	row++;}
		for(int i=97; i<380; i+=17) {	coins[row][0] = 385;	coins[row][1] = i;	row++;}
		for(int i=131; i<250; i+=17) {	coins[row][0] = 439;	coins[row][1] = i;	row++;}
		for(int i=403; i<440; i+=18) {	coins[row][0] = i;	coins[row][1] = 131;	row++;}
		for(int i=295; i<320; i+=18) {	coins[row][0] = i;	coins[row][1] = 335;	row++;}
		for(int i=349; i<390; i+=18) {	coins[row][0] = i;	coins[row][1] = 284;	row++;}
		for(int i=403; i<540; i+=18) {	coins[row][0] = i;	coins[row][1] = 284;	row++;}
		for(int i=295; i<640; i+=18) {	coins[row][0] = i;	coins[row][1] = 386;	row++;}
		for(int i=457; i<500; i+=18) {	coins[row][0] = i;	coins[row][1] = 233;	row++;}
		//top right of map
		for(int i=131; i<250; i+=17) {	coins[row][0] = 493;	coins[row][1] = i;	row++;}
		for(int i=511; i<530; i+=18) {	coins[row][0] = i;	coins[row][1] = 131;	row++;}
		for(int i=97; i<380; i+=17) {	coins[row][0] = 547;	coins[row][1] = i;	row++;}
		for(int i=97; i<135; i+=17) {	coins[row][0] = 601;	coins[row][1] = i;	row++;}
		for(int i=131; i<180; i+=17) {	coins[row][0] = 655;	coins[row][1] = i;	row++;}
		for(int i=97; i<250; i+=17) {	coins[row][0] = 709;	coins[row][1] = i;	row++;}
		for(int i=619; i<655; i+=18) {	coins[row][0] = i;	coins[row][1] = 131;	row++;}
		for(int i=601; i<700; i+=18) {	coins[row][0] = i;	coins[row][1] = 182;	row++;}
		for(int i=199; i<350; i+=17) {	coins[row][0] = 601;	coins[row][1] = i;	row++;}
		for(int i=233; i<480; i+=17) {	coins[row][0] = 655;	coins[row][1] = i;	row++;}
		for(int i=673; i<703; i+=18) {	coins[row][0] = i;	coins[row][1] = 233;	row++;}
		for(int i=619; i<649; i+=18) {	coins[row][0] = i;	coins[row][1] = 335;	row++;}
		for(int i=565; i<595; i+=18) {	coins[row][0] = i;	coins[row][1] = 284;	row++;}
	}

	public Coins(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	public Rectangle getBounds() {
		return new Rectangle(442, 316, 5, 5);
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		for (int a = 0; a < coins.length; a++) {
			g.fillOval(coins[a][0], coins[a][1], 5, 5);
		}
	}

	public void tick() {
		collision();
		isCoinsEmpty();
	}
	
	public static boolean isCoinsEmpty() {
		for (int k = 0; k < coins.length; k++) {
			if(coins[k][0] != 990) {
				return false;
			}
		}
		return true;
	}

	//checks for collision with player
	private void collision() {
		for (int f = 0; f < handler.object.size(); f++) {
			GameObject tempObject = handler.object.get(f);

			if (tempObject.getId() == ID.Player || tempObject.getId() == ID.Player2) {

				for (int k = 0; k < coins.length; k++) {
					for (int j = 0; j <= 32; j++) {

						if (tempObject.getX() + j > coins[k][0] && tempObject.getX() < coins[k][0] + 5) {
							if (tempObject.getY() + j > coins[k][1] && tempObject.getY() < coins[k][1] + 5) {
								// player collision with coins
								
								//at this point I realized I should have used an arraylist instead of an array so I could remove the coin
								//I felt that it would be easier to relocate these coins to being off the screen instead of removing them 
								//	from the array
								coins[k][0]=990;
								HUD.score+= 10;
							}
						}
					}
				}
			}
		}
	}

}
