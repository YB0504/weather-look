<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%-- <c:set var="lat" value="35.1018" />
<c:set var="lon" value="129.0403" /> --%>

<script>

function weathercall(lat, lon){
const apiUrl = 'http://api.openweathermap.org/data/2.5/daily';
/* const lat = '35.1028';  // 실제 위도 값으로 대체
const lon = '129.0403';  // 실제 경도 값으로 대체 */
const apiKey = '9657c91ee7eafcd506fa727097c899fa';  // 실제 API 키로 대체

const apiUrlWithParams = `${apiUrl}?lat=${lat}&lon=${lon}&appid=${apiKey}`;

fetch(apiUrlWithParams)
  .then(response => response.json())
  .then(data => {
    // API 응답을 처리하는 로직을 여기에 추가
    console.log(data);
  })
  .catch(error => {
    // API 호출 중 발생한 오류 처리
    console.error('API 호출 중 오류:', error);
  });
}

</script>


</body>
</html>