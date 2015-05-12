package ui;

import gamedata.GameData;
import gamedata.TotalData;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

import ui_game.PanelGame;
import ui_start.PanelSelectMission;
import ui_start.PanelStartGame;
import audio.BackgroundMusic;
import control.GameControl;
import control.PlayerControl;
/**
 * 抽象类：所有界面的模板，包括界面标题栏标题，大小，位置等信息
 * @author 恩哥哥
 * 2015.4.8.
 * 改动：1、界面大小改为屏幕大小的0.618倍；2、去掉了高度、宽度的静态变量；3、将静态变量全部大写；by CX  2015.4.8
 * 改动：把大小暂时设为固定数值了 by CX 2015.4.15
 * 改动：我把边框给去掉了，由于关闭按钮转交给范爷下，大家这段时间就点小红点玩吧
 */

public class FrameTotal extends JFrame{
	//=================基本参数设定==================
	//Frame的位置坐标
	public static int WINDOWX;
	public static int WINDOWY;
	//窗口大小
	public static int WINDOWW;
	public static int WINDOWH;
	//====================基本组件声明======================
	//声明音效文件
	final BgmSyncData bgmSyncData;
	final SoundSyncData SoundSyncData;
	/*
	 * 声明三个面板
	 */
	private PanelStartGame panelStartGame;
	private PanelSelectMission panelSelectMission;
	private PanelGame panelGame;
	/**
	 * 音乐声明
	 * TODO 未声明音乐素材
	 */
	public BackgroundMusic musicStart;
	public BackgroundMusic musicSelect;
	public BackgroundMusic musicGame;
	//鼠标指针
	//========================游戏逻辑构建===========================
	//游戏数据类
	public static TotalData TOTALDATA;
	//游戏控制器
	private GameControl gameControl;
	//玩家控制器
	private PlayerControl playerControl;
	
	static{
		TOTALDATA = new TotalData();
	}
	public FrameTotal(){
		try {
			musicStart=new BackgroundMusic("audio/music/Cornfield Chase.wav");
			musicSelect=new BackgroundMusic("audio/music/Cornfield Chase.wav");
			musicGame=new BackgroundMusic("audio/music/Cornfield Chase.wav");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//====================Frame基本参数设定=======================
		//仅仅是为了打印到屏幕中间
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();  
		
		WINDOWW = (int) (screen.width * 0.8);
		WINDOWH = (int) (screen.height * 0.8);
		
		this.setSize(WINDOWW, WINDOWH);   
		
		//将界面置于显示屏正中央
		WINDOWX = screen.width-this.getWidth()>>1;
		WINDOWY = (screen.height-this.getHeight()>>1);
		this.setLocation(WINDOWX, WINDOWY);
		//允许鼠标拖动
		new WindowDragger(this,this.getContentPane());
		//====================游戏逻辑构建=======================
		this.initGameLogic();
		//声明音效状态
		this.bgmSyncData=new BgmSyncData();
		this.SoundSyncData=new SoundSyncData();
		
		this.initPanelStartGame();
		this.setUndecorated(true);
		//显示界面
		this.setVisible(true);
		//将界面加入游戏控制器中
		this.gameControl.addFrame(this);
		
		//鼠标指针
		this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
			    Toolkit.getDefaultToolkit().getImage("image/cursor/cur.png"), new Point(0, 0),
			    "Slef"));
	}
	
	/**
	 * 初始化游戏逻辑
	 */
	private void initGameLogic(){
		//游戏控制器
		this.gameControl = new GameControl();
		//玩家控制器
		this.playerControl = new PlayerControl(gameControl);
	}
	
	//======================生成三个panel的方法========================
	/**
	 * 初始化开始面板
	 */
	public void initPanelStartGame(){
		this.panelStartGame = new PanelStartGame(this.musicStart, this.bgmSyncData, this.SoundSyncData, this);
		this.panelStartGame.addControl(playerControl);
		this.add(panelStartGame);
		this.panelStartGame.setVisible(true);
		this.validate();
		this.gameControl.setPanelStartGame(this.panelStartGame);
		this.repaint();
	}
	
	/**
	 * 初始化选关面板
	 */
	public void initPanelSelectMission(){
		this.panelSelectMission = new PanelSelectMission(this.musicSelect, this.bgmSyncData, this.SoundSyncData, this);
		this.panelSelectMission.addControl(playerControl);
		this.add(panelSelectMission);
		this.panelSelectMission.setVisible(true);
		this.validate();
		this.gameControl.setPanelSelectMission(this.panelSelectMission);
		this.repaint();
	}
	
	/**
	 * 初始化游戏面板
	 * @param gameData 
	 */
	public void initPanelGame(GameData gameData){
		this.panelGame = new PanelGame(this.musicGame, this.bgmSyncData, this.SoundSyncData, this,gameData);
		this.panelGame.addControl(playerControl);
		this.add(panelGame);
		this.panelGame.setVisible(true);
		this.validate();
		this.gameControl.setPanelGame(this.panelGame);
		this.repaint();
	}
}