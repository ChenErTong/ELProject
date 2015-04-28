/**
 * 开始界面
 */
package ui_start;

import ui.*;

/**
 * @author DorA
 *
 * 2015年4月17日00:20:14
 */
public class FrameStartGame extends JFrameTotal{
	

	private final FrameStartGame f=this;
	
	
	public FrameStartGame(BgmSyncData bgmSyncData,SoundSyncData soundSyncData){
		super();
	
		this.setContentPane(new PanelStartGame(f,bgmSyncData,soundSyncData));

	}
	
	
	public void closeFrame(){
		f.dispose();
	}
		
	
	public static void main(String[]args){
		BgmSyncData bgmSyncData=new BgmSyncData();
		SoundSyncData soundSyncData=new SoundSyncData();
		final FrameStartGame frame=new FrameStartGame(bgmSyncData, soundSyncData);

	}
	



}