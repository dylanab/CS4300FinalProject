package pt;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class ArticleController
 */
@WebServlet(name = "FileController",urlPatterns = {"/media/*"})
    public class FileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor. 
	 */
	public FileController() {
        
	}

	/**
	 * Non-idempotent actions: serve static content
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try{
		ServletContext ctx = this.getServletContext();
		RequestDispatcher dispatcher = ctx.getRequestDispatcher();
	    } catch(Exception e){
		e.getMessage();
	    }
	    
	    String path = request.getPathInfo();
	  
	    dispatcher = ctx.getRequestDispatcher(path);
	    dispatcher.forward(request, response);
		  
	}//doGet

	/**
	 * Idempotent actions:
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        }

}