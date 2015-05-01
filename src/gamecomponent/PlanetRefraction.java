package gamecomponent;

import java.awt.Point;
import java.util.ArrayList;

import gamedata.GameData;

public class PlanetRefraction extends Planet implements Runnable {
	private GameData gameData;
	private boolean check;
	public PlanetRefraction(int x,int y,int Radius,GameData gameDAta){
		// 常规的参数设置
		this.locationX = x;
		this.locationY = y;
		this.radius = Radius;
		this.gameData=gameDAta;
		this.check=true;
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
		//
		//
		Thread t=new Thread(this);
		t.start();
		
	}
	//
	public void run(){
		while (check) {
			try {
				Thread.sleep(25);
			} catch (Exception e) {
				// TODO
			}
			ArrayList<Light> lightList = this.gameData.getLightControl().getLightList();
			if (!lightList.isEmpty()) {
				for (int i = 1; i <= lightList.size(); i++) {
					//
					this.getLight(lightList.get(lightList.size() - 1));
					//
					if(checkDistance(this.locationX,this.locationY,this.lightX,this.lightY,this.radius)){
						//
						Point touch=getTouch(this.locationX+radius, locationY+radius, lightX, lightY, radius, directX, directY);
						Point[] launchData=getAll(touch, locationX+radius, locationY+radius, radius, directX, directY);
					}
				}

			}
		}
	}
	//
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
		int answer=(int) (radius-Point.distance(centerX, centerY, lightX, lightY));
		return (answer>-1);
	}
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
	private Point getTouch(int centerX,int centerY,int lightX,int lightY,int radius,double directX,double directY){
		Point answer = null;
		double x,y;
		//
		double a=1+Math.pow(directY, 2)/Math.pow(directX, 2);
		double b=2*directY*lightY/directX-2*Math.pow(directY, 2)*lightX/Math.pow(directX, 2)
				-2*centerY*directY/directX-2*centerX;
		double c=centerX*centerX+lightY*lightY-2*directY*lightX*lightY/directX-2*centerY*lightY+2*centerY*lightX*directY/directX
				+Math.pow(directY*lightX/directX, 2)+centerY*centerY-radius*radius;
		
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
	 * 
	 * @param touch
	 * @param centerX
	 * @param centerY
	 * @param radius
	 * @param directX
	 * @param directY
	 * @return
	 */
	private Point[] getAll(Point touch,int centerX,int centerY,int radius,double directX,double directY){
		Point[] answer=null;
		//获得中心线与x正向夹角
		double beita=getDegreeWithX(centerX-touch.x, centerY-touch.y);
		double seita=getDegreeSpecial(touch, centerX, centerY, directX, directY);
		double aerfa=Math.sin((Math.sin(seita)*0.8));
		double gama=Math.PI+beita-2*aerfa;
		double check=Math.PI-2*aerfa;
		//
		Point a=new Point((int)(centerX+radius*Math.cos(gama)),(int)(centerY+radius*Math.cos(gama)));
		Point b=new Point((int)(centerX-radius*Math.cos(gama)),(int)(centerY-radius*Math.cos(gama)));
		if((getDegreeSpecial(touch, centerX, centerY, centerX-a.x, directY-a.y)-check)<(getDegreeSpecial(touch, centerX, centerY, centerX-b.x, centerY-b.y))){
			answer[0]=a;
			answer[1]=new Point(a.x-centerX,a.y-centerY);
		}
		else{
			answer[0]=b;
			answer[1]=new Point(b.x-centerX,b.y-centerY);
		}
		return answer;
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private double getDegreeWithX(double x,double y){
		double answer;
		answer=Math.atan(y/x);
		if(answer<0)
			answer+=Math.PI;
		return answer;
	}
	/**
	 * 
	 * @param touch
	 * @param centerX
	 * @param centerY
	 * @param directX
	 * @param directY
	 * @return
	 */
	private double getDegreeSpecial(Point touch,int centerX,int centerY,double directX,double directY){
		double answer;
		touch.setLocation(centerX-touch.x, centerY-touch.y);
		answer=Math.acos((touch.x*directX+touch.y*directY)/Math.pow(((directX*directX+directY*directY)*(touch.x*touch.x+touch.y*touch.y)), 0.5));
		
		return answer;
	}
	//

}
