package game;

import java.awt.*;

import javax.swing.JFrame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * �ɻ���Ϸ��������
 * @author MR Code
 *
 */
public class MyGameFrame extends JFrame{
	
	Image planeimg = GameUtil.getImage("image/plane.png");
	Image bgimg = GameUtil.getImage("image/bg.jpg");			//ע��ͼƬ��ʽһ��Ҫ��
	
	Plane plane = new Plane(planeimg,250,250);
	
	//���췽��
	@Override							//������ͼ
	public void paint(Graphics g) {		//�Զ������ã�g�൱��һֱ����
		g.drawImage(bgimg, 0, 0, null);
		
		plane.drawSelf(g);				//���ɻ�
			
	}
	
	
	/**
	 * �����ͼ�߳�
	 * @author Mr zeng
	 *
	 */
	class PaintThread extends Thread{		//��������ֱ�Ӷ����࣬��֤��������ֱ�ӱ�ʹ�ã�������Ҫʵ����
		@Override
		public void run() {
			while(true) {
				
				//System.out.println("���ڻ�һ��");
				repaint();			//paint()��ϵͳ���õģ��û������Ե��ã���repaint()���Ե�
				
				try {
					Thread.sleep(40);			//��֤���ۿ������������ģ����һ�ͼ���򲻻�ռ�ô����ڴ�
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * ���̼������ڲ���
	 * @author Mr zeng
	 *
	 */
	class KeyMonitor extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {		//���¼�����Ϣ����KeyEvent�����������
			System.out.println(e.getKeyCode());
		}

		@Override
		public void keyReleased(KeyEvent e) {
			System.out.println(e.getKeyCode());
		}
		
	}
	
	
	/**
	 * ��ʼ������
	 */
	public void launchFrame() {
		
		//��ʼ�����ڴ�С�Լ�λ��
		this.setTitle("Mr Zun ��Ʒ");
		this.setVisible(true);			//����Ĭ�ϲ��ɼ�����ʱҪ�����ɼ�
		this.setSize(500,500);			//���ô��ڴ�С����500����500
		this.setLocation(300,300); 			//���ô���λ�ã����������Ͻǵ�����
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);				//��֤�رմ��ں󣬳�������������ԭ��δ֪������
			}
		});
		
		//������ͼ�߳�
		new PaintThread().start();	
		addKeyListener(new KeyMonitor());		//���������Ӽ��̵ļ���
	}
	
	
	
	
	/**
	 * ������
	 * @param args
	 */
	public static void main(String[] args) {
		MyGameFrame f = new MyGameFrame();	//�������ڶ���
		f.launchFrame();					//���ô�������
		
	}
	
}