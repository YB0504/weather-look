<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!doctype html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<title>WeatherLook</title>
	<link href="ico/weatherico.png" rel="icon">
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

        $('.reply').click(function () {
            var reId = $(this).attr('id');
            var $replyForm = $('#replyForm_' + reId);

            // 이미 답글 입력 창이 존재하는지 확인
            if ($replyForm.length === 0) {
                var formHtml = '<li>';
                formHtml += '<div class="comment-details" id="replyForm_' + reId + '">';
                formHtml += '<input type="hidden" name="re_ref" value="' + reId + '">';
                formHtml += '<input type="hidden" name="re_level" value="0">';
                formHtml += '<input type="hidden" name="re_step" value="0">';
                formHtml += '<input type="text" placeholder="답글을 입력하세요" name="re_content">';
                formHtml += '<button class="btn btn-primary" onclick="dailyReplyInsert(' + reId + ')">등록</button>';
                formHtml += '<button class="btn btn-primary" onclick="cancelReply(' + reId + ')">취소</button>';  // 추가된 부분
                formHtml += '</div>';
                formHtml += '</li>';

                $(this).closest('li').after(formHtml);
                $('#replyForm_' + reId + ' input[name="re_content"]').focus();
            } else {
                // 이미 답글 입력 창이 존재하면 숨김
                $replyForm.toggle();

                $('#replyForm_' + reId + ' input[name="re_content"]').focus();
            }
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

        function cancelReply(reId) {
            $('#replyForm_' + reId).hide().find('input[name="re_content"]').val('');
            lst();
        }

        function dailyReplyInsert(reId) {
            var reContent = $('input[name="re_content"]').val();
            var reRef = $('input[name="re_ref"]').val();
            var reLevel = $('input[name="re_level"]').val();
            var reStep = $('input[name="re_step"]').val();
            var post_id = "${daily.post_id}";

            var formData = {
                "re_content": reContent,
                "re_ref": reRef,
                "re_level": reLevel,
                "re_step": reStep,
                "post_id": post_id
            };

            $.ajax({
                type: "POST",
                url: "dailyReplyInsert",
                data: formData,
                success: function (data) {
                    // 서버에서 반환한 데이터 처리 (댓글 목록 갱신)
                    $('#slist').html(data);
                },
                error: function (error) {
                    // 에러 처리
                    console.log("Error:", error);
                }
            });
        }

        function openReReportPopup(reId) {
            window.open('dailyReReport?re_id=' + reId, '신고하기', 'width=450,height=500');
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
					<div class="comment-details">
						<h4 class="comment-author" style="font-size: 13px">${rb.nick}</h4>
						<span class="date-span"><fmt:formatDate value="${rb.re_regdate }" pattern="yyyy-MM-dd"/></span>
						<p id="p_${rb.re_id}" class="comment-description">${rb.re_content}</p>
						<div id="btn_${rb.re_id}">
							<a class="btn btn-link reply" id="${rb.re_id}">댓글</a>
							<c:if test="${rb.nick eq sessionScope.nick}">
								<a class="btn btn-link edit1" id="${rb.re_id}">수정</a>
								<a class="btn btn-link" onclick="del(${rb.re_id},${rb.post_id})">삭제</a>
							</c:if>
							<c:if test="${rb.nick ne sessionScope.nick }">
								<a class="btn btn-link" onclick="openReReportPopup(${rb.re_id})">신고</a>
							</c:if>

						</div>
					</div>
				</li>
			</c:if>
			<c:if test="${rb.re_level > 0}">
				<li class="comment-children">
					<div class="comment-details">
						<h4 class="comment-author" style="font-size: 13px">${rb.nick}</h4>
						<span class="date-span"><fmt:formatDate value="${rb.re_regdate }" pattern="yyyy-MM-dd"/></span>
						<p id="p_${rb.re_id}" class="comment-description">${rb.re_content}</p>
						<div id="btn_${rb.re_id}">
							<c:if test="${rb.nick eq sessionScope.nick}">
								<a class="btn btn-link edit1" id="${rb.re_id}">수정</a>
								<a class="btn btn-link" onclick="del(${rb.re_id},${rb.post_id})">삭제</a>
							</c:if>
							<c:if test="${rb.nick ne sessionScope.nick }">
								<a class="btn btn-link" onclick="openReReportPopup(${rb.re_id})">신고</a>
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