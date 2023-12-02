<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function() {
		$('.edit1').click(function() { // 클래스값이 edit1으로 되어있는 값을 구해와라(수정버튼)
			var id  = $(this).attr('id');  // rno(this는 여러개수정버튼 중에 사용자가 선택한 수정버튼, id변수에 저장),id=번호값
			var txt = $('#td_'+id).text(); // replytext
			$('#td_'+id).html("<textarea rows='3' cols='30' id='tt_"+id+"'>"+txt
				+"</textarea>");
			$('#btn_'+id).html(
			   "<input type='button' value='확인' onclick='up("+id+")'> "
			  +"<input type='button' value='취소' onclick='lst()'>");
		});
	});
	function up(id) {	// 댓글 내용 수정
		var re_content = $('#tt_'+id).val();
		var formData = "re_id="+id+'&re_content='+re_content
			+"&post_id=${daily.post_id}";
		$.post('rdUpdate',formData, function(data) {
			$('#slist').html(data);
		});
	}
	function lst() {
		$('#slist').load('rdlist?post_id=${daily.post_id}')
	}
	function del(re_id,post_id) {
		var formData="re_id="+re_id+"&post_id="+post_id;
		$.post("rdDelete",formData, function(data) {
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
			<c:forEach var="rb" items="${rdlist}">
				<tr>
					<td>${rb.nick}</td>				<!-- 댓글 작성자명 -->
					<td id="td_${rb.re_id}">${rb.re_content}</td>	<!-- 댓글내용 자식테이블: rno -->
					<td>${rb.re_regdate }</td>
					<td id="btn_${rb.re_id}">
						<c:if test="${rb.post_id==daily.post_id }"> 
							<input type="button" value="수정" class="edit1" id="${rb.re_id}">
							<input type="button" value="삭제"	 onclick="del(${rb.re_id},${rb.post_id})">
						</c:if> 
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>