<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 정보 수정</title>
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="./js/member.js"></script>
	<script>
        $(function () {
            $("#address").val("${member.address}")

            $("#defaultImageButton").click(function () {
                changeDefaultImage();
            });

            $('#profile_image_form').change(function () {
                previewFile();
            });
        });

        function changeDefaultImage() {
            $("#profile_image").val("default.jpeg")
            $("#previewImg").attr("src", "upload/default.jpeg")
	        $("#profile_image_form").val("")
        }

        function previewFile() {
            $("#profile_image").val("")
            var fileInput = $('#profile_image_form')[0];
            var previewImg = $('#previewImg')[0];

            var file = fileInput.files[0];
            var reader = new FileReader();

            reader.onloadend = function () {
                previewImg.src = reader.result;
            };

            if (file) {
                reader.readAsDataURL(file);
            } else {
                previewImg.src = 'upload/default.jpeg';
            }
        }
	</script>
</head>
<body>

<form action="memberUpdate" method="post" enctype="multipart/form-data" onSubmit="return check()">
	<input type="hidden" name="admin_role" value="${member.admin_role}">
	<input type="hidden" name="token" value="${member.token}">
	<table border="1" width="400" align="center">
		<caption>회원 정보 수정</caption>
		<tr>
			<th>ID</th>
			<td>
				<input type="text" name="id" id="id" value="${member.id}" readonly>
			</td>
		</tr>
		<tr>
			<th>PASSWORD</th>
			<td>
				<input type="password" name="passwd" id="passwd">
			</td>
		</tr>
		<tr>
			<th>닉네임</th>
			<td>
				<input type="text" name="nick" id="nick" value="${member.nick}" readonly>
			</td>
		</tr>
		<tr>
			<th>프로필 사진</th>
			<td>
				<c:if test="${empty member.profile_image}">
					<img id="previewImg" src="/upload/default.jpeg" width="100" height="100">
				</c:if>
				<c:if test="${not empty member.profile_image}">
					<img id="previewImg" src="/upload/${member.profile_image}" width="100" height="100">
				</c:if>
				<input type="button" id="defaultImageButton" value="기본 이미지 선택">
				<input type="hidden" name="profile_image" id="profile_image">
				<input type="file" name="profile_image_form" id="profile_image_form">
			</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td>
				<input type="text" name="phone" id="phone" value="${member.phone}">
			</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>
				<%@ include file="../include/address.jsp" %>
				<select name="address" id="address">
					<option value="">지역선택</option>
					<c:forEach var="ad" items="${address}">
						<option value="${ad}">${ad}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="완료">
				<input type="button" value="취소" onclick="location.href='myPage'">
			</td>
		</tr>

	</table>
</form>

</body>
</html>