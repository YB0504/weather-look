<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>마이페이지</title>
	<style>
        header {
            background-color: #333;
            color: white;
            padding: 10px;
            text-align: center;
        }

        .top-menu {
            background-color: #444;
            display: flex;
            justify-content: space-around;
            align-items: center;
            padding: 10px;
        }

        .top-menu a {
            color: white;
            text-decoration: none;
        }
	</style>
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script>
        $(function () {
            $("#deleteMemberLink").click(function () {
                return confirm("회원탈퇴 하시겠습니까");
            });
        });
	</script>
</head>
<body>

<header>
	<h1>마이 페이지</h1>
	<div class="top-menu">
		<a href="myPage" id="myPageLink">모아보기</a>
		<a href="memberUpdateForm" id="updateMemberLink">회원 정보 수정</a>
		<a href="deleteMember" id="deleteMemberLink">회원 탈퇴</a>
	</div>
</header>