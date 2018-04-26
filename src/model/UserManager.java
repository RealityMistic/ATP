package model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import beans.Player;

public class UserManager {
	@SuppressWarnings("finally")
	static String path;
	
	public UserManager(String pathp) {
		super();
		path = pathp;
	}
	public boolean updateExploPoints(String userp, int newpointsp){
		Data2 data = new Data2(path);
		
		Connection conn = data.getConn();
		int r=0;
		try{
			Statement stat = conn.createStatement();
		//	System.out.println("Inserting "+newpointsp);
			
			String sql = "update player set explopoints='"+ Integer.toString(newpointsp) +"' where user='"+userp+"';";
		//	System.out.println("SQL sentence: "+ sql);
			r=stat.executeUpdate(sql);
		}
		catch(SQLException ex){
			ex.printStackTrace();	
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			data.closeConn(conn);		
			if (r>0) return true;
			else return false;
		}
	}
	public Player loadPlayer(String user, String pass){
		Data2 data = new Data2(path);
		Connection conn = data.getConn();
	//	String userl, namel, edlevell, genderl, emaill;
	//	int agel, jourpointsl, explopointsl;
		Player playerl = null;
	try{
		
        
        Statement stat = conn.createStatement();
        String sql = "select * from player where user='"+ user +"' and pass='"+ pass +"';";
        ResultSet rs = stat.executeQuery(sql);
        
        if (rs.next()){
        	playerl = new Player(rs.getString("name"), rs.getString("user"), 
        			rs.getString("email"), rs.getString("gender"), rs.getString("edlevel"), 
        			rs.getString("pass"), rs.getInt("age"), rs.getInt("jourpoints"), rs.getInt("explopoints"));
        	
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
			return playerl;
		}
		
	}
	
	public boolean loginUser(String userp, String passp){
		Data2 data = new Data2(path);
		boolean result = false;
		Connection conn = data.getConn();
//		System.out.println("UserManager trying to login with user "+userp+" and password "+passp);
		try{
		Statement stat = conn.createStatement();
		String sql = "select * from player where user='"+ userp +"' and pass='"+ passp +"';";
		ResultSet rs=stat.executeQuery(sql);
		
		if (rs.next()){
			result=true;
//			System.out.println("User exists");
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
			return result;
		}
	}
	
	public boolean existsUser(Player existingp){
		Data2 data = new Data2(path);
		boolean result = false;
		Connection conn = data.getConn();
	try{
		
        
        Statement stat = conn.createStatement();
        String sql = "select * from player where user='"+ existingp.getUser()+"' and pass='"+existingp.getPass()+"';";
        ResultSet rs = stat.executeQuery(sql);
        
        if (rs.next()){
        	result = true;
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
			return result;
		}
		
	}
	public String addNewUser(Player newplayerp) {
		Data2 data = new Data2(path);
		Connection conn = data.getConn();
		String result = null;
		
		try {
			DatabaseMetaData dbm = conn.getMetaData();
			Statement stat = conn.createStatement();
			// check if "employee" table is there
			ResultSet tables = dbm.getTables(null, null, "player", null);
			if (!tables.next()) {
				stat.executeUpdate("create table player (name, user, email, gender, edlevel, pass, "
						+ "age integer, jourpoints integer, explopoints integer, lastlogin timestamp, lastlevel, lastaudio, primary key(user));");
			}	
	        
	       // stat.executeUpdate("drop table if exists player;");
	        
	        if (!existsUser(newplayerp)){
	       // 	System.out.println("addNewUser is adding an user");
	        	Timestamp curtime = new Timestamp(System.currentTimeMillis());
	        	PreparedStatement prep = conn.prepareStatement(
	            "insert into player values (?,?,?,?,?,?,?,'0','0',?,?,'1');");
	        	
	        	 prep.setString(1, newplayerp.getName());
	        	 prep.setString(2, newplayerp.getUser());
	        	 prep.setString(3, newplayerp.getEmail());
	        	 prep.setString(4, newplayerp.getGender());
	        	 prep.setString(5, newplayerp.getEdlevel());
	        	 prep.setString(6, newplayerp.getPass());
	        	 prep.setInt(7, newplayerp.getAge());
	        	 prep.setTimestamp(8, curtime);
	        	 prep.setString(9, "A1");

	             prep.execute();
//	             System.out.println("User added");
	             result ="OK";
	        } 

		}
		catch(SQLException ex){
			ex.printStackTrace();	
			
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
        finally {
        	data.closeConn( conn );
        	return result;
        }	
	}
}
