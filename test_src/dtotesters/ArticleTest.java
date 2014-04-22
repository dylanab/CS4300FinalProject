package pt;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class ArticleTest extends TestCase {

	@Before
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Test
	/**
 	* Verify that an Article object contains the correct data
 	*/
	public void testConstructor() {
	    User user = new User();
	    Article instance = new Article(1, user, "title", "text", "categories", "image");

	    assertEquals("article id", 1, instance.getId());
	    assertEquals("user object", user, instance.getUser());
	    assertEquals("title", "title", instance.getTitle());
	    assertEquals("text", "text", instance.getText());
	    assertEquals("categories", "categories", instance.getCategories());
	    assertEquals("image", "image", instance.getImagePath());
	}

}
