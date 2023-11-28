<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정</title>
</head>
<body>

<form method=post action="commupdate">
<input type="hidden" name="post_id" value="${comm.post_id }">
<input type="hidden" name="page" value="${page }">
<table border=1 width=400 align=center>

	<tr><th>작성자명</th>
		<td><input type=text name="nick" readonly="readonly"
				value = "${comm.nick }"></td>
	</tr>
	<tr><th>머리말</th>
		<td><select name = "category" id = "category" >
		<c:if test="${comm.category == 'question' }">
		<option value = "question" >질문하기</option>
		<option value = "talk">수다</option>
		</c:if>
	<c:if test="${comm.category == 'talk' }">
		<option value = "talk">수다</option>
		<option value = "question" >질문하기</option>
	</c:if>
		</select> </td>
	</tr>
	<tr><th>제목</th>
		<td><input type=text name="title" required="required"
					value="${comm.title }"></td>
	</tr>
	<tr><th>내용</th>
		<td><textarea cols=40 rows=5 name="content" required="required">${comm.content}</textarea></td>
	</tr>
	<tr><td colspan=2 align=center>
			<input type=submit value="글 수정">
			<input type=button value="취소" 
onclick="location.href='commlist?page=${page}'"	>
		</td>
	</tr>
</table>
</form>

</body>
</html>