package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class GameObject {
	Image img;
	double x,y;
	int speed;
	int width,height;
	
	//构造函数
	/**
	 * 创建重载构造器
	 * @param img
	 * @param x
	 * @param y
	 * @param speed
	 * @param width
	 * @param height
	 */
	public GameObject(Image img, double x, double y, int speed, int width, int height) {
		super();
		this.img = img;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.width = width;
		this.height = height;
	}

	public GameObject(Image img, double x, double y) {
		super();
		this.img = img;
		this.x = x;
		this.y = y;
	}

	public GameObject() {
		
	}
	
	
	/**
	 * 根据上面的成员属性画图
	 * @param g
	 */
	public void drawSelf(Graphics g) {
		g.drawImage(img, (int)x, (int)y, null);
	}

	/**
	 * 返回物体所在的矩形，便于以后做碰撞检测
	 * @return
	 */
	public Rectangle getRect() {
		return new Rectangle((int)x,(int) y, width, height);
	}
	
}
