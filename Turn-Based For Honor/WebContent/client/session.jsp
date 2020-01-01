<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.4.1.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts.js"></script>
<link href="${pageContext.request.contextPath}/css/style.css" type="text/css" rel="stylesheet"/>
<title>${map.name}</title>
</head>
<body>
	<div class="window">
		<div class="battlefield" id="background">
			<div class="back nointeract left">
				<table class="grid field">
					<c:forEach items="${map.map}" var="cols" >
						<tr>
							<c:forEach items="${cols}" var="col">
								<td>
									<div class="blocks" style="background:url(img/terrian/land/land_<%=(int)Math.floor(Math.random()*4)%>.gif)">
									</div>
								</td>
							</c:forEach>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="front nointeract left" id="battlefield">
				<form action="#" id="battlefield_head">
					<input type="hidden" name="id" value="${session.id}">
					<input type="hidden" name="user_id" value="${currentuser.id}">
					<input type="hidden" name="type" value="battlefield">
				</form>
			</div>
		</div>
		<!-- 以下是浮窗 -->
		<div id="chatframe" class="float chatframe subwindow left fade" style="width:30%;height:25%;">
			<form action="#" method="get" id="chat_head" class="hidden">
				<input type="hidden" name="id" value="${session.id}">
				<input type="hidden" name="type" value="session">
			</form>
			<form action="#" method="get" id="chat_init_head" class="hidden">
				<input type="hidden" name="id" value="${session.id}">
				<input type="hidden" name="type" value="session">
				<input type="hidden" name="init" value="1">
			</form>
		</div>
		<button class="buttontoggle float fade" target="#chatframe" style="margin-left: 30%;"></button>
		<div id="battlelog" class="hidden">
			<form action="#" method="get" id="battlelog_head" class="hidden">
				<input type="hidden" name="session_id" value="${session.id}">
				<input type="hidden" name="user_id" value="${currentuser.id}">
			</form>
		</div>
		<div class="float playerlist subwindow fade" id="playerlist" style="height:240px">
			<form action="#" method="get" id="playerlist_head" class="hidden">
				<input type="hidden" name="type" value="playerlist">
				<input type="hidden" name="id" value="${session.id}">
				<input type="hidden" name="user_id" value="${currentuser.id}">
			</form>
		</div>
		<button class="buttontoggle float fade" target="#playerlist" style="margin-left: 96%;margin-top:240px;background:url(img/ui/toggle_m90.gif);"></button>
		<div id="charactermenu" class="charactermenu subwindow float hidden">
			<form action="#" id="charactermenu_head" class="hidden">
				<input type="hidden" name="id" value="${session.id }">
				<input type="hidden" name="type" value="character_select">
			</form>
		</div>
		<div id="timer" class="timer float nointeract">
			<form action="#" method="get" id="timer_head" class="hidden">
				<input type="hidden" name="type" value="timer">
				<input type="hidden" name="id" value="${session.id}">
			</form>
		</div>
	</div>
	
	<script type="text/javascript">
		$('#return', window.parent.document).show(100);
		$('#return', window.parent.document).click(function(){
			history.go(-1);
		});
		$('.buttontoggle').click(function (){
			var target = $(this).attr("target");
			$(target).toggle();
		});
	</script>
</body>
<script type="text/javascript">
	window.onload=function(){
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
		function fpl(){
			console.log("Updating PlayerList");
			return refreshDiv("playerlist","get","#playerlist_head","/Turn-Based_For_Honor/LoadSessionServlet",false);
		}
		function fcm(){
			console.log("Updating CharacterMenu");
			return refreshDiv("charactermenu","get","#charactermenu_head","/Turn-Based_For_Honor/LoadSessionServlet",false);
		}
		function fcf(){
			console.log("Updating Chatframe");
			return refreshDiv("chatframe","get","#chat_init_head","/Turn-Based_For_Honor/ChatServlet",true);
		}
		function updateeverything(){
            var tasks = [fbl,ftm,fbf,fpl,fcm,fcf];
            return tasks.reduce(function(prev,next){
              return prev.then(next);
            },$.Deferred().resolve());
        }
		$.when(updateeverything()).done(function(){
            console.log("Updated");
    	});
	}
</script>
</html>