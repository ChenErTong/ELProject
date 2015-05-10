package gamecomponent;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import ui_game.PanelGame;

public class PlanetDragger {
	private Planet button;
	private PanelGame panel;
	private int x,y;
	private Point firstPoint;
	private boolean base=true;
	
	public PlanetDragger(Planet Button,PanelGame panelGame){
		this.button=Button;
		this.panel=panelGame;
		
		button.addMouseListener(this.createMouseListener());
		button.addMouseMotionListener(this.createMouseMotionListener());
		
	}
	//
	private MouseListener createMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                firstPoint=new Point(button.getX(), button.getY());
                x = e.getY();
                y = e.getY();
//                firstPoint=SwingUtilities.convertPoint(button, e.getPoint(), panel);
//                System.out.println(x+" "+y);
            }
        };
    }
	//
    private MouseMotionAdapter createMouseMotionListener() {
        return new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point dragPoint = new Point(e.getPoint());
                dragPoint=SwingUtilities.convertPoint(button, dragPoint, panel);
                dragPoint.setLocation(dragPoint.x - x, dragPoint.y - y);
                checkBorder(dragPoint, panel.getWidth()-button.getWidth(), panel.getHeight()-button.getHeight());
                //
//                System.out.println(firstPoint);
//                System.out.println(dragPoint);
                Point touch=getTouch(140, 140, button.locationX+button.radius, button.locationY+button.radius,
                		50+button.radius, dragPoint.x+button.radius, dragPoint.y+button.radius);

                
                
                
                //
				if(base){
					button.setLocation(dragPoint);
					firstPoint.setLocation(dragPoint);
				}
            }
           
        };
    }
    //
    public void stop(){
    	this.base=false;
    }
    //
    private void checkBorder(Point point,int width,int height){
    	if(point.x<0)
    		point.x=0;
    	else if(point.x>width)
    		point.x=width;
    	if(point.y<0)
    		point.y=0;
    	else if(point.y>height)
    		point.y=height;
    	
    }
    //
    private Point getTouch(int centerX,int centerY,int beforeX,int beforeY,int radius,int afterX,int afterY){
    	Point answer;
		//
    	try{
    		double k=(afterY-beforeY)/(afterX-beforeX);
    		double a=1+Math.pow(k, 2);
    		double b=-2*(centerX+k*(k*beforeX+centerY-beforeY));
    		double c=centerX*centerX+Math.pow((k*beforeX+centerY-beforeY), 2)-radius*radius;
    		//
    		if((b*b-4*a*c)>0){
//    			System.out.println(beforeX+"ddd"+afterX);
    				answer=caculate(k, a, b, c, beforeX, beforeY, afterX, afterY);
    				return answer;
    			}
    		else{
//    			answer=new Point(1,1);
    			return null;
    			}
    	}
    	catch(ArithmeticException e){
    		if(Math.abs(afterX-centerX)>=radius)
    			return null;
    		else {
    			return caculate(centerX, centerY, afterX, beforeY, radius, afterY);
    		}
    	}
	}
    //
    private Point caculate(double k,double a,double b,double c,int beforeX,int beforeY,int afterX,int afterY){
    	double answer1=(-b+Math.pow((b*b-4*a*c), 0.5))/(2*a);
    	double answer2=(-b-Math.pow((b*b-4*a*c), 0.5))/(2*a);
    	double answerX;
    	if(Math.abs(beforeX-answer1)>Math.abs(beforeX-answer2))
    		answerX=answer2;
    	else 
    		answerX=answer1;
//    	System.out.println(answerX);
//    	System.out.println(beforeX+" "+afterX);
    	if(((answerX>beforeX)&&(answerX>afterX))||((answerX<beforeX)&&(answerX<afterX))){
    		return null;
    	}
    	double answerY=k*answerX-k*beforeX+beforeY;
    	
    	Point answer=(new Point((int)answerX,(int)answerY));
    	return answer;
    }
    //
    private Point caculate(int centerX,int centerY,int x,int beforeY,int radius,int afterY){
    	double answer;
    	//
    	double a=1.0;
    	double b=-2*centerY;
    	double c=centerY*centerY+Math.pow(x-centerX, 2)-radius*radius;
    	//
    	double answer1=(-b+Math.pow((b*b-4*a*c), 0.5))/(2*a);
    	double answer2=(-b-Math.pow((b*b-4*a*c), 0.5))/(2*a);
    	if(Math.abs(answer1-beforeY)>Math.abs(answer2-beforeY))
    		answer=answer2;
    	else 
    		answer=answer1;
    	if((answer>=beforeY&&answer>=afterY)||(answer<=beforeY&&answer<=afterY))
    		return null;
    	return (new Point(x,(int)answer));
    }

}
