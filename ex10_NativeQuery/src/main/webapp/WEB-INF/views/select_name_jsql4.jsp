<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>select_name_jsql4</title>
</head>
<body>
	
	<h1>jsql_nativeQuery - @query: name like</h1>
	
	<c:forEach items="${m3 }" var="members">
	아이디: ${members.id }<br>
	이름: ${members.name }<br>
	이메일: ${members.email }
	<hr>
	</c:forEach>
	
</body>
</html>