<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<%
		bean.User user = (bean.User)session.getAttribute("currentuser");
		if(user!=null){
			response.sendRedirect(this.getServletContext().getContextPath()+"/client/main.jsp");
		}
	%>
	<jsp:include page="/client/security/signin.jsp"/>
</body>
</html>