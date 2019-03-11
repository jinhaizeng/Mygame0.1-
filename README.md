[速学堂](https://www.sxt.cn/Java_jQuery_in_action/thirteen-draw.html)
Mygame0.1飞机小游戏

## 1.PlayGame0.1知识点
* Swing是一个为Java设计的GUI工具包。Swing是Java基础类的一部分。Swing包括了图形用户界面（GUI）组件如：文本框，文本域，按钮，分隔窗格和表。
 Swing提供许多比AWT更好的屏幕显示元素。它们用纯Java写成，所以同Java本身一样可以跨平台运行，这一点不像AWT。它们是JFC的一部分。它们支持可更换的面板和主题（各种操作系统默认的特有主题），然而不是真的使用原生平台提供的设备，而是仅仅在表面上模仿它们。这意味着你可以在任意平台上使用Java支持的任意面板。轻量级组件的缺点则是执行速度较慢，优点就是可以在所有平台上采用统一的行为。
* 继承`JFrame`这个，其实就是Swing
* 关闭窗口，但是程序没有结束的解决方法
    ```java
    this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);				//保证关闭窗口后，程序正常结束，原因未知，待查
			}
		});
    ```

##  2.PlayGame0.2知识点
**这个项目主要是用来学习图形图像**
* paint函数重写
* [paint函数调用黑屏解决办法](https://zhidao.baidu.com/question/1964290833153505660.html)
* 改版画笔属性时（如颜色、字体），要先保存原来的画笔信息，函数调用完成后再恢复信息
    ```java
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
		g.setColor(c);					//恢复原来的画笔颜色
		g.setFont(f);
			
	}
	```
* 加载图片的代码直接抄的，后面肯定还是要理解的。要花时间去啃
```java
package game;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class GameUtil {
	// 工具类最好将构造器私有化。
	private GameUtil() {

	}
	/**
	 * 返回指定路径文件的图片对象
	 * @param path
	 * @return
	 */
	public static Image getImage(String path) {
		BufferedImage bi = null;
		try {
			URL u = GameUtil.class.getClassLoader().getResource(path);
			bi = ImageIO.read(u);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bi;
	}
}
```
## 3.PlayGmae0.3知识点
* paint()是系统调用的，用户不可以调用，而repaint()可以调	
* 在类MyGameFrame里面直接定义另一个类PaintThread，这样使用时就不需要实例化，可以直接调用类及其方法

## 4.PlayGame0.4知识点
* 利用父类，将一些需要移动的内容（如：飞机和炮弹）定义到父类里面
* 此版本，利用飞机（子类）继承父类
* 任务物体可以看做矩形，所以可以用返回矩形的方式便于以后做碰撞检测
```java
public Rectangle getRect() {
		return new Rectangle((int)x,(int) y, width, height);
	}
```

## 5.PlayeGame0.4知识点——键盘控制原理
内部逻辑还没理清，存在一个问题，键盘只需要定义就可以直接监听了，而不需要调用方法，为什么
* 为了识别按下的按键是哪个，系统对键盘所有的按键做了编号，每个按键都对应相应的数字。

## 6.PlayGame0.7知识点——炮弹的边界返回问题
* 调用`repaint()`是间接调用的`paint()`
* 产量可以定义在一个类`Constant`里面，这样修改某些参数时，直接修改这个类里面的常量就可以了
* 做碰撞反弹的时候要注意，边界判断要减去小球的高度和宽度，否则小球会有一段时间藏在边界里面
```java
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
```

### 6.PlayGame0.8知识点——容器和数组产生多发炮弹
基本的操作语句
```java
Shell[] shells = new Shell[50];		//声明50个炮弹

//初始化50个炮弹,Shell类型的数组，其初始化也是用一个Shell的对象进行赋值
		for(int i=0 ; i<shells.length ;i++) {
			shells[i] = new Shell();
		}
```
双缓冲的代码直接给的，没有细究。双缓冲用于解决图片加载闪烁的问题
```java
private Image offScreenImage = null;
 
public void update(Graphics g) {
    if(offScreenImage == null)
        offScreenImage = this.createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);//这是游戏窗口的宽度和高度
     
    Graphics gOff = offScreenImage.getGraphics();
    paint(gOff);
    g.drawImage(offScreenImage, 0, 0, null);
}  
```

利用java API中`Rectangle`类的方法来处理矩形的碰撞检测的问题，采用`intersect()`的方法

爆炸数组轮播处理
```java
package game;

import java.awt.Graphics;
import java.awt.Image;

public class Explode {
	double x,y;		//爆炸的位置
	
	static Image[] imgs = new Image[16];
	static {
		for(int i = 0; i < 16; i++) {
			imgs[i] = GameUtil.getImage("image/explode/e"+(i+1)+".gif");
			imgs[i].getWidth(null);
		}
	}
	
	int count;
	
	public void draw(Graphics g) {
		if(count <= 15) {
			g.drawImage(imgs[count], (int)x, (int)y, null);
			count++;
		}
	}
}
```
注意数组设置的为`static`，保证程序加载时一次完成，否则每次碰撞都要加载，浪费内存，方法体也同理