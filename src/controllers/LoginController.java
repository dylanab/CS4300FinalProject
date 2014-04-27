package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import daos.*;
import security.*;
import dtos.*;
/**
 * Servlet implementation class LoginController
 * Author: Dylan Bowne
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
		RequestDispatcher dispatcher;
		ServletContext ctx;
	    try {
		ctx = this.getServletContext();
		dispatcher = ctx.getRequestDispatcher("/WEB-INF/view/login.jsp");
	    dispatcher.forward(request, response);
	    } catch(Exception e) {
		e.getMessage();
	    }
	}//doGet

	/**
	 * Non-Idempotent actions: Login
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		ServletContext ctx;
	    try{
		ctx = this.getServletContext();

	    String username = request.getParameter("user");
	    String password = request.getParameter("pass");
	    
	    username = username.trim();
	    password = password.trim();

	    String hex_pass = Encrypter.toSha256(password);

	    String db_user = "testuser";
	    String db_pass = "testpass";
	    String db_url = "jdbc:mysql://localhost/testdb";

	    UserHelper userhelper = new UserHelper(db_url, db_user, db_pass);
	    User user = userhelper.validate(username, hex_pass);
	    if(user != null) {
		//login successful.
		//store the user info in the session.
		request.getSession().setAttribute("id", user.getId());
		request.getSession().setAttribute("username", user.getName());
		request.getSession().setAttribute("role", user.getRole());
		//route to profile, might need to change this to a different jsp.
		dispatcher = ctx.getRequestDispatcher("/WEB-INF/view/profile.jsp");
	    } else {
		//login unsuccessful, route back to login with a thing to set off the c:if
		request.setAttribute("error", "name/pass incorrect");
		dispatcher = ctx.getRequestDispatcher("/WEB-INF/view/login.jsp");
	    }
	    dispatcher.forward(request, response);
	    }//try
	    catch(Exception e){
		e.getMessage();
		response.sendRedirect("/Login");
	    }
	}//doPost
}//class
