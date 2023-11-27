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
	<section id="test">
		<h1>
			<a href="main">메인으로 가기</a>
		</h1>

		<h1>
			<a href="main">메인페이지 제작중</a>
		</h1>

		<h2>
			<a href="apitest">api테스트</a>
		</h2>
		<h2>
			<a href="maintest">main테스트</a>
		</h2>
		<h2>
			<a href="weather">이중dto테스트</a>
		</h2>
		<h2>
			<a href="report">신고글 관리페이지</a>
		</h2>

		<h2>지역 드롭박스, 기온 드롭박스</h2>




		<h3>
			<a href="search">검색창으로 이동!</a>
		</h3>
	</section>

	<!-- 구분선 ==================================================== -->

	<section id="container_all">

		<div class="re_f">
			<ul class="re_p">
				<li class="today"><a href="#">DAILYLOOK</a></li>
				<li class="write"><a href="write.html">글쓰기</a></li>
			</ul>
		</div>

		<div class="container_3x1">

			<div class="container_region">
				<select id="region">
					<option>지역1</option>
					<option>지역2</option>
					<option>지역3</option>
					<option>지역4</option>
				</select>
			</div>

			<div class="container_combination">
				<div class="container_2x3">
					<c:forEach begin="1" end="6" varStatus="i">
						<div class="weather_6days">날씨 들어갈 자리 레스고 ${i.index }</div>
					</c:forEach>
				</div>
			</div>


		</div>
		<div align="center">
			<div class="container_3x3">
				<c:forEach var="r" items="${mainlist}" varStatus="i">
					<form action="detail" method="get">
						<input type="hidden" id="type_name" name="type_name"
							value="${r.type_name }"> <input type="hidden"
							id="post_id" name="post_id" value="${r.post_id }">
						<div class="item_main">
							<a href="detail?type_name=${r.type_name}&post_id=${r.post_id}">
								<ul class="item_main_img">
									<li class="img">

										<div class="txt">
											<p class="p1">${r.title}
												<a href="profile.html"><img src="images/boy.png"
													alt="boy"></a>
											</p>
											<p class="p2">${r.type_name}게시판${r.nick}</p>
										</div> <!-- txt -->
									</li>
								</ul>
							</a>
						</div>
					</form>
				</c:forEach>
			</div>
		</div>
	</section>


	<div align="center">
		<c:if test="${page <=1 }">
				[이전]&nbsp;
			</c:if>

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


</body>
</html>