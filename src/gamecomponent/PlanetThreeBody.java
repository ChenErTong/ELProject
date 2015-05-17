package gamecomponent;

import gamedata.GameData;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class PlanetThreeBody extends Planet {
	/**三体类；即最终的接受点，需要light类的支持
	 * 同planetearth类
	 * @param x sun的水平坐标
	 * @param y sun的竖直坐标
	 * @param Radius sun的半径；
	 */
	public PlanetThreeBody(int x,int y,int Radius,int tag,GameData gameData){
		//常规的参数设置
		this.locationX=x;
		this.locationY=y;
		this.radius=Radius;
		this.tag=tag;
		//构造按钮的图片，自动缩放
		this.planetImg=getImageIcon("image/星球/santi.png", 2*radius,2*radius);
		this.setIcon(planetImg);
		//按钮的位置
		this.setBounds(locationX, locationY, 2*radius,2*radius);
		//设置不打印矩形内容
		this.setContentAreaFilled(false);
		//设置不打印边框
		this.setBorderPainted(false);
		//设置可见
		this.setVisible(true);	
	}

	/**
	 * 当光线抵达时取消光线控制器以及所有光线
	 */
	public void stopLight(LightControl lightControl){
		//得到三体类
		int distanceX = this.locationX+this.radius-this.lightX;
		int distanceY = this.locationY+this.radius-this.lightY;
		double distance = Math.sqrt((double)(distanceX*distanceX+distanceY*distanceY));
		
		if(distance<(double)this.radius){
			lightControl.deleteLights();
		}
	}
}