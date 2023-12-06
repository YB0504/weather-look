<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>상세 페이지</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
	<script>
        $(function(){
            $('#heart').click(function() {
                var heart = confirm("추천 하시겠습니까?");
                if(heart){
                    location.href='likeInsert?post_id=${review.post_id}&nick=${sessionScope.nick}&page=${page}'
                }
            });

            $('#heart_fill').click(function() {
                var heart = confirm("추천을 취소하시겠습니까?");
                if(heart){
                    location.href='likeDelete?post_id=${review.post_id}&page=${page}&like_id=${like.like_id}'
                }
            });

            $('#scrap').click(function() {
                var heart = confirm("스크랩 하시겠습니까?");
                if(heart){
                    location.href='scrapInsert?post_id=${review.post_id}&nick=${sessionScope.nick}&page=${page}'
                }
            });

            $('#scrap_fill').click(function() {
                var heart = confirm("스크랩을 취소하시겠습니까?");
                if(heart){
                    location.href='scrapDelete?post_id=${review.post_id}&page=${page}&scrap_id=${scrap.scrap_id}'
                }
            });
        });
	</script>
</head>
<body>
<div align="center">
<table border=1 width=400>
	<caption>상세 페이지</caption>
	<tr>
		<td>작성자</td>
		<td>${review.nick }</td>
	</tr>
	<tr>
		<td>제목</td>
		<td>${review.title }</td>
	</tr>
	<tr>
		<td>좋아요</td>
		<td>
			<c:if test="${empty like}">
				<i id="heart" class="bi bi-heart"></i>
			</c:if>
			<c:if test="${not empty like}">
				<i id="heart_fill" class="bi bi-heart-fill" style="color: red"></i>
			</c:if>
		</td>
	</tr>
	<tr>
		<td>스크랩</td>
		<td>
			<c:if test="${empty scrap}">
				<i id="scrap" class="bi bi-bookmark"></i>
			</c:if>
			<c:if test="${not empty scrap}">
				<i id="scrap_fill" class="bi bi-bookmark-fill" style="color: dodgerblue"></i>
			</c:if>
		</td>
	</tr>
	<tr>
		<td colspan=2 align=center>
			<input type="button" value="목록"
			       onClick="location.href='reviewList?page=${page}'">
		<c:if test="${not empty sessionScope.nick and sessionScope.nick eq review.nick}">
			<input type="button" value="수정"
			       onClick="location.href='reviewUpdateForm?post_id=${review.post_id}&page=${page}'">
			<input type="button" value="삭제" onclick="delcheck()">
		</c:if>
		<c:if test="${not empty sessionScope.nick and sessionScope.nick ne review.nick}">
			<input type="button" value="신고" onclick="openReportPopup(${review.post_id})">
		</c:if>
		</td>
	</tr>
</table><br>
</div>
</body>
</html>