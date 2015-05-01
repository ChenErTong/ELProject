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
	private int x,y,tempX,tempY;
	private boolean temp=true;
	
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
                Point clickPoint =e.getPoint();

                x = clickPoint.x ;
                y = clickPoint.y ;
//                Point test=SwingUtilities.convertPoint(button, clickPoint, panel);
//                System.out.println(test);
//                System.out.println(x+" "+y);
            }
        };
    }

    private MouseMotionAdapter createMouseMotionListener() {
        return new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
//            	boolean test=true;
//            	Point clickPoint=
                Point dragPoint = new Point(e.getPoint());
                dragPoint=SwingUtilities.convertPoint(button, dragPoint, panel);
                dragPoint.setLocation(dragPoint.x - x, dragPoint.y - y);
                checkBorder(dragPoint, panel.getWidth()-button.getWidth(), panel.getHeight()-button.getHeight());
//                if(temp==true){
//                	temp=checkPlanet(dragPoint);
//                }
//                else {
//                	if(checkPlanet(dragPoint))
//                	  temp=true;
//                }
//                if(temp){
//                	button.setLocation(dragPoint.x, dragPoint.y);
////                	temp=true;
//                }
//                tempX=button.locationX;
//                tempY=button.locationY;
//                if(checkPlanet(dragPoint))
//                	button.setLocation(dragPoint);
//                else
//                	button.setLocation(tempX, tempY);
//                tempX=button.locationX;
//                tempY=button.locationY;
                button.setLocation(dragPoint);
            }
           
        };
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
    private boolean checkPlanet(Point point){
    	if(Point.distance(button.getLocationX()+75,button.getLocationY()+75,140, 140)>125)
    		return true;
    	return false;
    }

}
