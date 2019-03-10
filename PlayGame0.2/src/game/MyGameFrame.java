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
	
	Image ball = GameUtil.getImage("image/ball.png");
	
	@Override							//帮助画图
	public void paint(Graphics g) {		//自动被调用，g相当于一直画笔
		Color c = g.getColor();			//保存原来的画笔颜色
		Font f = g.getFont();
		
		g.setColor(Color.BLUE);
		g.setFont(new  Font("宋体",Font.BOLD,50));		//设置字体
		
		super.paint(g);					//添加上这行代码，表示再原有的基础上重绘，不然会黑屏
		g.drawLine(100, 100, 300, 300);
		g.drawRect(100, 100, 300, 300);
		g.drawOval(100, 100, 300, 300);
		g.drawString("我是谁？",200,200);
		
		
		g.drawImage(ball, 250, 250, null);
		g.setColor(c);					//恢复原来的画笔颜色
		g.setFont(f);
			
	}
	
	/**
	 * 初始化窗口
	 */
	public void launchFrame() {
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
	}
	
	public static void main(String[] args) {
		MyGameFrame f = new MyGameFrame();	//创建窗口对象
		f.launchFrame();					//调用创建窗口
		
	}
	
}
