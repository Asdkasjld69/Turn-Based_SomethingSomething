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
<title>BATTLELOG</title>
</head>
	<span>-第${battlelogs.get(0).turn}回合开始-</span>
	<c:forEach items="${battlelogs}" var="battlelog" varStatus="row">
		<li style="list-style-type:none"> 
			${battlelog.player_name}(${battlelog.character_name})(${battlelog.movex},${battlelog.movey})
		</li>
	</c:forEach>
	<span>-第${battlelogs.get(0).turn}回合结束-</span>
</html>