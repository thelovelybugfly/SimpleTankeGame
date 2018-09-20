package tank.game;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class Boom {

	public int x;
	public int y;
	private int i;
	
	public Boom(int x ,int y) {
		this.x = x;
		this.y = y;
	}
	
	public void draw(Image img , Graphics g , JPanel p){
		g.drawImage(img, x << 5, y << 5, (x + 1) << 5 , (y + 1) << 5 , i << 5 , 0 , 
				(i + 1) << 5 , 32 , p);
		i++;
		if(i > 4){
			GameJPanel.booms.remove(this);
		}
	}

}

