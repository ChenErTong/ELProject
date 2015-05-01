/**
 * 用来同步背景音乐开关数据，需要在游戏启动时初始化这个类
 */
package ui;

/**
 * @author DorA
 *
 * 2015年4月28日上午9:29:24
 */
public class BgmSyncData {
	private int control;
	
	public BgmSyncData(){
		control=0;
	}
	
	//改变静音设置值
	public void setControl(int control){
		this.control=control;
	}
	
	//获取静音设置值
	public int getControl(){
		return control;
	}	
}