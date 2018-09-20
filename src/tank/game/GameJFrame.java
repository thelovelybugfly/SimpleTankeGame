package tank.game;

import java.awt.Color;

import javax.swing.JFrame; 

public class GameJFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GameJFrame(String title) {
		// ���´��ڽ�������һЩ��ʽ
		this.setTitle(title);
		// ���ÿ��
		this.setSize(806, 828);
		
		//���ñ�����ɫ
		this.setBackground(Color.black);
		// ���ô��ھ���
		this.setLocation(400, 10);
		// ���⣺�ر��˴��ڣ�����û��һ�����
		// ��ɶԴ��ڵ���Ⱦ����ͼ��̹�ˣ�-->JPanel
		new Music();
		// �����ڸ���Ⱦ���(GameJPanel)����
		this.setContentPane(new GameJPanel());
		//��ֹ�������ű仯
		this.setResizable(false);
		//�Ѵ�����ʾ������û�п��
		this.setVisible(true);
		// ȷ���رմ���ͬʱ����Ҳ������
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GameJFrame("̹�˴�ս���ɰ�");
	}

}
