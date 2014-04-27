package daotesters;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

import java.util.ArrayList;

import daos.*;
import dtos.*;

public class ArticleHelperTest extends TestCase{

    @Before
    public void setUp() throws Exception {
	super.setUp();

        String JDBC_URL = "jdbc:mysql://172.17.152.92/testpolitalk";
        String DB_USER = "luke";
        String DB_PASS  = "ukulele5";

        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                                                          JDBC_URL, DB_USER, DB_PASS);
            PreparedStatement clearUserStatement =
                conn.prepareStatement("delete from Users");
            PreparedStatement clearArticleStatement =
                conn.prepareStatement("delete from Articles");

            clearArticleStatement.execute();
            clearUserStatement.execute();

            System.out.println("Cleared all db tables");
        }
        catch(SQLException sqle) {
            System.out.println("Exception in setUp:" +
                               sqle.getMessage());
        }
    }

    @Test
	public void testConstructor() {
	ArticleHelper instance = new ArticleHelper("jdbc:mysql://172.17.152.92/testpolitalk", "luke", "ukulele5");
	assertNotNull("Constructor not null", instance);
	//assertNotNull("",);

    }


    /**
     * Tests that adding a user to the database works properly
     */
    @Test
    public void submitArticleTest() throws Exception{
	ArticleHelper instance = new ArticleHelper("jdbc:mysql://172.17.152.92/testpolitalk", "luke", "ukulele5");
	ArrayList<Article> articleList = new ArrayList<Article>();
	
	Article article1 = new Article(1, "Article 1", "lolololol", "path", "something, something else", 0, 1, 2);
	instance.addArticleToDB(article1);

	articleList = instance.getArticles();

	assertEquals("length after one article insert", 1, articleList.size());
	assertEquals("author id", 1, articleList.get(0).getAuthor_id());
	assertEquals("text", "lolololol", articleList.get(0).getText());
	assertEquals("response", 2, articleList.get(0).getResponse_id());
	assertEquals("imagepath", "path", articleList.get(0).getImage_path());


    }//addArticleTest

    /**
     * Test for editing an article's text
     */
    @Test
    public void updateArticleText() throws Exception{
    	ArticleHelper instance = new ArticleHelper("jdbc:mysql://172.17.152.92/testpolitalk", "luke", "ukulele5");
    	ArrayList<Article> articleList = new ArrayList<Article>();
    	
    	Article article1 = new Article(1, "Article 1", "lolololol", "path", "something, something else", 0, 1, 2);
    	instance.addArticleToDB(article1);
    	
    	article1 = new Article(1, "Article 1 edited", "lolololol", "path", "something, something else", 0, 1, 2);
    	instance.editArticle(article1);
    	
    	articleList = instance.getArticles();
    	assertEquals("new article name", "Article 1 edited", articleList.get(0).getTitle());
    }

    /**
     *Test article removal
     */
     public void removeArticleTest() throws Exception {
	 ArticleHelper instance = new ArticleHelper("jdbc:mysql://172.17.152.92/testpolitalk", "luke", "ukulele5");
	 ArrayList<Article> articleList = new ArrayList<Article>();

	 Article article = new Article(1, "Article is here", "lolololol", "path", "something, something else", 0, 1, 2);
	 instance.addArticleToDB(article);

	 articleList = instance.getArticles();
	 assertEquals("new article name", "Article is here", articleList.get(0).getTitle());
	 
	 int a_id = articleList.get(0).getId();
	 instance.setArticle_id(a_id);
	 instance.removeArticle();

	 //check for article, shouldn't find it. NOTE: this also partially tests checkForArticle
	 instance.setArticle_id(a_id);
	 Article foundArticle = instance.getArticle();
	 assertNull(foundArticle);
     }

    /**
     *Test checkForArticle 
     */
    public void checkForArticleTest() throws Exception {
	ArticleHelper instance = new ArticleHelper("jdbc:mysql://172.17.152.92/testpolitalk", "luke", "ukulele5");
	ArrayList<Article> articleList = new ArrayList<Article>();

	Article article = new Article(1, "Article Title", "lolololol", "path", "something, something else", 0, 1, 2);
	instance.addArticleToDB(article);

	articleList = instance.getArticles();
	assertEquals("new article name", "Article Title", articleList.get(0).getTitle());

	int a_id = articleList.get(0).getId();

	//check for article, should find it.
	instance.setArticle_id(a_id);
	Article foundArticle = instance.getArticle();
	assertNotNull(foundArticle);
    }

    /**
     *Test articleSearch
     *author: Dylan Bowne
     */
    public void testArticleSearch() throws Exception {
	ArticleHelper instance = new ArticleHelper("jdbc:mysql://172.17.152.92/testpolitalk", "luke", "ukulele5");
        ArrayList<Article> articleList = new ArrayList<Article>();

        Article article1 = new Article(1, "Article Title1", "lolololol", "path", "something, something else", 0, 1, 2);
        Article article2 = new Article(2, "Article Title2", "DERDEDRERERDER", "path", "something, something else", 0, 1, 2);
        Article article3 = new Article(3, "Article Title3", "hi", "path", "something, something else", 0, 2, 2);

        instance.addArticleToDB(article1);        
	instance.addArticleToDB(article2);        
	instance.addArticleToDB(article3);

	instance.setAuthor_id(1);
	instance.setSearchString("Title2");
	articleList = instance.articleSearchOfUser();

	assertNotNull(articleList.get(0));
	assertEquals(articleList.get(0).getText, "DERDEDRERERDER");

	assertEquals("should only find one article", 1, articleList.size());
        assertEquals("author id", 1, articleList.get(0).getAuthor_id());
        assertEquals("text", "DERDEDRERERDER", articleList.get(0).getText());
        assertEquals("response", 2, articleList.get(0).getResponse_id());
        assertEquals("imagepath", "path", articleList.get(0).getImage_path());
        assertEquals("categories", "something, something else", articleList.get(0).getCategories());

    }

}//class