package control;

import audio.SoundEffect;
import gamecomponent.PlanetEarth;
import gamedata.GameData;
import gamedata.TotalData;
import ui.FrameTotal;
import ui.WindowDragger;
import ui_game.FrameWin;
import ui_game.PanelGame;
import ui_start.PanelEdit;
import ui_start.PanelSelectDIY;
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
	 * 自定义层
	 */
	private PanelSelectDIY panelSelectDIY;
	/**
	 * 游戏设计界面
	 */
	private PanelEdit panelEdit;
	/**
	 * 游戏界面层
	 */
	private PanelGame panelGame;
	/**
	 * 单局游戏数据
	 */
	private GameData gameData;
	
	public GameControl(){
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
	public void setPanelSelectDIY(PanelSelectDIY panelSelectDIY) {
		this.panelSelectDIY = panelSelectDIY;
	}
	public void setPanelGame(PanelGame panelGame) {
		this.panelGame = panelGame;
	}
	public void setPanelEdit(PanelEdit panelEdit) {
		this.panelEdit = panelEdit;
	}
	
	//
	public void stopDrag(){
		if(this.panelGame != null){
			this.panelGame.stopDrag();
		}else if (this.panelEdit != null){
			this.panelEdit.stopDrag();
		}
		
	}
	
	/**
	 * 发射光线
	 * @param launchX
	 * @param lightY
	 */
	public void launchLight() {
		if(this.panelGame != null){
			this.printPlanetLocation();
			this.gameData.getLightControl().launchLight(PlanetEarth.launchX, PlanetEarth.launchY, this.gameData.getLightDirectionX(), this.gameData.getLightDirectionY());
			this.panelGame.repaint();
			//音效
			SoundEffect.LIGHT.play();		
		}else if(this.panelEdit != null){
			this.gameData.getPlanetEarth().setLocations();
			this.gameData.setLaunchDirections();
			this.gameData.getLightControl().launchLight(PlanetEarth.launchX, PlanetEarth.launchY, this.gameData.getLightDirectionX(), this.gameData.getLightDirectionY());
			this.panelEdit.repaint();
			//音效
			SoundEffect.LIGHT.play();
		}
	}
	//==========================以下是各个界面间的跳转方法==============================
	/**
	 * 从开始界面或者DIY界面跳转至选关界面
	 */
	public void toSelectMission() {
		this.frameTotal.musicStart.stop();
		SoundEffect.ENTER.play();
		if(this.panelStartGame != null){
			this.frameTotal.remove(this.panelStartGame);
			this.panelStartGame = null;
		}else if(this.panelSelectDIY != null){
			this.frameTotal.remove(this.panelSelectDIY);
			this.panelSelectDIY = null;
		}
		
		this.frameTotal.initPanelSelectMission();
	}
	
	/**
	 * 从通关界面返回至选关界面
	 */
	public void returnFromWin() {
		this.panelGame.closeFrameWin();
		SoundEffect.ENTER.play();
		this.frameTotal.remove(this.panelGame);
		this.panelGame = null;
		this.frameTotal.initPanelSelectMission();
	}
	
	/**
	 * 从游戏界面返回选关界面
	 */
	public void returnFromGame() {
		this.panelGame.closeFrameWin();
		this.frameTotal.remove(this.panelGame);
		this.panelGame.clock.stop();
		SoundEffect.ENTER.play();
		this.panelGame = null;
		this.frameTotal.initPanelSelectMission();
	}
	
	/**
	 * 从选关界面返回至开始界面
	 */
	public void returnToStart() {
		SoundEffect.ENTER.play();	
		this.frameTotal.musicSelect.stop();
		if(this.panelSelectMission != null){
			this.frameTotal.remove(this.panelSelectMission);
			this.panelSelectMission = null;
		}else if(this.panelSelectDIY != null){
			this.frameTotal.remove(this.panelSelectDIY);
			this.panelSelectDIY = null;
		}
		this.frameTotal.initPanelStartGame();
	}
	
	/**
	 * 进入自定义界面
	 */
	public void toSelectDIY() {
		this.frameTotal.musicStart.stop();
		SoundEffect.ENTER.play();
		if(this.panelSelectMission != null){
			this.frameTotal.remove(this.panelSelectMission);
			this.panelSelectMission = null;
		}else if(this.panelEdit != null){
			this.frameTotal.remove(this.panelEdit);
			this.panelEdit = null;
		}		
		this.frameTotal.initPanelGameSelectDIY();
	}
	
	/**
	 * 进入关卡设计界面
	 */
	public void toPanelEdit() {
		this.gameData =new GameData("edit");
		SoundEffect.ENTER.play();
		this.frameTotal.musicSelect.stop();
		this.frameTotal.remove(this.panelSelectDIY);
		this.panelSelectDIY = null;
		this.frameTotal.initPanelEdit(this.gameData);		
	}
	
	/**
	 * 打开帮助界面
	 */
	public void openFrameHelp(){
		SoundEffect.ENTER.play();
		this.panelStartGame.jbtHelp.setVisible(false);
		this.panelStartGame.jbtQuit.setVisible(false);
		this.panelStartGame.openFrameHelp();
		
	}
	
	/**
	 * 关闭帮助界面
	 */
	public void closeFrameHelp() {
		SoundEffect.ENTER.play();
		this.panelStartGame.closeFrameHelp();
		this.panelStartGame.jbtHelp.setVisible(true);
		this.panelStartGame.jbtQuit.setVisible(true);
	}

	/**
	 * 进行下一关
	 */
	public void nextLevel() {
		//关闭通关界面
		this.panelGame.closeFrameWin();
		SoundEffect.ENTER.play();
		//重新建立单局游戏数据
		this.gameData = new GameData(this.gameData.nextLevel(this.gameData.getFileName()));
		//移除原有的游戏界面
		this.frameTotal.remove(this.panelGame);
		this.panelGame = null;
		//下一关游戏界面
		this.frameTotal.initPanelGame(this.gameData);
	}
	
	/**
	 * 退出游戏
	 */
	public void Quit() {
		SoundEffect.ENTER.play();
		FrameTotal.TOTALDATA.saveData();
		this.frameTotal.removeAll();
		this.frameTotal.dispose();
		System.exit(0);
	}

	/**
	 * 从选关界面进入进入游戏界面
	 * @param level
	 */
	public void toGameLevel(String fileName) {		
		SoundEffect.ENTER.play();
		this.gameData =new GameData(fileName);
		this.frameTotal.musicSelect.stop();
		if(this.panelSelectMission != null){
			this.frameTotal.remove(this.panelSelectMission);
			this.panelSelectMission = null;
		}else if(this.panelSelectDIY != null){
			this.frameTotal.remove(this.panelSelectDIY);
			this.panelSelectDIY = null;
		}
		if(this.gameData.getFileName() == "edit"){
			this.frameTotal.initPanelEdit(this.gameData);		
		}else{
			this.frameTotal.initPanelGame(this.gameData);	
			this.panelGame.addControl(this);
		}	
	}
	
	/**
	 * 改变界面分辨率
	 * @param resolution 新分辨率
	 */
	public void changeResolution(int resolution){
		if((FrameTotal.TOTALDATA.getResolution() != resolution)&&(this.panelStartGame != null)){
			FrameTotal.TOTALDATA.setResolution(resolution);
			FrameTotal.TOTALDATA.saveData();
			this.frameTotal.dispose();
			this.frameTotal = new FrameTotal(this);
		}
	}

	public void printPlanetLocation() {
		System.out.println("地球 :x="+this.gameData.getPlanetEarth().getLocationX()+"||y="+this.gameData.getPlanetEarth().getLocationY());
		System.out.println("三体 :x="+this.gameData.getPlanetThreeBody().getLocationX()+"||y="+this.gameData.getPlanetThreeBody().getLocationY());
		for (int i = 0; i < this.gameData.getPlanetReflections().size(); i++) {
			System.out.println("反射"+i+":x="+this.gameData.getPlanetReflections().get(i).getLocationX()+"||y="+this.gameData.getPlanetReflections().get(i).getLocationY());
		}
		for (int i = 0; i < this.gameData.getPlanetRefractions().size(); i++) {
			System.out.println("折射"+i+":x="+this.gameData.getPlanetRefractions().get(i).getLocationX()+"||y="+this.gameData.getPlanetRefractions().get(i).getLocationY());
		}
		for (int i = 0; i < this.gameData.getPlanetBlackHoles().size(); i++) {
			System.out.println("黑洞"+i+":x="+this.gameData.getPlanetBlackHoles().get(i).getLocationX()+"||y="+this.gameData.getPlanetBlackHoles().get(i).getLocationY());
		}
	}
	
	//存储编辑界面的游戏设计，输出成xml文件
	public void saveData() {
		this.panelEdit.saveData();
	}

	//向编辑界面中加入星球
	public void addPlanet(char planetTag) {
		this.panelEdit.addPlanet(planetTag);
	}
	
	//删除编辑界面中的星球
	public void deletePlanet(char planetTag) {
		this.panelEdit.deletePlanet(planetTag);
	}
}