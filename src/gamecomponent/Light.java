package gamecomponent;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
/**
 * TODO 尚不清楚如何将图片倾斜插入
 * @author 恩哥哥
 * 2015.4.14.
 */
public class Light {
	/**
	 * 激光前进的速度
	 */
	private static int SPEED = 1;
	/**
	 * TODO 暂用红色激光图
	 */
	private static final Image INITIMAGE = new ImageIcon("image/componnet/RedLight.png").getImage();
	/**
	 * 初始坐标
	 * 方向向量
	 */
	private int launchX;
	private int launchY;
	private int directX;
	private int directY;
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
	public Light(int launchX, int launchY, int directX, int directY){
		this.launchX = launchX;
		this.launchY = launchY;
		this.directX = directX;
		this.directY = directY;
		
		this.canDeliver = true;
		
		this.lightImage = this.Rotate(INITIMAGE, this.directX, this.directY);
		
		this.lightImage_W = this.lightImage.getWidth(null);
		this.lightImage_H = this.lightImage.getHeight(null);
		
		this.ratio = (double)this.lightImage_H/this.lightImage_W;
		
		//根据方向不同进行对绘画初始顶点进行赋值
		if(this.directX<0){
			this.initImageX = this.lightImage_W;
		}
		if(this.directY<0){
			this.initImageY = this.lightImage_H;
		}
		
	}
	/**
	 * 光线传递
	 */
	public void deliverLight(){
		if(this.directX>0){
			this.directX = this.directX+this.SPEED;
		} else if(this.directX<0){
			this.directX = this.directX+this.SPEED;
		}
		
		if(this.directY>0){
			this.directY = (int) (this.directY+this.SPEED*this.ratio);
		} else if(this.directY<0){
			this.directY = (int) (this.directY+this.SPEED*this.ratio);
		}
	}
	
	public boolean isCanDeliver() {
		return canDeliver;
	}
	
	/**
	 * 绘制光线
	 * @param g 画笔
	 */
	public void paint(Graphics g){
		//根据不同情况的方向向量以不同的坐标绘制激光
		if((this.directX>=0)&&(this.directY>=0)){
			g.drawImage(lightImage, this.launchX, this.launchY, this.launchX+this.directX, this.launchY+this.directY, this.initImageX, this.initImageY, this.initImageX+this.directX, this.initImageY+this.directY, null);
		} else if((this.directX>=0)&&(this.directY<0)){
			g.drawImage(lightImage, this.launchX, this.launchY+this.directY, this.launchX+this.directX, this.launchY, this.initImageX, this.initImageY+this.directY, this.initImageX+this.directX, this.initImageY, null);
		} else if((this.directX<0)&&(this.directY<0)){
			g.drawImage(lightImage, this.launchX+this.directX, this.launchY+this.directY, this.launchX, this.launchY, this.initImageX+this.directX, this.initImageY+this.directY, this.initImageX, this.initImageY, null);
		} else if((this.directX<0)&&(this.directY>=0)){
			g.drawImage(lightImage, this.launchX+this.directX, this.launchY, this.launchX, this.launchY+this.directY, this.initImageX+this.directX, this.initImageY, this.initImageX, this.initImageY+this.directY, null);
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
		double angel = Math.toDegrees(Math.atan(directY/directX));
		System.out.println(angel);
		
		int src_width = src.getWidth(null);
		int src_height = src.getHeight(null);
		System.out.println(src_width+" "+src_height);
		//计算新图片大小
		Rectangle rect_des = this.CalcRotatedSize(new Rectangle(new Dimension(src_width, src_height)), angel);
		
		BufferedImage res = null;
		res = new BufferedImage(rect_des.width, rect_des.height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = res.createGraphics();
		//转换
		g2.translate((rect_des.width-src_width)/2, (rect_des.height-src_height)/2);
		g2.rotate(Math.toRadians(angel), src_width/2, src_height/2);
		
		g2.drawImage(src, null, null);
		
		System.out.println(rect_des.width+" "+rect_des.height);
		
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
