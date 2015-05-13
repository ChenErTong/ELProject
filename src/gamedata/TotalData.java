package gamedata;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

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
	//解锁最高关卡等级
	private int level;
	//屏幕分辨率
	private int resolution;
	//当地数据文件
	private File baseData;
	//文件中关卡评级前缀
	private String frontGrade;
	//文件中最高解锁关卡前缀
	private String frontLevel;
	//文件中屏幕分辨率前缀
	private String frontResolution;
	
	public TotalData(){
		this.loadData();
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
	public int getGrade(int level) {
		return grade[level - 1];
	}

	public int getLevel() {
		return level;
	}
		
	public int getResolution() {
		return resolution;
	}
	public void setResolution(int resolution) {
		this.resolution = resolution;
	}
	
	/**
	 * 读取文件中数据并赋值
	 */
	private void loadData(){
		String line = new String();
		
		baseData = new File("data/BaseData");
		try {
			BufferedReader br = new BufferedReader(new FileReader(baseData));
			
			//为所有关卡赋予已有分数
			this.grade = new int[5];
			for (int i = 0; i < grade.length; i++) {
				line = br.readLine();
				this.frontGrade = line.split(":")[0]+":";
				grade[i] = Integer.parseInt(line.split(":")[1]);
			}
			
			//为解锁关卡最高等级赋值
			line = br.readLine();
			this.frontLevel = line.split(":")[0]+":";
			this.level = Integer.parseInt(line.split(":")[1]);
			
			//为解锁关卡最高等级赋值
			line = br.readLine();
			this.frontResolution = line.split(":")[0]+":";
			this.resolution = Integer.parseInt(line.split(":")[1]);
			
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 关闭游戏时存入数据
	 */
	public void saveData() {
		String line;
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(baseData));
			
			for (int i = 0; i < grade.length; i++) {
				line = this.frontGrade + Integer.toString(grade[i]) + "\n";
				bw.write(line);
			}
			
			line = this.frontLevel + Integer.toString(level) + "\n";
			bw.write(line);
			
			line = this.frontResolution + Integer.toString(resolution) + "\n";
			bw.write(line);
			
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}