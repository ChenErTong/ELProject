package gamedata;

import gamecomponent.LightControl;

/**
 * 游戏数据类，存放和游戏数据有关的信息，可以传入GameService和JPanelGame中
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
