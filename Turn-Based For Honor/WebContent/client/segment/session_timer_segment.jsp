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
				<span class="header" id="join">
					加入
				</span>
			</c:if>
			<c:if test="${session.cap<=session.ucount}">
				<span class="header">
					准备中
				</span>
			</c:if>
		</c:if>
		<c:if test="${thisuser!=null}">
			<c:if test="${thisuserpcl==null||thisuserpcl.size()<=0}">
				<span class="header" id="select">
					选择角色
				</span>
			</c:if>
			<c:if test="${thisuserpcl!=null&&thisuserpcl.size()>0}">
				<c:if test="${thisuserpcl.get(0).state<session.state&&thisuserpcl.get(0).state>-2}">
					<span class="header" id="confirminit">
						选择位置
					</span>
				</c:if>
				<c:if test="${thisuserpcl.get(0).state>=session.state||thisuserpcl.get(0).state<=-2}">
					<span class="header">
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
					<span class="header" id="confirmed">
						第${session.state}回合
					</span>
				</c:if>
				<c:if test="${thisuserpcl.get(0).state<session.state&&thisuserpcl.get(0).state>-2}">
					<span class="header" id="confirm">
						第${session.state}回合
					</span>
				</c:if>
			</c:if>
			<c:if test="${thisuser==null}">
				<span class="header">
					第${session.state}回合
				</span>
			</c:if>
		</c:if>
		<c:if test="${session.state==-2}">
			<span class="header">
				已结束
			</span>
		</c:if>
		<c:if test="${session.state==-3}">
			<span class="header">
				已被终止
			</span>
		</c:if>
		<c:if test="${session.state<=-1000}">
			<span class="header">
				Error
			</span>
		</c:if>
	</c:if>
</div>
<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
<script type="text/javascript">
$(function(){
	$('#timer').css({"margin-left":$('#main',window.parent.document).width()/2-$('.header').width()/2+"px"});
	$(window).resize(function(){
		$('#timer').css({"margin-left":$('#main',window.parent.document).width()/2-$('.header').width()/2+"px"});
	});
	$('#join').click(function(){
		function fpl(){
			return refreshDiv("playerlist","post","#playerlist_head","/Turn-Based_For_Honor/LoadSessionServlet",false);
		}
		function fcf(){
			console.log("Updating Chatframe");
			return refreshDiv("chatframe","get","#chat_init_head","/Turn-Based_For_Honor/ChatServlet",true);
		}
		function ftm(){
			return refreshDiv("timer","get","#timer_head","/Turn-Based_For_Honor/LoadSessionServlet",false);
		}
		function updatetimer(){
	        var tasks = [fpl,fcf,ftm];
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
	$('#situation').hover(function(){
		$('#timer').css({"margin-left":$('#main',window.parent.document).width()/2-$(this).width()/2+"px"});
	});
	$('#situation').mouseleave(function(){
		$('#timer').css({"margin-left":$('#main',window.parent.document).width()/2-$(this).width()/2+"px"});
	});
	$('#confirm').click(function(){
		var sessionstate = ${session.state};
		if(sessionstate>0){
			<c:forEach items="${thisuserpcl}" var="pc">  
           　　			var pcid = ${pc.id}; 
           　　			function fbl(){
           　　				console.log("Updating BattleLog");
           　　				return refreshDiv("battlelog","post","#action"+pcid,"/Turn-Based_For_Honor/BattleLogServlet",true);	
           　　			}
           　　			function ftm(){
           　				console.log("Updating Timer");
           　				return refreshDiv("timer","get","#timer_head","/Turn-Based_For_Honor/LoadSessionServlet",false);
           　			}
           　       			function fpl(){
           　				console.log("Updating PlayerList");
           　				return refreshDiv("playerlist","get","#playerlist_head","/Turn-Based_For_Honor/LoadSessionServlet",false);
           　			}

           　			function fbf(){
           　				console.log("Updating BattleField");
           　				return refreshDiv("battlefield","get","#battlefield_head","/Turn-Based_For_Honor/LoadSessionServlet",false);
           　			}
           　			function updateeverything(){
           　	            var tasks = [fbl,ftm,fpl,fbf];
           　	            return tasks.reduce(function(prev,next){
           　	              return prev.then(next);
           　	            },$.Deferred().resolve());
           　	        }
           　			$.when(updateeverything()).done(function(){
           　	            console.log("Updated");
           　	    	});
           　　		</c:forEach>
       		
		}
	});
});

</script>

</html>