package game;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

/**
 * �ɻ���Ϸ��������
 * @author MR Code
 *
 */
public class MyGameFrame extends JFrame{
	
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
