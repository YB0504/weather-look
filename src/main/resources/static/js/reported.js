 function remove(type_name, post_id, page) {
 
var confirmed = confirm("삭제하시겠습니까?");
 
  if (confirmed) {
  
    $.ajax({
      type: "POST",
      url: "remove",
      data: { type_name : type_name, post_id : post_id, page : page },
      success: function(result) {


      },
      error: function(error) {
        console.error("삭제 중 오류 발생: ", error);
      }
    });
    }
}
