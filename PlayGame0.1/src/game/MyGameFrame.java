package game;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

/**
 * 飞机游戏的主界面
 * @author MR Code
 *
 */
public class MyGameFrame extends JFrame{
	
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
