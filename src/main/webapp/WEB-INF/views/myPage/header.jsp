<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ko">
<!DOCTYPE html>
<%--head--%>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>웨더룩</title>
	<link rel="stylesheet" href="css/mypage.css">
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="js/script.js"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
	<script>
        $(function () {
            $("#deleteMemberLink").click(function () {
                return confirm("회원탈퇴 하시겠습니까");
            });
        });
	</script>
</head>

<%--body--%>
<body>
<div id="wrap">
	<header id="header">
		<div class="head">
			<ul class="my_head">
				<li class="logo">
					<a href="index.html"><img src="images/logo.png" alt="logo"></a>
				</li>
				<li class="my_p">
					<a href="#">MY PAGE</a>
				</li>
			</ul>
		</div>
	</header>
	<section id="contents">
		<div class="f_pro">
			<ul class="f_pro1">
				<li class="f_pro01"><img src="images/user.png" alt="user"></li>
				<li class="f_pro03"><a href="myPostList">작성글</a></li>
				<li class="f_pro03"><a href="updateMemberForm">회원정보</a></li>
				<li class="f_pro06" id="deleteMemberLink"><a href="deleteMember">회원탈퇴</a></li>
			</ul>
			<div class="inner-content">
				<div class="row">
					<div class="col-md-12"> <!-- s_pro -->
						<div class="s_pro">
							<ul class="s_pro1">
								<li><a href="myPostList">작성글</a></li>
								<li><a href="myReplyList">댓글</a></li>
								<li><a href="myLikeList">좋아요</a></li>
								<li><a href="myScrapList">스크랩</a></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<table class="table table-striped table-hover">
							<thead>
							<th class="th-1">번호</th>
							<th class="th-2">제목</th>
							<th class="th-3">작성자</th>
							<th class="th-4">날짜</th>
							<th class="th-5">조회수</th>
							</thead>