/**
 * 背景音乐开关按钮。在界面中调用该类之后必须调用setMusic方法设定一首背景音乐。
 */
package ui;


import java.awt.Graphics;
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
	private int w=JFrameTotal.WINDOWW;
	private int h=JFrameTotal.WINDOWH;
	//静音按钮的设置值
	private int control;
	//按钮的图标
	private ImageIcon defaultIcon=new ImageIcon("image/button/音乐按钮.png");
	private ImageIcon muteIcon=new ImageIcon("image/button/音乐静音按钮.png");

	//背景音乐，由各界面传入
	private BackgroundMusic bgm;
	
	private ButtonBackgroundMusic b=this;
	BgmSyncData bgmSyncData;
	
	public ButtonBackgroundMusic(BgmSyncData data){		
		super();

		bgmSyncData=data;
		this.setBounds((int)(w*0.82),(int)(h*0.1),50,49);

		
		control=bgmSyncData.getControl();
		final int open=1,off=0;	
		
		//根据同步数据来显示不同的图标
		if(control==open){			
			b.setIcon(muteIcon);	
		}else{
			b.setIcon(defaultIcon);
		}
	
		//监听器
		this.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){			
				if( control==open){
					bgm.play();			//播放背景音乐
					b.setIcon(defaultIcon);
					control--;
				}else if( control==off){					
					bgm.stop();			//停止播放
					b.setIcon(muteIcon);
					control++;
				}	
				bgmSyncData.setControl(control);
			}	
			
		});
		//设置不绘制矩形的内容
		this.setContentAreaFilled(false);
		//设置不绘制边框
		this.setBorderPainted(false);
		//设置可见
		this.setVisible(true);
				
	}	

	//在各界面中设置背景乐曲目,根据是否静音决定是否播放背景乐
	public void setMusic(BackgroundMusic bgm){
		//set
		this.bgm=bgm;
		//play
		if(bgmSyncData.getControl()==0){
			this.bgm.play();					
		}
	}
	
}


