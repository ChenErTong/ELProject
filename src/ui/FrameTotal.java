package ui;

import gamedata.GameData;
import gamedata.TotalData;

import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.sun.awt.AWTUtilities;

import ui_game.PanelGame;
import ui_start.PanelEdit;
import ui_start.PanelSelectDIY;
import ui_start.PanelSelectMission;
import ui_start.PanelStartGame;
import audio.BackgroundMusic;
import control.GameControl;
import control.KeyControl;
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
	public PanelGame panelGame;
	private PanelSelectDIY panelSelectDIY;
	private PanelEdit panelEdit;
	private PanelLoading panelLoading;
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
	//键盘控制器
	private KeyControl keyControl;
	static{
		TOTALDATA = new TotalData();
	}
	public FrameTotal(GameControl gameControl){
		this.setIconImage(new ImageIcon("image/button/LOGO.png").getImage());
		//游戏控制器
		this.gameControl = gameControl;
		//玩家控制器
		this.playerControl = new PlayerControl(gameControl);
				
		try {
			musicStart=new BackgroundMusic("audio/music/Cornfield Chase.wav");
			musicSelect=new BackgroundMusic("audio/music/Cornfield Chase.wav");
			musicGame=new BackgroundMusic("audio/music/Cornfield Chase.wav");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//====================Frame基本参数设定=======================
		//仅仅是为了打印到屏幕中间
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();  
		
		WINDOWW = TOTALDATA.getResolution();
		WINDOWH = (int) (WINDOWW * 0.586);
		if(WINDOWW == 9999){
			WINDOWW = screen.width;
			WINDOWH = screen.height;
		}
	
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
		
		if(FrameTotal.TOTALDATA.getResolution()!=9999){
		AWTUtilities.setWindowShape(this,  new RoundRectangle2D.Double(0.0, 0.0, this.getWidth(), this.getHeight(), FrameTotal.WINDOWW*0.02, FrameTotal.WINDOWH*0.04));	
		}
		
		//显示界面
		this.setVisible(true);
		//将界面加入游戏控制器中
		this.gameControl.addFrame(this);
		
		//鼠标指针
		this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
			    Toolkit.getDefaultToolkit().getImage("image/cursor/Arrow.png"), new Point(0, 0),
			    "Slef"));
		
		this.keyControl = new KeyControl(this.gameControl);
		
		Toolkit.getDefaultToolkit().addAWTEventListener(this.keyControl, AWTEvent.KEY_EVENT_MASK);
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
		this.requestFocus();
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
		this.requestFocus();
	}
	
	/**
	 * 初始化loading面板
	 */
	public void initPanelLoading(){
		this.panelLoading=new PanelLoading(this);
		this.add(panelLoading);
		this.panelLoading.setVisible(true);
		this.validate();
		this.gameControl.setPanelSelectMission(this.panelSelectMission);
		this.repaint();
		this.requestFocus();
	}
	public void removePanelLoading(){
		this.remove(panelLoading);
	}
	
	/**
	 * 初始化游戏面板
	 * @param gameData 
	 */
	public void initPanelGame(GameData gameData){
		this.initPanelLoading();
		this.panelGame = new PanelGame(this.musicGame, this.bgmSyncData, this.SoundSyncData, this, gameData);
		this.panelGame.addControl(playerControl);
		this.add(panelGame);
		this.validate();
		this.gameControl.setPanelGame(this.panelGame);
		this.repaint();
		this.requestFocus();
	}
	
	/**
	 * 初始化游戏DIY面板 
	 */
	public void initPanelGameSelectDIY() {
		this.panelSelectDIY = new PanelSelectDIY(this.musicSelect, this.bgmSyncData, this.SoundSyncData, this);
		this.panelSelectDIY.addControl(playerControl);
		this.add(panelSelectDIY);
		this.panelSelectDIY.setVisible(true);
		this.validate();
		this.gameControl.setPanelSelectDIY(this.panelSelectDIY);
		this.repaint();
		this.requestFocus();
	}

	/**
	 * 初始化编辑面板 
	 * @param gameData 
	 */
	public void initPanelEdit(GameData gameData) {
		this.panelEdit = new PanelEdit(this.musicStart, this.bgmSyncData, this.SoundSyncData, this, gameData);
		this.panelEdit.addControl(playerControl);
		this.add(panelEdit);
		this.panelEdit.setVisible(true);
		this.validate();
		this.gameControl.setPanelEdit(this.panelEdit);
		this.repaint();
		this.requestFocus();
	}
	
	/**
	 * 根据比例转换出宽度
	 */
	public static int TRANSFERW(double ratioW){
		return (int)(ratioW * WINDOWW);
	}
	
	/**
	 * 根据比例转换出高度
	 */
	public static int TRANSFERH(double ratioH){
		return (int)(ratioH * WINDOWH);
	}
	
	/**
	 * 得到比例
	 */
	public static double getRatioW(int w){
		return ((double)w / (double)WINDOWW);
	}
	
	public static double getRatioH(int h){
		return ((double)h / (double)WINDOWH);
	}
}