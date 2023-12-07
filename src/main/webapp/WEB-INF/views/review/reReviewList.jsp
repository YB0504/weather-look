<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-latest.js"></script>
    <style>
        /* 숨겨진 입력 창의 스타일 */
        .reply-form {
            display: none;
            margin-top: 10px;
        }
            /* 댓글 및 답글의 들여쓰기 스타일 */
    	.comment {
			border-bottom: 1px solid #ddd; /* 댓글 사이에 구분선 추가 */
        	padding: 10px;
    	}

	    .reply-level-1 {
    	    margin-left: 20px; /* 첫 번째 답글은 20px 들여쓰기 */
   		}

    	.reply-level-2 {
        	margin-left: 40px; /* 두 번째 답글은 40px 들여쓰기 */
    	}
    </style>
<script type="text/javascript">

	$(function() {
		$('.edit1').click(function() {
			var id  = $(this).attr('id');
			var txt = $('#td_'+id).text();
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
			+"&post_id=${review.post_id}";
		$.post('reUpdate',formData, function(data) {
			$('#slist').html(data);
		});
	}
	
	function lst() {
		$('#slist').load('reReviewList?post_id=${review.post_id}')
	}
	
	function del(re_id,post_id) {
		var formData="re_id="+re_id+"&post_id="+post_id;
		$.post("reDelete",formData, function(data) {
			$('#slist').html(data);
		});
	}
	
	// 댓글의 답글버튼 클릭 시
    $('.reply').click(function() {
        var reId = $(this).attr('id');
        var $replyForm = $('#replyForm_'+reId);

        if ($replyForm.length === 0) {
            var formHtml = '<tr class="reply-form" id="replyForm_'+reId+'">';
            formHtml += '<td colspan="4">';
            formHtml += '<div>';
            formHtml += '<input type="hidden" name="re_ref" value="' +reId+ '">';
            formHtml += '<input type="hidden" name="re_level" value="0">';
            formHtml += '<input type="hidden" name="re_step" value="0">';
            formHtml += '<input type="text" placeholder="답글을 입력하세요" name="re_content">';
            formHtml += '<button class="btn btn-primary" onclick="reReplyInsert('+reId+')">등록</button>';
            formHtml += '</div>';
            formHtml += '</td>';
            formHtml += '</tr>';

            $(this).closest('tr').after(formHtml);
            $('#replyForm_'+reId+' input[name="re_content"]').focus();
        } else {
            $replyForm.toggle();
            $('#replyForm_'+reId+' input[name="re_content"]').focus();
        }
    });
    
    function reReplyInsert(reId) {
        var reContent = $('input[name="re_content"]').val();
        var reRef = $('input[name="re_ref"]').val();
        var reLevel = $('input[name="re_level"]').val();
        var reStep = $('input[name="re_step"]').val();
        var post_id = "${review.post_id}";

        var formData = {
            "re_content": reContent,
            "re_ref": reRef,
            "re_level": reLevel,
            "re_step": reStep,
            "post_id": post_id
        };

        $.ajax({
            type: "POST",
            url: "reReplyInsert",
            data: formData,
            success: function(data) {
                // 서버에서 반환한 데이터 처리 (댓글 목록 갱신)
                $('#slist').html(data);
            },
            error: function(error) {
                // 에러 처리
                console.log("Error:", error);
            }
        });
    }
    
    // 신고 팝업 열기
	function openReReportPopup(reid) {
		window.open('sendReReport?re_id=' + reid, '신고하기', 'width=450,height=500');
	}
    
</script>
</head>
<body>
	<div align="center">
		<h2>댓글</h2>
		<table border="1">
			<tr>
				<td>작성자</td>
				<td>내용</td>
				<td>수정일</td>
				<td></td>
			</tr>
			<c:forEach var="rb" items="${reReviewList}">
				<tr class="comment reply-level-${rb.re_level}">
					<td>${rb.nick}</td>				
					<td id="td_${rb.re_id}">${rb.re_content}</td>	
					<td>${rb.re_regdate }</td>
					<td id="btn_${rb.re_id}">
					<c:if test="${rb.re_level eq 0}">
						<input type="button" value="답글" class="reply" id="${rb.re_id}">
					</c:if>
						<c:if test="${rb.nick eq sessionScope.nick }">
							<input type="button" value="수정" class="edit1" id="${rb.re_id}">
							<input type="button" value="삭제"	 onclick="del(${rb.re_id},${rb.post_id})">
						</c:if>
						<c:if test="${rb.nick ne sessionScope.nick }">
							<input type="button" value="신고" onclick="openReReportPopup(${rb.re_id})">
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>