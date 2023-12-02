<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정</title>
</head>
<body>

	<form method=post action="dailyupdate">
		<input type="hidden" name="post_id" value="${daily.post_id }">
		<input type="hidden" name="page" value="${page }">
		<table border=1 width=400 align=center>
			<tr>
				<th>작성자명</th> 
				<td><input type=text name="nick" readonly="readonly"
					value="${daily.nick }">
				</td>
			</tr>

			<tr>
				<th>제목</th>
				<td><input type=text name="title" required="required"
					value="${daily.title }"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea cols=40 rows=5 name="content" required="required">${daily.content}</textarea></td>
			</tr>
			<tr>
				<td colspan=2 align=center><input type=submit value="글 수정">
					<input type=button value="취소" onclick="history.go(-1)"></td>
			</tr>
		</table>
	</form>

</body>
</html>