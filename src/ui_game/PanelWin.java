package ui_game;

import gamecomponent.Planet;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import ui.JFrameTotal;
import control.PlayerControl;
/**
 * 通关后显示的界面
 * @author 恩哥哥
 * 2015.4.23.
 */
public class PanelWin extends JPanel{
	/**
	 * 得带面板的长宽
	 */
	static int WIDTH = (int)(JFrameTotal.WINDOWW*0.6);
	static int HEIGHT = (int)(JFrameTotal.WINDOWH*0.6);
	/**
	 * 返回按钮
	 */
	JButton returnButton;
	/**
	 * 返回按钮的图片
	 */
	private static final ImageIcon BUTTON_RETURN = Planet.getImageIcon("image/button/返回.jpg", (int)(WIDTH*0.2), (int)(HEIGHT*0.2));
	public PanelWin(PlayerControl playerControl){
		//声明然会按钮并进行相关的属性声明
		this.setLayout(null);
		returnButton = new JButton();
		returnButton.setIcon(BUTTON_RETURN);
		returnButton.setBounds((int)(WIDTH*0.2), (int)(HEIGHT*0.6), (int)(WIDTH*0.2), (int)(HEIGHT*0.2));
		returnButton.addActionListener(playerControl);
		returnButton.setContentAreaFilled(false);
		returnButton.setBorderPainted(false);
		returnButton.setActionCommand("ReturnFromWin");
		returnButton.setVisible(true);
		this.add(returnButton);
	}
}
