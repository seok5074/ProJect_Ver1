<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 부트스트랩  -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.1.0/css/all.css">
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet">
<!-- 아이콘 라이브러리  -->
<link rel="stylesheet" href="css/fontello-embedded.css">
<link rel="stylesheet" href="css/animation.css">
<link rel="stylesheet" type="text/css" href="css/fontello.css">
<link rel="stylesheet" href="css/board.css">
<link rel="shortcut icon" href="images/favicon.png">
<link rel="icon" href="images/favicon.png">

<%--세션의 값이 light일 경우 light.css를 불러옴 --%>
<c:if
	test="${empty sessionScope.nowTheme or sessionScope.nowTheme == 'light'}">
	<link rel="stylesheet" href="css/light.css">
</c:if>
<%--세션의 값이 dark일 경우 dark.css를 불러옴 --%>
<c:if test="${sessionScope.nowTheme == 'dark'}">
	<link rel="stylesheet" href="css/dark.css">
</c:if>

<!--  기본 디자인 css  -->
<link rel="stylesheet" type="text/css" href="css/main.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('.search-button').click(function() {
			$(this).parent().toggleClass('open');
		});
		$("mainMenu").hover(function() {
			$(this).css("color", "#ff0000");
		});
		// 현재 페이지 주소를 가져옴 
		var link = document.location.href;
		console.log(link);
		// 페이지 주소를 바탕으로 현재 메뉴 페이지에 빨간색 밑줄을 그어줌 
		if (link == 'http://localhost:8080/2019081944/main') {
			$("#latest").css("border-bottom", "1px solid #ff0000");
		} else if (link == 'http://localhost:8080/2019081944/popular') {
			$("#popularity").css("border-bottom", "1px solid #ff0000");
		} else if (link == 'http://localhost:8080/2019081944/mainBoardList') {
			$("#board").css("border-bottom", "1px solid #ff0000");
		} else if (link == 'http://localhost:8080/Main_Page/main#') {
			$("#subscribe").css("border-bottom", "1px solid #ff0000");
		} else if (link == 'http://localhost:8080/Main_Page/main#') {
			$("#mypage").css("border-bottom", "1px solid #ff0000");
		}
		//  메뉴의 위치를 얻어옴
		var top = parseInt($(".right_menu").css("top"));
		// 스크롤될때 같이 움직이게 설정 
		$(window).scroll(function() {
			var scrollTop = $(window).scrollTop();
			console.log("scrollTop :" + scrollTop);
			var quickTop = top + scrollTop;
			$(".right_menu").animate({
				top : quickTop
			}, 1);
		});

		var form = $("#change_theme_form");

		var check = $("#setTheme");
		check.click(function() {
			$(".toggle").toggle();
		});
		// Prevent the page from reloading
		$('input[id="setTheme"]').change(function() {
			var checked = $(this).prop('checked');
			if (checked) {
				$('#setTheme').val("dark");
				form.submit();
			} else if (!checked) {
				$('#setTheme').val("light");
				form.submit();
			}
		});
	});
</script>
<header>
	<c:if test="${empty sessionScope.nowTheme or sessionScope.nowTheme == 'light'}">
		<a href="mainBoardList"> 
			<img class="logo" alt="lightTheme logo" src="images/light_logo.png" class="logo_img" height="70">
		</a>
	</c:if>
	<c:if test="${sessionScope.nowTheme == 'dark'}">
		<a href="mainBoardList"> <img class="logo" alt="darkTheme logo" src="images/dark_logo.png" class="logo_img" height="70">
		</a>
	</c:if>

	<div>
		<c:choose>
			<c:when test="${ not sessionScope.isLogin }">
				<div id="loginForm">
					<form action="mem/mem_login.jsp" id="formLogin" name="loginForm"
						method="post">
						<div id="loginInput">
							<input type="hidden" name="currentURL" id="loginURL" />
						</div>
						<input type="submit" id="btnLogin" value="로그인" class="btn btn-outline-info" />
					</form>
				</div>
			</c:when>
			<c:when test="${ sessionScope.isLogin }">
				<div id="loginForm">
					<form action="login/logout.jsp" id="formLogout" name="logoutForm" method="post">
						<br>
						 ${ id } 님 환영합니다. 
						<input type="hidden" name="currentURL" id="logoutURL" /> 
						<a href="login/logout.jsp" id="Logout" class="btn btn-outline-info">로그아웃</a>
					</form>
				</div>
			</c:when>
		</c:choose>
		<br />
		<nav class="mainMenu">
			<ul>
				<li><a href="main" id="latest">최신 동영상</a></li>
				<li><a href="popular" id="popularity">인기 동영상</a></li>
				<li><a href="mainBoardList" id="board">게시판</a></li>
				<c:if test="${ not empty sessionScope.isLogin }">
					<li><a href='mem/mypage_modify_form?id=${ id }' id="mypage">마이페이지</a></li>
				</c:if>
				<c:if test="${ empty sessionScope.isLogin }">
					<li><a href='mem/mem_login.jsp' id="mypage">마이페이지</a></li>
				</c:if>
				<li style="position: relative; bottom: 7px;"><c:if
						test="${empty sessionScope.nowTheme or sessionScope.nowTheme == 'light'}">
						<form action="setTheme" id="change_theme_form" method="post">
							<label class="switch"> <input type="checkbox"
								id="setTheme" name="theme"> <span class="slider round"></span>
							</label>
							<p class="toggle"  style="color: #000;">다크로 변경</p>
							<p style="display: none;" class="toggle" style="color: #000;"></p>
						</form>
					</c:if> <c:if test="${sessionScope.nowTheme == 'dark'}">
						<form action="setTheme" id="change_theme_form" method="post">
							<label class="switch"> <input type="checkbox"
								id="setTheme" name="theme" checked="checked"> <span
								class="slider round"></span>
							</label>
							<p class="toggle" style="color: #fff;">라이트로 변경</p>
							<p style="display: none;" class="toggle" style="color: #fff;"></p>
						</form>
					</c:if></li>
			</ul>
		</nav>
	</div>
	<div>&nbsp;</div>
</header>
<br />
<br />
<br />
<div class="right_menu">
	<p>페이지이동</p>
	<p>
		<a href="main">동영상 최신순 목록</a>
	</p>
	<p>
		<a href="popular">동영상 인기순 목록</a>
	</p>
	<p>
		<a href="mainBoardList">게시판</a>
	</p>
</div>
