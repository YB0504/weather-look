<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 페이지</title>
 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
function delcheck(){
	var result = confirm("정말 삭제하시겠습니까?");
	if(result){
		location.href='commdelete?post_id=${comm.post_id}&page=${page}'	
	
		return false;
	}
	
}
function delfail(){
	alert("삭제 권한이 없습니다.");
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
 <script>
$(function(){
	
  $('#heart').click(function() {
  var heart = confirm("추천 하시겠습니까?");
  if(heart)  
		location.href='commlikeinsert?post_id=${comm.post_id}&nick=${sessionScope.nick}&page=${page}'  
  });
});
</script>
<script>
$(function(){
	
	$('#heart-fill').click(function(){
		var heartfill = confirm("추천을 취소하시겠습니까?");
		if(heartfill)
			location.href = 'commlikedelete?post_id={commlike.post_id}&nick=${commlike.nick}$page=${page}'
	});
});
</script>
<script>
	function updatebutton(){
		alert("수정 권한이 없습니다");
	}

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
 
 <c:choose>
 	<c:when test="${sessionScope.nick == comm.nick}">
			<input type="button" value="수정"
onClick="location.href='commupdateform?post_id=${comm.post_id}&page=${page}'">
 	</c:when>
 	<c:otherwise>
	<input type="button" value="수정"
onclick="updatebutton()">
		
 	</c:otherwise>
 </c:choose>
 
 
 <c:choose>
 <c:when test="${sessionScope.nick == comm.nick }">
			<input type="button" value="삭제" onClick="delcheck()"> 				
 </c:when>
 <c:otherwise>
			<input type="button" value="삭제" onClick="delfail()"> 				
 </c:otherwise>
 </c:choose>		 
</td>
</tr>
	</table><p>   
	<div>
 	session : ${sessionScope.nick }<br>
 	like_id : ${commlike.like_id }
 </div>
  <div align="center">
	<c:if test="${not empty commlike}">
		<i class="bi bi-heart-fill" style = "color : red" id="heart-fill"></i>	
	</c:if>      
	<c:if test="${empty commlike}">
        <i class="bi bi-heart" id="heart" ></i> 	
	</c:if>
</svg>
   
</div>
		<div align = "center">
		<form name="frm" id="frm">
			<input type="hidden" name="nick" value="${sessionScope.nick}">
			<input type="hidden" name="post_id" value="${comm.post_id}"> 댓글 :
			<textarea rows="3" cols="50" name="re_content"></textarea>
			<input type="button" value="확인" id="repInsert">
		</form>
		<div id = "slist"></div>
	</div>
</body>
</html>