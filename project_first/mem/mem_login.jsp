<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="../css/login.css">
<link rel="shortcut icon" href="../images/favicon.png">
<link rel="icon" href="../images/favicon.png">
</head>
<body>
	<div id="main_box">
		<h1> 로 그 인</h1>
		<div>
			<c:choose>
		<c:when test="${ not sessionScope.isLogin }">
		<div id="loginForm">		
			<form action="../login/login.jsp" id="formLogin"
				name="loginForm" method="post">
				<div id="loginInput">
					<input type="hidden" name="currentURL" id="loginURL"/>
					<input type="text" name="id" id="inputId" placeholder="아이디"/><br/><br/>
					<input type="password" name="pwd" id="inputPwd" placeholder="비밀번호"/><br/><br/>
				</div>			
				<input type="submit"  id="btnLogin" value="로그인" /><br/>
			</form>
		</div>
		</c:when>
		<c:when test="${ sessionScope.isLogin }" >
		<div id="logoutForm">			
			<form action="../login/logout.jsp" id="formLogout"
				name="logoutForm" method="post">
				${ id } 님 환영합니다.
				<input type="hidden" name="currentURL" id="logoutURL"/>
				<input type="submit"  id="btnLogout" value="로그아웃" />
			</form>
		</div>
		</c:when>
	</c:choose>
	</div>
	<div>
			<input 	type="button" value="회원가입"  id="btnInsert"
					onclick="location.href='insert_form'">
	</div>
	</div>


	
</body>
</html>