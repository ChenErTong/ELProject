package ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import control.PlayerControl;
/**
 * 抽象类：所有界面的模板，包括界面标题栏标题，大小，位置等信息
 * @author 恩哥哥
 * 2015.4.8.
 * 改动：1、界面大小改为屏幕大小的0.618倍；2、去掉了高度、宽度的静态变量；3、将静态变量全部大写；by CX  2015.4.8
 * 改动：把大小暂时设为固定数值了 by CX 2015.4.15
 */

public abstract class FrameTotal extends JFrame{
	//TODO 标题栏名称
	protected static final String TITLE = "ELPrject";
	//目测的地址栏高度
	private static final int WINDOW_UP = 16;
	//Frame的位置坐标
	protected static int WINDOWX;
	protected static int WINDOWY;
	//窗口大小
	public static final int WINDOWW = 1024;
	public static final int WINDOWH = 700;
	
	public FrameTotal(){
		//设置标题栏名称
		this.setTitle(TITLE);
		
		//设置不可改变大小以及关闭后停止运行
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//仅仅是为了打印到屏幕中间
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();  
		//设置JFrame大小，暂时用窗口比例的80%表示
//		WINDOWW = (int)(screen.width*0.8);
//		WINDOWH = (int)(screen.height*0.8);
		this.setSize(WINDOWW, WINDOWH);   
		
		//将界面置于显示屏正中央
		WINDOWX = screen.width-this.getWidth()>>1;
		WINDOWY = (screen.height-this.getHeight()>>1)-WINDOW_UP;
		this.setLocation(WINDOWX, WINDOWY);
//		this.setUndecorated(true);
		//设置可以显示
		this.setVisible(false);
	}
//	/**
//	 * 给界面加入监听
//	 * @param playerControl	玩家控制器
//	 */
//	public void addControl(PlayerControl playerControl){
//		this.playerControl = playerControl;
//	}
}
