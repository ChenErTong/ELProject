package main;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import control.GameControl;
import control.PlayerControl;
import ui.FrameTotal;
import ui_game.PanelGame;
/**
 * 游戏入口
 * @author 恩哥哥
 * 2015.4.15.
 */
public class Main {
	public static void main(String[] args) {
		//游戏控制器
		GameControl gameControl = new GameControl();
		
		FrameTotal frameTotal = new FrameTotal(gameControl);
		
		if(SystemTray.isSupported()){
			SystemTray tray=SystemTray.getSystemTray();
			Image image=PanelGame.getImage(new ImageIcon("image/bg/界面背景.png"), 20, 20);
			TrayIcon trayIcon=new TrayIcon(image,"系统信息");
			try {
				tray.add(trayIcon);
			} catch (AWTException e) {
				e.printStackTrace();
			}
		}
	}
}