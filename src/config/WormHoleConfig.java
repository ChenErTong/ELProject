package config;

import org.dom4j.Element;

/**
 * ≥Ê∂¥≈‰÷√
 * @author ∂˜∏Á∏Á
 */
public class WormHoleConfig {
	private String className;
	
	private int location1X;
	
	private int location1Y;
	
	private int location2X;
	
	private int location2Y;
	
	private int radius;
	
	public WormHoleConfig(Element wormhole){
		this.className = wormhole.attributeValue("className");
		this.location1X = Integer.parseInt(wormhole.attributeValue("location1X"));
		this.location1Y = Integer.parseInt(wormhole.attributeValue("location1Y"));
		this.location2X = Integer.parseInt(wormhole.attributeValue("location2X"));
		this.location2Y = Integer.parseInt(wormhole.attributeValue("location2Y"));
		this.radius = Integer.parseInt(wormhole.attributeValue("radius"));
	}

	public String getClassName() {
		return className;
	}

	public int getLocation1X() {
		return location1X;
	}

	public int getLocation1Y() {
		return location1Y;
	}

	public int getLocation2X() {
		return location2X;
	}

	public int getLocation2Y() {
		return location2Y;
	}

	public int getRadius() {
		return radius;
	}
}
