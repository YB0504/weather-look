<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성</title>
</head>
<body>

<form method=post action="commwrite">
<table border=1 width=400 align=center>
	<caption>글 작성</caption>
	<tr><th>머릿말</th>
		<td><select name = "category">		
		<option value = "">카테고리 선택</option>
		<option value = "question">질문하기</option>
		<option value = "talk">토크</option>
		</select> </td>
	</tr>
	<tr><th>작성자명</th>
		<td><input type=text name="nick" required="required"></td>
	</tr>
	<tr><th>제목</th>
		<td><input type=text name="title" required="required"></td>
	</tr>
	<tr><th>내용</th>
		<td><textarea cols=40 rows=5 name="content" required="required"></textarea></td>
	</tr>
	<tr><td colspan=2 align=center>
			<input type=submit value="글 작성">
			<input type=reset value="취소">
		</td>
	</tr>
</table>
</form>

</body>
</html>
​