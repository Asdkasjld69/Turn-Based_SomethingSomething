<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PROFILE</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts.js"></script>
</head>
<%
	if(request.getAttribute("user")==null){
		response.sendRedirect(this.getServletContext().getContextPath()+"/user/UNF.html");
	}
%>
<body onload="inheritOptions('${user.gender}','gender');">
	<div style="margin-left:35%;">
		<div style="float:left">
			<h1>${user.role}</h1>
			<img src="${pageContext.request.contextPath}${user.avatar}" style="width:128px;height:128px">
			<c:if test="${isself==1}">
				<form action="EditProfileServlet" method="POST" enctype="multipart/form-data">
					<input type="hidden" name="id" value="${user.id}">
					<input type="hidden" name="isself" value="${isself}">
					<input type="file" accept="image/bmp,image/png,image/gif,image/jpg" name="upload">
					<span>${imageMessage }</span>
					<table>
						<tr>
						<td>用户名:</td>
						<td>${user.username}</td>
						</tr>
						<tr>
						<td>性别:</td>
						<td>
							<select name="gender" id="gender">
								<option value="S">保密</option>
								<option value="M">男</option>
								<option value="F">女</option>
								<option value="T">变性人</option>
								<option value="B">双性人</option>
								<option value="N">无性人</option>
								<option value="D">性别认知障碍者</option>
							</select>
						</td>
						</tr>
						<tr><td>注册时间:</td><td>${user.registTime }</td></tr>
					</table>
					<input type="submit" name="submit" value="修改信息" />
				</form>
			</c:if>
			<c:if test="${isself!=1}">
				<table>
					<tr>
					<td>用户名:</td>
					<td>${user.username }</td>
					</tr>
					<tr>
					<td>性别:</td>
					<td>${user.gender }</td>
					</tr>
					<tr><td>注册时间:</td><td>${user.registTime }</td></tr>
				</table>
			</c:if>
		</div>
		<div style="float:left">
			<table>
				<tr><td>完成游戏次数:</td><td>${user.gamesfinished }</td></tr>
				<tr><td>经历回合数:</td><td>${user.turnsmoved }</td></tr>
				<tr><td>中途退出次数:</td><td>${user.gamesabandoned }</td></tr>
				<tr><td>击杀次数:</td><td>${user.kills }</td></tr>
				<tr><td>死亡次数:</td><td>${user.deaths }</td></tr>
				<tr><td>助攻次数:</td><td>${user.assists }</td></tr>
				<tr><td>胜利次数:</td><td>${user.win }</td></tr>
				<tr><td>战败次数:</td><td>${user.lose }</td></tr>
				<tr><td>平局次数:</td><td>${user.draw }</td></tr>
				<tr><td>好评:</td><td>${user.likes }</td></tr>
				<tr><td>差评:</td><td>${user.dislikes }</td></tr>
			</table>
		</div>
	</div>
</body>
</html>