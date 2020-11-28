<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vanilla Sky</title>
<link rel="stylesheet" href="resources/css/home.css">
</head>
<body>
	<jsp:include page="navbar.jsp" />

	<div class="container">
		<h1>Vanilla Sky</h1>
		<form:form action="login" modelAttribute="memberInfo" method="post">
			<!-- ID입력 -->
			<div class="input-group mt-3 mb-1">
				<div class="input-group-prepend">
					<span class="input-group-text" id="basic-addon1">Nickname</span>
				</div>
				<form:input path="nickName" name="userID" class="form-control"
					placeholder="사용할 닉네임을 적어주세요" aria-label="Input Nickname"
					aria-describedby="basic-addon1" />
				<form:errors path="nickName" />
			</div>
			<!-- 로그인 버튼 -->
			<button type="submit" class="btn btn-dark btn-sm btn-block">
				입장하기</button>
		</form:form>
	</div>

</body>
</html>