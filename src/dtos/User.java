package dtos;

public class User {

private int id;
private String name;
private int role;
private String password;
private String image_path;



public User(int id, String name, int role, String password, String image_path){
	this.id = id;
	this.name = name;
	this.role = role;
	this.password = password;
	this.image_path = image_path;
}//constructor


public User(String name, int role, String password, String image_path){
        this.name = name;
        this.role = role;
        this.password = password;
        this.image_path = image_path;
}//constructor

public User() {
    this.name = null;
    this.role = 0;
    this.password = null;
    this.image_path = null;

}//no arg constructor

public int getId(){ return id; }
public String getName(){ return name; }
public int getRole(){ return role; }
public String getPassword(){ return password; }
public String getImage_path(){ return image_path; }


}//class
