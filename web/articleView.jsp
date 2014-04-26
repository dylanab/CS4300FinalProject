 <%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%> 
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<!-- Authors: Christopher Ghyzel, Luke Gratham
	 Description: Page viewing a single article
	 Roles:
	  Chris Ghyzel: Layout/Styling and page logic
	  Luke Gratham: Page logic
 -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ArticleView - PoliTalk</title>
<style>
body {
	background-color: #1240AB;
	color: #A60400;
	position: relative;
	font-family: Futura, "Trebuchet MS", Arial, sans-serif;
	font-size: 20px;
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
	z-index: 11;
	margin: auto;
	min-width: 700px;
	max-width: 800px;
	min-height: 500px;
	padding-left: 50px;
	padding-bottom: 15px;
	padding-top: 10px;
	background-color: white;
	margin: auto
}

#portal {
	position: absolute;
	text-align: center;
	bottom: 10px;
	width: 100%;
}

.controlPanel {
	position: absolute;
	z-index: 12;
	margin-top: 50px;
}

.controlPanel table {
	float: left;
	background-color: white;
	text-align: left;
}

.controlPanel table tr th {
	border: 1px solid #A60400;
	background-color: #A60400;
}

.controlPanel table tr th:hover {
	color: #A60400;
	background-color: white;
}

.controlPanel table tr th a {
	display: block;
	color: white;
}

.controlPanel table tr th a:hover {
	color: #A60400;
	/*color: #1240AB;*/
	text-decoration: none;
}

table tr th {
	border-left: 15px solid white;
}

table tr td {
	border-left: 15px solid white;
}

a {
	color: #1240AB;
	text-decoration: none;
}

a:hover {
	color: #1240AB;
	text-decoration: underline;
}

.editButton {
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
	width: 60px;
	text-decoration: none;
	text-align: center;
	text-shadow: 1px 1px 0px #810e05;
}

.editButton:hover {
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #c62d1f
		), color-stop(1, #d63a2f));
	background: -moz-linear-gradient(center top, #c62d1f 5%, #d63a2f 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#c62d1f',
		endColorstr='#d63a2f');
	background-color: #c62d1f;
}

.editButton:active {
	position: relative;
	top: 1px;
}
.articleHead {
	margin-top: -15px;
	margin-left:10px;
	font-size:16px;
}
.articleContent {
	position: relative;
	margin-left: 10px;
	margin-right: 50px;
}

img {
	float: left;
	margin-right: 10px;
	width: 100px;
	height: 100px;
}
/* This button was generated using CSSButtonGenerator.com */
</style>
</head>
<body>
<div class="mainBody">
<h2>${article_object.title}</h2>
	<div class="articleHead">By <a href="profile?user_id=${article_object.author_id}">GetUserFiller</a> 
		<c:if test="${not empty article_object.response_id}">
				<br> A response to <a href="article?article_id=${article_object.response_id}">GetArticlebyId</a>
		</c:if>
  	</div>
	<div class="articleContent">
			<img src="${article_object.image_path }" />
			${article_object.text}
	</div>
<c:if test="${article_object.author_id eq sessionScope.username}" >
<form action="/editingView" method="get">
<input type="hidden" name="article_id" value="${article.id}" />
<input class="editButton" type="submit" value="Edit" />
</form>
</c:if>
</div>
</body>
</html>

