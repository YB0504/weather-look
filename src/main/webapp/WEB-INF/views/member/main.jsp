<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

메인입니다~

ID : ${sessionScope.id }<br>
Nickname : ${sessionScope.nick}<br>
profile_image : ${sessionScope.profile_image}<br>
<img src="${member.profile_image}">

</body>
</html>