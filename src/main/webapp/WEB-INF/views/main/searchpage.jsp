<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Page</title>
<link rel="stylesheet" href="css/searchpage.css">
<link rel="stylesheet" href="css/sunsearch.css">
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

	<h1>
		<a href="main">메인으로 가기</a>
	</h1>

	<h1>통합검색 페이지</h1>

	<header id="header">
		<h1 class="logo">
			<a href="index.html"><img src="images/logo.png" alt="logo"></a>
		</h1>
		<nav class="nav">
			<div class="menu">
				<ul class="main">
					<li><a href="dailylookmain.html">데일리룩</a></li>
					<li><a href="review.html">쇼핑후기</a></li>
					<li><a href="community.html">커뮤니티</a></li>
				</ul>
				<ul class="right">
					<li><a href="mypage.html"><img src="images/user.png"
							alt="search"></a></li>
					<li><a href="#" onclick="showPopup();"><img
							src="images/search.png" alt="mypage"></a></li>
				</ul>
				<ul class="bottom">
					<li><a href="login.html">로그인</a></li>
					<li><a href="joinform.html">회원가입</a></li>
				</ul>

			</div>
		</nav>
	</header>
	<section id="contents">
		<div class="s_1">
			<ul>
				<li><a href="#"><img src="images/search.png" alt="search"></a>
				</li>
				<li><input type="text"></li>
			</ul>
		</div>
		<div class="w_img"></div>
		<div class="s_2">
			<a href="#">${listcount }개의 글</a>
		</div>
			<table   class="sr">
			<tr>
				<td><a href="#">게시판</a></td>
				<td><a href="#">제목</a></td>
				<td><a href="#">작성자</a></td>
				<td><a href="#">조회수</a></td>
				<td><a href="#">좋아요</a></td>
				<td><a href="#">날짜</a></td>
			</tr>

		<c:forEach var="r" items="${searchresult }">
				<tr>
					<td>${r.type_name }</td>
					<td><a href=main> ${r.title } </a></td>
					<td>${r.nick }</td>
					<td>${r.read_count }</td>
					<td>${r.likes }</td>
					<td>${r.reg_date }</td>
				</tr>
		</c:forEach>
			</table>

	</section>
	<!-- contents -->




	<table align="center">
		<tr>
			<th>검색어</th>
			<td><input type="text" name="keyword" id="keyword"
				value="${keyword }"></td>
			<td><input type="button" name="search" id="search" value="검색"
				onClick="location.href='search?page=1&keyword='+document.getElementById('keyword').value"></td>
		</tr>
	</table>

	<!-- 키워드가 있으면 검색결과 갯수 표시하는 줄 출력하기 -->

	<table border="1" align="center">

		<tr>
			<th>게시판</th>
			<th>제목</th>
			<th>닉네임</th>
			<th>조회수</th>
			<th>작성일자</th>
			<th>좋아요</th>
		</tr>



		<c:forEach var="r" items="${searchresult }">
			<tr>
				<td>${r.type_name }</td>
				<td><a href=main> ${r.title } </a></td>
				<td>${r.nick }</td>
				<td>${r.read_count }</td>
				<td>${r.reg_date }</td>
				<td>${r.likes }</td>
			</tr>
		</c:forEach>

	</table>
	<div align="center">
		<c:if test="${page <=1 }">
				[이전]&nbsp;
			</c:if>

		<c:if test="${page > 1 }">
			<a href="search?page=${page-1}&keyword=${keyword}">[이전]</a>&nbsp;
			</c:if>

		<c:forEach var="a" begin="${startpage}" end="${endpage}">
			<c:if test="${a == page }">
					[${a}]
				</c:if>
			<c:if test="${a != page }">
				<a href="search?page=${a}&keyword=${keyword}">[${a}]</a>&nbsp;
				</c:if>
		</c:forEach>

		<c:if test="${page >= maxpage }">
				[다음] 
			</c:if>
		<c:if test="${page < maxpage }">
			<a href="search?page=${page+1}&keyword=${keyword}">[다음]</a>
		</c:if>
	</div>
</body>
</html>