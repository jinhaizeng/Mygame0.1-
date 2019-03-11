package game;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 炮弹类
 * @author Mr zeng
 *
 */
public class Shell extends GameObject{
	
	double degree;		//degree表示弧度，表示范围：0~2π
	
	public Shell() {
		 x = 200;
		 y = 200;
		 width = 10;
		 height = 10;
		 speed = 3;
		 
		 degree =Math.random()*Math.PI*2;	//Math.random()生成0~1之间的数
	}
	
	/**
	 * 绘制炮弹的函数
	 * @param g
	 */
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.YELLOW);
		g.fillOval((int)x, (int)y, width, height);		//填充一个圆形炮弹
		
		//沿着任意角度去飞
		x += speed*Math.cos(degree);
		y += speed*Math.sin(degree);
		
		//炮弹碰到边界反弹（degree取反）
		if(x<0 || x>Constant.GAME_WIDTH-width) {		//减去球的宽度
			degree = Math.PI - degree;
		}
		if(y<30 || y>Constant.GAME_HEIGHT-height) {		//减去球的高度,并且要考虑到标题的宽度
			degree = -degree;
		}
		
		g.setColor(c);
	}
}
