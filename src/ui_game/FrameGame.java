package ui_game;

import java.awt.Graphics;

import control.PlayerControl;
import gamecomponent.PlanetEarth;
import gamecomponent.PlanetSun;
import gamecomponent.PlanetThreeBody;
import gamedata.GameData;
import ui.FrameTotal;
/**
 * 游戏界面框架
 * @author 恩哥哥
 * 2015.4.8.
 */

public class FrameGame extends FrameTotal{
	public PanelGame panelGame;
	
	public FrameGame(PlayerControl playerControl, GameData gameData){
		super();
		
		//加入游戏界面面板
		this.panelGame = new PanelGame(gameData);
		this.panelGame.addControl(playerControl);
		this.setContentPane(panelGame);
		//
//		panelGame.add(new PlanetEarth(90,600,50));
//		panelGame.add(new PlanetSun(320,440,100),null);
//		panelGame.add(new PlanetThreeBody(800,30,75));
//		this.setVisible(true);
		
	}
	
	
}
