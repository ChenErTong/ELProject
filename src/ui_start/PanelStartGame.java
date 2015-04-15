/**
 * 开始界面的按钮面板，应该包括开始游戏、帮助、退出游戏、静音的按钮，静音按钮。
 * 静音按钮还没写，监听器也没写，yeah！
 */
package ui_start;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
/**
 * @author DorA
 *
 * 2015年4月8日下午11:51:29
 */
public class PanelStartGame extends JPanel{
	private ImageIcon defaultIcon=new ImageIcon("image/button/img1.jpg");
	private ImageIcon rollIcon=new ImageIcon("image/button/img2.jpg");
	
	
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension screen = toolkit.getScreenSize();
	//界面宽度,，0.618是黄金分割比
	protected int width=(int)(screen.width*0.618);
	//界面高度,，0.618是黄金分割比
	protected int height=(int)(screen.height*0.618);
	

	
	
	public PanelStartGame(){
		setLayout(null);
	//添加一个开始游戏按钮
		JButton jbtStart=new JButton(defaultIcon);
		jbtStart.setBounds((int)(width*0.3),(int)(height*0.5),100,100);
		jbtStart.setPressedIcon(rollIcon);
		jbtStart.setRolloverIcon(rollIcon);
		add(jbtStart);
		//按下后开始游戏
		
	//添加一个帮助&演示按钮
		JButton jbtHelp=new JButton(defaultIcon);
		jbtHelp.setBounds((int)(width*0.1),(int)(height*0.3),100,100);
		jbtHelp.setPressedIcon(rollIcon);
		jbtHelp.setRolloverIcon(rollIcon);
		add(jbtHelp);
		//此处应有监听器
		
	//添加一个退出游戏按钮
		JButton jbtQuit=new JButton(defaultIcon);
		jbtQuit.setBounds((int)(width*0.5),(int)(height*0.8),100,100);
		jbtQuit.setPressedIcon(rollIcon);
		jbtQuit.setRolloverIcon(rollIcon);
		add(jbtQuit);
		//此处应有监听器
	
	
	}
	
	
}
