<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>select_name_jsql3</title>
</head>
<body>
	
	<h1>jsql_nativeQuery - @query: name like</h1>
	
	총 글의 갯수: ${totalElements }<br>
	총 페이지: ${totalPages }<br>
	페이지당 글의 수: ${size }<br>
	현재 페이지: ${pageNumber }<br>
	현재 페이지의 글의 수: ${numberOfElements }<br>
	<hr>
	
	<c:forEach items="${contents }" var="members">
	아이디: ${members.id }<br>
	이름: ${members.name }<br>
	이메일: ${members.email }
	<hr>
	</c:forEach>
	
</body>
</html>