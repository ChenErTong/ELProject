package gamecomponent;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
/**
 * TODO 尚不清楚如何将图片倾斜插入
 * 延伸方法：不断增加图片截取长度，并相应增加图片在游戏界面中的显示长度，从而表现出传播效果
 * @author 恩哥哥
 * 2015.4.14.
 */
public class Light {
	/**
	 * 定义坐标参数
	 * 左下角和右上角坐标
	 */
	private int leftDownX;
	private int leftDownY;
	private int rightUpX;
	private int rightUpY;
	/**
	 * TODO 引入激光图片(暂缺)
	 */
//	private static Image IMG_LIGHT = new ImageIcon().getImage();
	/**
	 * 是否继续传递
	 */
	private boolean canDeliver = false;
	
	/**
	 * 构造函数
	 * @param leftDownX 左下角X
	 * @param leftDownY 左下角Y
	 * @param rightUpX 右上角X
	 * @param rightUpY 右上角Y
	 *  @param g 画笔
	 */
	public Light(int leftDownX, int leftDownY, int rightUpX, int rightUpY){
		this.leftDownX = leftDownX;
		this.leftDownY = leftDownY;
		this.rightUpX = rightUpX;
		this.rightUpY = rightUpY;
		
		this.canDeliver = true;
	}
	/**
	 * 光线传递
	 * @return
	 */
	public void deliverLight(){
	
	}
	
	public boolean isCanDeliver() {
		return canDeliver;
	}
	
	/**
	 * 绘制光线
	 * @param g 画笔
	 */
	private void paint(Graphics g){
		g.drawLine(leftDownX, leftDownY, rightUpX, rightUpY);
	}
}
