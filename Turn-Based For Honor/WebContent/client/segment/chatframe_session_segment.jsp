<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.js"></script>
<link href="${pageContext.request.contextPath}/css/style.css" type="text/css" rel="stylesheet"/>
<title>CHAT_SESSION_SEGMENT</title>
</head>
<form action="#" method="get" id="chat_init_head" class="hidden">
	<input type="hidden" name="type" value="session">
	<input type="hidden" name="id" value="${session.id}">
	<input type="hidden" name="init" value="1">
</form>
<form action="#" method="get" id="chat_head" class="hidden">
	<input type="hidden" name="type" value="session">
	<input type="hidden" name="id" value="${session.id}">
</form>
<form action="#" method="get" id="chat_check_head" class="hidden">
	<input type="hidden" name="type" value="session">
	<input type="hidden" name="id" value="${session.id}">
	<input type="hidden" name="check" value="1">
</form>
<div id="chat" class="chat" style="width:100%;height:220px">
</div>
<c:if test="${thisuser!=null||currentuser.role=='SUPER'}">
<form action="#" method="post" id="inputmessage" style="margin-top:5px" onkeydown="if(event.keyCode==13) return false;">
	<input type="hidden" name="type" value="session">
	<input type="hidden" name="id" value="${session.id}">
	<input type="text" id="message" name="message" style="width:240px;height:18px;margin-left: 5px">
</form>
</c:if>
<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
<script type="text/javascript">
$(function(){
	$('#message').width($('#chat').width());
	$(window).resize(function(){
		$('#message').width($('#chat').width());
	})
	console.log("Updating Chats");
	refreshDiv("chat","get","#chat_head","/Turn-Based_For_Honor/ChatServlet",true);
	$('#inputmessage').keydown (function(event){
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

</html>