package control;

import java.awt.AWTEvent;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;

public class KeyControl implements AWTEventListener{
	private GameControl gameControl;
	
	public KeyControl(GameControl gameControl) {
		this.gameControl = gameControl;
	}

	public void eventDispatched(AWTEvent event) {
		 if (((KeyEvent) event).getID() == KeyEvent.KEY_PRESSED) {
	        	int code = ((KeyEvent) event).getKeyCode();
	        	int resolution = 0;
	        	switch(code){
	        	case KeyEvent.VK_F1:
	        		resolution = 1024;
	        		break;
	        	case KeyEvent.VK_F2:
	        		resolution = 1366;
	        		break;
	        	case KeyEvent.VK_F3:
	        		resolution = 9999;
	        		break;
	        	case KeyEvent.VK_ESCAPE:
	        		this.gameControl.Quit();
	        		break;
	        	}
	        	this.gameControl.changeResolution(resolution);
	        }
		
	}

}
