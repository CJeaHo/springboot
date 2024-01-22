<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>menu</title>
</head>
<body>
	<h1>Member JPA #01</h1>
	<a href="/insert">데이터 추가</a><p/><br>
	<a href="/selectAll">전체 조회</a><p/><br>
	
	<a href="/selectById?id=1">개별 조회 - byId</a><p/><br>
	<a href="/selectByEmail?email=test1@test">개별 조회 - byEmail</a><p/><br>
	<a href="/selectByName?name=이범신">개별 조회 - byName</a><p/><br>
	<a href="/selectByNameLike?name=순">개별 조회 - Name Like</a><p/><br>
	<a href="/selectByNameLikeDesc?name=순">개별 조회 - Name Like Desc</a><p/><br>
	<a href="/selectByNameLikeSort?name=순">개별 조회 - Name Like Sort</a><p/><br>
	
</body>
</html>