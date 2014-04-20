<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
<!DOCTYPE html>

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <jsp:useBean class="pt.articleHelper" id="articleBean" />
    <jsp:setProperty property="searchString" name="articleBean" value="${searchString}" />
    <jsp:setProperty property="author_id" name="articleBean" value="${author_id}" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile - PoliTalk</title>
</head>

<%-- role is the session attribute. THIS MAY NEED TO MOVE TO A DIFFERENT PAGE	 --%>
<c:if test="${sessionScope.role eq 4}">
    <a href="/adminControls" />Admin Controls</a>
    <a href="/modControls" />Moderator Controls</a>
</c:if>

<c:if test="${sessionScope.role eq 4}">
    <a href="/modControls" />Moderator Controls</a>
</c:if>


${user_object.name}
${user_object.image_path}


<form action="/profile" method="get">
    <input type="text" name="searchString" required />
    <input type="hidden" name="user_id" value="${user_id}">
    <input type="submit" value="submit" />
</form>

<table cellpadding="10">
<tr> <td></td> <td>Article Title</td> <td>Hits</td>  </tr>
<c:forEach items="${articleBean.articleSearchOfUser}" var="article">
<%-- list all of this user's articles --%>
<tr>
    <td><a href="article?id=${article.id}">${article.image_path}</a></td>
    <td>${article.title}</td>
    <td>${article.hits}</td>
</tr>
</c:forEach>

</table>

</html>
