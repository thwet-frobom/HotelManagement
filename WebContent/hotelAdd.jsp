<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"

        url="jdbc:mysql://localhost/hotelDB" user="root"

        password="root35" />

    <sql:query dataSource="${db}" var="res">  

        SELECT * from user;  

    </sql:query>

<form action="portalHotel" method="post" enctype="multipart/form-data">
<table>
<tr><td>Hotel Name:</td><td><input type="text" name="hotelname"></td></tr>

<tr>
<td>Hotel Admin ID:</td>
<td>
<select name="userid">
<c:forEach var="row" items="${res.rows}">

    <c:choose>

        <c:when test="${row.userrole.equals('Hotel Admin')}">
        
        <option value="${row.user_id }">${row.user_id }</option>
       
        </c:when>
        
        </c:choose>
        </c:forEach>
        </select>
        </td>

</tr>

<tr><td>Address:</td><td><input type="text" name="address"></td></tr>
<tr><td>Hotel Image:</td><td><input type="file" name="image"></td></tr>

</table>
<input type="submit" value="Add" name="modify">
</form>
</body>
</html>