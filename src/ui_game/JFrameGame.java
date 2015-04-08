package ui_game;

import javax.swing.JFrame;

import ui.JFrameTotal;
/**
 * 游戏界面框架
 * @author 恩哥哥
 * 2015.4.8.
 */

public class JFrameGame extends JFrameTotal{
	
	public JFrameGame(){
		super();
		
		//加入游戏界面面板
		JPanelGame panelGame = new JPanelGame();
		this.setContentPane(panelGame);
	}
}
