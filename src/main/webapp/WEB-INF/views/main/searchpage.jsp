<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Page</title>

<!-- Google Fonts -->
<link href="https://fonts.gstatic.com" rel="preconnect">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="assets/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="assets/vendor/boxicons/css/boxicons.min.css"
	rel="stylesheet">
<link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
<link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
<link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
<link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

<!-- Template Main CSS File -->
<link href="assets/css/style.css" rel="stylesheet">


</head>
<body>

	<%@ include file="../include/header.jsp"%>

	<main id="main" class="main">

		<div class="pagetitle">
			<h1>검색 결과</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item active">Search</li>
					<c:if test="${!empty keyword }">
						<li class="breadcrumb-item active">${listcount }개의 글</li>
					</c:if>
				</ol>
			</nav>
		</div>
		<!-- End Page Title -->


		<!-- 검색결과 리스트 -->
		<div class="card">
			<div class="card-body">
				<table class="table">

					<tr>
						<th class="col-md-1">게시판</th>
						<th class="col-md-6">제목</th>
						<th class="col-md-1">닉네임</th>
						<th class="col-md-1">조회수</th>
						<th class="col-md-2">작성일</th>
					</tr>

					<c:forEach var="r" items="${searchresult }">
						<tr>
							<td>${r.type_name }</td>
							<td><a href="detail?type_name=${r.type_name}&post_id=${r.post_id}"> ${r.title } </a></td>
							<td>${r.nick }</td>
							<td>${r.read_count }</td>
							<td><fmt:formatDate pattern="yyyy-MM-dd"
									value="${r.reg_date }" /></td>
						</tr>
					</c:forEach>

				</table>
			</div>
		</div>


			<!-- 페이징 ==============> -->

			<div style="display: flex; justify-content: center;">
				<ul class="pagination">
					<c:if test="${startpage > 10}">
						<li class="page-item disabled"><a class="page-link"
							href="search?page=${startpage - 10}&keyword=${keyword}" tabindex="-1"
							aria-disabled="true">&laquo;</a></li>
					</c:if>

					<c:forEach var="i" begin="${startpage}" end="${endpage}">
						<c:if test="${i == page }">
							<li class="page-item active" aria-current="page"><a
								class="page-link" href="">${i}</a></li>
						</c:if>
						<c:if test="${i != page }">
							<li class="page-item"><a class="page-link"
								href="search?page=${i}&keyword=${keyword}">${i}</a></li>
						</c:if>
					</c:forEach>

					<c:if test="${endpage < maxpage}">
						<li class="page-item"><a class="page-link"
							href="search?page=${startpage+10}&keyword=${keyword}">&raquo;</a></li>
					</c:if>
				</ul>
			</div>

			<!--  <============== 페이징 -->


	</main>

	<!-- Vendor JS Files -->
	<script src="assets/vendor/apexcharts/apexcharts.min.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="assets/vendor/chart.js/chart.umd.js"></script>
	<script src="assets/vendor/echarts/echarts.min.js"></script>
	<script src="assets/vendor/quill/quill.min.js"></script>
	<script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
	<script src="assets/vendor/tinymce/tinymce.min.js"></script>
	<script src="assets/vendor/php-email-form/validate.js"></script>

	<!-- Template Main JS File -->
	<script src="assets/js/main.js"></script>


</body>
</html>