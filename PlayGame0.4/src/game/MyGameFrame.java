package game;

import java.awt.*;

import javax.swing.JFrame;
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