/**
 * 
 */
package ui_start;

import gamecomponent.Planet;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import control.PlayerControl;
import ui.FrameTotal;

/**
 * @author DorA
 *
 * 2015年4月29日下午7:03:07
 */
public class PanelHelp extends JPanel{
	private PlayerControl playerControl;
	/**
	 * 得带面板的长宽
	 */
	static int WIDTH = 700;
	static int HEIGHT = 408;
	private ImageIcon img1=new ImageIcon("image/help/help1.jpg");
	private ImageIcon img2=new ImageIcon("image/help/help2.jpg");
	private ImageIcon img3=new ImageIcon("image/help/help3.jpg");
	private ImageIcon img4=new ImageIcon("image/help/help4.jpg");
	/**
	 * 关闭按钮
	 */
	private JButton closeButton;
	/**
	 * TODO 关闭按钮的图片
	 */
	private static final ImageIcon BUTTON_CLOSE = Planet.getImageIcon("image/button/Return4.png", (int)(WIDTH*0.2), (int)(HEIGHT*0.2));
	
	PanelHelp panelHelp=this;
	JLabel jp1=new JLabel(img1);
	JLabel jp2=new JLabel(img2);
	JLabel jp3=new JLabel(img3);
	JLabel jp4=new JLabel(img4);
	
	int countUp=0;
	int countDown=0;
	int page;
	
	public PanelHelp(PlayerControl playerControl){
		this.playerControl = playerControl;
		
		this.setLayout(null);
		
		initButton();
		initLabel();
		
		jp1.setBounds(0, 0, WIDTH, HEIGHT);
		this.add(jp1);
	}
	/**
	 * 初始化各个按钮
	 */
	private void initButton(){
		closeButton = new JButton();
		closeButton.setIcon(BUTTON_CLOSE);
		closeButton.setBounds((int)(WIDTH*0.2), (int)(HEIGHT*0.6), (int)(WIDTH*0.2), (int)(HEIGHT*0.2));
		closeButton.addActionListener(playerControl);
		closeButton.setContentAreaFilled(false);
		closeButton.setBorderPainted(false);
		closeButton.setActionCommand("CloseFrameHelp");
		closeButton.setVisible(true);
		this.add(closeButton);	
	}
	
	void initLabel(){
		JButton jbtUp=new JButton("<");
		JButton jbtDown=new JButton(">");	
		jbtUp.setBounds((int)(WIDTH*0.85), (int)(HEIGHT*0.5), 50,50);
		jbtDown.setBounds((int)(WIDTH*0.85), (int)(HEIGHT*0.8), 50,50);
		this.add(jbtUp);
		this.add(jbtDown);
		
		jbtUp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				countUp++;
				page=countDown-countUp+1;
				switch(page){
				case 1:	 panelHelp.remove(jp2);	
					jp1.setBounds(0, 0, WIDTH, HEIGHT);
					panelHelp.add(jp1);
					break;
				case 2:	panelHelp.remove(jp3);	 
					jp2.setBounds(0, 0, WIDTH, HEIGHT);
					panelHelp.add(jp2);
					break;
				case 3:	panelHelp.remove(jp4);	
					jp3.setBounds(0, 0, WIDTH, HEIGHT);
					panelHelp.add(jp3);
				break;
				default: countUp--;
				}

				repaint();
			}
		});
		
		jbtDown.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				countDown++;
				page=countDown-countUp+1;
				switch(page){
				case 2:	 panelHelp.remove(jp1);	
					jp2.setBounds(0, 0, WIDTH, HEIGHT);
					panelHelp.add(jp2);
					break;
				case 3:	panelHelp.remove(jp2);	 
					jp3.setBounds(0, 0, WIDTH, HEIGHT);
					panelHelp.add(jp3);
					break;
				case 4:	panelHelp.remove(jp3);	
					jp4.setBounds(0, 0, WIDTH, HEIGHT);
					panelHelp.add(jp4);
				break;
				default: countDown--;
				}

				repaint();
			}
		});
	}

	
	
}