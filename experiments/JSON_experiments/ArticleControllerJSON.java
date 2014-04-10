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
@WebServlet("/Article")
public class ArticleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ArticleController() {
        
    }

	/**
	 * Non-idempotent actions: send articleList to mainpage
	 * 			   send a single article to singleArticle 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  try{
	ServletContext ctx = this.getServletContext();
        RequestDispatcher dispatcher;	

	int article_id = request.getParameter("article_id");

	if(article_id != null){ /* single article request */
	    ArticleHelper helper = new ArticleHelper();
	    Article article = helper.getArticle(article_id);
	    
	    JSONObject articleJSON  = new JSONObject();
	    articleJSON.put("article_id", article.getId());
	    articleJSON.put("author_id", article.getAuthor());
	    articleJSON.put("date", article.getDate());
	    articleJSON.put("text", article.getText());
	    //probably some other stuff

	    request.setAttribute("article", articleJSON);

	    dispatcher = ctx.getRequstDispatcher("/");
	    dispatcher.forward(request,response);

	}
	else{ /* main page article request */
	    ArticleHelper helper = new ArticleHelper();
	    ArrayList<Article> articleList;

	    articleList = helper.getArticleList();
	    request.setAttribute("articleList", articleList);
	    dispatcher = ctx.getRequestDispatcher("/");
	    dispatcher.forward(request, response);
	}

  }
  catch(Exception e){
    e.getMessage();
  }
	}//doGet

	/**
	 * Idempotent actions:
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
