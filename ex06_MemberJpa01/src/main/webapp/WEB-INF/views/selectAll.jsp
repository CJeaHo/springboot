<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>selectAll</title>
</head>
<body>
	<h1>Member JPA #01 -selectAll</h1>
	
	<c:forEach items="${meAll }" var="members">
	아이디: ${members.id }<br>
	이름: ${members.username }<br>
	날짜: ${members.createDate }
	<hr>
	</c:forEach>
</body>
</html>