<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp"/>
<c:if test="${empty likeList}">
	</table>
	<br>
	좋아요한 글이 없습니다
</c:if>
<c:if test="${not empty likeList}">
	<c:set var="num" value="${likeCount-(page-1)*listSize}"/>
	<tbody>
	<c:forEach var="like" items="${likeList}">
		<tr>
			<td>
				<c:out value="${num}"/>
				<c:set var="num" value="${num-1}"/>
			</td>
			<td><a href="#">${like.title}</a></td>
			<td>${like.nick}</td>
			<td>
				<c:set var="now" value="<%=new java.util.Date()%>" />
				<fmt:formatDate value="${like.reg_date}" pattern="yyyy-MM-dd" type="Date"/>
			</td>
			<td>${like.read_count}</td>
		</tr>
	</c:forEach>
	</tbody>
	</table>

	<%--paging--%>
	<div id="myPagePagingDiv">
		<c:if test="${page <=1 }">
			[이전]&nbsp;
		</c:if>
		<c:if test="${page > 1 }">
			<a href="myLikeList?page=${page-1}">[이전]</a>&nbsp;
		</c:if>
		<c:forEach var="a" begin="${startPage}" end="${endPage}">
			<c:if test="${a == page }">
				[${a}]
			</c:if>
			<c:if test="${a != page }">
				<a href="myLikeList?page=${a}">[${a}]</a>&nbsp;
			</c:if>
		</c:forEach>
		<c:if test="${page >= totalPage }">
			[다음]
		</c:if>
		<c:if test="${page < totalPage }">
			<a href="myLikeList?page=${page+1}">[다음]</a>
		</c:if>
	</div>
</c:if>
<jsp:include page="footer.jsp"/>
