<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/css/style.css" type="text/css" rel="stylesheet"/>
<title>Sign In</title>
</head>
<body>
	<%
		bean.User user = (bean.User)session.getAttribute("currentuser");
		if(user!=null){
			response.sendRedirect(this.getServletContext().getContextPath()+"/ListSessionServlet");
		}
	%>
	<div>
		<c:url var="signin" value="/SignInServlet">
			<c:param name=""></c:param>
		</c:url>
		<form action="${signin}" method="GET">
			<table>
				<tr>
					<td>用户名：</td><td><input type="text" name="username"></td>
				</tr>
				<tr>
					<td>密码：</td><td><input type="password" name="password"></td>
				</tr>
				<tr>
					<td><input type="submit" name="signin" value="登陆"></td><td><input type="button" onclick="location.href='${pageContext.request.contextPath}/client/security/signup.jsp'" name="signon" value="注册"></td>
				</tr>
			</table>
		</form>
		<span>${message}</span>
	</div>
</body>
</html>