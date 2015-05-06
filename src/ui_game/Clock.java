/**
 * 一个秒表，从进入关卡开始计时，显示一个秒表图形和字符串
 */
package ui_game;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;



/**
 * @author DorA
 *
 * 2015年5月7日上午12:01:40
 */
public class Clock extends JPanel{
	private long startMillis;
	private long currentMillis;
	private int sec;
	
	public Clock(){
		//获取初始化时钟的时刻
		startMillis=System.currentTimeMillis();
		Timer timer=new Timer(1000,new TimerListener());
		timer.start();
	}
	
	//返回从初始化时钟到当前经过的秒数
	public int getSec(){
		currentMillis=System.currentTimeMillis();
		sec = (int)((currentMillis-startMillis)/1000);
		return sec;
	}
	
	//返回经过时间的字符串
	public String getTimeString(){
		int minute=(sec/60)%60;
		int second=sec%60;
		int hour=sec/60/60;
		String time;
		
		if((hour==0)&&(minute==0)){		
			time="您已经探险了"+second+"秒";
		}else if(hour==0){
			time="您已经探险了"+minute+"分钟"+second+"秒";
		}else time="您已经探险了"+hour+"小时"+minute+"分钟"+second+"秒";
		
		return time;
	}

	private class TimerListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			sec=getSec();
			repaint();
		}
	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		//draw circle
		int xCenter=getWidth()/2;
		int yCenter=getHeight()/2;
		int radius=(int)(Math.min(getWidth(), getHeight())*0.7*0.5);
		g.drawOval(xCenter-radius, yCenter-radius, 2*radius, 2*radius);
		//draw the second hand
		int sLength=(int)(radius*0.8);
		int xSec=(int)(xCenter+sLength*Math.sin(Math.PI*sec/30));
		int ySec=(int)(yCenter-sLength*Math.cos(Math.PI*sec/30));
		g.drawLine(xCenter, yCenter, xSec, ySec);
		//获取字体的尺寸信息
		FontMetrics fm=g.getFontMetrics();	
		int stringAscent=fm.getAscent();
		int stringDescent=fm.getDescent();
		int stringWidth=fm.stringWidth(getTimeString());
		//把这行字水平居中
		int xCoordinate=getWidth()/2-stringWidth/2;
		//显示当前经过时间
		g.drawString(getTimeString(), xCoordinate, getHeight()-(stringAscent+stringDescent)*2);

	}

	public static void main(String[] args) {
		JFrame frame=new JFrame();
		frame.setLayout(null);
		Clock clock=new Clock();
		clock.setBounds(800,100,150,200);
	//	clock.setLocation(800, 100);
		frame.add(clock);
		frame.setSize(1024, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
