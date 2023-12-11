<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>WeatherLook</title>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
function reportCheck() {
    if ($("#reason").val() == "") {
        alert("신고 사유를 선택하세요");
        return false;
    }
}
</script>
</head>
<body>
<div align="center">

<c:if test="${empty report}">
  <h2>신고하기</h2>
  <form method="post" action="commReReportIn" onsubmit="return reportCheck()">
  <input type="hidden" name="re_id" value="${re_id}">
   <table>
    <tr>
     <td>
     	<select id="reason" name="reason">
     		<option value = "">신고사유</option>
			<option value = "욕설/비방">욕설/비방</option>
			<option value = "광고/홍보">광고/홍보</option>
			<option value = "불법/도박">불법/도박</option>
			<option value = "사칭/도용">사칭/도용</option>
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
  </c:if>
  
    <c:if test="${!empty report}">
    <h2>신고 결과</h2>
    <table>
     <tr>
      <th>신고 : </th>
      <td>${report}</td>
     </tr>
    </table>
    <div>
    	<input type="button" value="닫기" onclick="self.close();" />
    </div>
  </c:if>
  
 </div>
</body>
</html>