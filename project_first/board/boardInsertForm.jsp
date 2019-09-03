<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.jspstudy.bbs.beans.*, com.jspstudy.bbs.dao.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<c:if
	test="${empty sessionScope.nowTheme or sessionScope.nowTheme == 'light'}">
	<link rel="stylesheet" href="css/light.css">
</c:if>
<%--세션의 값이 dark일 경우 dark.css를 불러옴 --%>
<c:if test="${sessionScope.nowTheme == 'dark'}">
	<link rel="stylesheet" href="css/dark.css">
</c:if>
<meta charset="UTF-8">
<title>게시판수정</title>
<link href="css/board.css" rel="stylesheet" />
<link rel="stylesheet" href="css/fontello-embedded.css">
<link rel="stylesheet" href="css/animation.css">
<link rel="stylesheet" type="text/css" href="css/fontello.css">
<script src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
<link rel="shortcut icon" href="images/favicon.png">
<link rel="icon" href="images/favicon.png">
<script type="text/javascript">
	function send(f) {
		var subject = f.subject.value.trim();
		var content = f.content.value.trim();
		if (subject == '') {
			alert('이름입력');
			f.subject.value = "";
			f.subject.focus();
			return;
		}
		if (content == '') {
			alert('내용입력');
			f.content.value = "";
			f.content.focus();
			return;
		}
		f.action = "modify"; //boardModifyAciton 으로 전송
		f.submit(); //전송 
	}
</script>
</head>
<body>
	<%@include file="header.jsp"%>
	<form name="checkForm" id="checkForm">
		<input type="hidden" name="idx" value="${ board.idx }">
		<table class="contentTable">
			<tr>
				<td colspan="4" class="boardTitle"><h2>게시 글 수정하기</h2></td>
			</tr>
			<tr>
				<td colspan="4">&nbsp;</td>
			</tr>
			<tr>
				<td class="contentTh">제&nbsp;&nbsp;&nbsp;&nbsp;목</td>
				<td colspan="3" class="contentTd"><input name="subject"
					value="${ board.subject }"></td>
			</tr>
			<tr>
				<td class="contentTh">글쓴이</td>
				<td class="contentTd"><input name="name" readonly
					value="${ board.name }"></td>
				<td class="contentTh">작성일</td>
				<td class="contentTd"><fmt:formatDate
						value="${ board.regDate }" pattern="yyyy-MM-dd HH:mm" /></td>
			</tr>
			<tr>
				<td class="contentTh">조회수</td>
				<td class="contentTd">${ board.readHit }</td>
				<td class="contentTh">좋아요수</td>
				<td class="contentTd">${ board.likeHit }</td>
			</tr>
			<tr>
				<td colspan="4"><textarea class="readContent" name="content"
						id="editor">${ board.content }</textarea> <script>
							CKEDITOR.replace('editor', {

							});
						</script></td>
			</tr>
			<tr>
				<td colspan="4" />
			</tr>
			<tr>
				<td colspan="4" align="center"><input type="button" value="수정하기"
					class="btn btn-primary" onclick="send(this.form);"> <input
					type="button" value="목록보기" class="btn btn-info"
					onclick="location.href='mainBoardList'"></td>
			</tr>
		</table>
	</form>
</body>
</html>




