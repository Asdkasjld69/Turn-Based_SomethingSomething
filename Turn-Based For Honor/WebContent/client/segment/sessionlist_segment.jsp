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
			<tr class="${pass} nointeract" >
				<td width="30%" onclick="attemptJoin('${join}','${session.password}')">
					${session.name}
					<form action="#" id="remove_head${session.id}" class="hidden">
						<input type="hidden" name="role" value="SUPER">
						<input type="hidden" name="type" value="remove">
						<input type="hidden" name="id" value="${session.id}">
					</form>
				</td>
				<td width="30%" onclick="attemptJoin('${join}','${session.password}')">
					${session.map_name}
				</td>
				<td width="10%" onclick="attemptJoin('${join}','${session.password}')">
					${session.ucount}/${session.cap}
				</td>
				<td width="10%" onclick="attemptJoin('${join}','${session.password}')">
					${session.state==0?"准备中":session.state==-1?"已结束":"第"}${session.state>0?session.state:""}${session.state>0?"回合":""}
				</td>
				<c:if test="${currentuser.role=='SUPER'}">
					<td width="5%">
						<button class="removal" sid="${session.id}">删除</button>
					</td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
	<script type="text/javascript">
	$(function(){
		$('.removal').click(function(){
			if(window.confirm("确认删除?")){
				refreshDiv("sesslist","post","#remove_head"+$(this).attr("sid"),"/Turn-Based_For_Honor/ListSessionServlet",false);
			}
		})
	})
	</script>
</html>