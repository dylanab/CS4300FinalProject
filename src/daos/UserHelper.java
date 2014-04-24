package daos;

import java.sql.*;
import java.util.ArrayList;
import dtos.*;
public class UserHelper {

	protected Connection conn = null;
	//protected Statement statement = null;
	protected PreparedStatement validateUser;
	protected PreparedStatement addUser;
	protected PreparedStatement selectUID;
	protected PreparedStatement changeUserPW;
	protected PreparedStatement changeUserRole;
	protected PreparedStatement getUser;
	protected PreparedStatement getUserList;
	/**
	 * Create the driver, connection, and prepared statement for the database
	 * @param url
	 * @param username
	 * @param password
	 */
	public UserHelper(String url, String username, String password) {
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance(); //create driver
			conn = DriverManager.getConnection(url, username, password); //connect to db passed to constructor
			if(conn == null){
				throw new Exception("Unable to connect to database");
			}
			//statement = conn.createStatement();
			//if(statement == null){
				//throw new Exception("Unable to create a valid statement for the database");
			//}
			validateUser = conn.prepareStatement("Select Uid, ProfilePicPath, Role from Users where Username =?, Password =?");
			addUser = conn.prepareStatement("Insert into Users (Username, Password, ProfilePicPath, Role ) vaules (?, ?, ?, ? )");
			selectUID = conn.prepareStatement("Select Uid from Users where Username =?");
			changeUserPW = conn.prepareStatement("UPDATE Users SET Password=? WHERE Uid=?");
			changeUserRole = conn.prepareStatement("UPDATE Users SET Role=? WHERE Username=?");
			getUser = conn.prepareStatement("Select * from Users Where Uid=?");
			getUserList = conn.prepareStatement("Select * from Users");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	 /**
	  * Validate the login of a user or return a null user if the user login is not in the database
	  * @param name
	  * @param password
	  * @return
	  */
	public User validate(String name, String password) {
		//String query = "Select Uid, ProfilePicPath, Role from Users where Username = " + name + ", Password = " + password;
		int uid = 0;
		String path = null;
		int role = 0;
		User u = null;
		ResultSet r;
		try {
			//if(statement.execute(query)) {
				//ResultSet r = statement.getResultSet();
				validateUser.setString(1, name);
				validateUser.setString(2, password);
				r = validateUser.executeQuery();
				while(r.next()) { //this resultSet should only have one result so the loop should only go once
					uid = r.getInt(1);
					path = r.getString(2);
					role = r.getInt(3);
				}
				u = new User(uid, name, role, password, path); //add parameters
		}catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}
	
	/**
	 * Adds a user to the database and returns a user DTO that contains a user id
	 * @param newUser
	 * @param npassword
	 * @return
	 */
	public void addNewUser() { //make prepared statement in aux methods
		try{
			addUser.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		//return toReturn;
	}
	
	/**
	 * Changes a users password and returns either the user if the change is successful
	 * or null if it is unsuccessful
	 * @param user
	 * @param nPassword
	 * @return
	 */
	public void changePW(){ //make prepared statements
		//User u = null;
		//String query = "UPDATE Users SET Password='"+nPassword+"' WHERE Uid='"+user.getId()+"'";
		try{
			//changeUserPW.setString(1, nPassword);
			//changeUserPW.setInt(2, user.getId());\
			changeUserPW.executeUpdate();
			//updateReturn = statement.executeUpdate(query);
		}catch(Exception e){
			e.printStackTrace();
		}
		//return u;
	}
	
	/**
	 * Change the password of a user
	 */
	public void changeRole() {
		try{
			changeUserRole.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Set the username in the prepared statements
	 * @param username
	 */
	public void setUsername(String username) {
		try{
			addUser.setString(1, username);
			selectUID.setString(1, username);
			changeUserPW.setString(2, username);
			changeUserRole.setString(2, username);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Set the role in the prepared statements
	 * @param role
	 */
	public void setRole(int role) {
		try{
			changeUserRole.setInt(1, role);
			addUser.setInt(4, role);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Set the password for the prepared statements
	 * @param pw
	 */
	public void setPassword(String pw) {
		try{
			addUser.setString(2, pw);
			changeUserPW.setString(1, pw);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Set the uid for the prepared statements
	 * @param uid
	 */
	public void setUid(int uid){
		try{
			changeUserPW.setInt(1, uid);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Set the profile image path for the prepared statements
	 * @param path
	 */
	public void setImage_path(String path) {
		try{
			addUser.setString(3, path);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public User checkForUser(int id){
		User u = null;
		ResultSet r;
		int uid = -1;
		String username = null;
		int role = 0;
		String pw = null;
		String path = null;
		try{
			getUser.setInt(1, id);
			r = getUser.executeQuery();
			while(r.next()){
				uid = r.getInt("Uid");
				username = r.getString("Username");
				role = r.getInt("Role");
				pw = r.getString("Password");
				path = r.getString("ProfilePicPath");
				u = new User(uid, username, role, pw, path);//(uid, name, role, password, path)
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return u;
	}
	
	/**
	 * get a list of all users
	 */
	public ArrayList<User> getUsers(){
		ArrayList<User> users = new ArrayList<User>();
		ResultSet r;
		int uid = 0;
		String username = null;
		String pw = null;
		String path = null;
		int role = 0;
		User u = null;
		try{
			r = getUserList.executeQuery();
			while(r.next()){
				uid = r.getInt("Uid");
				username = r.getString("Username");
				role = r.getInt("Role");
				pw = r.getString("Password");
				path = r.getString("ProfilePicPath");
				u = new User(uid, username, role, pw, path);
				users.add(u);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return users;
	}
}

