<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean class="daos.ArticleHelper" id="articleBean" />
<jsp:setProperty property="searchString" name="articleBean"
	value="${searchString}" />
<jsp:setProperty property="author_id" name="articleBean"
	value="${author_id}" />

<html>


<!-- Authors: Christopher Ghyzel, Luke Gratham
	 Description: Personal profile page
	 Roles:
	  Chris Ghyzel: Layout/Styling and page logic
	  Luke Gratham: Page logic
 -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile - PoliTalk</title>
<style>
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

.searchButton {
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

.submitButton:hover {
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #c62d1f
		), color-stop(1, #d63a2f));
	background: -moz-linear-gradient(center top, #c62d1f 5%, #d63a2f 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#c62d1f',
		endColorstr='#d63a2f');
	background-color: #c62d1f;
}

.searchButton:active {
	position: relative;
	top: 1px;
}

.userPic {
	width: 100px;
	height: 150px;
	margin-right: 50px;
	float: left;
}

.articlePic {
	width: 50px;
	height: 50px;
}
/* This button was generated using CSSButtonGenerator.com */
</style>
</head>
<body>
<%-- role is the session attribute. THIS MAY NEED TO MOVE TO A DIFFERENT PAGE	 --%>
<c:if test="${sessionScope.role eq 4}">
	<div class="controlPanel">
		<table>
			<tr>
				<td><a href="/adminControls" />Admin Controls </a></td>
			</tr>
			<tr>
				<th><a href="/modControls" />Moderator Controls</th>
			</tr>
		</table>
	</div>
</c:if>

<c:if test="${sessionScope.role eq 4}">
	<div class="controlPanel">
		<table>
			<tr>
				<th><a href="/modControls" />Moderator Controls</a></th>
			</tr>
		</table>
	</div>
</c:if>

<img class="userPic" src="${user_object.image_path}" />
<h3>${user_object.name}</h3>


<form action="/profile" method="get">
	<input type="text" name="searchString" required /> <input
		type="hidden" name="user_id" value="${user_id}"> <input class="searchButton"
		type="submit" value="Search" />
</form>

<table cellpadding="10">
	<tr>
		<th></th>
		<th>Article Title</th>
		<th>Hits</th>
	</tr>
	<c:forEach items="${articleBean.articleSearchOfUser}" var="article">
		<%-- list all of this user's articles --%>
		<tr>
			<td><a href="article?id=${article.id}"><img class="articlePic" src="${article.image_path}" /></a></td>
			<td>${article.title}</td>
			<td>${article.hits}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>
