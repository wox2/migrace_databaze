<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Students</title>
    </head>
    <body>
        <h2>Edit Student</h2>
        <form action="save" method="post">
            Birth number: 
            
            <% if (request.getAttribute("birthNumber") != null) {%>
                <input type="text" name="birthNumber" value="${requestScope.birthNumber}" disabled>
                <input type="hidden" name="birthNumber" value="${requestScope.birthNumber}"/>
            <% } else { %>
                <input type="text" name="birthNumber" value="${requestScope.birthNumber}">
            <% }%>
            <br/>
            First name: <input type="text" value="${requestScope.firstName}" name="firstName"><br/>
            Surname: <input type="text" value="${requestScope.surname}" name="surname"><br/>
            Add Course:
                    <select name="course" size="1">
                        <option value="">(no course)</option>
                        <%
                           for( x33eja.model.Course c : (java.util.List<x33eja.model.Course>) request.getAttribute("courses")) {
                            out.write("<option value="+c.getCourseId()+">"+c.getName()+"</option>");
                           }
                        %>
                    </select>
            <input type="submit" value="save" /><br/>
        </form>
        </body>
        <h2>Students</h2>
        <a href="students">add</a><br/>
        <%
            java.util.Map<x33eja.model.Student,Integer> map = (java.util.Map<x33eja.model.Student,Integer>) request.getAttribute("students");

            for(x33eja.model.Student s : map.keySet()) {
                out.write(s.getFirstName()+" " + s.getSurname()+" ("+map.get(s)+")"+" <a href=\"students?birthNumber="+s.getBirthNumber()+"\">edit</a><br/>");
            }
        %>
    </body>
</html>