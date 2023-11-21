<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Updated Layout</title>
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
</head>
<body>

<header>
	<h1>마이 페이지</h1>
	<div class="top-menu">
		<a href="mypage">모아보기</a>
		<a href="">메뉴 2</a>
		<a href="#">메뉴 3</a>
		<a href="#">메뉴 4</a>
	</div>
</header>