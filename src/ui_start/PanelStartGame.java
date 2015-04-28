/**
 * 开始界面面板
 */
package ui_start;

import gamedata.GameData;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	private ImageIcon defaultIcon=new ImageIcon("image/button/img1.jpg");
	private ImageIcon rollIcon=new ImageIcon("image/button/img2.jpg");

	//开始按钮
	private JButton jbtStart;
	//帮助按钮
	private JButton jbtHelp;
	//退出按钮
	private JButton jbtQuit;
	
	public PanelStartGame(BgmSyncData bgmData,SoundSyncData soundData, GameData gameData){
		super(bgmData, soundData, gameData);

		//添加一个开始游戏按钮
		this.jbtStart=new JButton(defaultIcon);
		this.jbtStart.setBounds((int)(width*0.2),(int)(height*0.5),100,100);
		this.jbtStart.setToolTipText("select mission");
		this.jbtStart.setPressedIcon(rollIcon);
		this.jbtStart.setRolloverIcon(rollIcon);
		//按下后进入选关界面	
		this.jbtStart.setActionCommand("ToSelectMission");
		this.add(jbtStart);
		
		//添加一个帮助&演示按钮
		this.jbtHelp=new JButton(defaultIcon);
		this.jbtHelp.setBounds((int)(width*0.45),(int)(height*0.5),100,100);
		this.jbtHelp.setPressedIcon(rollIcon);
		this.jbtHelp.setRolloverIcon(rollIcon);
		this.add(jbtHelp);

		//添加一个退出游戏按钮
		this.jbtQuit=new JButton(defaultIcon);
		this.jbtQuit.setBounds((int)(width*0.7),(int)(height*0.5),100,100);
		this.jbtQuit.setToolTipText("Quit");
		this.jbtQuit.setPressedIcon(rollIcon);
		this.jbtQuit.setRolloverIcon(rollIcon);
		this.add(jbtQuit);

		//在分层面板加入背景图片面板	
		this.backgroundImg=new ImageIcon("image/bg/界面背景.png");
		JLabel background = new JLabel(this.backgroundImg);
		background.setBounds(0,0,width,height);		
		this.add(background);
			
	}	
	
	
	public void addControl(PlayerControl playerControl) {
		this.playerControl = playerControl;
		/**
		 * 给所有按钮加入监听
		 */
		jbtStart.addActionListener(playerControl);
		jbtHelp.addActionListener(playerControl);
		jbtQuit.addActionListener(playerControl);
	}
}
