package ui_game;

import gamecomponent.Planet;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.FrameTotal;
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
	static int WIDTH = (int)(FrameTotal.WINDOWW*0.784);
	static int HEIGHT = (int)(FrameTotal.WINDOWH*0.784);
	/**
	 * 通关评级
	 */
	private int grade;
	/**
	 * 返回按钮
	 */
	private JButton returnButton;
	/**
	 * 下一关按钮
	 */
	private JButton nextButton;
	/**
	 * 返回按钮的图片
	 */
	private static final ImageIcon BUTTON_RETURN = Planet.getImageIcon

("image/button/Return4.png", (int)(WIDTH*0.2), (int)(HEIGHT*0.2));
	/**
	 * 下一关按钮图片
	 */
	private static final ImageIcon BUTTON_NEXT = Planet.getImageIcon

("image/button/NEXT3.png", (int)(WIDTH*0.2), (int)(HEIGHT*0.2));
	/**
	 * 背景图片
	 */
	private ImageIcon img;
	private JLabel bg;
	
	public PanelWin(PlayerControl playerControl,int grade){		
		this.grade=grade;
		//声明然会按钮并进行相关的属性声明
		this.setLayout(null);

		//返回按钮声明
		returnButton = new JButton();
		returnButton.setIcon(BUTTON_RETURN);
		returnButton.setBounds((int)(WIDTH*0.1), (int)(HEIGHT*0.8), (int)

(WIDTH*0.2), (int)(HEIGHT*0.2));
		returnButton.addActionListener(playerControl);
		returnButton.setContentAreaFilled(false);
		returnButton.setBorderPainted(false);
		returnButton.setActionCommand("ReturnFromWin");
		returnButton.setVisible(true);
		this.add(returnButton);
		//下一关按钮声明
		nextButton = new JButton();
		nextButton.setIcon(BUTTON_NEXT);
		nextButton.setBounds((int)(WIDTH*0.75), (int)(HEIGHT*0.8), (int)

(WIDTH*0.2), (int)(HEIGHT*0.2));
		nextButton.addActionListener(playerControl);
		nextButton.setContentAreaFilled(false);
		nextButton.setBorderPainted(false);
		nextButton.setActionCommand("NextLevel");
		nextButton.setVisible(true);
		this.add(nextButton);
		
		//根据评分显示背景图片
		getMark();
		bg=new JLabel(img);
		this.add(bg);
		bg.setBounds(0, 0, WIDTH, HEIGHT);
		
	}
	
	//分析通关时间数据
	public void getMark(){
		if (this.grade == 5){
			img=new ImageIcon("image/win/S.png");
		}else if(this.grade == 4){
			img=new ImageIcon("image/win/A.png");
		}else if(this.grade == 3){
			img=new ImageIcon("image/win/B.png");
		}else if(this.grade == 2){
			img=new ImageIcon("image/win/C.png");
		}else if(this.grade == 1){
			img=new ImageIcon("image/win/D.png");
		}else if(this.grade==0){
			img=new ImageIcon("image/lose/lose.png");
		}
	}	
}