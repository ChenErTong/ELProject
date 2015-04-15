/**
 * 图片背景面板，提供一个imageicon即可构造一个panel放进frame里啦
 */
package ui_start;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
/**
 * @author DorA
 *
 * 2015年4月8日下午11:51:29
 */

public class PanelBackground extends JLayeredPane{
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension screen = toolkit.getScreenSize();
	//界面宽度,，0.618是黄金分割比
	private int width=(int)(screen.width*0.618);
	//界面高度,，0.618是黄金分割比
	private int height=(int)(screen.height*0.618);
	
	PanelBackground(){
	}

	PanelBackground(ImageIcon bg){
		JLabel background = new JLabel(bg);
		background.setBounds(0,0,width,height);
		add(background);
	}

}
