/**
 * 开始界面
 * 现已实现：显示背景图片，进入游戏自动播放背景乐，按退出按钮关闭游戏，按静音按钮开关背景乐。
 */
package ui_start;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import control.PlayerControl;

import javax.swing.*;

import control.GameControl;
import audio.BackgroundMusic;


/**
 * @author DorA
 *
 * 2015年4月17日00:20:14
 */
public class FrameStartGame extends ui.JFrameTotal{

	//开始界面背景图片
	private static ImageIcon bg=new ImageIcon("image/bg/界面背景.png");
	//背景音乐
	private BackgroundMusic bgm=new BackgroundMusic();
	//按钮的图标
	private ImageIcon defaultIcon=new ImageIcon("image/button/img1.jpg");
	private ImageIcon rollIcon=new ImageIcon("image/button/img2.jpg");
	//静音按钮的设置值
	private int set=0;
	//玩家控制器
	//private PlayerControl pc=new PlayerControl(new GameControl());
	
	//FrameSelectMission fsm=new FrameSelectMission();
	
	public FrameStartGame(){
		super();
		final FrameStartGame f=this;
		
		//播放背景音乐
		bgm.play();
		
		//在分层面板加入背景图片面板	
		JLabel background = new JLabel(bg);
		background.setBounds(0,0,1024,768);		
		this.getLayeredPane().add(background);
		
		//将内容面板设置为透明	
		Container cp=this.getContentPane();
		((JPanel)cp).setOpaque(false);
		

		//自由布局
		setLayout(null);
		
		//添加一个开始游戏按钮
		JButton jbtStart=new JButton(defaultIcon);
		jbtStart.setBounds((int)(1024*0.3),(int)(768*0.5),100,100);
		jbtStart.setToolTipText("select mission");
			jbtStart.setPressedIcon(rollIcon);
			jbtStart.setRolloverIcon(rollIcon);
		f.add(jbtStart);
		
		//按下后进入选关界面	
		jbtStart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//add(panel);
			//	closeFrame();
				f.dispose();
				FrameSelectMission fsm=new FrameSelectMission();
				
			}
		});	

		
		
		//添加一个帮助&演示按钮
		JButton jbtHelp=new JButton(defaultIcon);
		jbtHelp.setBounds((int)(1024*0.1),(int)(768*0.3),100,100);
		jbtHelp.setPressedIcon(rollIcon);
		jbtHelp.setRolloverIcon(rollIcon);
		add(jbtHelp);
			//此处应有监听器
		jbtHelp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//add(panel);
				
			}
		});
			
		//添加一个退出游戏按钮
		JButton jbtQuit=new JButton(defaultIcon);
		jbtQuit.setBounds((int)(1024*0.6),(int)(768*0.1),100,100);
		jbtQuit.setToolTipText("Quit");
		jbtQuit.setPressedIcon(rollIcon);
		jbtQuit.setRolloverIcon(rollIcon);
		add(jbtQuit);
		
			
		//监听器，按下按钮后关闭游戏	
		jbtQuit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);		
			}
		});
		
		//在内容面板加入背景音乐开关按钮
		JButton jbtSilence=new JButton();
		jbtSilence.setBounds((int)(1024*0.8),(int)(768*0.1),100,100);
		add(jbtSilence);
		
		//按钮监听器，背景音乐开关
		jbtSilence.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				final int open=1,off=0;	
				if(set==open){
					bgm.play();			//播放背景音乐
					set--;
				}else if(set==off){					
					bgm.stop();			//停止播放
					set++;
				}				
			}	
		});

	}
	

	
/*	public static void main(String[]args){
		final FrameStartGame frame=new FrameStartGame();

	}*/

}
