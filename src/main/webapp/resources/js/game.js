
var state = {
	isEntered: false,
	isDrawMode: false,
	isEraseMode: false,
}
var canvas, ctx, drawButton;
const eraserSize = 20;


window.addEventListener('load', function() {
	canvas = document.getElementById("canvas");
	ctx = canvas.getContext("2d");

	canvas.addEventListener("mousedown", listener);
	canvas.addEventListener("mousemove", listener);
	canvas.addEventListener("mouseup", listener);
	canvas.addEventListener("mouseout", listener);

	drawButton = document.getElementById("drawButton");

});

function button_click(type) {
	/// 배경색 바꿔주기
	for (i = 0; i < 3; i++) {
		document.getElementsByClassName("button")[i].style.backgroundColor = "white";
		document.getElementsByClassName("button")[i].style.color = "black";
	}

	document.getElementById(type).style.backgroundColor = "gray";
	document.getElementById(type).style.color = "white";

	/// 기능 구현
	if (type == "clearButton") {
		ctx.clearRect(0, 0, canvas.width, canvas.height);
		ctx.beginPath();
		drawButton.click();

		drawPointSend(0, 0, "clearDraw");
	}
	else if (type == "eraseButton") {
		state.isDrawMode = false;
		state.isEraseMode = true;
	}

	else if (type == "drawButton") {
		state.isDrawMode = true;
		state.isEraseMode = false;
	}

	else if (type == "startButton") {
		if(document.getElementsByClassName("user").length < 2){
			alert("혼자서는 게임 진행이 불가능합니다.");
			return;
		}
		
		document.getElementById("startButton").style.display = "none";
		startAlarmToServer();
		drawButton.click();
	}
}

function listener(event) {

	switch (event.type) {
		case "mousedown":
			initDraw(event);
			break;

		case "mousemove":
			if (state.isEntered) {
				StartDraw(event);
			}
			break;

		case "mouseout":
			state.isEntered = false;
			break;

		case "mouseup":
			StopDraw();
			break;
	}
}

function initDraw(event) {
	ctx.beginPath();
	state.isEntered = true;
	var coors = getPosition(event);
	ctx.moveTo(coors.x, coors.y);

	drawPointSend(coors.x, coors.y, "initDraw");
}


function StartDraw(event) {
	var coors = getPosition(event);

	if (state.isDrawMode) {
		ctx.lineTo(coors.x, coors.y);
		ctx.stroke();

		drawPointSend(coors.x, coors.y, "draw");
	}

	if (state.isEraseMode) {
		ctx.clearRect(coors.x - eraserSize, coors.y - eraserSize, eraserSize * 2, eraserSize * 2);
		drawPointSend(coors.x, coors.y, "erase");
	}

}

function StopDraw() {
	state.isEntered = false;
}


function getPosition(event) {
	var x = event.pageX - canvas.offsetLeft;
	var y = event.pageY - canvas.offsetTop;

	return { x: x, y: y };
}