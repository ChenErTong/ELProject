package gamedata;

import gamecomponent.LightControl;
import gamecomponent.Planet;
import gamecomponent.PlanetBlackHole;
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
	//黑洞组
	private List<PlanetBlackHole> planetBlackHoles;
	
	//管线控制器
	private LightControl lightControl;
	
	//第0个是地球，第1个是三体星球，纵坐标分别是X坐标，Y坐标，半径
	private int[][] planetPoints;
	
	//当前关卡游戏等级
	private int level;
	public GameData(int level){		
		lightControl = new LightControl();
		
		this.level = level;
		try {
			//读出配置文件中的所有星球数据
			LevelConfig levelCfg = ConfigFactory.getLEVEL_CFG();		
			DataConfig dataCfg = levelCfg.getDataConfig().get(this.level - 1);
			
			this.planetReflections = new ArrayList<PlanetReflection>(dataCfg.reflectionNum);
			this.planetRefractions = new ArrayList<PlanetRefraction>(dataCfg.refractionNum);
			this.planetBlackHoles = new ArrayList<PlanetBlackHole>(dataCfg.blackholeNum);
			
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
			this.planetPoints = new int[planetsCfg.size()][3];
			
			for (int i = 0; i < planetPoints.length; i++) {
				this.planetPoints[i][0] = planets.get(i).getLocationX();
				this.planetPoints[i][1] = planets.get(i).getLocationY();
				this.planetPoints[i][2] = planets.get(i).getRadius();
			}
			
			//声明地球
			this.planetEarth = (PlanetEarth)planets.get(0);
			//声明三体星球
			this.planetThreeBody = (PlanetThreeBody)planets.get(1);
			//声明反射星球
			for (int i = 2; i < 2+dataCfg.reflectionNum; i++) {
				this.planetReflections.add((PlanetReflection) planets.get(i));
			}
			//声明折射星球
			for (int i = 2+dataCfg.reflectionNum; i < 2+dataCfg.reflectionNum+dataCfg.refractionNum; i++) {
				this.planetRefractions.add((PlanetRefraction) planets.get(i));
			}
			//声明黑洞
			for (int i = 2+dataCfg.reflectionNum+dataCfg.refractionNum; i < 2+dataCfg.reflectionNum+dataCfg.refractionNum+dataCfg.blackholeNum; i++) {
				this.planetBlackHoles.add((PlanetBlackHole) planets.get(i));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void refreshLight() {
		lightControl = new LightControl();
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

	public List<PlanetBlackHole> getPlanetBlackHoles() {
		return planetBlackHoles;
	}

	public int getLevel() {
		return level;
	}
}