package gamecomponent;

import gamedata.GameData;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * 太阳类，本质上没什么可做的，仅仅是一个必经点罢了
 * 还是不省接口了，万一以后有用呢？
 * 2015.4.14
 * @author CX
 */
public class PlanetSun extends Planet implements Runnable{
	private boolean NOW = true;
	private boolean BEFORE = true;
	public int count = 0;

	/**
	 * 同planetearth类
	 * @param x sun的水平坐标
	 * @param y sun的竖直坐标
	 * @param Radius sun的半径；要比earth大一点哦~
	 */
	public PlanetSun(int x,int y,int Radius,int tag,GameData gameData){
		//常规的参数设置
		this.locationX=x;
		this.locationY=y;
		this.radius=Radius;
		this.tag=tag;
		this.gameData = gameData;
		
		//构造按钮的图片，自动缩放
		this.planetImg=getImageIcon("image/星球/星球6.png", 2*radius,2*radius);
		this.setIcon(planetImg);
		//按钮的位置
		this.setBounds(locationX, locationY, 2*radius, 2*radius);
		//设置不打印矩形的内容
		this.setContentAreaFilled(false);
		//设置不打印边框
		this.setBorderPainted(false);
		//设置可见
		this.setVisible(true);
		
		Thread t = new Thread(this);
		t.start();
	}
	
	public void run() {
		while(true){
			try{
				Thread.sleep(25);
			}
			catch(Exception e){
			}

			ArrayList<Light> lightList = this.gameData.getLightControl().getLightList();
			if(!lightList.isEmpty()){
				this.getLight(lightList.get(lightList.size() - 1));
				if(checkDistance(locationX, locationY, lightX, lightY, radius)){
					NOW = false;
					if(NOW != BEFORE){
						count++;
						BEFORE = NOW;
					}
				}else{
					NOW = true;
					if(NOW != BEFORE){
						count++;
						BEFORE = NOW;
					}
				}
				if(count == 3){
					GAMECONTINUE = false;
				}
				System.out.println(count);
			}
		}		
	}

	/**
	 * 用于检测光线顶点与星球的距离，判断是否接触
	 * @param centerX 星球按钮中心的x坐标，就是locationX+radius
	 * @param centerY 星球按钮中心的y坐标，就是locationY+radius
	 * @param lightX 光线顶点x坐标
	 * @param lightY 光线顶点y坐标
	 * @param radius 星球半径
	 * @return boolean值，true代表发生接触；false代表未发生接触
	 */
	private boolean checkDistance(int centerX,int centerY,int lightX,int lightY,int radius){
		int answer=(int) (radius-Point.distance(centerX+radius, centerY+radius, lightX, lightY));
		return (answer>-1);
	}

	public void setCount() {
		this.count  = 0;
	}

	public void initeCondition() {
		this.count = 0;
		NOW = true;
		BEFORE = true;
	}
}