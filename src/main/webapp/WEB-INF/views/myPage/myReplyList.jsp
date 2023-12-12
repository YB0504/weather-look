<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="ko">
<!DOCTYPE html>
<%--head--%>
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
				<li class="breadcrumb-item"><a href="index.html">작성 댓글</a></li>
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
				<c:if test="${empty replyList}">
			</table>
			<br>
			작성한 댓글이 없습니다
			</c:if>
			<c:if test="${not empty replyList}">
				<c:set var="num" value="${replyCount-(page-1)*listSize}"/>
				<tbody>
				<c:forEach var="reply" items="${replyList}">
					<tr>
						<td>
							<c:out value="${num}"/>
							<c:set var="num" value="${num-1}"/>
						</td>
						<td><a href="detail?type_name=${reply.board_type}&post_id=${reply.post_id}">${reply.title}</a></td>
						<td>${reply.nick}</td>
						<td>
							<c:set var="now" value="<%=new java.util.Date()%>" />
							<fmt:formatDate value="${reply.reg_date}" pattern="yyyy-MM-dd" type="Date"/>
						</td>
						<td>${reply.read_count}</td>
					</tr>
				</c:forEach>
				</tbody>
				</table>

				<%--paging--%>
				<div style="display: flex; justify-content: center;">
					<ul class="pagination">
						<!-- 이전 블럭으로 이동 -->
						<c:if test="${startPage > 10}">
							<li class="page-item">
								<a class="page-link" href="myReplyList?page=${startPage - 10}" tabindex="-1">&laquo;</a>
							</li>
						</c:if>
						<!-- 각 블럭에 10개의 페이지 출력 -->
						<c:forEach var="i" begin="${startPage}" end="${endPage}">
							<c:if test="${i == page }"> <!-- 현재 페이지 -->
								<li class="page-item active" aria-current="page">
									<a class="page-link" href="">${i}</a>
								</li>
							</c:if>
							<c:if test="${i != page }"> <!-- 현재 페이지가 아닌 경우 -->
								<li class="page-item"><a class="page-link" href="myReplyList?page=${i}">${i}</a></li>
							</c:if>
						</c:forEach>

						<!-- 다음 블럭으로 이동 -->
						<c:if test="${endPage < totalPage}">
							<li class="page-item">
								<a class="page-link" href="myReplyList?page=${endPage+1}">&raquo;</a>
							</li>
						</c:if>
					</ul>
				</div>
			</c:if>
		</div>
	</section>
</main>

<jsp:include page="../include/footer.jsp"/>

</body>
</html>