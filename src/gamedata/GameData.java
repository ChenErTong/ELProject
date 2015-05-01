package gamedata;

import gamecomponent.LightControl;

/**
 * 单场游戏数据，仅有游戏界面和游戏控制器调用
 * @author 恩哥哥
 * 2015.4.15.
 */
public class GameData {
	private LightControl lightControl;
	
	public GameData(){
		lightControl = new LightControl();
	}

	public LightControl getLightControl() {
		return lightControl;
	}
}
