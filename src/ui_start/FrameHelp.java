package ui_start;

import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;
import control.PlayerControl;
import ui.FrameTotal;

public class FrameHelp extends JFrame{
	private PanelHelp panelHelp;
	
	public FrameHelp(PlayerControl playerControl){
		this.panelHelp = new PanelHelp(playerControl);
		
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		//定义通关窗口的大小与位置
		int w = PanelHelp.WIDTH;
		int h = PanelHelp.HEIGHT;
		int x = (FrameTotal.WINDOWW-w)/2+FrameTotal.WINDOWX;
		int y = (FrameTotal.WINDOWH-h)/2+FrameTotal.WINDOWY;
		this.setSize(w, h);
		this.setLocation(x, y);
		this.setUndecorated(true);
		this.setContentPane(panelHelp);
		//鼠标指针
		this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage("image/cursor/cur.png"), new Point(0, 0),"Slef"));
		//设置为不可移动
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}