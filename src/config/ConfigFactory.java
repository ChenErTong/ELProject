package config;

public class ConfigFactory {
	private static LevelConfig LEVEL_CFG = null;
	
	static{
		try {
			LEVEL_CFG = new LevelConfig();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public static LevelConfig getLEVEL_CFG() {
		return LEVEL_CFG;
	}
}
