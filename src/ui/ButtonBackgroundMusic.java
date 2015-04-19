/**
 * 背景音乐开关按钮。在界面中调用该类之后必须设定一首背景音乐。
 */
package ui;

import java.awt.Color;
import java.awt.event.*;

import audio.BackgroundMusic;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * @author DorA
 *
 * 2015年4月19日下午10:07:42
 */
public class ButtonBackgroundMusic extends JButton{
	//静音按钮的设置值
	private int control=0;
	//按钮的图标
	private ImageIcon defaultIcon=new ImageIcon("image/button/img1.jpg");
	private ImageIcon muteIcon=new ImageIcon("image/button/img2.jpg");
	
	//背景音乐，由各界面传入
	private BackgroundMusic bgm;
	
	private final ButtonBackgroundMusic b=this;
	
	public ButtonBackgroundMusic(){		
		super();
		
		this.setText("音乐");
		this.setBounds((int)(1024*0.7),(int)(768*0.1),100,100);
		
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
					bgm.play();			//播放背景音乐
					b.setIcon(defaultIcon);
					control--;
				}else if( control==off){					
					bgm.stop();			//停止播放
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
	
	//用来同步各界面间的静音数据
	public void setMusic(BackgroundMusic bgm){
		this.bgm=bgm;
	}
	

}
