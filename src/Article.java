package pt;

import java.util.ArrayList;



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

Article(int id, User user, String title, String text, String image_path, String categories){
	this id = id;
	this.user = user;
	this.title = title;
	this.text = text;
	this image_path = image_path;
	this.categories = categories
} //constructor



public int getId(){ return id; }
public User getUser(){ return user; }
public String getTitle(){ return title; }
public String getText(){ return text; }
public String getImage_path(){ return image_path; }
public String getCategories(){ return categories }



} //categories
