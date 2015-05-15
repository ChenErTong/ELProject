package gamecomponent;

import gamedata.GameData;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 * 抽象类：星球，包括位置，半径，图片等参数
 * 修改1：干脆这个类继承jbutton好了，要不然java只能继承一个，button属性就不好体现了； by CX 2015.4.13
 * 修改2：1、把原来的image改为imageicon了；2、增添了一个方法  by CX 2015.4.15
 * @author 恩哥哥
 * 2015.4.8.
 */
public abstract class Planet extends JButton {
	
	private static final long serialVersionUID = 1L;
	//圆心位置坐标
	protected int locationX;
	protected int locationY;
	//半径
	protected int radius;
	protected int virtualRadius;
	//图片
	protected ImageIcon planetImg;
	//光线坐标
	protected int lightX;
	protected int lightY;
	protected double directX;
	protected double directY;
	//星球在配置文件中的编号
	protected int tag;
	//游戏数据
	protected GameData gameData;
	
	protected static boolean GAMECONTINUE = true;
	/**
	 * 将图片缩放到指定形式
	 * @author CX
	 * @param filename路径名
	 * @param width期望的宽度
	 * @param height期望的高度
	 * @return 缩放后的图像；为imageicon格式
	 */
	public static ImageIcon getImageIcon(String filename,int width,int height){
		ImageIcon temp=new ImageIcon(filename);
		Image alsoTemp=temp.getImage();
		alsoTemp=alsoTemp.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
		temp.setImage(alsoTemp);
		return temp;
	}
	
	public void getLight(Light light){
		this.lightX = light.getEndX();
		this.lightY = light.getEndY();
		this.directX=light.getDirectX();
		this.directY=light.getDirectY();
	}

	public void setLocationX(int locationX) {
		this.locationX = locationX;
	}

	public void setLocationY(int locationY) {
		this.locationY = locationY;
	}

	public int getLocationX() {
		return locationX;
	}

	public int getLocationY() {
		return locationY;
	}

	public int getRadius() {
		return radius;
	}
	
	public int getTag() {
		return tag;
	}

	public static boolean isGAMECONTINUE() {
		return GAMECONTINUE;
	}

	public static void setGAMECONTINUE() {
		GAMECONTINUE = true;
	}
}