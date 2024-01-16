<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<title>writeForm</title>
</head>
<body>
	<h4>상세보기</h4>
	<hr>
	제목: ${detailBoard.title }<br>
	작성자: ${detailBoard.writer }<br>
	내용: ${detailBoard.content }<br>
	<button type="button" class="btn btn-light" onclick="location.href='list'">목록보기</button>
</body>
</html>