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

	@Override							//������ͼ
	public void paint(Graphics g) {		//�Զ������ã�g�൱��һֱ����
		Color c = g.getColor();			//����ԭ���Ļ�����ɫ
		
		g.setColor(Color.BLUE);
		
		super.paint(g);					//��������д��룬��ʾ��ԭ�еĻ������ػ棬��Ȼ�����
		g.drawLine(100, 100, 300, 300);
		g.drawRect(100, 100, 300, 300);
		g.drawOval(100, 100, 300, 300);
		g.drawString("����˭��",200,200);
		g.setColor(c);					//�ָ�ԭ���Ļ�����ɫ
			
	}
	
	/**
	 * ��ʼ������
	 */
	public void launchFrame() {
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
	}
	
	public static void main(String[] args) {
		MyGameFrame f = new MyGameFrame();	//�������ڶ���
		f.launchFrame();					//���ô�������
		
	}
	
}
