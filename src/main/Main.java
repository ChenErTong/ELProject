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
	//开始面板
	PanelStartGame panelStartGame = new PanelStartGame(gameData);
	//选关面板
	PanelSelectMission panelSelectMission = new PanelSelectMission(gameData);
	//游戏面板
	PanelGame panelGame = new PanelGame(gameData);
	//游戏控制器
	GameControl gameControl = new GameControl(panelStartGame, panelSelectMission, panelGame, gameService);
	//玩家控制器
	PlayerControl playerControl = new PlayerControl(gameControl);
	//向面板中加入玩家控制器，对其进行监听
	panelStartGame.addControl(playerControl);
	panelSelectMission.addControl(playerControl);
	panelGame.addControl(playerControl);
	//声明三个游戏界面，其中后两个不显示
	FrameStartGame frameStartGame = new FrameStartGame(panelStartGame);
	FrameSelectMission frameSelectMission = new FrameSelectMission(panelSelectMission);
    FrameGame frameGame = new FrameGame(panelGame);
    //给三个游戏界面加入玩家控制器，用于界面跳转
    frameStartGame.addControl(playerControl);
    frameSelectMission.addControl(playerControl);
    frameGame.addControl(playerControl);
    
    gameControl.addFrames(frameStartGame, frameSelectMission, frameGame);
	}

}
