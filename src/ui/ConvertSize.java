package ui;

import java.awt.Dimension;
import java.awt.Toolkit;

public class ConvertSize {
	private final static int GAME_SIZE_WIDTH=1366;
	private final static int GAME_SIZE_HEIGHT=768;
	private int width;
	private int height;
	private int locationX;
	private int locationY;
	
	public ConvertSize(int x,int y,int oldWidth,int oldHeight){
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screenSize=kit.getScreenSize();
		double percent=screenSize.getWidth()/GAME_SIZE_WIDTH;
		this.width=(int)(oldWidth*percent);
		this.height=(int)(oldHeight*percent);
		this.locationX=(int)(x*percent);
		this.locationY=(int)(y*percent);		
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	public int getLocationX(){
		return this.locationX;
	}
	public int getLocationY(){
		return this.locationY;
	}
}