<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

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
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script>
        $(function () {
            $('#item_type').val("${review.item_type}")
        });
	</script>
</head>

<body>

<jsp:include page="../include/header.jsp"/>

<main id="main" class="main">
	<section class="section col-6" style="margin: 0 auto">
		<div class="pagetitle">
			<h1>게시글 수정</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="reviewList">Review</a></li>
				</ol>
			</nav>
		</div><!-- End Page Title -->
		<div class="row">
			<div style="margin: 0 auto">
				<div class="card" style="padding: 25px 10px 0 10px;">
					<div class="card-body">
						<form method=post action="reviewUpdate" enctype="multipart/form-data">
							<input type="hidden" name="post_id" value="${review.post_id }">
							<input type="hidden" name="page" value="${page }">
							<input type="hidden" name="nick" id="nick" value="${sessionScope.nick}"/>
							<div class="row mb-3">
								<label for="item_type" class="col-sm-2 col-form-label">머릿말</label>
								<div class="col-sm-10">
									<select class="col-sm-10 form-select" id="item_type" name="item_type">
										<option value = ""></option>
										<option value = "top">Top</option>
										<option value = "bottom">Bottom</option>
										<option value = "outer">Outer</option>
										<option value = "shoes">Shoes</option>
										<option value = "acc">ACC</option>
									</select>
								</div>
							</div>
							<div class="row mb-3">
								<label for="title" class="col-sm-2 col-form-label">제목</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="title" name="title" value="${review.title}" required>
								</div>
							</div>
							<div class="row mb-3">
								<label for="content" class="col-sm-2 col-form-label">내용</label>
								<div class="col-sm-10">
									<textarea class="form-control" style="height: 300px" id="content" name="content" requireds>${review.content}</textarea>
								</div>
							</div>
							<div class="row mb-3">
								<label for="thumbnailImg" class="col-sm-2 col-form-label">미리보기</label>
								<div class="col-sm-10">
									<img src="upload/${review.review_file}" id="thumbnailImg" style="max-width: 200px">
								</div>
							</div>
							<div class="row mb-3">
								<label for="content" class="col-sm-2 col-form-label">대표 이미지</label>
								<div class="col-sm-10">
									<input type="file" class="form-control" id="uploadFile" name="uploadFile">
								</div>
							</div>
							<div class="row mb-3">
								<div class="col-sm-2">
								</div>
								<div class="col-sm-8">
									<button type="submit" class="btn btn-primary" style="width: 100%">수정완료</button>
								</div>
								<div class="col-sm-2">
									<button type="button" class="btn btn btn-outline-primary" style="width: 100%" onclick="history.go(-1)">취소</button>
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

</html>s