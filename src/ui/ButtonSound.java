/**
 * 音效开关按钮。
 */
package ui;

import gamecomponent.Planet;

import java.awt.event.*;

import ui.SoundSyncData;
import audio.SoundEffect;

import javax.swing.*;

/**
 * @author DorA
 * 2015年4月20日00:34:26
 */
public class ButtonSound extends JButton{
	private int w=FrameTotal.WINDOWW;
	private int h=FrameTotal.WINDOWH;
	//静音按钮的设置值
	private int control;
	//按钮的图标
	private ImageIcon defaultIcon=Planet.getImageIcon("image/button/音效按钮2.png", 50, 50);
	private ImageIcon muteIcon=Planet.getImageIcon("image/button/禁音按钮版本2.png", 50, 50);
	
	private ButtonSound b=this;
	private SoundSyncData soundSyncData;
	
	public ButtonSound(SoundSyncData data){		
		super();
		
		soundSyncData=data;
		
		this.setBounds(960,532,50,50);
		
		//获得控制位数值
		control=soundSyncData.getControl();
		
		final int open=1,off=0;	
		
		//根据同步数据来显示不同的图标
		if(control==open){			
			b.setIcon(muteIcon);
		}else{
			b.setIcon(defaultIcon);
		}
	
		//监听器
		this.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){			
				if( control==open){
					SoundEffect.setMute(false);
					b.setIcon(defaultIcon);
					control--;
				}else if( control==off){	
					SoundEffect.setMute(true);
					b.setIcon(muteIcon);
					control++;
				}	
				soundSyncData.setControl(control);
			}	
		});

		//设置不绘制矩形的内容
		this.setContentAreaFilled(false);
		//设置不绘制边框
		this.setBorderPainted(false);
		//设置可见
		this.setVisible(true);	
	}
}