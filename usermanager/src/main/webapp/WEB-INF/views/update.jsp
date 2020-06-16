<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改数据</title>
</head>
<body>
	<form action="/update?op=after" method="post">
		<input type="hidden" name="id" value="${user.id}"><br/>
		昵称：<input type="text" name="name" value="${user.name}"><br/>
		年龄：<input type="text" name="age" value="${user.age}"><br/>
		<input type="submit" value="提交">
	</form>	
</body>
</html>