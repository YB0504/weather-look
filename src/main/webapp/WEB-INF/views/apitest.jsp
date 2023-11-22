<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-latest.js"></script>

<script>

var result;

function apitest() {
    $.ajax({
        url: "https://api.vworld.kr/req/address?",
        type: "GET",
        dataType: "jsonp",
        jsonp: "callback",
        data: {
            service: "address",
            request: "getaddress",
            version: "2.0",
            crs: "EPSG:4326",
            type: "ROAD",
            point: $("#point").val(),
            format: "json",
            errorformat: "json",
            key: "CE69BB2D-2546-3032-888A-540E22453ED5"
        },
        success: function(result) {
            console.log(result);

         

            // 주소 정보 추출
            var addressResult = result.response.result[0];

            // 우편번호 출력
            $("#zipcode").text(addressResult.zipcode);

            // 주소 유형 출력
            $("#addressType").text(addressResult.type);

            // 전체 주소 출력
            $("#alladdress").text(addressResult.text);
            
            document.getElementById("address").value = addressResult.text;
            
        },
        error: function(error) {
            console.error("에러 발생:", error);
        }
    });
}
function apitest2(){
$.ajax({
    url: "https://api.vworld.kr/req/address?",
    type: "GET",
    dataType: "jsonp",
    data: {
      service: "address",
      request: "GetCoord",
      version: "2.0",
      crs: "EPSG:4326",
      type: "ROAD",
      address: "서울특별시 강남구 봉은사로 524",
      format: "json",
      errorformat: "json",
      key: "CE69BB2D-2546-3032-888A-540E22453ED5"
    },
    success: function (result) {
      console.log(result);
    }
  })
}

</script>


</head>
<body>

<table border="1">
    <tr>
        <th>위치정보</th>
        <td><input type="text" name="point" id="point"></td>
    </tr>
    <tr>
        <td colspan="2" align="center"><input type="button" value="전송" onClick="apitest()"></td>
    </tr>
    <tr>
        <td colspan="2" align="center"><input type="button" value="2" onClick="apitest2()"></td>
    </tr>
    <tr>
        <th>주소값</th>
        <td><input type="text" name="address" id="address"></td>
    </tr>
</table>

<!-- "127.101313354,37.402352535" -->

</body>
</html>