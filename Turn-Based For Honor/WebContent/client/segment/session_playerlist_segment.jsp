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
</head>
<form action="#" method="get" id="playerlist_head" class="hidden">
	<input type="hidden" name="type" value="playerlist">
	<input type="hidden" name="id" value="${session.id}">
	<input type="hidden" name="user_id" value="${currentuser.id}">
</form>
<table class="nointeract">
	<c:forEach items="${players}" var="player">
		<c:set var="back" value="#000000"/>
		<c:set var="front" value="#FFFFFF"/>
		<c:if test="${player.team==1}">
			<c:set var="front" value="#66FF66"/>
		</c:if>
		<c:if test="${player.team==2}">
			<c:set var="front" value="#FF6666"/>
		</c:if>
		<c:if test="${player.team==3}">
			<c:set var="front" value="#6666FF"/>
		</c:if>
		<c:if test="${player.team==4}">
			<c:set var="front" value="#FF66FF"/>
		</c:if>
		<c:if test="${player.health<=0}">
			<c:set var="back" value="#990000"/>
		</c:if>
		<c:if test="${player.state<session.state}">
			<c:set var="back" value="#666666"/>
		</c:if>
		<c:if test="${player.state>=session.state}">
			<c:set var="back" value="#AAFFAA"/>
		</c:if>
		<c:if test="${player.state<=-2}">
			<c:set var="back" value="#333333"/>
		</c:if>
		<tr style="background:${back};" ondblclick="window.open('${pageContext.request.contextPath}/UserProfileServlet?id=${player.user_id}')">
			<td class="playerinfo" >
				<img src="${pageContext.request.contextPath}${player.user_avatar}" style="width:48px;height:48px;">
			</td>
			<td class="playerinfo" style="color:${front};">
				${player.username}
			</td>
		</tr>
	</c:forEach>
</table>
</html>