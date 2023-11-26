 function remove(type_name, post_id, page) {
 
 
 
    $.ajax({
      type: "POST",
      url: "remove",
      data: { type_name : type_name, post_id : post_id, page : page },
      success: function(result) {

        alert("삭제되었습니다.");

      },
      error: function(error) {
        console.error("삭제 중 오류 발생: ", error);
      }
    });
}
