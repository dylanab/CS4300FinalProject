package dtotesters;

import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;
import daos.*;
import dtos.*;
public class ArticleTest extends TestCase {

	@Before
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Test
	/**
 	* Verify that an Article object contains the correct data
 	*/
	public void testConstructors() {
	    //User user = new User();

	    //test normal constructor
	    Article instance = new Article(1, "title", "text", "image", "categories", 5, 10, 15);

	    assertEquals("article id", 1, instance.getId());
	    //assertEquals("user object", user, instance.getUser());
	    assertEquals("title", "title", instance.getTitle());
	    assertEquals("text", "text", instance.getText());
	    assertEquals("image", "image", instance.getImage_path());
	    assertEquals("categories", "categories", instance.getCategories());
	    assertEquals("hits", 5, instance.getHits());
	    assertEquals("author id", 10, instance.getAuthor_id());
	    assertEquals("response id", 15, instance.getResponse_id());

	    //test article without initializing text
	    instance = new Article(1, "title", "image", "categories", 15, 20, 25);

	    assertEquals("article id", 1, instance.getId());
            //assertEquals("user object", user, instance.getUser());
            assertEquals("title", "title", instance.getTitle());
            assertEquals("image", "image", instance.getImage_path());
            assertEquals("categories", "categories", instance.getCategories());
            assertEquals("hits", 15, instance.getHits());
            assertEquals("author id", 20, instance.getAuthor_id());
            assertEquals("response id", 25, instance.getResponse_id());

	    //test no arg constructor
	    instance = new Article();

            assertEquals("article id", 0, instance.getId());
            //assertEquals("user object", user, instance.getUser());
            assertEquals("title", null, instance.getTitle());
            assertEquals("image", null, instance.getImage_path());
            assertEquals("categories", null, instance.getCategories());
            assertEquals("hits", 0, instance.getHits());
            assertEquals("author id", 0, instance.getAuthor_id());
            assertEquals("response id", 0, instance.getResponse_id());
	    

	}

}
