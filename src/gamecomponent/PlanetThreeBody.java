package gamecomponent;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlanetThreeBody extends Planet implements ActionListener {
	/**三体类；即最终的接受点，需要light类的支持
	 * 同planetearth类
	 * @param x sun的水平坐标
	 * @param y sun的竖直坐标
	 * @param Radius sun的半径；
	 */
	public PlanetThreeBody(int x,int y,int Radius){
		//常规的参数设置
		this.locationX=x;
		this.locationY=y;
		this.radius=Radius;
		//构造按钮的图片，自动缩放
		this.planetImg=this.getImageIcon("image/星球/星球4.png", 2*radius,(int)(2*radius*1.414));
		this.setIcon(planetImg);
		//按钮的位置
		this.setBounds(locationX, locationY, 2*radius,(int)(2*radius*1.414));
		//监听器，个人比较喜欢内置
		this.addActionListener(this);
		//设置不打印矩形内容
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
