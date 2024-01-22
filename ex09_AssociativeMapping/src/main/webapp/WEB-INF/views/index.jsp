<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
</head>
<body>
	<form action="minsert" method="post">
		아디: <input name="id"><p/>
		비번: <input type="password" name="pw"><p/>
		이름: <input name="name"><p/>
		<button>회원가입</button> <!-- 아무것도 안넣으면 submit -->
	</form>
	
	<form action="binsert" method="post">
		제목: <input name="title"><p/>
		내용: <input name="content"><p/>
		<button>게시글 등록</button>
	</form>
</body>
</html>