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
<form action="#" id="battlefield_head">
	<input type="hidden" name="id" value="${session.id}">
	<input type="hidden" name="user_id" value="${currentuser.id}">
	<input type="hidden" name="type" value="battlefield">
</form>
<table class="grid">
	<c:forEach items="${map.map}" var="rows" varStatus="row">
		<tr>
			<c:forEach items="${rows}" var="cols" varStatus="col">
				<td>
					<c:set var="object" value=""/>
					<c:if test="${cols==1}">
						<c:set var="object" value="obstacle/obstacle"/>
					</c:if>
					<c:if test="${cols==2}">
						<c:set var="object" value="fire/fire"/>
					</c:if>
					<c:if test="${cols==3}">
						<c:set var="object" value="trap/trap"/>
					</c:if>
					<c:if test="${cols==4}">
						<c:set var="object" value="pit/pit"/>
					</c:if>
					<c:set var="type" value=""/>
					<c:if test="${object!=''}">
						<c:set var="type" value="5"/>
						<c:if test="${col.index>0}">
							<c:if test="${map.map[row.index][col.index-1]==cols }">
								<c:set var="type" value="${type}4"/>
							</c:if>
						</c:if>
						<c:if test="${col.index+1<map.width}">
							<c:if test="${map.map[row.index][col.index+1]==cols }">
								<c:set var="type" value="${type}6"/>
							</c:if>
						</c:if>
						<c:if test="${row.index>0}">
							<c:if test="${map.map[row.index-1][col.index]==cols }">
								<c:set var="type" value="${type}8"/>
							</c:if>
						</c:if>
						<c:if test="${row.index+1<map.height}">
							<c:if test="${map.map[row.index+1][col.index]==cols }">
								<c:set var="type" value="${type}2"/>
							</c:if>
						</c:if>
						<c:if test="${col.index>0}">
							<c:if test="${row.index>0}">
								<c:if test="${map.map[row.index-1][col.index-1]==cols }">
									<c:set var="type" value="${type}7"/>
								</c:if>
							</c:if>
							<c:if test="${row.index+1<map.height}">
								<c:if test="${map.map[row.index+1][col.index-1]==cols }">
									<c:set var="type" value="${type}1"/>
								</c:if>
							</c:if>
						</c:if>
						<c:if test="${col.index+1<map.width}">
							<c:if test="${row.index>0}">
								<c:if test="${map.map[row.index-1][col.index+1]==cols }">
									<c:set var="type" value="${type}9"/>
								</c:if>
							</c:if>
							<c:if test="${row.index+1<map.height}">
								<c:if test="${map.map[row.index+1][col.index+1]==cols }">
									<c:set var="type" value="${type}3"/>
								</c:if>
							</c:if>
						</c:if>
					</c:if>
					<c:if test="${object==''}">
						<c:if test="${col.index>0}">
							<c:if test="${row.index>0}">
								<c:if test="${map.map[row.index][col.index-1]==3&&map.map[row.index-1][col.index]==3 }">
									<c:set var="object" value="trap/trap"/>
									<c:set var="type" value="487"/>
								</c:if>
								<c:if test="${map.map[row.index][col.index-1]==4&&map.map[row.index-1][col.index]==4 }">
									<c:set var="object" value="pit/pit"/>
									<c:set var="type" value="487"/>
								</c:if>
							</c:if>
							<c:if test="${row.index+1<map.height}">
								<c:if test="${map.map[row.index][col.index-1]==3&&map.map[row.index+1][col.index]==3 }">
									<c:set var="object" value="trap/trap"/>
									<c:set var="type" value="421"/>
								</c:if>
								<c:if test="${map.map[row.index][col.index-1]==4&&map.map[row.index+1][col.index]==4 }">
									<c:set var="object" value="pit/pit"/>
									<c:set var="type" value="421"/>
								</c:if>
							</c:if>
						</c:if>
						<c:if test="${col.index+1<map.width}">
							<c:if test="${row.index>0}">
								<c:if test="${map.map[row.index][col.index+1]==3&&map.map[row.index-1][col.index]==3 }">
									<c:set var="object" value="trap/trap"/>
									<c:set var="type" value="689"/>
								</c:if>
								<c:if test="${map.map[row.index][col.index+1]==4&&map.map[row.index-1][col.index]==4 }">
									<c:set var="object" value="pit/pit"/>
									<c:set var="type" value="689"/>
								</c:if>
							</c:if>
							<c:if test="${row.index+1<map.height}">
								<c:if test="${map.map[row.index][col.index+1]==3&&map.map[row.index+1][col.index]==3 }">
									<c:set var="object" value="trap/trap"/>
									<c:set var="type" value="623"/>
								</c:if>
								<c:if test="${map.map[row.index][col.index+1]==4&&map.map[row.index+1][col.index]==4 }">
									<c:set var="object" value="pit/pit"/>
									<c:set var="type" value="623"/>
								</c:if>
							</c:if>
						</c:if>
					</c:if>
					<c:if test="${object !=''}">
						<c:set var="object" value="${object}_${type}"/>
						<div class="blocks" style="background:url(img/terrian/${object}.gif)">
							<!--<c:out value="${type}"></c:out>-->
						</div>
					</c:if>
					<c:if test="${object ==''}">
						<c:set var="isplayer" value="${0}"/>
						<c:set var="player_id" value=""/>
						<c:set var="targeted" value=""/>
						<c:forEach items="${characters}" var="chara">
							<c:if test="${row.index==chara.posy&&col.index==chara.posx}">
								<c:set var="isplayer" value="${1}"/>
								<c:set var="player_chara" value="${chara}"/>
							</c:if>
						</c:forEach>
						<c:forEach items="${thisbattlelogs}" var="battlelog">
							<c:if test="${row.index==battlelog.movey&&col.index==battlelog.movex}">
								<c:set var="targeted" value=" lockedon"/>
							</c:if>
						</c:forEach>
						<c:if test="${isplayer<=0||player_chara.state<=-1}">
							<form action="#" id="init${col.index}_${row.index}">
								<input type="hidden" name="user_id" value="${currentuser.id}">
								<input type="hidden" name="player_id" value="${thisuserpcl!=null&&thisuserpcl.size()>0?thisuserpcl.get(0).getId():null}">
								<input type="hidden" name="session_id" value="${session.id}">
								<input type="hidden" name="movex" value="${col.index}">
								<input type="hidden" name="movey" value="${row.index}">
								<input type="hidden" name="stateno" value="0">
								<input type="hidden" name="priority" value="5">
							</form>
							<div class="coord blocks select${targeted}" id="" posx="${col.index}" posy="${row.index}"></div>
						</c:if>
						<c:if test="${isplayer>0&&player_chara.state>-1}">
							<form action="#" id="action${player_chara.id}">
								<input type="hidden" name="user_id" value="${currentuser.id}">
								<input type="hidden" name="player_id" value="${player_chara.id}">
								<input type="hidden" name="session_id" value="${session.id}">
								<input type="hidden" name="movex" value="${col.index}">
								<input type="hidden" name="movey" value="${row.index}">
								<input type="hidden" name="stateno" value="0">
								<input type="hidden" name="priority" value="5">
							</form>
							<div class="pchara blocks select${targeted}" posx="${col.index}" posy="${row.index}" id="" isthis="${player_chara.user_id==currentuser.id}" cid="${player_chara.character_id}">
								<div class="blocks" style="background: url(img/avatar/character/${player_chara.character_id}.gif)">
									<span class="label">${player_chara.health}/${player_chara.health_max}</span>
									<c:if test="${player_chara.user_id==currentuser.id}">
										<br/><br/>
										<span class="label">YOU</span>
									</c:if>
								</div>
							</div>
						</c:if>
					</c:if>
				</td>
			</c:forEach>
		</tr>
	</c:forEach>
</table>
<script type="text/javascript">
$(function(){
	$('.coord').click(function(){
		var sessionstate = ${session.state};
		if(sessionstate>-2){
			var haschara = ${thisuserpcl!=null&&thisuserpcl.size()>0};
			var charastate = ${(thisuserpcl!=null&&thisuserpcl.size()>0)?thisuserpcl.get(0).state:-1};
			if((sessionstate==0)&&(haschara==true)&&(charastate<sessionstate)){
				$(this).attr("id","coordchosen");
				$(this).removeClass("select").addClass("targeted");
				$('.coord').not(this).attr("id","");
				$('.coord').not(this).removeClass("targeted").addClass("select");
				$('#confirminit').click(function(){
					var posx = $('#coordchosen').attr("posx");
					var posy = $('#coordchosen').attr("posy");
					var form = "#init"+posx+"_"+posy;
					
					function fbl(){
						console.log("Updating BattleLog");
						return refreshDiv("battlelog","post",form,"/Turn-Based_For_Honor/BattleLogServlet",true);
					}
					function fbf(){
						console.log("Updating BattleField");
						return refreshDiv("battlefield","get","#battlefield_head","/Turn-Based_For_Honor/LoadSessionServlet",false);
					}
					function fpl(){
						console.log("Updating PlayerList");
						return refreshDiv("playerlist","get","#playerlist_head","/Turn-Based_For_Honor/LoadSessionServlet",false);
					}
					function ftm(){
						console.log("Updating Timer");
						return refreshDiv("timer","get","#timer_head","/Turn-Based_For_Honor/LoadSessionServlet",false);
					}
					function updatebattlelog(){
			            var tasks = [fbl,ftm,fbf,fpl];
			            return tasks.reduce(function(prev,next){
			              return prev.then(next);
			            },$.Deferred().resolve());
			        }
					$.when(updatebattlelog()).done(function(){
			            console.log("Updated");
		        	});
				});
			}
		}
	});
	$('.pchara').click(function(){
		var sessionstate = ${session.state};
		if(sessionstate>-2){
			var isthis = $(this).attr("isthis");
			var cid = $(this).attr("cid");
			$(this).attr("id","charachosen");
			$('.pchara').not(this).attr("id","");
			if(isthis=="true"){
				alert("IT'S YOU!");
			}
		}
	});
});
</script>

</html>