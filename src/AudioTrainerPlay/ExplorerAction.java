package AudioTrainerPlay;

import java.io.IOException;
import java.util.logging.Level;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class atpExplorer
 */
@WebServlet("/ExplorerAction")
public class ExplorerAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String path;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public void init(ServletConfig servletConfig) throws ServletException{
	    super.init(servletConfig);
	    path = servletConfig.getServletContext().getRealPath("/WEB-INF");
	//    System.out.println("ExplorerAction init: "+path);
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	logger.log(Level.SEVERE, "User {0} started a EXPLORER game",player.getName());
		HttpSession session = request.getSession(true);
		session.setAttribute("newexplorergame", new Boolean(false));
		request.getRequestDispatcher("which-level.html").forward(request,response);
	}

}
