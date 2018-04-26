package AudioTrainerPlay;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequestWrapper;
/**
 * Servlet implementation class ContinueExplorerAction
 */
@WebServlet("/ContinueExplorerAction")
public class ContinueExplorerAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContinueExplorerAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		boolean continueexplorergame=true;
		session.setAttribute("continueexplorergame",  new Boolean(continueexplorergame));
		request.getRequestDispatcher("ExplorerPlay.jsp").forward(request,response);
	}

}
