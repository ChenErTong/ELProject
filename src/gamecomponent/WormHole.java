package gamecomponent;

public class WormHole extends Planet{
	private static final long serialVersionUID = 1L;
	public WormHole(int x,int y,int Radius){
		//常规的参数设置
		this.locationX=x;
		this.locationY=y;
		this.radius=Radius;
		//构造按钮的图片，自动缩放
		this.planetImg=getImageIcon("image/星球/星球4.png", 2*radius,2*radius);
		this.setIcon(planetImg);
		//按钮的位置
		this.setBounds(locationX, locationY, 2*radius, 2*radius);
		//设置不打印矩形的内容
		this.setContentAreaFilled(false);
		//设置不打印边框
		this.setBorderPainted(false);
		//设置可见
		this.setVisible(true);
	}
}
