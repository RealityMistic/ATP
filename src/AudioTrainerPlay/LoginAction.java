package AudioTrainerPlay;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Player;
import model.Data2;
import model.UserManager;
/**
 * Servlet implementation class LoginAction
 */
@WebServlet("/LoginAction")
public class LoginAction extends HttpServlet {
	static String path;
	private static final long serialVersionUID = 1L;
	public void init(ServletConfig servletConfig) throws ServletException{
	    super.init(servletConfig);
	    path = servletConfig.getServletContext().getRealPath("/WEB-INF");
	//    System.out.println("LoginAction init: "+path);
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user, pass;

		user = request.getParameter("user");
		pass = request.getParameter("password");

		UserManager um = new UserManager(path);
		
		if (um.loginUser(user, pass)){
			// Pantalla de bienvenida
//			logger.log(Level.SEVERE, "User {0} logged in", user);
			Cookie atpUserCookie = new Cookie("ATPuser", user);
			atpUserCookie.setMaxAge(60*60);
			response.addCookie(atpUserCookie);
			HttpSession s=request.getSession();
			Player player = null;
			player = um.loadPlayer(user, pass);
		//	System.out.println("Name " + player.getName() + " User "+ player.getUser() +
		//			"Email " + player.getEmail() + " Gender "+ player.getEdlevel() 
		//			+ " Pass " + player.getPass() + " Age " + player.getAge()  
		//			+ " Jourpoints " + player.getJourpoints() + " Explopoints " + player.getExplopoints());
			
			s.setAttribute("player", player);
			Data2 data = new Data2(path);
			FileWriter fstream = new FileWriter(data.ReadLoginLogroute(),true);
     	    BufferedWriter fbw = new BufferedWriter(fstream);
			String pString = user + "," + Utils.now();
			fbw.write(pString);
    	    fbw.newLine();
           	fbw.close();
			
		//	CSVWriter writer = new CSVWriter(new FileWriter("/home/juan/workspace/ATP/src/resource/login-file.csv"), ',');
		//	String loginentry = user+","+Utils.now();
		//	String[] entries=loginentry.split(",");
		//	writer.writeNext(entries);
		//	writer.close();
			request.getRequestDispatcher("bienvenida.html").forward(request, response);
	
		} 
		else {
//			logger.log(Level.SEVERE, "Somebody failed to log in with username {0} registered", user);
			request.getRequestDispatcher("login-error.html").forward(request, response);
	
		}
	
	}


}
