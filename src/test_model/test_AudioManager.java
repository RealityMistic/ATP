package test_model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import beans.Audio;
import model.AudioManager;

public class test_AudioManager {

	@Test
	public void testLoadAudio() {
		AudioManager am = new AudioManager("C:\\Users\\cesar\\Google Drive\\Grado Software\\PFG\\ATLAS-workspace\\ATP-Cesar\\WebContent\\WEB-INF\\");
		Audio resultado = am.loadAudio("B1",1);	
		Audio esperado = new Audio("B1", "Amazing Facts", 1, "B1_amazing_facts_1.mp3");
		assertEquals(resultado.getFilename(), esperado.getFilename());	
	}

}
