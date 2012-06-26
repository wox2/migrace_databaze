function httpResponse(event) {
	var httpRequest = event.target;
	if (httpRequest.readyState != 4) { return; }

	alert("Stav požadavku: "+httpRequest.status);
	var content = document.getElementById("content");
	content.innerHTML = httpRequest.responseText;
}

function menuClick(event) {
	alert("Bylo kliknuto na menu");
}

function odkazClick(event) {
	var odkaz = event.target;
	event.preventDefault();
	event.stopPropagation();
	
	var url = odkaz.href;
	var httpRequest = new XMLHttpRequest();
	httpRequest.open("GET", url, true);
	httpRequest.onreadystatechange = httpResponse;
	httpRequest.send(null);
}

function navesUdalosti() {
	var menu = document.getElementById("menu");
	menu.addEventListener("click", menuClick, false);
	
	var odkazy = menu.getElementsByTagName("a");
	for (var i=0;i<odkazy.length;i++) {
		odkazy[i].addEventListener("click", odkazClick, false);
	}
}
