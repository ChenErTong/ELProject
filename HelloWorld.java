package com.swing;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
/**
 * Build a class of the window,containing login name,password and buttons.
 * @author hz371_000
 * 2015.3.22/发牢骚方法减肥 
 */

public class HelloWorld extends JPanel{
	static final int WIDTH=300;
	static final int HEIGHT=150;
	JFrame loginframe;
	
	/*
	 * Set the components:
	 * x stands for the line;
	 * y stands for the row;
	 * w stands for the numbers of lines for certain component;
	 * h stands for the numbers of rows for certain component.
	 */
	public void add(Component c,GridBagConstraints constraints,int x,int y,int w,int h){
		 constraints.gridx=x;
		 constraints.gridy=y;
		 constraints.gridwidth=w;
		 constraints.gridheight=h;
		 add(c,constraints);
	 }
	/*
	 * loginframe is the frame of the whole interface;
	 * setDefaultCloseOperation is a method to close the window;
	 * lay is an object of BridBagLayout;
	 * nameinput is the area to input the loginname;
	 * passwordinput is the area to input the password;
	 * title is the label to show the title;
	 * name is the label to show the name;
	 * password is the label to show the password;
	 * OK is the button to enter the system;
	 * cancel is the button to exit;
	 * ok.addActionListener is a listener method to enter the system;
	 * cancel.addActionListener is a listener method to enter the system.
	 */
	HelloWorld(){
		loginframe=new JFrame("Welcome to the world of Java");
		loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout lay=new GridBagLayout();
		setLayout(lay);
		loginframe.add(this,BorderLayout.WEST);
		loginframe.setSize(WIDTH,HEIGHT);
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screenSize=kit.getScreenSize();
		int width=screenSize.width;
		int height=screenSize.height;
		int x=(width-WIDTH)/2;
		int y=(height-HEIGHT)/2;
		loginframe.setLocation(x,y);
		JButton ok=new JButton("Login");
		JButton cancel=new JButton("cancel");
		JLabel title=new JLabel("Welcome to the world of Java");
		JLabel name=new JLabel("Name");
		JLabel password=new JLabel("Password");
		final JTextField nameinput=new JTextField(15);
		final JTextField passwordinput=new JTextField(15);
		GridBagConstraints constraints=new GridBagConstraints();
		constraints.fill=GridBagConstraints.NONE;
		constraints.anchor=GridBagConstraints.EAST;
		constraints.weightx=3;
		constraints.weighty=4;
		add(title,constraints,0,0,4,1);
		add(name,constraints,0,1,1,1);
		add(password,constraints,0,2,1,1);
		add(nameinput,constraints,2,1,1,1);
		add(passwordinput,constraints,2,2,1,1);
		add(ok,constraints,0,3,1,1);
		add(cancel,constraints,2,3,1,1);
		loginframe.setResizable(false);
		loginframe.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HelloWorld helloword=new HelloWorld();
System.out.println("范炜已看过");
	}

}
