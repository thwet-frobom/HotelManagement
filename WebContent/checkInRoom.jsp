<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%@page import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

   <center><h2>Sorry:The Room you want is now unavailable:</h2></center><br>
   <center>But: The Following Rooms are available when the date expire:</center>
    <sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"

        url="jdbc:mysql://localhost/hotelDB" user="root"

        password="root35" />

  
    <sql:query dataSource="${db}" var="res">  

        SELECT * from temproom;  

    </sql:query>
<br/>

<form method="post" >
<center><table>
<caption><h2>List of Guests In My Hotel</h2></caption>
<tr>
                <th>Room No</th>
                <th>From Date</th>
                <th>To Date</th>
          
</tr>
<c:forEach var="user" items="${res.rows}">
                <tr>
                    <td><c:out value="${user.roomno}" /></td>
                    <td><c:out value="${user.fromdate}" /></td>
                    <td><c:out value="${user.todate}" /></td>
                </tr>
             </c:forEach>

</table>
</center>
</form>
</body>
</html>