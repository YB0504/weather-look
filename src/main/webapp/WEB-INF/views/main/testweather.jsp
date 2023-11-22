<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:forEach var="r" items="${mainlist }">
		<table border="1" align="center">
			<tr>
				<td>${r.type_name }게시판</td>
				<td>${r.title }</td>
			</tr>
			<tr>
				<td colspan="2" height="30">사진</td>
			</tr>
			<tr>
				<td>${r.latitude }</td>
				<td>${r.temperature }</td>
			</tr>
		</table>
		<br>
	</c:forEach>

</body>
</html>