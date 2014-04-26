use politalk;
source db/schema_for_project.sql

insert into Users (Uid, Username, Password, ProfilePicPath, Role) values (1, 'Luke', 'lukePW', 'default.jpg', 4);
insert into Users (Uid, Username, Password, ProfilePicPath, Role) values (2, 'Dylan', 'dylanPW', 'default.jpg', 4);
insert into Users (Uid, Username, Password, ProfilePicPath, Role) values (3, 'Michael', 'michaelPW', 'default.jpg', 4);
insert into Users (Uid, Username, Password, ProfilePicPath, Role) values (4, 'Chris', 'chrisPW', 'default.jpg', 4);

insert into Users (Uid, Username, Password, ProfilePicPath, Role) values (5, 'Joe', 'joePW', 'default.jpg', 3);

insert into Users (Uid, Username, Password, ProfilePicPath, Role) values (6, 'Bob', 'bobPW', 'default.jpg', 2);
insert into Users (Uid, Username, Password, ProfilePicPath, Role) values (7, 'George', 'georgePW', 'default.jpg', 2);
insert into Users (Uid, Username, Password, ProfilePicPath, Role) values (8, 'Dan', 'danPW', 'default.jpg', 2);
insert into Users (Uid, Username, Password, ProfilePicPath, Role) values (9, 'Lucy', 'lucyPW', 'default.jpg', 2);