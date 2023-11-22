function apitest() {
    $.ajax({
        url: "https://api.vworld.kr/req/address?",
        type: "GET",
        dataType: "jsonp",
        data: {
            service: "address",
            request: "getaddress",
            version: "2.0",
            crs: "EPSG:4326",
            type: "ROAD",
            point: $("#point"),
            format: "json",
            errorformat: "json",
            key: "CE69BB2D-2546-3032-888A-540E22453ED5"
        },
        success: function (result) {
            console.log(result);
        }
    });
}

