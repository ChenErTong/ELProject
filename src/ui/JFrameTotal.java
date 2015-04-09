package ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
/**
 * 抽象类：所有界面的模板，包括界面标题栏标题，大小，位置等信息
 * @author 恩哥哥
 * 2015.4.8.
 * 改动：1、界面大小改为屏幕大小的0.618倍；2、去掉了高度、宽度的静态变量；3、将静态变量全部大写；by CX  2015.4.8
 */

public abstract class JFrameTotal extends JFrame{
	//TODO 标题栏名称
	protected static final String TITLE = "ELPrject";
	/*//界面宽度
	private static final int frameWidth = 1162;
	//界面高度
	private static final int frameHeight = 654;*/
	//减去屏幕任务栏误差
	private static final int WINDOW_UP = 16;
	
	public JFrameTotal(){
		//设置标题栏名称
		this.setTitle(TITLE);
		
		//设置不可改变大小以及关闭后停止运行
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();
		//设置界面大小
		this.setSize((int)(screen.width*0.618), (int)(screen.height*0.618));     //0.618是黄金分割比
		//将界面置于显示屏正中央
		int x = screen.width-this.getWidth()>>1;
		int y = (screen.height-this.getHeight()>>1)-WINDOW_UP;
		this.setLocation(x, y);
		
		
		//设置可以显示
		this.setVisible(true);
	}
}
