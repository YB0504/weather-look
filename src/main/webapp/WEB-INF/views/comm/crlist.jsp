<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<!doctype html>
<html lang="ko">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<script src = "http://code.jquery.com/jquery-latest.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
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
		$.post('${path}/repUpdate',formData, function(data) {
			$('#slist').html(data);
		});
	}
	function lst() {
		$('#slist').load('crlist?post_id=${comm.post_id}');
	}
	function del(re_id,post_id) {
		var formData="re_id="+re_id+"&post_id="+post_id;
		$.post("${path}/repDelete",formData, function(data) {
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
					<td>${rb.re_regdate }</td>
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