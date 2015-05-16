package config;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

public class DataConfig {
	private String className;
	
	private String name;
	//地球，三体，反射星球，折射星球，黑洞，白矮星
	private ArrayList<PlanetConfig> planetsConfig;
	//虫洞
	private WormHoleConfig wormholeConfig;
	
	//反射星球数量
	public int reflectionNum = 0;
	//折射星球数量
	public int refractionNum = 0;
	//黑洞数量
	public int blackholeNum = 0;
	//白矮星数量
	public int whiteDwarfNum = 0;
	//虫洞数量
	public int wormholeNum = 0;

	//构造器
	public DataConfig(Element data) {
		this.planetsConfig = new ArrayList<PlanetConfig>();
		
		this.className = data.attributeValue("className");
		this.name = data.attributeValue("name");
		this.reflectionNum = Integer.parseInt(data.attributeValue("reflectionNum"));
		this.refractionNum = Integer.parseInt(data.attributeValue("refractionNum"));
		this.blackholeNum = Integer.parseInt(data.attributeValue("blackholeNum"));
		this.whiteDwarfNum = Integer.parseInt(data.attributeValue("whiteDwarfNum"));
		this.wormholeNum = Integer.parseInt(data.attributeValue("wormholeNum"));
		
		@SuppressWarnings("unchecked")
		List<Element> planets = data.elements("planet");
		for (Element planet : planets) {
			PlanetConfig pc = new PlanetConfig(planet);
			planetsConfig.add(pc);
		}
		
		if(this.wormholeNum == 1){
			Element wormhole = data.element("wormhole");
			wormholeConfig = new WormHoleConfig(wormhole);
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

	public WormHoleConfig getWormholeConfig() {
		return wormholeConfig;
	}
}
