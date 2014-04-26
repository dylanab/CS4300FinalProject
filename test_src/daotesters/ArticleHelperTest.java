package daotesters;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import daos.*;
import dtos.*;

public class ArticleHelperTest {

    @Before
	public void setUp() throws Exception {
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
	assertEquals("author id", 1, articleList.get(0).getAuthorId());
	assertEquals("text", "lolololol", articleList.get(0).getArticleText());
	assertEquals("response", 2, articleList.get(0).getArticleResponses());
	assertEquals("imagepath", "path", articleList.get(0).getArticleImagePath());


    }//addArticleTest

    /**
     * Test for editing an article's text
     */
    @Test
    public void updateArticleText() throws Exception(){
    	ArticleHelper instance = new ArticleHelper("jdbc:mysql://172.17.152.92/testpolitalk", "luke", "ukulele5");
    	ArrayList<Article> articleList = new ArrayList<Article>();
    	
    	Article article1 = new Article(1, "Article 1", "lolololol", "path", "something, something else", 0, 1, 2);
    	instance.addArticleToDB(article1);
    	
    	article1 = new Article(1, "Article 1 edited", "lolololol", "path", "something, something else", 0, 1, 2);
    	instance.editArticle(article1);
    	
    	articleList = instance.getArticles();
    	assertEquals("new article name", "Article 1 edited", articleList.get(0).getTitle());
    }




}//class