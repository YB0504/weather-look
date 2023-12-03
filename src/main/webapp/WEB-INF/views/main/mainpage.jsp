<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">


<title>Weatherlook Main</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="assets/img/favicon.png" rel="icon">
<link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=https://fonts.googleapis.com/css?family=Inconsolata:400,500,600,700|Raleway:400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="assets/vendor/aos/aos.css" rel="stylesheet">
<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="assets/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

<!-- Template Main CSS File -->
<link href="assets/css/style.css" rel="stylesheet">

<link href="css/mainpage2.css" rel="stylesheet">

<!-- =======================================================
  * Template Name: MyPortfolio
  * Updated: Sep 18 2023 with Bootstrap v5.3.2
  * Template URL: https://bootstrapmade.com/myportfolio-bootstrap-portfolio-website-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>
<body>

	<!-- ======= Navbar ======= -->
	<div class="collapse navbar-collapse custom-navmenu" id="main-navbar">
		<div class="container py-2 py-md-5">
			<div class="row align-items-start">
				<div class="col-md-2">
					<ul class="custom-menu">
						<li class="active"><a href="main">Home</a></li>
						<li ><a href="dailylist">DailyLook</a></li>
						<li><a href="services.html">Review</a></li>
						<li><a href="works.html">Community</a></li>
						<li><a href="search">Search</a></li>
					</ul>
				</div>
				<div class="col-md-6 d-none d-md-block  mr-auto">
					<div class="tweet d-flex">
						<span class="bi bi-twitter text-white mt-2 mr-3"></span>
						<div>
							<p>
								<em>Lorem ipsum dolor sit amet consectetur adipisicing
									elit. Quisquam necessitatibus incidunt ut officiis explicabo
									inventore. <br> <a href="#">t.co/v82jsk</a>
								</em>
							</p>
						</div>
					</div>
				</div>
				<div class="col-md-4 d-none d-md-block">
					<h3>Hire Me</h3>
					<p>
						Lorem ipsum dolor sit amet consectetur adipisicing elit. Quisquam
						necessitatibus incidunt ut officiisexplicabo inventore. <br>
						<a href="#">myemail@gmail.com</a>
					</p>
				</div>
			</div>

		</div>
	</div>

	<nav class="navbar navbar-light custom-navbar">
		<div class="container">
			<a class="navbar-brand" href="main">WeatherLook</a> <a
				href="#" class="burger" data-bs-toggle="collapse"
				data-bs-target="#main-navbar"> <span></span>
			</a>
		</div>
	</nav>

	<main id="main">



		<!-- 게시판 이동 외 =========================== -->

		<c:set var="highest" value="8.2" />
		<!-- test value -->
		<c:set var="lowest" value="4.0" />
		<!-- test value -->

			<div class="weather_weekly">
			
			<c:forEach var="w" items="${weekly }" varStatus="i">

					<div class="weather_day" data-aos="fade-up">
						<h2>
							<a href="weather?page=1&lowest=${w.lowest}&highest=${w.highest}">
								${i.index} 번째 / <img src = "ico_svg/icon_flat_wt${w.ico }.svg"> 최저기온 ${w.lowest } 최고기온 ${w.highest }</a>
						</h2>
					</div>

				</c:forEach>

			</div>
			
		<section class="section site-portfolio">



			<!-- 하위 3x3 썸네일 출력 -->

			<div class="container">
				<div id="portfolio-grid" class="row no-gutter" data-aos="fade-up"
					data-aos-delay="200">
					<c:forEach var="r" items="${mainlist}" varStatus="i">
						<div class="item col-sm-6 col-md-4 col-lg-4 mb-4">
							<a href="detail?type_name=${r.type_name}&post_id=${r.post_id}"
								class="item-wrap fancybox">
								<div class="work-info">
									<h2>${r.title}</h2>
									<span>${r.type_name}게시판${r.nick}</span>
								</div> <img class="img-fluid" src="images/img_${i.index }.jpg">
							</a>
						</div>
					</c:forEach>
				</div>
			</div>



			<div align="center">


				<c:if test="${page > 1 }">
					<a href="main?page=${page-1}">[이전]</a>&nbsp;
			</c:if>

				<c:forEach var="a" begin="${startpage}" end="${endpage}">
					<c:if test="${a == page }">
					[${a}]
				</c:if>
					<c:if test="${a != page }">
						<a href="main?page=${a}">[${a}]</a>&nbsp;
				</c:if>
				</c:forEach>

				<c:if test="${page >= maxpage }">
				[다음] 
			</c:if>
				<c:if test="${page < maxpage }">
					<a href="main?page=${page+1}">[다음]</a>
				</c:if>
			</div>
		</section>
	</main>

	<!-- Vendor JS Files -->
	<script src="assets/vendor/aos/aos.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
	<script src="assets/vendor/swiper/swiper-bundle.min.js"></script>
	<script src="assets/vendor/php-email-form/validate.js"></script>

	<!-- Template Main JS File -->
	<script src="assets/js/main.js"></script>

</body>

</html>