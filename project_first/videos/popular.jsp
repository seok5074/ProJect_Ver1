<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.project.videos.dao.*, com.project.videos.vo.*"%>
<%@ page import="java.util.*, java.net.*" %>
<%!
	final int PAGE_SIZE = 4;
	final int PAGE_GROUP = 10;
%>
<%
	request.setCharacterEncoding("utf-8");
	String pageNum = request.getParameter("pageNum");	
	String keyword = request.getParameter("keyword");
	ArrayList<MainVO> videoList = null;
	MainDao dao = MainDao.getInstance();
	if(pageNum == null) {
		pageNum = "1";
	}
	int currentPage = Integer.parseInt(pageNum);
	int startRow = currentPage * PAGE_SIZE - (PAGE_SIZE - 1);
	int endRow = startRow + PAGE_SIZE - 1;	
	int listCount = 0;	
	boolean searchOption = (keyword == null || keyword.equals("")) ? false : true;
	if(! searchOption) {
		listCount = dao.videoCount();
	} else {
		listCount = dao.videoCount(keyword);
	}
	if(listCount > 0) {
		if(! searchOption) {
			videoList = dao.videoLikeList(startRow, endRow);
		} else {
			videoList = dao.searchVideoList(keyword, startRow, endRow);
		}
	}
	int pageCount = listCount / PAGE_SIZE 
			+ (listCount % PAGE_SIZE == 0 ? 0 : 1);
	int startPage = (currentPage / PAGE_GROUP) * PAGE_GROUP + 1
			- (currentPage % PAGE_GROUP == 0 ? PAGE_GROUP : 0);
	int endPage = startPage + PAGE_GROUP - 1;
	if(endPage > pageCount) {
		endPage = pageCount;
	}	
%>
<c:set var="currentPage" value="<%= currentPage %>" />
<c:set var="pageGroup" value="<%= PAGE_GROUP %>" /> 
<c:set var="pageCount" value="<%= pageCount %>" />
<c:set var="startPage" value="<%= startPage %>" />
<c:set var="endPage" value="<%= endPage %>" />
<c:set var="videoList" value="<%=videoList%>" />
<c:set var="searchOption" value="<%= searchOption %>" />
<title>인기 동영상</title>
<%@include file="../board/header.jsp"%>
		<article class="">
			<br />
			<c:if test="${ searchOption }">
				<h2 style="margin-left: 50px;">검색 결과 입니다</h2>
				<br>
			</c:if>
			<c:if test="${ searchOption and !empty videoList }">
				<% keyword = URLEncoder.encode(keyword, "utf-8"); %>
				<c:set var="keyword" value="<%= keyword %>" />
				<c:forEach var="v" items="${videoList}">
					<div class="video" onclick="location.href='videoDetail?v_no=${v.v_no}'">
					<c:if test="${empty v.v_sumnailloc or v.v_sumnailloc == 'no_file' }">
						<img class="Thumbnail" alt="임시 썸네일" src="logo.png" >&nbsp;
					</c:if>
					<c:if test="${!empty v.v_sumnailloc }">
						<img class="Thumbnail" alt="썸네일" src="upload/${v.v_sumnailloc }" >&nbsp;
					</c:if>
						<h3 class="video_title">${v.v_title}</h3>
						<div class="video_detail">
							<i class="demo-icon icon-user"><a href="#">${v.v_uploader }</a><br/></i>
							<i class="demo-icon icon-calendar">업로드 날짜 : ${v.v_regdate } &nbsp;</i>
							<i class="demo-icon icon-eye">조회수 : ${v.v_views }회 &nbsp;</i>
							<i class="demo-icon icon-thumbs-up"> 추천수 : ${v.v_like }회 &nbsp;</i><br /> 
							<i class="demo-icon icon-hashtag">
								<a href="#">${v.v_hashtag}</a>&nbsp;
							</i>
							<br/> 
							주제 : ${v.v_category }<br/>
						</div>
					</div>
					<br />
				</c:forEach>
				<form class="searchForm" name="sch_form" method="get" action="main">
						<div class="wrap">
						   <div class="search">
						     <c:if test="${empty sessionScope.nowTheme or sessionScope.nowTheme == 'light'}">
							     <input type="text" class="searchTerm" name="keyword">
							      <button type="submit" class="searchButton">
								 	<i class="fa fa-search"></i>
							      </button>
						      </c:if>
						       <c:if test="${sessionScope.nowTheme == 'dark'}">
							     <input type="text" class="darksearchTerm" name="keyword">
							      <button type="submit" class="darksearchButton">
								 	<i class="fa fa-search"></i>
							      </button>
						      </c:if>
						 	</div>
						</div>
					</form>
				<br/>
				<div class="paging">
					<ul class="pagination justify-content-center"
							style="margin: 20px 0">
						<c:if test="${ startPage > pageGroup }"> 
							<li class="page-item"><a href="main?pageNum=${ startPage - pageGroup }">이전</a></li>
						</c:if>	
						<c:forEach var="i" begin="${ startPage }" end="${ endPage }">
							<c:if test="${ i == currentPage }">
							<li class="page-item"><a class="page-link" style="color: #ff0000;"> 
								<span class="currentPage"> ${ i } </span></a></li>
							</c:if>			
							<c:if test="${ i != currentPage }">
								<li class="page-item"><a class="page-link" href="main?pageNum=${ i }" 
								style="color: #000;">${ i }</a></li>
							</c:if>			
						</c:forEach>
						<c:if test="${ endPage < pageCount }">
							<li class="page-item"><a class="page-link" href="main?pageNum=
							${ startPage + pageGroup }" style="color: #ff0000;">다음</a></li>
						</c:if>		
					</ul>
				</div>	
			</c:if>		
			<c:if test="${empty videoList }">
				<div style="margin-left: 50px;">
					<p>업로드 된 영상이 없습니다. </p>
					<p onclick="history.go(-1);">이전 페이지로 돌아가기</p>
				</div>
				<form class="searchForm" name="sch_form" method="get" action="main">
						<div class="wrap">
						   <div class="search">
						     <c:if test="${empty sessionScope.nowTheme or sessionScope.nowTheme == 'light'}">
							     <input type="text" class="searchTerm" name="keyword">
							      <button type="submit" class="searchButton">
								 	<i class="fa fa-search"></i>
							      </button>
						      </c:if>
						       <c:if test="${sessionScope.nowTheme == 'dark'}">
							     <input type="text" class="darksearchTerm" name="keyword">
							      <button type="submit" class="darksearchButton">
								 	<i class="fa fa-search"></i>
							      </button>
						      </c:if>
						 	</div>
						</div>
				</form>
			</c:if>
			<c:if test="${!searchOption and !empty videoList }">
				<c:forEach var="v" items="${videoList}">
					<div class="video" onclick="location.href='videoDetail?v_no=${v.v_no}'">
					<c:if test="${empty v.v_sumnailloc or v.v_sumnailloc == 'no_file' }">
						<img class="Thumbnail" src="logo.png" >&nbsp;
					</c:if>
					<c:if test="${!empty v.v_sumnailloc }">
						<img class="Thumbnail" src="upload/${v.v_sumnailloc }">&nbsp;
					</c:if>
						<h3 class="video_title">${v.v_title}</h3>
						<div class="video_detail">
							<i class="demo-icon icon-user"><a href="#">${v.v_uploader }</a><br/></i>
							<i class="demo-icon icon-calendar">업로드 날짜 : ${v.v_regdate } &nbsp;</i>
							<i class="demo-icon icon-eye">조회수 : ${v.v_views }회 &nbsp;</i>
							<i class="demo-icon icon-thumbs-up"> 추천수 : ${v.v_like }회 &nbsp;</i><br /> 
							<i class="demo-icon icon-hashtag">
								<a href="#">${v.v_hashtag}</a>&nbsp;
							</i><br/> 
							주제 : ${v.v_category }
						</div>
					</div>
					<br />
				</c:forEach>
					<form class="searchForm" name="sch_form" method="get" action="main">
						<div class="wrap">
						   <div class="search">
						     <c:if test="${empty sessionScope.nowTheme or sessionScope.nowTheme == 'light'}">
							     <input type="text" class="searchTerm" name="keyword">
							      <button type="submit" class="searchButton">
								 	<i class="fa fa-search"></i>
							      </button>
						      </c:if>
						       <c:if test="${sessionScope.nowTheme == 'dark'}">
							     <input type="text" class="darksearchTerm" name="keyword">
							      <button type="submit" class="darksearchButton">
								 	<i class="fa fa-search"></i>
							      </button>
						      </c:if>
						 	</div>
						</div>
					</form>
				<br/>
				<div class="paging">
					<ul class="pagination justify-content-center"
							style="margin: 20px 0">
						<c:if test="${ startPage > pageGroup }"> 
							<li class="page-item">
							<a class="page-link" href="main?pageNum=${ startPage - pageGroup }" style="color: #ff0000;">이전</a></li>
						</c:if>	
						<c:forEach var="i" begin="${ startPage }" end="${ endPage }">
							<c:if test="${ i == currentPage }">
							<li class="page-item"><a class="page-link" style="color: #ff0000;">
								<span class="currentPage"> ${ i } </span></a></li>
							</c:if>			
							<c:if test="${ i != currentPage }">
								<li class="page-item"><a class="page-link" href="main?pageNum=${ i }" style="color: #000;">${ i }</a></li>
							</c:if>			
						</c:forEach>
						<c:if test="${ endPage < pageCount }">
							<li class="page-item"><a class="page-link" href="main?pageNum=
							${ startPage + pageGroup }" style="color: #ff0000;">다음</a></li>
						</c:if>	
					</ul>	
				</div>	
			</c:if>		
	</article>
</body>
</html>