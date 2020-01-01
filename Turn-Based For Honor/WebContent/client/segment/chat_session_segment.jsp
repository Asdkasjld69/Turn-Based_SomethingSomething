<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.js"></script>
<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
<link href="${pageContext.request.contextPath}/css/style.css" type="text/css" rel="stylesheet"/>
<title>CHAT_SESSION_SEGMENT</title>
<style type="text/css">
li{
overflow: hidden;
text-overflow: ellipsis;
word-wrap: break-word;
word-break: normal;
}
</style>
</head>
	<form action="#" method="get" id="chat_head" class="hidden">
		<input type="hidden" name="type" value="session">
	</form>
	<c:forEach items="${chats}" var="chat">
		<li id="${chat.id}" style="list-style-type:none">
			${chat.username} : ${chat.content}
		</li>
	</c:forEach>
</html>