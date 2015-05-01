package gameservice;

import gamedata.GameData;
import gamedata.TotalData;
/**
 * TODO
 * 游戏逻辑处理器，接收来自GameControl的信号，同时需引入GameData的数据
 * @author 恩哥哥
 *2015.4.14.
 */
public class GameService {
	//游戏总数据
	TotalData totalData;
	//单局游戏数据
	GameData gameData;
	
	public GameService(TotalData totalData) {
		this.totalData = totalData;
	}

	public void refreshGameData(GameData gameData){
		this.gameData = gameData;
	}

	public void launchLight(int lightX,int lightY) {
		//TODO 启动光线  初始X坐标，初始Y坐标，方向向量X坐标，方向向量Y坐标
		this.gameData.getLightControl().launchLight(lightX, lightY, 10, 7);
	}


}
