package main;

import gamedata.GameData;
import gameservice.GameService;
import ui_game.JFrameGame;
import ui_game.PanelGame;
import control.GameControl;
import control.PlayerControl;
/**
 * 将结构连接起来
 * @author 恩哥哥
 * 2015.4.15.
 */

public class Main {

	public static void main(String[] args) {
	GameData gameData = new GameData();
	GameService gameService = new GameService(gameData);
	PanelGame panelGame = new PanelGame(gameData);
	GameControl gameControl = new GameControl(panelGame, gameService);
	PlayerControl playerControl = new PlayerControl(gameControl);
	JFrameGame frameGame = new JFrameGame(panelGame);
		
	panelGame.addControl(playerControl);
	panelGame.Test();
	}

}
