/**
 * 逐行显示字幕，要显示的把一段话传入构造方法，中间用换行符隔开。
 */
package ui_start;

import java.awt.Color;
import java.awt.Font;
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
 * 2015年5月5日12:04:48
 */
public class StorylinePanel extends JPanel{
	private int frameWidth=1024;
	private int frameHeight=700;
	//文本信息
	private String text="1971年秋是叶文洁来到红岸的第二年\n文革的高压使她感到无助与绝望，\n父亲死去的场景依旧历历在目。\n";
	private String message="";
	private String[] messageList;
	//刷新间隔(毫秒)
	int interval=2000;
	//一行文字的位置信息
	private int xCoordinate;
	private int yCoordinate=300;

	//字体，可以调用方法来设置
	private Font font=new Font("黑体", Font.PLAIN, 30);
	//字体尺度信息
	private int stringAscent;
	private int stringDescent;
	private int stringWidth;
	//行数计数器
	int i=0;
	
	public StorylinePanel(String text){
		this.text=text;
	
		//计时器
		Timer timer=new Timer(interval,new TimerListener());
		timer.start();
		
		
		//将一段话拆成一行一行的字符串，放在数组里
		this.messageList=text.split("\n");	
	}
	
	private class TimerListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//逐行显示文字
			if(i<messageList.length){
				message=messageList[i];
				i++;
				repaint();
			}
		}
	}

	
	//绘制一段文字
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
	
		//设置字体
		g.setFont(font);
		g.setColor(Color.white);
			
		//获取字体的尺寸信息
		FontMetrics fm=g.getFontMetrics();	
		stringAscent=fm.getAscent();
		stringDescent=fm.getDescent();
		
		//第一次绘制时将字符串垂直居中
		if(yCoordinate==0){
			yCoordinate=frameHeight/2+stringAscent/2;
		}
			
		//一行字的宽度
		stringWidth=fm.stringWidth(message);
		//把这行字水平居中
		xCoordinate=getWidth()/2-stringWidth/2;
		yCoordinate=frameHeight/2+stringAscent/2;
		
		//绘制
		g.drawString(message, xCoordinate, yCoordinate);
			
		}
	
/*	public static void main(String[]args){
		JFrame frame=new JFrame();
		
		frame.add(new StorylinePanel("1971年秋是叶文洁来到红岸的第二年，\n文革的高压使她感到无助与绝望，\n父亲死去的场景依旧历历在目。\n "));
	

		frame.setSize(1024,700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
	}*/
}
