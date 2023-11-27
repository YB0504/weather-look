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
	
</script>
<script>
Kakao.init('832303dbd469e2e260ff6e85306d6fd8'); //발급받은 키 중 javascript키를 사용해준다.
console.log(Kakao.isInitialized()); // sdk초기화여부판단
//카카오로그인
function kakaoLogin() {
    Kakao.Auth.login({
      success: function (response) {
        Kakao.API.request({
          url: '/v2/user/me',
          success: function (response) {
        	  console.log(response)
        	  	// 로그인 성공 후 정보에서 닉네임 추출
        		var nickname = response.properties.nickname;
				var profileImage = response.properties.profile_image;
				
				console.log(profileImage);
				location.href = "kakaologin?nickname=" + nickname + "&profileImage=" + profileImage;
          },
          fail: function (error) {
            console.log(error)
          },
        })
      },
      fail: function (error) {
        console.log(error)
      },
    })
  }
//카카오로그아웃  
function kakaoLogout() {
    if (Kakao.Auth.getAccessToken()) {
      Kakao.API.request({
        url: '/v1/user/unlink',
        success: function (response) {
        	console.log(response)
        },
        fail: function (error) {
          console.log(error)
        },
      })
      Kakao.Auth.setAccessToken(undefined)
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
					<%@ include file="../include/kakaoLogin.jsp" %>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>