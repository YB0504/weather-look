function search{
	var keywordValue = document.getElementById('keyword').value;
	var searchUrl = 'search?page=1&keyword=' + encodeURIComponent(keywordValue);
	window.location.href = searchUrl;
}
