package control;

import gamecomponent.PlanetEarth;
import gamedata.GameData;
import gamedata.TotalData;
import ui.FrameTotal;
import ui_game.PanelGame;
import ui_start.PanelSelectMission;
import ui_start.PanelStartGame;
/**
 * 游戏控制器，用于接收PlayerControl传入的信息，并刷新JPanelGame。
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
	 * 单局游戏数据
	 */
	private GameData gameData;
	
	public GameControl(TotalData totalData){
		//加入游戏总数据
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
	
	//
	public void stopDrag(){
		this.panelGame.stopDrag();
	}
	
	/**
	 * 发射光线
	 * @param lightX
	 * @param lightY
	 */
	public void launchLight() {
		//TODO 启动光线  初始X坐标，初始Y坐标，方向向量X坐标，方向向量Y坐标
		this.gameData.getLightControl().launchLight(PlanetEarth.lightX, PlanetEarth.lightY, 10, -7);
		this.panelGame.repaint();
	}
	//==========================以下是各个界面间的跳转方法==============================
	/**
	 * 从开始界面跳转至选关界面
	 */
	public void toSelectMission() {
		this.frameTotal.musicStart.stop();
		this.frameTotal.remove(this.panelStartGame);
		this.frameTotal.initPanelSelectMission();
	}
	
	/**
	 * 从选关界面跳转至第一关游戏界面
	 */
	public void toFirstLevel() {
		this.gameData =new GameData(1);
		this.frameTotal.musicSelect.stop();
		this.frameTotal.remove(this.panelSelectMission);
		this.frameTotal.initPanelGame(this.gameData);
		
		this.panelGame.addControl(this);
	}
	
	/**
	 * 从通关界面返回至选关界面
	 */
	public void returnFromWin() {
		this.panelGame.closeFrameWin();
		this.frameTotal.remove(this.panelGame);
		this.frameTotal.initPanelSelectMission();
	}
	
	/**
	 * 从游戏界面返回选关界面
	 */
	public void returnFromGame() {
		this.frameTotal.remove(this.panelGame);
		this.frameTotal.initPanelSelectMission();
	}
	
	/**
	 * 从选关界面返回至开始界面
	 */
	public void returnToStart() {
		this.frameTotal.remove(this.panelSelectMission);
		this.frameTotal.musicSelect.stop();
		this.frameTotal.initPanelStartGame();
	}
	
	/**
	 * 打开帮助界面
	 */
	public void openFrameHelp(){
		this.panelStartGame.openFrameHelp();
	}
	
	/**
	 * 关闭帮助界面
	 */
	public void closeFrameHelp() {
		this.panelStartGame.closeFrameHelp();	
	}
	
	/**
	 * 进行下一关
	 */
	public void nextLevel() {
		//关闭通关界面
		this.panelGame.closeFrameWin();
		
		//重新建立单局游戏数据
		this.gameData =new GameData(1);
		//移除原有的游戏界面
		this.frameTotal.remove(this.panelGame);		
		//下一关游戏界面
		this.frameTotal.initPanelGame(this.gameData);
	}
	
	/**
	 * 退出游戏
	 */
	public void Quit() {
		this.frameTotal.removeAll();
		this.frameTotal.dispose();
		System.exit(0);
	}
}