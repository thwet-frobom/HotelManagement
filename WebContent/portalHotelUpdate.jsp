<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="portalHotel" method="post" enctype="multipart/form-data">
<table>
<tr><td>Hotel Name:</td><td><input type="text" name="hotelname"></td></tr>
<tr><td>Hotel Admin ID:</td><td><input type="text" name="userid"></td></tr>

<tr><td>Address:</td><td><input type="text" name="address"></td></tr>
<tr><td>Hotel Image:</td><td><input type="file" name="image"></td></tr>

</table>
<input type="submit" value="Update" name="modify">
</body>
</html>