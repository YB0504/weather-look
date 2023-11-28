<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 페이지</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>

<script>
function delcheck(){
	var result = confirm("정말 삭제하시겠습니까?");
	if(result){
		location.href='commdelete?post_id=${comm.post_id}&page=${page}'	
	
		return false;
	}
	
}
	
</script>

<script>
$(function(){
	
	$('#slist').load('crlist?post_id=${comm.post_id}')
	
	$('#repInsert').click(function(){
		if(!frm.re_content.value){
			alert('댓글을 입력하세요.');
			frm.re_content.focus();
			return false;
		}
		var frmData = $('form').serialize();
		
		$.post('crInsert', frmData, function(data){
			
			$('#slist').html(data);
			frm.re_content.value = '';
		});
	});
});
</script>

</head>
<body>

<table border = 1 width = 400  align = "center">
		<caption>게시글 상세정보</caption>
				<tr>
				<td>머리말</td>
				<td>
				<c:if test="${comm.category == 'question' }">질문</c:if>
				<c:if test="${comm.category == 'talk' }">수다</c:if>
				</td>
			</tr>
			<tr>
				<td>제목</td>
				<td>${comm.title}</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${comm.nick}</td>
			</tr>
			<tr>
				<td>조회수</td>
				<td>${comm.read_count}</td>
			</tr>			
			<tr>
				<td>날짜</td>
				<td>
				<fmt:formatDate value="${comm.reg_date }"
					pattern = "yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
			<tr>
				<td>내용</td>
				<td><pre>${comm.content}</pre></td>
			</tr>
			<tr>
			<td colspan =2 align = center>
			<input type="button" value="목록"
onClick="location.href='commlist?page=${page}'"	>
 
			<input type="button" value="수정"
onClick="location.href='commupdateform?post_id=${comm.post_id}&page=${page}'">
			 
			<input type="button" value="삭제" onClick="delcheck()"> 				
</td>
</tr>
		</table><p>
		<div align = "center">
		<form name="frm" id="frm">
			<input type="hidden" name="nick" value="${comm.nick}">
			<input type="hidden" name="post_id" value="${comm.post_id}"> 댓글 :
			<textarea rows="3" cols="50" name="re_content"></textarea>
			<input type="button" value="확인" id="repInsert">
		</form>
		<div id = "slist"></div>
	</div>
</body>
</html>