 function remove(type_name, post_id, page) {
    $.ajax({
      type: "POST",
      url: "remove", // 실제 서블릿 또는 컨트롤러 엔드포인트로 변경해야 합니다.
      data: { type_name : type_name, post_id : post_id, page : page },
      success: function(result) {
        // 삭제가 성공하면 행을 화면에서도 제거할 수 있습니다.
        alert("삭제되었습니다.");
        // 예를 들어, jQuery를 사용하여 행을 삭제할 수 있습니다.
        // $("#row_" + rowId).remove();
      },
      error: function(error) {
        console.error("삭제 중 오류 발생: ", error);
      }
    });
  }