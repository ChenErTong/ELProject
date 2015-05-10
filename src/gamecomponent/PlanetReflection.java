package gamecomponent;

import java.awt.Point;
import java.util.ArrayList;

import gamedata.GameData;

/**
 * 反射星球   2015.5.1
 * @author CXWorks
 * 
 */
public class PlanetReflection extends Planet implements Runnable {
	//要从gameData获取数据
	private GameData gameData;
	/**
	 * 构造反射的星球
	 * @param x x坐标
	 * @param y y坐标
	 * @param Radius 星球半径
	 * @param gameData 必须导入gameData来获取数据
	 */
	public PlanetReflection(int x,int y,int Radius,GameData gameData){
		// 常规的参数设置
		this.locationX = x;
		this.locationY = y;
		this.radius = Radius;
		this.gameData=gameData;
		// 构造按钮的图片，自动缩放
		this.planetImg = this.getImageIcon("image/星球/星球1.png", 2 * radius,2 * radius);
		this.setIcon(planetImg);
		// 按钮的位置
		this.setBounds(locationX, locationY, 2 * radius, 2 * radius);
		// 设置不绘制矩形的内容
		this.setContentAreaFilled(false);
		// 设置不绘制边框
		this.setBorderPainted(false);
		// 设置可见
		this.setVisible(true);
		//开始反射检测的线程
		Thread t=new Thread(this);
		t.start();
		
	}
	/**
	 * 检测、反射的线程
	 */
	public void run(){
		while (true) {
			try {
				Thread.sleep(25);
			} catch (Exception e) {
				// TODO
			}
//			System.out.println("aaa");
			//获取当前所有光线
			ArrayList<Light> lightList = this.gameData.getLightControl().getLightList();
			
			if (!lightList.isEmpty()) {
				this.getLight(lightList.get(lightList.size() - 1));
				// 检测是否发生接触
				if (checkDistance(locationX, locationY, lightX, lightY, radius)) {
					
					// 获得光线与圆的交点，同时也是新光线的起始点
					Point location = getLocation(locationX + radius, locationY
							+ radius, lightX, lightY, radius - 2, directY,
							directX);
//					System.out.println("bbb"+location);
					// 获得新光线的方向
					Point direct = getDirection(locationX + radius, locationY
							+ radius, directX, directY, location.x, location.y);
					// TODO finish it
					// System.out.println(location);
					if(location.equals(new Point(0,0))){
						
					}
					else{
						// 将之前的光线停止
						this.gameData.getLightControl().stopLight(
								lightList.get(lightList.size() - 1));
						this.gameData.getLightControl().launchLight(location.x,
							location.y, direct.x, direct.y);
					}
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
	//
	/**
	 * 用于计算光线与星球的交点
	 * @param centerX 星球中心的x坐标
	 * @param centerY 星球中心的y坐标
	 * @param lightX 光线顶点的x坐标
	 * @param lightY 光线顶点的y坐标
	 * @param radius 星球半径
	 * @param directX 光线x方向的向量
	 * @param directY 光线y方向的向量
	 * @return Point类型的交点
	 */
	private Point getLocation(int centerX,int centerY,int lightX,int lightY,int radius,double directX,double directY){
		Point answer = null;
		double x,y;
		//
		double a=1+Math.pow(directY, 2)/Math.pow(directX, 2);
		double b=2*directY*lightY/directX-2*Math.pow(directY, 2)*lightX/Math.pow(directX, 2)
				-2*centerY*directY/directX-2*centerX;
		double c=centerX*centerX+lightY*lightY-2*directY*lightX*lightY/directX-2*centerY*lightY+2*centerY*lightX*directY/directX
				+Math.pow(directY*lightX/directX, 2)+centerY*centerY-radius*radius;
//		System.out.println(lightX+" "+lightY);
//		System.out.println(directX+" "+directY);
//		System.out.println(centerX+" "+centerY);
		if(directX>=0)
			x=(-b-Math.pow(b*b-4*a*c, 0.5))/(2*a);
		else
			x=(-b+Math.pow(b*b-4*a*c, 0.5))/(2*a);
		y=directY*x/directX+lightY-directY*lightX/directX;

		answer=new Point((int)x,(int)y);
		//
		return answer;
	}
	/**
	 * 用于计算新光线的方向
	 * @param centerX 圆心的x坐标
	 * @param centerY 圆心的y坐标
	 * @param directX 原光线的传播方向的x值
	 * @param directY 原光线的传播方向的y值
	 * @param intersectionX 光线与圆的交点x坐标
	 * @param intersectionY 光线与圆的交点y坐标
	 * @return Point类型，为新光线的方向
	 */
	private Point getDirection(int centerX,int centerY,double directX,double directY,int intersectionX,int intersectionY){
		Point answer=null;
		double sita=getDegreeWithX(centerX-intersectionX, centerY-intersectionY);
		double aerfa=getDegreeWithX(directX, directY);
		double beita=2*sita-aerfa;
		if(beita<0)
			beita+=Math.PI;
//		System.out.println(aerfa+" "+beita);
		//
		Point a=new Point(intersectionX+50,(int)(intersectionY+50*Math.tan(beita)));
		Point b=new Point(intersectionX-50,(int)(intersectionY-50*Math.tan(beita)));
		if(a.distance(centerX, centerY)>b.distance(centerX, centerY))
			answer=new Point(1000,(int)(1000*Math.tan(beita)));
		else 
			answer=new Point(-1000,(int)(-1000*Math.tan(beita)));
		return answer;
	}
	/**
	 * 一个辅助性的方法，获得光线与x正方向的夹角
	 * @param x 光线传播的x坐标
	 * @param y 光线传播的y坐标
	 * @return double的夹角值
	 */
	private double getDegreeWithX(double x,double y){
		double answer;
		answer=Math.atan(y/x);
		if(answer<0)
			answer+=Math.PI;
		return answer;
	}
}