/**
 * 游戏背景音乐，可调用方法播放或关闭背景音乐。构造方法需要传入音乐文件名，如“bgm01”，音乐文件存放在audio/music文件夹。
 */
package audio;


import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

//import javax.sound.midi.MidiSystem;
//import javax.sound.midi.Sequence;
//import javax.sound.midi.Sequencer;
/**
 * @author DorA
 *
 * 2015年4月16日22:36:01
 */
public class BackgroundMusic{
//	private Sequence bgm;
//	private Sequencer player;
	private AudioClip clip;
	//初始化播放器，打开音乐文件
	public BackgroundMusic(String musicFile) throws Exception{
		File file=new File(musicFile);
		URI uri=file.toURI();
		URL url=uri.toURL();
			
		this.clip=Applet.newAudioClip(url);			
	}
//		try{
//			bgm =MidiSystem.getSequence(new File(musicFile));
//			player=MidiSystem.getSequencer();
//			player.setSequence(bgm);
//			player.open();
//			
//		}catch(Exception e){}
	
	//暂停播放
	public void stop(){	
		this.clip.stop();
//		 if(player.isRunning()){
//			 player.stop();
//		 }		 
	 }
	
	//开始播放（从暂停处开始）
	public  void play() {
		this.clip.play();
		this.clip.loop();
//		if (!player.isRunning()) {
//			
//			player.setLoopCount(player.LOOP_CONTINUOUSLY);
//			player.start();
//		}
	}
}