package ui_game;

import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;
import control.PlayerControl;
import ui.FrameTotal;
/**
 * 通关界面的窗口
 * @author 恩哥哥
 * 20.5.4.24.
 */
public class FrameWin extends JFrame{
	PanelWin panelWin;
	
	public FrameWin(PlayerControl playerControl,int grade){
		panelWin = new PanelWin(playerControl, grade);

		this.setResizable(false);
		this.setUndecorated(true);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//鼠标指针
		this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage("image/cursor/cur.png"), new Point(0, 0), "Slef"));
		//定义通关窗口的大小与位置
		int w = PanelWin.WIDTH;
		int h = PanelWin.HEIGHT;
		int x = (FrameTotal.WINDOWW-w)/2+FrameTotal.WINDOWX;
		int y = (FrameTotal.WINDOWH-h)/2+FrameTotal.WINDOWY;
		this.setSize(w, h);
		this.setLocation(x, y);
		
		this.setContentPane(panelWin);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	

}