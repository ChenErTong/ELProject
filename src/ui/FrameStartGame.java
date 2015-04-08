/**
 * 开始界面的frame，好像和恩哥哥的有出入，先传了再说吧。
 * 已添加背景图片和按钮多合一面板。
 */
package ui;

import java.awt.Container;
import javax.swing.*;
/**
 * @author DorA
 *
 * 2015年4月8日下午11:51:29
 */
public class FrameStartGame extends JFrame{
//开始界面背景图片
	private static ImageIcon bg=new ImageIcon("image/bg/bg1.png");
//实例化一个按钮多合一面板
	private static PanelStartGame panelStartGame=new PanelStartGame();

	public FrameStartGame(){	
	}
	
	public static void main(String[]args){
		FrameStartGame frame=new FrameStartGame();
		
	//加入背景图片面板	
		JLabel background = new JLabel(bg);
		background.setBounds(0,0,1000, 650);		
		frame.getLayeredPane().add(background);
	//加入按钮面板	
		Container cp=frame.getContentPane();
		cp.add(panelStartGame);
		((JPanel)cp).setOpaque(false);
		
		frame.setSize(1000,650);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
