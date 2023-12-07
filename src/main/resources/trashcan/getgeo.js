function getGeo(){
    $("#resultArea").html("");
    $.ajax({
        type: "GET",
        url: "http://map.ngii.go.kr/openapi/search.json",
        data: {
            target:"geo",
            apikey:"9A379F96C5C59BC8F788D9FAE09F554D",
            juso:"경기도 수원시 영통구 월드컵로 92"
        },
        dataType : "jsonp",
        crossDomain:true,
        success: function(result) {
            var search = result.search;
            var header = search.header;
            var responseCode = header.responseCode;
            var responseMessage = header.responseMessage;
            var totalCount = header.totalCount;

            if(responseCode!="0"&&responseCode!="100"){
                $("#resultArea").html(responseMessage);
            } else {
                var htmlStr = "";
                if(totalCount=="0"){
                    $("#resultArea").html("검색결과가 없습니다.");
                } else {
                    var geo = search.contents.geo;
                    htmlStr+="<table>";
                    htmlStr+="<tr>";
                    htmlStr+="<td>주소 타입</td><td>"+geo.adresTy+"</td>";
                    htmlStr+="</tr><tr>";
                    htmlStr+="<td>정제된 주소</td><td>"+geo.adres+"</td>";
                    htmlStr+="</tr><tr>";
                    htmlStr+="<td>건물관리번호</td><td>"+geo.bdMgtSn+"</td>";
                    htmlStr+="</tr><tr>";
                    htmlStr+="<td>PNU</td><td>"+geo.pnu+"</td>";
                    htmlStr+="</tr><tr>";
                    htmlStr+="<td>관심지점x좌표</td><td>"+geo.x+"</td>";
                    htmlStr+="</tr><tr>";
                    htmlStr+="<td>관심지점Y좌표</td><td>"+geo.y+"</td>";
                    htmlStr+="</tr>";
                    htmlStr+="</table>";
                    $("#resultArea").html(htmlStr);
                }
            }
          },
        error : function(xhr, ajaxSettings, thrownError){
        }
    });
}
