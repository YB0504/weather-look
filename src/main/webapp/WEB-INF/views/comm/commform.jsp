<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta content="width=device-width, initial-scale=1.0" name="viewport">

	<title>Forms / Elements - NiceAdmin Bootstrap Template</title>
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
</head>

<body>

<jsp:include page="../include/header.jsp"/>

<main id="main" class="main">
	<section class="section col-6" style="margin: 0 auto">
		<div class="pagetitle">
			<h1>게시글 입력</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="commlist">Community</a></li>
				</ol>
			</nav>
		</div><!-- End Page Title -->
		<div class="row">
			<div style="margin: 0 auto">
				<div class="card" style="padding: 25px 10px 0 10px;">
					<div class="card-body">
						<form method=post action="commwrite">
							<input type="hidden" id="nick" name="nick" value="${sessionScope.nick}">
							<!--데일리룩은 제외-->
							<div class="row mb-3">
								<label for="category" class="col-sm-2 col-form-label">머릿말</label>
								<div class="col-sm-10">
									<select class="col-sm-10 form-select" id="category" name="category">
										<option value=""></option>
										<option value="패션질문">패션질문</option>
										<option value="토크">토크</option>
									</select>
								</div>
							</div>
							<div class="row mb-3">
								<label for="title" class="col-sm-2 col-form-label">제목</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="title" name="title">
								</div>
							</div>
							<div class="row mb-3">
								<label for="content" class="col-sm-2 col-form-label">내용</label>
								<div class="col-sm-10">
									<textarea class="form-control" style="height: 300px" id="content" name="content"></textarea>
								</div>
							</div>
							<div class="row mb-3">
								<div class="col-sm-2">
								</div>
								<div class="col-sm-8">
									<button type="submit" class="btn btn-primary" style="width: 100%">작성완료</button>
								</div>
								<div class="col-sm-2">
									<button type="button" class="btn btn btn-outline-primary" style="width: 100%" onclick="location.href='commlist'">목록보기</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
</main><!-- End #main -->

<jsp:include page="../include/footer.jsp"/>

</body>

</html>