<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="loginform" method="post" enctype="multipart/form-data">
<table border="1" width="400" align="center">
	<caption>회원 가입</caption>
	<tr>
		<th>ID</th>
		<td>
			<input type="text" name="id">
			<input type="button" name="idcheck" value="ID중복검사">
		</td>
	</tr>
	<tr>
		<th>PASSWORD</th>
		<td>
			<input type="text" name="passwd">
		</td>
	</tr>
	<tr>
		<th>닉네임</th>
		<td>
			<input type="text" name="nick">
			<input type="button" name="nickcheck" value="별명중복검사">
		</td>
	</tr>
	<tr>
		<th>PASSWORD</th>
		<td>
			<input type="text" name="passwd">
		</td>
	</tr>
	<tr>
		<th>프로필 사진</th>
		<td>
			<input type="file" name="profile_image">
		</td>
	</tr>
	<tr>
		<th>전화번호</th>
		<td>
			<input type="text" name="phone">
		</td>
	</tr>
	<tr>
		<th>주소</th>
		<td>
			<select name="address">
				<c:forEach var="ad" items="${address }">
					<option value="ad">${ad }</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	
</table>
</form>

</body>
</html>