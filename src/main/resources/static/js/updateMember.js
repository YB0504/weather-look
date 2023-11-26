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