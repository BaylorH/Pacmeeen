package MyGame;

import java.awt.*;
import java.util.Random;

public class Blinky2 extends GameObject{
	
	private Random r = new Random();
	public static int releaseTimer=300;
	Handler handler;
	float a,b;
	int c;
	
	public Blinky2(int x, int y, ID id, Handler handler) {
		super(x,y,id);
		this.handler = handler;
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
		
	}

	public void tick() {
		x += velX;
		y += velY;
		
		tp();
		release();
		
	}
	
	public void release() {
		//hold in spawn
		if(releaseTimer > 0) {
			velX=0;
			velY=0;
			releaseTimer--;
		}
		//release from spawn
		else if(releaseTimer==0) {
			if(getId() == ID.Blinky2) {
				if(y>287) {
					if(x < Game.WIDTH/2-20) {
						velX=+1;
					}
					else {
						velX=0;
						velY=-1;
					}
				}
				else {
					releaseTimer=-1;
				}
			}
		}
		//tracking algorithm for the rest of the game
		else {
			inLines();
			if((velX==0 && velY==0)) {
				for(int k = 0; k < handler.object.size(); k++) {
					GameObject tempObject = handler.object.get(k);
					
					if(tempObject.getId() == ID.Player){
						//tempObject is player
						
						a = tempObject.getX()-x;		//difference between the players's x position and Blinky's
						b = tempObject.getY()-y;		//difference between the players's y position and Blinky's
						if(Math.abs(a) > Math.abs(b)) {
							//Blinky is farther from the player with respect to x (x distance greater than y distance)
							if(a>0) {
								//player is to the right of Blinky
								if(b>0) {
									//player is below Blinky
									int number = r.nextInt(11)+1;		//random number decides which direction Blinky will head in
									
									if(number<=5){		//Blinky is most likely to travel right
										velX=1;
									}
									else if(number==6) {
										velX=-1;
									}
									else if(number<=10) {		//Blinky is likely to travel down
										velY=1;
									}
									else if(number==11) {
										velY=-1;
									}
									
								}
								else {
									//player is above Blinky
									int number = r.nextInt(11)+1;
									
									if(number<=5){		//Blinky is most likely to travel right
										velX=1;
									}
									else if(number==6) {
										velX=-1;
									}
									else if(number==7) {
										velY=1;
									}
									else if(number<=11) {		//Blinky is likely to travel up
										velY=-1;
									}
								}
							}
							else {
								//player is to the left of Blinky
								if(b>0) {
									//player is below Blinky
									int number = r.nextInt(11)+1;
									
									if(number==1){
										velX=1;
									}
									else if(number<=6) {		//Blinky is most likely to travel left
										velX=-1;
									}
									else if(number<=10) {		//Blinky is likely to travel down
										velY=1;
									}
									else if(number==11) {
										velY=-1;
									}
								}
								else {
									//player is above Blinky
									int number = r.nextInt(11)+1;
									
									if(number==1){
										velX=1;
									}
									else if(number<=6) {		//Blinky is most likely to travel left
										velX=-1;
									}
									else if(number==7) {
										velY=1;
									}
									else if(number<=11) {		//Blinky is likely to turn up
										velY=-1;
									}
								}
							}
						}
						else {
							//Blinky is farther from the player with respect to y (y distance greater than x distance)
							if(a>0) {
								//player is to the right of Blinky
								if(b>0) {
									//player is below Blinky
									int number = r.nextInt(11)+1;
									
									if(number<=4){		//Blinky is likely to travel right
										velX=1;
									}
									else if(number==5) {
										velX=-1;
									}
									else if(number<=10) {		//Blinky is most likely to travel down
										velY=1;
									}
									else if(number==11) {
										velY=-1;
									}
									
								}
								else {
									//player is above Blinky
									int number = r.nextInt(11)+1;
									
									if(number<=4){		//Blinky is likely to travel right
										velX=1;
									}
									else if(number==5) {
										velX=-1;
									}
									else if(number==6) {
										velY=1;
									}
									else if(number<=11) {		//Blinky is most likely to travel up
										velY=-1;
									}
								}
							}
							else {
								//player is to the left of Blinky
								if(b>0) {
									//player is below Blinky
									int number = r.nextInt(11)+1;
									
									if(number==1){
										velX=1;
									}
									else if(number<=5) {		//Blinky is likely to travel left
										velX=-1;
									}
									else if(number<=10) {		//Blinky is most likely to travel down
										velY=1;
									}
									else if(number==11) {
										velY=-1;
									}
								}
								else {
									//player is above Blinky
									int number = r.nextInt(11)+1;
									
									if(number==1){
										velX=1;
									}
									else if(number<=5) {		//Blinky is likely to travel left
										velX=-1;
									}
									else if(number==6) {
										velY=1;
									}
									else if(number<=11) {		//Blinky is most likely to travel up
										velY=-1;
									}
								}
							}
						}	
					}
				}
			}
		}
	}
	
	public void inLines() {
		if(touchingLines((int)x,(int)y)==true) {
			//after collision with map lines
			if(velX==1) {
				velX=0;
				x-=1;
			}
			if(velX==-1) {
				velX=0;
				x+=1;
			}
			if(velY==1) {
				velY=0;
				y-=1;
			}
			if(velY==-1) {
				velY=0;
				y+=1;
			}	
		}
	}
	
	//check for collision with map lines
	public boolean touchingLines(int xPos,int yPos) {
		for(int k=0; k<Map.lines.length; k++) {
			for(int j=0; j<=26; j++) {
				if(xPos+j +4> Map.lines[k][0] && xPos-13 < Map.lines[k][0] + Map.lines[k][2]) {
					if(yPos+j > Map.lines[k][1] && yPos-13 < Map.lines[k][1] + Map.lines[k][3]) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	//teleport through warp tunnels
	public void tp() {
		if(x <= 127) {
			x=775;
		}
		if(x >= 780) {
			x=132;
		}
	}

	public void render(Graphics g) {
		if(HUD.powerUp == true) {
			g.setColor(Color.blue);
		}
		else {
			g.setColor(Color.green);
		}
		//base ghost shape
		g.fillRect((int)x, (int)y, 16, 16);
		g.fillOval((int)x, (int)y-6, 16, 14);
		if(HUD.powerUp == true) {
			//face for Blinky if powerUp active
			g.setColor(Color.white);
			g.fillRect((int)x+2,(int)y,4,1);
			g.fillRect((int)x+7,(int)y,4,1);
			g.fillOval((int)x+5,(int)y+7,4,4);
		}
		else {
			//eyes
			g.setColor(Color.white);
			g.fillOval((int)x,(int)y+2,8,5);
			g.fillOval((int)x+8,(int)y+2,8,5);
			g.setColor(Color.black);
			g.fillOval((int)x+1,(int)y+2,4,3);
			g.fillOval((int)x+8+3,(int)y+2+2,4,3);
			//mouth
			g.fillOval((int)x+3,(int)y+8,10,2);
		}
	}
}