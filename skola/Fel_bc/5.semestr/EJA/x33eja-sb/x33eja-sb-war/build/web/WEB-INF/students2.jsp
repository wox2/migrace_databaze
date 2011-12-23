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
        <form action="students2" method="post">
            Birth number: 
            <% if (request.getAttribute("birthNumber") != null) {%>
                 <input type="text" name="birthNumber" value="${requestScope.current.birthNumber}" disabled>
            <% } else {%>
                 <input type="text" name="birthNumber" value="${requestScope.current.birthNumber}">
            <% }%>
            <br/>
            First name: <input type="text" value="${requestScope.current.firstName}" name="firstName"><br/>
            Surname: <input type="text" value="${requestScope.current.surname}" name="surname"><br/>
            Courses: <%
            java.util.List<x33eja.model.Course> list = ((x33eja.model.Student) request.getAttribute("current")).getEnrolledIn();

            if (list != null) {
                for (x33eja.model.Course c : list) {
                    out.write(c.getName() + "   ");
                }

            }
            %>
            <br/>
            Add Course:
            <select name="course" size="1">
                <option value="">(no course)</option>
                <%
            for (x33eja.model.Course c : (java.util.List<x33eja.model.Course>) request.getAttribute("courses")) {
                out.write("<option value=" + c.getCourseId() + ">" + c.getName() + "</option>");
            }
                %>
            </select>
            <input type="submit" name="add" value="add course" /><br/><br/>
            <input type="submit" name="cancel" value="cancel" /><input type="submit" name="save" value="save" /><br/>
        </form>        
    </body>
    <h2>Students</h2>
    <a href="students2">add</a><br/>
    <%
            java.util.Map<x33eja.model.Student, Long> map = (java.util.Map<x33eja.model.Student, Long>) request.getAttribute("students");

            for (x33eja.model.Student s : map.keySet()) {
                out.write(s.getFirstName() + " " + s.getSurname() + " (" + map.get(s) + ")" + " <a href=\"students2?birthNumber=" + s.getBirthNumber() + "\">edit</a><br/>");
            }
    %>
</body>
</html>