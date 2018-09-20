package tank.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

/*
 * 玩家类
 */
public class Player extends Character {

	public Player(int x, int y, int direct) {
		super(x, y, direct);

	}

	@Override
	public void draw(Image character, Graphics g, JPanel p, Image mapImage) {

		super.draw(character, g, p, mapImage);
	}
	
	public void move(KeyEvent e, int[][] map) {

		switch (e.getKeyCode()) {
		// 方向向上
		//地图坐标 25x25;
		case KeyEvent.VK_UP:
			direct = 0;
			if (y <=25 && y > 0) {
				if ((map[y - 1][x] == 0 || map[y - 1][x] == 2||map[y - 1][x] == 1)
						&& otherTankY(-1)) {
					y--;
				}if (y>0 && map[y - 1][x] == 5) {
					if (y == 1 || !otherTankY(-2)) {
						y--;
					} else {
						y -= 2;
					}
				}
			}if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				keySpace();
			}
			break;
		case KeyEvent.VK_DOWN:
			direct = 1;
			if (y < 24 && y >= 0) {
				if ((map[y + 1][x] == 0 || map[y + 1][x] == 2||map[y + 1][x] == 1) && otherTankY(1)) {
					y++;
				} if (y<24 && map[y + 1][x] == 5) {
					if (y == 23 || !otherTankY(2)) {
						y--;
					} else {
						y += 2;

					}
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				keySpace();
			}
			break;
		case KeyEvent.VK_LEFT:
			direct = 2;
			if (x <= 24 && x > 0) {
				if ((map[y][x - 1] == 0 || map[y][x - 1] == 2||map[y][x - 1] == 1)
						&& otherTankX(-1)) {
					x--;
				}  if (x>0 && map[y][x - 1] == 5) {
					if (x == 1 || !otherTankX(-2)) {
						x--;
					} else {
						x -= 2;
					}

				}
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				keySpace();
			}
			break;
		case KeyEvent.VK_RIGHT:
			direct = 3;
			if (x < 24 && x >= 0) {
				if ((map[y][x + 1] == 0 || map[y][x + 1] == 2||map[y][x + 1] == 1) && otherTankX(1)) {
					x++;
				}  if (x<24 && map[y][x + 1] == 5) {
					if (x == 23 || !otherTankX(2)) {
						x++;

					} else {
						x += 2;
					}

				}
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				keySpace();
			}
			break;
		case KeyEvent.VK_SPACE:
			keySpace();
			break;

		}

	}

	// 空格进行发弹
	public void keySpace() {
		GameJPanel.bullets.add(new PlayerBullet(x, y, direct));
	}

	// 判断Y方向是否有其他坦克
	public boolean otherTankY(int a) {
		for (int i = 0; i < GameJPanel.tanks.size(); i++) {
			if (GameJPanel.tanks.get(i).x == x
					&& GameJPanel.tanks.get(i).y == y + a) {
				return false;
			}
		}
		return true;
	}

	// 判断X方向是否有其他坦克
	public boolean otherTankX(int a) {
		for (int i = 0; i < GameJPanel.tanks.size(); i++) {
			if (GameJPanel.tanks.get(i).x == x + a
					&& GameJPanel.tanks.get(i).y == y) {
				return false;
			}
		}
		return true;
	}

}