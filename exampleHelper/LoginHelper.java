package ticksys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.lang.String;


/**
 * contains functions to get login info
 * from the database and to add a worker
 */
public class LoginHelper {

protected PreparedStatement getUserPassStatement;
protected PreparedStatement addWorkerStatement;
protected PreparedStatement getWorkersStatement;
protected PreparedStatement getUserRoleStatement;
protected PreparedStatement getUserIdStatement;



/**
 * creates connection to database and loads prepared statements,
 * if there are no workers in the database, create a manager
 */
public LoginHelper() throws Exception{
	System.out.println("in loginhelper()");
	String JDBC_URL = "jdbc:mysql://localhost/ticketSysDB";
	String DB_USER = "luke";
	String DB_PASS = "ukulele5";
	
	ResultSet rs;
	
	try{
System.out.println("attempt to connect");
	    Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                 JDBC_URL, DB_USER, DB_PASS);
            
            System.out.println("connection made");

	    getUserPassStatement = conn.prepareStatement(
		"select password from Worker where username=?");
	    
	    addWorkerStatement = conn.prepareStatement(
		"insert into Worker (username, password, role) values(?, ?, ?)");
	    
	    getWorkersStatement = conn.prepareStatement(
	    	"select * from Worker where role='worker'");

	    getUserRoleStatement = conn.prepareStatement(
		"select role from Worker where username=?");

	    getUserIdStatement = conn.prepareStatement(
		"select id from Worker where username=?");
	    
	    //System.out.println("in LoginHelper(), after PS");
	    //check if there are any workers in the database
	    rs = getWorkersStatement.executeQuery();
	    if( !rs.next() ){
	    	addWorkerStatement.setString(1, "TheBossMan");
	    	addWorkerStatement.setString(2, "bosspass");
	    	addWorkerStatement.setString(3, "manager");
	    	addWorkerStatement.executeUpdate();
	    }
	}//try
	catch(SQLException sqle){
	    System.out.println("exception in LoginHelper():"+sqle.getMessage());
	}


}//consturor



/**
 * gives the username to getUserPassStatement
 * AND	
 * addWorkerStatement
 */
public void setUsername(String user) throws Exception{
	try{
	    getUserPassStatement.setString(1, user);
	    getUserRoleStatement.setString(1, user);
	    getUserIdStatement.setString(1, user);
	    addWorkerStatement.setString(1, user);
	}
	catch(SQLException sqle){
	    System.out.println("Exception in setUsername: "+sqle.getMessage());
	}

}//setUsername


/**
 *gives the password to addWorkerStatement
 */
public void setPassword(String pass) throws Exception{
	try{
	    addWorkerStatement.setString(2, pass);
	}
	catch(SQLException sqle){
	    System.out.println("Exception in setPassword: "+sqle.getMessage());
	}


}//setPassword


/**
 *gives the role to addWorkerStatement
 */
public void setRole(String role){
	try{
	    addWorkerStatement.setString(3, role);
	}
	catch(SQLException sqle){
	    System.out.println("Exception in setRole: "+sqle.getMessage());
	}


}//setRole



/**
 *
 *  gets  password from the database
 *  returns the pass
 **/
public String getPass() throws Exception{
        try{
        	System.out.println("in getPass");
	    ResultSet rs1;
	    String pass = null;
	    
	   
	    rs1 = getUserPassStatement.executeQuery();
	      if( rs1.next() ){
	      	System.out.println("in getPass, there was a next");
	      pass = rs1.getString("password");
	      System.out.println("in getPass, DBpass is "+pass);
	      }
	    return pass;
        }
        catch(SQLException sqle){
            System.out.println("Exception in getPass"+sqle.getMessage());
            String pass = "";
            return pass;
        }

}//getPass


/**
 *Get the worker id of the specified worker from the database
 */ 
/*
public int getWorkerId() throws Exception{
	try{
		ResultSet rs;
		 
	}
	catch(SQLException sqle){
	    System.out.println("Exception in getWorkerId()"+sqle.getMessage());
	}


}
*/

/**
 * gets role from the database
 */
public String getRole() throws Exception{
	try{
	ResultSet rs1;

	rs1 = getUserRoleStatement.executeQuery();
	rs1.next();
	String role = rs1.getString("role");
	return role;
	}
	catch(SQLException sqle){
	    System.out.println("Exception in getRole"+sqle.getMessage());
	    String role = "";
	    return role;
	}
	
}//getRole


/**
 * gets id of worker from the database
 */
public int getId() throws Exception{
try{
        ResultSet rs1;

        rs1 = getUserIdStatement.executeQuery();
        rs1.next();
        int id = rs1.getInt("id");
        return id;
        }
        catch(SQLException sqle){
            System.out.println("Exception in getRole"+sqle.getMessage());
            int id = -1;
            return id;
        }


}


/**
 * Inserts a worker into the database
 */
public void addWorker() throws Exception{
	try{
	    addWorkerStatement.executeUpdate();
	}
	catch(SQLException sqle){
		System.out.println("Exception in addWorker()"+sqle.getMessage());
	}

} //addWorker



public ArrayList<Worker> getWorkerList() throws Exception{
	ArrayList<Worker> result = new ArrayList<Worker>();
	ResultSet rs1;
	
	try{
		rs1 = getWorkersStatement.executeQuery();
		
		while(rs1.next()){
		//get a worker's info and put it into the result list
			int id = rs1.getInt("id");
			String user = rs1.getString("username");
			String pass = rs1.getString("password");
			String role = rs1.getString("role");
			
			Worker worker = new Worker(id, user, pass, role);
			result.add(worker);
			
		}
	}
	catch(SQLException sqle){
		System.out.println("Exception in getWorkerList:"+sqle.getMessage());
	}
	
	
	
	return result;
}





}//class




