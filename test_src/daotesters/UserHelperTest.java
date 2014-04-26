package daotesters;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import daos.*;
import dtos.*;
public class UserHelperTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testConstructor() {
		UserHelper instance = new UserHelper("jdbc:mysql://172.17.152.92/testpolitalk", "luke", "ukulele5");
		assertNotNull("Constructor not null", instance);
	    
	}

	
	/**
	* Tests that adding a user to the database works properly
	*/
	@Test
	public void addUserTest() throws Exception{
		UserHelper instance = new UserHelper("jdbc:mysql://172.17.152.92/testpolitalk", "luke", "ukulele5");
	    ArrayList<User> userList = new ArrayList<User>();

	    User u = new User("luke", 4, "pass", "path");
	    instance.setUsername(u.getName());
	    instance.setRole(u.getRole());
	    instance.setPassword(u.getPassword());
	    instance.setImage_path(u.getImage_path());
	    instance.addNewUser();

	    userList = instance.getUsers();

	    assertEquals("length after one user insert", 1, workerList.size());
	    assertEquals("username", "luke", userList.get(0).getName());
	    assertEquals("passowrd", "pass", userList.get(0).getPassword());
	    assertEquals("role", "admin", userList.get(0).getRole());
	    

	}//addUserTest
	

	/**
 	* Test for changing a user's password	 
 	*/ 
	@Test
	public void passwordChangeTest(){

		UserHelper instance = new UserHelper("jdbc:mysql://172.17.152.92/testpolitalk", "luke", "ukulele5");
	    ArrayList<User> userList = new ArrayList<User>();
	
	    instance.setUsername("luke");
            instance.setPassword("pass");
            instance.setRole(4);
            instance.setImage_path("path");
            instance.addUser();

	    userList = instance.getUsers();

	    instance.setUid(userList.get(0).getId());
	    instance.setPassword("newpass");
	    instance.changePassword();

	    userList = instance.getUsers();

	    assertEquals("change pass", "newpass", userList.get(0).getPassword());

	}//passwordChangeTest()

	
	/**
	 * Test changing the role of a user
	 */
	@Test
	public void roleChangeTest(){
		UserHelper instance = new UserHelper("jdbc:mysql://172.17.152.92/testpolitalk", "luke", "ukulele5");
	    ArrayList<User> userList = new ArrayList<User>();
	
	    instance.setUsername("luke");
            instance.setPassword("pass");
            instance.setRole(3);
            instance.setImage_path("path");
            instance.addUser();

	    userList = instance.getUsers();
	    
	    instance.setRole(4);
	    instance.setUid(userList.get(0).getId());
	    instance.changeRole();
	    
	    userList = instance.getUsers();
	    assertEquals("change role", 4, userList.get(0).getRole());
	    
	    
	}
	
	/**
	 * Test the validate user method
	 */
	@Test
	public void validateTest(){
		UserHelper instance = new UserHelper("jdbc:mysql://172.17.152.92/testpolitalk", "luke", "ukulele5");
		
	    instance.setUsername("luke");
        instance.setPassword("pass");
        instance.setRole(3);
        instance.setImage_path("path");
        instance.addUser();
        
        User u = instance.validate("luke", "pass");
        
        assertEquals("validation test", 1, u.getId());
	}
}//class
