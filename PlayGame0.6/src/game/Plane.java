package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class Plane extends GameObject{
	//飞机独有的属性
	boolean left,right,up,down;
	
	//构造方法
	public Plane(Image img, double x,double y) {
		this.img = img;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void drawSelf(Graphics g) {
		g.drawImage(img, (int)x, (int)y, null);
		x++;
	}
	
	//按下某个键，增加相应的方
	public void addDirection(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		case KeyEvent.VK_UP:
			up = true;
			break;
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			break;	
		}
	}
}
