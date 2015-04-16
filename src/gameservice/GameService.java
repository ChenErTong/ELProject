package gameservice;

import gamecomponent.LightControl;
import gamedata.GameData;
/**
 * TODO
 * 游戏逻辑处理器，接收来自GameControl的信号，同时需引入GameData的数据
 * @author 恩哥哥
 *2015.4.14.
 */
public class GameService {
	GameData gameData;
	
	public GameService(GameData gameData){
		this.gameData = gameData;
	}

	public void launchLight() {
		//TODO 启动光线  初始X坐标，初始Y坐标，方向向量X坐标，方向向量Y坐标
		this.gameData.getLightControl().launchLight(0, 100, 10, 7);
	}


}
