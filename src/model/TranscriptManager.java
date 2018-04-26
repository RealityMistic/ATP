package model;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import beans.Audio;

public class TranscriptManager {
		static String path;
		public TranscriptManager(String pathp) {
			super();
			path = pathp;
		}
		
		static String readFile(String pathfilep) 
				  throws IOException 
				{
		/*			byte[] encoded=null;
					try{
						encoded = Files.readAllBytes(Paths.get(pathp));
					}
					catch(IOException exception) {
				        System.out.println(exception);
				    } 
					finally{
						return new String(encoded, Charset.defaultCharset());
					}
		*/
				    String result2 = "";
					Scanner linReader = new Scanner(new File(pathfilep)).useDelimiter("\n");
					while (linReader.hasNext())
			        {
			            String line = linReader.nextLine();
			            result2 += line+"<br/>";
			        }
			        linReader.close();
			        return result2;
				}
		
		public String loadTranscript(Audio audiop){
			Data2 data = new Data2(path);
			String filename = data.ReadAudioRoute() + audiop.getFilename();
			filename = filename + ".txt";
		    String result="";
			try{
			result = readFile(filename);
			}
			catch(IOException exception) {
		        System.out.println(exception);
		    } 
			finally{
				return result;
			}
			
			
		}
	
	

}
