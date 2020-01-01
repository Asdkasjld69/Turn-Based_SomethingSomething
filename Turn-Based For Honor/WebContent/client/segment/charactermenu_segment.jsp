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
<title>CHARACTERMENU_SEGMENT</title>
</head>
<form action="#" id="charactermenu_head" class="hidden">
	<input type="hidden" name="id" value="${session.id }">
	<input type="hidden" name="type" value="character_select">
</form>
<c:forEach items="${chars}" var="chara" varStatus="row">
	<form action="#" id="chara${chara.id}info" class="hidden">
		<input type="hidden" name="id" value="${session.id }">
		<input type="hidden" name="type" value="character_select">
		<input type="hidden" name="character_id" value="${chara.id}">
		<input type="hidden" name="ccount" value="${thisuserpcount}">
		<c:forEach items="${thisuserpl}" var="pcharacter" varStatus="num">
			<input type="hidden" name="player_idx${num.index}" value="${pcharacter.id}">
		</c:forEach>
	</form>
	<div class="charaunit select nointeract" id="chara${chara.id}" >
		<div class="charaunit" style="background:url(${pageContext.request.contextPath}/img/avatar/character/${chara.id}.gif) no-repeat center;background-size:cover;">
			<br/><br/><br/>
			<span class="label">${chara.name}</span>
		</div>
	</div>
</c:forEach>
<script type="text/javascript">
$('.charaunit').click(function(){
	var id = $(this).attr("id");
	function fcm(){
		console.log("Updating CharacterMenu");
		return refreshDiv("charactermenu","post","#"+id+"info","/Turn-Based_For_Honor/LoadSessionServlet",false);
	}
	function fpl(){
		console.log("Updating PlayerList");
		return refreshDiv("playerlist","get","#playerlist_head","/Turn-Based_For_Honor/LoadSessionServlet",false);
	}
	function fbf(){
		console.log("Updating BattleField");
		return refreshDiv("battlefield","get","#battlefield_head","/Turn-Based_For_Honor/LoadSessionServlet",false);
	}
	function ftm(){
		console.log("Updating Timer");
		return refreshDiv("timer","get","#timer_head","/Turn-Based_For_Honor/LoadSessionServlet",false);
	}
	function updateplayerchara(){
           var tasks = [fcm,fbf,ftm,fpl];
           return tasks.reduce(function(prev,next){
             return prev.then(next);
           },$.Deferred().resolve());
       }
	$.when(updateplayerchara()).done(function(){
           console.log("Updated");
	})
	$('#charactermenu').hide();
});
</script>
</html>