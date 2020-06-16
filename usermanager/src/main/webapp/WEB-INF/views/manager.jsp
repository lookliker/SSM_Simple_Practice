<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>欢迎</title>
</head>
<body>

	<a href="/insert?op=before"><h1>新增</h1></a>
	<table border="1" cellspacing="0">
		<tr>
			<th hidden="true">id</th>
			<th width="10%">昵称</th>
			<th width="5%">年龄</th>
			<th width="50%">头像</th>
			<th width="10%">操作</th>
		</tr>
		<c:forEach items="${users}" var="u">
			<tr>
				<td hidden="true">${u.id}</td>
				<td>${u.name}</td>
				<td>${u.age}</td>
				<td><img alt="error!" src="/images/${u.image}" width="150"><a
					href="/image?op=before&id=${u.id}">更改</a></td>
				<td><a href="/update?op=before&id=${u.id}">编辑</a>__<a
					href="/delete?id=${u.id}">删除</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>