<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method=post action="reviewInsert" enctype="multipart/form-data">
	<input type="hidden" name="nick" id="nick" value="${nick}">
	<table>
		<caption>쇼핑 후기 작성</caption>
		<tr><th>의상 분류</th>
			<td><select name = "item_type" id="item_type">		
				<option value = "">ItemType</option>
				<option value = "top">상의</option>
				<option value = "bottom">하의</option>
				<option value = "outer">아우터</option>
				<option value = "cap">모자</option>
			</select> </td>
		</tr>
<%-- 		<tr><th>작성자명</th>
			<td><input type=text name="nick" id="nick" value="${nick }" readonly></td>
		</tr> --%>
		<tr><th>제목</th>
			<td><input type=text name="title" id="title" required="required"></td>
		</tr>
		<tr><th>내용</th>
			<td><textarea cols=40 rows=5 name="content" id="content" required="required"></textarea></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<input type="file" id="review_file_form" name="review_file_form">
			</td>
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