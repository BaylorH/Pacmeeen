package MyGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Map extends GameObject {
	Random r = new Random();
	Handler handler;
	
	int row = 0;
	
	public static int[][] lines = new int[65][4];{
	
	//center box (pink)
	lines[row][0] = 442; lines[row][1] = 316; lines[row][2] = 55; lines[row][3] = 2; row++;
	//center box (blue)
	lines[row][0] = 412; lines[row][1] = 315; lines[row][2] = 5; lines[row][3] = 55; row++;
	lines[row][0] = 417; lines[row][1] = 315; lines[row][2] = 25; lines[row][3] = 5; row++;
	lines[row][0] = 492; lines[row][1] = 315; lines[row][2] = 25; lines[row][3] = 5; row++;
	lines[row][0] = 412; lines[row][1] = 365; lines[row][2] = 110; lines[row][3] = 5; row++;
	lines[row][0] = 517; lines[row][1] = 315; lines[row][2] = 5; lines[row][3] = 55; row++;
	//top & bottom of map
	lines[row][0] = 202; lines[row][1] = 55; lines[row][2] = 530; lines[row][3] = 5; row++;
	lines[row][0] = 202; lines[row][1] = 615; lines[row][2] = 535; lines[row][3] = 5; row++;
	//left border of map
	lines[row][0] = 202; lines[row][1] = 55; lines[row][2] = 5; lines[row][3] = 105; row++;
	lines[row][0] = 152; lines[row][1] = 155; lines[row][2] = 55; lines[row][3] = 5; row++;
	lines[row][0] = 152; lines[row][1] = 210; lines[row][2] = 55; lines[row][3] = 5; row++;
	lines[row][0] = 202; lines[row][1] = 210; lines[row][2] = 5; lines[row][3] = 50; row++;
	lines[row][0] = 202; lines[row][1] = 260; lines[row][2] = 50; lines[row][3] = 5; row++;
	lines[row][0] = 252; lines[row][1] = 260; lines[row][2] = 5; lines[row][3] = 150; row++;
	lines[row][0] = 152; lines[row][1] = 410; lines[row][2] = 105; lines[row][3] = 5; row++;
	lines[row][0] = 152; lines[row][1] = 460; lines[row][2] = 105; lines[row][3] = 5; row++;
	lines[row][0] = 202; lines[row][1] = 460; lines[row][2] = 5; lines[row][3] = 160; row++;
	//right border of map
	lines[row][0] = 732; lines[row][1] = 55; lines[row][2] = 5; lines[row][3] = 105; row++;
	lines[row][0] = 732; lines[row][1] = 155; lines[row][2] = 55; lines[row][3] = 5; row++;
	lines[row][0] = 732; lines[row][1] = 210; lines[row][2] = 55; lines[row][3] = 5; row++;
	lines[row][0] = 732; lines[row][1] = 210; lines[row][2] = 5; lines[row][3] = 50; row++;
	lines[row][0] = 687; lines[row][1] = 260; lines[row][2] = 50; lines[row][3] = 5; row++;
	lines[row][0] = 682; lines[row][1] = 260; lines[row][2] = 5; lines[row][3] = 150; row++;
	lines[row][0] = 682; lines[row][1] = 410; lines[row][2] = 105; lines[row][3] = 5; row++;
	lines[row][0] = 682; lines[row][1] = 460; lines[row][2] = 105; lines[row][3] = 5; row++;
	lines[row][0] = 732; lines[row][1] = 460; lines[row][2] = 5; lines[row][3] = 160; row++;
	//top left fill of map
	lines[row][0] = 252; lines[row][1] = 105; lines[row][2] = 5; lines[row][3] = 55; row++;
	lines[row][0] = 252; lines[row][1] = 105; lines[row][2] = 55; lines[row][3] = 5; row++;
	lines[row][0] = 252; lines[row][1] = 210; lines[row][2] = 55; lines[row][3] = 5; row++;
	lines[row][0] = 307; lines[row][1] = 210; lines[row][2] = 5; lines[row][3] = 110; row++;
	lines[row][0] = 357; lines[row][1] = 105; lines[row][2] = 5; lines[row][3] = 165; row++;
	lines[row][0] = 307; lines[row][1] = 155; lines[row][2] = 55; lines[row][3] = 5; row++;
	lines[row][0] = 307; lines[row][1] = 365; lines[row][2] = 55; lines[row][3] = 5; row++;
	lines[row][0] = 357; lines[row][1] = 315; lines[row][2] = 5; lines[row][3] = 55; row++;
	lines[row][0] = 464; lines[row][1] = 105; lines[row][2] = 5; lines[row][3] = 110; row++;
	lines[row][0] = 412; lines[row][1] = 155; lines[row][2] = 5; lines[row][3] = 110; row++;
	lines[row][0] = 412; lines[row][1] = 105; lines[row][2] = 110; lines[row][3] = 5; row++;
	lines[row][0] = 412; lines[row][1] = 265; lines[row][2] = 110; lines[row][3] = 5; row++;
	//top right fill of map
	lines[row][0] = 517; lines[row][1] = 155; lines[row][2] = 5; lines[row][3] = 110; row++;
	lines[row][0] = 572; lines[row][1] = 105; lines[row][2] = 5; lines[row][3] = 165; row++;
	lines[row][0] = 572; lines[row][1] = 155; lines[row][2] = 55; lines[row][3] = 5; row++;
	lines[row][0] = 627; lines[row][1] = 105; lines[row][2] = 55; lines[row][3] = 5; row++;
	lines[row][0] = 677; lines[row][1] = 105; lines[row][2] = 5; lines[row][3] = 55; row++;
	lines[row][0] = 627; lines[row][1] = 210; lines[row][2] = 60; lines[row][3] = 5; row++;
	lines[row][0] = 627; lines[row][1] = 210; lines[row][2] = 5; lines[row][3] = 110; row++;
	lines[row][0] = 572; lines[row][1] = 315; lines[row][2] = 5; lines[row][3] = 55; row++;
	lines[row][0] = 572; lines[row][1] = 365; lines[row][2] = 60; lines[row][3] = 5; row++;
	//bottom left fill of map
	lines[row][0] = 307; lines[row][1] = 410; lines[row][2] = 105; lines[row][3] = 5; row++;
	lines[row][0] = 307; lines[row][1] = 410; lines[row][2] = 5; lines[row][3] = 55; row++;
	lines[row][0] = 357; lines[row][1] = 465; lines[row][2] = 5; lines[row][3] = 55; row++;
	lines[row][0] = 357; lines[row][1] = 515; lines[row][2] = 55; lines[row][3] = 5; row++;
	lines[row][0] = 412; lines[row][1] = 465; lines[row][2] = 110; lines[row][3] = 5; row++;
	lines[row][0] = 462; lines[row][1] = 410; lines[row][2] = 5; lines[row][3] = 55; row++;
	lines[row][0] = 252; lines[row][1] = 515; lines[row][2] = 55; lines[row][3] = 5; row++;
	lines[row][0] = 252; lines[row][1] = 515; lines[row][2] = 5; lines[row][3] = 55; row++;
	lines[row][0] = 307; lines[row][1] = 570; lines[row][2] = 55; lines[row][3] = 5; row++;
	//bottom right fill of map
	lines[row][0] = 412; lines[row][1] = 570; lines[row][2] = 110; lines[row][3] = 5; row++;
	lines[row][0] = 462; lines[row][1] = 515; lines[row][2] = 5; lines[row][3] = 55; row++;
	lines[row][0] = 522; lines[row][1] = 410; lines[row][2] = 110; lines[row][3] = 5; row++;
	lines[row][0] = 627; lines[row][1] = 410; lines[row][2] = 5; lines[row][3] = 55; row++;
	lines[row][0] = 577; lines[row][1] = 465; lines[row][2] = 5; lines[row][3] = 55; row++;
	lines[row][0] = 522; lines[row][1] = 515; lines[row][2] = 55; lines[row][3] = 5; row++;
	lines[row][0] = 632; lines[row][1] = 515; lines[row][2] = 55; lines[row][3] = 5; row++;
	lines[row][0] = 577; lines[row][1] = 570; lines[row][2] = 55; lines[row][3] = 5; row++;
	lines[row][0] = 682; lines[row][1] = 515; lines[row][2] = 5; lines[row][3] = 55; row++;
	
	}

	public Map(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}
	
	public Rectangle getBounds() {
		//center box
		return new Rectangle(442, 316, 55, 2);
	}
	
	public void render(Graphics g) {
		//center box
		g.setColor(Color.pink);
		g.fillRect(442, 316, 55, 2);
		
		g.setColor(Color.blue);
		for(int a=1; a<lines.length; a++) {
			g.fillRect(lines[a][0], lines[a][1], lines[a][2], lines[a][3]);
		}
	}

	public void tick() {
	}

}