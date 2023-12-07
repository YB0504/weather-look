function delcheck() {
    var result = confirm("정말 삭제하시겠습니까?");
    if (result) {
        location.href = 'commdelete?post_id=${comm.post_id}&page=${page}'

        return false;
    }
}

$(function () {
    $('#repInsert').click(function () {
        if (!frm.re_content.value) {
            alert('댓글을 입력하세요.');
            frm.re_content.focus();
            return false;
        }
        var frmData = $('form').serialize();

        $.post('crInsert', frmData, function (data) {
            $('#slist').html(data);
            frm.re_content.value = '';
        });
    });
});
