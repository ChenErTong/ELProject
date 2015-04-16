package ui_game;

import gamecomponent.PlanetEarth;
import gamecomponent.PlanetSun;
import gamecomponent.PlanetThreeBody;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import ui.JFrameTotal;
/**
 * 游戏界面框架
 * @author 恩哥哥
 * 2015.4.8.
 */

public class JFrameGame extends JFrameTotal{

	
	public JFrameGame(PanelGame panelGame){
		super();
		//加入游戏界面面板

		this.setContentPane(panelGame);
		
		panelGame.add(new PlanetEarth(90,340,150),null);
		panelGame.add(new PlanetSun(320,440,200),null);
		panelGame.add(new PlanetThreeBody(800,-300,200));
		

		
	}

}
