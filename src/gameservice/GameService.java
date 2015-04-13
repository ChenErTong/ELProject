package gameservice;

import gamecomponent.LightControl;
/**
 * TODO
 * 游戏逻辑处理器，接收来自GameControl的信号，同时需引入光线控制器和JPanelGame的数据
 * @author 恩哥哥
 *2015.4.14.
 */
public class GameService {
	LightControl lightControl;

	public void launchLight() {
		//TODO 初始化光线控制器
		lightControl = new LightControl();
		//TODO 启动光线
		lightControl.launchLight(0, 0, 1, 1);
	}


}
