<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.js"></script>
<link href="${pageContext.request.contextPath}/css/style.css" type="text/css" rel="stylesheet"/>
</head>
<form action="#" method="get" id="timer_head" class="hidden">
	<input type="hidden" name="type" value="timer">
	<input type="hidden" name="id" value="${session.id}">
</form>
<div id="situation" roundstate="${session.getState()}">
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
<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
<script type="text/javascript">
$('#join').click(function(){
	function fpl(){
		return refreshDiv("playerlist","post","#playerlist_head","/Turn-Based_For_Honor/LoadSessionServlet",false);
	}
	function ftm(){
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
	});
	$('.pchara').click(function(){
		var isthis = $(this).attr("isthis");
		var cid = $(this).attr("cid");
		$(this).attr("id","charachosen");
		$('.pchara').not(this).attr("id","");
		if(isthis=="true"){
			alert(isthis);
			switch(cid){
			case 10:
			case 11:
			case 12:
			}
		}
	});
</script>

</html>