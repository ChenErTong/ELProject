package config;

public class ConfigFactory {
	private static LevelConfig LEVEL_CFG = null;
	private static FrameConfig FRAME_CFG = null;
	
	static{
		try {
			LEVEL_CFG = new LevelConfig();
			FRAME_CFG = new FrameConfig();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public static LevelConfig getLEVEL_CFG() {
		return LEVEL_CFG;
	}

	public static FrameConfig getFRAME_CFG() {
		return FRAME_CFG;
	}
}
