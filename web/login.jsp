<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<style>
.loginButton {
	-moz-box-shadow: inset 0px 1px 0px 0px #f5978e;
	-webkit-box-shadow: inset 0px 1px 0px 0px #f5978e;
	box-shadow: inset 0px 1px 0px 0px #f5978e;
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #d4271b
		), color-stop(1, #ab2316));
	background: -moz-linear-gradient(center top, #d4271b 5%, #ab2316 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#d4271b',
		endColorstr='#ab2316');
	background-color: #d4271b;
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
	font-size: 14px;
	font-weight: bold;
	font-style: normal;
	height: 26px;
	line-height: 26px;
	width: 150px;
	text-decoration: none;
	text-align: center;
	text-shadow: 1px 1px 0px #810e05;
}

.loginButton:hover {
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #ab2316
		), color-stop(1, #d4271b));
	background: -moz-linear-gradient(center top, #ab2316 5%, #d4271b 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ab2316',
		endColorstr='#d4271b');
	background-color: #ab2316;
}

.loginPasswordButton:active {
	position: relative;
	top: 1px;
}
/* This button was generated using CSSButtonGenerator.com */
body {
	background-color: #C20400;
	font-family: Futura, "Trebuchet MS", Arial, sans-serif;
}

#main {
	-moz-box-shadow: inset 0px 1px 0px 0px #f5978e;
	-webkit-box-shadow: inset 0px 1px 0px 0px #f5978e;
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
	background-color: #FFFFFF;
	max-width: 775px;
	min-width: 575px;
	min-height: 400px;
	margin: auto;
	padding-left: 25px;
	padding-right: 25px;
	padding-bottom: 10px;
	padding-top: 10px;
}

input {
	padding-left: 5px;
	border: 1px solid #C20400;
	color: #C20400;
}

table {
	font-size: 18px;
	margin-left: 50px;
}

td {
	padding-right: 15px;
}

label {
	color: #C20400;
	font-weight: bold;
}

h1 {
	margin-left:40px;
	color: #C20400;
}

#portal {
	position: absolute;
	font-weight: bold;
	width: 100%;
	max-width: 725px;
	bottom: 10px;
}

#innerPortal {
	position: relative;
	height: 100%;
	width: 100%;
	text-align: center;
	font-size: 20px;
}

a {
	color: #C20400;
	text-decoration: none;
}

a:hover {
	color: #C20400;
	text-decoration: underline;
}
</style>
</head>
<body>
	<div id="main">
	<h1>Login</h1>
		<table>
			<form action="login" method="post">
				<tr><td>Username:</td><td><input type="text" name="userName" maxlength="45" /></td></tr>
				<tr><td>Password:</td><td><input type="password" name="password" maxlength="45" /></td></tr>
				<tr><td></td><td><input class="loginButton" type="submit" value="Login" /></td></tr>
			</form>
			<c:if test="${not empty errMsg }">
				<p style="color: red">${errMsg}</p>
			</c:if>
		</table>
	</div>
</body>
</html>