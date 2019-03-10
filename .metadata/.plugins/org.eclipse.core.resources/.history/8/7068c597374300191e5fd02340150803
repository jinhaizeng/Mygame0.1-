package game;

import java.awt.*;

import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 飞机游戏的主界面
 * @author MR Code
 *
 */
public class MyGameFrame extends JFrame{
	
	Image plane = GameUtil.getImage("image/plane.png");
	Image bg = GameUtil.getImage("image/bg.jpg");			//注意图片格式一定要对
	int planeX = 250 ,planeY = 250;
	//构造方法
	@Override							//帮助画图
	public void paint(Graphics g) {		//自动被调用，g相当于一直画笔
		g.drawImage(bg, 0, 0, null);
		g.drawImage(plane, planeX, planeY, null);
		planeX++;
			
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
	 * 初始化窗口
	 */
	public void launchFrame() {
		
		//初始化窗口大小以及位置
		this.setTitle("Mr Zun 作品");
		this.setVisible(true);			//窗口默认不可见，此时要让他可见
		this.setSize(500,500);			//设置窗口大小，高500，宽500
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
