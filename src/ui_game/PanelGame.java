package ui_game;

import gamecomponent.Light;
import audio.SoundEffect;
import gamecomponent.Planet;
import gamecomponent.PlanetBlackHole;
import gamecomponent.PlanetDragger;
import gamecomponent.PlanetEarth;
import gamecomponent.PlanetThreeBody;
import gamecomponent.PlanetWormHole;
import gamedata.GameData;
import gamedata.TotalData;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import ui.BgmSyncData;
import ui.FrameTotal;
import ui.PanelTotal;
import ui.SoundSyncData;
import audio.BackgroundMusic;
import control.GameControl;
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
	
	/**
	 * 声明长宽
	 */
	private static final int WIDTH = FrameTotal.WINDOWW;
	private static final int HEIGHT = FrameTotal.WINDOWH;
	
	private int count=0;
	private GameData gameData;
	private PlanetEarth earth;
	private PlanetThreeBody threeBody;
	private PlanetBlackHole blackHole;
	private PlanetWormHole wormHole;
	//游戏胜利
	private boolean isGameWin;
	//游戏重新刷新一局
	private boolean isGameRefresh;
	//
	private PlanetDragger[] dragger=new PlanetDragger[2];
	//计时器
	private Clock clock=new Clock();
	private int secPassed;
	//关卡游戏评级
	private int grade;
	//返回按钮
	private JButton returnButton;
	//关闭按钮
	private JButton closeButton;
	
	//返回按钮图片
	private static final ImageIcon BUTTON_RETURN = Planet.getImageIcon("image/button/Return4.png", (int)(WIDTH*0.1), (int)(HEIGHT*0.1));
	//关闭按钮
	private static final ImageIcon BUTTON_CLOSE = Planet.getImageIcon("image/button/关闭按钮.png", (int)(HEIGHT*0.1), (int)(HEIGHT*0.1));
	//背景图片
	private ImageIcon[] backgroundDemo=new ImageIcon[3];
	private Image[] background=new Image[3];
	
	public PanelGame(BackgroundMusic bgm, BgmSyncData bgmData,SoundSyncData soundData, FrameTotal frameTotal, GameData gameData){
		super(bgm, bgmData, soundData, frameTotal);
		this.gameData=gameData;
		//初始化是否结束游戏
		this.isGameWin = false;
		this.isGameRefresh = false;
		
		this.setLayout(null);
		//
		backgroundDemo[0]=new ImageIcon("image/bg/背景1.png");
		backgroundDemo[1]=new ImageIcon("image/bg/背景2.png");
		backgroundDemo[2]=new ImageIcon("image/bg/背景3.png");
		//设置背景图片
		for(int i=0;i<3;i++){
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
		this.returnButton.setBounds((int)(WIDTH*0.88), (int)(HEIGHT*0.9), (int)(WIDTH*0.1), (int)(HEIGHT*0.1));
		this.returnButton.setContentAreaFilled(false);
		this.returnButton.setBorderPainted(false);
		this.returnButton.setActionCommand("ReturnFromGame");
		this.returnButton.setVisible(true);
		this.add(returnButton);
		//加入返回按钮
		this.closeButton = new JButton();
		this.closeButton.setIcon(BUTTON_CLOSE);
		this.closeButton.setBounds((int)(WIDTH*0.88), (int)(HEIGHT*0.2), (int)(HEIGHT*0.1), (int)(HEIGHT*0.1));
		this.closeButton.setContentAreaFilled(false);
		this.closeButton.setBorderPainted(false);
		this.closeButton.setActionCommand("Quit");
		this.closeButton.setVisible(true);
		this.add(closeButton);
		
		//加入地球
		this.earth = this.gameData.getPlanetEarth();
		this.earth.setActionCommand("earth");
		this.add(this.earth);
		
		this.add(this.gameData.getPlanetSun());
		
		//加入三体
		this.threeBody = this.gameData.getPlanetThreeBody();
		this.threeBody.setActionCommand("threeBody");;
		this.add(this.threeBody);
		
		//加入反射
		for (int i = 0; i < this.gameData.getPlanetReflections().size(); i++) {
			dragger[0]=new PlanetDragger(this.gameData.getPlanetReflections().get(i),this);
			this.add(this.gameData.getPlanetReflections().get(i));
		}
		
		//加入折射
		for (int i = 0; i < this.gameData.getPlanetRefractions().size(); i++) {
			dragger[1]=new PlanetDragger(this.gameData.getPlanetRefractions().get(i),this);
			this.add(this.gameData.getPlanetRefractions().get(i));		
		}
		//加入黑洞
		for (int i = 0; i < this.gameData.getPlanetBlackHoles().size(); i++) {
			this.add(this.gameData.getPlanetBlackHoles().get(i));		
		}
		//加入虫洞
		if(this.gameData.haveWornhole){
			this.add(this.gameData.getPlanetWormHole().getWormHole());
			this.add(this.gameData.getPlanetWormHole().getAnotherWormHole());
		}
		
		//加入计时器
		this.add(clock);
		clock.setOpaque(false);
		clock.setBounds(850,200,150,200);
	}
	
	/**
	 * 加入玩家控制器，对面板操作进行监听
	 * @param playerControl
	 */
	public void addControl(PlayerControl playerControl){
		this.playerControl = playerControl;
		this.returnButton.addActionListener(playerControl);
		this.closeButton.addActionListener(playerControl);
		
		this.earth.addActionListener(this.playerControl);
		this.threeBody.addActionListener(this.playerControl);
	}
	
	/**
	 * 游戏通关，结束游戏
	 * 停止游戏界面线程，开启通关界面
	 */
	private void gameOver(){
		int level = this.gameData.getLevel();
		this.computeGrade(this.clock.getSec());
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
		this.frameTotal.setEnabled(false);
		this.winFrame = new FrameWin(this.playerControl, this.grade);
		//计时器停止计时
		this.clock.stop();
	}
	
	/**
	 * 根据时间计算出关卡评级
	 * @param sec 通关时间
	 */
	private void computeGrade(int sec) {
		if (sec<=60){
			this.grade = 5;
		}else if(sec<=120){
			this.grade = 4;
		}else if(sec<=180){
			this.grade = 3;
		}else if(sec<=240){
			this.grade = 2;
		}else if(sec>240){
			this.grade = 1;
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
		this.frameTotal.setEnabled(true);
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
		if((endX<0)||(endX>WIDTH)||(endY<0)||(endY>HEIGHT)){
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
			
			// TODO 判断光线是否进入星球范围内(三体星与工具星不同，三体是删除所有光线而工具星则是静止一条光线并处理后发射另一条光线)
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
	}

	/**
	 * 绘画游戏面板的各种组件
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		//背景图品刷新
		g.drawImage(background[count/8], 0, 0, null);
		count ++;
		if(count>23){
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