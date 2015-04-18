package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
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
		if(code.equals("launchLight")){
			this.gameControl.launchLight();
		}
		else if(code.equalsIgnoreCase("earth")){
			
		}
		else if(code.equalsIgnoreCase("sun")){
			
		}
		else if(code.equalsIgnoreCase("threeBody")){
			
		}
	}

}
