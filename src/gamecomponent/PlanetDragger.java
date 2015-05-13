package gamecomponent;

import gamedata.GameData;

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
	private boolean base=true;
	private boolean contact=false;
	private GameData gameData;
	
	public PlanetDragger(Planet Button,PanelGame panelGame,GameData gameData){
		this.button=Button;
		this.panel=panelGame;
		this.gameData=gameData;
		
		button.addMouseListener(this.createMouseListener());
		button.addMouseMotionListener(this.createMouseMotionListener());
		
	}
	//
	private MouseListener createMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
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
                //
                
                checkBorder(dragPoint, panel.getWidth()-button.getWidth(), panel.getHeight()-button.getHeight());
                for(int i=0;i<gameData.planetPoints.length;i++){
                	checkPlanet(gameData.planetPoints[i][0]+gameData.planetPoints[i][2],
                			gameData.planetPoints[i][1]+gameData.planetPoints[i][2],
                			gameData.planetPoints[i][2]+button.radius,
                			dragPoint,button.radius);
                }
//                checkBorder(dragPoint, panel.getWidth()-button.getWidth(), panel.getHeight()-button.getHeight());
                if(base){
                	button.setLocation(dragPoint);
                }
            }

			private void checkPlanet(int centerX,int centerY,int radius,Point after,int br) {
				// TODO Auto-generated method stub
				Point temp=new Point(after.x+br,after.y+br);
				double dis;
				if((dis=temp.distance(centerX, centerY))<radius){
					temp.setLocation(radius*(temp.x-centerX)/dis+centerX,radius*(temp.y-centerY)/dis+centerY );
				}
//				System.out.println(after);
				after.setLocation(temp.x-br, temp.y-br);
//				System.out.println(after);
				
			}
           
        };
    }
    //
    public void stop(){
    	this.base=false;
    }
    //
    public void start(){
    	this.base=true;
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

}
