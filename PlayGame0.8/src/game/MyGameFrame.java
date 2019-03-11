package game;

import java.awt.*;
import javax.swing.JFrame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

/**
 * 飞机游戏的主界面
 * @author MR Code
 *
 */
public class MyGameFrame extends Frame{
	
	Image planeimg = GameUtil.getImage("image/plane.png");
	Image bgimg = GameUtil.getImage("image/bg.jpg");			//注意图片格式一定要对
	
	Plane plane = new Plane(planeimg,250,250);
	
	Shell[] shells = new Shell[50];			//炮弹对象
	
	Explode bao;					//先声明爆炸对象，在发生爆炸的时候再实例化
	
	Date startTime = new Date();
	Date endTime;
	int period;
	//构造方法
	@Override							//帮助画图
	public void paint(Graphics g) {		//自动被调用，g相当于一直画笔
		Color c = g.getColor();
		Font m = g.getFont();
		
		g.drawImage(bgimg, 0, 0, null);
		
		plane.drawSelf(g);				//画飞机
		
		//画50个炮弹
		for(int i=0 ; i<shells.length ;i++) {
			shells[i].draw(g);
			
			//飞机与炮弹的碰撞检测
			boolean peng = shells[i].getRect().intersects(plane.getRect());
			
			if(peng) {
				plane.live = false;
				
				if(bao == null) {		//保证发生碰撞时，没有爆炸对象再创建爆炸对象，否则会一直创建爆炸对象
					bao = new Explode(plane.x, plane.y);
					endTime = new Date();
					period = (int)((endTime.getTime()-startTime.getTime())/1000);
				}
				bao.draw(g);
			}
			
			//计时功能，画出存活时间
			if(!plane.live) {
				g.setColor(Color.red);
				Font f = new Font("宋体",Font.BOLD,50);
				g.setFont(f);
				g.drawString("存活："+period+"秒", (int)plane.x, (int)plane.y);
			}
		}
		g.setColor(c);
		g.setFont(m);
	}
	
	
	/**
	 * 定义绘图线程
	 * @author Mr zeng
	 *
	 */
	class PaintThread extends Thread{		//在类里面直接定义类，保证这个类可以直接被使用，而不需要实例化
		@Override
		public void run() {
			while(true) {
				
				//System.out.println("窗口画一次");
				repaint();			//paint()是系统调用的，用户不可以调用，而repaint()可以调
				
				try {
					Thread.sleep(40);			//保证人眼看动画是连续的，而且绘图程序不会占用大量内存
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 键盘监听的内部类
	 * @author Mr zeng
	 *
	 */
	class KeyMonitor extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {		//按下键的信息都在KeyEvent这个对象里了
			plane.addDirection(e);					//按下键，传入按键信息，调用方向函数
		}

		@Override
		public void keyReleased(KeyEvent e) {
			plane.minusDirection(e);				//松开键键，传入按键信息，调用方向函数
		}
		
	}
	
	
	/**
	 * 初始化窗口
	 */
	public void launchFrame() {
		
		//初始化窗口大小以及位置
		this.setTitle("Mr Zun 作品");
		this.setVisible(true);			//窗口默认不可见，此时要让他可见
		this.setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);			//设置窗口大小，高500，宽500
		this.setLocation(300,300); 			//设置窗口位置，即窗口左上角的坐标
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);				//保证关闭窗口后，程序正常结束，原因未知，待查
			}
		});
		
		//启动绘图线程
		new PaintThread().start();	
		addKeyListener(new KeyMonitor());		//给窗口增加键盘的监听
		
		//初始化50个炮弹,Shell类型的数组，其初始化也是用一个Shell的对象进行赋值
		for(int i=0 ; i<shells.length ;i++) {
			shells[i] = new Shell();
		}
	}
	
	//解决加载图片的问题——双缓冲代码
	private Image offScreenImage = null;
	 
	public void update(Graphics g) {
	    if(offScreenImage == null)
	        offScreenImage = this.createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);//这是游戏窗口的宽度和高度
	     
	    Graphics gOff = offScreenImage.getGraphics();
	    paint(gOff);
	    g.drawImage(offScreenImage, 0, 0, null);
	}  
	
	
	
	/**
	 * 主函数
	 * @param args
	 */
	public static void main(String[] args) {
		MyGameFrame f = new MyGameFrame();	//创建窗口对象
		f.launchFrame();					//调用创建窗口
		
	}
	
}
