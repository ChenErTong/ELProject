/**
 * 选关界面面板
 */
package ui_start;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import audio.BackgroundMusic;
import ui.*;


/**
 * @author DorA
 *
 * 2015年4月28日上午1:35:28
 */
public class PanelSelectMission extends ui.PanelTotal{
	private int w=FrameTotal.WINDOWW;
	private int h=FrameTotal.WINDOWH;
	//按钮的图标
	private ImageIcon icon1=new ImageIcon("image/button/img1.jpg");	
	//虚拟，该值应当由各关卡传入
	private boolean isPassed=true;
	

	public PanelSelectMission(ImageIcon bg,BackgroundMusic bgm,BgmSyncData bgmData,SoundSyncData soundData){
		super();
		
		if(isPassed){
			//显示第一关图标
			JButton jbtMission1=new JButton(icon1);
			jbtMission1.setBounds((int)(w*0.2), (int)(h*0.4), 100, 100);
			add(jbtMission1);
			
			//监视器，点击图标进入关卡一
			jbtMission1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					//mission1();		
				}	
			});
		}
		
		if(isPassed){
			//显示第二关图标
			JButton jbtMission2=new JButton(icon1);
			jbtMission2.setBounds((int)(w*0.5), (int)(h*0.4), 100, 100);
			add(jbtMission2);
			
			//监视器，点击图标进入关卡二
			jbtMission2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					//mission2();		
				}	
			});
		}
		
		if(isPassed){
			//显示第三关图标
			JButton jbtMission3=new JButton(icon1);
			jbtMission3.setBounds((int)(w*0.8), (int)(h*0.4), 100, 100);
			add(jbtMission3);
			
			//监视器，点击图标进入关卡三
			jbtMission3.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					//mission3();		
				}	
			});
		}
		
		

		
		
		//返回按钮
		JButton jbtBack=new JButton(icon1);
		jbtBack.setBounds((int)(w*0.5), (int)(h*0.7), 100, 100);
		add(jbtBack);
		
	/*	//监视器，点击图标进入开始界面
		jbtBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				bgm.stop();			//关闭背景音乐
				frame.dispose();		//关闭该界面
				FrameStartGame fsm=new FrameStartGame(bgmSyncData, soundSyncData);	//打开新界面	
			}	
		});*/
	}


	
	
	
	

}
