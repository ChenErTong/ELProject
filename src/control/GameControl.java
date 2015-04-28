package control;

import gamecomponent.PlanetEarth;
import gamedata.GameData;
import gameservice.GameService;
import ui_game.FrameGame;
import ui_game.PanelGame;
import ui_start.FrameSelectMission;
import ui_start.FrameStartGame;
import ui_start.PanelSelectMission;
import ui_start.PanelStartGame;
/**
 * 游戏控制器，用于接收PlayerControl传入的信息，并将信息传入GameService，并刷新JPanelGame。
 * @author 恩哥哥
 * 2015.4.13.
 */
public class GameControl {
	/**
	 * 游戏控制器
	 */
	private PlayerControl playerControl;
	/**
	 * 开始界面
	 */
	private FrameStartGame frameStartGame;
	/**
	 * 选关界面
	 */
	private FrameSelectMission frameSelectMission;
	/**
	 * 游戏界面
	 */
	private FrameGame frameGame;
	/**
	 * 开始界面层
	 */
	private PanelStartGame panelStartGame;
	/**
	 * 选关界面层
	 */
	private PanelSelectMission panelSelectMission;
	/**
	 * 游戏界面层
	 */
	private PanelGame panelGame;
	/**
	 * 游戏逻辑层
	 */
	private GameService gameService;
	/**
	 * 游戏数据层
	 */
	private GameData gameData;
	
	public GameControl(GameService gameService, GameData gameData){		
		this.gameService=gameService;
		this.gameData =gameData;
	}

	/**
	 * 加入玩家控制器
	 * @param playerControl
	 */
	public void addControl(PlayerControl playerControl) {
		this.playerControl = playerControl;
	}	
	/**
	 * 向游戏控制器中加入所有界面
	 * @param frameStartGame
	 * @param frameSelectMission
	 * @param frameGame
	 */
	public void gameStart(FrameStartGame frameStartGame) {
		this.frameStartGame = frameStartGame;
		this.frameStartGame.setVisible(true);
		this.panelStartGame = this.frameStartGame.panelStartGame;
	}
	
	public void launchLight() {
		this.gameService.launchLight(PlanetEarth.lightX, PlanetEarth.lightY);
		this.panelGame.repaint();
	}
	//==========================以下是各个界面间的跳转方法==============================
	/**
	 * 从开始界面跳转至选关界面
	 */
	public void toSelectMission() {
		this.panelStartGame.removeAll();
		this.frameStartGame.dispose();
		this.frameStartGame.pack();
		this.frameSelectMission = new FrameSelectMission(playerControl, gameData);
		this.frameSelectMission.setVisible(true);
		this.panelSelectMission = this.frameSelectMission.panelSelectMission;
	}
	/**
	 * 从选关界面跳转至第一关游戏界面
	 */
	public void toFirstLevel() {
		this.panelSelectMission.removeAll();
		this.frameSelectMission.dispose();
		this.frameSelectMission.pack();
		this.frameGame = new FrameGame(playerControl, gameData);
		frameGame.setVisible(true);
		this.panelGame = this.frameGame.panelGame;
	}
	/**
	 * 从通关界面返回至选关界面
	 */
	public void returnFromWin() {
		this.panelGame.removeAll();
		this.frameGame.dispose();
		this.frameGame.pack();
		this.frameSelectMission = new FrameSelectMission(playerControl, gameData);
		this.frameSelectMission.setVisible(true);
		this.panelSelectMission = this.frameSelectMission.panelSelectMission;
	}
	
}
