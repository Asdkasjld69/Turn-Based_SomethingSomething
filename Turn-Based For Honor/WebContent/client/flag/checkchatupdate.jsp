<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<br/>
pub:${pubnum}<br/>
<c:forEach items="${sess}" var="sessi"> 
	sess${sessi.key}:${sessi.value.size()}<br/>
</c:forEach>

</html>