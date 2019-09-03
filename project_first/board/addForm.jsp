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
<title>글쓰기</title>
<link href="css/board.css" rel="stylesheet" />
<script src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
<link rel="shortcut icon" href="images/favicon.png">
<link rel="icon" href="images/favicon.png">
<script type="text/javascript">
	function send(f) {
		var subject = f.subject.value.trim();
		
		if (subject == '') {
			alert('제목을 입력');
			f.subject.value = '';
			f.subject.focus();
			return;
		}
		f.submit();
	}
</script>
</head>
<body>
	<%@include file="header.jsp"%>
	<form action="addProcess" method="post">
		<table class="contentTable">
			<tr>
				<td colspan="4" class="boardTitle"><h2>게시 글 새로쓰기</h2></td>
			</tr>
			<tr>
				<td colspan="4">&nbsp;</td>
			</tr>
			<tr>
				<td class="contentTh">제&nbsp;&nbsp;&nbsp;&nbsp;목</td>
				<td class="contentTd"><input name="subject"></td>
			<c:if test="${sessionScope.isLogin }">
				<td class="contentTh">글쓴이</td>
				<td class="contentTd"><input name="name" value="${ id }" readonly="readonly"></td>
			</c:if>
			</tr>
			<tr>
				<td colspan="4"><textarea class="readContent" name="content"
						id="editor"></textarea> <script>
							CKEDITOR.replace('editor', {
								height : 300
							});
						</script></td>
			</tr>
			<tr>
				<td colspan="4" />
			</tr>

			<tr>
				<td colspan="4" align="center"><input type="reset" class="btn btn-danger" value="다시쓰기" />
					<input type="button" value="글쓰기" class="btn btn-primary" onclick="send(this.form);">
					<input type="button" value="목록보기" class="btn btn-info" onclick="location.href='mainBoardList'"></td>
			</tr>
		</table>
	</form>
</body>
</html>