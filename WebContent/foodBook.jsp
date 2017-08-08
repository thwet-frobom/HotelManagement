
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

<%@ include file="header.jsp"%>

<body>

<% ArrayList arr = new ArrayList();
    String hid = (String) session.getAttribute("hotelid");
    out.println("Hotel ID on Food Page:"+hid);
    %>
    
<font color="Blue"><h2>Available food in this hotel</h2></font>
    <%--  <%
   //String hotelID = (String) session.getAttribute("hotelID");
   String hotelID = request.getParameter("hotelID");
   int hid = Integer.parseInt(hotelID);
   out.println("Hotelidis:"+hotelID);
   %> --%>
   
    <sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"

        url="jdbc:mysql://localhost/hotelDB" user="root"

        password="root35" />

    <sql:query dataSource="${db}" var="res">  

        SELECT * from food where hotel_id=<%= hid %>;  

    </sql:query>

    
<form action="billBooking" method="post">
<table>
    <tr> <td>Snack:</td>
     <tr><th>Food Name</th>
           <th>Click</th> 
          <!--  <th>Available Quantity</th>  -->
           </tr>
    <td><c:forEach var="row" items="${res.rows}">

    <c:choose>

        <c:when test="${row.food_type.equals('Snack')}">

           <%--  <c:if test="${row.isBooked==0}">

            <% singleCount++;
            %> --%>
            <tr>
            <td><c:out value="${row.food_name }"> </c:out></td>
            <td><input type="checkbox" name="snack" value="${row.food_price }"></td>
            <%-- <td><c:out value="${row.quantity }"> </c:out></td> --%>
            
            </tr>
            
        </c:when>
        </c:choose>
        </c:forEach>
        </td>
  </tr>
        <tr> <td>Drink:</td>
        <tr><th>Food Name</th>
           <th>Click</th> 
           <!-- <th>Available Quantity</th>  -->
           </tr>
    <td><c:forEach var="row" items="${res.rows}">

    <c:choose>

        <c:when test="${row.food_type.equals('Drink')}">

             <tr>
             <td></td>
             </tr>
             <tr>
            <td><c:out value="${row.food_name }"> </c:out></td>
            <td><input type="checkbox" name="snack" value="${row.food_price }"></td>
            <%-- <td><c:out value="${row.quantity }"> </c:out></td> --%>
            
            </tr>
        </c:when>
        </c:choose>
        </c:forEach>
        </td>
  </tr>
</table>
<input type="submit" value="Order">
<a href="roomBook.jsp">Back</a>
  </form>  
</body>

</html>
