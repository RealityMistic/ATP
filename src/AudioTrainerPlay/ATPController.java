package AudioTrainerPlay;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Player;

/**
 * Servlet implementation class atpController
 */
@WebServlet("/ATPController")
public class ATPController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	static String path;
	Player player;
    /**
     * @see HttpServlet#HttpServlet()
     */
	
				

	
		
    public ATPController() {
        super();
    }
        public void init(ServletConfig servletConfig) throws ServletException{
    	    super.init(servletConfig);
    	    path = servletConfig.getServletContext().getRealPath("/WEB-INF");
    	    
    	}
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = "";
		String url="index.html";
		action = request.getParameter("action");
		HttpSession session = request.getSession(true);
		session.setAttribute("path", path);
		// boolean newjournalistgame;
		boolean continueexplorergame;

      //  SimpleDateFormat format = new SimpleDateFormat("M-d_HHmmss"); 
        
		
		switch (action){
			case "doRegister":
//				System.out.println("ATPController sent a RegisterAction");
				url="RegisterAction";
				break;
			case "doUpdate":
				url="UpdateAction";
				break;
			case "doDelete":
				url="DeleteAction";
				break;
			case "doLogin":
//				System.out.println("ATPController sent a LoginAction");
				url="LoginAction";
				break;
		/*	case "doStartJournalist":
				url="JournalistAction";
				newjournalistgame=true;
				request.setAttribute("newjournalistgame", new Boolean(newjournalistgame));
				break;
		*/
			case "doStartExplorer":
				url="ExplorerAction";
				continueexplorergame=false;
				session.setAttribute("newexplorergame", new Boolean(continueexplorergame));
				break;
		/*	case "doContinueJournalist":
				url="JournalistAction";
				newjournalistgame=false;
				request.setAttribute("newjournalistgame", new Boolean(newjournalistgame));
				break; 
		*/
			case "doContinueExplorer":
				url="ContinueExplorerAction";
				
				break;
			case "doOptions":
				url="options.html";
				break;
			case "doAbout":
				url="about.html";
				break;
			case "doHall-Fame":
				url="hall-of-fame.jsp";
				break;
		}
		request.getRequestDispatcher(url).forward(request, response);
	}		
		
		
		
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
