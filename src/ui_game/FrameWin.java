package ui_game;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;

import control.PlayerControl;
import ui.FrameTotal;
/**
 * 通关界面的窗口
 * @author 恩哥哥
 * 20.5.4.24.
 */
public class FrameWin extends JFrame{
	/**
	 * 界面的长宽
	 */
	static int WIDTH = (int) (FrameTotal.WINDOWW*0.312);
	static int HEIGHT = (int) (FrameTotal.WINDOWH*0.533);
	PanelWin panelWin;
	
	public FrameWin(PlayerControl playerControl,int grade){
		panelWin = new PanelWin(playerControl, grade);

		this.setResizable(false);
		this.setUndecorated(true);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//鼠标指针
		this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage("image/cursor/Arrow.png"), new Point(0, 0), "Slef"));
		int x = (FrameTotal.WINDOWW-WIDTH)/2+FrameTotal.WINDOWX;
		int y = (FrameTotal.WINDOWH-HEIGHT)/2+FrameTotal.WINDOWY;
		this.setSize(WIDTH, HEIGHT);
		this.setLocation(x, y);
		
		this.setContentPane(panelWin);
		
		this.setLocationRelativeTo(null);
		
		//将窗体设为圆形
		this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                setShape(new Ellipse2D.Double(0,0,WIDTH,HEIGHT));
            }
        });
				
		this.setVisible(true);
	}
	

}