package gamedata;
/**
 * 游戏总数据成绩存储点,各关卡解锁情况等信息
 */
public class TotalData {
	/**
	 * 每个关卡评级
	 * 若为0则显示未通关
	 * 若为1则代表D等级
	 * 若为2则代表C等级
	 * 若为3则代表B等级
	 * 若为4则代表A等级
	 * 若为5则代表S等级
	 */
	private int[] grade;
	//游戏关卡等级
	private int level;
	
	public TotalData(){
		this.grade = new int[5];
		for (int i = 0; i < grade.length; i++) {
			grade[i] = 0;
		}
		
		this.level = 1;
	}
	/**
	 * 游戏通关一关后升级一级
	 */
	public void levelUp(){
		this.level ++;
	}
	
	/**
	 * 为每个关卡设置评级
	 * @param level 需要设置评级的关卡
	 * @param nowGrade 该关卡获得的评级
	 */
	public void setGrade(int level, int nowGrade){
		this.grade[level - 1] = nowGrade;
	}
	public int[] getGrade() {
		return grade;
	}

	public int getLevel() {
		return level;
	}
}