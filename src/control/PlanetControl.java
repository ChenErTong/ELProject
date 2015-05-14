package control;

import gamecomponent.Planet;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlanetControl implements KeyListener{
	private Planet planet;
	
	public PlanetControl(Planet planet){
		this.planet = planet;
	}
	
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_W: 
		case KeyEvent.VK_UP: 
			this.planet.setLocationY(this.planet.getLocationY()-1);
			break;
		case KeyEvent.VK_S: 
		case KeyEvent.VK_DOWN:
			this.planet.setLocationY(this.planet.getLocationY()+1);
			break;
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
			this.planet.setLocationX(this.planet.getLocationX()-1); 
			break;
		case KeyEvent.VK_D: 
		case KeyEvent.VK_RIGHT:
			this.planet.setLocationX(this.planet.getLocationX()+1);
			break;
		}
		this.planet.setLocation(this.planet.getLocationX(), this.planet.getLocationY());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
