<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script src="/js/getreversegeo.js"></script>
</head>
<body>
	<table border="1">
		<tr>
			<th>위치정보</th>
			<td><input type="text" name="point" id="point"></td>
		</tr>
		<tr>
			<th>위도</th>
			<td><input type="text" name="latitude" id="latitude"></td>
		</tr>
		<tr>
			<th>경도</th>
			<td><input type="text" name="longitude" id="longitude"></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="button" value="전송"
				onClick="getReverseGeo()"></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="button" value="2"
				onClick="apitest2()"></td>
		</tr>
		<tr>
			<th>주소값</th>
			<td><input type="text" name="address" id="address"></td>
		</tr>
	</table>
</body>

<!-- 
            x:"962622.81338169",
            y:"1916772.2842511"
 -->
</html>