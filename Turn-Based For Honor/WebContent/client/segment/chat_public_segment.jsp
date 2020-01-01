<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.js"></script>
<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
<style type="text/css">
li{
overflow: hidden;
text-overflow: ellipsis;
word-wrap: break-word;
word-break: normal;
}
</style>
<link href="${pageContext.request.contextPath}/css/style.css" type="text/css" rel="stylesheet"/>
</head>
<c:forEach items="${chats}" var="chat">
	<c:url var="info" value="/UserProfileServlet">
		<c:param name="id" value="${chat.user_id}"></c:param>
	</c:url>
	<li id="${chat.id}" style="list-style-type:none;"><a href="#" onclick="window.open('${pageContext.request.contextPath}/UserProfileServlet?id=${chat.user_id}')">${chat.username}</a>(${chat.create_time}) : ${chat.content}</li>
</c:forEach>

</html>