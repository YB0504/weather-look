<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<script src = "http://code.jquery.com/jquery-latest.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
	$(function() {
		$('.edit1').click(function() {
			var id  = $(this).attr('id');  // rno
			var txt = $('#td_'+id).text(); // replytext
			$('#td_'+id).html("<textarea rows='3' cols='30' id='tt_"+id+"'>"+txt
				+"</textarea>");
			$('#btn_'+id).html(
			   "<input type='button' value='확인' onclick='up("+id+")'> "
			  +"<input type='button' value='취소' onclick='lst()'>");
		});
	});
	function up(id) {
		var re_content = $('#tt_'+id).val();
		var formData = "re_id="+id+'&re_content='+re_content
			+"&post_id=${comm.post_id}";
		$.post('repUpdate',formData, function(data) {
			$('#slist').html(data);
		});
	}
	function lst() {
		$('#slist').load('crlist?post_id=${comm.post_id}');
	}
	function del(re_id,post_id) {
		var formData="re_id="+re_id+"&post_id="+post_id;
		$.post("repDelete",formData, function(data) {
			$('#slist').html(data);
		});
	}
</script>
</head>
<body>
	<div class="container" align="center">
		<h2 class="text-primary">댓글</h2>
		<table class="table table-bordered">
			<tr>
				<td>작성자</td>
				<td>내용</td>
				<td>수정일</td>
				<td></td>
			</tr>
			<c:forEach var="rb" items="${crlist}">
				<tr>
					<td>${rb.nick}</td>
					<td id="td_${rb.re_id}">${rb.re_content}</td>
					<td>
					<fmt:formatDate value="${rb.re_regdate }"
					 pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td id="btn_${rb.re_id}">
						<c:if test="${rb.post_id==comm.post_id }">
							<input type="button" value="수정" class="edit1" id="${rb.re_id}">
							<input type="button" value="삭제"	 onclick="del(${rb.re_id},${rb.post_id})">
						</c:if></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>