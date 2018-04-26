package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import beans.Audio;

public class JSONParser {
	static String path;
	public JSONParser(String pathp){
		path = pathp;
	}
	public JSONObject loadJSON (Audio audiop){
	//	String filename = "C:/Users/cesar/Google Drive/Grado Software/PFG/ATLAS-workspace/ATP-Juanjo/WebContent/audios/" + audiop.getFilename();
		Data2 data = new Data2(path);
		
		String filename = data.ReadAudioRoute() + audiop.getFilename();
		filename = filename + ".json";
		
//		System.out.println("loadJSON");
		
		String jsonString = "";
		JSONObject jsonObj;
		try {
			jsonString = new Scanner(new File(filename)).useDelimiter("\\Z").next();
		//	System.out.println(jsonString);
		} catch(FileNotFoundException exception) {
			System.out.println("The file " + filename + " was not found.");
		}  catch(IOException exception) {
	        System.out.println(exception);
	    } 
		finally {		
		 jsonObj = new JSONObject(jsonString);
		}
		return jsonObj;
	}
	
	public String generateTest(JSONObject jsonObj, String levelp){
		int numquestions=1; 
		
//		System.out.println("generateTest");
		
		String resultado="<form action='Prize.jsp?level="+levelp+"' method='post'>\n";
		String level = jsonObj.getString("level");
		JSONObject tests=jsonObj.getJSONObject("test");
		numquestions=tests.getInt("numquestions");
		JSONArray questions= tests.getJSONArray("questions");
		// Iterator<String> iterator=questions.iterator();
		try{
			for(int i=0;i<questions.length();i++){
				JSONObject item = questions.getJSONObject(i);
				resultado +="<div class='row'><div class='col s6 center-element'/>\n<div class='card orange lighten-1 white-text'>"+item.getString("question")+"";
				/*
				String[] orderedq = new String[10];
				for (int l=0;l<10;l++){
					orderedq[l]="";
				}
				
				for (int j=1;j<=3;j++){
					Integer na = new Integer(j);
					String answern= "answer"+ na.toString();
					System.out.println("Item question number" + item.getInt("qnumber"));
					System.out.println("Item answer "+item.getString(answern));
				    orderedq[item.getInt("qnumber")] = item.getString(answern);
				//	System.out.println("JSON iteración "+na);
				}
				
				for (int k=1;k<=3;k++){
					
					resultado +="<input type='radio' name='answer' value='" + orderedq[k]+"'> "
								+orderedq[k]+"<br/>";
				}
				*/
				for (int j=1;j<=item.getInt("numanswers");j++){
					Integer na = new Integer(j);
					Integer ni= new Integer(i);
					String answern= "answer"+ na.toString();
					String questi= "quest"+ ni.toString();
					String nidj="nid"+na.toString();
					String questinidj= questi+nidj;
					
					resultado += "<br/>\n<input type='radio' class='yellow darken-1' name='"
							+ questi +"' id='"
							+ questinidj + "' value='"
							+ na.intValue()+"'><label class='white-text' for='"+ questinidj +"'>" 
							+ item.getString(answern) + "</label>";
				}
				resultado +="</div></div></div>";
			} 
		}catch (NullPointerException exception){
			System.out.println(exception);
		}
		
		return resultado;
		
		
}
	@SuppressWarnings("finally")
	public String generateTestAnswers(JSONObject jsonObj, String levelp){
//		int numquestions=1; 
		
//		System.out.println("generateTest");
		
		String resultado = "<div class='container'><div class='row'><div class='col s12 m10 l10'/>"+
				"<ul class=\"collapsible collapsible-accordion\" data-collapsible=\"expandable\">\n";
		String level = jsonObj.getString("level");
		JSONObject tests=jsonObj.getJSONObject("test");
//		numquestions=tests.getInt("numquestions");
		JSONArray questions= tests.getJSONArray("questions");
		// Iterator<String> iterator=questions.iterator();
		try{
			int [] answers = retrieveAnswers(jsonObj, level);
			
			for(int i=0;i<questions.length();i++){
				JSONObject item = questions.getJSONObject(i);
				resultado += "\n<li><div class=\"collapsible-header lime accent-4\">\n"
				+"<h5 class='bold'>"+item.getString("question")+"</h5></div>";
				
				for (int j=1;j<=item.getInt("numanswers");j++){
					if (j == answers[i]){
						Integer na = new Integer(j);
					//	Integer ni= new Integer(i);
						String answern= "answer"+ na.toString();
					//	String questi= "quest"+ ni.toString();
					//	String nidj="nid"+na.toString();
					//  String questinidj= questi+nidj;
					
					resultado += "\n<div class=\"collapsible-body deep-orange accent-2\"><p class='white-text'>\n" 
							+ item.getString(answern) + "</p></div></li>";
					break;
					}
				}
			
			}
			resultado +="</ul>\n</div>\n</div>\n</div>";
		}
		catch (NullPointerException exception){
			System.out.println(exception);
		} finally {
		
			return resultado;	
		}
}
	
	
	public int[] retrieveAnswers(JSONObject jsonObj, String levelp){
		int numquestions=1; 
		int[] resultado;
//		System.out.println("JSONParser retrieveAnswers");
		
		
		String level = jsonObj.getString("level");
		JSONObject tests=jsonObj.getJSONObject("test");;
		numquestions=tests.getInt("numquestions");
		resultado = new int[numquestions];
		for (int j=0;j<numquestions;j++){
			resultado[j]=1;
		}
//		System.out.println("numquestions "+numquestions);
		JSONArray questions= tests.getJSONArray("questions");
		// Iterator<String> iterator=questions.iterator();
		try{
			for(int i=0;i<numquestions;i++){
				JSONObject item = questions.getJSONObject(i);
//				System.out.println(item.getInt("correctanswer"));
				resultado[i]= item.getInt("correctanswer");
				// System.out.println("resultado["+i+"] "+resultado[i]);
				
			} 
		}catch (NullPointerException exception){
			System.out.println(exception);
		}
		
		return resultado;
		
		
}
}