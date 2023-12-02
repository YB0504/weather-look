<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">


	<title>상세 페이지</title>
	<script>
        function delcheck() {

            var result = confirm("정말 삭제하시겠습니까?");
            if (result) {
                location.href = 'dailydelete?post_id=${daily.post_id}&page=${page}'

                return false;
            }
        }
	</script>

</head>
<body>

<table border=1 width=400 align="center">
	<caption>상세 페이지</caption>
	<tr>
		<td>작성자</td>
		<td>${daily.nick }</td>
	</tr>
	<tr>
		<td>날짜</td>
		<td>
			<fmt:formatDate value="${daily.reg_date}" pattern="yyyy-MM-dd HH:mm:ss"/>
		</td>
	</tr>
	<tr>
		<td>조회수</td>
		<td>${daily.read_count }</td>
	</tr>
	<tr>
		<td>제목</td>
		<td>${daily.title }</td>
	</tr>
	<tr>
		<td>내용</td>
		<td>
			<pre>${daily.content }</pre>
		</td>
	</tr>
	<c:if test="${not empty daily.daily_file}">
		<tr>
			<td>첨부파일</td>
			<td>
				<pre><img src="upload/${daily.daily_file}" width="200"></pre>
			</td>
		</tr>
	</c:if>
	<tr>
		<td colspan=2 align=center>
			<input type="button" value="목록"
			       onClick="location.href='dailylist?page=${page}'">

			<input type="button" value="수정"
			       onClick="location.href='dailyupdateform?post_id=${daily.post_id}&page=${page}'">

			<input type="button" value="삭제" onclick="delcheck()">
			<%--          <input type="button" value="삭제" onclick="location.href='dailydelete?post_id=${daily.post_id}&page=${page }'" >  --%>
		</td>
	</tr>
</table>

</body>
</html>