<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>你好，世界</title>
</head>
<body>
		<form action="/login" method="post">
			账号<input type="text" name="name" required="required"><br>
			密码<input type="password" name="password" required="required"><br>
			<input type="submit" value="登录">
		</form>
</body>
</html>