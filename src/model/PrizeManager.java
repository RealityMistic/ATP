package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beans.FPrize;
import beans.Prize;

public class PrizeManager {
	static String path;
	
	public PrizeManager(String pathp) {
		super();
		path = pathp;
	}
	public FPrize getFinalPrize(int pointsp){
		Data2 data = new Data2(path);
		Connection conn = data.getConn();
			FPrize finalprize=null;
			int marginpoints=pointsp+500;
			try{
		        Statement stat = conn.createStatement();
		        String sql = "select * from fprizes p1 where threshold>="+pointsp
		        		+"  order by p1.threshold ASC limit 1"
		        		+ ";";
		        System.out.println(sql);
		        ResultSet rs = stat.executeQuery(sql);
		        
		        if (rs.next()){
		        	finalprize = new FPrize(rs.getString("type"), rs.getInt("threshold"), rs.getString("filename"));
//		        	System.out.println("Prize-filename "+rs.getString("filename"));
		        	
		        }
		        else {
		        	System.out.println("PrizeManager no ha devuelto registros.");
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
					return finalprize;
				}
		}
		
	
	public Prize getPrize(int pointsp, String levelp){
		Data2 data = new Data2(path);
		Connection conn = data.getConn();
		Prize currentprize=null;
		int marginpoints=pointsp+500;
		try{
	        Statement stat = conn.createStatement();
	        String sql = "select * from prizes p1 where threshold>="+pointsp
	        		+" and level='"+ levelp 
	        		+"' order by p1.threshold ASC limit 1"
	        		+ ";";
//	        System.out.println(sql);
	        ResultSet rs = stat.executeQuery(sql);
	        
	        if (rs.next()){
	        	currentprize = new Prize(rs.getString("level"), rs.getString("type"), rs.getInt("threshold"), rs.getString("filename"));
//	        	System.out.println("Prize-filename "+rs.getString("filename"));
	        	
	        }
	        else {System.out.println("PrizeManager no ha devuelto registros.");}
	        
		}
		
	        catch(SQLException ex){
				ex.printStackTrace();	
				
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			finally{
				data.closeConn(conn);		
				return currentprize;
			}
	}
}
