package dtotesters;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import dtos.*;
import daos.*;
public class UserTest extends TestCase {

	@Before
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void testConstructor() {
	    //test the normal constructor
	    User instance = new User(1, "name", 1, "pass", "image");

	    assertEquals("user id", 1, instance.getId());
	    assertEquals("user name", "name", instance.getName());
	    assertEquals("user role", 1, instance.getRole());
	    assertEquals("user pass", "pass", instance.getPassword());
	    assertEquals("user image", "image", instance.getImage_path());

	    //test the no id constructor
	    instance = new User("name", 2, "pass", "image");

            assertEquals("user name", "name", instance.getName());
            assertEquals("user role", 2, instance.getRole());
            assertEquals("user pass", "pass", instance.getPassword());
            assertEquals("user image", "image", instance.getImage_path());

	    //test the no arg constructor
            instance = new User();

            assertEquals("user name", null, instance.getName());
            assertEquals("user role", 0, instance.getRole());
            assertEquals("user pass", null, instance.getPassword());
            assertEquals("user image", null, instance.getImage_path());
	}

}
