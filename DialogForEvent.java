package swingPackage;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.*;

import swingPackage.MainJFrame;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JDialog;

public class DialogForEvent implements ActionListener {
	public JDialog eventDialog;
	private JButton yes,no;
	private JLabel year,month,day;
	private JTextField event;
	private JPanel buttonPanel,messagePanel;
	public String Event;
	public int rows,columns;
	public Calendar sb;
	
	public DialogForEvent(Calendar Date,int Rows,int Columns){
		rows=Rows;
		columns=Columns;
		eventDialog=new JDialog(MainJFrame.mainJFrame);
		eventDialog.setTitle("添加事件");
		//eventDialog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screenSize=kit.getScreenSize();
		eventDialog.setLocation(screenSize.width/2-200, screenSize.height/2-100);
		
		eventDialog.setSize(400, 200);
		eventDialog.setLayout(new BorderLayout());
		Container contentPane=eventDialog.getContentPane();
		
		Date.add(Calendar.MONTH, -1);
		Date.set(Calendar.DAY_OF_MONTH, 1);
		int out=Date.get(Calendar.MONTH);
		int dayOfWeek=Date.get(Calendar.DAY_OF_WEEK)%7;
		Date.add(Calendar.DAY_OF_MONTH, 7*rows+columns-dayOfWeek+1);
		if(dayOfWeek==0)
			Date.add(Calendar.DAY_OF_MONTH, -7);
		sb=Calendar.getInstance();
		sb.set(Date.get(Calendar.YEAR), Date.get(Calendar.MONTH), Date.get(Calendar.DAY_OF_MONTH));
		
		
		//下面是标签
		JLabel year=new JLabel(Integer.toString(Date.get(Calendar.YEAR))+" 年 ");
		JLabel month=new JLabel(Integer.toString(Date.get(Calendar.MONTH)+1)+" 月 ");
		JLabel day=new JLabel(Integer.toString(Date.get(Calendar.DAY_OF_MONTH))+" 日");
		Font label=new Font("ok",Font.BOLD,24);
		year.setFont(label);
		month.setFont(label);
		day.setFont(label);
		
		yes=new JButton("确定");
		no=new JButton("取消");
		yes.addActionListener(this);
		no.addActionListener(this);
		buttonPanel=new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(yes);
		buttonPanel.add(no);
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		//
		
		messagePanel=new JPanel();
		messagePanel.setLayout(new FlowLayout());
		messagePanel.add(year);
		messagePanel.add(month);
		messagePanel.add(day);
		//
		event=new JTextField(22);
		event.setFont(new Font("楷体",Font.BOLD,28));
		messagePanel.add(event);
		contentPane.add(messagePanel,BorderLayout.CENTER);
		eventDialog.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("取消")){
			eventDialog.dispose();
		}
		else if(e.getActionCommand().equals("确定")){
			JTableS.addEvent.addElement(new Event(sb,event.getText()));
			
			Event=JTableS.calendarModel.getValueAt(rows, columns)+"\n"+event.getText();;
			JTableS.calendarModel.setValueAt(Event, rows, columns);
			eventDialog.dispose();
			
		}

	}
	


	
}

	
