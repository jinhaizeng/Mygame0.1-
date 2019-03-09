[速学堂](https://www.sxt.cn/Java_jQuery_in_action/thirteen-draw.html)
Mygame0.1飞机小游戏

## PlayGame0.1知识点
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

## # PlayGame0.2知识点
* paint函数重写
* [paint函数调用黑屏解决办法](https://zhidao.baidu.com/question/1964290833153505660.html)
* 改版画笔属性时（如颜色、字体），要先保存原来的画笔信息，函数调用完成后再恢复信息
    ```java
   @Override							//帮助画图
	public void paint(Graphics g) {		//自动被调用，g相当于一直画笔
		Color c = g.getColor();			//保存原来的画笔颜色
		
		g.setColor(Color.BLUE);
		
		g.setColor(c);					//恢复原来的画笔颜色
			
	}
```