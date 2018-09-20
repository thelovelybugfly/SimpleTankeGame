package tank.game;


public class PlayerBullet extends Bullet {


	
	public PlayerBullet(int x,int y,int d){
		super(x,y,d);
	
	}

	// 重写父类抽象方法
	@Override
	public void synDiretion(int d) {
		switch(d){
		case 0:
			this.direct = 0;
			break;
		case 1:
			this.direct = 2;
			break;
		case 2:
			this.direct = 3;
			break;
		case 3:
			this.direct = 1;
			break;
		}
	}
	// 击杀敌方坦克
	public void collision(int[][] map){
		super.collision(map);
		
		for(int i = 0 ; i < GameJPanel.tanks.size(); i++){
			EnemyTank tank = GameJPanel.tanks.get(i);
			if(tank.y == y && tank.x == x){
				tank.isDrawTank = false;
				this.isDrawBullet = false;
				GameJPanel.bullets.remove(this);
				GameJPanel.booms.add(new Boom(x,y));
				GameJPanel.tanks.remove(i);
				
			}
		}
		
	}
	
	

}
