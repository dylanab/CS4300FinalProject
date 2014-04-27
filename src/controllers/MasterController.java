package controllers;

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
import dtos.*;
import daos.*;

/**
 * Servlet implementation class MasterController
 * Author: Luke Grantham
 */
@WebServlet(
	description = "Handles all urls except from login ",
	urlPatterns = {
	    "/adminControls",
	    "/profile",
	    "/modControls",
	    "/editorView",
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
	 * Non-idempotent actions: dispatch to all the pages except login
	 * 			   attach article to singleArticle/editorView dispatch
	 * 			   attach user object dispatch
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try{

	int role = -1;
	int article_id = -1;
	int user_id = -1;
	
	HttpSession session = request.getSession();
        String session_user = (String)session.getAttribute("username");
        Integer session_role = (Integer)session.getAttribute("role");
        Integer session_id = (Integer)session.getAttribute("id");


	/* get parameters */
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String confirmpass = request.getParameter("confirmpass");
	String articleTitle = request.getParameter("articleTitle");
	String articleContent = request.getParameter("articleContent");
	String category = request.getParameter("category");

        if( (request.getParameter("role")) != null ){
            role = Integer.parseInt( request.getParameter("role") );
        }
	if( (request.getParameter("article_id")) != null ){
            article_id = Integer.parseInt( request.getParameter("article_id") );
        }
	if( (request.getParameter("user_id")) != null ){
            user_id = Integer.parseInt( request.getParameter("user_id") );
        }

        /* trim strings user, pass, confirmpass */
        user = user.trim();
        pass = pass.trim();
        confirmpass = confirmpass.trim();


	ServletContext ctx = this.getServletContext();
        RequestDispatcher dispatcher;	

	String userPath = request.getServletPath();
	
	if(userPath.equals("/index")){
	    /* dispatch to index */
	    dispatcher = ctx.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
	}
	else if(userPath.equals("/adminControls")){
		/* request to access admincontrols */
		if(session_role == 4){
		    dispatcher = ctx.getRequestDispatcher("/adminControls.jsp");
		    dispatcher.forward(request, response);
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
	    /* dispatch to articleview */
	    Article article_object;
	    ArticleHelper helper = new ArticleHelper();
	    helper.setArticle_id(article_id);
	    article_object = helper.getArticle();	

	    if(article_object != null){
		request.setAttribute("article_object", article_object);
	        dispatcher = ctx.getRequestDispatcher("/articleView.jsp");
                dispatcher.forward(request, response);
	    }
	    else{
		dispatcher = ctx.getRequestDispatcher("/error.jsp");
                dispatcher.forward(request, response);
	    }
	} 
	else if(userPath.equals("/createArticle")){
	    /* bleeeeh */
	    
	}
	else if(userPath.equals("/editorView")){ /* dispatch to editArticle */
	        Article article_object;
	        ArticleHelper helper = new ArticleHelper();
		helper.setArticle_id(article_id);
	        article_object = helper.getArticle();
	    
	        if(article_object != null){
	    	    request.setAttribute("article_object", article_object);
	            dispatcher = ctx.getRequestDispatcher("/editorView.jsp");
                    dispatcher.forward(request, response);
	        }
	        else{
		    dispatcher = ctx.getRequestDispatcher("/error.jsp");
                    dispatcher.forward(request, response);
	        }
	    
	}
	else{
	    /* dispatch to profile page */
	    User user_object;
	    UserHelper helper = new UserHelper();
	    user_object = helper.getUser(user_id);

	   if(user_object != null){
	       request.setAttriubte("user_object", user_object);
	       dispatcher = ctx.getRequestDispatcher("/profile.jsp");
               dispatcher.forward(request, response);
	   }
	   else{
	       dispatcher = ctx.getRequestDispatcher("/error.jsp");
               dispatcher.forward(request, response);
	   }
   
	}


    }//try
    catch(Exception e){
	e.getMessage();
    }
  }//doGet

	/**
	 * Idempotent actions: remove an article
	 * 		       add new user
	 * 		       change user password
	 * 		       change user role
	 * 		       edit article
	 *
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

        if(userPath.equals("/index")){
        }
        else if(userPath.equals("/adminControls")){
	    
	    if(pass == null){ /* add a new user */
		if(pass == confirmpass){
		    Encrypter.toSha256(pass);
		    UserHelper helper = new Userhelper();
		    helper.setUsername(user);
		    helper.setPassword(pass);
		    helper.setRole(role);
		    helper.setImage_path("default.jpg");
		    helper.addNewUser();
	    	    dispatcher = ctx.getRequestDispatcher("/adminControls");
		    dispatcher.forward(request,response);
		}
	    }
	    else if(role == -1){ /* request to change a user's password */
		if(pass == confirmpass){
		    UserHelper helper = new UserHelper();
		    Encrypter.toSha256(pass);
		    helper.setUsername(user);
		    helper.setPassword(pass);
		    helper.changePassword();
		}
	    }
	    else{ /* request to change a user's role */
		UserHelper helper = new UserHelper();
                helper.setUsername(user);
                helper.setRole(role);
                helper.changeRole();
		dispatcher = ctx.getRequestDispatcher("/adminControls");
	 	dispatcher.forward(request, response);
	    }
        }
        else if(userPath.equals("/modControls")){
	    /* request to remove an article */
	    ArticleHelper helper = new ArticleHelper();
	    helper.setArticle_id(article_id);
	    helper.removeArticle();
	
	    dispatcher = ctx.getRequestDispatcher("/modControls");
            dispatcher.forward(request, response);

        }
        else if(userPath.equals("/articleView")){
	    
        }
        else if(userPath.equals("/createArticle")){
	    /* compose new article */
            ArticleHelper helper = new ArticleHelper();
            Article article_object = new article(articleTitle, articleContent, "article_default.jpg", category, 0, session_user);
            helper.addArticleToDB(article_object);
            request.setAttribute("article_object", article_object);
            dispatcher = ctx.getRequestDispatcher("/articleView.jsp");
            dispatcher.forward(request, response);
        }
	else if(userPath.equals("/editArticle")){
		/* TODO */
	}
        else{//profile
        }


    }
    catch(Exception e){
	e.getMessage();
    }		
  }//doPost
}

