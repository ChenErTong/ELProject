package ui_game;


import gamecomponent.Light;
import gamecomponent.LightControl;
import gamecomponent.PlanetEarth;
import gamecomponent.PlanetSun;
import gamecomponent.PlanetThreeBody;
import gamedata.GameData;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;




import control.PlayerControl;
/**
 * 游戏面板类，并且传入GameData的数据和引入PlayerControl对面板上的操作进行监听，引入线程
 * @author 恩哥哥
 * 2015.4.15.
 */

public class JPanelGame extends JPanel implements Runnable{
	PlayerControl playerControl;
	
	private GameData gameData;
	private PlanetEarth earth;
	private PlanetSun sun;
	//TODO
	public PlanetThreeBody threeBody;
	
	
	public JPanelGame(GameData gameData){
		this.setLayout(null);
		this.gameData = gameData;
		//
		this.earth=new PlanetEarth(90,90,50);
		this.earth.setActionCommand("earth");
		this.add(earth);
		//
		this.sun=new PlanetSun(320,250,100);
		this.sun.setActionCommand("sun");
		this.add(sun);
		//
		this.threeBody=new PlanetThreeBody(700, 550, 75);
		this.threeBody.setActionCommand("threeBody");
		this.add(threeBody);

		Thread t = new Thread(this);
		t.start();
		//
		
	}
	
//	//================Test====================
//	public void Test(){	
//	    testButton.addActionListener(playerControl); 
//	    
//	}

	/**
	 * 加入玩家控制器，对面板操作进行监听
	 * @param playerControl
	 */
	public void addControl(PlayerControl playerControl){
		this.playerControl = playerControl;
		this.earth.addActionListener(playerControl);
		this.sun.addActionListener(playerControl);
		this.threeBody.addActionListener(playerControl);
	}
	
	public void run() {
		while(true){
			try {
				Thread.sleep(25);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			ArrayList<Light> lightList = this.gameData.getLightControl().getLightList();
			if(!lightList.isEmpty()){
				for (int i = 0; i < lightList.size(); i++) {
					threeBody.getLight(lightList.get(i));
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
		int height=768;
		int width=1024;
		/*
		 * 下面的代码超级绕，有问题还是直接找我好了；by CX
		 * 作用是无论在什么分辨率的情况下图片可以自动的放大缩小，来适应不同的系统
		 * 而且还牵扯到上面的代码
		 * */
		ImageIcon backgroundDemo=new ImageIcon("image/bg/银河.jpg");
		Image background=backgroundDemo.getImage();
		background=background.getScaledInstance(width, height, Image.SCALE_SMOOTH);//缩放图片的核心方法
		backgroundDemo.setImage(background);
		background=backgroundDemo.getImage();
		g.drawImage(background, 0, 0, null);
		//绘画光线链表中所有的光线
		
		if(this.gameData.getLightControl().getisExist()){
			ArrayList<Light> lightList = this.gameData.getLightControl().getLightList();
			for (int i = 0; i < lightList.size(); i++) {
				lightList.get(i).paint(g);
			}	
		}	
	}
}
