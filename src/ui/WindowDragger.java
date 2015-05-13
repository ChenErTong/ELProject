package ui;

import gamecomponent.Planet;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import java.awt.Component;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

/**
 * 使用说明：将要被拖动的类作为第一个参数，如jframe；感应拖动的区域作为第二个参数。
 * 建议在需要被拖动的类的构造里新建此类即可
 * @author CXWorks
 */
public class WindowDragger {

    private Window fWindow;

    private Component fComponent;

    private int dX;

    private int dY;

    /**
     * 让指定的Component通过鼠标拖动来移动Window
     * @param window
     * @param component
     */
    public WindowDragger(Window window, Component component) {

        fWindow = window;
        fComponent = component;

        fComponent.addMouseListener(createMouseListener());
        fComponent.addMouseMotionListener(createMouseMotionListener());
    }
    
	private MouseListener createMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point clickPoint = new Point(e.getPoint());
                SwingUtilities.convertPointToScreen(clickPoint, fComponent);

                dX = clickPoint.x - fWindow.getX();
                dY = clickPoint.y - fWindow.getY();
            }
        };
    }

    private MouseMotionAdapter createMouseMotionListener() {
        return new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
            	if(FrameTotal.TOTALDATA.getResolution()!=9999){
            		Point dragPoint = new Point(e.getPoint());
                    SwingUtilities.convertPointToScreen(dragPoint, fComponent);

                    fWindow.setLocation(dragPoint.x - dX, dragPoint.y - dY);
            	}       
            }
        };
    }
}