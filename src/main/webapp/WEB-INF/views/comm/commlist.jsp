<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="ko">
<!DOCTYPE html>
<%--head--%>
<head>
	<meta charset="utf-8">
	<meta content="width=device-width, initial-scale=1.0" name="viewport">

	<title>Dashboard - NiceAdmin Bootstrap Template</title>
	<meta content="" name="description">
	<meta content="" name="keywords">

	<!-- Favicons -->
	<link href="assets/img/favicon.png" rel="icon">
	<link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

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
	<script>
        $(function () {
            $("#deleteMemberLink").click(function () {
                return confirm("회원탈퇴 하시겠습니까");
            });
        });
	</script>
</head>
<body>
<jsp:include page="../include/header.jsp"/>

<!-- ======= Main ======= -->
<main id="main" class="main">

	<div class="pagetitle" style="margin-bottom: 30px">
		<h1>활동내역</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="index.html">좋아요</a></li>
			</ol>
		</nav>
	</div><!-- End Page Title -->

	<section class="section dashboard">
		<div class="row">
			<table class="table table-hover">
				<thead>
				<th class="col-md-1">번호</th>
				<th class="col-md-8">제목</th>
				<th class="col-md-1">작성자</th>
				<th class="col-md-1">날짜</th>
				<th class="col-md-1">조회수</th>
				</thead>
				<c:if test="${empty commlist}">
			</table>
			<br>
			좋아요한 글이 없습니다
			</c:if>
			<c:if test="${not empty commlist}">
				<c:set var="num" value="${listCount-(page-1)*10}"/>
				<tbody>
				<c:forEach var="comm" items="${commlist}">
					<tr>
						<td>
							<c:out value="${num}"/>
							<c:set var="num" value="${num-1}"/>
						</td>
						<td>
							<a href="commcontent?post_id=${b.post_id}&page=${page}">${comm.title}</a>
						</td>
						<td>${comm.nick}</td>
						<td>
							<c:set var="now" value="<%=new java.util.Date()%>"/>
							<fmt:formatDate value="${comm.reg_date}" pattern="yyyy-MM-dd" type="Date"/>
						</td>
						<td>${comm.read_count}</td>
					</tr>
				</c:forEach>
				</tbody>
				</table>

				<%--paging--%>
				<!-- 페이지 처리 -->
				<center>
					<!-- 1 페이지로 이동 -->
					<a href="commlist?page=1" style="text-decoration:none"> << </a>

					<!-- 이전 블럭으로 이동 -->
					<c:if test="${startPage > 10}">
						<a href="commlist?page=${startPage - 10}">[이전]</a>
					</c:if>

					<!-- 각 블력에 10개의 페이지 출력 -->
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
						<c:if test="${i == page }"> <!-- 현재 페이지 -->
							[${i}]
						</c:if>
						<c:if test="${i != page }"> <!-- 현재 페이지가 아닌경우 -->
							<a href="commlist?page=${i}">[${i}]</a>
						</c:if>
					</c:forEach>

					<!-- 다음 블럭으로 이동 -->
					<c:if test="${endPage < pageCount}">
						<a href="commlist?page=${startPage + 10}">[다음]</a>
					</c:if>

					<!-- 마지막 페이지로 이동 -->
					<a href="commlist?page=${pageCount}" style="text-decoration:none"> >> </a>

				</center>
			</c:if>
		</div>
	</section>
</main><!-- End #main -->

<jsp:include page="../include/footer.jsp"/>
</body>
</html>