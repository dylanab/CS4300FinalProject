package pt;


/**
 * DTO representing an Article
 */
public class Article {

private int id;
private User user;
private String title;
private String text;
private String image_path;
private String categories;
private int hits;
private int author_id;
private int response_id;

Article(int id, User user, String title, String text, String image_path, String categories, int hits, int author_id, int response_id){
	this.id = id;
	this.user = user;
	this.title = title;
	this.text = text;
	this.image_path = image_path;
	this.categories = categories;
	this.hits = hits;
	this.author_id = author_id;
	this.response_id = response_id;
} //constructor



public int getId(){ return id; }
public User getUser(){ return user; }
public String getTitle(){ return title; }
public String getText(){ return text; }
public String getImage_path(){ return image_path; }
public String getCategories(){ return categories; }
public int getHits(){ return hits; }
public int getAuthor_id(){ return author_id; }
public int getResponse_id(){ return response_id; }


} //categories