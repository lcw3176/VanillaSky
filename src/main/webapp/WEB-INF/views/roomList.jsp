<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Vanilla Sky</title>
<link rel="stylesheet" href="resources/css/roomList.css">
</head>

<script>
	// IE 에러나서 넣음
	function encodeParams(roomName, userId) {
		location.href = "gameroom?roomName=" + encodeURI(roomName) + "&id="
				+ encodeURI(userId);
	}
</script>

<body>
	<jsp:include page="navbar.jsp" />

	<div class="roomList">
		<a href="makeroom"><button type="button" class="btn btn-primary"
				id="makeRoomButton">방 만들기</button></a>

		<table class="table">
			<thead>
				<tr>
					<th scope="col"></th>
					<th scope="col">방제목</th>
					<th scope="col">인원</th>
					<th scope="col">상태</th>
				</tr>
			</thead>
			<c:if test="${! empty roomList }">
				<tbody>
					<c:forEach var="room" items="${roomList }" varStatus="status">
						<tr>
							<th scope="row">${status.count}</th>

							<c:choose>
								<c:when test="${room.running }">
									<td>${room.roomName }</td>
									<td>${room.nowNumber}/${room.maxNumber }</td>
									<td style="color: red;">진행중</td>

								</c:when>
								<c:when test="${not room.running }">
									<c:if test="${room.nowNumber == room.maxNumber  }">
										<td>${room.roomName }</td>
										<td>${room.nowNumber}/${room.maxNumber }</td>
										<td>대기중</td>
									</c:if>

									<c:if test="${room.nowNumber != room.maxNumber  }">
										<td><a href="#"
											onclick="encodeParams('${room.roomName}', '${userId}')">${room.roomName }</a></td>
										<td>${room.nowNumber}/${room.maxNumber }</td>
										<td>대기중</td>
									</c:if>
								</c:when>
							</c:choose>
						</tr>
					</c:forEach>
				</tbody>
			</c:if>
		</table>
		<!-- <a
											href="<c:url value="/gameroom?roomName=${room.roomName}&id=${userId}" />">${room.roomName }</a> -->
	</div>

</body>
</html>