package ui_game;

import javax.swing.JFrame;

import ui.JFrameTotal;
/**
 * 通关界面的窗口
 * @author 恩哥哥
 * 20.5.4.24.
 */
public class JFrameWin extends JFrameTotal{
	JPanelWin panelWin;
	
	public JFrameWin(){
		this.setTitle("通关界面");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		//定义通关窗口的大小与位置
		int w = (int)(WINDOWW*0.4);
		int h = (int)(WINDOWH*0.4);
		int x = (WINDOWW-w)/2+WINDOWX;
		int y = (WINDOWH-h)/2+WINDOWY;
		this.setSize(w, h);
		this.setLocation(x, y);
		
		this.setContentPane(panelWin);
	}
}
