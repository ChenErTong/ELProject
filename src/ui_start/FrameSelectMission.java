/**
 * 选关界面，应在按下开始界面“开始游戏”按钮后进入。根据是否解锁该关卡来决定是否可以点击图标进入该关卡。
 * 是否解锁该关卡应该有每个关卡提供一个布尔值。
 */
package ui_start;

import java.awt.Container;
import java.awt.event.*;

import javax.swing.*;


/**
 * @author DorA
 *
 * 2015年4月18日下午6:09:14
 */
public class FrameSelectMission extends ui.JFrameTotal{
	//开始界面背景图片
	private static ImageIcon bg=new ImageIcon("image/bg/界面背景.png");
	//按钮的图标
	private ImageIcon icon1=new ImageIcon("image/button/img1.jpg");
	
	//虚拟，该值应当由各关卡传入
	private boolean isPassed=true;
	
	public FrameSelectMission(){	//括号中应该传入各关卡是否通过的布尔值
		super();
		//在分层面板加入背景图片面板	
		JLabel background = new JLabel(bg);
		background.setBounds(0,0,1024,768);		
		this.getLayeredPane().add(background);
		
		//将内容面板设置为透明	
		Container cp=this.getContentPane();
		((JPanel)cp).setOpaque(false);
		
		//自由布局
		setLayout(null);
		
		if(isPassed){
			//显示第一关图标
			JButton jbtMission1=new JButton(icon1);
			jbtMission1.setBounds((int)(1024*0.2), (int)(768*0.4), 100, 100);
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
			jbtMission2.setBounds((int)(1024*0.5), (int)(768*0.4), 100, 100);
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
			jbtMission3.setBounds((int)(1024*0.8), (int)(768*0.4), 100, 100);
			add(jbtMission3);
			
			//监视器，点击图标进入关卡三
			jbtMission3.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					//mission3();		
				}	
			});
		}
		
	}
	/*
	public static void main(String[]args){
		FrameSelectMission f=new FrameSelectMission();
	}*/
}
