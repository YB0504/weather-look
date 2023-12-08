function getReverseGeo(){

    $("#resultArea").html("");
    $.ajax({
        type: "GET",
        url: "http://map.ngii.go.kr/openapi/search.json",
        data: {
            target:"reverseGeo",
            apikey:"9A379F96C5C59BC8F788D9FAE09F554D",
            x: document.getElementById("latitude").value,
            y: document.getElementById("longitude").value
        },
        dataType : "jsonp",
        crossDomain:true,
        success: function(result) {
            var search = result.search;
            var header = search.header;
            var responseCode = header.responseCode;
            var responseMessage = header.responseMessage;

            if(responseCode!="0"&&responseCode!="100"){
                $("#resultArea").html(responseMessage);
            } else {
                var htmlStr = "";
                var jibun = search.contents.jibun;
                htmlStr+="<p>지번주소</p>";
                htmlStr+="<table><tr>";
                htmlStr+="<td>PNU</td><td>"+jibun.pnu+"</td>";
                htmlStr+="</tr><tr>";
                htmlStr+="<td>시/도/명</td><td>"+jibun.sidoName+"</td>";
                htmlStr+="</tr><tr>";
                htmlStr+="<td>시/군/구명</td><td>"+jibun.sigName+"</td>";
                htmlStr+="</tr><tr>";
                htmlStr+="<td>읍/면/동명</td><td>"+jibun.emdName+"</td>";
                htmlStr+="</tr><tr>";
                htmlStr+="<td>리명</td><td>"+jibun.liName+"</td>";
                htmlStr+="</tr><tr>";
                htmlStr+="<td>산</td><td>"+jibun.mntnYn+"</td>";
                htmlStr+="</tr><tr>";
                htmlStr+="<td>지번주소</td><td>"+jibun.jibunAdres+"</td>";
                htmlStr+="</tr><tr>";
                htmlStr+="<td>X좌표</td><td>"+jibun.x+"</td>";
                htmlStr+="</tr><tr>";
                htmlStr+="<td>Y좌표</td><td>"+jibun.y+"</td>";
                htmlStr+="</tr></table>";
                var road = search.contents.road;
                htmlStr+="<p>도로명주소</p>";
                htmlStr+="<table><tr>";
                htmlStr+="<td>bdMgtSn</td><td>"+road.bdMgtSn+"</td>";
                htmlStr+="</tr><tr>";
                htmlStr+="<td>시/도/명</td><td>"+road.sidoName+"</td>";
                htmlStr+="</tr><tr>";
                htmlStr+="<td>시/군/구명</td><td>"+road.sigName+"</td>";
                htmlStr+="</tr><tr>";
                htmlStr+="<td>읍/면/동명</td><td>"+road.emdName+"</td>";
                htmlStr+="</tr><tr>";
                htmlStr+="<td>도로명</td><td>"+road.roadName+"</td>";
                htmlStr+="</tr><tr>";
                htmlStr+="<td>건물본번</td><td>"+road.buldMnnm+"</td>";
                htmlStr+="</tr><tr>";
                htmlStr+="<td>건물부번</td><td>"+road.buldSlno+"</td>";
                htmlStr+="</tr><tr>";
                htmlStr+="<td>도로명주소</td><td>"+road.roadAdres+"</td>";
                htmlStr+="</tr><tr>";
                htmlStr+="<td>X좌표</td><td>"+road.x+"</td>";
                htmlStr+="</tr><tr>";
                htmlStr+="<td>Y좌표</td><td>"+road.y+"</td>";
                htmlStr+="</tr></table>";
                $("#resultArea").html(htmlStr);
            }
        },
        error : function(xhr, ajaxSettings, thrownError){
        }
    });
}
