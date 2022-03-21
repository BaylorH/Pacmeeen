package MyGame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	//commented code are for a potential new movement system I was working on for the player
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player){
				//key events for player 1
				
				//if 2 player active then WASD should be strictly for P1
				if(Menu.getSecondPlayer() ==false) {
					if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
						//if(tempObject.getVelX()==0 && tempObject.getVelY()==0) {
							tempObject.setVelX(0);
							tempObject.setVelY(-2);
						//}
						//else if(turnOpen(key)>0) {
						//	while((int)tempObject.getX() != turnOpen(key)) {
								//do nothing
						//	}
						//	tempObject.setVelY(-1);
						//}
						//else if(turnOpen(key)==0) {
						//	tempObject.setVelX(0);
						//	tempObject.setVelY(-1);
						//}
						
					}
					if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
						//if(tempObject.getVelX()==0 && tempObject.getVelY()==0) {
							tempObject.setVelX(0);
							tempObject.setVelY(2);
						//}
						//else if(turnOpen(key)>0) {
						//	while((int)tempObject.getX() != turnOpen(key)) {
								//do nothing
						//		System.out.println(turnOpen(key));
						//	}
						//	tempObject.setVelY(1);
						//}
						//else if(turnOpen(key)==0) {
						//	tempObject.setVelX(0);
						//	tempObject.setVelY(1);
						//}
					}
					if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
					//	if(tempObject.getVelX()==0 && tempObject.getVelY()==0) {
							tempObject.setVelX(2);
							tempObject.setVelY(0);
					//	}
					//	else if(turnOpen(key)>0) {
					//		while((int)tempObject.getY() != turnOpen(key)) {
								//do nothing
					//		}
					//		tempObject.setVelX(1);
					//	}
					//	else if(turnOpen(key)==0) {
					//		tempObject.setVelX(1);
					//		tempObject.setVelY(0);
					//	}
					}
					if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
						//if(tempObject.getVelX()==0 && tempObject.getVelY()==0) {
							tempObject.setVelX(-2);
							tempObject.setVelY(0);
						//}
						//else if(turnOpen(key)>0) {
						//	while((int)tempObject.getY() != turnOpen(key)) {
								//do nothing
						//	}
						//	tempObject.setVelX(-1);
						//}
						//else if(turnOpen(key)==0) {
						//	tempObject.setVelX(-1);
						//	tempObject.setVelY(0);
						//}
					}
				}
				else {
					//if 2 player active then WASD should be strictly for P1
					if(key == KeyEvent.VK_W) {
						//if(tempObject.getVelX()==0 && tempObject.getVelY()==0) {
							tempObject.setVelX(0);
							tempObject.setVelY(-2);
						//}
						//else if(turnOpen(key)>0) {
						//	while((int)tempObject.getX() != turnOpen(key)) {
								//do nothing
						//	}
						//	tempObject.setVelY(-1);
						//}
						//else if(turnOpen(key)==0) {
						//	tempObject.setVelX(0);
						//	tempObject.setVelY(-1);
						//}
						
					}
					if(key == KeyEvent.VK_S) {
						//if(tempObject.getVelX()==0 && tempObject.getVelY()==0) {
							tempObject.setVelX(0);
							tempObject.setVelY(2);
						//}
						//else if(turnOpen(key)>0) {
						//	while((int)tempObject.getX() != turnOpen(key)) {
								//do nothing
						//		System.out.println(turnOpen(key));
						//	}
						//	tempObject.setVelY(1);
						//}
						//else if(turnOpen(key)==0) {
						//	tempObject.setVelX(0);
						//	tempObject.setVelY(1);
						//}
					}
					if(key == KeyEvent.VK_D) {
					//	if(tempObject.getVelX()==0 && tempObject.getVelY()==0) {
							tempObject.setVelX(2);
							tempObject.setVelY(0);
					//	}
					//	else if(turnOpen(key)>0) {
					//		while((int)tempObject.getY() != turnOpen(key)) {
								//do nothing
					//		}
					//		tempObject.setVelX(1);
					//	}
					//	else if(turnOpen(key)==0) {
					//		tempObject.setVelX(1);
					//		tempObject.setVelY(0);
					//	}
					}
					if(key == KeyEvent.VK_A) {
						//if(tempObject.getVelX()==0 && tempObject.getVelY()==0) {
							tempObject.setVelX(-2);
							tempObject.setVelY(0);
						//}
						//else if(turnOpen(key)>0) {
						//	while((int)tempObject.getY() != turnOpen(key)) {
								//do nothing
						//	}
						//	tempObject.setVelX(-1);
						//}
						//else if(turnOpen(key)==0) {
						//	tempObject.setVelX(-1);
						//	tempObject.setVelY(0);
						//}
					}
				}
			}
			
			if(tempObject.getId() == ID.Player2){
				//key events for player 2
				
				if(key == KeyEvent.VK_UP) {
					tempObject.setVelX(0);
					tempObject.setVelY(-2);
				}
				if(key == KeyEvent.VK_DOWN) {
					tempObject.setVelX(0);
					tempObject.setVelY(2);
				}
				if(key == KeyEvent.VK_RIGHT) {
					tempObject.setVelX(2);
					tempObject.setVelY(0);
				}
				if(key == KeyEvent.VK_LEFT) {
					tempObject.setVelX(-2);
					tempObject.setVelY(0);
				}
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
	}
	
	//was working on changing P2's movement system... ignore below code
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player2){
				
			}
		}
	}
	
	//ignore code below... was working on changing P1's movement system
	//returns coordinate to turn or -1 if blocked
	public int turnOpen(int key) {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player){
				
			}
		}
		return -1;
	}
	
	//checks for player collision with lines
	public boolean touchingLines(int xPos,int yPos) {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player || tempObject.getId() == ID.Player2){
		
				for(int k=0; k<Map.lines.length; k++) {
					for(int j=0; j<=32; j++) {
				
						if(tempObject.getX()+j > Map.lines[k][0] && tempObject.getX() < Map.lines[k][0] + Map.lines[k][2]) {
							if(tempObject.getY()+j > Map.lines[k][1] && tempObject.getY() < Map.lines[k][1] + Map.lines[k][3]) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

}
