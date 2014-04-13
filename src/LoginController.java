package pt;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/Login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginController() {
        
    }

	/**
	 * Idempotent actions: 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
		ServletContext ctx = this.getServletContext();
		RequestDispatcher dispatcher;
	    } catch(Exception e) {
		e.getMessage();
	    }
	}//doGet

	/**
	 * Non-Idempotent actions: Login
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try{
		ServletContext ctx = this.getServletContext();
		RequestDispatcher dispatcher;
	    }//try
	    catch(Exception e){
		e.getMessage();
	    }
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");

	    String hex_pass = Encrypyer.toSha256(password);

	    UserHelper userhelper = new UserHelper();
	    User user = userhelper.validate(username, hex_pass);
	    if(user != null) {
		//login successful.
		//store the user info in the session.
		request.getSession().setAttribute("id", user.getId());
		request.getSession().setAttribute("username", user.getName());
		request.getSession().setAttribute("role", user.getRole());
		//route to profile, might need to change this to a different jsp.
		dispatcher = ctx.getRequestDispatcher("/profile.jsp");
	    } else {
		//login unsuccessful, route back to login with a thing to set off the c:if
		request.setAttribute("error", "name/pass incorrect");
		dispatcher = ctx.getReqestDispatcher("/login.jsp");
	    }
	    
	}//doPost
}//class
