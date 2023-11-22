<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성</title>
</head>
<body>

<form method=post action="dailywrite">
<table border=1 width=400 align=center>
	<caption>글 작성</caption>
	
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
			<input type=reset value="취소"
>
		</td>
	</tr>
</table>
</form>

</body>
</html>
​