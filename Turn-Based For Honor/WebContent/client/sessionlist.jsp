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

<body onload="refreshDiv('sesslist','get','#search','/Turn-Based_For_Honor/ListSessionServlet',${false});">
	<div class="window">
		<form action="#" id="search" method="GET" class="left nointeract">
			<input type="hidden" name="type" value="${currentuser.role}">
			<table>
				<c:if test="${currentuser.role.equals('SUPER')}">
					<tr>
						<td>房间id</td>
						<td><input type="text" name="search_id" value=""></td>
						<td>地图id</td>
						<td>
							<input type="text" name="search_map_id" value="">
						</td>
					</tr>
				</c:if>
				<tr>
					<td>房间名</td>
					<td><input type="text" name="search_name" value=""></td>
					<td>地图</td>
					<td>
						<input type="text" name="search_map_name" value="">
					</td>
					<td>状态</td>
					<td>
						<select id="search_state" name="search_state">
							<option value="" selected="selected">任意</option>
							<option value="0">准备中</option>
							<option value="1">已开始</option>
						</select>
					</td>
					<td>密码</td>
					<td>
						<select id="search_password" name="search_password">
							<option value="" selected="selected">任意</option>
							<option value="N">无</option>
							<option value="Y">有</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>人数</td>
					<td><input type="range" id="search_cap" name="search_cap" min="1" max="4" step="1" value="1"><span id="capnum">任意</span></td>
				</tr>
			</table>
		</form>
		<button id="createsession" class="nointeract" style="float:right">创建新房间</button>
		<table class="list" style="width:98%">
			<tr style="background:#EEEEEE">
				<td width="30%">
					房间名
				</td>
				<td width="30%">
					地图
				</td>
				<td width="10%">
					人数
				</td>
				<td width="10%">
					状态
				</td>
			</tr>
		</table>
		<div id="sesslist" style="width:100%;height:500px;overflow-y:scroll">
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
		</div>
	</div>
	<iframe id="createframe" class="createframe subwindow float hidden" src="${pageContext.servletContext.contextPath}/client/subwindow/createmenu.jsp" scrolling="no"></iframe>
	<div id="prompt" class="subwindow float hidden" style="width:256px;height:48px;padding:8px;margin-left:50%;margin-top:20%;color:#FFFFFF">
		<span id="promptmessage">请输入密码:</span>
		<input id="spass" type="password"><br/>
		<button class="cancel" target="#prompt">取消</button>
	</div>
	<script type="text/javascript">
		$('.createframe').hide();
		$('#return', window.parent.document).hide(100);
		$('#createsession').click(function (){
			$('.createframe').toggle(100);
		});
		$('#sesslist').click(function (){
			$('.createframe').hide(100);
		});
		$('#search').click(function (){
			$('.createframe').hide(100);
		});
		$('#search').change(function(){
			refreshDiv("sesslist","get","#search","/Turn-Based_For_Honor/ListSessionServlet",false);
		});
		$('#search_cap').change(function(){
			var num = $('#search_cap').val();
			if(num<=1){
				$('#capnum').html("任意");
			}
			else{
				$('#capnum').html(num);
			}
		})
		
		function attemptJoin(target,password){
			$('.createframe').hide();
			if(password==""){
				location.href=target;
			}
			else{
				$('#prompt').show();
				$('#prompt').keydown(function(event){
					if(event.keyCode==13){
						if($('#spass').val()==password){
							location.href=target;
						}
						else{
							$('#promptmessage').css({"color":"#FFAAAA"});
							alert("密码错误!");
						}
					}
				});
			}
		}
		$('.cancel').click(function(){
			var target = $(this).attr("target");
			$('#promptmessage').css({"color":"#FFFFFF"});
			$(target).hide();
		});
	    function refresh(){
	    	refreshDiv("sesslist","get","#search","/Turn-Based_For_Honor/ListSessionServlet",false);
	    }
	    
	</script>
</body>
</html>