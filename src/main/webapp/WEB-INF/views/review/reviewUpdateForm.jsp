<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method=post action="reviewUpdate" enctype="multipart/form-data">
	<input type="hidden" name="post_id" value="${review.post_id }">
	<input type="hidden" name="page" value="${page }">
	<input type="hidden" name="nick" id="nick" value="${sessionScope.nick}"/>
	<table border=1 width=400 align=center>
		<tr>
			<th>작성자명</th>
			<td><input type=text name="nick" readonly="readonly"
			           value="${sessionScope.nick }">
			</td>
		</tr>

		<tr>
			<th>제목</th>
			<td><input type=text name="title" required="required"
			           value="${review.title }"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea cols=40 rows=5 name="content" required="required">${review.content}</textarea></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<c:if test="${empty review.review_file}">
					<img src="#" id="thumbnailImg" alt="" style="max-width: 200px">
				</c:if>
				<c:if test="${not empty review.review_file}">
					<img src="upload/${review.review_file}" id="thumbnailImg" alt="" style="max-width: 200px">
				</c:if>
				<input type="file" id="uploadFile" name="uploadFile">
			</td>
		</tr>
		<tr>
			<td colspan=2 align=center>
				<input type=submit value="글 수정">
				<input type=button value="취소" onclick="history.go(-1)">
			</td>
		</tr>
	</table>
</form>

</body>
</body>
</html>