package MyGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import MyGame.Game.STATE;

public class GameOver extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	
	public GameOver(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		//player presses play again
		if(mouseOver(mx, my, 210*3/2, 300, 200*3/2, 100) && game.gameState == STATE.GameOver) {
			game.gameState = STATE.Menu;
			HUD.LIVES = 3;
			Player.setFruitCollected(false);
			Fruits.turn = 1;
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
		g.setColor(Color.white);
		g.drawString("GAMEOVER",  275, 215);
		
		g.setFont(fnt2);
		g.drawRect(210*3/2, 300, 200*3/2, 100);
		g.drawString("Menu",  405, 365);
		
		g.setFont(fnt3);
		g.setColor(Color.yellow);
		g.drawString("Score: " + HUD.score, 415, 250);
	}

}