/**
 * 游戏背景音乐，可调用方法播放或关闭背景音乐。构造方法需要传入音乐文件名，如“bgm01”，音乐文件存放在audio/music文件夹。
 */
package audio;

import java.io.File;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.*;
import javax.sound.midi.Sequencer;
/**
 * @author DorA
 *
 * 2015年4月16日22:36:01
 */
public class BackgroundMusic{

	private Sequence bgm;
	private Sequencer player;

	//初始化播放器，打开音乐文件
	public BackgroundMusic(String musicName){
		try{
			bgm =MidiSystem.getSequence(new File("audio/music/"+musicName+".mid"));
			player=MidiSystem.getSequencer();
			player.setSequence(bgm);
			player.open();
			
		}catch(Exception e){}
		
	 }
	
	//暂停播放
	public void stop(){		
		 if(player.isRunning()){
			 player.stop();
		 }		 
	 }
	
	//开始播放（从暂停处开始）
	public  void play() {
		if (!player.isRunning()) {
			player.setLoopCount(player.LOOP_CONTINUOUSLY);
			player.start();
		}
	}
}