<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reports Board</title>
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script src="/js/reported.js"></script>
</head>
<body>

<h1 align = "center">신고된 게시글</h1>

<br>

<table border = "1" align = "center">

<tr>
<th>신고일자</th>
<th>게시판</th>
<th>제목</th>
<th>신고사유</th>
<th>조치</th>
</tr>

<c:forEach var = "r" items = "${reportlist }">
<tr>
<td>${r.report_date }</td>
<td>${r.type_name }</td>
<td>${r.title}</td>
<td>${r.reason }</td>
<td><input type = "button" id = "delete" name = "delete" value = "삭제"
onClick = "remove('${r.type_name}', '${r.post_id}', '${page}')"/></td>
</tr>
</c:forEach>

</table>
<div align = "center">
<c:if test="${page <=1 }">
				[이전]&nbsp;
			</c:if>
			
			<c:if test="${page > 1 }">
				<a href="report?page=${page-1}">[이전]</a>&nbsp;
			</c:if>			

			<c:forEach var="a" begin="${startpage}" end="${endpage}">
				<c:if test="${a == page }">
					[${a}]
				</c:if>
				<c:if test="${a != page }">
					<a href="report?page=${a}">[${a}]</a>&nbsp;
				</c:if>
			</c:forEach>			
			
			<c:if test="${page >= maxpage }">
				[다음] 
			</c:if>
			<c:if test="${page < maxpage }">
				<a href="report?page=${page+1}">[다음]</a>
			</c:if>			
</div>


</body>
</html>