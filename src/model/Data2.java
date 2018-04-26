package model;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Servlet implementation class ReadConf_
 */
@WebServlet("/Data2")


public class Data2 extends HttpServlet {
	static Scanner sc;
	static File file;
	static String path;
	static String endpath="/ATP.conf";
	private static String driver = "org.sqlite.JDBC";
//	private static final String bd = "jdbc:sqlite:C:/Users/cesar/Google Drive/Grado Software/PFG/ATLAS-workspace/ATP-Juanjo/src/resource/ATP.db";
// No funciona..
	
	public void init(ServletConfig servletConfig) throws ServletException{
	    super.init(servletConfig);
	    path = servletConfig.getServletContext().getRealPath("/WEB-INF");
	    System.out.println("INIT DATA: "+path);
	}
	
	public Data2(String pathp) {
		path = pathp;
		// TODO Auto-generated constructor stub
	}

	public Connection getConn(){
		String bd = ReadDBchain();
	//	System.out.println("bd "+bd);
		Connection cn = null;
		  try{
	           Class.forName(driver);
	           cn=DriverManager.getConnection(bd);
	      }
		  catch(ClassNotFoundException | SQLException ex){
	            ex.printStackTrace();
	        }
	        
	        return cn;
	   }
	
	public void closeConn(Connection cnp){
		 if(cnp!=null){
	          try {
	            cnp.close();
	          } catch (SQLException ex) {
	               ex.printStackTrace();
	          }
	       }
	}
	public  String ReadDBchain(){
		
		String conffile = path + endpath;
//		System.out.println("readdbchain conffile "+conffile);

		String line="";
	try {
		
		// file = new File("ATP.conf");
		sc = new Scanner(new File(conffile) );
		line = sc.nextLine();
//		System.out.println("Config file 1st line: "+ line);
		
	}  finally {
		return line;
	}
}
public String ReadLoginLogroute(){
	String conffile = path + endpath;
//	System.out.println("read login conffile "+conffile);
	String line="";
	try {
		
		sc = new Scanner(new File(conffile));
		line = sc.nextLine();
//		System.out.println("1 - "+line);
		line = sc.nextLine();
//		System.out.println("2 - "+line);
		
	}  finally{
		return line;
	}
}
public String ReadAudioRoute(){
	String conffile = path + endpath;
//	System.out.println("readaudio conffile "+conffile);
	String line="";
try {
		
		sc = new Scanner(new File(conffile) );
		line = sc.nextLine();
	//	System.out.println("1 - "+line);
		line = sc.nextLine();
	//	System.out.println("2 - "+line);
		line=sc.nextLine();
	//	System.out.println("3 - "+line);
		line = sc.nextLine();
//		System.out.println("4 - "+line);
		
	} finally {
		return line;
	}
}
public  String ReadGameLogroute(){
	String conffile = path + endpath;
//	System.out.println("readgame conffile "+conffile);
	String line="";
	try {
		
		sc = new Scanner(new File(conffile) );
		line = sc.nextLine();
//		System.out.println("1 - "+line);
		line = sc.nextLine();
//		System.out.println("2 - "+line);
		line=sc.nextLine();
//		System.out.println("3 - "+line);
		
	} finally {
		return line;
	}
}


}
