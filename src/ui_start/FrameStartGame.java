/**
 * 开始界面
 * 现已实现：显示背景图片，进入游戏自动播放背景乐，按退出按钮关闭游戏，按静音按钮开关背景乐。
 */
package ui_start;

import java.awt.Container;

import ui.*;

import java.awt.event.*;

import javax.swing.*;

import audio.*;


/**
 * @author DorA
 *
 * 2015年4月17日00:20:14
 */
public class FrameStartGame extends JFrameTotal{

	//开始界面背景图片
	private static ImageIcon bg=new ImageIcon("image/bg/界面背景.png");
	//背景音乐
	public static BackgroundMusic bgm=new BackgroundMusic("bgm01");
	//按钮的图标
	private ImageIcon defaultIcon=new ImageIcon("image/button/img1.jpg");
	private ImageIcon rollIcon=new ImageIcon("image/button/img2.jpg");
	//保留静音按钮对象
	public static ButtonBackgroundMusic jbtBgm;
	public static ButtonSound jbtS;
/*	//静音按钮的设置值
	private int set=0;*/
	//玩家控制器
	//private PlayerControl pc=new PlayerControl(new GameControl());
	
	//FrameSelectMission fsm=new FrameSelectMission();
	
	public FrameStartGame(){
		super();
		final FrameStartGame f=this;
		
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
				bgm.stop();			//关闭背景音乐
				f.dispose();		//关闭该界面
				FrameSelectMission fsm=new FrameSelectMission();	//打开新界面
				
			}
		});	

		
		
		//添加一个帮助&演示按钮
		JButton jbtHelp=new JButton(defaultIcon);
		jbtHelp.setBounds((int)(1024*0.1),(int)(768*0.3),100,100);
		jbtHelp.setToolTipText("Help");
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
		jbtQuit.setBounds((int)(1024*0.3),(int)(768*0.1),100,100);
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
		ButtonBackgroundMusic jbtSilence=new ButtonBackgroundMusic();
		jbtBgm=jbtSilence;
		if(jbtSilence.getControl()==0){
			bgm.play();					//根据是否静音决定是否播放背景乐
		}
		jbtSilence.setMusic(bgm);
		add(jbtSilence);
		
		//加入音效开关按钮
		ButtonSound jbtSound=new ButtonSound();
		jbtS=jbtSound;
		add(jbtSound);
		

	}
	
	
	
	public static void main(String[]args){
		final FrameStartGame frame=new FrameStartGame();

	}

}
