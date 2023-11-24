<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Page</title>


</head>
<body>

<h1>통합검색 페이지</h1>
<table align = "center">
<tr>
<th>검색어</th>
<td><input type = "text" name = "keyword" id = "keyword" value = "${keyword }"></td>
<td><input type="button" name="search" id="search" value="검색" 
onClick="location.href='search?page=1&keyword='+document.getElementById('keyword').value"
></td>
</tr>
</table>

<!-- 키워드가 있으면 검색결과 갯수 표시하는 줄 출력하기 -->

<table border = "1" align = "center">

<tr>
<th>게시판</th>
<th>제목</th>
<th>닉네임</th>
<th>조회수</th>
<th>작성일자</th>
<th>좋아요</th>
</tr>



<c:forEach var = "r" items = "${searchresult }">
<tr>
<td>${r.type_name }</td>
<td>
<a href = main>
${r.title }
</a>
</td>
<td>${r.nick }</td>
<td>${r.read_count }</td>
<td>${r.reg_date }</td>
<td>${r.likes }</td>
</tr>
</c:forEach>

</table>
<div align = "center">
<c:if test="${page <=1 }">
				[이전]&nbsp;
			</c:if>
			
			<c:if test="${page > 1 }">
				<a href="search?page=${page-1}&keyword=${keyword}">[이전]</a>&nbsp;
			</c:if>			

			<c:forEach var="a" begin="${startpage}" end="${endpage}">
				<c:if test="${a == page }">
					[${a}]
				</c:if>
				<c:if test="${a != page }">
					<a href="search?page=${a}&keyword=${keyword}">[${a}]</a>&nbsp;
				</c:if>
			</c:forEach>			
			
			<c:if test="${page >= maxpage }">
				[다음] 
			</c:if>
			<c:if test="${page < maxpage }">
				<a href="search?page=${page+1}&keyword=${keyword}">[다음]</a>
			</c:if>			
</div>
</body>
</html>