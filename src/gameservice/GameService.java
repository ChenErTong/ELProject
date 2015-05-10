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
	/**
	 * 构造函数，初始化总游戏数据
	 */
	public GameService(TotalData totalData) {
		this.totalData = totalData;
	}
	
	/**
	 * 新一局开始后刷新游戏数据
	 * @param gameData
	 */
	public void refreshGameData(GameData gameData){
		this.gameData = gameData;
	}
	
	/**
	 * 发射光线
	 * @param lightX
	 * @param lightY
	 */
	public void launchLight(int lightX,int lightY) {
		//TODO 启动光线  初始X坐标，初始Y坐标，方向向量X坐标，方向向量Y坐标
		this.gameData.getLightControl().launchLight(300, 300, -10, 4);
	}
}