<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   
<title>Weatherlook Main</title>
<link rel="stylesheet" href="css/dailylookmain.css">
<link rel="stylesheet" href="css/sunmain.css">
<script src="js/jquery-1.12.4.min.js"></script>
<script src="js/script.js"></script>
<script lang="javascript">
	function showPopup() {
		window.open("search.html", "b",
				"width=700, height=400, left=200, top=200");
	}
</script>

</head>
<body>


	<!-- 구분선 ==================================================== -->



		<div class="3x3-container">
			<c:forEach var="r" items="${mainlist}" varStatus="i">
				<div class="main-item">
				왜 안되나 테스트 하러 왔다
				</div>
				<c:if test="${i.index % 3 == 2}">
					<div style="clear: both;"></div>
					3배수 테스트
				</c:if>
			</c:forEach>
		</div>

	<!-- 구분선 ==================================================== -->


</body>
</html>