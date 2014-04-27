package dtos;


/**
 * DTO representing an Article
 */
public class Article {

private int id;
private String title;
private String text;
private String image_path;
private String categories;
private int hits;
private int author_id;
private int response_id;


public Article(int id, String title, String text, String image_path, String categories, int hits, int author_id, int response_id){
	this.id = id;
	this.title = title;
	this.text = text;
	this.image_path = image_path;
	this.categories = categories;
	this.hits = hits;
	this.author_id = author_id;
	this.response_id = response_id;
} //normal constructor  


public Article(String title, String text, String image_path, String categories, int hits, int author_id){
        this.title = title;
        this.text = text;
        this.image_path = image_path;
        this.categories = categories;
        this.hits = hits;
        this.author_id = author_id;
} //no article id or response id


/**
 * Does not pass initialize text field
 */
public Article(int id, String title, String image_path, String categories, int hits, int author_id, int response_id){
        this.id = id;
   
        this.title = title;
        
        this.image_path = image_path;
        this.categories = categories;
        this.hits = hits;
        this.author_id = author_id;
        this.response_id = response_id;
} //constructor without text

public Article(){
   this.id = -1;
   this.title = null;
   this.text = null;
   this.image_path = null;
   this.categories = null;
   this.hits = 0;
   this.author_id = -1;
   this.response_id = -1;
} //no arg constructor




public int getId(){ return id; }

public String getTitle(){ return title; }
public String getText(){ return text; }
public String getImage_path(){ return image_path; }
public String getCategories(){ return categories; }
public int getHits(){ return hits; }
public int getAuthor_id(){ return author_id; }
public int getResponse_id(){ return response_id; }


} //class
