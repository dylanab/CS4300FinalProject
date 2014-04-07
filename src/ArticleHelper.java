package pt;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jooq.*;

public class ArticleHelper {
	protected Connection conn = null;
	protected Statement statement = null;
	protected ArrayList<Article> allArticles;
	protected String jsonAricles = null;
	
	public ArticleHelper(String url, String user, String pass) {
		allArticles = new ArrayList<Article>();
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance(); //create driver
			conn = DriverManager.getConnection(url, user, pass); //connect to db passed to constructor
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
	 * Retrieves all of the articles from the database
	 * and returns them in an array list for the java classes
	 * as well as forms a json object for the angular
	 */
	public ArrayList<Article> getArticles() {
		String query = "Select a.Id, a.Catagories, u.Uid, a.Hits, a.ImageFilePath, a.ArticleText, a.Response, u.Username, u.Password, u.ProfilePicPath, u.Role, a.Title from Articles a, Users u";
		int a_id = 0;
		String a_catagories = null;
		int u_id = 0;
		int a_hits = 0;
		String a_imageFilePath = null;
		String a_articleText = null;
		int a_response = 0;
		String u_username = null;
		String u_password = null;
		int u_role = 0;
		String a_title = null;
		
		try{
			if(statement.execute(query)){
				ResultSet r = statement.getResultSet();
				
				while(r.next()) {
					a_id = r.getInt(1);
					a_catagories = r.getString(2);
					u_id = r.getInt(3);
					a_hits = r.getInt(4);
					a_imageFilePath = r.getString(5);
					a_articleText = r.getString(6);
					a_response = r.getInt(7);
					u_username = r.getString(8);
					u_password = r.getString(9);
					u_role = r.getInt(10);
					a_title = r.getString(11);
					List<String> catagories_list = Arrays.asList(a_catagories.split(", "));
					Article articleFromDB = new Article(a_id, new User(u_id, u_username, u_password, u_role), a_title, a_articleText, a_imageFilePath, a_catagories);
					//Article articleFromDB = new Article(a_id, new User(u_id, u_username, u_password, u_role), a_title, a_articleText, a_imageFilePath, catagories_list);
					//commented out articleFromDB is for a constructor that takes a list of catagories as opposed to a string
					//maybe create user depending on how parameters for article are defined
					//create article
					allArticles.add(articleFromDB);
				}//while
				//may use jOOQ to convert the resultset into a json object
				jsonAricles = DSL.using(conn).fetch(r).formatJSON();
			}
			return allArticles;
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	/**
	 * Get the jsonArticles string created in the getArticles method
	 */
	public String getJSON() {
		if(jsonArticles == null)
			this.getArticles();
		return jsonArticles;
	}

}
