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
	private ImageIcon imgNewGame1 = new ImageIcon("image/button/按钮部分1.png");
	private ImageIcon imgHelp1 = new ImageIcon("image/button/按钮部分3.png");
	private ImageIcon imgQuit1 = new ImageIcon("image/button/按钮部分4.png");
	//变化的图标
	private ImageIcon imgNewGame2 = new ImageIcon("image/button/按钮部分1-1.gif");
	private ImageIcon imgHelp2 = new ImageIcon("image/button/按钮部分3-1.gif");
	private ImageIcon imgQuit2 = new ImageIcon("image/button/按钮部分4-1.gif");

	//帮助界面
	private FrameHelp frameHelp;
	
	//开始按钮
	private JButton jbtStart;
	//帮助按钮
	private JButton jbtHelp;
	//退出按钮
	private JButton jbtQuit;

	public PanelStartGame(BackgroundMusic bgm, BgmSyncData bgmData,SoundSyncData soundData, FrameTotal frameTotal){
		super(bgm, bgmData, soundData, frameTotal);

	/*	Timer t=new Timer(40,new TimerListener());
		t.start();*/
		//添加一个开始游戏按钮
		this.jbtStart=new JButton(imgNewGame1);
		this.jbtStart.setBounds((int)(FrameTotal.WINDOWW*0.396),(int)(FrameTotal.WINDOWH*0.583),(int)(FrameTotal.WINDOWW*0.231),(int)(FrameTotal.WINDOWH*0.083));
		this.jbtStart.setContentAreaFilled(false);
		this.jbtStart.setBorderPainted(false);
		this.jbtStart.setPressedIcon(imgNewGame2);
		this.jbtStart.setRolloverIcon(imgNewGame2);
		//按下后进入选关界面	
		this.jbtStart.setActionCommand("ToSelectMission");
		this.add(jbtStart);
		
		//添加一个帮助&演示按钮
		this.jbtHelp=new JButton(imgHelp1);
		this.jbtHelp.setBounds((int)(FrameTotal.WINDOWW*0.452),(int)(FrameTotal.WINDOWH*0.708),(int)(FrameTotal.WINDOWW*0.115),(int)(FrameTotal.WINDOWH*0.083));
		this.jbtHelp.setContentAreaFilled(false);
		this.jbtHelp.setBorderPainted(false);
		this.jbtHelp.setPressedIcon(imgHelp2);
		this.jbtHelp.setRolloverIcon(imgHelp2);
		this.jbtHelp.setActionCommand("OpenPanelHelp");
		this.add(jbtHelp);

		//添加一个退出游戏按钮
		this.jbtQuit=new JButton(imgQuit1);
		this.jbtQuit.setBounds((int)(FrameTotal.WINDOWW*0.452),(int)(FrameTotal.WINDOWH*0.833),(int)(FrameTotal.WINDOWW*0.115),(int)(FrameTotal.WINDOWH*0.083));
		this.jbtQuit.setContentAreaFilled(false);
		this.jbtQuit.setBorderPainted(false);
		this.jbtQuit.setPressedIcon(imgQuit2);
		this.jbtQuit.setRolloverIcon(imgQuit2);
		this.jbtQuit.setActionCommand("Quit");
		this.add(jbtQuit);
		
		//在分层面板加入背景图片面板	
		this.backgroundImg=Planet.getImageIcon("image/bg/界面背景.png", FrameTotal.WINDOWW,FrameTotal.WINDOWH);
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
		jbtQuit.addActionListener(playerControl);		
		jbtStart.addMouseMotionListener(new MouseMotion());
		jbtHelp.addMouseMotionListener(new MouseMotion());
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
//		this.frameTotal.setEnabled(false);
		this.frameHelp = new FrameHelp(this.playerControl);
		this.frameHelp.setAlwaysOnTop(true);
		WindowDragger.CANDRAGGER = false;
	}
	
	/**
	 * 关闭帮帮助界面
	 */
	public void closeFrameHelp(){
//		this.frameTotal.setEnabled(true);
		WindowDragger.CANDRAGGER = true;
		this.frameHelp.dispose();
	}
}