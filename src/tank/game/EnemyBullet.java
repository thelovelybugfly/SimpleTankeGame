package tank.game;

public class EnemyBullet extends Bullet {

	
	
	public EnemyBullet(int x,int y,int d){
		super(x,y,d);
	}

	@Override
	//重写抽象方法
	public void synDiretion(int d) {
		this.direct = d;
	}
	
	public void collision(int[][] map){
		super.collision(map);

		Player tank = GameJPanel.player;
		if(tank.y == y && tank.x == x){
			playerAge(tank);

			this.isDrawBullet = false;
			GameJPanel.enemyBullets.remove(this);
			GameJPanel.booms.add(new Boom(x,y));

		}
	}
	
	// 判断玩家生命
	public void playerAge(Player tank){
		if(GameJPanel.playerAge != 0){
			GameJPanel.playerAge--;
			tank.x = 10;
			tank.y = 24;
			tank.direct = 0;
		}else{
			
			
			GameJPanel.isOver = true;
		}
		
	}

	
	

}
