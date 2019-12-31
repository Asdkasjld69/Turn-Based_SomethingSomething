<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<%
	String target = (String)request.getAttribute("target");
	String self = (String)request.getAttribute("self");
	System.out.println(target);
%>
<script type="text/javascript">
	var surl = "<%=self %>";
	var url = "<%=target %>";
	window.location.href=surl;
	window.parent.location.href=url;
</script>
<body>

</body>
</html>