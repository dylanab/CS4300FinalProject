#
#	this SQL file creates the schema for the Politalk database
#

use politalk

DROP TABLE IF EXISTS Users;

CREATE TABLE Users (
	Uid INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	Username VARCHAR(50),
	Password VARCHAR(64),
	ProfilePicPath VARCHAR(200),
	Role INT,
);

DROP TABLE IF EXISTS Articles;

CREATE TABLE Articles (
	Id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	Title VARCHAR(50),
	Catagories VARCHAR(200),
	AuthorID INT,
	Hits INT,
	ImageFilePath VARCHAR(200),
	ArticleText MEDIUMTEXT,
	Response INT,
	FOREIGN KEY(AuthorID) references Users(Uid),
	FOREIGN KEY(Response) references Articles(Id)
);