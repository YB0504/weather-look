<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<title>Insert title here</title>
	<script type="text/javascript">
        $(function () {
            $('.edit1').click(function () {
                var id = $(this).attr('id');  // rno
                var txt = $('#p_' + id).text(); // replytext
                $('#p_' + id).html("<textarea rows='2' cols='80' class='form-control' id='tt_" + id + "'>" + txt + "</textarea>");
                $('#btn_' + id).html(
                    "<a class='btn btn-link edit1' id='${rb.re_id}' onclick='up(" + id + ")'>확인</a>"
                    + "<a class='btn btn-link' onclick='lst()'>취소</a>");
            });
        });

        function up(id) {
            var re_content = $('#tt_' + id).val();
            var formData = "re_id=" + id + '&re_content=' + re_content
                + "&post_id=${daily.post_id}";
            $.post('rdUpdate', formData, function (data) {
                $('#slist').html(data);
            });
        }

        function lst() {
            $('#slist').load('rdlist?post_id=${daily.post_id}');
        }

        function del(re_id, post_id) {
            if (confirm("댓글을 삭제하시겠습니까?")) {
                var formData = "re_id=" + re_id + "&post_id=" + post_id;
                $.post("rdDelete", formData, function (data) {
                    $('#slist').html(data);
                });
            }
        }
	</script>
</head>
<body>
<!--댓글-->
<div class="box-comments">
	<ul class="list-comments">
		<c:forEach var="rb" items="${rdlist}">
			<c:if test="${rb.re_level eq 0}">
				<li>
					<div class="comment-avatar">
						<img src="assets/img/product-3.jpg" alt="">
					</div>
					<div class="comment-details">
						<h4 class="comment-author" style="font-size: 13px">${rb.nick}</h4>
						<span class="date-span"><fmt:formatDate value="${rb.re_regdate }" pattern="yyyy-MM-dd"/></span>
						<p id="p_${rb.re_id}" class="comment-description">${rb.re_content}</p>
						<div id="btn_${rb.re_id}">
							<a class="btn btn-link">댓글</a>
							<c:if test="${rb.nick eq sessionScope.nick}">
								<a class="btn btn-link edit1" id="${rb.re_id}">수정</a>
								<a class="btn btn-link" onclick="del(${rb.re_id},${rb.post_id})">삭제</a>
							</c:if>
						</div>
					</div>
				</li>
			</c:if>
			<c:if test="${rb.re_level > 0}">
				<li class="comment-children">
					<div class="comment-avatar">
						<img src="assets/img/product-3.jpg" alt="">
					</div>
					<div class="comment-details">
						<h4 class="comment-author" style="font-size: 13px">${rb.nick}</h4>
						<span class="date-span"><fmt:formatDate value="${rb.re_regdate }" pattern="yyyy-MM-dd"/></span>
						<p id="p_${rb.re_id}" class="comment-description">${rb.re_content}</p>
						<div id="btn_${rb.re_id}">
							<a class="btn btn-link">댓글</a>
							<c:if test="${rb.nick eq sessionScope.nick}">
								<a class="btn btn-link edit1" id="${rb.re_id}">수정</a>
								<a class="btn btn-link" onclick="del(${rb.re_id},${rb.post_id})">삭제</a>
							</c:if>
						</div>
					</div>
				</li>
			</c:if>
		</c:forEach>
	</ul>
</div>
</body>
</html>