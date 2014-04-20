package pt;

import java.sql.*;

public class UserHelper {

	protected Connection conn = null;
	//protected Statement statement = null;
	protected PreparedStatement validateUser;
	protected PreparedStatement addUser;
	protected PreparedStatement selectUID;
	protected PreparedStatement changeUserPW;
	
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
	public User addNewUser(User newUser) {
		int updateReturn = 0;
		User toReturn = newUser;
		ResultSet r;
		if(newUser != null) { //make sure parameter is not null
			//String query = "Insert into Users (Username, Password, ProfilePicPath, Role ) vaules ( " +
				//	"'" + newUser.getName() + "', " +
					//"'" + newUser.getPassword() + "', " +
					//"'" + newUser.getImage_path() + "', " +
					//"'" + newUser.getRole() + "' )";
			try {
				addUser.setString(1, newUser.getName());
				addUser.setString(2, newUser.getPassword());
				addUser.setString(3, newUser.getImage_path());
				addUser.setInt(4, newUser.getRole());
				updateReturn = addUser.executeUpdate();
				//updateReturn = statement.executeUpdate(query);
				if(updateReturn > 0){
					//query = "Select Uid from Users where Username = " + newUser.getName();
					int uId = 0;
					selectUID.setString(1, newUser.getName());
					r = selectUID.executeQuery();
					while(r.next()) {
						//ResultSet r = statement.getResultSet();
						uId = r.getInt(1);
						toReturn.setId(uId);
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
		//String query = "UPDATE Users SET Password='"+nPassword+"' WHERE Uid='"+user.getId()+"'";
		try{
			changeUserPW.setString(1, nPassword);
			changeUserPW.setInt(2, user.getId());
			updateReturn = changeUserPW.executeUpdate();
			//updateReturn = statement.executeUpdate(query);
			if(updateReturn>0){
				u = new User(user.getId(), user.getName(), user.getRole(), nPassword, user.getImage_path());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return u;
	}
}
