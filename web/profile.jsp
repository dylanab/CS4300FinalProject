<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile - PoliTalk</title>
</head>
<c:if test="${role} = 'admin'">
    <a href="url of admincontrols" />
    <a href="url of modControls" />
</c:if>

<c:if test="${role} = 'mod'">
    <a href="url of adminControls" />
    <a href="url of modControls" />
</c:if>


${user.name}
${user.image_path}

<table cellpadding="10">
<tr> <td></td> <td>Article Title</td> <td>Hits</td>  </tr>
<c:forEach items="${articleList}" var="article">
<%-- list all of this user's articles --%>
<tr>
    <td>${article.image_path}</td>
    <td>${article.title}</td>
    <td>${article.hits}</td>
</tr>
</c:forEach>

</table>

</html>
