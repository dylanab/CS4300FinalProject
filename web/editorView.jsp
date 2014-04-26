<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean class="daos.ArticleHelper" id="articleBean" />
<jsp:setProperty property="article_id" name="articleBean" value="${param.article_id}" />
<!DOCTYPE html>
<html>
<!-- Authors: Christopher Ghyzel, Dylan Bowne
	 Description: Page for editing/creating articles
	 Roles:
	  Chris Ghyzel: Layout/Styling and Page logic
	  Dylan Bowne:  Installed NicEdit
 -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Article Editor - Politalk</title>

<style>
body {
	background-color: #1240AB;
	position: relative;
}

input {
	height: 20px;
	margin-bottom: 20px;
	padding-left: 10px;
	font-weight: bold;
	font-family: Futura, "Trebuchet MS", Arial, sans-serif;
	font-size: 20px;
	color: #A60400;
}

h1 {
	color: #A60400;
	font-family: Futura, "Trebuchet MS", Arial, sans-serif;
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

a {
	color: #1240AB;
	text-decoration: none;
}

a:hover {
	color: #1240AB;
	text-decoration: underline;
}

.changeButton {
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

.changeButton:hover {
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0.05, #c62d1f
		), color-stop(1, #d63a2f));
	background: -moz-linear-gradient(center top, #c62d1f 5%, #d63a2f 100%);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#c62d1f',
		endColorstr='#d63a2f');
	background-color: #c62d1f;
}

.changeButton:active {
	position: relative;
	top: 1px;
}
/* This button was generated using CSSButtonGenerator.com */
.articleHead {
	margin-top: -15px;
	margin-left: 10px;
	font-size: 16px;
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
select {
   width: 150px;
   padding: 5px;
   font-size: 16px;
   line-height: 1;
   height: 30px;
}
.category {
	color: #A60400;
	font-size: 20px;
	font-family: Futura, "Trebuchet MS", Arial, sans-serif;
}
</style>
</head>
<body>
	<div class="mainBody">

		<script type="text/javascript"
			src="http://js.nicedit.com/nicEdit-latest.js"></script>
		<script type="text/javascript">
			//<![CDATA[
			bkLib.onDomLoaded(function() {
				nicEditors.allTextAreas()
			});
			//]]>
		</script>
		<c:if test="${empty param.article_id }">
			<h1>Create Article</h1>
			<form method='post' action='createArticle'>

				<input type="text" value="Title" name="articleTitle" />
				<textarea name="articleContent" cols="80">
    </textarea>
				 
					<br /><span class="category">Category: </span><select
					name="category">

					<option value="Fashion">Fashion</option>
					<option value="News">News</option>
					<option value="Politics">Politics</option>
					<option value="Pop_Culture">Pop Culture</option>
					<option value="Satire">Satire</option>
					<option value="Science">Science</option>
					<option value="Sports">Sports</option>
					<option value="Technology">Technology</option>
					<option value="Other" selected>Other</option>

				</select>
					<br /><br /><input class="changeButton" type='submit' value='Create'
					name='Create Article'>
			</form>
		</c:if>

		<c:if test="${not empty param.article_id }">
			<h1>Edit Article</h1>
			<form method='post' action='editArticle'>


				<input type="text" value="${articleBean.article.title }" name="articleTitle" /> 
				
				
				<input type="hidden" name="article_id" value="${param.article_id}" />
				<textarea name="articleContent" cols="80">
				${articleBean.article.text }
    			</textarea>
    			<br /><span class="category">Category: </span><select
					name="category">

					<option value="Fashion">Fashion</option>
					<option value="News">News</option>
					<option value="Politics">Politics</option>
					<option value="Pop_Culture">Pop Culture</option>
					<option value="Satire">Satire</option>
					<option value="Science">Science</option>
					<option value="Sports">Sports</option>
					<option value="Technology">Technology</option>
					<option value="Other" selected>Other</option>

				</select>
				<br /><br /><input class="changeButton" type='submit' value='Submit'
					name='Create Article'>
			</form>
		</c:if>

	</div>
</body>