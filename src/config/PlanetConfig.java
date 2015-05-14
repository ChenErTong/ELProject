package config;

import org.dom4j.Element;

public class PlanetConfig {
	private String className;
	
	private int locationX;
	
	private int locationY;
	
	private int radius;

	private int tag;
	
	public PlanetConfig(Element planet){
		this.className = planet.attributeValue("className");
		this.locationX = Integer.parseInt(planet.attributeValue("locationX"));
		this.locationY = Integer.parseInt(planet.attributeValue("locationY"));
		this.radius = Integer.parseInt(planet.attributeValue("radius"));
		this.tag = Integer.parseInt(planet.attributeValue("tag"));
	}
	
	public String getClassName() {
		return className;
	}

	public int getLocationX() {
		return locationX;
	}

	public int getLocationY() {
		return locationY;
	}

	public int getRadius() {
		return radius;
	}

	public int getTag() {
		return tag;
	}
}
