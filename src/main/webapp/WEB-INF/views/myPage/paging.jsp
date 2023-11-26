<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.ootd.weatherlook.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="myPagePagingDiv">
	<c:if test="${page <=1 }">
		[이전]&nbsp;
	</c:if>
	<c:if test="${page > 1 }">
		<a href="myPostList?page=${page-1}">[이전]</a>&nbsp;
	</c:if>
	<c:forEach var="a" begin="${startPage}" end="${endPage}">
		<c:if test="${a == page }">
			[${a}]
		</c:if>
		<c:if test="${a != page }">
			<a href="myPostList?page=${a}">[${a}]</a>&nbsp;
		</c:if>
	</c:forEach>
	<c:if test="${page >= totalPage }">
		[다음]
	</c:if>
	<c:if test="${page < totalPage }">
		<a href="myPostList?page=${page+1}">[다음]</a>
	</c:if>
</div>