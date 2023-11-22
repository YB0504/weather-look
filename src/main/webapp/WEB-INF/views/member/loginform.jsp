<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	function loginCheck() {
		if($.trim($("#id").val())==""){
			alert("ID를 입력하세요");
			$("#id").val("").focus();
			return false;
		}
		if($.trim($("#passwd").val())==""){
			alert("비밀번호를 입력하세요");
			$("#passwd").val("").focus();
			return false;
		}
		
	}
</script>

</head>
<body>

<form action="main" method="post" onsubmit="return loginCheck()">
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
				<input type="submit" value="Login">
				<input type="reset" value="Cancel">
				<input type="button" value="Sign in" onclick="location.href='memberjoin'">
				<input type="button" value="ID 찾기">
				<input type="button" value="비밀번호 찾기">
			</td>
		</tr>
	</table>
</form>
</body>
</html>