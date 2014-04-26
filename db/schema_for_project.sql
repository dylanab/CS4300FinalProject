#
#	this SQL file creates the schema for the Politalk database (Michael Tankersley)
#

use politalk;

DROP TABLE IF EXISTS Users;

CREATE TABLE Users (
	Uid INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	Username VARCHAR(50),
	Password VARCHAR(64),
	ProfilePicPath VARCHAR(200),
	Role INT
);

DROP TABLE IF EXISTS Articles;

CREATE TABLE Articles (
	Id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	Title VARCHAR(50),
	Catagories VARCHAR(200),
	AuthorID INT,
	FOREIGN KEY (AuthorID) REFERENCES Users(Uid),
	Hits INT,
	ImageFilePath VARCHAR(200),
	ArticleText MEDIUMTEXT,
	Response INT UNSIGNED,
	FOREIGN KEY (Response) REFERENCES Articles(Id)
);