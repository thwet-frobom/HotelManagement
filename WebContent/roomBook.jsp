<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%@page import="java.util.*"%>

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Insert title here</title>

</head>

<%@ include file="header.jsp"%>

<body>

    <% ArrayList arr = new ArrayList();
    String hid = (String) session.getAttribute("hotelid");
    out.println("Hotel ID on Room Page:"+hid);
    %>

    <sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"

        url="jdbc:mysql://localhost/hotelDB" user="root"

        password="root35" />

    <sql:query dataSource="${db}" var="result">  

        SELECT * from hotel where hotel_id=<%= hid %>;  

    </sql:query>

    

    

    <sql:query dataSource="${db}" var="res">  

        SELECT * from room where hotel_id=<%= hid %>;  

    </sql:query>

    

<br/>

    <c:forEach var="table" items="${result.rows}">

        <h2> <font color="blue"> <c:out value="${table.hotel_name}" /> Hotel Yangon  </font></h2>

        <br/>

        <h3> <c:out value="${table.address}" /> </h3>

        <img src="${table.hotelImages}" />

        

    </c:forEach>

    

    <br/>

    

    

    <% int singleCount=0;int doubleCount=0; int singleRNo=0; int doubleRNo=0;%>
<form action="booking" methos="post">
<table>
    <tr> <td>Single Room:</td>
    <td><c:forEach var="row" items="${res.rows}">

    <c:choose>

        <c:when test="${row.roomType.equals('Single')}">

            <c:if test="${row.isBooked==0}">

            <% singleCount++;
            %>
            
            <c:out value="${row.roomNo }"> </c:out>
            <input type="checkbox" name="single" value="${row.roomNo }">
            </c:if>

        </c:when>
        </c:choose>
        </c:forEach>
        </td>
  </tr>
        <tr> <td>Duble Room:</td>
    <td><c:forEach var="row" items="${res.rows}">

    <c:choose>

        <c:when test="${row.roomType.equals('Double')}">

            <c:if test="${row.isBooked==0}">

            <% doubleCount++;
            %>
            
            <c:out value="${row.roomNo }"> </c:out>
            <input type="checkbox" name="double" value="${row.roomNo }">
            </c:if>

        </c:when>
        </c:choose>
        </c:forEach>
        </td>
  </tr>
    
  <tr>
  <td>DD/MM/YYYY:</td>
  <td>FromDate:<input type="text" name="fromdate"></td>
  <td>ToDate:<input type="text" name="todate"></td>
  </tr>
</table>
<input type="submit" value="Submit"> 
<a href="foodBook.jsp">FoodOrder</a>
<a href="index.jsp">Back</a>
  </form>  
</body>

</html>