package gamecomponent;

import java.awt.Point;
import java.util.ArrayList;

import gamedata.GameData;

public class PlanetReflection extends Planet implements Runnable {
	
	private GameData gameData;
	private boolean check;
	public PlanetReflection(int x,int y,int Radius,GameData gameDAta){
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
	
	public void run(){
		while (check) {
			try {
				Thread.sleep(25);
			} catch (Exception e) {
				// TODO
			}
			// System.out.println("aa");
			ArrayList<Light> lightList = this.gameData.getLightControl().getLightList();
			if (!lightList.isEmpty()) {
				this.getLight(lightList.get(lightList.size() - 1));
				if (checkReflection(locationX, locationY, lightX, lightY,radius)) {
					this.gameData.getLightControl().stopLight(lightList.get(lightList.size() - 1));
					Point location=getLocation(locationX+radius, locationY+radius, lightX, lightY, radius, directY, directX);
					Point direct=getDirection(locationX+radius, locationY+radius, directX, directY, location.x, location.y);
					System.out.println(direct.x+" "+direct.y);
					//TODO finish it
//					this.gameData.getLightControl().launchLight(location.x, location.y, 10,7);
//					this.setVisible(false);
					check=false;
				}
			}
		}
	}
	//
	private boolean checkReflection(int centerX,int centerY,int lightX,int lightY,int radius){
		int answer=(int) (radius-Point.distance(centerX+radius, centerY+radius, lightX, lightY));
		return (answer>-1);
	}
	//
	private Point getLocation(int centerX,int centerY,int lightX,int lightY,int radius,double directX,double directY){
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
	//
	private Point getDirection(int centerX,int centerY,double directX,double directY,int intersectionX,int intersectionY){
		Point answer=null;
		double sita=getDegreeWithX(centerX-intersectionX, centerY-intersectionY);
		double aerfa=getDegreeWithX(directX, directY);
		double beita=2*sita-aerfa;
		if(beita<0)
			beita+=Math.PI;
		System.out.println(aerfa+" "+beita);
		//
		Point a=new Point(intersectionX+50,(int)(intersectionY+50*Math.tan(beita)));
		Point b=new Point(intersectionX-50,(int)(intersectionY-50*Math.tan(beita)));
		if(a.distance(centerX, centerY)>b.distance(centerX, centerY))
			answer=new Point(1000,(int)(1000*Math.tan(beita)));
		else 
			answer=new Point(-1000,(int)(-1000*Math.tan(beita)));
		return answer;
	}
	//
	private double getDegreeWithX(double x,double y){
		double answer;
		answer=Math.atan(y/x);
		if(answer<0)
			answer+=Math.PI;
		return answer;
	}

}
