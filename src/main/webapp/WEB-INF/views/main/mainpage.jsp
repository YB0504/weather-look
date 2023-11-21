<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Weatherlook Main</title>
</head>
<body>

<h1>메인페이지 제작중</h1>

<h2>지역 드롭박스, 기온 드롭박스</h2>

<h2>주간예보</h2>

<h3><a href = "search">검색창으로 이동!</a></h3>



	<br>
	<c:forEach var="r" items="${recentlist }">
		<table border="1" align="center">
			<tr>
				<td>${r.type_name }게시판</td>
				<td>${r.title }</td>
			</tr>
			<tr>
				<td colspan="2" height="30">사진</td>
			</tr>
			<tr>
				<td>${r.nick }</td>
				<td>${r.likes }</td>
			</tr>
		</table>
		<br>
	</c:forEach>

</body>
</html>