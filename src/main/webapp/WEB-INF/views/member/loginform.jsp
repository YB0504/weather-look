<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<link rel="stylesheet" href="css/member.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">

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
<div id="wrap">
	<header id="header">
		<h1 class="logo">
			<a href="index.html"><img src="images/logo.png" alt="logo"></a>
		</h1>
		<nav class="nav">
			<div class="menu">
				<ul class="main">
					<li>
						<a href="dailylookmain.html" style="text-decoration: none">데일리룩</a>
					</li>
					<li>
						<a href="review.html" style="text-decoration: none">쇼핑후기</a>
					</li>
					<li>
						<a href="community.html" style="text-decoration: none">커뮤니티</a>
					</li>
				</ul>
				<ul class="right">
					<li>
						<a href="mypage.html"><img src="images/user.png" alt="search"></a>
					</li>
					<li>
						<a href="#" onclick="showPopup();"><img src="images/search.png" alt="mypage"></a>
					</li>
				</ul>
			</div>
		</nav>
	</header>
<div class="container mt-4">
    <form action="main" method="post" onsubmit="return loginCheck()" class="border border-primary rounded p-4">
        <h4 class="text-center mb-4">로그인</h4>

        <div class="form-group row">
            <label for="id">ID</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="id" name="id">
            </div>
        </div>

        <div class="form-group row">
            <label for="passwd">비밀번호</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="passwd" name="passwd">
            </div>
        </div>

        <div class="form-group row">
            <div class="col-sm-10 offset-sm-2">
                <input type="submit" class="btn btn-primary" value="Login">
                <input type="reset" class="btn btn-primary" value="Cancel">
                <input type="button" class="btn btn-primary" value="Sign in" onclick="location.href='memberjoin'">
                <input type="button" class="btn btn-primary" value="ID 찾기">
                <input type="button" class="btn btn-primary" value="비밀번호 찾기">
            </div>
        </div>

        <div class="form-group row">
            <div class="col-sm-10 offset-sm-2">
                <%@ include file="../include/kakaoLogin.jsp" %>
            </div>
        </div>
    </form>
</div>
        <footer id="footer">
            <div class="foot">
                <ul class="f_m">
                    <li><a href="#">회사소개</a></li>
                    <li><a href="#">이용약관</a></li>
                    <li><a href="#">개인정보처리방침</a></li>
                    <li><a href="#">입점문의</a></li>
                    <li><a href="#">공지사항</a></li>
                </ul>
                <p>COPYRIGHT © <span>WeatherLook INC.</span> All RIGHTS RESERVED</p>
                <div class="call">
                    <p class="f_p"> <span><img src="images/icon.png" alt="img"> </span>1566-1566 평일 AM 9:00 - PM 6:00
                    점심 PM 1:00 - PM 2:00
                    주말 및 공휴일 휴무</p>
                </div><!-- call -->
            </div> <!-- f -->
        </footer><!-- footer -->
    </div><!-- wrap -->
</body>
</html>