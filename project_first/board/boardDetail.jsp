<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.jspstudy.bbs.beans.*, com.jspstudy.bbs.dao.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${ board.subject }</title>
<link href="css/board.css" rel="stylesheet" />
<script src="js/jquery-3.4.1.min.js"></script>
<script src="js/reply.js"></script>
<link rel="shortcut icon" href="images/favicon.png">
<link rel="icon" href="images/favicon.png">
<script type="text/javascript">
	$(function() {
		$("#detailDelete").on("click", function() {
			var result = confirm("현재 게시 글을 삭제 하시겠습니까?");
			if (result) {
				$("#rIdx").val($(this).attr("data-no"));
				$("#checkForm").attr("action", "delete.do");
				$("#checkForm").submit();
			}
		});
		$("#detailUpdate").on("click", function() {
			var result = confirm("현재 게시 글을 수정 하시겠습니까?");
			if (result) {
				$("#rIdx").val($(this).attr("data-no"));
				$("#checkForm").attr("action", "modifyForm");
				$("#checkForm").submit();
			}
		});
		$("#rec_update").click(function() {
			console.log("idx : " + $(this).attr("data-val"));

			$.ajax({
				url : "recUpdate",
				type : "POST",
				data : {
					idx : $(this).attr("data-val")
				},
				success : function(result) {
					console.log("result : " + result);
					$("#rec_count").html(result);
				},
			})
		});

	});
</script>
<style type="text/css">
	.contentTh{
		color: #000;
	}
</style>
</head>
<body>
	<%@include file="header.jsp"%>
	<form name="checkForm" id="checkForm">
		<input type="hidden" name="idx" id="rIdx" />
	</form>
	<table class="contentTable">
		<tr>
			<td colspan="4" class="boardTitle"><h2>게시 글 상세보기</h2></td>
		</tr>
		<tr>
			<td colspan="4">&nbsp;</td>
		</tr>
		<tr>
			<td class="contentTh">제&nbsp;&nbsp;&nbsp;&nbsp;목</td>
			<td colspan="3" class="contentTd">${ board.subject }</td>
		</tr>
		<tr>
			<td class="contentTh">글쓴이</td>
			<td class="contentTd">${ board.name }</td>
			<td class="contentTh">작성일</td>
			<td class="contentTd"><fmt:formatDate value="${ board.regDate }"
					pattern="yyyy-MM-dd HH:mm" /></td>
		</tr>
		<tr>
			<td class="contentTh">조회수</td>
			<td class="contentTd">${ board.readHit }</td>
			<td class="contentTh">좋아요수</td>
			<td class="contentTd"><button
					class="w3-button w3-black w3-round" id="rec_update"
					data-val="${board.idx}">
					<span id="rec_count">${ board.likeHit }</span>&nbsp;<i
						class="demo-icon icon-thumbs-up"></i>
				</button>
		</tr>
		<tr>
			<td class="readContent" colspan="4"><pre>${ board.content }</pre>
			</td>
		</tr>
		<tr>
			<td colspan="4">&nbsp;</td>
		</tr>
		<c:if test="${sessionScope.id == board.name }">
			<tr>
				<td colspan="4" align="center"><c:if
						test="${ sessionScope.id == board.name }">
						<input type="button" id="detailUpdate" class="btn btn-warning"
							value="수정하기" data-no='${ board.idx }' />
						<input type="button" id="detailDelete" class="btn btn-danger"
							value="삭제하기" data-no="${ board.idx }" />
						<input type="button" value="목록보기" class="btn btn-info" onclick="location.href='mainBoardList'" />
					</c:if>
					</td>
				</tr>
		</c:if>
		<c:if test="${sessionScope.id != board.name }">
			<tr>
				<td colspan="4" align="center">
					<input type="button" value="목록보기" class="btn btn-info" onclick="location.href='mainBoardList'" />
				</td>
			</tr>
		</c:if>
		<tr>
			<td colspan="4" class="replyHeader">
				<div id="recommend">
					<span id="replyWrite"> &nbsp;댓글쓰기 </span>
				</div>
				<div id="replyTitle">
					<h4>이 글에 대한 댓글 리스트</h4>
				</div>
			</td>
		</tr>
		<c:if test="${ empty replyList }">
			<tr id="replyList">
				<td colspan="4">
					<div id="notReply">이 게시 글에 대한 댓글이 존재하지 않습니다.</div>
				</td>
			</tr>
		</c:if>
		<c:if test="${ not empty replyList }">
			<tr id="replyList">
				<td colspan="4">
					<table id="replyTable">
						<c:forEach var="reply" items="${ replyList }">
							<tr id="reply_${ reply.no }">
								<td>
									<div class="replyUser">
										<span class="member">${ reply.writer }</span>
									</div>
									<div class="replyModify">
										<span class="reply_date">
										 	<fmt:formatDate value="${ reply.regDate}" pattern="yyyy-MM-dd HH:mm:ss" />
										</span>
										<c:if test="${sessionScope.id == reply.writer }">
											<a href="#" class="modifyReply" data-no="${ reply.no }">
											 <img src="images/reply_btn_modify.gif" alt="댓글 수정하기" /></a>
										 </c:if> 
										<c:if test="${sessionScope.id == reply.writer }">
											<a href="#" class=deleteReply data-no="${ reply.no }"> 
												<img src="images/reply_btn_delete.gif" alt="댓글 삭제하기" />
											</a>
										</c:if>
										<!-- anonymous -->
									<c:if test="${reply.writer == 'anonymous'}">
										<a href="#" class="modifyReply" data-no="${ reply.no }">
										<img src="images/reply_btn_modify.gif" alt="댓글 수정하기" /></a>
									</c:if>
									<c:if test="${reply.writer == 'anonymous' }">
										<a href="#" class=deleteReply data-no="${ reply.no }"> 
												<img src="images/reply_btn_delete.gif" alt="댓글 삭제하기" />
										</a>
									</c:if>
									</div>
									<div class="replyContent" id="div_${ reply.no }">
										<pre>
										<span class="replyContentPre">${ reply.reply }</span>
									</pre>
									</div>
								</td>
							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
		</c:if>
	</table>
	<div id="replyForm">
		<form name="replyWriteForm" id="replyWriteForm">
			<input type="hidden" name="bbsNo" value="${ board.idx }" />
			<c:if test="${ not empty sessionScope.id }">
				<input type="hidden" name="replyWriter" value="${ sessionScope.id }" />
			</c:if>
			<c:if test="${ empty sessionScope.id }">
				<input type="hidden" name="replyWriter" value="anonymous" />
			</c:if>
			<table id="replyWriteTable">
				<tr>
					<td id="replyWriteTitle" colspan="2"><span>악의적인 댓글은 예고
							없이 삭제될 수 있으며 글쓰기 제한과 아이디 삭제 처리됩니다.</span></td>
				</tr>
				<tr>
					<td id="replyWriteContent"><textarea name="replyContent"
							id="replyContent" rows="4"></textarea></td>
					<td id="replyWriteImage"><input type="image"
						src="images/reply_btn_write.gif" id="replyWriteButton" alt="댓글 입력" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>




