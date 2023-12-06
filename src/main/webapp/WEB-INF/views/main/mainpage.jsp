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
		<c:set var="templow" />
		<c:set var="temphigh" />

		<div class="weather">
			<div class="weather_2x2box">

				<c:forEach var="d" items="${dates }" varStatus="status">
					<a href="#" onclick="updateLink('${d.df2}');">
						<div id="${d.df2 }" class="day">
							<div class="dayvalue">${d.df1}${d.df3 }</div>
							<div class="dayvalue" id="${d.df2 }_low"></div>
							<div class="dayvalue" id="${d.df2 }_high"></div>
						</div>
					</a>
				</c:forEach>



				<script>
				
			    function updateLink(dateId) {
			        var templow = document.getElementById(dateId + '_templow').textContent.slice(0, -1);
			        var temphigh = document.getElementById(dateId + '_temphigh').textContent.slice(0, -1);
			        var link = 'daily?templow=' + templow + '&temphigh=' + temphigh;
			        window.location.href = link;
			    }
				
				
			
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
					  
					

			                
				    // API 응답을 처리하는 로직을 여기에 추가
				    console.log(data);
				    displayWeatherInfo(data);
				   
				  })
				  .catch(error => {
				    // API 호출 중 발생한 오류 처리
				    console.error('API 호출 중 오류:', error);
				  });
				}

			  function displayWeatherInfo(data) {
		            
		            
		            for (var i = 0; i < data.list.length; i++) {
		  
		                var weatherlist = data.list[i];

		                var dateTimeParts = weatherlist.dt_txt.split(' '); // 날짜와 시간을 분리
		                var date = dateTimeParts[0];
		                var time = dateTimeParts[1];
		                
		                
		                
		                if(time === "21:00:00"){
			                var creatediv = document.createElement('div');
			                creatediv.classList.add('weathervalue');
			                creatediv.setAttribute('id', date+'_templow');
			                creatediv.setAttribute('value', weatherlist.main.temp);
			                creatediv.textContent = weatherlist.main.temp + '℃';
			               
			                var templow = document.getElementById(date+'_low');
			              
			                if(templow){
			                templow.appendChild(creatediv);
			                }
			                
			                creatediv = document.createElement('div');
			                creatediv.classList.add('weathervalue');
			                creatediv.setAttribute('id', date+'_icolow');
			                creatediv.innerHTML =   '<img src="https://openweathermap.org/img/wn/' +
			                weatherlist.weather[0].icon.substring(0, 2) +
			                'd@2x.png"/>';

			                temphigh = document.getElementById(date+'_low');
			              
			                if(temphigh){
				                temphigh.appendChild(creatediv);
				                }
			                
			                
		                }
		                if(time === "12:00:00"){
			                var creatediv = document.createElement('div');
			                creatediv.classList.add('weathervalue');
			                creatediv.setAttribute('id', date+'_temphigh');
			                creatediv.setAttribute('value', weatherlist.main.temp);
			                creatediv.textContent = weatherlist.main.temp + '℃';
			               
			                var temphigh = document.getElementById(date+'_high');
			              
			                if(temphigh){
			                temphigh.appendChild(creatediv);
			                }
			                
			                creatediv = document.createElement('div');
			                creatediv.classList.add('weathervalue');
			                creatediv.setAttribute('id', date+'_icohigh');
			                creatediv.innerHTML =   '<img src="https://openweathermap.org/img/wn/' +
			                weatherlist.weather[0].icon.substring(0, 2) +
			                'd@2x.png"/>'; 
			                temphigh = document.getElementById(date+'_high');
			              
			                if(temphigh){
				                temphigh.appendChild(creatediv);
				                }
			                
			                
			                
			                
		                }
/* 		                creatediv.textContent = 'Date: ' + weatherlist.dt_txt + ', Temperature: ' + weatherlist.main.temp
		                + ', desciption: ' + weatherlist.weather[0].description + ', icon: ' + weatherlist.weather[0].icon; */
		                

		            }
		            
		        }

		        // 페이지 로드 시 날씨 정보 표시 함수 호출
		        window.onload = displayWeatherInfo;

		    
		        
		        
</script>

			</div>

		</div>
		<!-- 날씨박스 -->

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



			 <nav aria-label="Page navigation example">
				<ul class="pagination" style = "justify-content: center;">
					<c:if test="${page > 1 }">
						<li class="page-item">
						<a class="page-link"
							href="main?page=${page-1}" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
					</c:if>

					<c:forEach var="a" begin="${startpage}" end="${endpage}">
				
							<li class="page-item"><a class="page-link"
								href="main?page=${a}">${a}</a></li>
					</c:forEach>


					<c:if test="${page < maxpage }">
						<li class="page-item"><a class="page-link" href="main?page=${page+1}"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a></li>
					</c:if>
				</ul>
</nav>


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