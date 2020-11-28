<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vanilla Sky</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/gameRoom.css">
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.min.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/game.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/socket.js"></script>

</head>

<body>
	<jsp:include page="navbar.jsp" />


	<div class="content">

		<li class="aside" id="leftaside"></li>


		<canvas id="canvas" class="canvas" width="1030" height="500">
            
		</canvas>

		<textarea rows="20" cols="25" id="chatarea" readonly>
			</textarea>


		<div class="button-list" id="button-list">
			<button class="button" id="clearButton"
				onclick="button_click('clearButton');">초기화</button>
			<button class="button" id="eraseButton"
				onclick="button_click('eraseButton');">지우개</button>
			<button class="button" id="drawButton"
				onclick="button_click('drawButton');">그리기</button>
			<button class="button" id="startButton"
				onclick="button_click('startButton');">게임 시작</button>
		</div>
		
		<div class="input-text" id="input-text">
			<div class="form-inline">
				<input class="form-control" id="input-bar" type="search"
					placeholder="Talk" onkeydown="checkEnter();">
				<button class="btn btn-outline-success my-2 my-sm-0"
					onclick="send();">말하기</button>
			</div>

		</div>

	</div>

</body>
</html>
