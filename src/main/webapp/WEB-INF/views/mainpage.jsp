<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="java.util.*"%>
<%@ page import="com.ootd.weatherlook.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<title>메인페이지</title>
</head>
<body>

	메인페이지 출력 테스트

	<form>
		<table border="1" align="center">

			<tr>
				<th>지역</th>
				<td><select name="region">
						<option></option>
						<option>서울</option>
						<option>대전</option>
						<option>대구</option>
						<option>부산</option>
				</select></td>
				<th>기온</th>
				<td>value : 16.5C</td>
				<th>항목 스크롤</th>
				<td><input type="text" /></td>
			</tr>

		</table>

	</form>
	<br>

	<table>

		<tr>
			<td>오늘</td>
			<td>오전 10도</td>
			<td>해</td>
			<td>오후 20도</td>
			<td>달</td>
			<td>내일</td>
			<td>오전 10도</td>
			<td>해</td>
			<td>오후 20도</td>
			<td>달</td>
		</tr>
		<tr>
			<td>수요일</td>
			<td>오전 10도</td>
			<td>해</td>
			<td>오후 20도</td>
			<td>달</td>
			<td>목요일</td>
			<td>오전 10도</td>
			<td>해</td>
			<td>오후 20도</td>
			<td>달</td>
		</tr>

	</table>


	<br>

	<table border = "1">
	<c:forEach var="daily" items="${dailylist}">
		<tr>
			<td colspan="6">${daily.title }</td>
		</tr>
		<tr>
			<td colspan="6" height="50">사진</td>
		</tr>
		<tr>
			<td>지역</td>
			<td>위도 : ${daily.latitude } 경도 : ${daily.longitude }</td>
			<td>기온</td>
			<td>${daily.temperature }</td>
			<td>좋아요</td>
			<td>2개</td>
		</tr>
</c:forEach>
	</table>

</body>
</html>