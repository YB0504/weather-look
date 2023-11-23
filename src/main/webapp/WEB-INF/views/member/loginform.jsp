<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
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
	
/* 	window.Kakao.init("832303dbd469e2e260ff6e85306d6fd8");
	
	function kakaoLogin() {
		window.Kakao.Auth.login({
			scope:'profile_nickname, profile_image',
			success: function (authObj) {
				console.log(authObj);
				window.Kakao.API.request({
					url:'/v2/user/me',
					success:res => {
						const kakao_account = res.kakao_account;
						console.log(kakao_account)
					}
				});
			}
		});
	}
	
	function kakaoLoginPro(response){
		var data = {id:response.id,email:response.kakao_account.email}
		$.ajax({
			type : 'POST',
			url : '/user/kakaoLoginPro.do',
			data : data,
			dataType : 'json',
			success : function(data){
				console.log(data)
				if(data.JavaData == "YES"){
					alert("로그인되었습니다.");
					location.href = 'member/main'
				}else if(data.JavaData == "register"){
					$("#kakaoEmail").val(response.kakao_account.email);
					$("#kakaoId").val(response.id);
					$("#kakaoForm").submit();
				}else{
					alert("로그인에 실패했습니다");
				}
				
			},
			error: function(xhr, status, error){
				alert("로그인에 실패했습니다."+error);
			}
		});
	 */
</script>

</head>
<body>

	<form action="main" method="post" onsubmit="return loginCheck()">
		<table border="1" width="600" align="center">
			<caption>로그인</caption>
			<tr>
				<th>ID</th>
				<td><input type="text" id="id" name="id"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" id="passwd" name="passwd"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Login"> 
					<input type="reset" value="Cancel">
					<input type="button" value="Sign in" onclick="location.href='memberjoin'"> 
					<input type="button" value="ID 찾기"> 
					<input type="button" value="비밀번호 찾기">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<a href="javascript:kakaoLogin()"> 
					<img src="./img/kakao_login_medium_narrow.png">
				</a>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>