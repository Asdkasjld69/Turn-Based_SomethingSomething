<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CHAT_PUBLIC</title>
<link href="${pageContext.request.contextPath}/css/style.css" type="text/css" rel="stylesheet"/>
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.js"></script>
<style type="text/css">
</style>
</head>
<body>
	<form action="#" method="get" id="head">
		<input type="hidden" name="type" value="public">
	</form>
	<div id="chat" style="width:100%;height:475px;overflow-y:scroll">
		<c:forEach items="${chats}" var="chat">
			<c:url var="info" value="/UserProfileServlet">
				<c:param name="id" value="${chat.user_id}"></c:param>
			</c:url>
			<li id="${chat.id}" style="list-style-type:none"><a href="#" onclick="window.open('${pageContext.request.contextPath}/UserProfileServlet?id=${chat.user_id}')">${chat.username}</a>(${chat.create_time}) : ${chat.content}</li>
		</c:forEach>
	</div>
	<form action="#" method="post" id="inputmessage" style="margin-top:5px" onkeydown="if(event.keyCode==13) return false;">
		<input type="hidden" name="type" value="public">
		<input type="text" id="message" name="message" style="width:290px;height:32px">
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts.js"></script>
	<script type="text/javascript">
	refreshDiv("chat","get","#head","/Turn-Based_For_Honor/ChatServlet",${true});
	$(function(){
		$('#chat').height($(window).height()-60);
		$('#message').width($('body').width());
		$(window).resize(function(){
			$('#chat').height($(window).height()-60);
			$('#message').width($('body').width());
		})
		$("#inputmessage").keydown (function(event){
			if(event.keyCode==13){
				if($('#message').val().trim()!=""){
					refreshDiv("chat","post","#inputmessage","/Turn-Based_For_Honor/ChatServlet",true);
					$('#message').val("");
				}
				else{
					return false;
				}
			}
		});
	})
	</script>
</body>
</html>