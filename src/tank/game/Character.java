package tank.game;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

public class Character {
	
	protected int x;	
	protected int y;	
	protected int direct;	
	protected int step = 0 ;	

	public Character(int x,int y,int direct){
		this.x = x;
		this.y = y;
		this.direct = direct;
	}
	
	public void draw(Image character, Graphics g, JPanel p,Image mapImage){
		g.drawImage(character, x << 5, y << 5 , (x + 1) << 5, (y + 1) << 5, 
				direct << 5, 0 , (direct + 1) << 5, 1 << 5, p);
		step();
		underGrass(g , p , mapImage);
		underwater(g, p, mapImage);
	}

	
	public void underGrass(Graphics g, JPanel p, Image mapImage) {
		if(GameMap.map[y][x] == 2){
			g.drawImage(mapImage,(x << 5)-5, (y << 5)-5 , ((x + 1) << 5) + 5, ((y + 1) << 5) + 5, 
					32*2, 0, 32*3 , 32, p);
		}		
	}
	public void underwater(Graphics g, JPanel p, Image mapImage) {
		if(GameMap.map[y][x] == 1){
			g.drawImage(mapImage,(x << 5), (y << 5), ((x + 1) << 5), ((y + 1) << 5), 
					 32*3, 0, 32*4 , 32 , p);
		}
	}
	
	private void step() {
		step++;
		if(step == 2){
			step = 0;
		}
	}
	
}

