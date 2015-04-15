package ui_game;

import gamecomponent.PlanetEarth;
import gamecomponent.PlanetSun;
import gamecomponent.PlanetThreeBody;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.*;


public class JPanelGame extends JPanel{
	public JPanelGame(){
	}
	
	//重载paintComponent方法，绘制背景图
	/**
	 * @author CX
	 */
	@Override
	public void paintComponent(Graphics g){
		
		int height=768;
		int width=1024;
		/*
		 * 下面的代码超级绕，有问题还是直接找我好了；by CX
		 * 作用是无论在什么分辨率的情况下图片可以自动的放大缩小，来适应不同的系统
		 * 而且还牵扯到上面的代码
		 * */
		ImageIcon backgroundDemo=new ImageIcon("image/bg/银河.jpg");
		Image background=backgroundDemo.getImage();
		background=background.getScaledInstance(width, height, Image.SCALE_SMOOTH);//缩放图片的核心方法
		backgroundDemo.setImage(background);
		background=backgroundDemo.getImage();
		g.drawImage(background, 0, 0, null);
		this.repaint();
	}
	

}
