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
	 * TODO 暂用红色激光图
	 */
	private static final Image INITIMAGE = new ImageIcon("image/componnet/Light.png").getImage();
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
	 * 旋转后光线的图片
	 */
	private Image lightImage;
	/**
	 * 旋转后图片的长宽
	 */
	private int lightImage_W;
	private int lightImage_H;
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
		
		this.lightImage = this.Rotate(INITIMAGE, this.directX, this.directY);
		
		this.lightImage_W = this.lightImage.getWidth(null);
		this.lightImage_H = this.lightImage.getHeight(null);
		
		this.ratio = (double)this.lightImage_H/this.lightImage_W;
	}
	/**
	 * 光线传递
	 */
	public void deliverLight(){
		if(this.directX>0){
			this.directX = this.directX+this.SPEED;
		} else if(this.directX<0){
			this.directX = this.directX-this.SPEED;
		}
		
		if(this.directY>0){
			this.directY = this.directY+this.SPEED*this.ratio;
		} else if(this.directY<0){
			this.directY = this.directY-this.SPEED*this.ratio;
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
	/**
	 * TODO 1个像素太小
	 * 绘制光线
	 * @param g 画笔
	 */
	public void paint(Graphics g){
		//根据不同情况的方向向量以不同的坐标绘制激光
		if((this.directX>=0)&&(this.directY>=0)){
			g.drawImage(lightImage, this.launchX, this.launchY, this.launchX+(int)this.directX, this.launchY+(int)this.directY, this.initImageX, this.initImageY, this.initImageX+(int)this.directX, this.initImageY+(int)this.directY, null);
		} else if((this.directX>=0)&&(this.directY<0)){
			g.drawImage(lightImage, this.launchX, this.launchY+(int)this.directY, this.launchX+(int)this.directX, this.launchY, this.initImageX, this.initImageY+(int)this.directY, this.initImageX+(int)this.directX, this.initImageY, null);
		} else if((this.directX<0)&&(this.directY<0)){
			g.drawImage(lightImage, this.launchX+(int)this.directX, this.launchY+(int)this.directY, this.launchX, this.launchY, this.initImageX+(int)this.directX, this.initImageY+(int)this.directY, this.initImageX, this.initImageY, null);
		} else if((this.directX<0)&&(this.directY>=0)){
			g.drawImage(lightImage, this.launchX+(int)this.directX, this.launchY, this.launchX, this.launchY+(int)this.directY, this.initImageX+(int)this.directX, this.initImageY, this.initImageX, this.initImageY+(int)this.directY, null);
		}
	}
	
	/**
	 * 将图片根据传入方向向量角度旋转
	 * @param src 需要旋转的图片
	 * @param directX 方向向量X
	 * @param directY 方向向量Y
	 * @return
	 */
	private BufferedImage Rotate(Image src, double directX,  double directY){
		double angel;
		//考虑到X或Y可能为0的情况
		if((directX>0)&&(directY==0)){
			angel = 0.0;
		} else if((directX<0)&&(directY==0)){
			angel = 180.0;
		} else if((directX==0)&&(directY>0)){
			angel = 90.0;
		}else if((directX==0)&&(directY<0)){
			angel = 270.0;
		}else{
			angel = Math.toDegrees(Math.atan(directY/directX));
		}
		
		
		int src_width = src.getWidth(null);
		int src_height = src.getHeight(null);
		//计算新图片大小
		Rectangle rect_des = this.CalcRotatedSize(new Rectangle(new Dimension(src_width, src_height)), angel);
		
		BufferedImage res = null;
		res = new BufferedImage(rect_des.width, rect_des.height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = res.createGraphics();
		//转换
		g2.translate((rect_des.width-src_width)/2, (rect_des.height-src_height)/2);
		g2.rotate(Math.toRadians(angel), src_width/2, src_height/2);
		
		g2.drawImage(src, null, null);
		return res;
	}
	
	private Rectangle CalcRotatedSize(Rectangle src, double angel){
		//如果角度小于0度，需要进行转换
		if(angel<0){
			angel = angel+360;
		}
		//如果角度大于90度，需要进行转换
		if(angel>=90){
			if(angel/90%2==1){
				int temp = src.height;
				src.height = src.width;
				src.width = temp;
			}
			angel = angel%90;
		}
//		System.out.println(angel);
		//半径r
		double r = Math.sqrt(src.height*src.height+src.width*src.width)/2;
		double len = 2*Math.sin(Math.toRadians(angel)/2)*r;
		double angel_alpha = (Math.PI-Math.toRadians(angel))/2;
		double angel_dalta_width = Math.atan((double)src.height/src.width);
		double angel_dalta_height = Math.atan((double)src.width/src.height);
		
		int len_dalta_width = (int)(len*Math.cos(Math.PI-angel_alpha-angel_dalta_width));
		int len_dalta_height = (int)(len*Math.cos(Math.PI-angel_alpha-angel_dalta_height));
		
		int des_width = src.width+len_dalta_width*2;
		int des_height = src.height+len_dalta_height*2;
		
		return new java.awt.Rectangle(new Dimension(des_width, des_height));
	}
}
