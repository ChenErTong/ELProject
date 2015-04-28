/**
 * 开始界面面板
 */
package ui_start;

import java.awt.Graphics;
import java.awt.Image;
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
 * 2015年4月17日00:20:14
 */
public class PanelStartGame extends JPanel{
	private int w=JFrameTotal.WINDOWW;
	private int h=JFrameTotal.WINDOWH;
	//开始界面背景图片
	ImageIcon ic=new ImageIcon("image/bg/界面背景.png");
	Image bg=ic.getImage();
	
	//按钮的图标
	private ImageIcon defaultIcon=new ImageIcon("image/button/img1.jpg");
	private ImageIcon rollIcon=new ImageIcon("image/button/img2.jpg");

	//背景音乐
	public static BackgroundMusic bgm=new BackgroundMusic("bgm01");
	
	
	public PanelStartGame(FrameStartGame f,BgmSyncData data,SoundSyncData soundData){
		final FrameStartGame frame=f;
		final BgmSyncData bgmSyncData=data;
		final SoundSyncData soundSyncData=soundData;
	
		this.setBounds(0, 0, w, h);		
		//自由布局
		setLayout(null);
				
		
		//添加一个开始游戏按钮
		JButton jbtStart=new JButton(defaultIcon);
		jbtStart.setBounds((int)(w*0.2),(int)(h*0.5),100,100);
		jbtStart.setToolTipText("select mission");
			jbtStart.setPressedIcon(rollIcon);
			jbtStart.setRolloverIcon(rollIcon);
		add(jbtStart);
		
		//按下后进入选关界面	
		jbtStart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				bgm.stop();			//关闭背景音乐
				frame.closeFrame();		//关闭该界面
				FrameSelectMission fsm=new FrameSelectMission(bgmSyncData,soundSyncData);	//打开新界面
				
			}
		});	
		
		
		//添加一个帮助&演示按钮
		JButton jbtHelp=new JButton(defaultIcon);
		jbtHelp.setBounds((int)(w*0.45),(int)(h*0.5),100,100);
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
		jbtQuit.setBounds((int)(w*0.7),(int)(h*0.5),100,100);
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
		ButtonBackgroundMusic jbtSilence=new ButtonBackgroundMusic(bgmSyncData);
		jbtSilence.setMusic(bgm);
		add(jbtSilence);
		
		//加入音效开关按钮
		ButtonSound jbtSound=new ButtonSound(soundSyncData);
		add(jbtSound);
		
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(bg, 0, 0, w,h,this);

	}

}
