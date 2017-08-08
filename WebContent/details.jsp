<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Insert title here</title>

</head>

<%@ include file="header.jsp"%>

<body>

   <%
   String hotelID = request.getParameter("hotelID");
   session.setAttribute("hotelid", hotelID);
   int hid = Integer.parseInt(hotelID);
   out.println("Hotelidis:"+hotelID);
   %>
    <sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"

        url="jdbc:mysql://localhost/hotelDB" user="root"

        password="root35" />

    <sql:query dataSource="${db}" var="result">  

        SELECT * from hotel where hotel_id=<%= hid %>;  

    </sql:query>

    

    

    <sql:query dataSource="${db}" var="res">  

        SELECT * from room where hotel_id=<%= hotelID %>;  

    </sql:query>

    

<br/>

    <c:forEach var="table" items="${result.rows}">

        <h2> <font color="blue"> <c:out value="${table.hotel_name}" /> Hotel Yangon  </font></h2>

        <br/>

        <h3> <c:out value="${table.address}" /> </h3>

        <img src="${table.hotelImages}" />

        

    </c:forEach>

    

    <br/>

    

    

    <% int singleCount=0;int doubleCount=0; %>

    <c:forEach var="row" items="${res.rows}">

    <c:choose>

        <c:when test="${row.roomType.equals('Single')}">

            <c:if test="${row.isBooked==0}">

            <% singleCount++; %>
            
           
            </c:if>

        </c:when>

        <c:otherwise>

            <c:if test="${row.isBooked==0}">

            <% doubleCount++; %>
          
            </c:if>

        </c:otherwise>

    </c:choose>

        

        

    </c:forEach>

    

    

    <h3> <font color="green" > Available Rooms: </font> </h3>

    

    <table>

    <tr>

        <td>Single Room: </td>

        <td><%=singleCount%>
   
        </td>

    </tr>

    

    <tr>

        <td>Double Room: </td>

        <td><%=doubleCount %></td>

    </tr>

    

    </table>

    

    <a href="<c:url value='userLogin.jsp?hotelID=hotelID'/>">Booking</a>
    <a href="index.jsp">Back</a>

    

</body>

</html>