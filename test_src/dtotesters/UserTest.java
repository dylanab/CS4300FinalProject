package pt;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class UserTest extends TestCase {

	@Before
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void testConstructor() {
	    //test the normal constructor
	    User instance = new User(1, "name", "role", "pass", "image");

	    assertEquals("user id", 1, instance.getId());
	    assertEquals("user name", "name", instance.getName());
	    assertEquals("user role", "role", instance.getRole());
	    assertEquals("user pass", "pass", instance.getPassword());
	    assertEquals("user image", "image", instance.getImage_path());

	    //test the no id constructor
	    User instance = new User("name", "role", "pass", "image");

            assertEquals("user name", "name", instance.getName());
            assertEquals("user role", "role", instance.getRole());
            assertEquals("user pass", "pass", instance.getPassword());
            assertEquals("user image", "image", instance.getImage_path());

	    //test the no arg constructor
            User instance = new User();

            assertEquals("user name", null, instance.getName());
            assertEquals("user role", 0, instance.getRole());
            assertEquals("user pass", null, instance.getPassword());
            assertEquals("user image", null, instance.getImage_path());
	}

}
