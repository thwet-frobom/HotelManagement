<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>  
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

        SELECT * from hotel;  

    </sql:query>

<form action="food" method="post">
<table>
<tr>
<td>Hotel_ID:</td>
<td>
<select name="hotelid">
<c:forEach var="row" items="${res.rows}">

       <option value="${row.hotel_id }">${row.hotel_id }</option>

        </c:forEach>
        </select>
        </td>
</tr>
<tr>
<td>Food_Name:</td>
<td><input type="text" name="foodname"></td>
</tr>
<tr>
<td>Food_Type:</td>
<td><input type="text" name="foodtype"></td>
</tr>
<tr>
<td>Food_Price:</td>
<td><input type="text" name="foodprice"></td>
</tr>
<tr>
<td>Quantity:</td>
<td><input type="text" name="quantity"></td>
</tr>

</table>
 <input type="submit" value="Add" name="modify">
</form>
</body>
</html>