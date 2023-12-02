<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
</head>

<body>
	<table border=1 align="center" width=800>
		<caption>데일리 목록</caption>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>날짜</th>
			<th>조회수</th>
		</tr>

		<!-- 화면 출력 번호  변수 정의 -->
		<c:set var="num" value="${listCount-(page-1)*10}" />

		<!-- 반복문 시작 -->
		<c:forEach var="b" items="${dailyList}">
			<tr align="center">
				<td>
					${num }
					<!-- 번호 출력 부분 --> 
					<c:set var="num" value="${num-1}" />
				</td>
				<td>
					<!-- 제목 출력 부분 --> 
					<a href="dailycontent?post_id=${b.post_id}&page=${page}">
						${b.title} </a>
				</td>
				<td>${b.nick}</td>
				<td><fmt:formatDate value="${b.reg_date}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>${b.read_count}</td>
			</tr>

		</c:forEach>

	</table>
	<a href="dailyform">글쓰기</a>


	<!-- 페이지 처리 -->
	<center>
		<c:if test="${listCount > 0 }">

			<!-- 1 페이지로 이동 -->
			<a href="dailylist?page=1" style="text-decoration: none"> << </a>

			<!-- 이전 블럭으로 이동 -->
			<c:if test="${startPage > 10}">
				<a href="dailylist?page=${startPage - 10}">[이전]</a>
			</c:if>

			<!-- 각 블력에 10개의 페이지 출력 -->
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<c:if test="${i == page }">
					<!-- 현재 페이지 -->
      					[${i}]
			</c:if>
				<c:if test="${i != page }">
					<!-- 현재 페이지가 아닌경우 -->
					<a href="dailylist?page=${i}">[${i}]</a>
				</c:if>
			</c:forEach>

			<!-- 다음 블럭으로 이동 -->
			<c:if test="${endPage < pageCount}">
				<a href="dailylist?page=${startPage + 10}">[다음]</a>
			</c:if>

			<!-- 마지막 페이지로 이동 -->
			<a href="dailylist?page=${pageCount}" style="text-decoration: none">
				>> </a>

		</c:if>
	</center>

</body>
</html>