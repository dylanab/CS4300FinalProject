 <%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ArticleView - PoliTalk</title>
</head>

${article_object.title}
${article_object.text}
${article_object.hits}
${article_object.categories}
${article_object.author_id}
${article_object.response_id} <%-- might need this? id of the article this is a response to --%>

<c:if test="${article_object.author_id eq sessionScope.username}" >
<form action="/editingView" method="get">
<input type="hidden" name="article_id" value="${article.id}" />
<input type="submit" value="Edit" />
</form>

</c:if>

</html>

