package tank.game;

import java.awt.Color;

import javax.swing.JFrame; 

public class GameJFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GameJFrame(String title) {
		// 对新窗口进行设置一些样式
		this.setTitle(title);
		// 设置宽高
		this.setSize(806, 828);
		
		//设置背景颜色
		this.setBackground(Color.black);
		// 设置窗口居中
		this.setLocation(400, 10);
		// 问题：关闭了窗口，程序并没有一起结束
		// 完成对窗口的渲染（地图，坦克）-->JPanel
		new Music();
		// 将窗口跟渲染面板(GameJPanel)关联
		this.setContentPane(new GameJPanel());
		//静止窗口缩放变化
		this.setResizable(false);
		//把窗口显示出来，没有宽高
		this.setVisible(true);
		// 确保关闭窗口同时程序也结束掉
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GameJFrame("坦克大战怀旧版");
	}

}
