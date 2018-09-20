package tank.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class GameJPanel extends JPanel implements Runnable{

	private static final long serialVersionUID = 1L;
	
	// 创建图片，地图变量
	public Image mapImage;	
	public Image imageTwo;	
	public int [][] map;	
	public Image star;		
	public Image bullet;	
	public Image boom;	
	public Image playerImage;	
	public Image enemyImage;	
	public Image buffImage;		
	
	
	public static boolean isOver = false;
	public static boolean isWinner = false;
	
	
	
	public static int playerAge = 2;
	private int tankCount =0;	
	private int ps =0;	
	
	public static Vector<EnemyTank> tanks = new Vector<EnemyTank>();
	
	public static Vector<PlayerBullet> bullets = new Vector<PlayerBullet>();
	
	public static Vector<EnemyBullet> enemyBullets = new Vector<EnemyBullet>();
	
	public static Vector<Boom> booms = new Vector<Boom>();
	
	
	static Player player = new Player(10,24,0);
	
	
	public MediaTracker mt;
	
	
	public GameJPanel() {
		
		map = GameMap.map;
		
		
		mt = new MediaTracker(this);
		this.setBackground(Color.black);
		
		try {
			mapImage = ImageIO.read(new File("imgs/com.bmp"));
			imageTwo = ImageIO.read(new File("imgs/map.bmp"));
			playerImage = ImageIO.read(new File("imgs/player.png"));
			enemyImage = ImageIO.read(new File("imgs/tank.png"));
			star = ImageIO.read(new File("imgs/player_show.bmp"));
			enemyImage = ImageIO.read(new File("imgs/tank.png"));
			bullet = ImageIO.read(new File("imgs/bullet.png"));
			boom = ImageIO.read(new File("imgs/boom.png"));
			
			
			mt.addImage(mapImage, 1);
			mt.addImage(imageTwo, 2);
			mt.addImage(playerImage, 3);
			mt.addImage(enemyImage, 4);
			mt.addImage(star, 5);
			mt.addImage(enemyImage, 6);
			mt.addImage(bullet, 7);
			mt.addImage(boom, 8);
			
		
			mt.waitForAll();
		} catch (IOException e) {
			
			e.printStackTrace();
		}catch (InterruptedException e1) {
			
			e1.printStackTrace();
		}

	
		this.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e){

			
				if(!isOver){
					player.move(e,map);
				}
				
			}
			
		});
		
		this.setFocusable(true);
		
		new Thread(this).start();
		
		
	}
	

	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		
		drawMap(g);
		player.draw(playerImage,g,this,mapImage);
		drawTank(g);
		drawBullet(g);
		enemyBullet(g);
		drawBoom(g);
		
		
	}

	
	private void drawMap(Graphics g) {
		
		for(int i = 0 ; i< map.length ; i++){
			for(int j = 0 ; j < map[i].length ; j++){
				switch (map[i][j]){
				case 1:
					//蓝色的水
					g.drawImage(mapImage, 32 * j , i * 32 , 32 * j + 32 , 32 * i + 32 , 32*3, 0, 32*4 , 32 , this);
					break;
				case 2:
					//绿色的草
					g.drawImage(mapImage, 32 * j , i * 32 , 32 * j + 32 , 32 * i + 32 , 32*2, 0, 32*3 , 32 , this);
					break;
				case 3:
					//白色的铁
					g.drawImage(mapImage, 32 * j , i * 32 , 32 * j + 32 , 32 * i + 32 , 32*1, 0, 32*2 , 32 , this);
					break;
				case 4:
					//褐色的土墙
					g.drawImage(mapImage, 32 * j , i * 32 , 32 * j + 32 , 32 * i + 32 , 0, 0, 32*1, 32 , this);
					break;
				case 5:
					//冰块
					g.drawImage(imageTwo, 32 * j , i * 32 , 32 * j + 32 , 32 * i + 32 , 32*5, 0, 32*6 , 32 , this);
					break;
				case 6:
					//老王boss 
					g.drawImage(mapImage, 32 * j , i * 32 , 32 * j + 32 , 32 * i + 32 , 32*5, 0, 32*6 , 32 , this);
					break;

				}
			}
		}
		
	}

	
	
	public void run() {
		
		while(true){
			try {
				Thread.sleep(50);
				createTank();
			
				
				this.repaint();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
		}
	}

	private void createTank(){
		if(tanks.size() < 8){ 
			EnemyTank tank = null;
			if(ps > 2){
				ps = 0;
			}
			int tj = 12 * ps;
			tank = new SimpleTank(tj , 0 , 2);
			tanks.add(tank);
			ps++;
			tankCount++;
			if(tankCount>=11){ 
				isOver = true;
			}
		}
	}

	
		private void drawTank(Graphics g){
			for(int i = 0 ; i < tanks.size() ; i++){
				tanks.get(i).draw(enemyImage ,g, this ,mapImage , star);
			}
		}
		
	
	private void drawBullet(Graphics g) {
		for(int i = 0 ; i < bullets.size() ; i++){
			bullets.get(i).draw(bullet, g, this, mapImage);
		}
		
	}
	
	
	private void enemyBullet(Graphics g) {
		for(int i = 0 ; i < enemyBullets.size() ; i++){
			enemyBullets.get(i).draw(bullet, g, this, mapImage);
		}
	}
	
	
	private void drawBoom(Graphics g) {
		for(int i = 0 ; i < booms.size() ; i++){
			booms.get(i).draw(boom, g, this);
		}
		
	}

}

