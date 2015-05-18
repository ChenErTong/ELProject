package gamedata;

import gamecomponent.LightControl;
import gamecomponent.Planet;
import gamecomponent.PlanetBlackHole;
import gamecomponent.PlanetEarth;
import gamecomponent.PlanetReflection;
import gamecomponent.PlanetRefraction;
import gamecomponent.PlanetSun;
import gamecomponent.PlanetThreeBody;
import gamecomponent.PlanetWhiteDwarf;
import gamecomponent.PlanetWormHole;

import java.awt.Point;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import configs.DataConfig;
import configs.LevelConfig;
import configs.PlanetConfig;
import configs.WormHoleConfig;

/**
 * 单场游戏数据，仅有游戏界面和游戏控制器调用
 * @author 恩哥哥
 * 2015.4.15.
 */
public class GameData {
	//地球
	private PlanetEarth planetEarth;
	//太阳
	private PlanetSun planetSun;
	//三体
	private PlanetThreeBody planetThreeBody;
	//反射星球组
	private List<PlanetReflection> planetReflections;
	//折射星球组
	private List<PlanetRefraction> planetRefractions;
	//黑洞组
	private List<PlanetBlackHole> planetBlackHoles;
	//白矮星组
	private List<PlanetWhiteDwarf> planetWhiteDwarfs;
	//虫洞
	private PlanetWormHole planetWormHole;
	//虫洞是否存在
	public boolean haveWornhole = false;
	
	//光线控制器
	private LightControl lightControl;
	
	//第0个是地球，第1个是三体星球，纵坐标分别是X坐标，Y坐标，半径
	public int[][] planetPoints;
	
	//初始光线方向
	private int lightDirectionX;
	private int lightDirectionY;
	
	private String[] levels = {"level1", "level2", "level3", "level4", "level5"};
	//将要打开的文件名称
	private String fileName;
	//将要写入的文件名称
	private String newFile;
	
	public GameData(String fileName){		
		lightControl = new LightControl();
		newFile = fileName;
		try {
			//读出配置文件中的所有星球数据
			LevelConfig levelCfg = new LevelConfig(fileName);		
			DataConfig dataCfg = levelCfg.getDataConfig();
			this.fileName = levelCfg.getFileName();
			
			this.planetReflections = new ArrayList<PlanetReflection>(dataCfg.reflectionNum);
			this.planetRefractions = new ArrayList<PlanetRefraction>(dataCfg.refractionNum);
			this.planetBlackHoles = new ArrayList<PlanetBlackHole>(dataCfg.blackholeNum);
			this.planetWhiteDwarfs = new ArrayList<PlanetWhiteDwarf>(dataCfg.whiteDwarfNum);
			
			List<PlanetConfig> planetsCfg = dataCfg.getPlanetsConfig();
			List<Planet> planets = new ArrayList<Planet>(planetsCfg.size());
			
			//创建所有星球对象
			for (PlanetConfig planetConfig : planetsCfg) {
				Class<?> cla = Class.forName(planetConfig.getClassName());
				Constructor<?> ctr = cla.getConstructor(int.class, int.class, int.class, int.class, GameData.class);
				Planet planet = (Planet)ctr.newInstance(
						planetConfig.getLocationX(), 
						planetConfig.getLocationY(), 
						planetConfig.getRadius(), 
						planetConfig.getTag(),
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
			//声明太阳
			this.planetSun = (PlanetSun)planets.get(1);
			//声明三体星球
			this.planetThreeBody = (PlanetThreeBody)planets.get(2);
			//声明反射星球
			for (int i = 3; i < 3+dataCfg.reflectionNum; i++) {
				this.planetReflections.add((PlanetReflection) planets.get(i));
			}
			//声明折射星球
			for (int i = 3+dataCfg.reflectionNum; i < 3+dataCfg.reflectionNum+dataCfg.refractionNum; i++) {
				this.planetRefractions.add((PlanetRefraction) planets.get(i));
			}
			//声明黑洞
			for (int i = 3+dataCfg.reflectionNum+dataCfg.refractionNum; i < 3+dataCfg.reflectionNum+dataCfg.refractionNum+dataCfg.blackholeNum; i++) {
				this.planetBlackHoles.add((PlanetBlackHole) planets.get(i));
			}
			
			for (int i = 3+dataCfg.reflectionNum+dataCfg.refractionNum+dataCfg.blackholeNum; i < 3+dataCfg.reflectionNum+dataCfg.refractionNum+dataCfg.blackholeNum+dataCfg.whiteDwarfNum; i++) {
				this.planetWhiteDwarfs.add((PlanetWhiteDwarf) planets.get(i));
			}
			//声明虫洞
			if(dataCfg.wormholeNum == 1){
				this.haveWornhole = true;
				
				WormHoleConfig wormholeCfg = dataCfg.getWormholeConfig();
				
				Class<?> cla = Class.forName(wormholeCfg.getClassName());
				Constructor<?> ctr = cla.getConstructor(int.class, int.class, int.class, int.class, int.class, GameData.class);
				planetWormHole = (PlanetWormHole) ctr.newInstance(wormholeCfg.getLocation1X(), wormholeCfg.getLocation1Y(), wormholeCfg.getLocation2X(), wormholeCfg.getLocation2Y(), wormholeCfg.getRadius(), this); 	
			}	
			
			this.setLaunchDirections();
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

	public PlanetSun getPlanetSun() {
		return planetSun;
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

	public List<PlanetWhiteDwarf> getPlanetWhiteDwarfs() {
		return planetWhiteDwarfs;
	}

	public String getFileName() {
		return fileName;
	}

	public String getNewFile() {
		return newFile;
	}

	public PlanetWormHole getPlanetWormHole() {
		return planetWormHole;
	}

	public int getLightDirectionX() {
		return lightDirectionX;
	}

	public int getLightDirectionY() {
		return lightDirectionY;
	}
	//
	public void refreshPlanet(int tag,Point location){
		if(!this.fileName.equals("edit")){
			this.planetPoints[tag][0]=location.x;
			this.planetPoints[tag][1]=location.y;
		}
	}
	
	public void setLaunchDirections(){
		this.lightDirectionX = this.planetSun.getLocationX()+this.planetSun.getRadius()-this.planetEarth.getLocationX()-this.planetEarth.getRadius();
		this.lightDirectionY = this.planetSun.getLocationY()+this.planetSun.getRadius()-this.planetEarth.getLocationY()-this.planetEarth.getRadius();
	}
	
	public String nextLevel(String thisLevel){
		String result = new String();
		
		for (int i = 0; i < levels.length; i++) {
			if(thisLevel.equals(levels[i])){
				result = levels[i+1];
			}
		}
		return result;
	}
}