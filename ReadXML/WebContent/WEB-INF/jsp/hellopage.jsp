<%@page import="org.eclipse.jdt.internal.compiler.ast.ForeachStatement"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
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
String message=(String)request.getAttribute("message");
String[]nameary= message.split("\\|");
int length =nameary.length;
for (int i=1;i<nameary.length;i++)
{
	%>
	Message <%=i%>: <%=nameary[i] %> 
	<br>
	<%	
}
%>
</body>
</html>