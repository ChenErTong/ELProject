package gamecomponent;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import gamedata.GameData;

public class PlanetBlackHole extends Planet implements Runnable{
	private GameData gameData;
	public PlanetBlackHole(int x,int y,int Radius,GameData gameData){
		//常规的参数设置
		this.locationX=x;
		this.locationY=y;
		this.radius=Radius;
		this.gameData=gameData;

		//构造按钮的图片，自动缩放
//		Image temp=new Image().
		this.planetImg=getImageIcon("image/星球/星球6.png", 2*radius, 2*radius);
		this.setIcon(planetImg);
		//按钮的位置
		this.setBounds(locationX, locationY, 2*radius, 2*radius);
		//设置不打印矩形的内容
		this.setContentAreaFilled(false);
		//设置不打印边框
		this.setBorderPainted(false);
		//设置可见
		this.setVisible(true);
		
		Thread t=new Thread(this);
		t.start();
	}

	public void run() {
		while(true){
			try{
				Thread.sleep(25);
			}
			catch(Exception e){
				System.out.println("CXWorks   在PlanetBlackHole线程抛出");
			}

			ArrayList<Light> lightList = this.gameData.getLightControl().getLightList();

			if(!lightList.isEmpty()){
				this.getLight(lightList.get(lightList.size() - 1));
				if(checkDistance(locationX, locationY, lightX, lightY, radius)){
					GAMECONTINUE=false;
				}
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
}
