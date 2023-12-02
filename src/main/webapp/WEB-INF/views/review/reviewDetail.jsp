<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>상세 페이지</title>
	<script>
        function delcheck() {

            var result = confirm("정말 삭제하시겠습니까?");
            if (result) {
                location.href = 'dailydelete?post_id=${daily.post_id}&page=${page}'

                return false;
            }
        }
	</script>
	<script>
        $(function () {

            $('#slist').load('rdlist?post_id=${daily.post_id}')

            $('#repInsert').click(function () {
                if (!frm.re_content.value) {
                    alert('댓글을 입력 하세요.');
                    frm.re_content.focus();
                    return false;
                }
                var frmData = $('form').serialize();

                $.post('rdInsert', frmData, function (data) {
                    //모델어트리뷰트 어노테이션 dto객체 생성해서 값 받을 수 있다.
                    $('#slist').html(data);	//새로 달린 댓글을 받아와서 slist 출력
                    frm.re_content.value = '';
                });
            });
        });
        
    	// 신고 팝업 열기
    	function openReportPopup() {
    		window.open("sendReport","신고하기","width=450,height=500");
    	}

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
		<td>날짜</td>
		<td>
			<fmt:formatDate value="${review.reg_date}" pattern="yyyy-MM-dd HH:mm:ss"/>
		</td>
	</tr>
	<tr>
		<td>조회수</td>
		<td>${review.read_count }</td>
	</tr>
	<tr>
		<td>제목</td>
		<td>${review.title }</td>
	</tr>
	<tr>
		<td>내용</td>
		<td>
			<pre>${review.content }</pre>
		</td>
	</tr>
	<c:if test="${not empty review.review_file}">
		<tr>
			<td>첨부파일</td>
			<td>
				<pre><img src="upload/${review.review_file}" width="200"></pre>
			</td>
		</tr>
	</c:if>
	<tr>
		<td colspan=2 align=center>
		<%-- <c:if test="${sessionScope.nick eq review.nick}"> --%>
			<input type="button" value="목록"
			       onClick="location.href='reviewList?page=${page}'">
			<input type="button" value="수정"
			       onClick="location.href='reviewUpdateForm?post_id=${review.post_id}&page=${page}'">
			<input type="button" value="삭제" onclick="delcheck()">
			<input type="button" value="신고" onclick="openReportPopup()">
		<%-- </c:if> --%>
		<%-- <c:if test="${empty sessionScope.nick and sessionScope.nick ne review.nick}">
			<input type="button" value="목록"
			       onClick="location.href='reviewList?page=${page}'">
			<input type="button" value="신고" onclick="openReportPopup()">
		</c:if> --%>
		</td>
	</tr>
</table><br>
<form name="frm" id="frm">
	<input type="hidden" name="nick" value="${review.nick}">
	<%-- 			<input type="hidden" name="nick" value="${sessionScope.nick}"> --%> 
	<input type="hidden" name="post_id" value="${review.post_id}"> 댓글 :
	<textarea rows="3" cols="50" name="re_content"></textarea>
	<input type="button" value="확인" id="repInsert">
</form>
	<div id="slist"></div>
</div>
</body>
</html>