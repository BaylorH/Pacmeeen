package MyGame;

import java.awt.*;
import java.util.Random;

public class Pinky extends GameObject{
	
	private Random r = new Random();
	public static int releaseTimer=500;
	Handler handler;
	public float a,b;
	public int c;
	public int coolDownTimer = 0;
	
	public Pinky(int x, int y, ID id, Handler handler) {
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
		coolDownTimerTick();
	}
	
	public void coolDownTimerTick() {
		if(coolDownTimer>0)
			coolDownTimer--;
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
			if(y>287) {
				if(x>Game.WIDTH/2-20) {
					velX=-1;
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
		
		//tracking algorithm for the rest of the game
		else {
			inLines();
			//if cooldowntimer == 0
			if((velX==0 && velY==0) || (coolDownTimerCheck() && turnOpen()==true)) {
				
				for(int k = 0; k < handler.object.size(); k++) {
					GameObject tempObject = handler.object.get(k);
					
					if(tempObject.getId() == ID.Player){
						//tempObject is player
						
						a = tempObject.getX()-x;		//difference between the players's x position and Pinky's
						b = tempObject.getY()-y;		//difference between the players's y position and Pinky's
						if(Math.abs(a) > Math.abs(b)) {
							//Pinky is farther from the player with respect to x (x distance greater than y distance)
							if(a>0) {
								//player is to the right of Pinky
								if(b>0) {
									//player is below Pinky
									int number = r.nextInt(12)+1;		//random number decides which direction Pinky will head in
									
									if(number<=6){		//Pinky is most likely to travel right
										velX=1;
									}
									else if(number==7) {
										velX=-1;
									}
									else if(number<=11) {		//Pinky is likely to travel down
										velY=1;
									}
									else if(number==12) {
										velY=-1;
									}
									
								}
								else {
									//player is above Pinky
									int number = r.nextInt(12)+1;
									
									if(number<=6){		//Pinky is most likely to travel right
										velX=1;
									}
									else if(number==7) {
										velX=-1;
									}
									else if(number==8) {
										velY=1;
									}
									else if(number<=12) {		//Pinky is likely to travel up
										velY=-1;
									}
								}
							}
							else {
								//player is to the left of Pinky
								if(b>0) {
									//player is below Pinky
									int number = r.nextInt(12)+1;
									
									if(number==1){
										velX=1;
									}
									else if(number<=7) {		//Pinky is most likely to travel left
										velX=-1;
									}
									else if(number<=11) {		//Pinky is likely to travel down
										velY=1;
									}
									else if(number==12) {
										velY=-1;
									}
								}
								else {
									//player is above Pinky
									int number = r.nextInt(12)+1;
									
									if(number==1){
										velX=1;
									}
									else if(number<=7) {		//Pinky is most likely to travel left
										velX=-1;
									}
									else if(number==8) {
										//1
										velY=1;
									}
									else if(number<=12) {		//Pinky is likely to travel up
										//2
										velY=-1;
									}
								}
							}
						}
						else {
							//Pinky is farther from the player with respect to y (y distance greater than x distance)
							if(a>0) {
								//player is to the right of Pinky
								if(b>0) {
									//player is below Pinky
									int number = r.nextInt(12)+1;
									
									if(number<=4){		//Pinky is likely to travel right
										velX=1;
									}
									else if(number==5) {
										velX=-1;
									}
									else if(number<=11) {		//Pinky is most likely to travel down
										velY=1;
									}
									else if(number==12) {
										velY=-1;
									}
									
								}
								else {
									//player is above Pinky
									int number = r.nextInt(12)+1;
									
									if(number<=4){		//Pinky is likely to travel right
										velX=1;
									}
									else if(number==5) {
										velX=-1;
									}
									else if(number==6) {
										velY=1;
									}
									else if(number<=12) {		//Pinky is most likely to travel up
										velY=-1;
									}
								}
							}
							else {
								//player is to the left of Pinky
								if(b>0) {
									//player is below Pinky
									int number = r.nextInt(12)+1;
									
									if(number==1){
										velX=1;
									}
									else if(number<=5) {		//Pinky is likely to travel left
										velX=-1;
									}
									else if(number<=11) {		//Pinky is most likely to travel down
										velY=1;
									}
									else if(number==12) {
										velY=-1;
									}
								}
								else {
									//player is above Pinky
									int number = r.nextInt(12)+1;
									
									if(number==1){
										velX=1;
									}
									else if(number<=5) {		//Pinky is likely to travel left
										velX=-1;
									}
									else if(number==6) {
										velY=1;
									}
									else if(number<=12) {		//Pinky is most likely to travel up
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
	
	public boolean coolDownTimerCheck() {
		if(coolDownTimer ==0) {
			return true;
		}
		else {
			return false;
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
	
	public boolean turnOpen() {
		//projects path of direction to see if Pinky would collide with map lines if she were to turn
		if(
				//if traveling left or right and a turn is open 
				(velX==1 || velX==-1) && (touchingLines((int)x,(int)y+30)==false || touchingLines((int)x,(int)y-30)==false) ||
				(velY==1 || velY==-1) && (touchingLines((int)x+30,(int)y)==false || touchingLines((int)x-30,(int)y)==false)
				) {
			coolDownTimer = 50;
			return true;
		}
		else
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
			g.setColor(Color.pink);
		}
		//base ghost shape
		g.fillRect((int)x, (int)y, 16, 16);
		g.fillOval((int)x, (int)y-6, 16, 14);
		if(HUD.powerUp == true) {
			//face for Pinky if powerUp active
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
			g.fillOval((int)x+2,(int)y+2+2,4,3);
			g.fillOval((int)x+8+2,(int)y+2+2,4,3);
			//mouth
			g.fillOval((int)x+3,(int)y+10,10,3);
		}
	}

}