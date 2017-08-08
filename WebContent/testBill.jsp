<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="calculateprice.jsp">

Pizza (50 Rs):<input type="checkbox" name="pizza" value="50"/>
Dosa (90 Rs):<input type="checkbox" name="dosa" value="90"/>


  <select name="item">
    <option value="1">1</option>
    <option value="2">2</option>
    <option value="3">3</option>
  </select>
  <input type="submit" value="Submit">


<input type="submit" value="get bill"/>

</form>


</body>
</html>