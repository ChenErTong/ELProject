/**
 * 开始界面面板
 */
package ui_start;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import gamecomponent.Planet;
import gamedata.TotalData;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Timer;

import control.PlayerControl;
import audio.BackgroundMusic;
import audio.SoundEffect;
import ui.*;

/**
 * @author DorA
 *
 * 2015年4月17日00:20:14
 */
public class PanelStartGame extends PanelTotal{
	//按钮的图标
	private ImageIcon imgNewGame1 = Planet.getImageIcon("image/button/按钮部分1.png", (int)(FrameTotal.WINDOWW*0.211),(int)(FrameTotal.WINDOWH*0.06));
	private ImageIcon imgHelp1 = Planet.getImageIcon("image/button/按钮部分3.png", (int)(FrameTotal.WINDOWW*0.105),(int)(FrameTotal.WINDOWH*0.06));
	private ImageIcon imgConfig1 = Planet.getImageIcon("image/button/按钮部分2.png", (int)(FrameTotal.WINDOWW*0.170),(int)(FrameTotal.WINDOWH*0.07));
	private ImageIcon imgQuit1 = Planet.getImageIcon("image/button/按钮部分4.png", (int)(FrameTotal.WINDOWW*0.105),(int)(FrameTotal.WINDOWH*0.06));
//	//变化的图标
//	private ImageIcon imgNewGame2 = new ImageIcon("image/button/按钮部分1-1.gif");
//	private ImageIcon imgHelp2 = new ImageIcon("image/button/按钮部分3-1.gif");
//	private ImageIcon imgQuit2 = new ImageIcon("image/button/按钮部分4-1.gif");

	//帮助界面
	private FrameHelp frameHelp;
	//配置界面
	private FrameConfig frameConfig;
	//开始按钮
	private JButton jbtStart;
	//帮助按钮
	private JButton jbtHelp;
	//配置按钮
	private JButton jbtConfig;
	//退出按钮
	private JButton jbtQuit;

	public PanelStartGame(BackgroundMusic bgm, BgmSyncData bgmData,SoundSyncData soundData, FrameTotal frameTotal){
		super(bgm, bgmData, soundData, frameTotal);

	/*	Timer t=new Timer(40,new TimerListener());
		t.start();*/
		//添加一个开始游戏按钮
		this.jbtStart=new JButton(imgNewGame1);
		this.jbtStart.setBounds((int)(FrameTotal.WINDOWW*0.396),(int)(FrameTotal.WINDOWH*0.583),(int)(FrameTotal.WINDOWW*0.231),(int)(FrameTotal.WINDOWH*0.06));
		this.jbtStart.setContentAreaFilled(false);
		this.jbtStart.setBorderPainted(false);
		this.jbtStart.setPressedIcon(imgNewGame1);
		this.jbtStart.setRolloverIcon(imgNewGame1);
		//按下后进入选关界面	
		this.jbtStart.setActionCommand("ToSelectMission");
		this.add(jbtStart);
		
		//添加一个帮助&演示按钮
		this.jbtHelp=new JButton(imgHelp1);
		this.jbtHelp.setBounds((int)(FrameTotal.WINDOWW*0.452),(int)(FrameTotal.WINDOWH*0.673),(int)(FrameTotal.WINDOWW*0.115),(int)(FrameTotal.WINDOWH*0.06));
		this.jbtHelp.setContentAreaFilled(false);
		this.jbtHelp.setBorderPainted(false);
		this.jbtHelp.setPressedIcon(imgHelp1);
		this.jbtHelp.setRolloverIcon(imgHelp1);
		this.jbtHelp.setActionCommand("OpenPanelHelp");
		this.add(jbtHelp);
		
		//添加一个配置按钮
		this.jbtConfig=new JButton(imgConfig1);
		this.jbtConfig.setBounds((int)(FrameTotal.WINDOWW*0.423),(int)(FrameTotal.WINDOWH*0.763),(int)(FrameTotal.WINDOWW*0.185),(int)(FrameTotal.WINDOWH*0.07));
		this.jbtConfig.setContentAreaFilled(false);
		this.jbtConfig.setBorderPainted(false);
		this.jbtConfig.setPressedIcon(imgConfig1);
		this.jbtConfig.setRolloverIcon(imgConfig1);
		this.jbtConfig.setActionCommand("OpenPanelConfig");
		this.add(jbtConfig);

		//添加一个退出游戏按钮
		this.jbtQuit=new JButton(imgQuit1);
		this.jbtQuit.setBounds((int)(FrameTotal.WINDOWW*0.452),(int)(FrameTotal.WINDOWH*0.853),(int)(FrameTotal.WINDOWW*0.115),(int)(FrameTotal.WINDOWH*0.06));
		this.jbtQuit.setContentAreaFilled(false);
		this.jbtQuit.setBorderPainted(false);
		this.jbtQuit.setPressedIcon(imgQuit1);
		this.jbtQuit.setRolloverIcon(imgQuit1);
		this.jbtQuit.setActionCommand("Quit");
		this.add(jbtQuit);
		
		//在分层面板加入背景图片面板	
		this.backgroundImg=Planet.getImageIcon("image/bg/开始界面.png", FrameTotal.WINDOWW,FrameTotal.WINDOWH);
		JLabel background = new JLabel(this.backgroundImg);
		background.setBounds(0,0,FrameTotal.WINDOWW,FrameTotal.WINDOWH);		
		this.add(background);			
	}
	private class TimerListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			repaint();
		}
	}
	
	/**
	 * 加入玩家控制器，对按钮进行监听
	 * @param playerControl
	 */
	public void addControl(PlayerControl playerControl) {
		this.playerControl = playerControl;
		/**
		 * 给所有按钮加入监听
		 */
		jbtStart.addActionListener(playerControl);
		jbtHelp.addActionListener(playerControl);
		jbtConfig.addActionListener(playerControl);
		jbtQuit.addActionListener(playerControl);		
		jbtStart.addMouseMotionListener(new MouseMotion());
		jbtHelp.addMouseMotionListener(new MouseMotion());
		jbtConfig.addMouseMotionListener(new MouseMotion());
		jbtQuit.addMouseMotionListener(new MouseMotion());
	}
	
	//鼠标移过按钮时发出音效
	class MouseMotion extends MouseMotionAdapter{
		public void mouseMoved(MouseEvent e) {
			SoundEffect.SELECT.play();
		}
	}
	
	/**
	 * 打开帮助界面
	 */
	public void openFrameHelp() {
		this.frameTotal.setEnabled(false);
		this.frameHelp = new FrameHelp(this.playerControl);
	}
	
	/**
	 * 关闭帮助界面
	 */
	public void closeFrameHelp(){
		this.frameTotal.setEnabled(true);	
		this.frameHelp.dispose();
	}

	public void openFrameConfig() {
		this.frameTotal.setEnabled(false);
		this.frameConfig = new FrameConfig(this.playerControl);
	}
	
	/**
	 * 关闭配置界面
	 */
	public void closeFrameConfig(){
		this.frameTotal.setEnabled(true);	
		this.frameConfig.dispose();
	}
}