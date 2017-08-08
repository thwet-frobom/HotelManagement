<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form  action="register" method="post">
Name:<input type="text" name="name"><br>
Password:<input type="password" name="password"><br>
ConfirmPassword:<input type="text" name="cpassword"><br>
NRC:<input type="text" name="nrc"><br>
Address:<input type="text" name="address"><br>
Phone:<input type="text" name="phone"><br>
E-mail:<input type="text" name="email"><br>
Gender:<input type="radio" name="gender"> Male
       <input type="radio" name="gender"> Female
       <br>
<input type="submit" value="Submit">
</form>
</body>
</html>