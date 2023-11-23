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

<h2><a href = "apitest">api테스트</a></h2>
<h2><a href = "weather">이중dto테스트</a></h2>
<h2><a href = "report">신고글 관리페이지</a></h2>

<h2>지역 드롭박스, 기온 드롭박스</h2>

<h2>주간예보</h2>

	<table>

		<tr>
			<td>오늘</td>
			<td>오전 10도</td>
			<td>해</td>
			<td>오후 20도</td>
			<td>달</td>
			</tr>
		<tr>
			<td>내일</td>
			<td>오전 10도</td>
			<td>해</td>
			<td>오후 20도</td>
			<td>달</td>
		</tr>

	</table>


<h3><a href = "search">검색창으로 이동!</a></h3>



	<br>
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
				<td>${r.nick }</td>
				<td>${r.likes }</td>
			</tr>
		</table>
		<br>
	</c:forEach>
	
	
	
<div align = "center">
<c:if test="${page <=1 }">
				[이전]&nbsp;
			</c:if>
			
			<c:if test="${page > 1 }">
				<a href="main?page=${page-1}">[이전]</a>&nbsp;
			</c:if>			

			<c:forEach var="a" begin="${startpage}" end="${endpage}">
				<c:if test="${a == page }">
					[${a}]
				</c:if>
				<c:if test="${a != page }">
					<a href="main?page=${a}">[${a}]</a>&nbsp;
				</c:if>
			</c:forEach>			
			
			<c:if test="${page >= maxpage }">
				[다음] 
			</c:if>
			<c:if test="${page < maxpage }">
				<a href="main?page=${page+1}">[다음]</a>
			</c:if>			
</div>
</body>
</html>