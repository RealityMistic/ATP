package AudioTrainerPlay;

import beans.Audio;
import model.JSONParser;

public class JSONController {
	static String path;
	public JSONController(String pathp){
		path = pathp;
	}
	public String generateTest(Audio audiop){
		JSONParser jp = new JSONParser(path);
		String resultado=jp.generateTest(jp.loadJSON(audiop), audiop.getLevel());
		
//		System.out.println("JSONController result:" + resultado);
		
		return resultado;
	}
	public String generateTestAnswers(Audio audiop){
		JSONParser jp = new JSONParser(path);
		String resultado=jp.generateTestAnswers(jp.loadJSON(audiop), audiop.getLevel());
		
//		System.out.println("JSONController result:" + resultado);
		
		return resultado;
	}

	public int[] retrieveAnswers(Audio audiop){
		JSONParser jp = new JSONParser(path);
//		System.out.println("JSONController retrieveAnswers()");
		int[] resultado = jp.retrieveAnswers(jp.loadJSON(audiop), audiop.getLevel());
		return resultado;
	}
}
