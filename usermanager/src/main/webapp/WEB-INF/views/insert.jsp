<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增用户</title>
</head>
<body>
	<form action="/insert?op=after" method="post" enctype="multipart/form-data">
		昵称：<input type="text" name="name" required="required"><br/>
		年龄：<input type="text" name="age" required="required"><br/>
		上传你的头像：<input type="file" name="image" accept="image/*"><br/>
		<input type="submit" value="提交">
	</form>	
</body>
</html>