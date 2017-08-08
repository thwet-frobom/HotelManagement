<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String foodID = request.getParameter("foodID");
out.println("Food ID:"+foodID);
session.setAttribute("foodid", foodID);
%>
<form action="food" method="post">
Hotel_ID:<input type="text" name="hotelid"><br>
Food_Name:<input type="text" name="foodname"><br>
Food_Type:<input type="text" name="foodtype"><br>
Food_Price:<input type="text" name="foodprice"><br>
Quantity:<input type="text" name="quantity"><br>
<input type="submit" value="Update" name="modify">
</form>
</body>
</html>