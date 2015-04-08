package ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
/**
 * 抽象类：所有界面的模板，包括界面标题栏标题，大小，位置等信息
 * @author 恩哥哥
 * 2015.4.8.
 */

public abstract class JFrameTotal extends JFrame{
	//TODO 标题栏名称
	protected static final String title = "ELPrject";
	//界面宽度
	private static final int frameWidth = 1162;
	//界面高度
	private static final int frameHeight = 654;
	//减去屏幕任务栏误差
	private static final int windowUp = 16;
	
	public JFrameTotal(){
		//设置标题栏名称
		this.setTitle(title);
		//设置界面大小
		this.setSize(frameWidth, frameHeight);
		//设置不可改变大小以及关闭后停止运行
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//将界面置于显示屏正中央
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();
		int x = screen.width-this.getWidth()>>1;
		int y = (screen.height-this.getHeight()>>1)-windowUp;
		this.setLocation(x, y);
		
		//设置可以显示
		this.setVisible(true);
	}
}
