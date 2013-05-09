<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css">
            label {display: block;}
        </style>
        <title>Komentáře</title>
    </head>
    <body>
        <h1>Komentáře</h1>
        <p>
        <c:choose>
        <c:when test="${user != null}">
        	Jste přihlášen jako ${user.nickname}. <a href="${url}">Odhlásit se.</a>
        </c:when>
        <c:otherwise>
        	Nejste přihlášen. <a href="${url}">Přihlásit se.</a>
        </c:otherwise>
		</c:choose>
		</p>
        <h2>Vložit nový komentář</h2>
        <%-- data se odesilaji na servlet --%>
        <form action="/" method="GET">
            <label>Text:
                <input type="text" name="message">
            </label>
            <input type="hidden" name="token" value="${token}">
            <input type="hidden" name="tokenName" value="${tokenName}">
            <input type="submit" name="submit" value="Odeslat" >
        </form>

        <%-- tady se vypisuje vysledek konverze - v request scope se ocekava polozka s nazvem amount --%>
        <h2>Komentáře</h2>
        <c:forEach var="item" items="${messages}">
           <div>
           <div>Message from user:
           <c:choose>
           	<c:when test="${item.user != null}">
           		${item.user.nickname}
           	</c:when>
           	<c:otherwise>
           		Anonymous
           	</c:otherwise>
           </c:choose>
           </div>
           <div>${item.message}</div>
           </div>
        </c:forEach>

    </body>
</html>