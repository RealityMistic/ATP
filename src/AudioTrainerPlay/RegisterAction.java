package AudioTrainerPlay;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Player;
import model.Data2;
import model.UserManager;


@WebServlet("/RegisterAction")
public class RegisterAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String path;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public void init(ServletConfig servletConfig) throws ServletException{
	    super.init(servletConfig);
	    path = servletConfig.getServletContext().getRealPath("/WEB-INF");
	 //   System.out.println("RegisterAction init: "+path);
	}
    public RegisterAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	Logger logger = Logger.getLogger( ATPController.class.getName() );
	//	FileHandler fh;
		String name, user, email, gender, edlevel, pass;
		int age;
		UserManager um = new UserManager(path);
		
		name = request.getParameter("name");
		user = request.getParameter("user");
	//	pass = request.getParameter("pass");
		email = request.getParameter("email");
		gender = request.getParameter("gender");
		age = Integer.parseInt((request.getParameter("age")));
		edlevel = request.getParameter("edlevel");
		pass = request.getParameter("password");
	//	System.out.println("Name: "+name+" User: "+user+" Pass: "+ pass +" Email: "+email+" Gender: " + gender + " Age :"+age+" Edlevel: "+ edlevel);
				
	//	System.out.println("RegisterAction was ready to add an user");
		Player newplayer = new Player(name, user, email, gender, edlevel, pass, age, 0, 0);
		Data2 data= new Data2(path);
		if (um.addNewUser(newplayer).equals("OK")){
			FileWriter fstream = new FileWriter(data.ReadLoginLogroute(),true);
     	    BufferedWriter fbw = new BufferedWriter(fstream);
			String pString = user + "," + Utils.now();
			fbw.write(pString);
    	    fbw.newLine();
           	fbw.close();
           	HttpSession s=request.getSession();
    		s.setAttribute("player", newplayer);
			request.getRequestDispatcher("bienvenida.html").forward(request, response);
		} else {
			request.getRequestDispatcher("login-error.html").forward(request, response);
		}
		}
		
	//	logger.log(Level.SEVERE, "User {0} registered",name);
	}


