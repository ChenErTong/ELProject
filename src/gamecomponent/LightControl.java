package gamecomponent;

import java.util.ArrayList;
/**
 * 光线控制器，控制一个关卡中的所有光线类
 * 延伸方法：不断增加图片截取长度，并相应增加图片在游戏界面中的显示长度，从而表现出传播效果
 * TODO 传入数据有问题
 * @author 恩哥哥
 * 2015.4.14.
 */
public class LightControl implements Runnable{
	/**
	 * 定义发射坐标
	 */
	private int launchX;
	private int launchY;
	/**
	 * 定义方向向量
	 */
	private double directX;
	private double directY;
	/**
	 * 是否存在
	 */
	private boolean isExist = false;
	/**
	 * 定义一束激光
	 */
	private ArrayList<Light> lightList;
	/**
	 * 构造函数
	 */
	public LightControl(){	
		//定义光线存在
		this.isExist = true;
		//声明一个光线链表(考虑到经过反射与折射后光线轨迹不同采用多个光线类显示)
		this.lightList = new ArrayList<Light>();
		
		//启动线程LightControl.
		Thread t = new Thread(this);
		t.start();
	}
	
	public void stopLight(){
		this.isExist = false;
	}
	/**
	 * TODO 发射激光方法(实现监听)
	 * @param launchX 发射X坐标
	 * @param launchY 发射Y坐标
	 * @param directX 方向向量X坐标
	 * @param directY 方向向量Y坐标
	 */
	public void launchLight(int launchX, int launchY, int directX, int directY){
		//引入光线发射起始坐标
		this.launchX = launchX;
		this.launchY = launchY;
		
		//引入光线发射向量并将其转化为标准类型
		double tempsqrt = Math.sqrt((double)(directX*directX+directY*directY));
		this.directX = (double)directX/tempsqrt;
		this.directY = (double)directY/tempsqrt;	

		// TODO 是否应在此类中初始化？如在此初始化如何将g传入light的paint方法中？
		Light light = new Light(this.launchX, this.launchY, this.directX, this.directY);
		this.lightList.add(light);
	}
	
	/**
	 * 由接收器得到光线是否存在
	 * @return 光线是否存在
	 */
	public boolean getisExist() {
		return this.isExist;
	}
	/**
	 * 得到一束光线
	 * @return 该光线控制器定义的光线
	 */
	public ArrayList<Light> getLightList() {
		return lightList;
	}

	/**
	 * 光线传播数据线程，每50毫秒(再调试)更改一次，不断向前延伸。
	 */
	public void run() {
		while(this.isExist){
			try {
				Thread.sleep(25);
			} catch (Exception e) {
				// TODO: handle exception
			}
			//光束是否存在
			for (int i = 0; i < this.lightList.size(); i++) {
				Light light = this.lightList.get(i);
				//判断激光是否为空并且是否能够继续传递
				if((light != null)&&light.isCanDeliver()){
					light.deliverLight();
				}
			}
		}
	}
		
}
