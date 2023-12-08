<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta content="width=device-width, initial-scale=1.0" name="viewport">

	<title>Components / Cards - NiceAdmin Bootstrap Template</title>
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
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script>
        $(function () {
            $('#slist').load('crlist?post_id=${daily.post_id}')

            $('#heart').click(function () {
                var heart = confirm("추천 하시겠습니까?");
                if(heart){
                    location.href='dailyLikeInsert?post_id=${daily.post_id}&nick=${sessionScope.nick}&page=${page}'
                }
            });

            $('#heart-fill').click(function () {
                var heartfill = confirm("추천을 취소하시겠습니까?");
                if(heartfill){
                    location.href='dailyLikeDelete?post_id=${daily.post_id}&page=${page}&like_id=${like.like_id}'
                }
            });

            $('#scrap').click(function () {
                var scrap = confirm("스크랩 하시겠습니까?");
                if(scrap){
                    location.href='dailyScrapInsert?post_id=${daily.post_id}&nick=${sessionScope.nick}&page=${page}'
                }
            });

            $('#scrap-fill').click(function () {
                var scrapfill = confirm("스크랩을 취소하시겠습니까?");
                if(scrapfill){
                    location.href='dailyScrapDelete?post_id=${daily.post_id}&page=${page}&scrap_id=${scrap.scrap_id}'
                }
            });

            $('#slist').load('rdlist?post_id=${daily.post_id}')

            $('#repInsert').click(function () {
                if (!replyForm.re_content.value) {
                    alert('댓글을 입력 하세요.');
                    replyForm.re_content.focus();
                    return false;
                }
                var frmData = $('form').serialize();

                $.post('rdInsert', frmData, function (data) {
                    $('#slist').html(data);
                    replyForm.re_content.value = '';
                });
            });
        });

        function delcheck() {
            var result = confirm("정말 삭제하시겠습니까?");
            if (result) {
                location.href = 'dailydelete?post_id=${daily.post_id}&page=${page}'

                return false;
            }
        }

        // 신고 팝업 열기
        function openReportPopup(postid) {
            window.open('dailyReport?post_id=' + postid, '신고하기', 'width=450,height=500');
        }
	</script>
</head>

<body>

<jsp:include page="../include/header.jsp"/>

<main id="main" class="main">
	<section class="section container">
		<div class="row align-items-top col-8" style="margin: 0 auto">
			<div class="pagetitle">
				<h1><a href="dailylist">DailyLook</a></h1>
				<nav>
					<ol class="breadcrumb">
						<li class="breadcrumb-item">${daily.region}</li>
					</ol>
				</nav>
			</div>
			<div class="card">
				<div class="d-flex justify-content-between align-items-center">
					<h1 class="card-title ml-auto">${daily.title}</h1>
					<c:if test="${daily.nick eq sessionScope.nick}">
						<span class="mr-auto">
							<a style="color: #1a1d20" class="btn btn-link edit1" onClick="location.href='dailyupdateform?post_id=${daily.post_id}&page=${page}'">수정</a>
							<a style="color: #1a1d20" class="btn btn-link" onclick="delcheck()">삭제</a>
						</span>
					</c:if>
				</div>
				<div class="d-flex justify-content-between align-items-center" style="margin-left: 20px; color: #2c0b0e">
					<span class="ml-auto">${daily.nick}</span>
					<span class="mr-auto"><fmt:formatDate value="${daily.reg_date }" pattern="yyyy-MM-dd"/> | 조회수 ${daily.read_count}</span>
				</div>
				<hr>
				<div class="card-body" style="min-height: 500px">
					<img src="upload/${daily.daily_file}" style="width: 100%; margin: 0 auto">
					<pre style="margin-top: 20px">${daily.content}</pre>
				</div>
				<!--좋아요, 스크랩, 신고-->
				<div class="d-flex justify-content-end align-items-center" style="padding: 10px; color: #2c0b0e">
					<c:if test="${not empty like}">
						<span class="mr-auto">
							<i class="bi bi-heart-fill" id="heart-fill" style="color : #FF0000; font-size: 35px"></i>&nbsp;&nbsp;&nbsp;&nbsp;
						</span>
					</c:if>
					<c:if test="${empty like}">
						<span class="mr-auto">
							<i class="bi bi-heart" id="heart" style="color : #FF0000; font-size: 35px"></i>&nbsp;&nbsp;&nbsp;&nbsp;
						</span>
					</c:if>
					<c:if test="${not empty scrap}">
						<span class="mr-auto">
							<i class="bi bi-bookmark-fill" id="scrap-fill" style="color : #3B5998; font-size: 35px"></i>&nbsp;&nbsp;&nbsp;&nbsp;
						</span>
					</c:if>
					<c:if test="${empty scrap}">
						<span class="mr-auto">
							<i class="bi bi-bookmark" id="scrap" style="color : #3B5998; font-size: 35px"></i>&nbsp;&nbsp;&nbsp;&nbsp;
						</span>
					</c:if>
					<c:if test="${sessionScope.nick ne daily.nick }">
						<i class="bi bi-exclamation-circle" onclick="openReportPopup(${daily.post_id})" style="font-size: 35px; color: #1a1d20"></i>
					</c:if>
				</div>
			</div>
		</div>

		<!--댓글-->
		<div class="col-md-8 offset-md-1 col-lg-8 offset-lg-1" style="margin: 0 auto">
			<div class="title-box-d">
				<h3 class="title-d">Comments</h3>
			</div>
			<form name="replyForm" id="replyForm" class="container mt-1 mb-5" style="padding: 0">
				<input type="hidden" name="nick" value="${sessionScope.nick}">
				<input type="hidden" name="post_id" value="${daily.post_id}">
				<div class="row">
					<div class="col-md-11 mb-2">
						<textarea class="form-control" style="height: 50px;" name="re_content"></textarea>
					</div>
					<div class="col-md-1 mb-2" style="padding: 0">
						<button type="button" class="btn btn-primary btn-block" style=" width: 60px; height: 50px; font-size: 17px" id="repInsert">확인</button>
					</div>
				</div>
			</form>
			<div id="slist"></div>
		</div>
	</section>

</main><!-- End #main -->

<jsp:include page="../include/footer.jsp"/>

</body>

</html>