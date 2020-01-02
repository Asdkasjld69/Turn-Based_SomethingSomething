<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="refresh" content="2;URL=${pageContext.request.contextPath}/client/security/signin.jsp">
<title>SUCCESS</title>
</head>
<body>
	<h1>激活成功！</h1>
	<h2>即将自动跳转至登陆页面...</h2>
	<a href="${pageContext.request.contextPath}/client/security/signin.jsp">心急的请点这里</a>
</body>
</html>