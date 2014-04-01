#
#	this SQL file creates the schema for the system database
#	when run, it will clear the existing database and replace it with empty tables
#

use politalk

DROP TABLE IF EXISTS Users;

CREATE TABLE Users (
	Uid INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	Username VARCHAR(50),
	Password VARCHAR(50),
	ProfilePicPath VARCHAR(200),
	Role INT,
);

DROP TABLE IF EXISTS Articles

CREATE TABLE Articles (
	Id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	Catagories VARCHAR(200),
	AuthorID INT,
	ImageFilePath VARCHAR(200),
	ArticleText MEDIUMTEXT,
	Response INT,
	FOREIGN KEY(AuthorID) references Users(Uid),
	FOREIGN KEY(Response) references Articles(Id)
);