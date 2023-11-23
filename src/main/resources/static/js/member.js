
 function check(){
	 if($.trim($("#id").val())==""){
		 alert("회원아이디를 입력하세요!");
		 $("#id").val("").focus();
		 return false;
	 }
	 if($.trim($("#passwd").val())==""){
		 alert("회원비번을 입력하세요!");
		 $("#passwd").val("").focus();
		 return false;
	 }
	 if($.trim($("#nick").val())==""){
		 alert("별명을 입력하세요!");
		 $("#nick").val("").focus();
		 return false;
	 }
	 if($.trim($("#phone").val())==""){
		 alert("전화번호를 입력하세요!");
		 $("#phone").val("").focus();
		 return false;
	 }
	 if($("#address").val()==""){
		 alert("주소를 선택하세요!");
		 return false;
	 } 	 
 }
 
 /* 아이디 중복 체크*/
function idcheck() {
    $("#idcheck").hide();
    var memid = $("#id").val();
    
    if($.trim($("#id").val())==""){
		 alert("회원아이디를 입력하세요!");
		 $("#id").val("").focus();
		 return false;
	 } 
    
    // 올바른 이메일 형식
        $.ajax({
            url: 'idcheck',
            type: 'POST',
            data: { "memid": memid },
            success: function (data) {
                if (data == 1) {
                    var newtext = '<font color="red">중복 아이디입니다.</font>';
      					$("#idcheck").text('');
        				$("#idcheck").show();
        				$("#idcheck").append(newtext);
                    	$("#id").val('').focus();
                    	return false;
                } else {
      				var newtext='<font color="blue">사용가능한 아이디입니다.</font>';
      					$("#idcheck").text('');
      					$("#idcheck").show();
      					$("#idcheck").append(newtext);
      					$("#passwd").focus();
                }
            },
            error: function () {
                // 에러 처리
                console.error('이메일 중복 확인 중 오류 발생');
            }
        }); // ajax 끝
}	// ID중복체크 끝
 
 /* 닉네임 중복 체크*/
function nickcheck() {
    $("#nickcheck").hide();
    var memnick = $("#nick").val();    
    
    if($.trim($("#nick").val())==""){
		 alert("별명 입력하세요!");
		 $("#nick").val("").focus();
		 return false;		 
	 } 
    
    // 올바른 이메일 형식
        $.ajax({
            url: 'nickcheck',
            type: 'POST',
            data: { "memnick": memnick },
            success: function (data) {
                if (data == 1) {
                    var newtext = '<font color="red">중복된 별명입니다.</font>';
      					$("#nickcheck").text('');
        				$("#nickcheck").show();
        				$("#nickcheck").append(newtext);
                    	$("#nick").val('').focus();
                    	return false;
                } else {
      				var newtext='<font color="blue">사용가능한 별명입니다.</font>';
      					$("#nickcheck").text('');
      					$("#nickcheck").show();
      					$("#nickcheck").append(newtext);
      					$("#phone").focus();
                }
            },
            error: function () {
                // 에러 처리
                console.error('닉네임 중복 확인 중 오류 발생');
            }
        }); // ajax 끝
}	// NICK중복체크 끝









