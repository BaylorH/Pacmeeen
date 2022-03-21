package MyGame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 2516295409585506749L;
	
	//Dimensions of screen
	public static final int WIDTH = 960;
	public static final int HEIGHT = 720;
	
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	private GameOver gameOver;
	
	public STATE gameState = STATE.Menu;
	
	public enum STATE{
		Menu,
		Game,
		GameOver
	};
	
	public Game() {
		handler = new Handler();
		menu = new Menu(this, handler);
		gameOver = new GameOver(this, handler);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		this.addMouseListener(gameOver);
		
		new Window(WIDTH, HEIGHT, "		( ͠° ͟ʖ ͡° ) PAC MEEEN ¯\\_(ツ)_/¯                                                                           Created by Baylor", this); 
		
		hud = new HUD();
		spawner = new Spawn(handler, hud);
	}	

	public synchronized void start() {
		thread = new Thread(this);
		thread.start(); 
		running = true;
	}
	
	public synchronized void stop () {
		try {
			thread.join();
			running = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		//when all coins have been collected by player
		if(Coins.isCoinsEmpty()) {
			HUD.LIVES++;
			HUD.score+=1000;
			HUD.level++;
			HUD.powerUp = false;
			handler.clearScreen();
			
			Map map = new Map(Game.WIDTH/2-32, 32, ID.Map, handler);
			Coins coins = new Coins(Game.WIDTH/2-32, 32, ID.Coins, handler);
			PowerCoins powerCoins = new PowerCoins(Game.WIDTH/2-32, 32, ID.Coins, handler);
			Fruits fruit = new Fruits(Game.WIDTH+100,Game.HEIGHT+100,ID.Apple);
			
			//checks if game objects have already been instantiated in the game
			handler.addObject(map);
			handler.addObject(coins);
			handler.addObject(powerCoins);
			handler.addObject(fruit);
			
			//attempting to debug. Removing the doubles of objects
			for(int i = 0; i < handler.object.size(); i ++) {
				GameObject tempObject = handler.object.get(i);
				
				if(tempObject.getId() == ID.Apple) {
					handler.removeObject(tempObject);
				}
				if(tempObject.getId() == ID.Blinky) {
					handler.removeObject(tempObject);
				}
				if(tempObject.getId() == ID.Blinky2) { 
					handler.removeObject(tempObject);
				}
				if(tempObject.getId() == ID.Blinky3) { 
					handler.removeObject(tempObject);
				}
				if(tempObject.getId() == ID.Pinky) { 
					handler.removeObject(tempObject);
				}
				if(tempObject.getId() == ID.Player) { 
					handler.removeObject(tempObject);
				}
				if(tempObject.getId() == ID.Player2) { 
					handler.removeObject(tempObject);
				}
			}
			
			boolean playerExists = false;
			boolean blinkyExists = false;
			boolean blinky2Exists = false;
			boolean blinky3Exists = false;
			boolean pinkyExists = false;
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
				Blinky.releaseTimer = 250;
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
				Blinky2.releaseTimer = 300;
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
				Blinky3.releaseTimer = 350;
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
				Pinky.releaseTimer = 1000;
			}
			
			if(Menu.isSecondPlayer()) {
				handler.addObject(new Player(500,476, ID.Player2, handler));
			}
			
			Menu.setFirstRun(false);
			
			//end of debugging
		}
		//when player runs out of lives
		if(HUD.LIVES==0) {
			gameState = STATE.GameOver;
			handler.clearScreen();
			for(int i = 0; i < handler.object.size(); i ++) {
				GameObject tempObject = handler.object.get(i);
				
				if(tempObject.getId() == ID.Apple) {
					handler.removeObject(tempObject);
				}
				if(tempObject.getId() == ID.Blinky) {
					handler.removeObject(tempObject);
				}
				if(tempObject.getId() == ID.Blinky2) { 
					handler.removeObject(tempObject);
				}
				if(tempObject.getId() == ID.Blinky3) { 
					handler.removeObject(tempObject);
				}
				if(tempObject.getId() == ID.Pinky) { 
					handler.removeObject(tempObject);
				}
				if(tempObject.getId() == ID.Player) { 
					handler.removeObject(tempObject);
				}
			}
			HUD.LIVES=-1;
		}
		else if(gameState == STATE.Game) {
			handler.tick();
			hud.tick();
			spawner.tick();
		}
		else if(gameState == STATE.Menu) {
			menu.tick();
		}
		else if(gameState == STATE.GameOver) {
			gameOver.tick();
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return; 
		}
		
		Graphics g = bs.getDrawGraphics();
		
		//background color
		g.setColor(Color.black);
		g.fillRect(0,0,WIDTH,HEIGHT);
		
		if(gameState == STATE.Game) {
			handler.render(g);
			hud.render(g);
		}
		else if(gameState == STATE.Menu) {
			menu.render(g);
		}
		else if(gameState == STATE.GameOver) {
			gameOver.render(g);
		}
		g.dispose();
		bs.show();
	}
	
	public static float clamp(float var, float min, float max) {
		if(var >= max)
			return var = max;
		if(var <= min)
			return var = min;
		else
			return var;
	}

	public static void main(String[] args) {
		new Game();
	}
}
