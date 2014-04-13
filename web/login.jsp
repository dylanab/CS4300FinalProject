<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login - PoliTalk</title>
</head>

<c:if test="${error == 'incorrect name/pass'}">
      <b>Incorrect username or password.</b>
</c:if>


<form action="/Login" method="post">
username: <input type="text" name="user" required />
password: <input type="password" name="pass" required />
<input type="submit" value="submit"/>
</form>


</html>
