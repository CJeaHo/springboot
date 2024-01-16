<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>loginForm</title>
</head>
<body>
	<h1>login</h1>
	
	<form action="login_check" method="post">
		id: <input name="username"><br/>
		pw: <input type="password" name="password"><br/>
		<input type="submit" value="로그인">
		
	</form>
</body>
</html>