package gamedata;

import gamecomponent.LightControl;
import gamecomponent.Planet;
import gamecomponent.PlanetEarth;
import gamecomponent.PlanetReflection;
import gamecomponent.PlanetRefraction;
import gamecomponent.PlanetThreeBody;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import config.ConfigFactory;
import config.DataConfig;
import config.LevelConfig;
import config.PlanetConfig;

/**
 * 单场游戏数据，仅有游戏界面和游戏控制器调用
 * @author 恩哥哥
 * 2015.4.15.
 */
public class GameData {
	//地球
	private PlanetEarth planetEarth;
	//三体
	private PlanetThreeBody planetThreeBody;
	//反射星球组
	private List<PlanetReflection> planetReflections;
	//折射星球组
	private List<PlanetRefraction> planetRefractions;
	
	private LightControl lightControl;
	
	public GameData(int level){		
		lightControl = new LightControl();
		
		try {
			//读出配置文件中的所有星球数据
			LevelConfig levelCfg = ConfigFactory.getLEVEL_CFG();		
			DataConfig dataCfg = levelCfg.getDataConfig().get(level - 1);
			
			this.planetReflections = new ArrayList<PlanetReflection>(dataCfg.reflectionNum);
			this.planetRefractions = new ArrayList<PlanetRefraction>(dataCfg.refractionNum);
			
			List<PlanetConfig> planetsCfg = dataCfg.getPlanetsConfig();
			List<Planet> planets = new ArrayList<Planet>(planetsCfg.size());
			
			//创建所有星球对象
			for (PlanetConfig planetConfig : planetsCfg) {
				Class<?> cla = Class.forName(planetConfig.getClassName());
				Constructor<?> ctr = cla.getConstructor(int.class, int.class, int.class, GameData.class);
				Planet planet = (Planet)ctr.newInstance(
						planetConfig.getLocationX(), 
						planetConfig.getLocationY(), 
						planetConfig.getRadius(), 
						this);
				
				planets.add(planet);				
			}
			
			this.planetEarth = (PlanetEarth)planets.get(0);
			this.planetThreeBody = (PlanetThreeBody)planets.get(1);
			for (int i = 2; i < 2+dataCfg.reflectionNum; i++) {
				this.planetReflections.add((PlanetReflection) planets.get(i));
			}
			for (int i = 2+dataCfg.reflectionNum; i < 2+dataCfg.reflectionNum+dataCfg.refractionNum; i++) {
				this.planetRefractions.add((PlanetRefraction) planets.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public LightControl getLightControl() {
		return lightControl;
	}

	public PlanetEarth getPlanetEarth() {
		return planetEarth;
	}

	public PlanetThreeBody getPlanetThreeBody() {
		return planetThreeBody;
	}

	public List<PlanetReflection> getPlanetReflections() {
		return planetReflections;
	}

	public List<PlanetRefraction> getPlanetRefractions() {
		return planetRefractions;
	}
}