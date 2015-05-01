/**
 * 选关界面面板
 */
package ui_start;

import gamedata.GameData;
import gamedata.TotalData;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import audio.BackgroundMusic;
import ui.BgmSyncData;
import ui.SoundSyncData;
import control.PlayerControl;


/**
 * @author DorA
 *
 * 2015年4月28日上午1:35:28
 */
public class PanelSelectMission extends ui.PanelTotal{
	//按钮的图标
	private ImageIcon icon1=new ImageIcon("image/button/img1.jpg");	
	//虚拟，该值应当由各关卡传入
	private boolean isPassed=true;
	
	//各个关卡进入按钮
	private JButton jbtMission1;
	private JButton jbtMission2;
	private JButton jbtMission3;
	//返回按钮
	private JButton jbtBack;
	
	public PanelSelectMission(BackgroundMusic bgm, BgmSyncData bgmData,SoundSyncData soundData, TotalData totalData){
		super(bgm, bgmData, soundData, totalData);
		
		if(this.isPassed){
			//显示第一关图标
			this.jbtMission1=new JButton(icon1);
			this.jbtMission1.setBounds((int)(width*0.2), (int)(height*0.4), 100, 100);
			this.jbtMission1.setActionCommand("ToFirstLevel");
			this.add(jbtMission1);
		}
		
		if(this.isPassed){
			//显示第二关图标
			this.jbtMission2=new JButton(icon1);
			this.jbtMission2.setBounds((int)(width*0.5), (int)(height*0.4), 100, 100);
			this.add(jbtMission2);
		}
		
		if(this.isPassed){
			//显示第三关图标
			this.jbtMission3=new JButton(icon1);
			this.jbtMission3.setBounds((int)(width*0.8), (int)(height*0.4), 100, 100);
			this.add(jbtMission3);
		}
		
		//返回按钮
		this.jbtBack=new JButton(icon1);
		this.jbtBack.setBounds((int)(width*0.5), (int)(height*0.7), 100, 100);
		this.jbtBack.setActionCommand("ReturnToStart");
		this.add(jbtBack);

		//在分层面板加入背景图片面板	
		this.backgroundImg=new ImageIcon("image/bg/界面背景.png");
		JLabel background = new JLabel(this.backgroundImg);
		background.setBounds(0,0,width,height);		
		this.add(background);
	}

	public void addControl(PlayerControl playerControl) {
		this.playerControl = playerControl;
		/**
		 * 给所有关卡按钮加入玩家控制器进行监听
		 */
		jbtMission1.addActionListener(playerControl);
		jbtMission2.addActionListener(playerControl);
		jbtMission3.addActionListener(playerControl);
		jbtBack.addActionListener(playerControl);
	}
}
