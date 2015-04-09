/**
 * 图片背景面板，提供一个imageicon即可构造一个panel放进frame里啦
 */
package ui_start;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
/**
 * @author DorA
 *
 * 2015年4月8日下午11:51:29
 */

public class PanelBackground extends JLayeredPane{
	
	PanelBackground(ImageIcon bg){
		JLabel background = new JLabel(bg);
		background.setBounds(0,0,1000, 650);
		add(background);
	}

}
