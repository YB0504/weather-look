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



<!-- 에셋2 ========================================= -->

<!-- Favicons -->
<!--   <link href="assets/img/favicon.png" rel="icon">
  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon"> -->

<!-- Google Fonts -->
<link href="https://fonts.gstatic.com" rel="preconnect">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="assets2/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="assets2/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="assets2/vendor/boxicons/css/boxicons.min.css"
	rel="stylesheet">
<link href="assets2/vendor/quill/quill.snow.css" rel="stylesheet">
<link href="assets2/vendor/quill/quill.bubble.css" rel="stylesheet">
<link href="assets2/vendor/remixicon/remixicon.css" rel="stylesheet">
<link href="assets2/vendor/simple-datatables/style.css" rel="stylesheet">

<!-- Template Main CSS File -->
<link href="assets2/css/style.css" rel="stylesheet">


<!-- ========================================= 에셋2 -->



<link href="css/mainpage2.css" rel="stylesheet">


</head>
<body>

<%@ include file="../include/header.jsp" %>
	<main id="main">



		<!-- 게시판 이동 외 =========================== -->

		<c:set var="highest"/>
		<!-- test value -->
		<c:set var="lowest"/>
		<!-- test value -->

		<script>
    document.addEventListener("DOMContentLoaded", function () {
        const clickableDays = document.querySelectorAll(".clickable-day");
        const weatherDays = document.querySelectorAll(".weather_day");

        clickableDays.forEach(clickableDay => {
            clickableDay.addEventListener("click", function () {
                const clickedDay = this.innerText.trim();
                console.log("Clicked day: " + clickedDay);

                weatherDays.forEach(weatherDay => {
                    const dayId = weatherDay.id;
                    // 클릭한 요일에 해당하는 요소만 표시
                    weatherDay.classList.toggle("hidden", dayId !== clickedDay);
                });
            });
        });
    });
</script>


		<div id="weather">

			<div class="week">
				<c:forEach var="day" items="${days }" varStatus = "i">
					<div class="day">
					<input type = "hidden" name = "dayvalue" value = "${i.index }">
						<a>${day }</a>
					</div>
				</c:forEach>
			</div>




			<div class="weather_weekly">
				<c:forEach var="w" items="${weekly}" varStatus="i">
					<div class="weather_day">
						<p class="weather_text">
							<a href="weather?page=1&lowest=${w.lowest}&highest=${w.highest}">
								${w.day} / <img src="ico_svg/icon_flat_wt${w.ico}.svg">
								최저기온 ${w.lowest} 최고기온 ${w.highest}
							</a>
						</p>
					</div>
				</c:forEach>
			</div>

		</div>

		<section>



			<!-- 하위 3x3 썸네일 출력 -->

			<div class="maincard">
				<c:forEach var="r" items="${mainlist}" varStatus="i">
					<div class="card-body">
						<a href="detail?type_name=${r.type_name}&post_id=${r.post_id}"
							class="maincardlink"> <img src="images/img_${i.index }.jpg">
							<div class="maincardinfo">
								<p>${r.title}</p>
								<span>${r.type_name}게시판${r.nick}</span>
							</div>
						</a>
					</div>
				</c:forEach>
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


	<!-- 에셋2 ========================================= -->

	<!-- Vendor JS Files -->
	<script src="assets2/vendor/apexcharts/apexcharts.min.js"></script>
	<script src="assets2/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="assets2/vendor/chart.js/chart.umd.js"></script>
	<script src="assets2/vendor/echarts/echarts.min.js"></script>
	<script src="assets2/vendor/quill/quill.min.js"></script>
	<script src="assets2/vendor/simple-datatables/simple-datatables.js"></script>
	<script src="assets2/vendor/tinymce/tinymce.min.js"></script>
	<script src="assets2/vendor/php-email-form/validate.js"></script>

	<!-- Template Main JS File -->
	<script src="assets2/js/main.js"></script>

	<!-- ========================================= 에셋2 -->

</body>

</html>