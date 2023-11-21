<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.ootd.weatherlook.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">


	<title>마이페이지</title>
</head>
<body>
<%@include file="header.jsp"%>
<h1>마이페이지 출력 테스트</h1>
<h3>내가 작성한 글</h3>
<table border="1">
	<tr>
		<td>
			<div>번호</div>
		</td>
		<td>
			<div>제목</div>
		</td>
		<td>
			<div>조회</div>
		</td>
		<td>
			<div>날짜</div>
		</td>
		<td>
			<div>내용</div>
		</td>
		<td>
			<div>작성자</div>
		</td>
	</tr>
	<c:forEach var="board" items="${boardList}">
		<tr>
			<td>${board.post_id}</td>
			<td>${board.title}</td>
			<td>${board.read_count}</td>
			<td>${board.reg_date}</td>
			<td>${board.content}</td>
			<td>${board.nick}</td>
		</tr>
	</c:forEach>
</table>

<br>

<h3>내가 작성한 댓글</h3>
<table border="1">
	<tr>
		<td>
			<div>댓글 ID</div>
		</td>
		<td>
			<div>내용</div>
		</td>
		<td>
			<div>날짜</div>
		</td>
		<td>
			<div>원문 글 ID</div>
		</td>
		<td>
			<div>작성자</div>
		</td>
	</tr>
	<c:forEach var="comment" items="${commentList}">
		<tr>
			<td>${comment.re_id}</td>
			<td>${comment.re_content}</td>
			<td>${comment.re_regdate}</td>
			<td>${comment.post_id}</td>
			<td>${comment.nick}</td>
		</tr>
	</c:forEach>
</table>

<br>

<h3>내가 좋아요 한 글</h3>
<table border="1">
	<tr>
		<td>
			<div>좋아요 ID</div>
		</td>
		<td>
			<div>제목</div>
		</td>
		<td>
			<div>작성자</div>
		</td>
		<td>
			<div>원문 글 ID</div>
		</td>
		<td>
			<div>원문 글 게시판 ID</div>
		</td>
	</tr>
	<c:forEach var="like" items="${likeList}">
		<tr>
			<td>${like.like_id}</td>
			<td>${like.title}</td>
			<td>${like.writer_nick}</td>
			<td>${like.post_id}</td>
			<td>${like.type_id}</td>
		</tr>
	</c:forEach>
</table>

<br>

<h3>내가 스크랩 한 글</h3>
<table border="1">
	<tr>
		<td>
			<div>스크랩 ID</div>
		</td>
		<td>
			<div>제목</div>
		</td>
		<td>
			<div>작성자</div>
		</td>
		<td>
			<div>원문 글 ID</div>
		</td>
		<td>
			<div>원문 글 게시판 ID</div>
		</td>
	</tr>
	<c:forEach var="scrap" items="${scrapList}">
		<tr>
			<td>${scrap.scrap_id}</td>
			<td>${scrap.title}</td>
			<td>${scrap.writer_nick}</td>
			<td>${scrap.post_id}</td>
			<td>${scrap.type_id}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>