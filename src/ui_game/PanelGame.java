package ui_game;


import gamecomponent.Light;
import gamecomponent.LightControl;
import gamedata.GameData;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import control.PlayerControl;
/**
 * 游戏面板类，并且传入GameData的数据和引入PlayerControl对面板上的操作进行监听，引入线程
 * @author 恩哥哥
 * 2015.4.15.
 */

public class PanelGame extends JPanel implements Runnable{
	PlayerControl playerControl;
	
	private GameData gameData;
	private LightControl lightControl;
	private JButton testButton;
	
	public PanelGame(GameData gameData){
		this.gameData = gameData;
		
	}

	/**
	 * 加入玩家控制器，对面板操作进行监听
	 * @param playerControl
	 */
	public void addControl(PlayerControl playerControl){
		this.playerControl = playerControl;
	}
	
	//===================测试=====================
	public void Test(){
        testButton = new JButton("测试光线");
		
		testButton.addActionListener(playerControl);
		testButton.setActionCommand("launchLight");
		
		this.add(testButton);
	}
	public void run() {
		try {
			Thread.sleep(50);
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.repaint();
	}
	
	/**
	 * 绘画游戏面板的各种组件
	 */
	public void paintComponent(Graphics g){
		//绘画光线链表中所有的光线
		ArrayList<Light> lightList = this.gameData.getLightControl().getLightList();
		for (int i = 0; i < lightList.size(); i++) {
			lightList.get(i).paint(g);
		}
		
	}
}
