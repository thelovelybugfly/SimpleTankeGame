package tank.game;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.JPanel;

public class EnemyTank extends Character implements Runnable{

	
	public int speed;
	
	
	public int bulletTime = 1;
	
	public int time;
	
	private int starId;
	public boolean isDrawStar = true;
	
	public boolean isDrawTank = true;
	
	public EnemyTank(int x, int y, int direct) {
		super(x, y, direct);
		new Thread(this).start();
	}
	
	
	public void draw(Image character, Graphics g, JPanel p , Image mapImage , Image star){
		if(isDrawStar){
			drawStar(g , p , star);
		}else{
			
			draw(character ,g , p , mapImage);
		}
	}
	
	
	public void draw(Image character, Graphics g, JPanel p, Image mapImage) {
		g.drawImage(character, x << 5, y << 5 , (x + 1) << 5, (y + 1) << 5, 
				0, direct << 5 , 32, (direct + 1) << 5, p);
		super.underGrass(g , p , mapImage);
	}

	
	public void drawStar(Graphics g, JPanel p, Image star) {
		if(starId >= 9){
			starId = 0;
			isDrawStar = false;
		}
		starId++;
		g.drawImage(star, x << 5, y << 5 , (x + 1) << 5, (y + 1) << 5, 
				(starId << 5) , 0 , (starId + 1) << 5 , 32 , p);
		
	}
	
	protected void move(int[][] map){
		if((direct == 0 && y == 0) || (direct == 1 && x == 24) || (direct == 2 && y == 24) || 
				(direct == 3 && x == 0)){
			direct = new Random().nextInt(4);
		}else{
			if((direct == 2)&& otherTankY(1)  && (map[y + 1][x] == 0 || map[y + 1][x] == 2 || map[y + 1][x] == 5 )){
				if(map[y + 1][x] == 5 && y != 23 &&  otherTankY(2)){
					y++;
				}
				y++;
			}else if((direct == 3)&& otherTankX(-1) && (map[y][x - 1] == 0 || map[y][x - 1] == 2 || map[y][x - 1] == 5)){
				if(map[y][x - 1] == 5 && x != 1 &&  otherTankX(-2)){
					x--;
				}
				x--;
			}else if((direct == 1)&& otherTankX(1) && (map[y][x + 1] == 0 || map[y][x + 1] == 2 || map[y][x + 1] == 5)){
				if(map[y][x + 1] == 5 && x != 23 &&  otherTankX(2)){
					x++;
				}
				x++;
			}else if((direct == 0)&& otherTankY(-1) && (map[y - 1][x] == 0 || map[y - 1][x] == 2 || map[y - 1][x] == 5)){
				if(map[y - 1][x] == 5 && y != 1 &&  otherTankY(-2)){
					y--;
				}
				y--;
			}else{
				direct = new Random().nextInt(4);
			}
		}
		
	}
	
	
	public boolean otherTankX(int a){
		for(int i = 0 ; i < GameJPanel.tanks.size() ; i++){
			if(GameJPanel.tanks.get(i).x == x+ a && GameJPanel.tanks.get(i).y == y){
				return false;
			}
		}
		if(GameJPanel.player.x == x + a && GameJPanel.player.y == y){
			return false;
		}
		return true;
	}	
	
	
	public boolean otherTankY(int a){
		for(int i = 0 ; i < GameJPanel.tanks.size() ; i++){
			if(GameJPanel.tanks.get(i).x == x && GameJPanel.tanks.get(i).y == y+ a){
				return false;
			}
		}
		if(GameJPanel.player.x == x && GameJPanel.player.y == y+ a){
			return false;
		}
		return true;
	}
	


	public void run() {
		
		int starTime = 0;
		
		int tSign = 0;
		while(isDrawTank){
			if(isDrawStar){
				starTime++;
				if(starTime > 30){
					isDrawStar = false;
				}
			}else{
				time++;
				tSign++;
				bulletTime++;
				if(tSign > speed){
					move(GameMap.map);
					tSign = 0;
					if(time > 12){ 
						direct = new Random().nextInt(4);
						time = 0;
					}
				}
				
				if(bulletTime % 10 == 0){
					GameJPanel.enemyBullets.add(new EnemyBullet(x, y, direct));
				}
				if(bulletTime == 100){
					bulletTime = 1;
				}
			}
			
			if(GameJPanel.isOver){
				isDrawTank = false;
			}
			
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		GameJPanel.tanks.remove(this); 
		
	}


}
