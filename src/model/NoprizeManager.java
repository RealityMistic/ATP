package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beans.Noprize;


public class NoprizeManager {
	static String path;
	public NoprizeManager(String pathp) {
		super();
		path = pathp;
	}

	public Noprize getNoprize(String levelp){
		Data2 data = new Data2(path);
		Connection conn = data.getConn();
		Noprize currentnoprize=null;
	try{
		
        Statement stat = conn.createStatement();
        String sql = "select * from noprizes where level='"+levelp 
        		+"' order by random() limit 1;";
 //       System.out.println(sql);
        ResultSet rs = stat.executeQuery(sql);
        
        if (rs.next()){
        	currentnoprize = new Noprize(rs.getString("type"), rs.getString("level"), rs.getString("filename"));
//        	System.out.println("Prize-filename "+rs.getString("filename"));
        	
        }
        else {System.out.println("getNoprize() no ha devuelto registros.");}
        
	}
	
        catch(SQLException ex){
			ex.printStackTrace();	
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			data.closeConn(conn);		
			return currentnoprize;
		}
}
}
