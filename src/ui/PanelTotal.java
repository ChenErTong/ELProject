/**
 * 界面面板的抽象类，继承后则自动添加音乐音效按钮，播放背景音乐，设置背景图片，设置面板大小与窗口大小相同。
 * 需要传入背景图片、背景音乐、背景音乐同步数据和音效同步数据。（背景图片背景音乐由各个界面定义并传入，音乐音效同步数据在启动游戏时初始化一次即可）
 */
package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import audio.BackgroundMusic;

/**
 * @author DorA
 *
 * 2015-4-28 15:24:40
 */
public abstract class PanelTotal extends JPanel{
	private int w=FrameTotal.WINDOWW;
	private int h=FrameTotal.WINDOWH;
	//背景图片
	private Image bg;
	
	public PanelTotal(){		
	}

	
	 public PanelTotal(ImageIcon ic,BackgroundMusic bgm,BgmSyncData bgmSyncData,SoundSyncData soundSyncData){
		 
		 	//设置大小
			this.setBounds(0, 0, w, h);
			
			//获取背景图片
			this.bg=ic.getImage();
			
			//自由布局
			setLayout(null);
		 
			//背景音乐开关按钮
			ButtonBackgroundMusic jbtSilence=new ButtonBackgroundMusic(bgmSyncData);
			jbtSilence.setMusic(bgm);
			add(jbtSilence);
			
			//音效开关按钮
			ButtonSound jbtSound=new ButtonSound(soundSyncData);
			add(jbtSound);
			
			
	 }
	 
	 //绘制背景图片
	 public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawImage(bg, 0, 0, w,h,this);

	}
}
