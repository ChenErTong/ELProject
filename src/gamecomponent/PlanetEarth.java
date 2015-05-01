
//package ui_game;
/**
 * 地球，我们伟大的母亲
 * 图片只好先盗图了
 * @author CX
 *2015.4.13
 */
//=======
package gamecomponent;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import control.PlayerControl;

public class PlanetEarth extends Planet{
	/**
	 * 构造方法：构造地球，并且添加内置的监听器
	 * @param x 地球的水平坐标
	 * @param y 地球的竖直坐标
	 * @param Radius 地球的半径
	 * @author CX
	 */
	public static int lightX;
	public static int lightY;
	
	public PlanetEarth(int x,int y,int Radius){
		//常规的参数设置
		this.locationX=x;
		this.locationY=y;
		this.radius=Radius;
		//构造按钮的图片，自动缩放
		this.planetImg=Planet.getImageIcon("image/星球/星球2.png", 2*radius, 2*radius);
		this.setIcon(planetImg);
		//按钮的位置
		this.setBounds(locationX, locationY, 2*radius, 2*radius);
		//设置不绘制矩形的内容
		this.setContentAreaFilled(false);
		//设置不绘制边框
		this.setBorderPainted(false);
		//设置可见
		this.setVisible(true);
		//
		lightX=this.locationX+this.radius;
		lightY=this.locationY+this.radius;
	}
//	public static int getLightX(){
//		
//		return ;
//	}
//	public int getLightY(){
//		return this.locationY+this.radius;
//	}
}