<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Control - PoliTalk</title>
</head>


<h4>Add new user</h4>
<form action="User" method="post">
   User:<input type="text" name="user" required /><br>
   Pass:<input type="password" name="pass" required />
   Confirm pass:<input type="password" name="confirmpass" required /><br>
   <input type="radio" name="role" value="user" />User<br>
   <input type="radio" name="role" value="admin" />Admin<br>
   <input type="radio" name="role" value="mod" />Moderator
<input type="submit" value="submit" />
</form>

<h4>Change role of user<h4>
<form action="User" method="get">
   <input type="text" name="user" required />
   <input type="radio" name="role" value="user" />User<br>
   <input type="radio" name="role" value="admin" />Admin<br>
   <input type="radio" name="role" value="mod" />Moderator
</form>


<h4>Change user password</h4>
<form action="User" method="get">
   User:<input type="text" name="user" required /><br>
   Pass:<input type="password" name="pass" required />
   Confirm pass:<input type="password" name="confirmpass" required /><br>
<input type="submit" value="submit" />
</form>

   




</html>

