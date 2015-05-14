package gamecomponent;

import java.awt.Point;
import java.util.ArrayList;

import gamedata.GameData;

public class PlanetWormHole implements Runnable{
	private WormHole[] wormHole=new WormHole[2];
	private GameData gameData;
	private int lightX;
	private int lightY;
	private double directX;
	private double directY;
	public PlanetWormHole(int firstX,int firstY,int secondX,int secondY,int radius,GameData gameData){
		wormHole[0]=new WormHole(firstX,firstY,radius);
		wormHole[1]=new WormHole(secondX,secondY,radius);
		this.gameData=gameData;//
		//
		new Thread(this).start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try{
				Thread.sleep(50);
			}
			catch(Exception e){
				System.out.println("CXWorks   此异常抛出于PlanetWormHole的线程");
			}
			//
			ArrayList<Light> lightList = this.gameData.getLightControl().getLightList();
//			System.out.println("aaa");
			if(!lightList.isEmpty()){
				this.getLight(lightList.get(lightList.size() - 1));
				if(checkDistance(wormHole[0].locationX, wormHole[0].locationY, lightX, lightY, wormHole[0].radius)){
					// 将之前的光线停止
					this.gameData.getLightControl().stopLight(
							lightList.get(lightList.size() - 1));
					//
					this.gameData.getLightControl().launchLight(wormHole[1].locationX+wormHole[1].radius, 
							wormHole[1].locationY+wormHole[1].radius, (int)this.directX,(int)this.directY);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
//					System.out.println((wormHole[1].locationX+wormHole[1].radius)+" "+
//							(wormHole[1].locationY+wormHole[1].radius)+" "+(int)this.directX+" "+(int)this.directY);
					
				}
				//
				if(checkDistance(wormHole[1].locationX, wormHole[1].locationY, lightX, lightY, wormHole[1].radius)){
					// 将之前的光线停止
					this.gameData.getLightControl().stopLight(
							lightList.get(lightList.size() - 1));
					//
					this.gameData.getLightControl().launchLight(wormHole[0].locationX+wormHole[0].radius, 
							wormHole[0].locationY+wormHole[0].radius, (int)this.directX, (int)this.directY);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	//
	private void getLight(Light light){
		this.lightX = light.getEndX();
		this.lightY = light.getEndY();
		this.directX=light.getDirectX();
		this.directY=light.getDirectY();
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
	//
	public WormHole getWormHole(){
		return this.wormHole[0];
	}
	public WormHole getAnotherWormHole(){
		return this.wormHole[1];
	}

}
/**
 * 
 * */
//public class WormHole extends Planet{
//	private static final long serialVersionUID = 1L;
//	public WormHole(int x,int y,int Radius){
//		//常规的参数设置
//		this.locationX=x;
//		this.locationY=y;
//		this.radius=Radius;
//		//构造按钮的图片，自动缩放
//		this.planetImg=getImageIcon("image/星球/星球4.png", 2*radius,2*radius);
//		this.setIcon(planetImg);
//		//按钮的位置
//		this.setBounds(locationX, locationY, 2*radius, 2*radius);
//		//设置不打印矩形的内容
//		this.setContentAreaFilled(false);
//		//设置不打印边框
//		this.setBorderPainted(false);
//		//设置可见
//		this.setVisible(true);
//	}
//}