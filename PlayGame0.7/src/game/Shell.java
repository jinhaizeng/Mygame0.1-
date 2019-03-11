package game;

import java.awt.Color;
import java.awt.Graphics;

/**
 * �ڵ���
 * @author Mr zeng
 *
 */
public class Shell extends GameObject{
	
	double degree;		//degree��ʾ���ȣ���ʾ��Χ��0~2��
	
	public Shell() {
		 x = 200;
		 y = 200;
		 width = 10;
		 height = 10;
		 speed = 3;
		 
		 degree =Math.random()*Math.PI*2;	//Math.random()����0~1֮�����
	}
	
	/**
	 * �����ڵ��ĺ���
	 * @param g
	 */
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.YELLOW);
		g.fillOval((int)x, (int)y, width, height);		//���һ��Բ���ڵ�
		
		//��������Ƕ�ȥ��
		x += speed*Math.cos(degree);
		y += speed*Math.sin(degree);
		
		//�ڵ������߽練����degreeȡ����
		if(x<0 || x>Constant.GAME_WIDTH-width) {		//��ȥ��Ŀ��
			degree = Math.PI - degree;
		}
		if(y<30 || y>Constant.GAME_HEIGHT-height) {		//��ȥ��ĸ߶�,����Ҫ���ǵ�����Ŀ��
			degree = -degree;
		}
		
		g.setColor(c);
	}
}
