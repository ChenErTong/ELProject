package main;

import java.awt.Panel;

import gamedata.GameData;
import gameservice.GameService;
import ui_game.JFrameGame;
import ui_game.JPanelGame;
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
	//游戏面板
	JPanelGame panelGame = new JPanelGame(gameData);
	//游戏控制器
	GameControl gameControl = new GameControl(panelGame, gameService);
	//玩家控制器
	PlayerControl playerControl = new PlayerControl(gameControl);
	//向面板中加入玩家控制器，对其进行监听
	panelGame.addControl(playerControl);
	//对按钮进行监听(测试方法)
	panelGame.Test();

	JFrameGame frameGame = new JFrameGame(panelGame);
	}

}
