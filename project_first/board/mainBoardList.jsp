<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.jspstudy.bbs.dao.*, com.jspstudy.bbs.beans.*"%>
<%@ page import="java.util.*, java.net.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="shortcut icon" href="images/favicon.png">
<link rel="icon" href="images/favicon.png">
</head>
<body>
	<%@include file="header.jsp"%>
	<table class="listTable">
		<tr>
			<td colspan="5" class="boardTitle"><h1>게시판에 오신걸 환영합니다!</h1></td>
		</tr>

		<c:if test="${ searchOption }">
			<tr>
				<td colspan="5" id="searchComment">"${ word }" 검색 결과</td>
			</tr>
		</c:if>

		<tr style="color: #000;">
			<th class="listThNo">NO</th>
			<th class="listThTitle">제목</th>
			<th class="listThWriter">작성자</th>
			<th class="listThRegDate">작성일</th>
			<th class="listThReadCount">조회수</th>
			<th class="listThLikeCount">좋아요</th>
		</tr>
		<c:if test="${searchOption and not empty bList }">
			<c:forEach var="b" items="${ bList }" varStatus="status">
				<tr class="listTr">
					<td class="listTdNo">${ b.idx  }</td>
					<td class="listTdTitle"><a
						href="boardDetail?idx=${ b.idx }&pageNum=
				${ currentPage }&type=${ type }&keyword=${ keyword }">${ b.subject }</a>
					</td>
					<td class="listTdWriter">${ b.name }</td>
					<td class="listTdRegDate"><fmt:formatDate
							value="${ b.regDate }" pattern="yyyy-MM-dd HH:mm" /></td>
					<td class="listTdReadCount">${ b.readHit }</td>
					<td class="listTdLikeCount">${ b.likeHit }</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="5" class="pagination">
					<ul class="pagination justify-content-center"
						style="margin: 20px 0">
						<c:if test="${ startPage > PAGE_GROUP }">
							<li class="page-item"><a class="page-link"
								href="mainBoardList?pageNum=	${ startPage - PAGE_GROUP }&type=${ type }&keyword= ${ keyword }" 
								style="color: #ff0000;">이전</a></li>
						</c:if>
						<c:forEach var="i" begin="${ startPage }" end="${ endPage }">
							<c:if test="${ i == currentPage }">
								<li class="page-item"><a class="page-link" href="#" style="color: #ff0000;">${ i }</a></li>
							</c:if>
							<c:if test="${ i != currentPage }">
								<li class="page-item"><a class="page-link"
									href="mainBoardList?pageNum=${ i }&type=	${ type }&keyword=${ keyword }" style="color: #000000;">${ i }</a></li>
							</c:if>
						</c:forEach>
						<c:if test="${ endPage < pageCount }">
							<li class="page-item"><a class="page-link"
								href="mainBoardList?pageNum=
					${ startPage + PAGE_GROUP }&type=${ type }&keyword=
					${ keyword }" style="color: #ff0000;">다음</a></li>
						</c:if>
					</ul>
				</td>
			</tr>
		</c:if>
		<c:if test="${ not searchOption and not empty bList }">
			<c:forEach var="b" items="${ bList }" varStatus="status">
				<tr class="listTr">
					<td class="listTdNo">${ b.idx  }</td>
					<td class="listTdTitle"><a
						href="boardDetail?idx=
					${ b.idx }&pageNum=${ currentPage }">${ b.subject }</a>
					</td>
					<td class="listTdWriter">${ b.name }</td>
					<td class="listTdRegDate"><fmt:formatDate
							value="${ b.regDate }" pattern="yyyy-MM-dd HH:mm" /></td>
					<td class="listTdReadCount">${ b.readHit }</td>
					<td class="listTdLikeCount">${ b.likeHit }</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="5" class="listPage">
					<ul class="pagination justify-content-center"
						style="margin: 20px 0">
						<c:if test="${ startPage > PAGE_GROUP }">
							<li class="page-item"><a class="page-link"
								href="mainBoardList?pageNum=${ startPage - PAGE_GROUP }" style="color: #ff0000;">이전</a></li>
						</c:if>

						<c:forEach var="i" begin="${ startPage }" end="${ endPage }">
							<c:if test="${ i == currentPage }">
								<li class="page-item"><a class="page-link" href="#" style="color: #ff0000;">${ i }</a></li>
							</c:if>
							<c:if test="${ i != currentPage }">
								<li class="page-item"><a class="page-link"
									href="mainBoardList?pageNum=${ i }" style="color: #000000;">${ i }</a></li>
							</c:if>
						</c:forEach>
						<c:if test="${ endPage < pageCount }">
							<li class="page-item"><a class="page-link"
								href="mainBoardList?pageNum=${ startPage + PAGE_GROUP }" style="color: #ff0000;">다음</a></li>
						</c:if>
					</ul>
				</td>
			</tr>
		</c:if>
		<c:if test="${ searchOption and empty bList }">
			<tr>
				<c:if test="${ searchOption }">
					<tr>
						<td colspan="2" class="boardListLink"><a
							style="float: right;" href="mainBoardList"> "${ word }"가 포함된
								게시 글이 존재하지 않습니다.</a></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					</tr>
				</c:if>
			</tr>
		</c:if>
		<c:if test="${ not searchOption and empty bList }">
			<tr>
				<td colspan="5" class="listTdSpan">게시 글이 존재하지 않습니다.</td>
			</tr>
		</c:if>
		<tr>
			<td colspan="5">
				<form class="searchForm" name="searchForm" action="mainBoardList">
					<div class="wrap">
						<div class="search">
							<select class="searchSelect" name="type">
								<option value="subject">제목</option>
								<option value="name">작성자</option>
								<option value="content">내용</option>
							</select> <input type="text" class="searchTerm" name="keyword">
							<button type="submit" class="searchButton">
								<i class="fa fa-search"></i>
							</button>
						</div>
					</div>
				</form>
			</td>
			<c:if test="${ not searchOption }">
				<c:if test="${not sessionScope.isLogin}">
					<td colspan="5" class="listWrite">
						<input type="submit" class="btn btn-primary" value="글쓰기"
						 onclick="location.href='mem/mem_login.jsp'" title="글쓰기를 하시려면 로그인이 필요합니다." />
					</td>
				</c:if>
				<c:if test="${sessionScope.isLogin}">
					<td colspan="5" class="listWrite"><input type="submit"
						class="btn btn-primary" value="글쓰기"
						onclick="location.href='addForm'" />
				</c:if>
			</c:if>
		</tr>
	</table>
</body>
</html>