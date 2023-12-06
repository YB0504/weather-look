<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 페이지</title>
<!-- <script src="http://code.jquery.com/jquery-latest.js"></script> -->

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
	$(function() {

		$('#slist').load('rdlist?post_id=${daily.post_id}')

		$('#repInsert').click(function() {
			if (!frm.re_content.value) {
				alert('댓글을 입력 하세요.');
				frm.re_content.focus();
				return false;
			}
			var frmData = $('#frm').serialize();

			$.post('rdInsert', frmData, function(data) {
				//모델어트리뷰트 어노테이션 dto객체 생성해서 값 받을 수 있다.
				$('#slist').html(data); //새로 달린 댓글을 받아와서 slist 출력
				frm.re_content.value = '';
			});
		});

		// 좋아요 버튼이 안눌러져 있을때  클릭 : insertSQL문 실행
// 		$('#nogood').click(function() {
// 			alert("nogood");
			
// 			var frmData = $('#frm2').serialize();
// 			$.post('heartInsert', frmData, function(data) {
// 				alert("좋아요가 눌러졌습니다.");
// 				alert(data);
// 				if(data == 1){
// 					$("#nogood").attr("src","./img/good.png");	
// //					location.reload();		//  새로고침
					
// 					return false;
// 				}
// 			});			
// 		});
		
		
		
		// 좋아요 버튼이 눌러져 있을때  클릭 : updateSQL문 실행
// 		$('#good').click(function() {
			
// 			alert("good");

// 			var frmData = $('#frm2').serialize();
// 			$.post('heartUpdate', frmData, function(data) {
// 				alert("좋아요가 취소되었습니다.");				
// 				alert(data);
				
// 				if(data == 1){
// 					$("#good").attr("src","./img/nogood.png");	
// //					location.reload();
					
// 					return false;
// 				}
// 			});			
// 		});
		
	});
	
	function nogood(nick, post_id, page, state){
		if(state == 'y'){
			alert("좋아요 취소가 눌러졌습니다.");
		}else if(state == 'n'){
			alert("좋아요가 눌러졌습니다.");
		}
		
		location.href="heartInsert?nick="+nick+"&post_id="+post_id+"&page="+page+"&state="+state;
		
		return false;
	}
	
// 	function good1(nick, post_id, page){
// 		alert("좋아요가 취소되었습니다.");
		
// 		location.href="heartUpdate?nick="+nick+"&post_id="+post_id+"&page="+page;
		
// 		return false;
// 	}
	
	
</script>
</head>
<body>
	<table border=1 width=400 align="center">
		<caption>상세 페이지</caption>
		<tr>
			<td>작성자</td>
			<td>${daily.nick }</td>
		</tr>
		<tr>
			<td>날짜</td>
			<td><fmt:formatDate value="${daily.reg_date}"
					pattern="yyyy-MM-dd HH:mm:ss" /></td>
		</tr>
		<tr>
			<td>조회수</td>
			<td>${daily.read_count }</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${daily.title }</td>
		</tr>
		<tr>
			<td>내용</td>
			<td><pre>${daily.content }</pre></td>
		</tr>
		<c:if test="${not empty daily.daily_file}">
			<tr>
				<td>첨부파일</td>
				<td><pre>
						<img src="upload/${daily.daily_file}" width="200">
					</pre></td>
			</tr>
		</c:if>
		<tr>
			<td colspan=2 align=center><input type="button" value="목록"
				onClick="location.href='dailylist?page=${page}'"> <input
				type="button" value="수정"
				onClick="location.href='dailyupdateform?post_id=${daily.post_id}&page=${page}'">

				<input type="button" value="삭제" onclick="delcheck()"> <%--          <input type="button" value="삭제" onclick="location.href='dailydelete?post_id=${daily.post_id}&page=${page }'" >  --%>
			</td>
		</tr>
	</table>
	<!-- 댓글 처리 -->
	<form name="frm" id="frm">
		<input type="hidden" name="nick" value="${daily.nick}">
		<%-- <input type="hidden" name="nick" value="${sessionScope.nick}"> --%>
		<input type="hidden" name="post_id" value="${daily.post_id}">
		댓글 :
		<textarea rows="3" cols="50" name="re_content"></textarea>
		<input type="button" value="확인" id="repInsert">
	</form>
	
	<!-- 좋아요 처리 -->
	<form name="frm2" id="frm2">
		<input type="hidden" name="nick" value="${daily.nick}"> 
		<input type="hidden" name="post_id" value="${daily.post_id}"> 
	</form>

	<c:if test="${dailylike.like_check == 'n' }">  <!-- insertSQL문 실행 -->
		<img src="./img/nogood.png" width=50 height=50 id="nogood" onclick="nogood('${daily.nick}','${daily.post_id}','${page}','n')">
	</c:if>
	<c:if test="${dailylike.like_check == 'y' }">	<!--  updateSQL문 실행 -->
		<img src="./img/good.png" width=50 height=50 id="good" onclick="nogood('${daily.nick}','${daily.post_id}','${page}','y')">
	</c:if>

	<div id="slist"></div>
	</div>
</body>
</html>