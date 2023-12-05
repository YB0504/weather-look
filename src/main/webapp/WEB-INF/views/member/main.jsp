<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

메인입니다~<br>

ID : ${sessionScope.id }<br>
Nickname : ${sessionScope.nick}<br>
프로필 : <img src="upload/${sessionScope.profile_image}">

</body>
</html>