package gamecomponent;

import java.awt.Point;
import java.util.ArrayList;

import gamedata.GameData;
/**
 * 折射星云    2015.5.1
 * @author CXWorks
 *
 */
public class PlanetRefraction extends Planet implements Runnable {
	//也要从gameData获取数据
	private GameData gameData; 
	private int lastLightX,lastLightY;
	private boolean lock=true;
	/**
	 * 构造折射的星球
	 * @param x x坐标
	 * @param y y坐标
	 * @param Radius 星球半径
	 * @param gameDAta 必须导入gameData来获取数据
	 */
	public PlanetRefraction(int x,int y,int Radius,GameData gameDAta){
		// 常规的参数设置
		this.locationX = x;
		this.locationY = y;
		this.radius = Radius;
		this.gameData=gameDAta;
		// 构造按钮的图片，自动缩放
		this.planetImg = this.getImageIcon("image/星球/星球3.png", 2 * radius,2 * radius);
		this.setIcon(planetImg);
		// 按钮的位置
		this.setBounds(locationX, locationY, 2 * radius, 2 * radius);
		// 设置不绘制矩形的内容
		this.setContentAreaFilled(false);
		// 设置不绘制边框
		this.setBorderPainted(false);
		// 设置可见
		this.setVisible(true);
		//新建、开始折射线程
		Thread t=new Thread(this);
		t.start();
		
	}
	
	/**
	 * 开始检测、折射的线程
	 */
	public void run(){
		while (true) {
			try {
				Thread.sleep(25);
			} catch (Exception e) {
				// TODO
			}
			ArrayList<Light> lightList = this.gameData.getLightControl().getLightList();
			if (!lightList.isEmpty()) {
				lastLightX = lightX;
				lastLightY = lightY;
				this.getLight(lightList.get(lightList.size() - 1));
				//
				if (checkDistance(this.locationX + radius, this.locationY
						+ radius, this.lightX, this.lightY, this.radius)) {
					//
					this.gameData.getLightControl().stopLight(
							lightList.get(lightList.size() - 1));
					//
					Point touch = getTouch(this.locationX + radius, locationY
							+ radius, lightX, lightY, radius, directX, directY);
//					System.out.println((this.locationX + radius) + "   "
//							+ (locationY + radius));
					Point[] launchData = getAll(touch, locationX + radius,
							locationY + radius, directX, directY, radius);

					// System.out.println(launchData[0]);
					// System.out.println(launchData[1]);
					this.gameData.getLightControl().launchLight(
							launchData[0].x, launchData[0].y, launchData[1].x,
							launchData[1].y);
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
		if(directX==0){
			double y=Math.pow((radius*radius-Math.pow(centerX-lightX, 2)), 0.5);
			if(lastLightY>centerY)
				return new Point(lightX,centerY+(int)y);
			else
				return new Point(lightX,centerY-(int)y);
		}
		//
		if(directY==0){
			double x=Math.pow((radius*radius-Math.pow(centerY-lightY, 2)), 0.5);
			if(lastLightX>centerX)
				return new Point(centerX+(int)x,lightY);
			else
				return new Point(centerX-(int)x,lightY);
		}
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
		if(answer.equals(new Point (0,0))||radius-answer.distance(centerX, centerY)>4)
			answer=this.binarySearch(new Point(lastLightX,lastLightY),new Point(lightX,lightY), 0);
		//
		return answer;
	}
	
	/**
	 * 获取新光线的所有数据
	 * @param touch 光线与星球交点
	 * @param centerX 星球圆心x
	 * @param centerY 星球圆心y
	 * @param directX 光线传播方向x
	 * @param directY 光线传播方向y
	 * @param radius 星球半径
	 * @return 长度为2的point数组，point[0]为新光线起始点，point[1]为新光线传播方向
	 */
	private Point[] getAll(Point touch,int centerX,int centerY,double directX,double directY,int radius){
		Point[] answer=new Point[2];
		boolean clock;
		//
		double seita=Math.acos((directX*(centerX-touch.x)+directY*(centerY-touch.y))/
				Math.pow(((directX*directX+directY*directY)*((centerX-touch.x)*(centerX-touch.x)+(centerY-touch.y)*(centerY-touch.y))), 0.5));
		//
		double aerfa=Math.asin(Math.sin(seita)*0.6);
		//
		int instruction=getInstruction(touch, centerX, centerY);
//		System.out.println(instruction);
		//
		double derta=getDerta(touch, centerX, centerY, radius, instruction);
//		System.out.println("     "+derta);
		//
//		double centerDegree=Math.atan((touch.y-centerY)/(touch.x-centerX));
//		if(centerDegree<0)
//			centerDegree+=Math.PI;
//		System.out.println(centerDegree);
		//
//		double lightDegree=Math.atan(directY/directX);
//		if(lightDegree<0)
//			lightDegree+=Math.PI;
		//
		if(((centerX-touch.x)*directY-(centerY-touch.y)*directX)<0){
			derta+=Math.PI-2*aerfa;
			clock=true;
		}
		else{
			derta-=(Math.PI-2*aerfa);
			clock=false;
		}
		//
//		System.out.println(clock);
		//
		answer[0]=new Point((int)(centerX+radius*Math.cos(derta)),(int)(centerY+radius*Math.sin(derta)));
		if(Math.abs(touch.x-centerX)<3||Math.abs(touch.y-centerY)<3){
			answer[1]=new Point((int)directX,(int)directY);
			return answer;
		}
		double line=getDirection(answer[0], centerX, centerY, clock, seita);
		//
		answer[1]=finalCheck(answer[0], centerX, centerY, line);
		
		return answer;
	}
	/**
	 * 获取交点在哪一个象限
	 * @param touch 交点
	 * @param centerX 星球圆心x
	 * @param centerY 星球圆心y
	 * @return 1、2、3、4分别代表一二三四象限
	 */
	private int getInstruction(Point touch,int centerX,int centerY){
		int answer;
		if(touch.x>=centerX){
			if(touch.y>centerY)
				answer=1;
			else
				answer=4;
		}
		else{
			if(touch.y>=centerY)
				answer=2;
			else
				answer=3;
		}
		return answer;
	}
	//
	/**
	 * 获得交点的极坐标的θ值，在0~2π之间
	 * @param touch 交点
	 * @param centerX 星球圆心x
	 * @param centerY 星球圆心y
	 * @param radius 星球半径
	 * @param instruction 象限
	 * @return 角度
	 */
	private double getDerta(Point touch,int centerX,int centerY,int radius,int instruction){
		double derta = Math.asin((touch.y - centerY) / (double) radius);
		if (instruction == 2)
			derta = Math.PI - derta;
		else if (instruction == 3)
			derta = Math.PI - derta;
		else if (instruction == 4)
			derta += Math.PI * 2;
		return derta;
	}
	/**
	 * 获得新光线的方向
	 * @param point
	 * @param centerX
	 * @param centerY
	 * @param clock 
	 * @param seita
	 * @return
	 */
	private double getDirection(Point point,int centerX,int centerY,boolean clock,double seita){
		double answer;
		answer=Math.asin((point.y-centerY)/(double)radius);
		if(answer<0)
			answer+=Math.PI;
		if(clock)
			answer+=seita;
		else
			answer-=seita;
		return answer;
	}
	/**
	 * 决定最后取值
	 * @param point
	 * @param centerX
	 * @param centerY
	 * @param line
	 * @return
	 */
	private Point finalCheck(Point point,int centerX,int centerY,double line){
		Point answer;
		Point a=new Point(point.x+50,(int)(point.y+50*Math.tan(line)));
		Point b=new Point(point.x-50,(int)(point.y-50*Math.tan(line)));
		if(a.distance(centerX, centerY)>b.distance(centerX, centerY))
			answer=new Point(1000,(int)(1000*Math.tan(line)));
		else 
			answer=new Point(-1000,(int)(-1000*Math.tan(line)));
		return answer;
	}
	//
	private Point binarySearch(Point first,Point second,int times) {
		if(times>1000){
			System.out.println("times");
			return first;
		}
		else{
			Point half = new Point();
			half.setLocation(((first.x+second.x)/2),((first.y+second.y)/2));
			int distance=(int) half.distance(locationX + radius, locationY + radius);
			if (distance - radius > 1)
				return binarySearch(half, second,++times);
			else if (distance - radius < -1)
				return binarySearch(first, half,++times);
			else
				return half;
		}
		
	}
}