<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
	.back{  
		position:absolute;
		z_index:1;
	}  
	.front{  
		position:absolute;
		z_index:2;
	}  
	.main{  
		position:absolute;
		z_index:3;
	}  
	.field{   
		background:#825313;
	}  
	.grid{
		border-collapse:collapse;  
		border-spacing:0;  
	}
	.blocks{  
		padding:0;  
	}  
</style>
<title>${map.name}</title>
</head>
<body>
	<div class="back">
		<table class="grid field">
			<c:forEach items="${map.map}" var="row" >
				<tr>
					<c:forEach items="${row}" var="col">
						<td class="blocks">
							<img src="img/terrian/land_<%=(int)Math.floor(Math.random()*3)%>.gif">
						</td>
					</c:forEach>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div class="front">
		<table class="grid">
			<c:forEach items="${map.map}" var="row" >
				<tr>
					<c:forEach items="${row}" var="col">
						<td class="blocks">
							<c:if test="${col==1}">
								<img src="img/terrian/obstacle.gif">
							</c:if>
							<c:if test="${col==2}">
								<img src="img/terrian/fire.gif">
							</c:if>
							<c:if test="${col==3}">
								<img src="img/terrian/trap.gif">
							</c:if>
							<c:if test="${col==4}">
								<img src="img/terrian/pit.gif">
							</c:if>
						</td>
					</c:forEach>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div class="main" style="width:${map.width*64}px;height:${map.height*64}px">
	
	</div>
</body>
</html>