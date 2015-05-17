package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * 玩家点击按钮信号输入玩家控制台，并引入GameControl。
 * @author 恩哥哥
 * 2015.4.13.
 */
public class PlayerControl implements ActionListener{
	private GameControl gameControl;
	
	public PlayerControl(GameControl gameControl){
		this.gameControl = gameControl;
	}

	public void actionPerformed(ActionEvent e) {
		String code = e.getActionCommand();
		if(code.equalsIgnoreCase("earth")){
			this.gameControl.launchLight();
			this.gameControl.stopDrag();
		} else if(code.equalsIgnoreCase("ReturnFromWin")){
			this.gameControl.returnFromWin();
		} else if(code.equalsIgnoreCase("ReturnFromGame")){
			this.gameControl.returnFromGame();
		} else if(code.equalsIgnoreCase("ReturnToStart")){
			this.gameControl.returnToStart();
		} else if(code.equalsIgnoreCase("ToSelectMission")){
			this.gameControl.toSelectMission();
		} else if(code.equalsIgnoreCase("OpenPanelHelp")){
			this.gameControl.openFrameHelp();
		} else if(code.equalsIgnoreCase("CloseFrameHelp")){
			this.gameControl.closeFrameHelp();
		} else if(code.equalsIgnoreCase("NextLevel")){
			this.gameControl.nextLevel();
		} else if(code.equalsIgnoreCase("Quit")){
			this.gameControl.Quit();
		} else if(code.equalsIgnoreCase("level1")
				||code.equalsIgnoreCase("level2")
				||code.equalsIgnoreCase("level3")
				||code.equalsIgnoreCase("level4")
				||code.equalsIgnoreCase("level5")
				||code.equalsIgnoreCase("user-defined1")
				||code.equalsIgnoreCase("user-defined2")
				||code.equalsIgnoreCase("user-defined3")
				||code.equalsIgnoreCase("user-defined4")
				||code.equalsIgnoreCase("user-defined5")){
			this.gameControl.toGameLevel(code);
		} else if(code.equalsIgnoreCase("ToSelectDIY")){
			this.gameControl.toSelectDIY();
		} else if(code.equalsIgnoreCase("DIY")){
			this.gameControl.toPanelEdit();
		} else if(code.equalsIgnoreCase("Save")){
			this.gameControl.saveData();
			this.gameControl.toSelectDIY();
		}
	}
}