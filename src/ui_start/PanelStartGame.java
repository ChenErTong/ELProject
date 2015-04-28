package ui_start;

import gamedata.GameData;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.PlayerControl;
import audio.BackgroundMusic;

public class PanelStartGame extends JPanel{
	//加入游戏数据
	private GameData gameData;
	//加入玩家控制器
	private PlayerControl playControl;
	//开始界面背景图片
	private static ImageIcon bg=new ImageIcon("image/bg/界面背景.png");
	//背景音乐
	private BackgroundMusic bgm=new BackgroundMusic();
	//按钮的图标
	private ImageIcon defaultIcon=new ImageIcon("image/button/img1.jpg");
	private ImageIcon rollIcon=new ImageIcon("image/button/img2.jpg");
	//静音按钮的设置值
	private int set=0;
	//开始按钮
	private JButton jbtStart;
	//帮助按钮
	private JButton jbtHelp;
	//退出按钮
	private JButton jbtQuit;
	//背景音乐按钮
	private JButton jbtSilence;
	
	public PanelStartGame(GameData gameData){
		this.gameData = gameData;
		
		//播放背景音乐
		bgm.play();
				
		
		
		//添加一个开始游戏按钮
		jbtStart=new JButton(defaultIcon);
		jbtStart.setBounds((int)(1024*0.3),(int)(768*0.5),100,100);
		jbtStart.setToolTipText("select mission");
		jbtStart.setPressedIcon(rollIcon);
		jbtStart.setRolloverIcon(rollIcon);
		//按下后进入选关界面	
		jbtStart.setActionCommand("ToSelectMission");
		this.add(jbtStart);
		
		//添加一个帮助&演示按钮
		jbtHelp=new JButton(defaultIcon);
		jbtHelp.setBounds((int)(1024*0.1),(int)(768*0.3),100,100);
		jbtHelp.setPressedIcon(rollIcon);
		jbtHelp.setRolloverIcon(rollIcon);
		add(jbtHelp);

		//添加一个退出游戏按钮
		jbtQuit=new JButton(defaultIcon);
		jbtQuit.setBounds((int)(1024*0.6),(int)(768*0.1),100,100);
		jbtQuit.setToolTipText("Quit");
		jbtQuit.setPressedIcon(rollIcon);
		jbtQuit.setRolloverIcon(rollIcon);
		add(jbtQuit);

		//在内容面板加入背景音乐开关按钮
		jbtSilence=new JButton();
		jbtSilence.setBounds((int)(1024*0.8),(int)(768*0.1),100,100);
		add(jbtSilence);
		
		//在分层面板加入背景图片面板	
		JLabel background = new JLabel(bg);
		background.setBounds(0,0,1024,768);		
		this.add(background);
		
		this.setLayout(null);
		
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

	public void addControl(PlayerControl playerControl) {
		this.playControl = playerControl;
		/**
		 * 给所有按钮加入监听
		 */
		jbtStart.addActionListener(this.playControl);
		jbtHelp.addActionListener(playerControl);
		jbtQuit.addActionListener(playerControl);
	}
}
