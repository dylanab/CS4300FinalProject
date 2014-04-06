<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Master Ticket Page</title>
</head>
<body>
	<script>
	var app = angular.module('articleApp', []);

	app.factory('ArticleList', function() {
	    var articleList = {};
	    articleList.articles = [ 
		<c:forEach var="article" items="${articleList}" varStatus="art_status">   
		{
		    title: ${article.title},
		    author: ${article.author_id},
		    categories: [
		    	<c:if test="${empty(article.catagories)}">
		    	" "
		    	</c:if>
		    	<c:forEach var = "catagory" items="${article.catagories}" varStatus="cat_status">
		    		 ${catagory} <c:if test="${!cat_status.last}" >,  </c:if>
		    	</c:forEach>
		    ],
		    imagepath: ${article.image_path},
		    hits: ${article.hits},
		    id: ${article.id}
		} <c:if test="${!art_status.last}" >,  </c:if> 
		</c:forEach>
	    ];
	    return articleList;
	});
	function ArticleCtrl($scope, ArticleList) {
	    $scope.articleList = ArticleList;
	};
	</script>
</body>
