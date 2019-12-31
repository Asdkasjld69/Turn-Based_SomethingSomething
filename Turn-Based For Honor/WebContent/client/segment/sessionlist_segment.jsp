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
<title>LOBBY</title>
</head>
	<table class="list" style="width:100%">
		<c:forEach items="${sessions}" var="session">
			<c:url var="join" value="/LoadSessionServlet">
				<c:param name="id" value="${session.id}"/>
				<c:param name="user_id" value="${currentuser.id}"/>
			</c:url>
			<c:set var="pass" value="item"/>
			<c:if test="${session.password!=null&&session.password!=''}">
				<c:set var="pass" value="itempass"/>
			</c:if>
			<tr class="${pass} nointeract" onclick="attemptJoin('${join}','${session.password}')">
				<td width="30%">
					${session.name}
				</td>
				<td width="30%">
					${session.map_name}
				</td>
				<td width="10%">
					${session.ucount}/${session.cap}
				</td>
				<td width="10%">
					${session.state==0?"准备中":session.state==-1?"已结束":"第"}${session.state>0?session.state:""}${session.state>0?"回合":""}
				</td>
			</tr>
		</c:forEach>
	</table>
</html>