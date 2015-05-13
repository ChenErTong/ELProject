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
	private ImageIcon[] selectIcon;
	
	private ImageIcon imgReturn=Planet.getImageIcon("image/button/Return4.png", (int)(FrameTotal.WINDOWW*0.098), (int)(FrameTotal.WINDOWH*0.117));
	//各个关卡进入按钮
	private JButton jbtMission1;
	private JButton jbtMission2;
	private JButton jbtMission3;
	private JButton jbtMission4;
	private JButton jbtMission5;
	//返回按钮
	private JButton jbtBack;
	public PanelSelectMission(BackgroundMusic bgm, BgmSyncData bgmData,SoundSyncData soundData, FrameTotal frameTotal){
		super(bgm, bgmData, soundData,frameTotal);
		
		//根据关卡解锁数量及关卡等级声明按钮的图标
		int imageW = (int)(FrameTotal.WINDOWW*0.112);
		int imageH = (int)(FrameTotal.WINDOWH*0.184);
		this.selectIcon = new ImageIcon[5];
		String address = new String();
		for (int i = 0; i < selectIcon.length; i++) {
			if(i<FrameTotal.TOTALDATA.getLevel()){
				address = "image/button/" + (i+1) + FrameTotal.TOTALDATA.getGrade(i+1)+".png";				
			}else{
				address = "image/button/" + (i+1) +"-1.png";
			}
			this.selectIcon[i] = Planet.getImageIcon(address, imageW, imageH);
		}
	
		//显示第一关图标
		this.jbtMission1=new JButton(this.selectIcon[0]);
		this.jbtMission1.setBounds((int)(FrameTotal.WINDOWW*0.044), (int)(FrameTotal.WINDOWH*0.213), imageW, imageH);
		this.jbtMission1.setContentAreaFilled(false);
		this.jbtMission1.setBorderPainted(false);
		this.jbtMission1.setActionCommand("1");
		this.add(jbtMission1);
	
		//显示第二关图标
		this.jbtMission2=new JButton(this.selectIcon[1]);
		this.jbtMission2.setBounds((int)(FrameTotal.WINDOWW*0.199), (int)(FrameTotal.WINDOWH*0.370), imageW, imageH);
		this.jbtMission2.setContentAreaFilled(false);
		this.jbtMission2.setBorderPainted(false);
		this.jbtMission2.setActionCommand("2");
		this.add(jbtMission2);
	
		//显示第三关图标
		this.jbtMission3=new JButton(this.selectIcon[2]);
		this.jbtMission3.setBounds((int)(FrameTotal.WINDOWW*0.432), (int)(FrameTotal.WINDOWH*0.427), imageW, imageH);
		this.jbtMission3.setContentAreaFilled(false);
		this.jbtMission3.setBorderPainted(false);
		this.jbtMission3.setActionCommand("3");
		this.add(jbtMission3);
	
		//显示第四关图标
		this.jbtMission4=new JButton(this.selectIcon[3]);
		this.jbtMission4.setBounds((int)(FrameTotal.WINDOWW*0.686), (int)(FrameTotal.WINDOWH*0.378), imageW, imageH);
		this.jbtMission4.setContentAreaFilled(false);
		this.jbtMission4.setBorderPainted(false);
		this.jbtMission4.setActionCommand("4");
		this.add(jbtMission4);
	
		//显示第五关图标
		this.jbtMission5=new JButton(this.selectIcon[4]);
		this.jbtMission5.setBounds((int)(FrameTotal.WINDOWW*0.851), (int)(FrameTotal.WINDOWH*0.253), imageW, imageH);
		this.jbtMission5.setContentAreaFilled(false);
		this.jbtMission5.setBorderPainted(false);
		this.jbtMission5.setActionCommand("5");
		this.add(jbtMission5);

		//返回按钮
		this.jbtBack=new JButton(imgReturn);
		this.jbtBack.setBounds((int)(FrameTotal.WINDOWW*0.0156), (int)(FrameTotal.WINDOWH*0.887), (int)(FrameTotal.WINDOWW*0.098), (int)(FrameTotal.WINDOWH*0.117));
		this.jbtBack.setContentAreaFilled(false);
		this.jbtBack.setBorderPainted(false);
		this.jbtBack.setActionCommand("ReturnToStart");
		this.add(jbtBack);

		//在分层面板加入背景图片面板	
		this.backgroundImg=Planet.getImageIcon("image/bg/解锁界面.png", FrameTotal.WINDOWW, FrameTotal.WINDOWH);
		JLabel background = new JLabel(this.backgroundImg);
		background.setBounds(0,0,FrameTotal.WINDOWW,FrameTotal.WINDOWH);		
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
		case 5: jbtMission5.addActionListener(this.playerControl);
		case 4: jbtMission4.addActionListener(this.playerControl);
		case 3: jbtMission3.addActionListener(this.playerControl);
		case 2: jbtMission2.addActionListener(this.playerControl);
		case 1: jbtMission1.addActionListener(this.playerControl);
		}
		
		jbtBack.addActionListener(playerControl);
	}
}