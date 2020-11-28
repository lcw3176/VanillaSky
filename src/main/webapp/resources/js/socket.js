var ws;

window.addEventListener('load', function() {

	if ("WebSocket" in window) {
		ws = new SockJS("http://119.192.62.139:5048/vanillasky/gameroom");

		ws.onmessage = function(event) {
			writeResponse(event.data);
		}
	}

	else {
		alert("지원되지 않는 브라우저입니다.");
	}

});

function send() {
	if (document.getElementById("input-bar").value.trim() == "") {
		return;
	}

	var message = new Object();
	message.value = document.getElementById("input-bar").value;
	message.type = "text";

	if (message.value.includes("정답!")){
		message.detailType = "answer";
	}

	else {
		message.detailType = "chat";
	}


	ws.send(JSON.stringify(message));
	document.getElementById("input-bar").value = "";
}

function startAlarmToServer() {
	var message = new Object();
	message.type = "text";
	message.detailType = "gameStart";
	message.value = "good";

	ws.send(JSON.stringify(message));

}
function drawPointSend(x, y, mode) {

	var message = new Object();

	message.coorx = x;
	message.coory = y;
	message.detailType = mode;
	message.type = "point";

	ws.send(JSON.stringify(message));
}

function closeSocket() {
	ws.close();
}

function checkEnter() {
	if (event.keyCode == 13) {
		send();
	}
}


function writeResponse(text) {

	var obj = JSON.parse(text);

	if (obj.type == "text") {
		if (obj.detailType == "welcome") { //
			welcomeAction(obj);
		}

		else if (obj.detailType == "goodbye") { //
			goodbyeAction(obj);
		}

		else if (obj.detailType == "gameStart") {  // 11
			gameStartAction(obj);
		}

		else if (obj.detailType == "winner") { // 22
			winnerAction(obj);
		}

		else if (obj.detailType == "answer") { // 11
			answerAction(obj);
		}

		else if (obj.detailType == "changePresenter") { // 22
			changeAction(obj);
		}

		else if (obj.detailType == "chat") { // 22
			chatAction(obj);
		}
	}

	else if (obj.type == "point") {
		if (obj.detailType == "initDraw") {
			initDrawAction(obj);
		}

		else if (obj.detailType == "draw") {
			drawAction(obj);
		}

		else if (obj.detailType == "erase") {
			eraseAction(obj);
		}

		else if (obj.detailType == "clearDraw") {
			clearDrawAction(obj);
		}
	}

	document.getElementById("chatarea").scrollTop = document.getElementById("chatarea").scrollHeight

}

function welcomeAction(obj) {
	var myName = decodeURI(window.location.href.split('/')[4].split('\&')[1].split('=')[1]);
	var isPresenter = obj.isPresenter;
	var name = obj.userId;
	var ip = obj.userIp;

	if (isPresenter) {
		if (obj.userId == myName) {
			document.getElementById("button-list").style.display = "block";
			document.getElementById("input-text").style.display = "none";
		}
	}

	for (var i = 0; i < document.getElementsByClassName("user").length; i++) {
		if (document.getElementsByClassName("user")[i].id == name) {
			return;
		}
	}

	var tag = document.getElementById("leftaside");
	var ul = document.createElement("ul");
	ul.className = "user";
	ul.id = name;
	var h5 = document.createElement("h5");
	h5.innerHTML = "닉네임: " + name;
	var h6 = document.createElement("h6");
	h6.innerHTML = "IP: " + ip;

	tag.appendChild(ul);
	ul.appendChild(h5);
	ul.appendChild(h6);
}

function goodbyeAction(obj) {
	var name = obj.userId;

	var tag = document.getElementById("leftaside");
	tag.removeChild(document.getElementById(name));
}

function gameStartAction(obj) {
	var chat = document.getElementById("chatarea");
	chat.innerHTML += "\n" + obj.userId + " 님이 게임을 시작했습니다.";
}

function winnerAction(obj) {
	var chat = document.getElementById("chatarea");
	chat.innerHTML += "\n" + obj.userId + " 님이 정답을 맞췄습니다.";
}

function answerAction(obj) {
	var chat = document.getElementById("chatarea");
	chat.innerHTML += "\n" + obj.userId + " 님에게만 보이는 정답입니다.";
	chat.innerHTML += "\n" + obj.value + " (을)를 설명해 주세요.";
}

function changeAction(obj) {
	var myNameFromUrl = decodeURI(window.location.href.split('/')[4].split('\&')[1].split('=')[1]);

	if (obj.userId == myNameFromUrl) {
		document.getElementById("startButton").style.display = "inline";
		document.getElementById("startButton").style.backgroundColor = "red";
		document.getElementById("button-list").style.display = "block";
		document.getElementById("input-text").style.display = "none";
	}

	else {
		document.getElementById("button-list").style.display = "none";
		document.getElementById("input-text").style.display = "block";
		state.isDrawMode = false;
		state.isEraseMode = false;
	}
}

function initDrawAction(obj) {
	ctx.beginPath();
	ctx.moveTo(obj.coorx, obj.coory);
}

function drawAction(obj) {
	ctx.lineTo(obj.coorx, obj.coory);
	ctx.stroke();
}

function eraseAction(obj) {
	ctx.clearRect(obj.coorx - eraserSize, obj.coory - eraserSize,
		eraserSize * 2, eraserSize * 2);

}

function clearDrawAction(obj) {
	ctx.clearRect(0, 0, canvas.width, canvas.height);
	ctx.beginPath();
}

function chatAction(obj) {
	var sendUser = obj.userId;
	var inputMessage = obj.value;

	var chat = document.getElementById("chatarea");
	chat.innerHTML += "\n" + sendUser + ":" + inputMessage;
	document.getElementById("chatarea").scrollTop = document.getElementById("chatarea").scrollHeight

}

