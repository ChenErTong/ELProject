package ui_game;

import gamecomponent.Light;
import audio.SoundEffect;
import gamecomponent.Planet;
import gamecomponent.PlanetBlackHole;
import gamecomponent.PlanetDragger;
import gamecomponent.PlanetEarth;
import gamecomponent.PlanetReflection;
import gamecomponent.PlanetRefraction;
import gamecomponent.PlanetThreeBody;
import gamecomponent.PlanetWormHole;
import gamedata.GameData;
import gamedata.TotalData;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import ui.BgmSyncData;
import ui.FrameTotal;
import ui.PanelTotal;
import ui.SoundSyncData;
import ui.WindowDragger;
import audio.BackgroundMusic;
import control.GameControl;
import control.KeyControl;
import control.PlanetControl;
import control.PlayerControl;
/**
 * 游戏面板类，并且传入GameData的数据和引入PlayerControl对面板上的操作进行监听，引入线程
 * @author 恩哥哥
 * 2015.4.15.
 */
public class PanelGame extends PanelTotal implements Runnable{
	PlayerControl playerControl;
	GameControl gameControl;
	FrameWin winFrame;
	
	private int count=0;
	private GameData gameData;
	private PlanetEarth earth;
	private PlanetThreeBody threeBody;
	private PlanetBlackHole[] blackHoles;
	private PlanetReflection[] reflections;
	private PlanetRefraction[] refractions;
	private PlanetWormHole wormHole;
	//游戏胜利
	private boolean isGameWin;
	//游戏重新刷新一局
	private boolean isGameRefresh;
	//
	private PlanetDragger[] dragger=new PlanetDragger[2];
	//计时器
	private long totalMillis=180000;
	private Clock clock=new Clock(totalMillis,this);
	//关卡游戏评级
	private int grade;
	//返回按钮
	private JButton returnButton;
	
	//返回按钮图片
	private static final ImageIcon BUTTON_RETURN = Planet.getImageIcon("image/button/Return4.png", (int)(FrameTotal.WINDOWW*0.098), (int)(FrameTotal.WINDOWW*0.020));
	//背景图片
	private ImageIcon[] backgroundDemo=new ImageIcon[16];
	private Image[] background=new Image[16];
	
	public PanelGame(BackgroundMusic bgm, BgmSyncData bgmData,SoundSyncData soundData, FrameTotal frameTotal, GameData gameData){
		super(bgm, bgmData, soundData, frameTotal);
		this.gameData=gameData;
		//初始化是否结束游戏
		this.isGameWin = false;
		this.isGameRefresh = false;
		
		this.setLayout(null);
		
		String address = new String();
		for (int i = 0; i < 16; i++) {
			address = "image/bg/" + (i+1) + ".jpg";
			backgroundDemo[i] = new ImageIcon(address);
		}

		//设置背景图片
		for(int i=0;i<16;i++){
			background[i]=getImage(backgroundDemo[i], this.width, this.height);
		}
		
		//初始化所有按钮
		this.initButton();
		
		Thread thread = new Thread(this);
		thread.start();
	}
	
	/**
	 * TODO 部分按钮图片未到位
	 * TODO 按钮坐标根据比例来
	 * 初始化所有的按钮
	 */
	private void initButton(){
		//加入返回按钮
		this.returnButton = new JButton();
		this.returnButton.setIcon(BUTTON_RETURN);
		this.returnButton.setBounds((int)(FrameTotal.WINDOWW*0.0156), (int)(FrameTotal.WINDOWH*0.015), (int)(FrameTotal.WINDOWW*0.098), (int)(FrameTotal.WINDOWW*0.036));
		this.returnButton.setContentAreaFilled(false);
		this.returnButton.setBorderPainted(false);
		this.returnButton.setActionCommand("ReturnFromGame");
		this.returnButton.setVisible(true);
		this.add(returnButton);
		
		//加入地球
		this.earth = this.gameData.getPlanetEarth();
		this.earth.setActionCommand("earth");;
		this.add(this.earth);
		
		this.add(this.gameData.getPlanetSun());
		
		//加入三体
		this.threeBody = this.gameData.getPlanetThreeBody();
		this.threeBody.setActionCommand("threeBody");;
		this.add(this.threeBody);
		
		//加入反射
		this.reflections = new PlanetReflection[this.gameData.getPlanetReflections().size()];
		for (int i = 0; i < this.reflections.length; i++) {
			this.reflections[i] = this.gameData.getPlanetReflections().get(i);
			PlanetControl pc = new PlanetControl(this.reflections[i]);
			this.reflections[i].addKeyListener(pc);
			dragger[0]=new PlanetDragger(this.reflections[i],this,this.gameData);
			this.add(this.reflections[i]);
		}
		
		//加入折射
		this.refractions = new PlanetRefraction[this.gameData.getPlanetRefractions().size()];
		for (int i = 0; i < this.refractions.length; i++) {
			this.refractions[i] = this.gameData.getPlanetRefractions().get(i);
			PlanetControl pc = new PlanetControl(this.refractions[i]);
			this.refractions[i].addKeyListener(pc);
			dragger[1]=new PlanetDragger(this.refractions[i],this,this.gameData);
			this.add(this.refractions[i]);		
		}
		
		//加入黑洞
		this.blackHoles = new PlanetBlackHole[this.gameData.getPlanetBlackHoles().size()];
		for (int i = 0; i < this.blackHoles.length; i++) {
			this.blackHoles[i] = this.gameData.getPlanetBlackHoles().get(i);
			this.add(this.blackHoles[i]);		
		}
		//加入虫洞
		if(this.gameData.haveWornhole){
			this.add(this.gameData.getPlanetWormHole().getWormHole());
			this.add(this.gameData.getPlanetWormHole().getAnotherWormHole());
		}
		
		//加入计时器
		this.add(clock);

	}
	
	/**
	 * 加入玩家控制器，对面板操作进行监听
	 * @param playerControl
	 */
	public void addControl(PlayerControl playerControl){
		this.playerControl = playerControl;		
		this.returnButton.addActionListener(this.playerControl);
		
		this.earth.addActionListener(this.playerControl);
		this.threeBody.addActionListener(this.playerControl);
	}
	
	/**
	 * 游戏通关，结束游戏
	 * 停止游戏界面线程，开启通关界面
	 */
	public void gameOver(){
		int level = this.gameData.getLevel();
		this.computeGrade(this.clock.getMillis());
		if(FrameTotal.TOTALDATA.getGrade(level) == 0){
			FrameTotal.TOTALDATA.levelUp();
		}
		if(FrameTotal.TOTALDATA.getGrade(level) < this.grade){
			FrameTotal.TOTALDATA.setGrade(level, this.grade);
		}
		this.isGameWin = true;
		//播放过关音效
		SoundEffect.WIN.play();
		//关闭bgm
		this.frameTotal.musicGame.stop();
		//主窗口失去控制权
		WindowDragger.CANDRAGGER = false;
		this.winFrame = new FrameWin(this.playerControl, this.grade);
		this.winFrame.setAlwaysOnTop(true);
		//计时器停止计时
		this.clock.stop();
	}
	
	//time is over, game stops
	public void gameLose(){
		this.grade=0;
		//关闭bgm
		this.frameTotal.musicGame.stop();
		//主窗口失去控制权
		WindowDragger.CANDRAGGER = false;
		this.winFrame = new FrameWin(this.playerControl, this.grade);
		this.winFrame.setAlwaysOnTop(true);
		//计时器停止计时
		this.clock.stop();
	}
	
	/**
	 * 根据时间计算出关卡评级
	 * @param sec 通关时间
	 */
	private void computeGrade(long millis) {
		int sec=(int)(totalMillis-millis);
		if (sec<=60){
			this.grade = 1;
		}else if(sec<=120){
			this.grade = 2;
		}else if(sec<=180){
			this.grade = 3;
		}else if(sec<=240){
			this.grade = 4;
		}else if(sec>240){
			this.grade = 5;
		}
	}
	
	/**
	 * 刷新游戏数据
	 * @param gameData
	 */
	public void initGameData(GameData gameData) {
		this.gameData = gameData;
	}
	
	/**
	 * 关闭通关界面
	 */
	public void closeFrameWin() {
		//主窗口得到控制权
		WindowDragger.CANDRAGGER = true;
		this.winFrame.dispose();	
	}

	/**
	 * 判断某条光线是否射出边界
	 * @param light
	 * @return 若射出边界则返回true，反之返回false
	 */
	public boolean isContactBorder(Light light){
		int endX = light.getEndX();
		int endY = light.getEndY();
		if((endX<0)||(endX>FrameTotal.WINDOWW)||(endY<FrameTotal.WINDOWH*0.082)||(endY>FrameTotal.WINDOWH)){
			return true;
		}
		
		if(!Planet.isGAMECONTINUE()){
			return true;
		}
		
		return false;		
	}
	
	public void run() {
		while((!this.isGameWin)&&(!this.isGameRefresh)){
			try {
				Thread.sleep(25);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			//判断光线是否进入星球范围内(三体星与工具星不同，三体是删除所有光线而工具星则是静止一条光线并处理后发射另一条光线)
			ArrayList<Light> lightList = this.gameData.getLightControl().getLightList();
			if(!lightList.isEmpty()){
				for (int i = 0; i < lightList.size(); i++) {
					if(this.isContactBorder(lightList.get(i))){
						this.refreshGame();
					}
					threeBody.getLight(lightList.get(i));
					//如果光线抵达则停止光线前进，反之不进行操作
					threeBody.stopLight(this.gameData.getLightControl());
				}
			}
			this.repaint();
		}	
	}
	
	/**
	 * 重新刷新一盘游戏
	 */
	private void refreshGame() {
		this.reDrag();
		this.gameData.getLightControl().deleteLights();
		this.gameData.refreshLight();
		Planet.setGAMECONTINUE();
	}

	/**
	 * 绘画游戏面板的各种组件
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		//背景图品刷新
		g.drawImage(background[count/3], 0, 0, null);
		count ++;
		if(count>44){
			count=0;
		}
		
		//绘画光线链表中所有的光线
		if(this.gameData.getLightControl().getisExist()){
			//若光线控制器存在，说明光线并未到达三体星，游戏继续
			ArrayList<Light> lightList = this.gameData.getLightControl().getLightList();
			for (int i = 0; i < lightList.size(); i++) {
				lightList.get(i).paint(g);
			}	
		}else if(!this.isGameWin){
			//若光线控制器不存在，说明游戏结束，显示通关界面
			this.gameOver();
		}
	}
	/**
	 * 控制所有功能星球不可移动
	 */
	public void stopDrag(){
		for(PlanetDragger cell:dragger){
			cell.stop();
		}
	}
	/**
	 * 控制所有功能星球恢复移动
	 */
	private void reDrag(){
		for(PlanetDragger cell:dragger){
			cell.start();
		}
	}

	public void addControl(GameControl gameControl) {
		this.gameControl = gameControl;
	}
	/**
	 * 将图片缩放到指定形式
	 * @author CX
	 * @param filename路径名
	 * @param width期望的宽度
	 * @param height期望的高度
	 * @return 缩放后的图像；为image格式
	 */
	public static Image getImage(ImageIcon temp,int width,int height){
		Image alsoTemp=temp.getImage();
		alsoTemp=alsoTemp.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
		return alsoTemp;
	}
}