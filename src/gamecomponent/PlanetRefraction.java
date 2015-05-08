package gamecomponent;

import java.awt.Point;
import java.util.ArrayList;

import gamedata.GameData;
/**
 * ��������    2015.5.1
 * @author CXWorks
 *
 */
public class PlanetRefraction extends Planet implements Runnable {
	//ҲҪ��gameData��ȡ����
	private GameData gameData; 
	/**
	 * �������������
	 * @param x x����
	 * @param y y����
	 * @param Radius ����뾶
	 * @param gameDAta ���뵼��gameData����ȡ����
	 */
	public PlanetRefraction(int x,int y,int Radius,GameData gameDAta){
		// ����Ĳ�������
		this.locationX = x;
		this.locationY = y;
		this.radius = Radius;
		this.gameData=gameDAta;
		// ���찴ť��ͼƬ���Զ�����
		this.planetImg = this.getImageIcon("image/����/����1.png", 2 * radius,2 * radius);
		this.setIcon(planetImg);
		// ��ť��λ��
		this.setBounds(locationX, locationY, 2 * radius, 2 * radius);
		// ���ò����ƾ��ε�����
		this.setContentAreaFilled(false);
		// ���ò����Ʊ߿�
		this.setBorderPainted(false);
		// ���ÿɼ�
		this.setVisible(true);
		//�½�����ʼ�����߳�
		Thread t=new Thread(this);
		t.start();
		
	}
	
	/**
	 * ��ʼ��⡢������߳�
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
				for (int i = 1; i <= lightList.size(); i++) {
					//
					this.getLight(lightList.get(lightList.size() - 1));
					//
					if(checkDistance(this.locationX+radius,this.locationY+radius,this.lightX,this.lightY,this.radius)){
						//
						this.gameData.getLightControl().stopLight(
								lightList.get(lightList.size() - i));
						//
						Point touch=getTouch(this.locationX+radius, locationY+radius, lightX, lightY, radius, directX, directY);
						System.out.println((this.locationX+radius)+"   "+(locationY+radius));
						Point[] launchData=getAll(touch, locationX+radius, locationY+radius, directX, directY, radius);
						
//						System.out.println(launchData[0]);
//						System.out.println(launchData[1]);
						this.gameData.getLightControl().launchLight(launchData[0].x, launchData[0].y, launchData[1].x, launchData[1].y);
					}
				}

			}
		}
	}
	
	/**
	 * ���ڼ����߶���������ľ��룬�ж��Ƿ�Ӵ�
	 * @param centerX ����ť���ĵ�x���꣬����locationX+radius
	 * @param centerY ����ť���ĵ�y���꣬����locationY+radius
	 * @param lightX ���߶���x����
	 * @param lightY ���߶���y����
	 * @param radius ����뾶
	 * @return booleanֵ��true���������Ӵ���false����δ�����Ӵ�
	 */
	private boolean checkDistance(int centerX,int centerY,int lightX,int lightY,int radius){
		int answer=(int) (radius-Point.distance(centerX, centerY, lightX, lightY));
		return (answer>-1);
	}
	
	/**
	 * ���ڼ������������Ľ���
	 * @param centerX �������ĵ�x����
	 * @param centerY �������ĵ�y����
	 * @param lightX ���߶����x����
	 * @param lightY ���߶����y����
	 * @param radius ����뾶
	 * @param directX ����x���������
	 * @param directY ����y���������
	 * @return Point���͵Ľ���
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
	 * ��ȡ�¹��ߵ���������
	 * @param touch ���������򽻵�
	 * @param centerX ����Բ��x
	 * @param centerY ����Բ��y
	 * @param directX ���ߴ�������x
	 * @param directY ���ߴ�������y
	 * @param radius ����뾶
	 * @return ����Ϊ2��point���飬point[0]Ϊ�¹�����ʼ�㣬point[1]Ϊ�¹��ߴ�������
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
		
		//
		double derta=getDerta(touch, centerX, centerY, radius, instruction);
		System.out.println("     "+derta);
		//
		double centerDegree=Math.atan((touch.y-centerY)/(touch.x-centerX));
		if(centerDegree<0)
			centerDegree+=Math.PI;
		//
		double lightDegree=Math.atan(directY/directX);
		if(lightDegree<0)
			lightDegree+=Math.PI;
		//
		if(Math.abs((centerDegree+seita-lightDegree))>Math.abs((centerDegree-seita-lightDegree))){
			derta+=Math.PI-2*aerfa;
			clock=true;
		}
		else{
			derta-=(Math.PI-2*aerfa);
			clock=false;
		}
		//
		answer[0]=new Point((int)(centerX+radius*Math.cos(derta)),(int)(centerY+radius*Math.sin(derta)));
		double line=getDirection(answer[0], centerX, centerY, clock, seita);
		//
		answer[1]=finalCheck(answer[0], centerX, centerY, line);
		
		return answer;
	}
	/**
	 * ��ȡ��������һ������
	 * @param touch ����
	 * @param centerX ����Բ��x
	 * @param centerY ����Բ��y
	 * @return 1��2��3��4�ֱ����һ����������
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
	 * ��ý���ļ�����Ħ�ֵ����0~2��֮��
	 * @param touch ����
	 * @param centerX ����Բ��x
	 * @param centerY ����Բ��y
	 * @param radius ����뾶
	 * @param instruction ����
	 * @return �Ƕ�
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
	 * ����¹��ߵķ���
	 * @param point
	 * @param centerX
	 * @param centerY
	 * @param clock 
	 * @param seita
	 * @return
	 */
	private double getDirection(Point point,int centerX,int centerY,boolean clock,double seita){
		double answer;
		answer=Math.atan((point.y-centerY)/(double)(point.x-centerX));
		if(answer<0)
			answer+=Math.PI;
		if(clock)
			answer+=seita;
		else
			answer-=seita;
		return answer;
	}
	/**
	 * �������ȡֵ
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
}