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
	    User instance = new User(1, "name", "role", "pass", "image");


	    assertEquals("user id", 1, instance.getId());
	    assertEquals("user name", "name", instance.getName());
	    assertEquals("user role", "role", instance.getRole());
	    assertEquals("user pass", "pass", instance.getPassword());
	    assertEquals("user image", "image", instance.getImage_path());
	}

}
