package AudioTrainerPlay;

import beans.Audio;
import model.AudioManager;

public class AudioController {
	String level;
	static String path;
	int numaudio;
	
	public AudioController(String pathp) {
		super();
		path = pathp;
	}

	AudioManager am = new AudioManager(path);
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public int getNumaudio() {
		return numaudio;
	}
	public void setNumaudio(int numaudio) {
		this.numaudio = numaudio;
	}
	
	public Audio loadAudio(){
		AudioManager am = new AudioManager(path);
		return am.loadAudio(level, numaudio);
	}
}
