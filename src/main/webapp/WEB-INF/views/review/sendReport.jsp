<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
 
  <h2>비번찾기</h2>
  <form method="post" action="reportSuccess" onsubmit="self.close();">  
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
   </table>
   <div>
    <input type="submit" value="신고하기">
    <!-- close함수로 팝업창 닫기 -->
    <input type="reset" value="취소" onclick="self.close();" />
   </div>
  </form>
 </div>
</body>
</html>