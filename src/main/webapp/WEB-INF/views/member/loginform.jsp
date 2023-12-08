<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta content="width=device-width, initial-scale=1.0" name="viewport">

	<title>Login</title>
	<meta content="" name="description">
	<meta content="" name="keywords">

	<!-- Favicons -->
	<link href="assets/img/favicon.png" rel="icon">
	<link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

	<!-- Google Fonts -->
	<link href="https://fonts.gstatic.com" rel="preconnect">
	<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	      rel="stylesheet">

	<!-- Vendor CSS Files -->
	<link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
	<link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
	<link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
	<link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
	<link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
	<link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

	<!-- Template Main CSS File -->
	<link href="assets/css/style.css" rel="stylesheet">

	<!-- =======================================================
	* Template Name: NiceAdmin
	* Updated: Nov 17 2023 with Bootstrap v5.3.2
	* Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
	* Author: BootstrapMade.com
	* License: https://bootstrapmade.com/license/
	======================================================== -->
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>
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
				var email = response.kakao_account.email;
				
				console.log('이메일 :',email)
				location.href = "kakaologin?nickname="+nickname+"&email="+email;
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
​
<body>
​
<main>
	<div class="container">
​
		<section class="section register min-vh-100 d-flex flex-column align-items-center justify-content-center py-4">
			<div class="container">
				<div class="row justify-content-center">
					<div class="col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center">
						<div class="card mb-3">
							<div class="card-body">
								<div class="pt-4 pb-2">
									<h5 class="card-title text-center pb-0 fs-4">Login to Your Account</h5>
									<p class="text-center small">Enter your e-mail & password to login</p>
								</div>
​
								<form class="row g-3 needs-validation" action="login" novalidate>
​
									<div class="col-12">
										<label for="id" class="form-label">E-mail</label>
										<div class="input-group has-validation">
											<input type="text" name="id" class="form-control" id="id"
											       required>
											<div class="invalid-feedback">Please enter your e-mail!</div>
										</div>
									</div>
​
									<div class="col-12" style="margin-bottom: 20px">
										<label for="passwd" class="form-label">Password</label>
										<input type="password" name="passwd" class="form-control" id="passwd"
										       required>
										<div class="invalid-feedback">Please enter your password!</div>
									</div>
									<div class="col-12">
										<button class="btn btn-primary w-100" type="submit">로그인</button>
									</div>
									<div class="col-12">
										<a href="javascript:kakaoLogin()">
											<img src="assets/img/kakao_login.png">
										</a>
									</div>
									<div class="col-12" style="display: flex; justify-content: space-between">
										<p class="small mb-0"><a href="memberjoin">회원가입</a></p>
										<p class="small mb-0" style="text-align: right">
											<a href="idSearchForm">아이디 찾기</a>&nbsp;&nbsp;
											<a href="pwSearchForm">비밀번호 찾기</a>
										</p>
									</div>
​
								</form>
​
							</div>
						</div>
​
​
					</div>
				</div>
			</div>
​
		</section>
​
	</div>
</main><!-- End #main -->
​
<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>
​
<!-- Vendor JS Files -->
<script src="assets/vendor/apexcharts/apexcharts.min.js"></script>
<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="assets/vendor/chart.js/chart.umd.js"></script>
<script src="assets/vendor/echarts/echarts.min.js"></script>
<script src="assets/vendor/quill/quill.min.js"></script>
<script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
<script src="assets/vendor/tinymce/tinymce.min.js"></script>
<script src="assets/vendor/php-email-form/validate.js"></script>
​
<!-- Template Main JS File -->
<script src="assets/js/main.js"></script>
​
</body>
​
</html>