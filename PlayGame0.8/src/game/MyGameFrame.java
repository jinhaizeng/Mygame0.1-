package game;

import java.awt.*;
import javax.swing.JFrame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

/**
 * �ɻ���Ϸ��������
 * @author MR Code
 *
 */
public class MyGameFrame extends Frame{
	
	Image planeimg = GameUtil.getImage("image/plane.png");
	Image bgimg = GameUtil.getImage("image/bg.jpg");			//ע��ͼƬ��ʽһ��Ҫ��
	
	Plane plane = new Plane(planeimg,250,250);
	
	Shell[] shells = new Shell[50];			//�ڵ�����
	
	Explode bao;					//��������ը�����ڷ�����ը��ʱ����ʵ����
	
	Date startTime = new Date();
	Date endTime;
	int period;
	//���췽��
	@Override							//������ͼ
	public void paint(Graphics g) {		//�Զ������ã�g�൱��һֱ����
		Color c = g.getColor();
		Font m = g.getFont();
		
		g.drawImage(bgimg, 0, 0, null);
		
		plane.drawSelf(g);				//���ɻ�
		
		//��50���ڵ�
		for(int i=0 ; i<shells.length ;i++) {
			shells[i].draw(g);
			
			//�ɻ����ڵ�����ײ���
			boolean peng = shells[i].getRect().intersects(plane.getRect());
			
			if(peng) {
				plane.live = false;
				
				if(bao == null) {		//��֤������ײʱ��û�б�ը�����ٴ�����ը���󣬷����һֱ������ը����
					bao = new Explode(plane.x, plane.y);
					endTime = new Date();
					period = (int)((endTime.getTime()-startTime.getTime())/1000);
				}
				bao.draw(g);
			}
			
			//��ʱ���ܣ��������ʱ��
			if(!plane.live) {
				g.setColor(Color.red);
				Font f = new Font("����",Font.BOLD,50);
				g.setFont(f);
				g.drawString("��"+period+"��", (int)plane.x, (int)plane.y);
			}
		}
		g.setColor(c);
		g.setFont(m);
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
			plane.addDirection(e);					//���¼������밴����Ϣ�����÷�����
		}

		@Override
		public void keyReleased(KeyEvent e) {
			plane.minusDirection(e);				//�ɿ����������밴����Ϣ�����÷�����
		}
		
	}
	
	
	/**
	 * ��ʼ������
	 */
	public void launchFrame() {
		
		//��ʼ�����ڴ�С�Լ�λ��
		this.setTitle("Mr Zun ��Ʒ");
		this.setVisible(true);			//����Ĭ�ϲ��ɼ�����ʱҪ�����ɼ�
		this.setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);			//���ô��ڴ�С����500����500
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
		
		//��ʼ��50���ڵ�,Shell���͵����飬���ʼ��Ҳ����һ��Shell�Ķ�����и�ֵ
		for(int i=0 ; i<shells.length ;i++) {
			shells[i] = new Shell();
		}
	}
	
	//�������ͼƬ�����⡪��˫�������
	private Image offScreenImage = null;
	 
	public void update(Graphics g) {
	    if(offScreenImage == null)
	        offScreenImage = this.createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);//������Ϸ���ڵĿ��Ⱥ͸߶�
	     
	    Graphics gOff = offScreenImage.getGraphics();
	    paint(gOff);
	    g.drawImage(offScreenImage, 0, 0, null);
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