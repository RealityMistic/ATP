package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Player;

public class TopTenPoints {
	static String path;
	
	public TopTenPoints(String pathp) {
		super();
		path = pathp;
	}

	public ArrayList<Player> getTopTenPoints(){
		

		Data2 data = new Data2(path);
		Connection conn = data.getConn();
		ArrayList<Player> topplayers = new ArrayList<Player>();

		try{
			Statement stat = conn.createStatement();
			String sql = "select * from player order by explopoints desc limit 10";
			ResultSet rs = stat.executeQuery(sql);
			
			while (rs.next()){
//					System.out.println("Un jugador encontrado "+ rs.getString("name") );
		        	topplayers.add(new Player(rs.getString("name"), rs.getString("user"), 
		        			rs.getString("email"), rs.getString("gender"), rs.getString("edlevel"), 
		        			rs.getString("pass"), rs.getInt("age"), rs.getInt("jourpoints"), rs.getInt("explopoints"))
		        			);
		        		
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
			return topplayers;
		}
	}
}
