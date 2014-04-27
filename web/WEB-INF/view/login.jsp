<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<!--  
  Authors: Dylan Bowne, Christopher Ghyzel
  Description: JSP page for user logins
  Dylan: Page logic & initial html
  Chris: CSS and HTML layout
-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login - PoliTalk</title>
<style>
/* CSS by Christopher Ghyzel */
body {
	background-color: #1240AB;
	color: #A60400;
	position: relative;
	font-family: Futura, "Trebuchet MS", Arial, sans-serif;
	font-size: 20px;
	color: #A60400;
}

input {
	height: 20px;
}

#error {
	color: red;
	width: 100%;
	max-width: 600px;
	position: absolute;
	bottom: 80px;
	font-weight: bold;
}

#error div {
	position: relative;
	text-align: center;
	width: 100%;
	height: 100%;
}

.mainBody {
	-moz-box-shadow: inset 0px 1px 0px 0px #1240AB;
	-webkit-box-shadow: inset 0px 1px 0px 0px #1240AB;
	-webkit-border-top-left-radius: 20px;
	-moz-border-radius-topleft: 20px;
	border-top-left-radius: 20px;
	-webkit-border-top-right-radius: 20px;
	-moz-border-radius-topright: 20px;
	border-top-right-radius: 20px;
	-webkit-border-bottom-right-radius: 20px;
	-moz-border-radius-bottomright: 20px;
	border-bottom-right-radius: 20px;
	-webkit-border-bottom-left-radius: 20px;
	-moz-border-radius-bottomleft: 20px;
	position: relative;
	margin: auto;
	min-width: 400px;
	max-width: 600px;
	min-height: 400px;
	max-height:600px;
	background-color: white;
}

#loginForm {
	position: absolute;
	text-align: center;
	top: 50px;
	width: 100%;
	top: 50px;
}

#loginForm table {
	margin: auto;
}

#loginForm table tr td {
	min-width: 120px;
}

#portal {
	position: absolute;
	text-align: center;
	bottom: 10px;
	width: 100%;
}

a {
	color: #1240AB;
	text-decoration: none;
}

a:hover {
	color: #1240AB;
	text-decoration: underline;
}

.loginButton {
	-moz-box-shadow: inset 0px 1px 0px 0px #f5978e;
	-webkit-box-shadow: inset 0px 1px 0px 0px #f5978e;
	box-shadow: inset 0px 1px 0px 0px #f5978e;
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #d63a2f
		), color-stop(1, #c62d1f));
	background: -moz-linear-gradient(center top, #d63a2f 5%, #c62d1f 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#d63a2f',
		endColorstr='#c62d1f');
	background-color: #d63a2f;
	-webkit-border-top-left-radius: 20px;
	-moz-border-radius-topleft: 20px;
	border-top-left-radius: 20px;
	-webkit-border-top-right-radius: 20px;
	-moz-border-radius-topright: 20px;
	border-top-right-radius: 20px;
	-webkit-border-bottom-right-radius: 20px;
	-moz-border-radius-bottomright: 20px;
	border-bottom-right-radius: 20px;
	-webkit-border-bottom-left-radius: 20px;
	-moz-border-radius-bottomleft: 20px;
	border-bottom-left-radius: 20px;
	text-indent: 0;
	border: 1px solid #d02718;
	display: inline-block;
	color: #ffffff;
	font-family: Arial;
	font-size: 15px;
	font-weight: bold;
	font-style: normal;
	height: 28px;
	line-height: 26px;
	width: 70px;
	text-decoration: none;
	text-align: center;
	text-shadow: 1px 1px 0px #810e05;
}

.loginButton:hover {
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #c62d1f
		), color-stop(1, #d63a2f));
	background: -moz-linear-gradient(center top, #c62d1f 5%, #d63a2f 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#c62d1f',
		endColorstr='#d63a2f');
	background-color: #c62d1f;
}

.loginButton:active {
	position: relative;
	top: 1px;
}
/* This button was generated using CSSButtonGenerator.com */
</style>
</head>
<body>
	<div class="mainBody">
		<div id="loginForm">
			<h2>Welcome to PoliTalk!</h2>
			<form action="login" method="post">
				<table>
					<tr>
						<td><label>Username:</label></td>
						<td><input type="text" name="user" required /></td>
					</tr>
					<tr>
						<td><label>Password:</label></td>
						<td><input type="password" name="pass" required /></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
					</tr>
				</table>
				<input class="loginButton" type="submit" value="Login" />
			</form>
		</div>
		<c:if test="${not empty param.errMsg}">
			<div id="error">
				<div>${param.errMsg }</div>
			</div>
		</c:if>
		<div id="portal">
			<div>
				<a href="index">Back to Front Page</a>
			</div>
		</div>
	</div>
</body>
</html>
