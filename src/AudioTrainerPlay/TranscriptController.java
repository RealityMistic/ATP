package AudioTrainerPlay;

import beans.Audio;
import model.TranscriptManager;

public class TranscriptController {
static String path;
	
	public TranscriptController(String pathp) {
		super();
		path=pathp;
	}
	public String loadTranscript(Audio audiop){
		String result;
		TranscriptManager tm = new TranscriptManager(path);
		result = tm.loadTranscript(audiop);
		return result;
	}
}
