<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
function reportCheck() {
    console.log("${review.post_id}");
    if ($("#reason").val() == "") {
        alert("신고 사유를 선택하세요");
        return false;
    }
    return true; // 폼이 전송되도록 함
}
</script>
</head>
<body>
<div>
 
  <h2>신고하기</h2>
  <form method="post" action="reportSuccess" onsubmit="return reportCheck()">
  <input type="hidden" name="post_id" value="${review.post_id}">
   <table>
    <tr>
     <td>
     	<select id="reason" name="reason">
     		<option value = "">신고사유</option>
			<option value = "사유1">사유1</option>
			<option value = "사유2">사유2</option>
			<option value = "사유3">사유3</option>
			<option value = "사유4">사유4</option>
     	</select>
     </td>
    </tr>
   </table><p>
   <div>
    <input type="submit" value="신고하기">
    <!-- close함수로 팝업창 닫기 -->
    <input type="reset" value="취소" onclick="self.close();" />
   </div>
  </form>
 </div>
</body>
</html>