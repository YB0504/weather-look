function reversegeo(){
var lat = document.getElementById("latitude").value;
console.log(lat);
var lng = document.getElementById("longitude").value;
console.log(lng);

var key = "a613ku0t75";


$.ajax({
  url: "https://map.naver.com/api/geocode.json?latlng=" + lat + "," + lng + "&key=" + key,
  type: "GET",
  dataType: "json",
    headers: {
    "Access-Control-Allow-Origin": "*"
  },
  success: function(data) {
    var address = data.result.address;
    console.log(address);
  },
  error: function(error) {
    console.log(error);
  }
});
}