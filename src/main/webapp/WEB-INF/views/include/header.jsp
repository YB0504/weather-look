<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
    $(function () {
        $("#deleteMemberLink").click(function () {
            return confirm("회원탈퇴 하시겠습니까");
        });
    });
</script>

<header id="header" class="header fixed-top d-flex align-items-center">
	<div class="d-flex align-items-center justify-content-between">
		<a href="main" class="logo d-flex align-items-center"> <span
				class="d-none d-lg-block">WEATHER LOOK</span>
		</a> <i class="bi bi-list toggle-sidebar-btn"></i>
	</div>
	<!-- End Logo -->
	<!-- 검색버튼 =============================== -->
	<div class="search-bar">
		<form class="search-form d-flex align-items-center" method="get"
		      action="search">
			<input type="text" name="keyword" placeholder="검색"
			       value="${keyword}" title="Enter search keyword">
			<button type="submit" title="Search">
				<i class="bi bi-search"></i>
			</button>
		</form>
	</div>

	<!-- 검색버튼 =============================== -->
	<nav class="header-nav ms-auto">
		<ul class="d-flex align-items-center">
			<li class="nav-item d-block d-lg-none"><a
					class="nav-link nav-icon search-bar-toggle " href="#"> <i
					class="bi bi-search"></i>
			</a></li>
			<!-- End Search Icon-->
			<li class="nav-item dropdown pe-3">
				<a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown">
				<span class="d-none d-md-block dropdown-toggle ps-2">${sessionScope.nick}</span>
			</a>
				<!-- End Profile Iamge Icon -->
				<ul
						class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
					<li class="dropdown-header">
						<h6>${sessionScope.nick}</h6>
					</li>
					<li>
						<hr class="dropdown-divider">
					</li>
					<li>
						<a class="dropdown-item d-flex align-items-center" href="logOut">
							<i class="bi bi-box-arrow-right"></i>
							<span>로그아웃</span>
						</a>
					</li>
				</ul><!-- End Profile Dropdown Items -->
			</li><!-- End Profile Nav -->
		</ul>
	</nav><!-- End Icons Navigation -->

</header>
<!-- End Header -->

<!-- ======= Sidebar ======= -->
<aside id="sidebar" class="sidebar">

	<ul class="sidebar-nav" id="sidebar-nav">
		<li class="nav-heading">Board</li>

		<li class="nav-item">
			<a class="nav-link collapsed" href="main">
				<i class="bi bi-grid-1x2"></i>
				<span>메인페이지</span>
			</a>
		</li>
		<li class="nav-item">
			<a class="nav-link collapsed" href="dailylist">
				<i class="bi bi-brightness-alt-high"></i>
				<span>데일리룩</span>
			</a>
		</li>

		<li class="nav-item">
			<a class="nav-link collapsed" href="reviewList">
				<i class="bi bi-bag-check"></i>
				<span>쇼핑후기</span>
			</a>
		</li>

		<li class="nav-item">
			<a class="nav-link collapsed" href="commlist">
				<i class="bi bi-chat-left-text"></i>
				<span>커뮤니티</span>
			</a>
		</li>


		<li class="nav-heading" style="margin-top: 40px">Personal</li>

		<li class="nav-item">
			<a class="nav-link collapsed" href="updateMemberForm">
				<i class="bi bi-person"></i>
				<span>회원정보</span>
			</a>
		</li><!-- End Profile Page Nav -->

		<li class="nav-item">
			<a class="nav-link collapsed" data-bs-target="#charts-nav" data-bs-toggle="collapse" href="#">
				<i class="bi bi-pencil-square"></i><span>활동 내역</span><i class="bi bi-chevron-down ms-auto"></i>
			</a>
			<ul id="charts-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
				<li>
					<a href="myPostList">
						<i class="bi bi-circle"></i><span>글</span>
					</a>
				</li>
				<li>
					<a href="myReplyList">
						<i class="bi bi-circle"></i><span>댓글</span>
					</a>
				</li>
				<li>
					<a href="myLikeList">
						<i class="bi bi-circle"></i><span>좋아요</span>
					</a>
				</li>
				<li>
					<a href="myScrapList">
						<i class="bi bi-circle"></i><span>스크랩</span>
					</a>
				</li>
			</ul>
		</li><!-- End Charts Nav -->

		<li class="nav-item">
			<a class="nav-link collapsed" data-bs-target="#qnaCarts-nav" data-bs-toggle="collapse" href="#">
				<i class="bi bi-question-circle"></i><span>문의하기</span><i class="bi bi-chevron-down ms-auto"></i>
			</a>
			<ul id="qnaCarts-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
				<li>
					<span style="font-size: small">문의 이메일: example@example.com</span>
				</li>
			</ul>
		</li><!-- End Charts Nav -->

		<li class="nav-item">
			<a class="nav-link collapsed" href="deleteMember" id="deleteMemberLink">
				<i class="bi bi-person-dash"></i>
				<span>회원탈퇴</span>
			</a>
		</li>

		<c:if test="${sessionScope.nick eq 'master'}">
			<li class="nav-heading" style="margin-top: 40px">Admin</li>
			<li class="nav-item">
				<a class="nav-link collapsed" href="reportedpost">
					<i class="bi bi-person-dash"></i>
					<span>관리자 페이지</span>
				</a>
			</li>
		</c:if>
	</ul>
</aside>
<!-- End Sidebar-->