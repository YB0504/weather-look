<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="UTF-8">
	<title>게시판 목록</title>

	<meta content="" name="description">
	<meta content="" name="keywords">

	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<link href="https://fonts.gstatic.com" rel="preconnect">
	<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
	<link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
	<link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
	<link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
	<link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
	<link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
	<link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">
	<link href="assets/css/style.css" rel="stylesheet">
	<link href="css/mainpage.css" rel="stylesheet">
</head>
<body>
<%@ include file="../include/header.jsp" %>
<%@ include file="../include/address.jsp" %>
<main id="main">

	<section>
		<div class="titlebox">
			<div class="pagetitle">
				<h1>DailyLook</h1>
				<nav>
					<ol class="breadcrumb">
						<li class="breadcrumb-item">${listcount }개의 글</li>
					</ol>
				</nav>
			</div>
		</div>

		<script>
            $(function () {
                $('#region').val("${region}")
            });
            $(function () {
                $('#temp').val("${temp}")
            });

            $(document).ready(function () {
                // 해당 값이 존재하는 경우에만 설정
                if ("${region}" !== "") {
                    $('#region').val("${region}");
                }

                if ("${temp}" !== "") {
                    $('#temp').val("${temp}");
                }

                $('#searchLink').click(function () {
                    var selectedRegion = $('#region').val();
                    var enteredTemperature = $('#temp').val();
                    var deviation = 5;
                    var templow = parseInt(enteredTemperature, 10) - parseInt(deviation);
                    var temphigh = parseInt(enteredTemperature, 10) + parseInt(deviation);

                    var url = "dailylist?page=1";

                    if (selectedRegion) {
                        url += "&region=" + encodeURIComponent(selectedRegion);
                    }

                    if (enteredTemperature) {
                        url += "&templow=" + templow;
                        url += "&temphigh=" + temphigh;
                    }
                    window.location.href = url;
                });
            });
		</script>

		<%--날씨 조건 박스--%>
		<div class="weatherconditionbox">
			<div class="weathercondition">
				<div class="weathercondition_region">
					<div class="weathercondition_text">지역</div>
					<div class="weathercondition_condition">
						<select id="region" class="form-select">
							<option value="">선택</option>
							<c:forEach var="a" items="${address }">
								<option value="${a }">${a }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="weathercondition_temperature">
					<div class="weathercondition_text">기온</div>
					<div class="weathercondition_condition">
						<input name="temp" id="temp" type="number" class="form-control">
					</div>
				</div>
				<div class="weathercondition_search">
					<a href="#" id="searchLink">
						<div class="icon">
							<i class="bi bi-search"></i>
						</div>
					</a>
				</div>
				<div class="weathercondition_write">
					<a href="dailyform">
						<div class="icon">
							<i class="bi bi-pencil-square"></i>
						</div>
					</a>
				</div>
			</div>
		</div>

		<%--3x3 썸네일 출력--%>
		<div class="container mt-4" style="max-width: 900px;">
			<div class="row">
				<c:forEach var="r" items="${dailyList}" varStatus="i">
					<div class="col-md-4 mb-4">
						<div style="max-width: 100%; display: flex; flex-direction: column; align-items: center;">
							<a href="dailycontent?post_id=${r.post_id}&page=${page}">
								<img src="upload/${r.daily_file}"
								     style="max-width: 100%; height: auto; border-radius: 8px;">
								<div style="text-align: left; margin-top: 10px;">
									<span style="font-family: 'Open Sans', sans-serif; color: #e03e2d; display: block; margin-bottom: 2px; font-weight: bold;">#daily</span>
									<span style="font-family: 'Open Sans', sans-serif; color: #000000; display: block; margin-top: 2px; font-weight: bold;">${r.title}</span>
								</div>
							</a>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>

		<%--페이징--%>
		<div style="display: flex; justify-content: center;">
			<ul class="pagination">
				<c:if test="${startPage > 10}">
					<li class="page-item disabled"><a class="page-link"
					                                  href="dailylist?page=${startpage - 10}&region=${region }&templow=${templow}&temphigh=${temphigh}"
					                                  tabindex="-1"
					                                  aria-disabled="true">&laquo;</a></li>
				</c:if>

				<c:forEach var="i" begin="${startPage}" end="${endPage}">
					<c:if test="${i == page }">
						<li class="page-item active" aria-current="page"><a
								class="page-link" href="">${i}</a></li>
					</c:if>
					<c:if test="${i != page }">
						<li class="page-item"><a class="page-link"
						                         href="dailylist?page=${i}&region=${region }&templow=${templow}&temphigh=${temphigh}">${i}</a>
						</li>
					</c:if>
				</c:forEach>

				<c:if test="${endPage < pageCount}">
					<li class="page-item"><a class="page-link"
					                         href="dailylist?page=${startPage + 10}&region=${region }&templow=${templow}&temphigh=${temphigh}">&raquo;</a>
					</li>
				</c:if>
			</ul>
		</div>
	</section>
</main>

<script src="assets/vendor/apexcharts/apexcharts.min.js"></script>
<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="assets/vendor/chart.js/chart.umd.js"></script>
<script src="assets/vendor/echarts/echarts.min.js"></script>
<script src="assets/vendor/quill/quill.min.js"></script>
<script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
<script src="assets/vendor/tinymce/tinymce.min.js"></script>
<script src="assets/vendor/php-email-form/validate.js"></script>
<script src="assets/js/main.js"></script>

</body>
</html>