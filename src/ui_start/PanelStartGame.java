/**
 * 开始界面面板
 */
package ui_start;

import gamecomponent.Planet;
import gamedata.TotalData;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import control.PlayerControl;
import audio.BackgroundMusic;
import ui.*;

/**
 * @author DorA
 *
 * 2015年4月17日00:20:14
 */
public class PanelStartGame extends PanelTotal{
	//按钮的图标
	private ImageIcon imgNewGame1 = Planet.getImageIcon("image/button/按钮部分1.png", 200, 50);
	private ImageIcon imgHelp1 = Planet.getImageIcon("image/button/按钮部分3.png", 80, 50);
	private ImageIcon imgQuit1 = Planet.getImageIcon("image/button/按钮部分4.png", 80, 50);
	//变化的图标
	private ImageIcon imgNewGame2 = Planet.getImageIcon("image/button/按钮部分1.png", 250, 75);
	private ImageIcon imgHelp2 = Planet.getImageIcon("image/button/按钮部分3.png", 250, 75);
	private ImageIcon imgQuit2 = Planet.getImageIcon("image/button/按钮部分4.png", 250, 75);

	//帮助界面
	private FrameHelp frameHelp;
	
	//开始按钮
	private JButton jbtStart;
	//帮助按钮
	private JButton jbtHelp;
	//退出按钮
	private JButton jbtQuit;

	public PanelStartGame(BackgroundMusic bgm, BgmSyncData bgmData,SoundSyncData soundData, TotalData totalData, FrameTotal frameTotal){
		super(bgm, bgmData, soundData, totalData, frameTotal);

		//添加一个开始游戏按钮
		this.jbtStart=new JButton(imgNewGame1);
		this.jbtStart.setBounds(412,354,200,50);
		this.jbtStart.setContentAreaFilled(false);
		this.jbtStart.setBorderPainted(false);
		this.jbtStart.setPressedIcon(imgNewGame2);
		this.jbtStart.setRolloverIcon(imgNewGame2);
		//按下后进入选关界面	
		this.jbtStart.setActionCommand("ToSelectMission");
		this.add(jbtStart);
		
		//添加一个帮助&演示按钮
		this.jbtHelp=new JButton(imgHelp1);
		this.jbtHelp.setBounds(472,436,80,50);
		this.jbtHelp.setContentAreaFilled(false);
		this.jbtHelp.setBorderPainted(false);
		this.jbtHelp.setPressedIcon(imgHelp2);
		this.jbtHelp.setRolloverIcon(imgHelp2);
		this.jbtHelp.setActionCommand("OpenPanelHelp");
		this.add(jbtHelp);

		//添加一个退出游戏按钮
		this.jbtQuit=new JButton(imgQuit1);
		this.jbtQuit.setBounds(472,508,80,50);
		this.jbtQuit.setContentAreaFilled(false);
		this.jbtQuit.setBorderPainted(false);
		this.jbtQuit.setPressedIcon(imgQuit2);
		this.jbtQuit.setRolloverIcon(imgQuit2);
		this.jbtQuit.setActionCommand("Quit");
		this.add(jbtQuit);
		
		//在分层面板加入背景图片面板	
		this.backgroundImg=new ImageIcon("image/bg/界面背景.png");
		JLabel background = new JLabel(this.backgroundImg);
		background.setBounds(0,0,width,height);		
		System.out.println(width);
		System.out.println(height);
		this.add(background);
			
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
	}

	/**
	 * 打开帮助界面
	 */
	public void openFrameHelp() {
		this.frameTotal.setEnabled(false);
		this.frameHelp = new FrameHelp(this.playerControl);
	}
	
	/**
	 * 关闭帮帮助界面
	 */
	public void closeFrameHelp(){
		this.frameTotal.setEnabled(true);	
		this.frameHelp.dispose();
	}
}