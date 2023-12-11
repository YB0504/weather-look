<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
	<meta content="width=device-width, initial-scale=1.0" name="viewport">

	<title>WeatherLook</title>
	<meta content="" name="description">
	<meta content="" name="keywords">

	<!-- Favicons -->
	<link href="ico/weatherico.png" rel="icon">

	<!-- Google Fonts -->
	<link href="https://fonts.gstatic.com" rel="preconnect">
	<link
			href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
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
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="./assets/js/member.js"></script>
	<script>
        $(function () {
            $("#defaultImageButton").click(function () {
                changeDefaultImage();
            });

            $('#profile_image_form').change(function () {
                previewFile();
            });
        });

        function changeDefaultImage() {
            $("#profile_image").val("default.jpeg")
            $("#previewImg").attr("src", "upload/default.jpeg")
            $("#profile_image_form").val("")
        }

        function previewFile() {
            $("#profile_image").val("")
            var fileInput = $('#profile_image_form')[0];
            var previewImg = $('#previewImg')[0];

            var file = fileInput.files[0];
            var reader = new FileReader();

            reader.onloadend = function () {
                previewImg.src = reader.result;
            };

            if (file) {
                reader.readAsDataURL(file);
            } else {
                previewImg.src = 'upload/default.jpeg';
            }
        }
	</script>
</head>
<body>

<jsp:include page="../include/header.jsp"/>

<main id="main" class="main">

	<div class="pagetitle" style="max-width: 700px; margin: 0 auto; margin-bottom: 30px">
		<h1>회원 가입</h1>
	</div><!-- End Page Title -->

	<section class="section dashboard" style="max-width: 700px; margin: 0 auto">
		<div class="row">
			<form class="validation-form" action="memberinsert" method="post" enctype="multipart/form-data">
				<div class="row">
					<div class="col-md-12 mb-3">
						<label for="id">ID</label>
						<input type="text" class="form-control" style="border: 1px solid #ccc;" id="id" name="id"
						       required>
						<input type="button" class="btn btn-primary" value="ID중복검사" onclick="idcheck()">
						<div id="idcheck"></div>
					</div>
					<div class="col-md-12 mb-3">
						<label for="passwd">PW</label>
						<input type="password" class="form-control" style="border: 1px solid #ccc;" id="passwd"
						       name="passwd" required>
					</div>
					<div class="col-md-12 mb-3">
						<label for="id">Nick</label>
						<input type="text" class="form-control" style="border: 1px solid #ccc;" id="nick" name="nick"
						       required>
						<input type="button" class="btn btn-primary" value="별명중복검사" onclick="nickcheck()">
						<div id="nickcheck"></div>
					</div>
					<div class="col-md-12 mb-3">
						<label for="phone">Phone</label>
						<input type="text" class="form-control" style="border: 1px solid #ccc;" id="phone" name="phone"
						       required>
					</div>
					<div class="col-md-12 mb-3">
						<label for="address">Address</label>
						<select class="form-select" id="address" style="border: 1px solid #ccc;" name="address"
						        required>
							<%@ include file="../include/address.jsp" %>
							<option value="">지역선택</option>
							<c:forEach var="ad" items="${address}">
								<option value="${ad}">${ad}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-md-12 mb-3">
						<label for="previewImg">Profile Image</label><br>
						<center><img id="previewImg" src="upload/default.jpeg" width="200px" height="auto"></center>
					</div>
					<div class="col-md-12 mb-3">
						<input type="button" class="form-control btn btn-light" style="border: 1px solid #ccc;"
						       id="defaultImageButton" value="기본 이미지 선택">
					</div>
					<div class="col-md-12 mb-3">
						<input type="hidden" name="profile_image" id="profile_image">
						<input type="file" class="form-control" style="border: 1px solid #ccc;"
						       name="profile_image_form" id="profile_image_form">
					</div>
				</div>
				<hr class="mb-4">
				<div align="center">
					<button class="btn btn-primary btn-lg btn-block" type="submit">회원가입</button>
					<button class="btn btn-primary btn-lg btn-block" type="button" onclick="location.href='loginform'">
						뒤로 가기
					</button>
				</div>
			</form>
		</div>
	</section>
</main><!-- End #main -->

<jsp:include page="../include/footer.jsp"/>

</body>
</html>