/**
 * 音效开关按钮。
 */
package ui;

import java.awt.Color;
import java.awt.event.*;

import audio.SoundEffect;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * @author DorA
 * 2015年4月20日00:34:26
 */
public class ButtonSound extends JButton{
	//静音按钮的设置值
	private int control=0;
	//按钮的图标
	private ImageIcon defaultIcon=new ImageIcon("image/button/img1.jpg");
	private ImageIcon muteIcon=new ImageIcon("image/button/img2.jpg");
	
	private ButtonSound b=this;
	
	public ButtonSound(){		
		super();
		
		this.setText("音效");
		this.setBounds((int)(1024*0.6),(int)(768*0.1),200,100);
		
		final int open=1,off=0;	
		
		//根据静音是否开启显示图标，各界面间保持同步
		if(control==open){			
			b.setIcon(muteIcon);
		}else{
			b.setIcon(defaultIcon);
		}
	
		//监听器
		this.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){			
				if( control==open){
					SoundEffect.setMute(false);
					b.setIcon(defaultIcon);
					control--;
				}else if( control==off){	
					SoundEffect.setMute(true);
					b.setIcon(muteIcon);
					control++;
				}				
			}	
		});
		
		setControl(control);
	}
	
	//改变静音设置值
	public void setControl(int control){
		this.control=control;
	}
	
	//获取静音设置值
	public int getControl(){
		return control;
	}
	
}
