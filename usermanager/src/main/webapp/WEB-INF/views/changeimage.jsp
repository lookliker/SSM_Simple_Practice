<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更换头像</title>
</head>
<body>
	当前头像：<img alt="error" src="/images/${image}">
	<form action="/image?op=after&id=${id}" method="post" enctype="multipart/form-data">
		更换头像：<input type="file" name="newImage" accept="image/*" required="required"><br>
		<input type="submit" value = "确认">
	</form>
</body>
</html>