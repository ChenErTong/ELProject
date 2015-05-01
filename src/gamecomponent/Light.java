package gamecomponent;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
/**
 * TODO 当方向向量其一为0时的情况还未解决，在于ratio太小，int转换后为0，以及像素单位太大的问题，希望可以通过double参数绘画。
 * @author 恩哥哥
 * 2015.4.14.
 */
public class Light {
	/**
	 * 激光前进的速度
	 */
	private static int SPEED = 10;
	/**
	 * 图片偏移量
	 */
	private static final int PADDING = 7;
	/**
	 * 4个方向的光线图片
	 */
	private static final Image HORIZON = new ImageIcon("image/componnet/Light.png").getImage();
	private static final Image PLUMB = new ImageIcon("image/componnet/Light2.png").getImage();
	private static final Image RIGHTUP = new ImageIcon("image/componnet/Light3.png").getImage();
	private static final Image RIGHTDOWN = new ImageIcon("image/componnet/Light4.png").getImage();
	/**
	 * 初始坐标
	 * 方向向量
	 */
	private int launchX;
	private int launchY;
	private double directX;
	private double directY;
	/**
	 * 光线尽头坐标
	 */
	private int endX;
	private int endY;
	/**
	 * 图片上初始绘画的坐标顶点
	 */
	private int initImageX = 0;
	private int initImageY = 0;
	/**
	 * 图片的宽高比,为高/宽
	 */
	private double ratio;
	/**
	 * 是否继续传递
	 */
	private boolean canDeliver = false;
	
	/**
	 * 构造函数
	 * @param launchX 左下角X
	 * @param launchY 左下角Y
	 * @param directX 方向向量X
	 * @param directY 方向向量Y
	 */
	public Light(int launchX, int launchY, double directX, double directY){
		this.launchX = launchX;
		this.launchY = launchY;
		this.directX = directX;
		this.directY = directY;
		
		this.canDeliver = true;
		
		if((this.directX<0)&&(this.directY==0)){
			this.initImageX = HORIZON.getWidth(null);
		}else if((this.directX==0)&&(this.directY<0)){
			this.initImageY = PLUMB.getHeight(null);
		}else if((this.directX>0)&&(this.directY<0)){
			this.initImageY = RIGHTUP.getHeight(null);
		}else if((this.directX<0)&&(this.directY>0)){
			this.initImageX = RIGHTUP.getWidth(null);
		}else if((this.directX<0)&&(this.directY<0)){
			this.initImageX = RIGHTDOWN.getWidth(null);
			this.initImageY = RIGHTDOWN.getHeight(null);
		}
		if(this.directX!=0){
			this.ratio = (double)this.directY/this.directX;
		}
	}
	/**
	 * 光线传递
	 */
	public void deliverLight(){
		if(this.directX>0){
			this.directX = this.directX+SPEED;
		} else if(this.directX<0){
			this.directX = this.directX-SPEED;
		}
		
		if(this.directX!=0){
			this.directY = this.directX*this.ratio;
		}else{
			if(this.directY>0){
				this.directY = this.directY+SPEED;
			} else if(this.directY<0){
				this.directY = this.directY-SPEED;
			}
		}
		
		//记录光线尽头坐标
		this.endX = this.launchX+(int)this.directX;
		this.endY = this.launchY+(int)this.directY;
	}
	//该光线是否能够继续传递
	public boolean isCanDeliver() {
		return canDeliver;
	}
	//停止光线传递
	public void stopDeliver(){
		this.canDeliver = false;
	}
	public int getEndX() {
		return endX;
	}
	public int getEndY() {
		return endY;
	}
	
	public double getDirectX() {
		return directX;
	}
	public double getDirectY() {
		return directY;
	}
	/**
	 * 绘制光线
	 * 绘制光线
	 * @param g 画笔
	 */
	public void paint(Graphics g){
		if((this.directX>0)&&(this.directY==0)){
			g.drawImage(HORIZON, this.launchX, this.launchY, this.launchX+(int)this.directX, this.launchY+PADDING, this.initImageX, this.initImageY, this.initImageX+(int)this.directX, this.initImageY+PADDING, null);
		}else if((this.directX<0)&&(this.directY==0)){
			g.drawImage(HORIZON, this.launchX+(int)this.directX, this.launchY-PADDING, this.launchX, this.launchY, this.initImageX+(int)this.directX, this.initImageY, this.initImageX, this.initImageY+PADDING, null);
		}else if((this.directX==0)&&(this.directY>0)){
			g.drawImage(PLUMB, this.launchX-PADDING, this.launchY, this.launchX, this.launchY+(int)this.directY, this.initImageX, this.initImageY, this.initImageX+PADDING, this.initImageY+(int)this.directY, null);
		}else if((this.directX==0)&&(this.directY<0)){
			g.drawImage(PLUMB, this.launchX, this.launchY+(int)this.directY, this.launchX+PADDING, this.launchY, this.initImageX, this.initImageY+(int)this.directY, this.initImageX+PADDING, this.initImageY, null);
		}else if((this.directX>0)&&(this.directY>0)){
			g.drawImage(RIGHTDOWN, this.launchX, this.launchY, this.launchX+(int)this.directX, this.launchY+(int)this.directY, this.initImageX, this.initImageY, this.initImageX+(int)this.directX, this.initImageY+(int)this.directX, null);
		}else if((this.directX<0)&&(this.directY<0)){
			g.drawImage(RIGHTDOWN, this.launchX+(int)this.directX, this.launchY+(int)this.directY, this.launchX, this.launchY, this.initImageX+(int)this.directX, this.initImageY+(int)this.directY, this.initImageX, this.initImageY, null);
		}else if((this.directX>0)&&(this.directY<0)){
			g.drawImage(RIGHTUP, this.launchX, this.launchY+(int)this.directY, this.launchX+(int)this.directX, this.launchY, this.initImageX, this.initImageY+(int)this.directY, this.initImageX+(int)this.directX, this.initImageY, null);
		}else if((this.directX<0)&&(this.directY>0)){
			g.drawImage(RIGHTUP, this.launchX+(int)this.directX, this.launchY, this.launchX, this.launchY+(int)this.directY, this.initImageX+(int)this.directX, this.initImageY, this.initImageX, this.initImageY+(int)this.directY, null);
		}
	}
}