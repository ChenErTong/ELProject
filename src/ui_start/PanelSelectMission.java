package ui_start;

import gamedata.GameData;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.PlayerControl;

public class PanelSelectMission extends JPanel{
	//加入游戏数据
	private GameData gameData;
	//加入玩家控制器
	private PlayerControl playerControl;
	//开始界面背景图片
	private static ImageIcon bg=new ImageIcon("image/bg/界面背景.png");
	//按钮的图标
	private ImageIcon icon1=new ImageIcon("image/button/img1.jpg");
	
	// TODO虚拟，该值应当由各关卡传入,当确定关卡数后声明为数组
	private boolean isPassed=true;
		
	//各个关卡进入按钮
	private JButton jbtMission1;
	private JButton jbtMission2;
	private JButton jbtMission3;
	
	public PanelSelectMission(GameData gameData){
		this.gameData = gameData;
		
		this.setLayout(null);
		
		if(isPassed){
			//显示第一关图标
			jbtMission1=new JButton(icon1);
			jbtMission1.setBounds((int)(1024*0.2), (int)(768*0.4), 100, 100);
			jbtMission1.setActionCommand("ToFirstLevel");
			add(jbtMission1);
		}
		
		if(isPassed){
			//显示第二关图标
			jbtMission2=new JButton(icon1);
			jbtMission2.setBounds((int)(1024*0.5), (int)(768*0.4), 100, 100);
			add(jbtMission2);
		}
		
		if(isPassed){
			//显示第三关图标
			jbtMission3=new JButton(icon1);
			jbtMission3.setBounds((int)(1024*0.8), (int)(768*0.4), 100, 100);
			add(jbtMission3);
		}
		
		//在分层面板加入背景图片面板	
		JLabel background = new JLabel(bg);
		background.setBounds(0,0,1024,768);		
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
	}
}
