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
<sql:setDataSource
        var="myDS"
        driver="com.mysql.jdbc.Driver"
        url="jdbc:mysql://localhost:3306/hotelDB"
        user="root" password="root35"
    />
     
    <sql:query var="listUsers"   dataSource="${myDS}">
        SELECT * FROM hotel;
    </sql:query>
     
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Hotels</h2></caption>
            <tr>
                <th>Hotel_ID</th>
                <th>User_ID</th>
                <th>Name</th>
                <th>Address</th>
                <th>Images</th>
                <th>Do</th>
                <th>Do</th>
            </tr>
            <c:forEach var="user" items="${listUsers.rows}">
                <tr>
                
                    <td><c:out value="${user.hotel_id}" /></td>
                    <td><c:out value="${user.user_id}" /></td>
                    <td><c:out value="${user.hotel_name}" /></td>
                    <td><c:out value="${user.address}" /></td>
                    <td> <img src="${user.hotelImages}" /></td>
                    <td><a href="portalHotelUpdate.jsp">Update</td>
                    <td><a href="portalHotelDelete.jsp">Delete</td>
                </tr>
               
             </c:forEach>
        </table>
    </div>
</body>
</html>