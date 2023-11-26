<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>웨더룩</title>
	<link rel="stylesheet" href="css/updateMember.css">
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="js/script.js"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="./js/member.js"></script>
	<script src="./js/updateMember.js"></script>
	<script>
        $(function () {
            $("#address").val("${member.address}")

            $("#defaultImageButton").click(function () {
                changeDefaultImage();
            });

            $('#profile_image_form').change(function () {
                previewFile();
            });
        });
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
						<a href="dailylookmain.html">데일리룩</a>
					</li>
					<li>
						<a href="review.html">쇼핑후기</a>
					</li>
					<li>
						<a href="community.html">커뮤니티</a>
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
	<div class="container">
		<div class="input-form-backgroud row">
			<div class="input-form col-md-12 mx-auto">
				<h4 class="mb-3">회원 정보 수정</h4>
				<form class="validation-form" action="updateMember" method="post" enctype="multipart/form-data" onSubmit="return check()">
					<input type="hidden" name="admin_role" value="${member.admin_role}">
					<div class="row">
						<div class="col-md-12 mb-3">
							<label for="id">ID</label>
							<input type="text" class="form-control" id="id" name="id" value="${member.id}">
						</div>
						<div class="col-md-12 mb-3">
							<label for="passwd">PW</label>
							<input type="password" class="form-control" id="passwd" name="passwd" value="">
						</div>
						<div class="col-md-12 mb-3">
							<label for="id">Nick</label>
							<input type="text" class="form-control" id="nick" name="nick" value="${member.nick}">
						</div>
						<div class="col-md-12 mb-3">
							<label for="phone">Phone</label>
							<input type="text" class="form-control" id="phone" name="phone" value="${member.phone}">
						</div>
						<div class="col-md-12 mb-3">
							<label for="address">Address</label>
							<select class="form-select" id="address" name="address">
								<%@ include file="../include/address.jsp" %>
								<option value="">지역선택</option>
								<c:forEach var="ad" items="${address}">
									<option value="${ad}">${ad}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-12 mb-3">
							<label for="previewImg">Profile Image</label><br>
							<c:if test="${empty member.profile_image}">
								<center><img id="previewImg" src="/upload/default.jpeg" width="200px" height="auto"></center>
							</c:if>
							<c:if test="${not empty member.profile_image}">
								<center><img id="previewImg" src="/upload/${member.profile_image}" width="200px" height="auto"></center>
							</c:if>
						</div>
						<div class="col-md-12 mb-3">
							<input type="button" class="form-control btn btn-light" id="defaultImageButton" value="기본 이미지 선택">
						</div>
						<div class="col-md-12 mb-3">
							<input type="hidden" name="profile_image" id="profile_image">
							<input type="file" class="form-control" name="profile_image_form" id="profile_image_form">
						</div>
					</div>
					<hr class="mb-4">
					<div align="center">
						<button class="btn btn-primary btn-lg btn-block" type="submit">수정 완료</button>
						<button class="btn btn-primary btn-lg btn-block" type="button" onclick="location.href='myPage'">뒤로 가기
						</button>
					</div>
				</form>
			</div>
		</div>
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
				<p class="f_p"><span><img src="images/icon.png" alt="img"> </span>1566-1566 평일 AM 9:00 - PM 6:00
					점심 PM 1:00 - PM 2:00
					주말 및 공휴일 휴무</p>
			</div><!-- call -->
		</div> <!-- f -->
	</footer><!-- footer -->
</div><!-- wrap -->
</body>
</html>