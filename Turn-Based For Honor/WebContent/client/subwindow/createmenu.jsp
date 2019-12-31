<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<link href="${pageContext.request.contextPath}/css/style.css" type="text/css" rel="stylesheet"/>
<title>CREATE</title>
</head>
<%
	if(request.getAttribute("loaded")==null){
		request.getRequestDispatcher("/ListMapServlet").forward(request,response);
	}
%>
<body>
	<form action="${pageContext.request.contextPath}/CreateSessionServlet" id="createmenu" class="createmenu">
		<div style="float: left;">
			<c:set var="map_id_default" value="1"/>
			<c:set var="map_name_default" value="CRASH"/>
			<c:set var="map_avatar_default" value="url(${pageContext.request.contextPath}/img/avatar/map/${map_id_default}.gif) no-repeat center"/>
			<input id="map_id" type="hidden" name="map_id" value="${map_id_default}"/>
			<span id="map_name">${map_name_default}</span>
			<div id="map" style="width: 196px;height: 196px;background:${map_avatar_default};background-size:cover;"></div>
		</div>
		<div id="setting" class="nointeract" style="float:left;margin-top:12px;height:240px">
			<table >
				<tr></tr>
				<tr>
					<td>房间名：</td>
					<td><input type="text" name="session_name" id="session_name" /></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input type="text" name="session_password" /></td>
				</tr>
				<tr>
					<td>人数：</td>
					<td>
						<select name="cap">
							<option value="2" selected="selected">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
						</select>
					</td>
				</tr>
				<tr>
					<td></td>
					<td><input type="button" id="create" name="create" value="创建"></td>
				</tr>
			</table>
		</div>
	</form>
	<div id="mapmenu" class="mapmenu subwindow float hidden">
		<c:forEach items="${maps}" var="map" varStatus="row">
			<div id="${map.id}" name="${map.name}" class="mapunit" style="background:url(${pageContext.request.contextPath}/img/avatar/map/${map.id}.gif);">
				<br/><br/>
				<span class="label nointeract">${map.name}</span>
			</div>
		</c:forEach>
	</div>
	<script type="text/javascript">
		$('.mapmenu').hide();
		$('#map').click(function (){
			$('.mapmenu').toggle(100);
		})
		$('#setting').click(function (){
			$('.mapmenu').hide(100);
		})
		$('.mapunit').click(function (){
			var i = $(this).attr("id");
			var n = $(this).attr("name");
			var b = $(this).attr("style").replace("background:","").replace(";","");
			$('#map_id').val(i);
			$('#map_name').html(n);
			$('#map').css({"background":b});
			$('.mapmenu').hide();
		})
		$('#create').click(function (){
			var flag = true;
			if($('#session_name').val().trim()==""){
				flag = false;
			}
			if(flag){
				$('#createmenu').submit();
			}
			else{
				return false;
			}
		})
	</script>
</body>
</html>