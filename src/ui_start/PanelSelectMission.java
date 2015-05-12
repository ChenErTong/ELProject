/**
 * 选关界面面板
 */
package ui_start;

import gamecomponent.Planet;
import gamedata.TotalData;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import audio.BackgroundMusic;
import ui.BgmSyncData;
import ui.FrameTotal;
import ui.PanelTotal;
import ui.SoundSyncData;
import control.PlayerControl;

/**
 * @author DorA
 *
 * 2015年4月28日上午1:35:28
 */
public class PanelSelectMission extends PanelTotal{
	//按钮的图标
	private ImageIcon icon1=Planet.getImageIcon("image/button/解锁界面星球1.png", 128, 128);
	private ImageIcon icon2=Planet.getImageIcon("image/button/解锁界面星球2.png", 108, 118);
	private ImageIcon icon3=Planet.getImageIcon("image/button/解锁界面星球3.png", 128, 113);
	private ImageIcon icon4=Planet.getImageIcon("image/button/解锁界面星球4.png", 113, 113);
	private ImageIcon icon5=Planet.getImageIcon("image/button/解锁界面星球5.png", 118, 108);
	
	private ImageIcon imgReturn=Planet.getImageIcon("image/button/Return4.png", 100, 70);
	//各个关卡进入按钮
	private JButton jbtMission1;
	private JButton jbtMission2;
	private JButton jbtMission3;
	private JButton jbtMission4;
	private JButton jbtMission5;
	//返回按钮
	private JButton jbtBack;
	public PanelSelectMission(BackgroundMusic bgm, BgmSyncData bgmData,SoundSyncData soundData, FrameTotal frameTotal){
		super(bgm, bgmData, soundData, frameTotal);
		
		//显示第一关图标
		this.jbtMission1=new JButton(icon1);
		this.jbtMission1.setBounds(45, 128, 128, 128);
		this.jbtMission1.setContentAreaFilled(false);
		this.jbtMission1.setBorderPainted(false);
		this.jbtMission1.setActionCommand("1");
		this.add(jbtMission1);
	
		//显示第二关图标
		this.jbtMission2=new JButton(icon2);
		this.jbtMission2.setBounds(204, 222, 108, 118);
		this.jbtMission2.setContentAreaFilled(false);
		this.jbtMission2.setBorderPainted(false);
		this.jbtMission1.setActionCommand("2");
		this.add(jbtMission2);
	
		//显示第三关图标
		this.jbtMission3=new JButton(icon3);
		this.jbtMission3.setBounds(442, 256, 128, 113);
		this.jbtMission3.setContentAreaFilled(false);
		this.jbtMission3.setBorderPainted(false);
		this.jbtMission1.setActionCommand("3");
		this.add(jbtMission3);
	
		//显示第四关图标
		this.jbtMission4=new JButton(icon4);
		this.jbtMission4.setBounds(702, 227, 113, 113);
		this.jbtMission4.setContentAreaFilled(false);
		this.jbtMission4.setBorderPainted(false);
		this.jbtMission1.setActionCommand("4");
		this.add(jbtMission4);
	
		//显示第五关图标
		this.jbtMission5=new JButton(icon5);
		this.jbtMission5.setBounds(871, 152, 118, 108);
		this.jbtMission5.setContentAreaFilled(false);
		this.jbtMission5.setBorderPainted(false);
		this.jbtMission1.setActionCommand("5");
		this.add(jbtMission5);

		//返回按钮
		this.jbtBack=new JButton(imgReturn);
		this.jbtBack.setBounds(16, 532, 100, 70);
		this.jbtBack.setContentAreaFilled(false);
		this.jbtBack.setBorderPainted(false);
		this.jbtBack.setActionCommand("ReturnToStart");
		this.add(jbtBack);

		//在分层面板加入背景图片面板	
		this.backgroundImg=Planet.getImageIcon("image/bg/解锁界面.png", width, height);
		JLabel background = new JLabel(this.backgroundImg);
		background.setBounds(0,0,width,height);		
		this.add(background);
	}
	
	/**
	 * 加入玩家控制器，对按钮进行监听
	 * @param playerControl
	 */
	public void addControl(PlayerControl playerControl) {
		this.playerControl = playerControl;
		/**
		 * 给所有关卡按钮加入玩家控制器进行监听
		 */
		switch(FrameTotal.TOTALDATA.getLevel()){
		case 5: jbtMission1.addActionListener(playerControl);
		case 4: jbtMission2.addActionListener(playerControl);
		case 3: jbtMission3.addActionListener(playerControl);
		case 2: jbtMission4.addActionListener(playerControl);
		case 1: jbtMission5.addActionListener(playerControl);
		}
		
		jbtBack.addActionListener(playerControl);
	}
}