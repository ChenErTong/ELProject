package main;

import gamedata.GameData;
import gameservice.GameService;
import ui_game.FrameGame;
import ui_game.PanelGame;
import ui_start.FrameSelectMission;
import ui_start.FrameStartGame;
import ui_start.PanelSelectMission;
import ui_start.PanelStartGame;
import control.GameControl;
import control.PlayerControl;
/**
 * 将结构连接起来
 * @author 恩哥哥
 * 2015.4.15.
 */

public class Main {

	public static void main(String[] args) {
	//游戏数据类
	GameData gameData = new GameData();
	//游戏逻辑类
	GameService gameService = new GameService(gameData);
	//游戏控制器
	GameControl gameControl = new GameControl(gameService, gameData);
	//玩家控制器
	PlayerControl playerControl = new PlayerControl(gameControl);
	//向游戏控制器中引入玩家控制器
	gameControl.addControl(playerControl);

	FrameStartGame frameStartGame = new FrameStartGame(playerControl, gameData);
	gameControl.gameStart(frameStartGame);
	}
}
