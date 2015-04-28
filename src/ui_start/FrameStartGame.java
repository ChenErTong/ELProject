/**
 * 开始界面
 */
package ui_start;

import javax.swing.ImageIcon;

import audio.BackgroundMusic;
import ui.*;

/**
 * @author DorA
 *
 * 2015年4月17日00:20:14
 */
public class FrameStartGame extends FrameTotal{
	
	//开始界面背景图片
	ImageIcon ic=new ImageIcon("image/bg/界面背景.png");	
	//背景音乐
	public static BackgroundMusic bgm=new BackgroundMusic("bgm01");

	final BgmSyncData bgmSyncData;
	final SoundSyncData soundSyncData;
	
	
	public FrameStartGame(BgmSyncData bgmData,SoundSyncData soundData){
		super();
		
		bgmSyncData=bgmData;
		soundSyncData=soundData;
	
		this.setContentPane(new PanelStartGame(ic,bgm,bgmSyncData,soundSyncData));

	}
	
	
/*	public void closeFrame(){
		f.dispose();
	}*/
		
	
	public static void main(String[]args){
		BgmSyncData bgmSyncData=new BgmSyncData();
		SoundSyncData soundSyncData=new SoundSyncData();
		final FrameStartGame frame=new FrameStartGame(bgmSyncData, soundSyncData);

	}
	



}