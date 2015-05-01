package ui_start;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import ui.FrameTotal;

public class FrameHelp extends JFrame{
	private int width=750;
	private int height=450;
	private PanelHelp panelHelp;
	
	public FrameHelp(){
		this.panelHelp = new PanelHelp();
		
		this.setResizable(false);
		this.setSize(width, height);
		this.setLocation((FrameTotal.WINDOWW-width)/2, (FrameTotal.WINDOWH-height)/2+FrameTotal.WINDOWY);
		
		this.setContentPane(panelHelp);
		this.panelHelp.setVisible(true);
		this.setVisible(true);
	}
}
