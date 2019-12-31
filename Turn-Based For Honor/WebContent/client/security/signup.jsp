<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SIGN ON</title>
</head>
<body>
	<div>
		<c:url var="signon" value="/SignUpServlet">
		</c:url>
		<form action="${signon}" method="GET">
			<table>
				<tr>
					<td>用户名：</td><td><input type="text" name="username"></td><td>${usernameMessage}</td>
				</tr>
				<tr>
					<td colspan = "2">性别：</td>
				</tr>
				<tr>
					<td colspan = "2">
						<input type="radio" name="gender" value="S" checked/>保密
						<input type="radio" name="gender" value="M" />男
						<input type="radio" name="gender" value="F" />女
						<input type="radio" name="gender" value="T" />变性人
						<input type="radio" name="gender" value="B" />双性人
						<input type="radio" name="gender" value="N" />无性人
						<input type="radio" name="gender" value="D" />性别认知障碍者
					</td>
				</tr>
				<tr>
					<td>密码：</td><td><input type="password" name="password"></td><td>${passwordMessage}</td>
				</tr>
				<tr>
					<td>重复密码：</td><td><input type="password" name="password2"></td><td>${password2Message}</td>
				</tr>
				<tr>
					<td>电子邮箱：</td><td><input type="text" name="email"></td><td>${emailMessage}</td>
				</tr>
				<tr>
					<td><input type="submit" name="signin" value="注册"></td><td><input type="reset" name="reset" value="重来"></td>
				</tr>
			</table>
		</form>
		<span>${message}</span>
	</div>
</body>
</html>