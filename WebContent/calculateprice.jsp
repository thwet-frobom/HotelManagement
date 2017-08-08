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
String pizza=request.getParameter("pizza");
String dosa=request.getParameter("dosa");

int total=0;

if(pizza!=null){
int pizzaprice=Integer.parseInt(pizza);
total+=pizzaprice;
}
if(dosa!=null){
int dosaprice=Integer.parseInt(dosa);
total+=dosaprice;
}

out.print("Total Price: "+total);
%> 
</body>
</html>