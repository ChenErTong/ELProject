/**
 * 一个秒表，从进入关卡开始计时，显示一个秒表图形和字符串
 */
package ui_game;

import java.awt.Color;
import ui.FrameTotal;
import gamecomponent.Planet;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;



/**
 * @author DorA
 *
 * 2015年5月7日上午12:01:40
 */
public class Clock extends JPanel{
	PanelGame panelGame;
	Clock clock=this;
	private int WINDOWW = FrameTotal.WINDOWW;
	private int WINDOWH = FrameTotal.WINDOWH;
	//计时器的大小和位置
	private final int WIDTH=(int)(WINDOWW*0.18),HEIGHT=(int)(WINDOWH*0.090);
	private final int x=(int)(WINDOWW*0.435),y=(int)(WINDOWH*0.008);
	//开始时刻
	private long startMillis;
	//现在时刻
	private long currentMillis;
	//限时
	private long totalMillis=180000;
	//计时器
	private Timer timer=new Timer(100,new TimerListener());
	//表盘图片
	ImageIcon img=Planet.getImageIcon("image/componnet/表盘.png", WIDTH,HEIGHT);
	//时间字体
	private Font font=new Font("swfit_slm_fw",Font.PLAIN,23);
	
	public Clock(long totalMillis, PanelGame panelGame){
		this.panelGame=panelGame;
		this.totalMillis=totalMillis;
		this.setBounds(x, y, WIDTH, HEIGHT);
		this.setOpaque(false);
		//获取初始化时钟的时刻
		startMillis=System.currentTimeMillis();
		this.timer.start();
	}
	
	//停止计时器
	public void stop(){
		this.timer.stop();
	}
	
	//返回从剩余的毫秒数
	public long getMillis(){
		currentMillis=System.currentTimeMillis();
		long millis = currentMillis-startMillis;
		return totalMillis-millis;
	}

	
	//返回剩余时间的字符串
	public String getTimeString(){
		long millis=getMillis();
		int sec=(int)(millis/1000);
		
		int hundredMillis=(int)((millis)%100);
		int minute=(int)(sec/60)%60;
		int second=sec%60;
		String time;
		
		time=String.format("%02d", minute)+":"+String.format("%02d", 

second)+":"+String.format("%02d", hundredMillis);
		
		return time;
	}

	private class TimerListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			repaint();
			if(getMillis()/100==0){				
				panelGame.gameLose();
			}			
		}
	}
	
	protected void paintComponent(Graphics g){
		
		super.paintComponent(g);
		g.setFont(font);
		g.setColor(Color.white);
		int xCenter=WIDTH/2;
		int yCenter=HEIGHT/2;
		
		g.drawImage(img.getImage(), 0,0, null);
		//获取字体的尺寸信息
		FontMetrics fm=g.getFontMetrics();	
		int stringAscent=fm.getAscent();
		int stringDescent=fm.getDescent();
		int stringWidth=fm.stringWidth(getTimeString());
		//把这行字居中
		int xCoordinate=xCenter-stringWidth/2;
		int yCoordinate=(int)(yCenter+(stringAscent-stringDescent)/2);
		//显示当前经过时间
		if(getMillis()/100<=0){
			g.drawString(" 00:00:00", xCoordinate, yCoordinate);
		}else{
			g.drawString(getTimeString(), xCoordinate, yCoordinate);
		}
		

	}



}