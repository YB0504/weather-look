<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!-- ======= Header ======= -->
<header id="header" class="header fixed-top d-flex align-items-center">

	<div class="d-flex align-items-center justify-content-between">
		<a href="index.html" class="logo d-flex align-items-center">
			<span class="d-none d-lg-block">WEATHER LOOK</span>
		</a>
		<i class="bi bi-list toggle-sidebar-btn"></i>
	</div><!-- End Logo -->

	<div class="search-bar">
		<form class="search-form d-flex align-items-center" method="POST" action="#">
			<input type="text" name="query" placeholder="Search" title="Enter search keyword">
			<button type="submit" title="Search"><i class="bi bi-search"></i></button>
		</form>
	</div><!-- End Search Bar -->

	<nav class="header-nav ms-auto">
		<ul class="d-flex align-items-center">

			<li class="nav-item d-block d-lg-none">
				<a class="nav-link nav-icon search-bar-toggle " href="#">
					<i class="bi bi-search"></i>
				</a>
			</li><!-- End Search Icon-->

			<li class="nav-item dropdown pe-3">

				<a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown">
					<img src="assets/img/profile-img.jpg" alt="Profile" class="rounded-circle">
					<span class="d-none d-md-block dropdown-toggle ps-2">K. Anderson</span>
				</a><!-- End Profile Iamge Icon -->

				<ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
					<li class="dropdown-header">
						<h6>Kevin Anderson</h6>
						<span>Web Designer</span>
					</li>
					<li>
						<hr class="dropdown-divider">
					</li>

					<li>
						<a class="dropdown-item d-flex align-items-center" href="users-profile.html">
							<i class="bi bi-person"></i>
							<span>My Profile</span>
						</a>
					</li>
					<li>
						<hr class="dropdown-divider">
					</li>

					<li>
						<a class="dropdown-item d-flex align-items-center" href="users-profile.html">
							<i class="bi bi-gear"></i>
							<span>Account Settings</span>
						</a>
					</li>
					<li>
						<hr class="dropdown-divider">
					</li>

					<li>
						<a class="dropdown-item d-flex align-items-center" href="pages-faq.html">
							<i class="bi bi-question-circle"></i>
							<span>Need Help?</span>
						</a>
					</li>
					<li>
						<hr class="dropdown-divider">
					</li>

					<li>
						<a class="dropdown-item d-flex align-items-center" href="#">
							<i class="bi bi-box-arrow-right"></i>
							<span>Sign Out</span>
						</a>
					</li>

				</ul><!-- End Profile Dropdown Items -->
			</li><!-- End Profile Nav -->

		</ul>
	</nav><!-- End Icons Navigation -->

</header><!-- End Header -->

<!-- ======= Sidebar ======= -->
<aside id="sidebar" class="sidebar">

	<ul class="sidebar-nav" id="sidebar-nav">
		<li class="nav-heading">Board</li>

		<li class="nav-item">
			<a class="nav-link collapsed" href="#">
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
			<a class="nav-link collapsed" href="users-profile.html">
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
					<a href="charts-chartjs.html">
						<i class="bi bi-circle"></i><span>문의 이메일: example@example.com</span>
					</a>
				</li>
			</ul>
		</li><!-- End Charts Nav -->

		<li class="nav-item">
			<a class="nav-link collapsed" href="pages-faq.html">
				<i class="bi bi-person-dash"></i>
				<span>회원탈퇴</span>
			</a>
		</li>
	</ul>
</aside><!-- End Sidebar-->