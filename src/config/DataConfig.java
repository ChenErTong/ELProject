package config;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

public class DataConfig {
	private String className;
	
	private String name;
	
	private ArrayList<PlanetConfig> planetsConfig;
	//反射星球数量
	public int reflectionNum = 0;
	//折射星球数量
	public int refractionNum = 0;
	//黑洞数量
	public int blackholeNum = 0;

	//构造器
	public DataConfig(Element data) {
		this.planetsConfig = new ArrayList<PlanetConfig>();
		
		this.className = data.attributeValue("className");
		this.name = data.attributeValue("name");
		this.reflectionNum = Integer.parseInt(data.attributeValue("reflectionNum"));
		this.refractionNum = Integer.parseInt(data.attributeValue("refractionNum"));
		this.blackholeNum = Integer.parseInt(data.attributeValue("blackholeNum"));
		@SuppressWarnings("unchecked")
		List<Element> planets = data.elements("planet");
		for (Element planet : planets) {
			PlanetConfig pc = new PlanetConfig(planet);
			planetsConfig.add(pc);
		}
	}

	public String getClassName() {
		return className;
	}

	public String getName() {
		return name;
	}

	public ArrayList<PlanetConfig> getPlanetsConfig() {
		return planetsConfig;
	}
}
