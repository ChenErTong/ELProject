package control;

import gamecomponent.PlanetEarth;
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
	
	public GameControl(PanelStartGame panelStartGame, PanelSelectMission panelSelectMission, PanelGame panelGame,GameService gameService){
		this.panelGame=panelGame;
		this.panelStartGame = panelStartGame;
		this.panelSelectMission = panelSelectMission;
		this.gameService=gameService;
	}

	/**
	 * 向游戏控制器中加入所有界面
	 * @param frameStartGame
	 * @param frameSelectMission
	 * @param frameGame
	 */
	public void addFrames(FrameStartGame frameStartGame, FrameSelectMission frameSelectMission, FrameGame frameGame) {
		this.frameStartGame = frameStartGame;
		this.frameSelectMission = frameSelectMission;
		this.frameGame = frameGame;
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
		this.frameStartGame.setVisible(false);
		this.frameSelectMission.setVisible(true);
	}
	/**
	 * 从选关界面跳转至第一关游戏界面
	 */
	public void toFirstLevel() {
		this.frameSelectMission.setVisible(false);
		this.frameGame.setVisible(true);
	}
	/**
	 * 从通关界面返回至选关界面
	 */
	public void returnFromWin() {
		this.frameSelectMission.setVisible(true);
		this.frameGame.setVisible(false);	
	}	
}
