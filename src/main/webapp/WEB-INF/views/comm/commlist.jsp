<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!doctype html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>게시판 목록</title>
</head>

<body>
		글 개수 : ${listcount} <br>
		<a href="commform">글작성</a>
		<table border=1 align="center" width=800>
			<caption>게시판 목록</caption>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>날짜</th>
				<th>조회수</th>
			</tr>

			<!-- 화면 출력 번호  변수 정의 -->		
			<c:set var="num" value="${listcount-(page-1)*10}"/> 	
	
			<!-- 반복문 시작 -->
			<c:forEach var="b" items="${boardlist}">			
			<tr align="center">
				<td>					
 					<!-- 번호 출력 부분 -->	
 					<c:out value="${num}"/>			
					<c:set var="num" value="${num-1}"/>	 
				</td>				
				<td>
					<!-- 제목 출력 부분 -->	
					<a href="boardcontent.do?no=${b.no}&page=${page}">
							${b.subject}
					</a>
				</td>
				<td>${b.writer}</td>
				<td>					
					<fmt:formatDate value="${b.register}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>${b.readcount}</td>
			</tr>
			
			</c:forEach>
					
		</table>

		
<!-- 페이지 처리 -->
<center>
<c:if test="${listcount > 0 }">

<!-- 1 페이지로 이동 -->
<a href="boardlist.do?page=1" style="text-decoration:none"> << </a>

<!-- 이전 블럭으로 이동 -->
<c:if test="${startPage > 10}">
	<a href="boardlist.do?page=${startPage - 10}">[이전]</a>
</c:if>

<!-- 각 블력에 10개의 페이지 출력 -->
<c:forEach var="i" begin="${startPage}" end="${endPage}">
	<c:if test="${i == page }">		<!-- 현재 페이지 -->
		[${i}]
	</c:if>
	<c:if test="${i != page }">		<!-- 현재 페이지가 아닌경우 -->
		<a href="boardlist.do?page=${i}">[${i}]</a>
	</c:if>
</c:forEach>

<!-- 다음 블럭으로 이동 -->
<c:if test="${endPage < pageCount}">
	<a href="boardlist.do?page=${startPage + 10}">[다음]</a>
</c:if>

<!-- 마지막 페이지로 이동 -->
<a href="boardlist.do?page=${pageCount}" style="text-decoration:none"> >> </a>

</c:if> 
</center>
	
</body>
</html>