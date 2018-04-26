package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import beans.Audio;


public class AudioManager {
	static String path;
	
public AudioManager(String pathp) {
		super();
		path = pathp;
	}

public Audio loadAudio(String levelp, int numaudiop){
		Data2 data = new Data2(path);
		Connection conn = data.getConn();
	//	String userl, namel, edlevell, genderl, emaill;
	//	int agel, jourpointsl, explopointsl;
		Audio audio = null;
	try{
		
        
        Statement stat = conn.createStatement();
        String sql = "select * from audios where level='"+ levelp +"' and numaudio='"+ numaudiop +"';";
   //    System.out.println("Select de audio: "+sql);
    
        ResultSet rs = stat.executeQuery(sql);
        
        if (rs.next()){
        	audio = new Audio(rs.getString("level"), rs.getString("name"), 
        			 rs.getInt("numaudio"), rs.getString("filename"));
        	
        }
        else {//System.out.println("AudioManager loadAudio NO ha devuelto registros.");
        	}
        
        
	}
	
        catch(SQLException ex){
			ex.printStackTrace();	
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			data.closeConn(conn);		
			return audio;
		}
}
}
