<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.4.1.js" ></script>
<link href="${pageContext.request.contextPath}/css/style.css" type="text/css" rel="stylesheet"/>
<title>${map.name}</title>
</head>
<%
	bean.Session cs = (bean.Session)request.getAttribute("session");
	bean.User tu = (bean.User)session.getAttribute("currentuser");
	request.setAttribute("thisuser", null);
	request.setAttribute("thisuserpl", null);
	request.setAttribute("thisuserpcl", null);
	request.setAttribute("thisuserpcount", 0);
	request.setAttribute("thisuserpccount", 0);
	if(tu!=null){
		bean.User insess = cs.getUser(tu.getId());
		java.util.List<bean.Player> pl = cs.getUserPlayer(tu.getId());
		java.util.List<bean.Player> pcl = cs.getPlayer(tu.getId());
		request.setAttribute("thisuser", insess);
		request.setAttribute("thisuserpl", pl);
		request.setAttribute("thisuserpcl", pcl);
		if(pl!=null){
			request.setAttribute("thisuserpcount", pl.size());
		}
		if(pcl!=null){
			request.setAttribute("thisuserpccount", pcl.size());
		}
	}
	else{
		response.sendRedirect(this.getServletContext().getContextPath()+"/");
	}
%>
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
												<c:if test="${map.map[row.index][col.index+1]==3&&map.map[row.index-1][col.index]==3 }">
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
										<c:set var="targeted" value="select"/>
										<c:forEach items="${characters}" var="chara">
											<c:if test="${row.index==chara.posy&&col.index==chara.posx}">
												<c:set var="isplayer" value="${1}"/>
												<c:set var="player_chara" value="${chara}"/>
											</c:if>
										</c:forEach>
										<c:forEach items="${battlelogs}" var="battlelog">
											<c:if test="${thisuserpcl!=null&&thisuserpcl.size()>0&&thisuserpcl.get(0).state>=session.state}">
												<c:if test="${row.index==battlelog.movey&&col.index==battlelog.movex}">
													<c:set var="targeted" value="lockedon"/>
												</c:if>
											</c:if>
										</c:forEach>
										<c:if test="${isplayer<=0||player_chara.state<=-1}">
											<form action="#" id="init${col.index}_${row.index}">
												<input type="hidden" name="player_id" value="${thisuserpcl!=null&&thisuserpcl.size()>0?thisuserpcl.get(0).getId():null}">
												<input type="hidden" name="session_id" value="${session.id}">
												<input type="hidden" name="movex" value="${col.index}">
												<input type="hidden" name="movey" value="${row.index}">
												<input type="hidden" name="stateno" value="0">
												<input type="hidden" name="priority" value="5">
											</form>
											<div class="coord blocks ${targeted}" id="" posx="${col.index}" posy="${row.index}"></div>
										</c:if>
										<c:if test="${isplayer>0&&player_chara.state>-1}">
											<form action="#" id="action${player_chara.id}">
												<input type="hidden" name="player_id" value="${player_chara.id}">
												<input type="hidden" name="session_id" value="${session.id}">
												<input type="hidden" name="movex" value="${col.index}">
												<input type="hidden" name="movey" value="${row.index}">
												<input type="hidden" name="stateno" value="0">
												<input type="hidden" name="priority" value="5">
											</form>
											<div class="pchara blocks ${targeted}" posx="${col.index}" id="" posy="${row.index}" isthis="${thisuserpcl.contains(player_character)}" cid="${player_chara.getCharacter_id()}" >
												<div class="blocks" style="background: url(img/avatar/character/warden.gif)"></div>
											</div>
										</c:if>
									</c:if>
								</td>
							</c:forEach>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<!-- 以下是浮窗 -->
		<div id="chatframe" class="float chatframe subwindow left fade" style="width:30%;height:25%;">
			<div id="chat" class="chat" style="width:100%;height:114px">
				<form action="#" method="get" id="chat_head" class="hidden">
					<input type="hidden" name="type" value="session">
				</form>
				<c:forEach items="${chats}" var="chat">
					<li style="list-style-type:none">
						${chat.username} : ${chat.content}
					</li>
				</c:forEach>
			</div>
			<c:if test="${thisuser!=null}">
				<form action="#" method="post" id="inputmessage" style="margin-top:5px" onkeydown="if(event.keyCode==13) return false;">
					<input type="hidden" name="type" value="session">
					<input type="hidden" name="id" value="${session.id}">
					<input type="text" id="message" name="message" style="width:240px;height:18px;margin-left: 5px">
				</form>
				<script type="text/javascript">
					$('#inputmessage').keydown (function(event){
						if(event.keyCode==13){
							if($('#message').val().trim()!=""){
								refreshDiv("chat","post","#inputmessage","/Turn-Based_For_Honor/ChatServlet",true);
								$('#message').val("");
							}
							else{
								return false;
							}
						}
					});
				</script>
			</c:if>
		</div>
		<button class="buttontoggle float fade" target="#chatframe" style="margin-left: 30%;"></button>
		<div id="battlelog" class="hidden"></div>
		<div class="float playerlist subwindow fade" id="playerlist" style="height:240px">
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
		</div>
		<button class="buttontoggle float fade" target="#playerlist" style="margin-left: 96%;margin-top:240px;background:url(img/ui/toggle_m90.gif);"></button>
		<div id="charactermenu" class="charactermenu subwindow float hidden">
			<form action="#" id="charactermenu_head" class="hidden">
				<input type="hidden" name="id" value="${session.id }">
				<input type="hidden" name="type" value="character_select">
			</form>
			<c:forEach items="${chars}" var="chara" varStatus="row">
				<form action="#" id="chara${chara.id}info" class="hidden">
					<input type="hidden" name="id" value="${session.id }">
					<input type="hidden" name="type" value="character_select">
					<input type="hidden" name="character_id" value="${chara.id}">
					<input type="hidden" name="ccount" value="${thisuserccount}">
					<c:forEach items="${thisuserpl}" var="pcharacter" varStatus="num">
						<input type="hidden" name="player_idx${num.index}" value="${pcharacter.id}">
					</c:forEach>
				</form>
				<div class="charaunit select nointeract" id="chara${chara.id}" >
					<div style="background:url(${pageContext.request.contextPath}/img/avatar/character/${chara.id}.gif);">
						<br/><br/><br/>
						<span class="label nointeract">${chara.name}</span>
					</div>
				</div>
			</c:forEach>
		</div>
		<div id="timer" class="timer float nointeract">
			<form action="#" method="get" id="timer_head" class="hidden">
				<input type="hidden" name="type" value="timer">
				<input type="hidden" name="id" value="${session.id}">
			</form>
			<div id="situation">
				<c:if test="${session.state==0}">
					<c:if test="${thisuser==null}">
						<c:if test="${session.cap>session.ucount}">
							<span id="join">
								加入
							</span>
						</c:if>
						<c:if test="${session.cap<=session.ucount}">
							<span>
								准备中
							</span>
						</c:if>
					</c:if>
					<c:if test="${thisuser!=null}">
						<c:if test="${thisuserpcl==null||thisuserpcl.size()<=0}">
							<span id="select">
								选择角色
							</span>
						</c:if>
						<c:if test="${thisuserpcl!=null&&thisuserpcl.size()>0}">
							<c:if test="${thisuserpcl.get(0).state<session.state&&thisuserpcl.get(0).state>-2}">
								<span id="confirminit">
									选择位置
								</span>
							</c:if>
							<c:if test="${thisuserpcl.get(0).state>=session.state||thisuserpcl.get(0).state<=-2}">
								<span>
									等待中
								</span>
							</c:if>
						</c:if>
					</c:if>
				</c:if>
				<c:if test="${session.state!=0}">
					<c:if test="${session.state>0}">
						<c:if test="${thisuser!=null}">
							<c:if test="${thisuserpcl.get(0).state>=session.state||thisuserpcl.get(0).state<=-2}">
								<span id="confirmed">
									第${session.state}回合
								</span>
							</c:if>
							<c:if test="${thisuserpcl.get(0).state<session.state&&thisuserpcl.get(0).state>-2}">
								<span id="confirm">
									第${session.state}回合
								</span>
							</c:if>
						</c:if>
						<c:if test="${thisuser==null}">
							<span>
								第${session.state}回合
							</span>
						</c:if>
					</c:if>
					<c:if test="${session.state==-1}">已结束</c:if>
					<c:if test="${session.state==-2}">已被终止</c:if>
					<c:if test="${session.state<=-1000}">Error</c:if>
				</c:if>
			</div>
			<script type="text/javascript">
				$('#join').click(function(){
					function fpl(){
						console.log("Updating PlayerList");
						return refreshDiv("playerlist","post","#playerlist_head","/Turn-Based_For_Honor/LoadSessionServlet",false);
					}
					function ftm(){
						console.log("Updating Timer");
						return refreshDiv("timer","get","#timer_head","/Turn-Based_For_Honor/LoadSessionServlet",false);
					}
					function updatetimer(){
			            var tasks = [fpl,ftm];
			            return tasks.reduce(function(prev,next){
			              return prev.then(next);
			            },$.Deferred().resolve());
			        }
					$.when(updatetimer()).done(function(){
			            console.log("Updated");
		        	})
				});
				$('#select').click(function(){
					$('#charactermenu').toggle();
					refreshDiv("charactermenu","get","#charactermenu_head","/Turn-Based_For_Honor/LoadSessionServlet",false);
				});
				$('#confirminit').hover(function(){
					$(this).html("确定");
				});
				$('#confirminit').mouseleave(function(){
					$(this).html("选择位置");
				});
				$('#confirm').hover(function(){
					$(this).html("确认");
				});
				$('#confirm').mouseleave(function(){
					$(this).html("第"+${session.state}+"回合");
				});
				$('#confirmed').hover(function(){
					$(this).html("等待中");
				});
				$('#confirmed').mouseleave(function(){
					$(this).html("第"+${session.state}+"回合");
				});
				$('.coord').click(function(){
					var sessionstate = ${session.state};
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
							alert($(form).serialize());
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
					            var tasks = [fbl,fbf,fpl,ftm];
					            return tasks.reduce(function(prev,next){
					              return prev.then(next);
					            },$.Deferred().resolve());
					        }
							$.when(updatebattlelog()).done(function(){
					            console.log("Updated");
				        	});
						});
					}
				});
				$('.pchara').click(function(){
					var isthis = $(this).attr("isthis");
					var cid = $(this).attr("cid");
					$(this).attr("id","charachosen");
					$('.pchara').not(this).attr("id","");
					if(isthis){
						switch(cid){
						case 10:
						case 11:
						case 12:
						}
					}
				});
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
			            var tasks = [fcm,fpl,fbf,ftm];
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
		</div>
	</div>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts.js"></script>
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
</html>