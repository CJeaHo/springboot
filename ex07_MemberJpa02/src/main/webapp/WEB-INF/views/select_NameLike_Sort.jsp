<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>select</title>
</head>
<body>
	<h1>Member JPA #02 -selectByNameLikeSort</h1>
	
	<c:forEach items="${m }" var="members">
	아이디: ${members.id }<br>
	이름: ${members.name }<br>
	날짜: ${members.email }
	<hr>
	</c:forEach>
</body>
</html>