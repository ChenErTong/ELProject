/**
 * 界面面板的抽象类，继承后则自动添加音乐音效按钮，播放背景音乐，设置背景图片，设置面板大小与窗口大小相同。
 * 需要传入背景图片、背景音乐、背景音乐同步数据和音效同步数据。（背景图片背景音乐由各个界面定义并传入，音乐音效同步数据在启动游戏时初始化一次即可）
 */
package ui;

import gamedata.GameData;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import control.PlayerControl;
import audio.BackgroundMusic;

/**
 * @author DorA
 *
 * 2015-4-28 15:24:40
 */
public abstract class PanelTotal extends JPanel{
	//Panel的长宽
	protected int width=FrameTotal.WINDOWW;
	protected int height=FrameTotal.WINDOWH;
	//背景音乐开关按钮
	protected ButtonBackgroundMusic jbtSilence;
	//音效开关按钮
	protected ButtonSound jbtSound;
	//背景图片
	public ImageIcon backgroundImg;
	//玩家控制器
	protected PlayerControl playerControl;
	//加入游戏数据
	protected GameData gameData;
	//背景音乐
	protected static BackgroundMusic bgm=new BackgroundMusic("bgm01");
	
	public PanelTotal(BgmSyncData bgmSyncData,SoundSyncData soundSyncData, GameData gameData){	
		this.gameData = gameData;
	 	//设置大小
		this.setBounds(0, 0, width, height);
		
		//自由布局
		this.setLayout(null);
	 
		//背景音乐开关按钮
		this.jbtSilence=new ButtonBackgroundMusic(bgmSyncData);
		this.jbtSilence.setMusic(bgm);
		this.add(jbtSilence);
		
		//音效开关按钮
		this.jbtSound=new ButtonSound(soundSyncData);
		this.add(jbtSound);			
	 }
}
