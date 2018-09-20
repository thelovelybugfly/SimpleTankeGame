package tank.game;

public class TankDemo {

	// 1.GUI
	// 2.JAVA SWING //JAVA GUI实现 用于绘制窗口
	// 3.MVC 软件代码设计规范
	
	/*
	 * JAVA针对GUI有两套实现：AWT SWing
	 * 	1.AWT 原生图形构造类：
	 * 		底层：java使用操作系统自带的图形API来完成GUI的，JAVA为了保证AWT能够跨平台性能，
	 * 			那么使用的API是每个操作系统的交集；
	 * 		问题：能用的东西不多，造成AWT的实现效果不佳。
	 * 2.Swing
	 * 		Swing是基于AWT的，但是里面的代码完全是由JAVA写的，并且在AWT实现的功能又加了很多额外的功能；
	 * 		特点：能够使用的API远远大于AWT；
	 * 		tip:Swing使用的是java代码，在执行时，java代码会转换为底层代码（C和C++）才能使系统资源处理此业务；
	 * 			而AWT是直接使用操作系统的资源，所以 性能：AWT > Swing , 功能：Swing > AWT;
	 * 
	 * JAVA里面Swing中使用JFrame 和 JPanel 来绘制
	 * JFrame --> 创建一个窗口
	 * JPanel --> 向窗口中渲染地图
	 * 
	 * 
	 * 如何绘制地图：
	 * 1.先使用Excel表规划好地图的设计
	 * 
	 * 
	 * 如何让tank移动
	 * 	1.本质上改变tank在地图上的坐标
	 * 	2.难点：通过键盘上的方向键来进行控制(键盘监听事件)
	 * 
	 * 如何让面板重新渲染
	 * 	1.把GameJPanel类实现Runnable接口，开启线程，线程里调用渲染方法
	 * 	2.确保渲染方法里调用了父类的渲染方法：super.paintComponent(g);
	 * 
	 * 
	 * */
	
	// 坦克大战项目
	/*
	 * 基本完成指标：地方tank以及我方tank能够移动及发射子弹，并且子弹有效；
	 * 1.设计画图
	 * 2.渲染地图
	 * 3.完成tank的渲染
	 * 	3.1 我方tank的渲染
	 * 	3.2 敌方tank的渲染
	 * 	3.3 敌方tank随机移动
	 * 4.子弹的渲染
	 * 	我方子弹、敌方子弹的渲染
	 * 5.子弹效果的渲染
	 * 	判断子弹跟tank位置的关系
	 * 6.扩展：
	 * 	6.1  音乐的追加
	 * 	6.2 buff的追加
	 * 	6.3  自定义大招 (未遂)
	 * 	6.4 杀敌数音效
	 * 	6.5 下一关、上一关
	 * 	6.6 模式：tank大乱斗(个人模型)
	 * 
	 * 项目上的架构：
	 * 	1.至少分为三个部分
	 * 		1.1 UI
	 * 		1.2 逻辑处理（比如：子弹如何判定有效，地方tank如何随机） service
	 * 		1.3 数据 （用户类 、 地方tank类 、 地图）model 
	 * 	提示：
	 * 		1.我方tank 和 地方tank 属性一样，行为也有很多相似的，可以抽象一个tank父类；
	 * 			1.1 tank父类：位置坐标，方向，速度（每次走的步数），行为：移动、发射子弹；
	 * 			1.2  如何同时控制敌方tank：每个tank实例都会开启一个线程；
	 * 			1.3 如何存敌方tank以及子弹：集合；Vector (线程安全版本的ArrayList)
	 * 			1.4 子弹类：至少包含 位置  以及  类型(敌方tank子弹还是我方tank的子弹)
	 * 				1.4.1 当子弹所在位置有tank时，判断tank是否是同一边的，如果不是，则减少生命值或直接干掉
	 * 
	 * 
	 * */
	
	
	public TankDemo() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

