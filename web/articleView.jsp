 <%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ArticleView - PoliTalk</title>
</head>

${article.title}
${article.text}
${article.hits}
${article.categories}
${article.author_id}
${article.response_id} <%-- might need this? id of the article this is a response to --%>


</html>

