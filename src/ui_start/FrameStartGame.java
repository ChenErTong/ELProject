/**
 * 开始界面的frame
 * 已添加背景图片和按钮多合一面板。
 */
package ui_start;

import java.awt.Container;
import javax.swing.*;

/**
 * @author DorA
 *
 * 2015年4月8日下午11:51:29
 */
public class FrameStartGame extends ui.JFrameTotal{
//开始界面背景图片
	private static ImageIcon bg=new ImageIcon("image/bg/bg1.png");
//实例化一个按钮多合一面板
	private static PanelStartGame panelStartGame=new PanelStartGame();

	public FrameStartGame(){	
		//加入背景图片面板	
		JLabel background = new JLabel(bg);
		background.setBounds(0,0,frameWidth,frameHeight);		
		this.getLayeredPane().add(background);
		//加入按钮面板	
		Container cp=this.getContentPane();
		cp.add(panelStartGame);
		((JPanel)cp).setOpaque(false);
		
	}
	
	public static void main(String[]args){
		FrameStartGame frame=new FrameStartGame();
		


	}

//	//开始界面背景图片
//		private ImageIcon bg=new ImageIcon("image/bg/bg1.png");
//	//实例化一个按钮多合一面板
//		private PanelStartGame panelStartGame=new PanelStartGame();
//
//		public FrameStartGame(){
//			//加入背景图片面板	
//				JLabel background = new JLabel(bg);
//				background.setBounds(0,0,frameWidth, frameHeight);		
//				this.getLayeredPane().add(background);
//			//加入按钮面板	
//				Container cp=this.getContentPane();
//				cp.add(panelStartGame);
//				((JPanel)cp).setOpaque(false);
//				
//				add(panelStartGame);
//			}
//		
//		public static void main(String[] args){
//			FrameStartGame f=new FrameStartGame();
//		}
}
