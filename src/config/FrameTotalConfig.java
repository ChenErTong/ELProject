package config;

import org.dom4j.Element;

public class FrameTotalConfig {	
	private int width;
	
	private int height;
	
	private int windowup;
	
	private String musicStart;
	
	private String musicSelect;
	
	private String musicGame;
	
	private String mouseImage;
	
	public FrameTotalConfig(Element frametotal) {
		this.width = Integer.parseInt(frametotal.attributeValue("width"));
		this.height = Integer.parseInt(frametotal.attributeValue("height"));
		this.windowup = Integer.parseInt(frametotal.attributeValue("windowup"));
		this.musicStart = frametotal.attributeValue("musicStart");
		this.musicSelect = frametotal.attributeValue("musicSelect");
		this.musicGame = frametotal.attributeValue("musicGame");
		this.mouseImage = frametotal.attributeValue("mouseImage");
		
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getWindowup() {
		return windowup;
	}

	public String getMusicStart() {
		return musicStart;
	}

	public String getMusicSelect() {
		return musicSelect;
	}

	public String getMusicGame() {
		return musicGame;
	}

	public String getMouseImage() {
		return mouseImage;
	}
}
