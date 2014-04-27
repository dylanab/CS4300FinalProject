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
public class UserHelperTest extends TestCase {

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
	    assertEquals("role", 4 , userList.get(0).getRole());
	    

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

       /**
        *Test getting a single user
        */
        @Test
        public void testGetUser() {

	    UserHelper instance = new UserHelper("jdbc:mysql://172.17.152.92/testpolitalk", "luke", "ukulele5");
            ArrayList<User> userList = new ArrayList<User>();

            User user = new User("dylan", 4, "pass", "path");
            instance.setUsername(u.getName());
            instance.setRole(u.getRole());
            instance.setPassword(u.getPassword());
            instance.setImage_path(u.getImage_path());
            instance.addNewUser();

            userList = instance.getUsers();

	    int u_id = userList.get(0).getId();

	    instance.setUid(u_id);

	    User returned_user = instance.getUser();

	    assertNotNull(returned_user);

            assertEquals("username", "dylan", returned_user.getName());
            assertEquals("passowrd", "pass", returned_user.get(0).getPassword());
            assertEquals("role", 4, returned_user.getRole());

	}
	

}//class
