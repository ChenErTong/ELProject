package gamecomponent;

import java.awt.Graphics;
import java.awt.Image;
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
	 * 激光前进的分速度
	 */
	private double SPEEDX;
	/**
	 * 图片偏移量
	 */
	private static final int PADDING = 7;
	/**
	 * 4个方向的光线图片
	 */
	private static final Image LIGHT0 = new ImageIcon("image/componnet/光线1.png").getImage();
	private static final Image LIGHT30 = new ImageIcon("image/componnet/光线2.png").getImage();
	private static final Image LIGHT60 = new ImageIcon("image/componnet/光线3.png").getImage();
	private static final Image LIGHT90 = new ImageIcon("image/componnet/光线4.png").getImage();
	private static final Image LIGHT120 = new ImageIcon("image/componnet/光线5.png").getImage();
	private static final Image LIGHT150 = new ImageIcon("image/componnet/光线6.png").getImage();
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
	 * 方向的宽高比,为高/宽
	 */
	private double ratio;
	/**
	 * 图片的宽高比，为高/宽
	 */
	private double imgRatio;
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
			this.initImageX = LIGHT0.getWidth(null);
		}else if((this.directX==0)&&(this.directY<0)){
			this.initImageY = LIGHT90.getHeight(null);
		}else if((this.directX>0)&&(this.directY<0)&&(this.directX+this.directY>0)){
			this.initImageY = LIGHT150.getHeight(null);
		}else if((this.directX>0)&&(this.directY<0)&&(this.directX+this.directY<=0)){
			this.initImageY = LIGHT120.getHeight(null);
		}else if((this.directX<0)&&(this.directY>0)&&(this.directX+this.directY>=0)){
			this.initImageX = LIGHT120.getWidth(null);
		}else if((this.directX<0)&&(this.directY>0)&&(this.directX+this.directY<0)){
			this.initImageX = LIGHT150.getWidth(null);
		}else if((this.directX<0)&&(this.directY<0)&&this.directX<=this.directY){
			this.initImageX = LIGHT30.getWidth(null);
			this.initImageY = LIGHT30.getHeight(null);
		}else if((this.directX<0)&&(this.directY<0)&&this.directX>this.directY){
			this.initImageX = LIGHT60.getWidth(null);
			this.initImageY = LIGHT60.getHeight(null);
		}
		
		if(this.directX!=0){
			this.ratio = (double)this.directY/this.directX;
			double temp = Math.sqrt(this.directX*this.directX+this.directY*this.directY);
			SPEEDX = SPEED*this.directX/temp;
		}
		
		if(((this.directX>=this.directY)&&(this.directY>0))||((this.directX<=this.directY)&&(this.directY<0))){
			imgRatio = (double)LIGHT30.getHeight(null)/LIGHT30.getWidth(null);
		}else if(((this.directY>this.directX)&&(this.directX>0))||((this.directY<this.directX)&&(this.directX<0))){
			imgRatio = (double)LIGHT60.getHeight(null)/LIGHT60.getWidth(null);
		}else if(((this.directX<0)&&(this.directY+this.directX>=0))||((this.directX>0)&&(this.directX+this.directY<=0))){
			imgRatio = (double)LIGHT120.getHeight(null)/LIGHT120.getWidth(null);
		}else if(((this.directY>0)&&(this.directY+this.directX<0))||((this.directY<0)&&(this.directX+this.directY>0))){
			imgRatio = (double)LIGHT150.getHeight(null)/LIGHT150.getWidth(null);
		}
	}
	/**
	 * 光线传递
	 */
	public void deliverLight(){
		if(this.directX!=0){
			this.directX = this.directX+SPEEDX;
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
		//水平向右
		if((this.directX>0)&&(this.directY==0)){
			g.drawImage(LIGHT0, this.launchX, this.launchY, this.launchX+(int)this.directX, this.launchY+PADDING, this.initImageX, this.initImageY, this.initImageX+(int)this.directX, this.initImageY+PADDING, null);
		}//水平向左
		else if((this.directX<0)&&(this.directY==0)){
			g.drawImage(LIGHT0, this.launchX+(int)this.directX, this.launchY-PADDING, this.launchX, this.launchY, this.initImageX+(int)this.directX, this.initImageY, this.initImageX, this.initImageY+PADDING, null);
		}//竖直向下
		else if((this.directX==0)&&(this.directY>0)){
			g.drawImage(LIGHT90, this.launchX-PADDING, this.launchY, this.launchX, this.launchY+(int)this.directY, this.initImageX, this.initImageY, this.initImageX+PADDING, this.initImageY+(int)this.directY, null);
		}//竖直向上
		else if((this.directX==0)&&(this.directY<0)){
			g.drawImage(LIGHT90, this.launchX, this.launchY+(int)this.directY, this.launchX+PADDING, this.launchY, this.initImageX, this.initImageY+(int)this.directY, this.initImageX+PADDING, this.initImageY, null);
		}//偏右下角且倾角与水平线小于等于45度
		else if((this.directX>0)&&(this.directY>0)&&(this.directX>=this.directY)){
			g.drawImage(LIGHT30, this.launchX, this.launchY, this.launchX+(int)this.directX, this.launchY+(int)this.directY, this.initImageX, this.initImageY, this.initImageX+(int)this.directX, this.initImageY+(int)(this.directX*this.imgRatio), null);
		}//偏右下角且倾角与水平线大于45度
		else if((this.directX>0)&&(this.directY>0)&&(this.directX<this.directY)){
			g.drawImage(LIGHT60, this.launchX, this.launchY, this.launchX+(int)this.directX, this.launchY+(int)this.directY, this.initImageX, this.initImageY, this.initImageX+(int)this.directX, this.initImageY+(int)(this.directX*this.imgRatio), null);
		}//偏左上角且倾角与水平线小于等于45度
		else if((this.directX<0)&&(this.directY<0)&&(this.directX<=this.directY)){
			g.drawImage(LIGHT30, this.launchX+(int)this.directX, this.launchY+(int)this.directY, this.launchX, this.launchY, this.initImageX+(int)this.directX, this.initImageY+(int)(this.directX*this.imgRatio), this.initImageX, this.initImageY, null);
		}//偏左上角且倾角与水平线大于45度
		else if((this.directX<0)&&(this.directY<0)&&(this.directX>this.directY)){
			g.drawImage(LIGHT60, this.launchX+(int)this.directX, this.launchY+(int)this.directY, this.launchX, this.launchY, this.initImageX+(int)this.directX, this.initImageY+(int)(this.directX*this.imgRatio), this.initImageX, this.initImageY, null);
		}//偏右上角且倾角与水平线大于等于45度
		else if((this.directX>0)&&(this.directY<0)&&(this.directX+this.directY<=0)){
			g.drawImage(LIGHT120, this.launchX, this.launchY+(int)this.directY, this.launchX+(int)this.directX, this.launchY, this.initImageX, this.initImageY-(int)(this.directX*this.imgRatio), this.initImageX+(int)this.directX, this.initImageY, null);
		}//偏右上角且倾角与水平线小于45度
		else if((this.directX>0)&&(this.directY<0)&&(this.directX+this.directY>0)){
			g.drawImage(LIGHT150, this.launchX, this.launchY+(int)this.directY, this.launchX+(int)this.directX, this.launchY, this.initImageX, this.initImageY-(int)(this.directX*this.imgRatio), this.initImageX+(int)this.directX, this.initImageY, null);
		}//偏左下角且倾角与水平线大于等于45度
		else if((this.directX<0)&&(this.directY>0)&&(this.directX+this.directY>=0)){
			g.drawImage(LIGHT120, this.launchX+(int)this.directX, this.launchY, this.launchX, this.launchY+(int)this.directY, this.initImageX+(int)this.directX, this.initImageY, this.initImageX, this.initImageY-(int)(this.directX*this.imgRatio), null);
		}//偏左下角且倾角与水平线小于45度
		else if((this.directX<0)&&(this.directY>0)&&(this.directX+this.directY<0)){
			g.drawImage(LIGHT150, this.launchX+(int)this.directX, this.launchY, this.launchX, this.launchY+(int)this.directY, this.initImageX+(int)this.directX, this.initImageY, this.initImageX, this.initImageY-(int)(this.directX*this.imgRatio), null);
		}
	}
}