/**
 * 播放游戏音效，可以设置成关闭音效。要播放音效时调用SoundEffect.SOUND_NAME.play()，通过变量volume设置是否关闭音效。
 */
package audio;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
/**
 * @author DorA
 *
 * 2015年4月16日22:37:26
 */
public enum SoundEffect {
	//枚举游戏音效和存储位置
	LIGHT("audio/sounds/light.wav");
	
	//可以设置为关闭音效模式
	public static enum Volume{
		MUTE,MEDIUM
	}	
	public static Volume volume=Volume.MEDIUM;
	
	private Clip clip;
	
	SoundEffect(){		
	}	
	
	SoundEffect(String soundFileName){		
		try {
			AudioInputStream ais=AudioSystem.getAudioInputStream(new File(soundFileName));
			clip=AudioSystem.getClip();
			clip.open(ais);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();}
	}
	
	//播放一个音效
	public void play() {		
		try{
			if(volume!=Volume.MUTE){
				if(clip.isRunning())
					clip.stop();
				clip.setFramePosition(0);
				clip.start();
			}
		}catch(Exception e){}
	      
	}
	
	//音效开关按钮调用
	public static void setMute(boolean setMute){
		if(setMute==true){
			volume=Volume.MUTE;
		}else{
			volume=Volume.MEDIUM;
		}
	}
}