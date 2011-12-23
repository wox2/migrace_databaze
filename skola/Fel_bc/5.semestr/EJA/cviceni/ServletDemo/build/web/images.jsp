<%-- 
    Document   : images
    Created on : Sep 26, 2010, 3:42:02 PM
    Author     : marek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<c:url value="/GetImage" var="imageURL">
    <c:param name="imageBorder" value="${requestScope.imageBorder}" />
</c:url>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Servlet Demo</title>
    </head>
    <body>
        <h1>Here is an image:</h1>

        <form action="Images" method="post">
            Select border color:
            <input type="text" value="${param.imageBorderText}" name="imageBorderText" /> (enter red, green, or blue)<br />
            Enter text:
            <input type="text" value="${param.imageText}" name="imageText" /> <br />
            <input type="submit" value="refresh" />
        </form>

        <img src="${imageURL}" alt="image from a servlet" />
    </body>
</html>
