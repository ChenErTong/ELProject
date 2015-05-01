package ui_game;


import gamecomponent.Light;
import gamecomponent.PlanetEarth;
import gamecomponent.PlanetReflection;
import gamecomponent.PlanetRefraction;
import gamecomponent.PlanetSun;
import gamecomponent.PlanetThreeBody;
import gamedata.GameData;
import gamedata.TotalData;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import ui.BgmSyncData;
import ui.FrameTotal;
import ui.PanelTotal;
import ui.SoundSyncData;
import audio.BackgroundMusic;
import control.PlayerControl;
/**
 * 游戏面板类，并且传入GameData的数据和引入PlayerControl对面板上的操作进行监听，引入线程
 * @author 恩哥哥
 * 2015.4.15.
 */

public class PanelGame extends PanelTotal implements Runnable{
	PlayerControl playerControl;
	FrameWin winFrame;
	
	private TotalData totalData;
	private GameData gameData;
	private PlanetEarth earth;
	private PlanetSun sun;
	private PlanetThreeBody threeBody;
	private PlanetReflection reflection;
	private PlanetRefraction refraction;
	
	private boolean isGameOver;
	
//	private static final Image background=backgroundDemo.getImage();
	public PanelGame(BackgroundMusic bgm, BgmSyncData bgmData,SoundSyncData soundData, TotalData totalData, FrameTotal frameTotal){
		super(bgm, bgmData, soundData, totalData, frameTotal);
		
		this.totalData = totalData;
		//初始化是否结束游戏
		this.isGameOver = false;
		
		this.initButton();
		
		Thread t = new Thread(this);
		t.start();
	}
	/**
	 * TODO 部分按钮图片未到位
	 * TODO 按钮坐标根据比例来
	 * 初始化所有的按钮
	 */
	private void initButton(){
		this.setLayout(null);
		//加入地球
		this.earth=new PlanetEarth(90,90,50);
		this.earth.setActionCommand("earth");
		this.add(earth);
		//加入太阳
		this.sun=new PlanetSun(320,250,100);
		this.add(sun);
		//加入三体
		this.threeBody=new PlanetThreeBody(700, 550, 75);
		this.threeBody.setActionCommand("threeBody");
		this.add(threeBody);
//		//加入反射
//		this.reflection=new PlanetReflection(500, 400, 75, gameData);
//		this.add(reflection);
//		//加入折射
//		this.refraction=new PlanetRefraction(500, 200, 75, gameData);
//		this.add(refraction);
	}
	/**
	 * 加入玩家控制器，对面板操作进行监听
	 * @param playerControl
	 */
	public void addControl(PlayerControl playerControl){
		this.playerControl = playerControl;
		this.earth.addActionListener(this.playerControl);
		this.sun.addActionListener(this.playerControl);
		this.threeBody.addActionListener(this.playerControl);
	}
	/**
	 * 游戏通关，结束游戏
	 * 停止游戏界面线程，开启通关界面
	 */
	private void gameOver(){
		this.isGameOver = true;
		//主窗口失去控制权
		this.frameTotal.setEnabled(false);;
		this.winFrame = new FrameWin(this.playerControl);
	}

	public void run() {
		while(!this.isGameOver){
			try {
				Thread.sleep(25);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			// TODO 判断光线是否进入星球范围内(三体星与工具星不同，三体是删除所有光线而工具星则是静止一条光线并处理后发射另一条光线)
			ArrayList<Light> lightList = this.gameData.getLightControl().getLightList();
			if(!lightList.isEmpty()){
				for (int i = 0; i < lightList.size(); i++) {
					threeBody.getLight(lightList.get(i));
					//如果光线抵达则停止光线前进，反之不进行操作
					threeBody.stopLight(this.gameData.getLightControl());
				}
			}
			this.repaint();
		}	
	}
	
	/**
	 * 绘画游戏面板的各种组件
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		/*
		 * 下面的代码超级绕，有问题还是直接找我好了；by CX
		 * 作用是无论在什么分辨率的情况下图片可以自动的放大缩小，来适应不同的系统
		 * 而且还牵扯到上面的代码
		 * */
		ImageIcon backgroundDemo=new ImageIcon("image/bg/银河.jpg");
		Image background=backgroundDemo.getImage();
		background=background.getScaledInstance(FrameTotal.WINDOWW, FrameTotal.WINDOWH, Image.SCALE_SMOOTH);//缩放图片的核心方法
		backgroundDemo.setImage(background);
		background=backgroundDemo.getImage();
		g.drawImage(background, 0, 0, null);

		//绘画光线链表中所有的光线
		if(this.gameData.getLightControl().getisExist()){
			//若光线控制器存在，说明光线并未到达三体星，游戏继续
			ArrayList<Light> lightList = this.gameData.getLightControl().getLightList();
			for (int i = 0; i < lightList.size(); i++) {
				lightList.get(i).paint(g);
			}	
		}else if(!this.isGameOver){
			//若光线控制器不存在，说明游戏结束，显示通关界面
			this.gameOver();
		}
	}
	public void initGameData(GameData gameData) {
		this.gameData = gameData;
	}
	public void closeFrameWin() {
		//主窗口得到控制权
		this.frameTotal.setEnabled(true);
		this.winFrame.dispose();	
	}
}
