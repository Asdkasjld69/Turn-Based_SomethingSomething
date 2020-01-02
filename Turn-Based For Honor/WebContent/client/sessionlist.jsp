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

<body>
	<div class="window">
		<form action="#" id="check_head" method="GET" class="hidden">
			<input type="hidden" name="type" value="${currentuser.role}">
			<input type="hidden" name="check" value="1">
		</form>
		<form action="#" id="search" method="GET" class="left nointeract">
			<input type="hidden" name="type" value="${currentuser.role}">
			<input type="hidden" name="currentusername" value="${currentuser.username}">
			<table>
				<c:if test="${currentuser.role.equals('SUPER')}">
					<tr>
						<td>房间id</td>
						<td><input type="text" name="search_id" value=""></td>
						<td>地图id</td>
						<td>
							<input type="text" name="search_map_id" value="">
						</td>
						<td>用户</td>
						<td>
							<input type="text" name="search_username">
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
					<td colspan="3"><input type="range" id="search_cap" name="search_cap" min="1" max="4" step="1" value="1"><span id="capnum">任意</span></td>
					<c:if test="${currentuser.role!='SUPER'}">
						<td>我的</td>
						<td>
							<select name="search_username">
								<option value="" selected="selected">任意</option>
								<option value="N">否</option>
								<option value="Y">是</option>
							</select>
						</td>
					</c:if>
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
				<c:if test="${currentuser.role=='SUPER'}">
					<td width="5%">
						操作
					</td>
				</c:if>
			</tr>
		</table>
		<div id="sesslist" style="width:100%;height:500px;overflow-y:scroll">
		</div>
	</div>
	<iframe id="createframe" class="createframe subwindow float hidden" src="${pageContext.servletContext.contextPath}/client/subwindow/createmenu.jsp" scrolling="no"></iframe>
	<div id="prompt" class="subwindow float hidden" style="width:256px;height:48px;padding:8px;margin-left:50%;margin-top:20%;color:#FFFFFF">
		<span id="promptmessage">请输入密码:</span>
		<input id="spass" type="password"><br/>
		<button class="cancel" target="#prompt">取消</button>
	</div>
	<script type="text/javascript">
	$('#return', window.parent.document).hide(100);
	$(function(){
		refreshDiv("sesslist","get","#search","/Turn-Based_For_Honor/ListSessionServlet",${false});
		$('#main', window.parent.document).attr("scrolling","no");
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
		$('.cancel').click(function(){
			var target = $(this).attr("target");
			$('#promptmessage').css({"color":"#FFFFFF"});
			$(target).hide();
		});
	});	
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
    var sessnum = 0;
	function checkUpdate(type,form,target)
	{
	  var xmlhttp;
	  if (window.XMLHttpRequest)
	  {
	    // IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
	    xmlhttp=new XMLHttpRequest();
	  }
	  else
	  {
	    // IE6, IE5 浏览器执行代码
	    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	  xmlhttp.onreadystatechange=function()
	  {
	    if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
	    	var r = xmlhttp.responseText;
	    	var htmlBlock = r.split("<br/>");
	    	var reg = /sessnum:[0-9]*/i;
	    	var flag = false;
	    	for (var i in htmlBlock){
	    		var blocks;
	    		if (blocks = htmlBlock[i].match(reg)){
	    			r = blocks[0].replace("sessnum:","");
	    			flag = true;
	    		}
	    	}
	    	if(flag == false){
	    		r = 0;
	    	}
	    	if(r!=sessnum){
	    		console.log(r);
	    		refreshDiv("sesslist","get","#search","/Turn-Based_For_Honor/ListSessionServlet",false);
	    		sessnum = r;
	    		return true; 
	    	}
	    	else{
	    		return false;
	    	}
	    }
	  }
	  target = target+"?t="+new Date().getTime();
	  if(form!=""){
		  target = target+"&"+$(form).serialize();
	  }
	  xmlhttp.open(type,target,true);
	  xmlhttp.send();
	}
	function checkSession(){
		checkUpdate("get","#check_head","/Turn-Based_For_Honor/ListSessionServlet");
	}
	setInterval("checkSession()",500);
	</script>
</body>
</html>