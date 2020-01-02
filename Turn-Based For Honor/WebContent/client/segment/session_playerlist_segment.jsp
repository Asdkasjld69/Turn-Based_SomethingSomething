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
<%
	System.out.println("THE SESSION IS:"+request.getAttribute("session"));
%>
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
			<c:if test="${(currentuser.role=='SUPER'||currentuser.id==session.host_id)&&player.user_id!=currentuser.id}">
			<td style="color:#FF7777" class="kick" pid="${player.user_id}">KICK</td>
			</c:if>
			<c:if test="${currentuser.role=='SUPER'&&player.user_id!=currentuser.id}">
			<td style="color:#FF4444" class="ban" pid="${player.user_id}">BAN</td>
			</c:if>
			<td class="playerinfo" >
				<form action="#" id="ban${player.user_id}" class="hidden">
					<input type="hidden" name="user_id" value="${player.user_id}">
					<input type="hidden" name="id" value="${session.id}">
					<input type="hidden" name="type" value="ban">
				</form>
				<form action="#" id="kick${player.user_id}" class="hidden">
					<input type="hidden" name="user_id" value="${player.user_id}">
					<input type="hidden" name="id" value="${session.id}">
					<input type="hidden" name="type" value="kick">
				</form>
				<img src="${pageContext.request.contextPath}${player.user_avatar}" style="width:48px;height:48px;">
			</td>
			<td class="playerinfo" style="color:${front};">
				${player.username}
			</td>
		</tr>
	</c:forEach>
</table>
<script type="text/javascript">
	$(function(){
		$('.ban').click(function(){
			var pid = $(this).attr("pid");
			if(window.confirm("要封禁该玩家吗？")){
				var pid = $(this).attr("pid");
				function fpl(){
					console.log("Updating PlayerList");
					return refreshDiv("playerlist","post","#ban"+pid,"/Turn-Based_For_Honor/LoadSessionServlet",false);
				}
				function fbl(){
					console.log("Updating BattleLog");
					return refreshDiv("battlelog","get","#battlelog_head","/Turn-Based_For_Honor/BattleLogServlet",true);
				}
				function fbf(){
					console.log("Updating BattleField");
					return refreshDiv("battlefield","get","#battlefield_head","/Turn-Based_For_Honor/LoadSessionServlet",false);
				}
				function ftm(){
					console.log("Updating Timer");
					return refreshDiv("timer","get","#timer_head","/Turn-Based_For_Honor/LoadSessionServlet",false);
				}
				function updateeverything(){
		            var tasks = [fpl,fbl,ftm,fbf];
		            return tasks.reduce(function(prev,next){
		              return prev.then(next);
		            },$.Deferred().resolve());
		        }
				$.when(updateeverything()).done(function(){
		            console.log("Updated");
		    	});
			}
		});
		$('.kick').click(function(){
			if(window.confirm("要踢出该玩家吗？")){
				var pid = $(this).attr("pid");
				function fpl(){
					console.log("Updating PlayerList");
					return refreshDiv("playerlist","post","#kick"+pid,"/Turn-Based_For_Honor/LoadSessionServlet",false);
				}
				function fbl(){
					console.log("Updating BattleLog");
					return refreshDiv("battlelog","get","#battlelog_head","/Turn-Based_For_Honor/BattleLogServlet",true);
				}
				function ftm(){
					console.log("Updating Timer");
					return refreshDiv("timer","get","#timer_head","/Turn-Based_For_Honor/LoadSessionServlet",false);
				}
				function fbf(){
					console.log("Updating BattleField");
					return refreshDiv("battlefield","get","#battlefield_head","/Turn-Based_For_Honor/LoadSessionServlet",false);
				}
				function updateeverything(){
		            var tasks = [fpl,fbl,ftm,fbf];
		            return tasks.reduce(function(prev,next){
		              return prev.then(next);
		            },$.Deferred().resolve());
		        }
				$.when(updateeverything()).done(function(){
		            console.log("Updated");
		    	});
			}
		});
	})
</script>
</html>