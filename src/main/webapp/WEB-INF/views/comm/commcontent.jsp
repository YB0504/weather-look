<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 페이지</title>
</head>
<body>

<table border=1 width=400 align="center">
	<caption>상세 페이지</caption>
	<tr>
		<td>작성자</td>
		<td>${comm.nick }</td>
	</tr>
	<tr>
		<td>날짜</td>
		<td>
			<fmt:formatDate value="${comm.reg_date}"
				pattern="yyyy-MM-dd HH:mm:ss"/>
		</td>
	</tr>
	<tr>
		<td>조회수</td>
		<td>${comm.read_count }</td>
	</tr>
	<tr>
		<td>제목</td>
		<td>${comm.title }</td>
	</tr>
	<tr>
		<td>내용</td>
		<td>
			<pre>${comm.content }</pre>
		</td>
	</tr>
	<tr>
		<td colspan=2 align=center>
			<input type="button" value="목록"
onClick="location.href='commlist?page=${page}'"	>
 
			<input type="button" value="수정"
onClick="location.href='commupdateform?post_id=${comm.post_id}&page=${page}'">
			 
			<input type="button" value="삭제"
onclick="location.href='commdelete?post_id=${comm.post_id}&page=${page }'"			> 
		</td>
	</tr>
</table>

</body>
</html>