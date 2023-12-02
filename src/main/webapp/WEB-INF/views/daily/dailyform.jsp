<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/exif-js"></script>
	<script src="https://code.jquery.com/jquery-latest.js"></script>
	<script type="text/javascript">
        let imageDate;
        let latitude;
        let longitude;

        $(document).ready(function () {
            // 파일 선택 시 이벤트 핸들러 등록
            $("#uploadFile").change(function () {
                getGPS().then(() => {
                    $('#latitude').val(latitude);
                    console.log($('#latitude').val());
                    $('#longitude').val(longitude);
                    console.log($('#longitude').val());
                    $('#imageDate').val(imageDate);
                    console.log($('#imageDate').val());
                });
            });
        });

        function getGPS() {
            const fileInfo = document.getElementById("uploadFile").files[0];
            const reader = new FileReader();

            return new Promise((resolve, reject) => {
                reader.onload = function () {
                    EXIF.getData(fileInfo, () => {
                        const tags = EXIF.getAllTags(fileInfo);

                        for (let key in tags) {
                            if (key === 'DateTime') {
                                let dateString = tags[key];
                                var dateParts = dateString.split(" ");
                                var datePortion = dateParts[0];
                                var yearMonthDay = datePortion.split(":");
                                var year = yearMonthDay[0].slice(-2);
                                var month = ('0' + yearMonthDay[1]).slice(-2);
                                var day = ('0' + yearMonthDay[2]).slice(-2);

                                imageDate = year + month + day + '0900';
                                // console.log(imageDate);
                            }

                            if (key === 'GPSLatitude') {
                                let latitudeArr = tags[key];

                                if (latitudeArr && latitudeArr.length === 3) {
                                    latitude = latitudeArr[0] + latitudeArr[1] / 60 + latitudeArr[2] / 3600;
                                    // console.log('latitude : ' + latitude);
                                } else {
                                    console.error('Invalid latitude data');
                                }
                            }

                            if (key === 'GPSLongitude') {
                                let longitudeArr = tags[key];

                                if (longitudeArr && longitudeArr.length === 3) {
                                    longitude = longitudeArr[0] + longitudeArr[1] / 60 + longitudeArr[2] / 3600;
                                    // console.log('longitude : ' + longitude);
                                } else {
                                    console.error('Invalid longitude data');
                                }
                            }
                            resolve();
                        }
                    });
                    $("#thumbnailImg").attr("src", reader.result);
                };

                if (fileInfo) {
                    reader.readAsDataURL(fileInfo);
                } else {
                    reject(new Error('No file selected'));
                }
            });
        }
	</script>
</head>
<body>

<form method="post" action="dailywrite" enctype="multipart/form-data">
	<input type="hidden" name="nick" id="nick" value="${sessionScope.nick}"/>
	<input type="hidden" name="latitude" id="latitude"/>
	<input type="hidden" name="longitude" id="longitude"/>
	<input type="hidden" name="imageDate" id="imageDate"/>
	<table border=1 width=400 align=center>
		<caption>글 작성</caption>
		<tr>
			<th>제목</th>
			<td><input type=text name="title" required="required"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea cols=40 rows=5 name="content" required="required"></textarea></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<img src="#" id="thumbnailImg" alt="" style="max-width: 200px">
				<input type="file" id="uploadFile" name="uploadFile">
			</td>
		</tr>
		<tr>
			<td colspan=2 align=center>
				<input type=submit value="글 작성">
				<input type=button value="취소" onclick="location.href='dailylist'">
			</td>
		</tr>
	</table>
</form>

</body>
</html>