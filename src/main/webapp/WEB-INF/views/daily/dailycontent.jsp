<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>데일리룩 CSS 테스트</title>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>MyPortfolio Bootstrap Template - Work Single</title>
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

</head>
<body>

	<main id="main">

		<section class="section">
			<div class="container">
				<div class="row mb-4 align-items-center">
					<div class="col-md-6" data-aos="fade-up">
						<h2>${daily.title }</h2>
						<p>${daily.nick }</p>
					</div>
				</div>
			</div>

			<div class="site-section pb-0">
				<div class="container">
					<div class="row align-items-stretch">
						<div class="col-md-8" data-aos="fade-up">
							<img src="assets/img/img_1_big.jpg" alt="Image" class="img-fluid">
						</div>
						<div class="col-md-3 ml-auto" data-aos="fade-up"
							data-aos-delay="100">
							<div class="sticky-content">
								<h3 class="h3">Boxed Water</h3>
								<p class="mb-4">
									<span class="text-muted">Design</span>
								</p>

								<div class="mb-5">
									<p>${content }</p>

								</div>

								<h4 class="h4 mb-3">What I did</h4>
								<ul class="list-unstyled list-line mb-5">
									<li>Design</li>
									<li>HTML5/CSS3</li>
									<li>CMS</li>
									<li>Logo</li>
								</ul>

								<p>
									<a href="#" class="readmore">Visit Website</a>
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>

		<!-- ======= Testimonials Section ======= -->
		<section class="section pt-0">
			<div class="container">

				<div class="testimonials-slider swiper" data-aos="fade-up"
					data-aos-delay="100">
					<div class="swiper-wrapper">

						<div class="swiper-slide">
							<div class="testimonial-wrap">
								<div class="testimonial">
									<img src="assets/img/person_1.jpg" alt="Image"
										class="img-fluid">
									<blockquote>
										<p>Lorem ipsum dolor sit amet consectetur adipisicing
											elit. Quisquam necessitatibus incidunt ut officiis explicabo
											inventore.</p>
									</blockquote>
									<p>&mdash; Jean Hicks</p>
								</div>
							</div>
						</div>
						<!-- End testimonial item -->

						<div class="swiper-slide">
							<div class="testimonial-wrap">
								<div class="testimonial">
									<img src="assets/img/person_2.jpg" alt="Image"
										class="img-fluid">
									<blockquote>
										<p>Lorem ipsum dolor sit amet consectetur adipisicing
											elit. Quisquam necessitatibus incidunt ut officiis explicabo
											inventore.</p>
									</blockquote>
									<p>&mdash; Chris Stanworth</p>
								</div>
							</div>
						</div>
						<!-- End testimonial item -->

					</div>
					<div class="swiper-pagination"></div>
				</div>

			</div>
		</section>
		<!-- End Testimonials Section -->

	</main>
	<!-- End #main -->


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