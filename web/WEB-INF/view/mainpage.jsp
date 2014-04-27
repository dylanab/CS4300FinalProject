<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!-- 
     Authors: Christopher Ghyzel, Dylan Bowne
     Description: The FrontPage, the meat of the app and the main interface. Using Angular we were able to add interactivity especially.
     Roles: 
     	    Chris: HTML, CSS, and most JavaScript
	    Dylan: Building JSON with JSTL
     
-->
<html ng-app="frontPage">
  <head>
    <meta charset-"utf8">
    <title>Politalk</title>    
  </head>
  <body>
    
    <ng-view></ng-view>
    
    <script type="text/javascript" src="js/lib/angular/angular.min.js"></script>
    <script type="text/javascript" src="js/lib/angular/angular-route.min.js"></script>

    <script type="text/javascript" src="js/app.js"></script>
    <script type="text/javascript" src="js/controllers.js"></script>
    <script type="text/javascript">
      app.factory('ArticleList', function() {
          var articleList = {};
          articleList.articles = [ 
      <c:forEach var="article" items="${articleList}" varStatus="art_status">
 	{
	    title: ${article.title},
	    author: ${article.author_id},
	    categories: [
	<c:if test="${empty(article.categories)}">
	  " "
	  </c:if>
	<c:forEach var = "catagory" items="${article.categories}" varStatus="cat_status">
	   ${category} <c:if test="${!cat_status.last}" >,  </c:if>
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
    </script>
  </body>
</html>
