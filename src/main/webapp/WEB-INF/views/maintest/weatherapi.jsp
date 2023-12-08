<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Insert title here</title>


<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

</head>
<body>

	<%-- <c:set var="lat" value="35.1018" />
<c:set var="lon" value="129.0403" /> --%>

	<div id="1">text1</div>
	<div id="2">text2</div>
	<div id="3">text3</div>
	<div id="4">text4</div>

	<div id="weather"></div>

	<c:set var="lat" value="37.5683" />
	<c:set var="lon" value="127" />



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
		            


		          
		            
		            	var num = 1;
		            for (var i = 0; i < data.list.length; i++) {
		            var weather = document.getElementById(num);
		            	
		                var weatherlist = data.list[i];

		                var dateTimeParts = weatherlist.dt_txt.split(' '); // 날짜와 시간을 분리
		                var date = dateTimeParts[0];
		                var time = dateTimeParts[1];
		                
		                if(time === "12:00:00" || time === "21:00:00"){
		                	
			                var weatherdaydiv = document.createElement('div');
			                weatherdaydiv.classList.add('day');
			                weatherdaydiv.textContent = 'Date: ' + weatherlist.dt_txt + ', Temperature: ' + weatherlist.main.temp
			                + ', desciption: ' + weatherlist.weather[0].description + ', icon: ' + weatherlist.weather[0].icon;
			                
			                weather.appendChild(weatherdaydiv);
		             
		                num++;
		                }
		                

		            }
		        }

		        // 페이지 로드 시 날씨 정보 표시 함수 호출
		        window.onload = displayWeatherInfo;

</script>






</body>
</html>