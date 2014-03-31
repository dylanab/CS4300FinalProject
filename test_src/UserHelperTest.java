package pt;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class UserHelperTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testConstructor() {
	    UserHelper instance = new UserHelper();
	    //assertNotNull("",);	
	    
	}

	
	/**
	* Tests that adding a user to the database works properly
	*/
	public void addUserTest() throws Exception{
	    UserHelper instance = new UserHelper();
	    ArrayList<User> userList = new ArrayList<User>();

	    instance.setName("luke");
	    instance.setPassword("pass");
	    instance.setRole("admin");
	    instance.setImage_path("path");
	    instance.addUser();

	    userList = instance.getUserList();

	    assertEquals("length after one user insert", 1, workerList.size());
	    assertEquals("username", "luke", userList.get(0).getName());
	    assertEquals("passowrd", "pass", userList.get(0).getPassword());
	    assertEquals("role", "admin", userList.get(0).getRole());
	    

	}//addUserTest
	

	/**
 	* Test for changing a user's password	 
 	*/ 
	public void passwordChangeTest(){

	    UserHelper instance = new UserHelper();
	    ArrayList<User> userList = new ArrayList<User>();
	
	    instance.setName("luke");
            instance.setPassword("pass");
            instance.setRole("admin");
            instance.setImage_path("path");
            instance.addUser();

	    userList = instance.getUserList();

	    instance.setId(userList.get(0).getId());
	    instance.setPassword("newpass");
	    instance.changePassword();

	    userList = instance.getUserList();

	    assertEquals("change pass", "newpass", userList.get(0).getPassword());



	}//passwordChangeTest()


	/**
 	* Test for updating a user's profile image
 	*/
	public void updateUserImage() throws Exception(){}




}//class
