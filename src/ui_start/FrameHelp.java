package ui_start;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import ui.FrameTotal;
import ui.WindowDragger;

public class FrameHelp extends JInternalFrame{
	private int width=750;
	private int height=450;
	private PanelHelp panelHelp;
	
	public FrameHelp(){
		this.panelHelp = new PanelHelp();
		
		this.setResizable(false);
		this.setSize(width, height);
		this.setLocation((FrameTotal.WINDOWW-width)/2, (FrameTotal.WINDOWH-height)/2);
		
		this.setContentPane(panelHelp);
		
		this.panelHelp.setVisible(true);
		this.setVisible(true);
	}
}
