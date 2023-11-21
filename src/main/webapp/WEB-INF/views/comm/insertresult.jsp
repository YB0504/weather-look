<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:if test="${result == 1 }">
	<script>
		alert("글 작성 성공");
		location.href="commlist";
	</script>
</c:if>
<c:if test="${result != 1 }">
	<script >
		alert("글 작성 실패");
		history.go(-1);
	</script>
</c:if>

</body>
</html>