<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<!-- Authors: Christopher Ghyzel, Luke Gratham
	 Description: Moderator control panel: enables mods to delete articles
	 Roles:
	  Chris Ghyzel: Layout/Styling
	  Luke Gratham: Page logic
 -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Moderator Controls - PoliTalk</title>
</head>
<style>body {
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
	min-width: 500px;
	max-width: 700px;
	min-height: 500px;
	padding-left:50px;
	padding-bottom:15px;
	padding-top:5px;
	background-color: white;
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

.submitButton {
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
	width: 105px;
	text-decoration: none;
	text-align: center;
	text-shadow: 1px 1px 0px #810e05;
}

.submitButton:hover {
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #c62d1f
		), color-stop(1, #d63a2f));
	background: -moz-linear-gradient(center top, #c62d1f 5%, #d63a2f 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#c62d1f',
		endColorstr='#d63a2f');
	background-color: #c62d1f;
}

.submitButton:active {
	position: relative;
	top: 1px;
}
/* This button was generated using CSSButtonGenerator.com */</style>
<body>
<div class="mainBody">
<h4>Remove an Article</h4>
<form action="modControls" method="post">
Article ID: <input type="text" name="article_id" required />
<input class ="submitButton" type="submit" value="submit"/>
</form>
</div>
</body>
</html>

