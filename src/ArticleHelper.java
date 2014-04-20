package pt;

import java.sql.*;
import java.util.ArrayList;



public class ArticleHelper {
	protected Connection conn = null;
	//protected Statement statement = null;
	protected ArrayList<Article> allArticles;
	protected PreparedStatement getAllArticles;
	protected PreparedStatement getArticle;
	protected PreparedStatement addArticle;
	protected PreparedStatement editArticle;
	protected PreparedStatement searchArticles;
	//protected PreparedStatement getLastID;

	/**
	 * Create the driver, connection, and prepared statement for the database calls.
	 * @param url
	 * @param user
	 * @param pass
	 */
	
	public ArticleHelper(String url, String user, String pass) {
		allArticles = new ArrayList<Article>();
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance(); //create driver
			conn = DriverManager.getConnection(url, user, pass); //connect to db passed to constructor
			if(conn == null){
				throw new Exception("Unable to connect to database");
			}
			
			//statement = conn.createStatement();
			//if(statement == null){
				//throw new Exception("Unable to create a valid statement for the database");
			//}
			//initialize PreparedStatements
			getAllArticles = conn.prepareStatement("Select a.Id, a.Catagories, u.Uid, a.Hits, a.ImageFilePath, a.ArticleText, a.Response, u.Username, u.Password, u.ProfilePicPath, u.Role, a.Title from Articles a, Users u");
			getArticle = conn.prepareStatement("Select a.Catagories, u.Uid, a.Hits, a.ImageFilePath, a.ArticleText, a.Response, u.Username, u.Password, u.ProfilePicPath, u.Role, a.Title from Articles a, Users u Where a.id=?");
			addArticle = conn.prepareStatement("Insert into Articles ( Title, Catagories, AuthorID, Hits, ImageFilePath, ArticleText, Response ) values (?, ?, ?, 0, ?, ?, null");
			editArticle = conn.prepareStatement("UPDATE Articles SET Title=?, Catagories=?, ImageFilePath=?, ArticleText=? WHERE Id=?");
			//getLastID = conn.prepareStatement("Select ");
			searchArticles = conn.prepareStatement("Select a.Id, a.Catagories, u.Uid, a.Hits, a.ImageFilePath, a.ArticleText, a.Response, u.Username, u.Password, u.ProfilePicPath, u.Role, a.Title from Articles a, Users u Where a.Title Like ?");
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
		//String query = "Select a.Id, a.Catagories, u.Uid, a.Hits, a.ImageFilePath, a.ArticleText, a.Response, u.Username, u.Password, u.ProfilePicPath, u.Role, a.Title from Articles a, Users u";
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
		String u_imagePath = null;
		ResultSet rs;
		
		try{
			//if(statement.execute(query)){
				//ResultSet r = statement.getResultSet();
				rs = getAllArticles.executeQuery();
				while(rs.next()) {
					a_id = rs.getInt(1);
					a_catagories = rs.getString(2);
					u_id = rs.getInt(3);
					a_hits = rs.getInt(4);
					a_imageFilePath = rs.getString(5);
					a_articleText = rs.getString(6);
					a_response = rs.getInt(7);
					u_username = rs.getString(8);
					u_password = rs.getString(9);
					u_imagePath = rs.getString(10);
					u_role = rs.getInt(11);
					a_title = rs.getString(12);
					//List<String> catagories_list = Arrays.asList(a_catagories.split(", "));
					Article articleFromDB = new Article(a_id, new User(u_id, u_username, u_role, u_password, u_imagePath),a_title, a_articleText, a_imageFilePath, a_catagories, a_hits, u_id, a_response);
					//Article articleFromDB = new Article(a_id, new User(u_id, u_username, u_password, u_role), a_title, a_articleText, a_imageFilePath, catagories_list);
					//commented out articleFromDB is for a constructor that takes a list of catagories as opposed to a string
					//maybe create user depending on how parameters for article are defined
					//create article
					allArticles.add(articleFromDB);
				}//while
				//may use jOOQ to convert the resultset into a json object
				//jsonArticles = DSL.using(conn).fetch(r).formatJSON();

		} catch (Exception e){
			e.printStackTrace();
		}
		return allArticles;
	}
	
	/**
	 * Get a single article based on the id of the article
	 * @param id
	 * @return
	 */
	public Article getArticle(int id){
		try{
			getArticle.setInt(1, id);
		}catch (Exception e){
			e.printStackTrace();
		}
		Article a = null;
		//String query = "Select a.Catagories, u.Uid, a.Hits, a.ImageFilePath, a.ArticleText, a.Response, u.Username, u.Password, u.ProfilePicPath, u.Role, a.Title from Articles a, Users u Where a.id='"+id+"'";
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
		String u_imagePath = null;
		ResultSet r;
		try{
			//if(statement.execute(query)){
				//ResultSet r = statement.getResultSet();
			r = getArticle.executeQuery();	
			while(r.next()){
					a_catagories = r.getString(1);
					u_id = r.getInt(2);
					a_hits = r.getInt(3);
					a_imageFilePath = r.getString(4);
					a_articleText = r.getString(5);
					a_response = r.getInt(6);
					u_username = r.getString(7);
					u_password = r.getString(8);
					u_imagePath = r.getString(9);
					u_role = r.getInt(10);
					a_title = r.getString(11);
					a = new Article(id, new User(u_id, u_username, u_role, u_password, u_imagePath),a_title, a_articleText, a_imageFilePath, a_catagories, a_hits, u_id, a_response);
				}
		}catch(Exception e){
			e.printStackTrace();
		}
		return a;
	}
	
	/**
	 * A method that adds a new article to the database and returns the article id or -1 if the article is not
	 * added to the database
	 * @param nArticle
	 * @return
	 */
	public int addArticleToDB(Article nArticle) {
		//String query = "Insert into Articles ( Title, Catagories, AuthorID, Hits, ImageFilePath, ArticleText, Response ) values (" +
			//	"'"+nArticle.getTitle() +"', "
				//+ "'"+nArticle.getCategories()+"', "
				//+"'"+nArticle.getAuthor_id()+"', "
				//+"'0', "
				//+"'"+nArticle.getImage_path()+"', "
				//+"'"+nArticle.getText()+"', "
				//+"'null' )";
		//"Insert into Articles ( Title, Catagories, AuthorID, Hits, ImageFilePath, ArticleText, Response ) values (?, ?, ?, 0, ?, ?, null")
		try{
			addArticle.setString(1,nArticle.getTitle());
		addArticle.setString(2, nArticle.getCategories());
		addArticle.setString(4, nArticle.getImage_path());
		addArticle.setString(5, nArticle.getText());
		addArticle.setInt(3, nArticle.getAuthor_id());
		} catch (Exception e){
			e.printStackTrace();
		}
		int aId=0;
		int q=0;
		ResultSet r;
		try {
			//q=statement.executeUpdate(query);
			q=addArticle.executeUpdate();
			if(q==1){
				//String sql = "select last_insert_id()";
				//if(statement.execute(sql)){
					r = addArticle.getGeneratedKeys();
					while(r.next()){
						aId = r.getInt(1);
						if(aId<=0){
							aId=-1;
						}
					}
				}
		}catch(Exception e){
			e.printStackTrace();
		}
		return aId;
	}
	
	/**
	 * Makes changes to an article in a database and return the database 
	 * id for the article or -1 if the article does not update
	 * @param eArticle
	 * @return
	 */
	public int editArticle(Article eArticle){
		int aId = -1;
		int q=0;
		//String query = "UPDATE Articles SET Title='"+eArticle.getTitle()+"', Catagories='"
			//	+eArticle.getCategories() +"', ImageFilePath='"+eArticle.getImage_path()
				//+"', ArticleText='"+eArticle.getText()+"'WHERE Id='"+eArticle.getId()+"'";
		try{
			//q=statement.executeUpdate(query);
			editArticle.setString(1, eArticle.getTitle());
			editArticle.setString(2, eArticle.getCategories());
			editArticle.setString(3, eArticle.getImage_path());
			editArticle.setString(4, eArticle.getText());
			editArticle.setInt(5, eArticle.getId());
			q = editArticle.executeUpdate();
			if(q>0){
				aId=eArticle.getId();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return aId;
	}
	
	public ArrayList<Article> articleSearchOfUser(){
		ArrayList<Article> articlesWithTitle = new ArrayList<Article>();
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
		String u_imagePath = null;
		ResultSet r;
		try{
			r = searchArticles.executeQuery();
			while(r.next()){
				a_id = r.getInt(1);
				a_catagories = r.getString(2);
				u_id = r.getInt(3);
				a_hits = r.getInt(4);
				a_imageFilePath = r.getString(5);
				a_articleText = r.getString(6);
				a_response = r.getInt(7);
				u_username = r.getString(8);
				u_password = r.getString(9);
				u_imagePath = r.getString(10);
				u_role = r.getInt(11);
				a_title = r.getString(12);
				Article articleFromDB = new Article(a_id, new User(u_id, u_username, u_role, u_password, u_imagePath),a_title, a_articleText, a_imageFilePath, a_catagories, a_hits, u_id, a_response);
				articlesWithTitle.add(articleFromDB);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return articlesWithTitle;
	}
	
	/**
	 * Set the searchstring in the PreparedStatements
	 * @param searchstring
	 */
	public void setSearchString(String searchstring){
		try{
			searchArticles.setString(1, searchstring.trim());
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
