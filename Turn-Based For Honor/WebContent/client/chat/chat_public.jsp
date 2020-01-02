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
	<form action="#" method="get" id="check_head">
		<input type="hidden" name="type" value="public">
		<input type="hidden" name="check" value="1">
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
	$(function(){
		refreshDiv("chat","get","#head","/Turn-Based_For_Honor/ChatServlet",true);
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
	});
	var currentchat = 0;
	function checkUpdate(type,form,target)
	{
	  var xmlhttp;
	  if (window.XMLHttpRequest)
	  {
	    // IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
	    xmlhttp=new XMLHttpRequest();
	  }
	  else
	  {
	    // IE6, IE5 浏览器执行代码
	    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	  xmlhttp.onreadystatechange=function()
	  {
	    if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
	    	var r = xmlhttp.responseText;
	    	var htmlBlock = r.split("<br/>");
	    	var reg = /pub:[0-9]*/i;
	    	var flag = false;
	    	for (var i in htmlBlock){
	    		var blocks;
	    		if (blocks = htmlBlock[i].match(reg)){
	    			r = blocks[0].replace("pub:","");
	    			flag = true;
	    		}
	    	}
	    	if(flag == false){
	    		r = 0;
	    	}
	    	if(r!=currentchat){
	    		console.log(r);
	    		refreshDiv("chat","get","#head","/Turn-Based_For_Honor/ChatServlet",true);
	    		currentchat = r;
	    		return true; 
	    	}
	    	else{
	    		return false;
	    	}
	    }
	  }
	  target = target+"?t="+new Date().getTime();
	  if(form!=""){
		  target = target+"&"+$(form).serialize();
	  }
	  xmlhttp.open(type,target,true);
	  xmlhttp.send();
	}
	function checkPublicChat(){
		checkUpdate("get","#check_head","/Turn-Based_For_Honor/ChatServlet");
	}
	setInterval("checkPublicChat()",100);
	</script>
</body>
</html>