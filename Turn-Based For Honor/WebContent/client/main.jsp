<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MAIN MENU</title>
<link href="${pageContext.request.contextPath}/css/style.css" type="text/css" rel="stylesheet"/>
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.js" ></script>
<script src="${pageContext.request.contextPath}/js/scripts.js"></script>

<%
	bean.User user = (bean.User)session.getAttribute("currentuser");
	if(user==null){
		response.sendRedirect(this.getServletContext().getContextPath()+"/client/security/signin.jsp");
	}
%>
</head>
<body>
	<div id="panel" height="590px">
		<table class="right">
			<tr>
				<td style="padding-left: 5px;"><a href="${pageContext.request.contextPath}/SignOutServlet">登出</a></td>
				<td style="padding-left: 5px;">您好，<a href="#" onclick="window.open('${pageContext.request.contextPath}/UserProfileServlet?id=${currentuser.id}')">${currentuser.username}</a></td>
			</tr>
		</table>
		
		<iframe class="left" src="${pageContext.request.contextPath}/client/sessionlist.jsp" id="main" name="main" width="72%" height="590px" scrolling="no"></iframe>
		<a id="return" style="float: left; display: none; margin-left:5px;" href="#">返回</a>
		<iframe class="right" style="margin-top:2%;overflow-y:scroll;" src="${pageContext.request.contextPath}/client/chat/chat_public.jsp" id="chat" name="chat" width="25%" height="540px" scrolling="no"></iframe>
	</div>
</body>
<script type="text/javascript">
$('#main').height($(window).height()-20);
$('#chat').height($(window).height()-80);
$(window).resize(function(){
	$('#main').height($(window).height()-20);
	$('#chat').height($(window).height()-80);
})
</script>
</html>