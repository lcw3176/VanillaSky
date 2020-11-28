<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Vanilla Sky</title>
<link rel="stylesheet" href="resources/css/makeroom.css">
</head>

<body>
	<jsp:include page="navbar.jsp" />

	<div class="makeRoom">
		<form:form action="makeroom" method="post" modelAttribute="room">
			<div class="form-group">
				<label for="exampleInputEmail1">방 이름</label>
				<form:input path="roomName" class="form-control"
					placeholder="제목을 입력하세요" />
				<small class="form-text text-muted">고운말로 써주세요</small>
			</div>
			<label>최대 인원 설정</label>
			<form:select class="form-control" path="maxNumber">
				<option>2</option>
				<option>3</option>
				<option>4</option>
			</form:select>

			<button type="submit" class="btn btn-primary"
				id="completeMakeRoomButton">완료</button>
		</form:form>
	</div>

</body>
</html>