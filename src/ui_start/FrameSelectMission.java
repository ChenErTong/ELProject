/**
 * 选关界面，应在按下开始界面“开始游戏”按钮后进入。根据是否解锁该关卡来决定是否可以点击图标进入该关卡。
 * 是否解锁该关卡应该有每个关卡提供一个布尔值。
 */
package ui_start;

import ui.*;

/**
 * @author DorA
 *
 * 2015年4月18日下午6:09:14
 */
public class FrameSelectMission extends ui.JFrameTotal{

	private final FrameSelectMission f=this;
	private final BgmSyncData bgmData;
	private final SoundSyncData soundData;
	
	public FrameSelectMission(BgmSyncData bgmSyncData,SoundSyncData soundSyncData){	//括号中应该传入各关卡是否通过的布尔值
		super();
		bgmData=bgmSyncData;
		soundData=soundSyncData;
		
		this.setContentPane(new PanelSelectMission(f,bgmData, soundData));
		
		
		
	}
	
	//关闭窗口方法
	public void closeFrame(){
		f.dispose();
	}

}