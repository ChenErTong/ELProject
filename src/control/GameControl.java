package control;

import gamecomponent.PlanetEarth;
import gamedata.GameData;
import gamedata.TotalData;
import gameservice.GameService;
import ui.FrameTotal;
import ui_game.PanelGame;
import ui_start.PanelSelectMission;
import ui_start.PanelStartGame;
/**
 * 游戏控制器，用于接收PlayerControl传入的信息，并将信息传入GameService，并刷新JPanelGame。
 * @author 恩哥哥
 * 2015.4.13.
 */
public class GameControl {
	/**
	 * 游戏界面
	 */
	private FrameTotal frameTotal;
	/**
	 * 开始界面层
	 */
	private PanelStartGame panelStartGame;
	/**
	 * 选关界面层
	 */
	private PanelSelectMission panelSelectMission;
	/**
	 * 游戏总数据
	 */
	private TotalData totalData;
	/**
	 * 游戏界面层
	 */
	private PanelGame panelGame;
	/**
	 * 游戏逻辑层
	 */
	private GameService gameService;
	/**
	 * 单局游戏数据
	 */
	private GameData gameData;
	
	public GameControl(TotalData totalData){	
		this.totalData = totalData;
	}
	/**
	 * 向游戏控制器中加入界面
	 * @param frameTotal
	 */
	public void addFrame(FrameTotal frameTotal) {
		this.frameTotal = frameTotal;	
	}
	
	public void setPanelStartGame(PanelStartGame panelStartGame) {
		this.panelStartGame = panelStartGame;
	}
	public void setPanelSelectMission(PanelSelectMission panelSelectMission) {
		this.panelSelectMission = panelSelectMission;
	}
	public void setPanelGame(PanelGame panelGame) {
		this.panelGame = panelGame;
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
		this.frameTotal.remove(this.panelStartGame);
		this.frameTotal.initPanelSelectMission();
	}
	/**
	 * 从选关界面跳转至第一关游戏界面
	 */
	public void toFirstLevel() {
		this.gameData =new GameData();
		this.gameService=new GameService(this.gameData);	
		this.frameTotal.remove(this.panelSelectMission);
		this.frameTotal.initPanelGame(this.gameData);
	}
	/**
	 * 从通关界面返回至选关界面
	 */
	public void returnFromWin() {
		this.panelGame.getWinFrame().dispose();
		this.frameTotal.remove(this.panelGame);
		this.frameTotal.initPanelSelectMission();
	}
	/**
	 * 从选关界面返回至开始界面
	 */
	public void returnToStart() {
//		this.panelSelectMission.setVisible(false);
		this.frameTotal.remove(this.panelSelectMission);
		this.frameTotal.initPanelStartGame();
	}
	
	public void openPanelHelp(){

	}
	
}
