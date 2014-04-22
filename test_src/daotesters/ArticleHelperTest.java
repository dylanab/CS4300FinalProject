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
	ArticleHelper instance = new ArticleHelper();
	//assertNotNull("",);

    }


    /**
     * Tests that adding a user to the database works properly
     */
    public void submitArticleTest() throws Exception{
	ArticleHelper instance = new ArticleHelper();
	ArrayList<Article> articleList = new ArrayList<Article>();

	instance.setAuthorId(1);
	instance.setCatagories("something, something else");
	instance.setImagePath("path");
	instance.setArticleText("lolololol");
	instance.setArticleResponse(2);
	instance.addArticle();

	articleList = instance.getArticleList();

	assertEquals("length after one article insert", 1, articleList.size());
	assertEquals("author id", 1, articleList.get(0).getAuthorId());
	assertEquals("text", "lolololol", articleList.get(0).getArticleText());
	assertEquals("response", 2, articleList.get(0).getArticleResponses());
	assertEquals("imagepath", "path", articleList.get(0).getArticleImagePath());


    }//addArticleTest

    /**
     * Test for editing an article's text
     */
    public void updateArticleText() throws Exception(){}

    /**
     *Test for updating an article's image
     */
    public void updateArticleImage() throws Exception(){}

    /**
     *Test removing an article
     */
    public void removeArticle() throws Exception() {}

    /**
     *Tests adding and removing catagories from articles
     */ 
    public void addRemoveCatagory() throws Exception() {}

    /**
     *Tests adding and removing responses from articles
     */
    public void addRemoveResponse() throws Exeception() {}


}//class