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

<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

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

	<%@ include file="../include/header.jsp"%>
	<main id="main">



		<!-- 게시판 이동 외 =========================== -->

		<c:set var="highest" />
		<!-- test value -->
		<c:set var="lowest" />
		<!-- test value -->

		<c:set var="lat" value="37.5683" />
		<c:set var="lon" value="127" />


		<div id="weather">

			<div class="week">
				<c:forEach var="d" items="${dates }" varStatus="status">
					<c:if test="${status.first}">
						<c:set var="today1" value="${d.df1}" />
						<c:set var="today2" value="${d.df2}" />
						<c:set var="today3" value="${d.df3}" />
					</c:if>
					<div class="day">
						<input type="hidden" class="datevalue" value="${d.df2}">
						<div class="d1">${d.df1}</div>
						<div class="lowest">
							<c:set var="lowest" />
							${lowest }
						</div>
						<div class="highest">
							<c:set var="highest" />
							${highest }
						</div>
					</div>
				</c:forEach>
			</div>



			<script>
			
			$(document).ready(function(){
		        // 페이지 로딩 완료 후 getWeather 함수 호출
		        getWeather(35.1028, 129.0403);
		    });
			
			
			function getWeather(lat, lon){
				const apiUrl = 'http://api.openweathermap.org/data/2.5/forecast';
				/* const lat = '35.1028';  // 실제 위도 값으로 대체
				const lon = '129.0403';  // 실제 경도 값으로 대체 */
				const apiKey = '9657c91ee7eafcd506fa727097c899fa';  // 실제 API 키로 대체


				
				const apiUrlWithParams = `http://api.openweathermap.org/data/2.5/forecast?lat=${lat}&lon=${lon}&appid=9657c91ee7eafcd506fa727097c899fa&units=metric`;
//				const apiUrlWithParams = `${apiUrl}?lat=${lat}&lon=${lon}&appid=${apiKey}`;
// http://api.openweathermap.org/data/2.5/daily?lat=35.1028&lon=129.0403&appid=9657c91ee7eafcd506fa727097c899fa
				
				
				fetch(apiUrlWithParams)
				  .then(response => response.json())
				  .then(data => {
					  
					  const weatherData = data.list;

			            // 각 날짜의 12:00:00 및 21:00:00 날씨 정보 추출
			            const dailyWeather = weatherData.reduce((result, item) => {
			                const date = item.dt_txt.split(' ')[0];
			                const time = item.dt_txt.split(' ')[1];

			                if (time === '12:00:00' || time === '21:00:00') {
			           

			                    // 최저 온도 및 최고 온도 업데이트
			                    if (result[date].lowest === null || item.main.temp < result[date].lowest) {
			                        result[date].lowest = item.main.temp;
			                    }

			                    if (result[date].highest === null || item.main.temp > result[date].highest) {
			                        result[date].highest = item.main.temp;
			                    }
			                }
			                
			                return result;
					
			            }, {});

			            // 추출된 데이터를 활용하여 lowest 및 highest 변수 업데이트
for (const date in dailyWeather) {
    const lowestTemp = dailyWeather[date].lowest;
    const highestTemp = dailyWeather[date].highest;

    // 해당 날짜에 맞는 요소 찾아 업데이트
    const dateInputElement = document.querySelector(`.datevalue[value="${date}"]`);
    
    if (dateInputElement) {
        const dayElement = dateInputElement.closest('.day');
        if (dayElement) {
            const lowestElement = dayElement.querySelector('.lowest');
            const highestElement = dayElement.querySelector('.highest');
            
            if (lowestElement) {
                lowestElement.innerText = `Lowest: ${lowestTemp}°C`;
            }
            if (highestElement) {
                highestElement.innerText = `Highest: ${highestTemp}°C`;
            }
        }
    }
}                
			                
				    // API 응답을 처리하는 로직을 여기에 추가
				    console.log(data);
			
				  })
				  .catch(error => {
				    // API 호출 중 발생한 오류 처리
				    console.error('API 호출 중 오류:', error);
				  });
				}

			

</script>

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