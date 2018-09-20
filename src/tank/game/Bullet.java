package tank.game;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/*
 * 子弹类
 * */
public abstract class Bullet extends Character implements Runnable{

	public int x;
	public int y;
	public int direct;
	public boolean isDrawBullet = true;
	

	

	
	public Bullet(int x ,int y ,int d ){
		super(x,y,d);
		this.x = x;
		this.y = y;
		synDiretion(d);
		new Thread(this).start();
	}
	
	public abstract void synDiretion(int d);
	
	public void move(){
		switch(direct){
		case 0:
			y--;
			break;
		case 1:
			x++;
			break;
		case 2:
			y++;
			break;
		case 3:
			x--;
			break;
		}
	}
	
	// 重写绘制方法
	public void draw(Image img, Graphics g, JPanel p,Image mapImage){
		g.drawImage(img, x << 5 , y << 5 , (x + 1) << 5 , (y + 1) << 5 , direct << 5 , 0 ,
				(direct + 1) << 5, 32 , p);
		underGrass(g,p,mapImage);
	}
	
	public void collision(int[][] map){
		if(y < 0 || x < 0 || x > 24 || y > 24){
			bulletJudge();
		}else if(map[y][x] == 4){
			map[y][x] = 0;
			bulletJudge();
			GameJPanel.booms.add(new Boom(x,y));
		}else if(map[y][x] == 6){
			GameJPanel.isOver = true;
			bulletJudge();
			GameJPanel.booms.add(new Boom(x,y));
		}else if(map[y][x] == 3){
			bulletJudge();
			GameJPanel.booms.add(new Boom(x,y));
		}
		
		
	}
	
	public void bulletJudge(){
		isDrawBullet = false;
		GameJPanel.bullets.remove(this);
		GameJPanel.enemyBullets.remove(this);
	}
	
//	 子弹对撞
	public void bulletEqual( ){
		for(int i = 0 ; i < GameJPanel.bullets.size() ; i++){
			PlayerBullet myBullet = GameJPanel.bullets.get(i);
			for(int j = 0 ; j < GameJPanel.enemyBullets.size() ; j++){
				EnemyBullet enemyBullet = GameJPanel.enemyBullets.get(j);
				if(myBullet.x == enemyBullet.x && myBullet.y == enemyBullet.y){
					isDrawBullet = false;
					GameJPanel.bullets.remove(i);
					GameJPanel.enemyBullets.remove(j);
					GameJPanel.booms.add(new Boom(x,y));
				}
			}
		}
	}
	

	
	
	
	public void run(){
		while(isDrawBullet){ //控制子弹移动
			move();
			bulletEqual();
			if(GameJPanel.isOver){
				isDrawBullet = false;
			}
			collision(GameMap.map);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void bulletPlayer(){

	}
	

}

