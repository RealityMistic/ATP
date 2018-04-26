package model;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import AudioTrainerPlay.Utils;

public class Logeador {
	static String path;
	public void loginLog(String userp){
		Data2 data = new Data2(path);
		try{
			// FileWriter fstream = new FileWriter("C:/Users/cesar/Google Drive/Grado Software/PFG/ATLAS-workspace/ATP-Juanjo/src/resource/login-file.csv",true);
 	    	FileWriter fstream = new FileWriter(data.ReadLoginLogroute(), true);
			BufferedWriter fbw = new BufferedWriter(fstream);
 	    	String logString = userp + "," + Utils.now();
			fbw.write(logString);
	    	fbw.newLine();
       		fbw.close();
		} catch (IOException e)
		{
			System.out.println(e.toString());
		}
	}
		public void gameLog(String userp, String levelp, String audiop, int pointsp){
			Data2 data = new Data2(path);
			try{
				// FileWriter fstream = new FileWriter("C:/Users/cesar/Google Drive/Grado Software/PFG/ATLAS-workspace/ATP-Juanjo/src/resource/game-file.csv",true);
				FileWriter fstream = new FileWriter(data.ReadGameLogroute() );
				BufferedWriter fbw = new BufferedWriter(fstream);
	 	    	String logString = userp + "," + Utils.now()+","+levelp+","+audiop+","+pointsp;
				fbw.write(logString);
		    	fbw.newLine();
	       		fbw.close();
			} catch (IOException e)
			{
				System.out.println(e.toString());
			}
	}
}
