package pt;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Servlet implementation class MasterController
 */
@WebServlet(
	description = "The ",
	urlPatterns = {
	    "/adminControls",
	    "/profile",
	    "/modControls",
	    "/editingView",
	    "/index",
	    "/articleView"
	  })
public class MasterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public MasterController() {
        
    }

	/**
	 * Non-idempotent actions: send articleList to mainpage
	 * 			   send a single article to singleArticle
	 * 			   dispatch to articleView
	 * 			   dispatch to mainView
	 * 			   dispatch to profile
	 *
	 * 			   String userPath = request.getServletPath(); 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try{

	int role = -1;
	
	HttpSession session = request.getSession();
        String session_user = (String)session.getAttribute("username");
        Integer session_role = (Integer)session.getAttribute("role");
        Integer session_id = (Integer)session.getAttribute("id");


	/* get parameters */
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String confirmpass = request.getParameter("confirmpass");
        if( (request.getParameter("role")) != null ){
            role = Integer.parseInt( request.getParameter("worker_id") );
        }

        /* trim strings user, pass, confirmpass */
        user = user.trim();
        pass = pass.trim();
        confirmpass = confirmpass.trim();


	ServletContext ctx = this.getServletContext();
        RequestDispatcher dispatcher;	

	String userPath = request.getServletPath();
	
	if(userPath.equals("/index"){
	    /* TODO: dispatch to index */
	}
	else if(userPath.equals("/adminControls")){

	    if(role != -1){
		/* TODO: request to change a user's role */
		UserHelper helper = new UserHelper();
	    }
	    else{ 
		/* request to access admincontrols */
		if(session_role == 4){
		    dispatcher = ctx.getRequestDispatcher("/adminControls.jsp");
		    dispatcher.forward(request, response);
		}
	    }
	}//else if
	else if(userPath.equals("/modControls")){
	    /* request to access modcontrols) */	
	    if( (session_role == 3) || (session_role == 4) ){
		dispatcher = ctx.getRequestDispatcher("/modControls.jsp");
		dispatcher.forward(request, response);
	    }
	}//else if
	else if(userPath.equals("/articleView")){
	    /* dispatch to articleview TODO: set attributes */
	    dispatcher = ctx.getRequestDispatcher("/articleView.jsp");
            dispatcher.forward(request, response);
	} 
	else if(userPath.equals("/editingView")){
	    /* TODO: dispatch to editingView */
	}
	else{
	    /* dispatch to profile page TODO: attach attributes/search string */
	    dispatcher = ctx.getRequestDispatcher("/profile.jsp");
            dispatcher.forward(request, response);
	}


    }//try
    catch(Exception e){
	e.getMessage();
    }
  }//doGet

	/**
	 * Idempotent actions: remove an article
	 * 		       
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try{

	int role = -1;
	int article_id = -1;
	
        HttpSession session = request.getSession();
        String session_user = (String)session.getAttribute("username");
        String session_role = (String)session.getAttribute("role");
        Integer session_id = (Integer)session.getAttribute("id");



	/* get parameters */
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String confirmpass = request.getParameter("confirmpass");
        if( (request.getParameter("role")) != null ){
            role = Integer.parseInt( request.getParameter("role") );
        }
	
	if( (request.getParameter("article_id")) != null ){
            article_id = Integer.parseInt( request.getParameter("article_id") );
        }

        /* trim strings user, pass, confirmpass */
	user = user.trim();
	pass = pass.trim();
	confirmpass = confirmpass.trim();


        ServletContext ctx = this.getServletContext();
        RequestDispatcher dispatcher;

        String userPath = request.getServletPath();

        if(userPath.equals("/index"){
        }
        else if(userPath.equals("/adminControls")){
	    
	    if(role != -1){ /* TODO: request to add a user */
			    
	    }
	    else{ /* TODO: request to change a user's password */

	    }
        }
        else if(userPath.equals("/modControls")){
	    /* TODO: request to remove an article */
	    
        }
        else if(userPath.equals("/articleView")){
        }
        else if(userPath.equals("/editingView")){
        }
        else{//profile
        }


    }
    catch(Exception e){
	e.getMessage();
    }






		
  }//doPost







}//class
