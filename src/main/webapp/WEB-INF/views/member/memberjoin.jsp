<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="./js/member.js"></script>
</head>
<body>

<form action="memberinsert" method="post" enctype="multipart/form-data" onSubmit="return check()">
<table border="1" width="400" align="center">
	<caption>회원 가입</caption>
	<tr>
		<th>ID</th>
		<td>
			<input type="text" name="id" id="id">
			<input type="button" value="ID중복검사" onclick="idcheck()">
			<div id="idcheck"></div>
		</td>
	</tr>
	<tr>
		<th>PASSWORD</th>
		<td>
			<input type="password" name="passwd" id="passwd">
		</td>
	</tr>
	<tr>
		<th>닉네임</th>
		<td>
			<input type="text" name="nick" id="nick">
			<input type="button" value="별명중복검사" onclick="nickcheck()">
			<div id="nickcheck"></div>
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
			<input type="text" name="phone" id="phone">
		</td>
	</tr>
	<tr>
		<th>주소</th>
		<td>
			<%@ include file="../memberinclude/address.jsp" %>
			<select name="address">
				<option value="">지역선택</option>
				<c:forEach var="ad" items="${address }">
					<option value="ad">${ad }</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="Sign in">
			<input type="reset" value="Cancel" onclick="$('#id').focus();">
		</td>
	</tr>
	
</table>
</form>

</body>
</html>