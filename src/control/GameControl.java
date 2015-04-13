package control;

import gameservice.GameService;
import ui_game.JPanelGame;
/**
 * 游戏控制器，用于接收玩家控制器传入的信息，并将信息传入游戏逻辑处理(gameService)，并刷新界面
 * @author 恩哥哥
 * 2015.4.13.
 */
public class GameControl {
	/**
	 * 游戏界面层
	 */
	private JPanelGame panelGame;
	/**
	 * 游戏逻辑层
	 */
	private GameService gameService;
	
	public GameControl(JPanelGame panelGame,GameService gameService){
		this.panelGame=panelGame;
		this.gameService=gameService;
	}

	public void launchLight() {
		this.gameService.launchLight();
		this.panelGame.repaint();
	}
}
