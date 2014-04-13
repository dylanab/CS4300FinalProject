package pt;

import java.sql.*;

public class UserHelper {

	protected Connection conn = null;
	protected Statement statement = null;
	
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
				//unable to connect to db
			}
			statement = conn.createStatement();
			if(statement == null){
				//unable to create statement
			}
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
		String query = "Select Uid, ProfilePicPath, Role from Users where Username = " + name + ", Password = " + password;
		int uid = 0;
		String path = null;
		int role = 0;
		User u = null;
		try {
			if(statement.execute(query)) {
				ResultSet r = statement.getResultSet();
				
				while(r.next()) { //this resultSet should only have one result so the loop should only go once
					uid = r.getInt(1);
					path = r.getString(2);
					role = r.getInt(3);
				}
				u = new User(uid, name, role, password, path); //add parameters
			}
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
	public User addNewUser(User newUser, String npassword) {
		int updateReturn = 0;
		User toReturn = null;
		if(newUser != null) { //make sure parameter is not null
			String query = "Insert into Users (Username, Password, ProfilePicPath, Role ) vaules ( " +
					"'" + newUser.getName() + "', " +
					"'" + npassword + "', " +
					"'" + newUser.getImage_path() + "', " +
					"'" + newUser.getRole() + "' )";
			try {
				updateReturn = statement.executeUpdate(query);
				if(updateReturn > 0){
					query = "Select Uid from Users where Username = " + newUser.getName();
					int uId = 0;
					if(statement.execute(query)) {
						ResultSet r = statement.getResultSet();
						uId = r.getInt(1);
						toReturn = new User(uId, newUser.getName(), newUser.getRole(), npassword, newUser.getImage_path());
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return toReturn;
	}
	
	/**
	 * Changes a users password and returns either the user if the change is successful
	 * or null if it is unsuccessful
	 * @param user
	 * @param nPassword
	 * @return
	 */
	public User changePW(User user, String nPassword){
		User u = null;
		int updateReturn =0;
		String query = "UPDATE Users SET Password='"+nPassword+"' WHERE Uid='"+user.getId()+"'";
		try{
			updateReturn = statement.executeUpdate(query);
			if(updateReturn>0){
				u = new User(user.getId(), user.getName(), user.getRole(), nPassword, user.getImage_path());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return u;
	}
}
