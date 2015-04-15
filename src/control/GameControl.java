package control;

import gameservice.GameService;
import ui_game.PanelGame;
/**
 * 游戏控制器，用于接收PlayerControl传入的信息，并将信息传入GameService，并刷新JPanelGame。
 * @author 恩哥哥
 * 2015.4.13.
 */
public class GameControl {
	/**
	 * 游戏界面层
	 */
	private PanelGame panelGame;
	/**
	 * 游戏逻辑层
	 */
	private GameService gameService;
	
	public GameControl(PanelGame panelGame,GameService gameService){
		this.panelGame=panelGame;
		this.gameService=gameService;
	}

	public void launchLight() {
		System.out.println("GameControl");
		this.gameService.launchLight();
		this.panelGame.repaint();
	}
}
