<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="main" method="post">
	<table border="1" width="600" align="center">
		<caption>로그인</caption>
		<tr>
			<th>ID</th>
			<td>
				<input type="text" id="id" name="id">
			</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>
				<input type="password" id="passwd" name="passwd">
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="Sign up">
				<input type="reset" value="Cancel">
				<input type="button" value="Sign in" onclick="location.href='memberjoin'">
			</td>
		</tr>
	</table>
</form>
</body>
</html>