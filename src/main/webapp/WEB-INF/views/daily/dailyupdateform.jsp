<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta content="width=device-width, initial-scale=1.0" name="viewport">

	<title>Forms / Elements - NiceAdmin Bootstrap Template</title>
	<meta content="" name="description">
	<meta content="" name="keywords">

	<!-- Favicons -->
	<link href="assets/img/favicon.png" rel="icon">
	<link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

	<!-- Google Fonts -->
	<link href="https://fonts.gstatic.com" rel="preconnect">
	<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	      rel="stylesheet">

	<!-- Vendor CSS Files -->
	<link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
	<link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
	<link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
	<link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
	<link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
	<link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

	<!-- Template Main CSS File -->
	<link href="assets/css/style.css" rel="stylesheet">
	<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/exif-js"></script>
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script>
        $(function () {
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

<jsp:include page="../include/header.jsp"/>

<main id="main" class="main">
	<section class="section col-6" style="margin: 0 auto">
		<div class="pagetitle">
			<h1>게시글 수정</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="dailylist">DailyLook</a></li>
				</ol>
			</nav>
		</div><!-- End Page Title -->
		<div class="row">
			<div style="margin: 0 auto">
				<div class="card" style="padding: 25px 10px 0 10px;">
					<div class="card-body">
						<form method=post action="dailyupdate" enctype="multipart/form-data">
							<input type="hidden" name="post_id" value="${daily.post_id }">
							<input type="hidden" name="page" value="${page }">
							<input type="hidden" name="nick" id="nick" value="${sessionScope.nick}"/>
							<input type="hidden" name="latitude" id="latitude"/>
							<input type="hidden" name="longitude" id="longitude"/>
							<input type="hidden" name="imageDate" id="imageDate"/>
							<div class="row mb-3">
								<label for="title" class="col-sm-2 col-form-label">제목</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="title" name="title" value="${daily.title}" required>
								</div>
							</div>
							<div class="row mb-3">
								<label for="content" class="col-sm-2 col-form-label">내용</label>
								<div class="col-sm-10">
									<textarea class="form-control" style="height: 300px" id="content" name="content" requireds>${daily.content}</textarea>
								</div>
							</div>
							<div class="row mb-3">
								<label for="thumbnailImg" class="col-sm-2 col-form-label">미리보기</label>
								<div class="col-sm-10">
									<img src="upload/${daily.daily_file}" id="thumbnailImg" style="max-width: 200px">
								</div>
							</div>
							<div class="row mb-3">
								<label for="content" class="col-sm-2 col-form-label">대표 이미지</label>
								<div class="col-sm-10">
									<input type="file" class="form-control" id="uploadFile" name="uploadFile">
								</div>
							</div>
							<div class="row mb-3">
								<div class="col-sm-2">
								</div>
								<div class="col-sm-8">
									<button type="submit" class="btn btn-primary" style="width: 100%">수정완료</button>
								</div>
								<div class="col-sm-2">
									<button type="button" class="btn btn btn-outline-primary" style="width: 100%" onclick="history.go(-1)">취소</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
</main><!-- End #main -->

<jsp:include page="../include/footer.jsp"/>

</body>

</html>s