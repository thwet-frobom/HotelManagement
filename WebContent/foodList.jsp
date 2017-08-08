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
        SELECT * FROM food;
    </sql:query>
     
   
    
    <form action="billBooking" method="post">
    <table>

    <tr>


   
        <td>Snack: </td>
         <tr><th>Food Name</th>
           <th>Price</th> 
           <th>Quantity</th> 
           </tr>
        <td><c:forEach var="row" items="${listUsers.rows}">

    <c:choose>

        <c:when test="${row.food_type.equals('Snack')}">

            <%-- <c:if test="${row.isBooked==0}">

            <% singleCount++;
            %>
            
            <c:out value="${row.roomNo }"> </c:out>
            
            </c:if> --%>
            <tr>
            <td><c:out value="${row.food_name}" /></td>
           
            <td><c:out value="${row.food_price}" /></td>
            <td><c:out value="${row.quantity}" /></td>
            <td><input type="text" name="userquantity"></td>
            <td><input type="submit" value="Order" name="order"></td>
         </tr>
        </c:when>
    </c:choose>

    </c:forEach>
         </td>
         
    </tr>

    <tr>

        <td>Drink: </td>
        <tr><th>Food Name</th>
           <th>Price</th> 
           <th>Quantity</th> 
           </tr>
        <td>
        <c:forEach var="row" items="${listUsers.rows}">

      <c:choose>

        <c:when test="${row.food_type.equals('Drink')}">

        <tr>
            <td><c:out value="${row.food_name}" /></td>
    
            <td><c:out value="${row.food_price}" /></td>
            <td><c:out value="${row.quantity}" /></td>
            <td><input type="text" name="userquantity"></td>
            <td><input type="submit" value="Order" name="order"></td>
        </tr>
        </c:when>
    </c:choose>
    </c:forEach>
        
        </td>

    </tr>
    </table>
    </form>
</body>
</html>