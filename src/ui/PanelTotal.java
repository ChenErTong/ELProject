/**
 * 界面面板的抽象类，继承后则自动添加音乐音效按钮，播放背景音乐，设置背景图片，设置面板大小与窗口大小相同。
 * 需要传入背景图片、背景音乐、背景音乐同步数据和音效同步数据。（背景图片背景音乐由各个界面定义并传入，音乐音效同步数据在启动游戏时初始化一次即可）
 */
package ui;

import gamecomponent.Planet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import audio.BackgroundMusic;
import control.PlayerControl;

/**
 * @author DorA
 *
 * 2015-4-28 15:24:40
 */
public abstract class PanelTotal extends JDesktopPane{
	//Panel的长宽
	protected int width=FrameTotal.WINDOWW;
	protected int height=FrameTotal.WINDOWH;
	//背景音乐开关按钮
	protected ButtonBackgroundMusic jbtSilence;
	//音效开关按钮
	protected ButtonSound jbtSound;
	//最小化按钮图片
	protected ImageIcon mininum=Planet.getImageIcon("image/button/最小化按钮.png",55,55);
	//背景图片
	public ImageIcon backgroundImg;
	//玩家控制器
	protected PlayerControl playerControl;
	//背景音乐
	protected static BackgroundMusic bgm;
	//主窗口
	protected FrameTotal frameTotal;
	
	public PanelTotal(BackgroundMusic bgmusic, BgmSyncData bgmSyncData,SoundSyncData soundSyncData, FrameTotal frame){	
		frameTotal = frame;
		bgm = bgmusic;
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
		
		//最小化按钮
		JButton jbtMin=new JButton(mininum);
		jbtMin.addActionListener(new ActionListener(){
	        @Override public void actionPerformed(ActionEvent e){
	            frameTotal.setExtendedState(JFrame.ICONIFIED);
	        }
	    });
		jbtMin.setContentAreaFilled(false);
		jbtMin.setBorderPainted(false);
		jbtMin.setBounds((int)(width*0.85),(int)(height*0.02),(int)(width*0.054),(int)(height*0.092));
		this.add(jbtMin);
	 }
}