/**
 * 用来同步音效开关数据，需要在游戏启动时初始化这个类
 */
package ui;
/**
 * @author DorA
 *
 * 2015-4-28 13:00:40
 */
public class SoundSyncData {
	private int control;
	
	public SoundSyncData(){
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