package gamecomponent;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 太阳类，本质上没什么可做的，仅仅是一个必经点罢了
 * 还是不省接口了，万一以后有用呢？
 * 2015.4.14
 * @author CX
 *
 */
public class PlanetSun extends Planet implements ActionListener {
	/**
	 * 同planetearth类
	 * @param x sun的水平坐标
	 * @param y sun的竖直坐标
	 * @param Radius sun的半径；要比earth大一点哦~
	 */
	public PlanetSun(int x,int y,int Radius){
		//常规的参数设置
		this.locationX=x;
		this.locationY=y;
		this.radius=Radius;
		//构造按钮的图片，自动缩放
		this.planetImg=this.getImageIcon("image/星球/星球6.png", 2*radius,(int)(2*radius*1.414));
		this.setIcon(planetImg);
		//按钮的位置
		this.setBounds(locationX, locationY, 2*radius, (int)(2*radius*1.414));
		//监听器，个人比较喜欢内置
		this.addActionListener(this);
		//设置不打印矩形的内容
		this.setContentAreaFilled(false);
		//设置不打印边框
		this.setBorderPainted(false);
		//设置可见
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
