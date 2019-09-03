<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../board/header.jsp"%>
<!-- video 라이브러리 -->
<title>${vo.v_title }</title>
<link href="https://vjs.zencdn.net/7.6.0/video-js.css" rel="stylesheet">
<script src="https://vjs.zencdn.net/ie8/1.1.2/videojs-ie8.min.js"></script>
<script src='https://vjs.zencdn.net/7.6.0/video.js'></script>
<script src="js/video_reply.js"></script>
<script>
	$(document).ready(function() {
		
		$("#close_content").click(function() {
			$(".content").hide(500, function() {
			});			
			$(".content_show").show(500, function() {
			});
		});
		
		$("#show_content").click(function() {
			$(".content").show(500, function() {
			});
			$(".content_show").hide(500, function() {
			});
		});
		
		$("#rec_update").click(function() {
			console.log("게시물no : " + $(this).attr("data-val"));

			$.ajax({
				url : "video_recUpdate",
				type : "POST",
				data : {
					v_no : $(this).attr("data-val")
				},
				success : function(result) {
					console.log("likeResult : " + result);
					$("#rec_count").html(result);
				},
			})
		});

	});
</script>
<style>
td{
	color: #000000;
}
th{
	color: #000000;
}
</style>
<body>
	<div class="video_player">
		<!-- video.js 라이브러리 사용 -->
		<video id="my-player" class="video-js" controls preload="auto"
			poster="upload/${vo.v_sumnailloc }" data-setup='{}' loop="loop"
			autoplay="autoplay" width="1000" style="color: #ffffff; ">
			<!-- 확장자별 소스 타입 지정 -->
			<source src="upload/${vo.v_videoloc }" type="video/mp4"></source>
			<source src="upload/${vo.v_videoloc }" type="video/ogg"></source>
			<source src="upload/${vo.v_videoloc }" type="video/avi"></source>
			<source src="upload/${vo.v_videoloc }" type="video/mpeg"></source>
			<source src="upload/${vo.v_videoloc }" type="video/webm"></source>
			<source src="upload/${vo.v_videoloc }" type="video/wmv"></source>
		</video>
		
		<div class="video_detail_tbl">
		<table class="table">
			<c:if test="${sessionScope.id == vo.v_uploader }">
				<p>
					<input type="button" value="삭제하기"
						onclick="location.href='delete?v_no=${vo.v_no}'"> <input
						type="button" value="수정하기"
						onclick="location.href='videoUpdateForm?v_no=${vo.v_no}'">
				</p>
			</c:if>
			<thead>
			<tr>
				<c:if test="${empty sessionScope.nowTheme or sessionScope.nowTheme == 'light'}">
					<th style="color: #000;">
						<h2 title="${vo.v_title }">${vo.v_title }</h2>
					</th>
				</c:if>
				<c:if test="${sessionScope.nowTheme == 'dark'}">
					<th style="color: #fff;">
						<h2 title="${vo.v_title }">${vo.v_title }</h2>
					</th>
				</c:if>
			</tr>
			</thead>
			<c:if test="${empty sessionScope.nowTheme or sessionScope.nowTheme == 'light'}">
				<tbody style="color: #000;">
			</c:if>
			<c:if test="${sessionScope.nowTheme == 'dark'}">
				<tbody style="color:#fff;">
			</c:if>
				<tr>
					<td>
						<p title="게시일, 조회수, 추천수">
						<i class="demo-icon icon-calendar"></i>게시일 &nbsp; ${vo.v_regdate } <i
							class="demo-icon icon-eye"></i>조회수:${vo.v_views } <i
							class="demo-icon icon-thumbs-up"></i>추천수:<span id="rec_count">
							${vo.v_like }</span>
						</p>
					<p title="영상 주제 및 #해시 태그">
						영상 주제 &nbsp; ${vo.v_category }&nbsp;&nbsp;&nbsp; <br /> 
						<i class="demo-icon icon-hashtag"></i>${vo.v_hashtag}
					</p>
					<p title="영상 업로더">
						<i class="demo-icon icon-user"></i>${vo.v_uploader }
					</p>
					<p style="text-align: right;">
						<button id="rec_update" data-val="${vo.v_no }" class="btn_like"
							title="추천하기">
							추천하기 <i class="demo-icon icon-thumbs-up"></i>
						</button>
					</p>
					<div class="content_show">
						상세보기				
						<button id="show_content" class="video_btn" style="cursor: pointer;"
							title="본문 자세히 보기">
							<i class="demo-icon icon-down-open"></i>
						</button>
					</div>
					<div class="content">
						
						<button id="close_content" class="video_btn"
							style="cursor: pointer;" title="본문 자세히 보기 닫기">
							<i class="demo-icon icon-up-open"></i>
						</button>
						${vo.v_content }
					</div>
					</td>
				</tr>
			</tbody>
			</table>
		</div>
		<br />
		<div class="video_reply">			
			<div id="replyForm">
				<form name="replyWriteForm" id="replyWriteForm">
					<input type="hidden" name="v_no" value="${ vo.v_no }" />
					<c:if test="${ not empty sessionScope.id }">
						<input type="hidden" name="replyWriter"
							value="${ sessionScope.id }" />
					</c:if>
					<c:if test="${ empty sessionScope.id }">
						<input type="hidden" name="replyWriter" value="익명" />
					</c:if>
					<table id="replyWriteTable">
						<tr>
							<td id="replyWriteTitle" colspan="2"><span>악의적인 댓글은
									예고 없이 삭제될 수 있으며 글쓰기 제한과 아이디 삭제 처리됩니다.</span></td>
						</tr>
						<tr>
							<td id="replyWriteContent"><textarea name="replyContent"
									id="replyContent" rows="4"></textarea></td>
							<td id="replyWriteImage"><input type="image"
								src="images/reply_btn_write.gif" id="replyWriteButton"
								alt="댓글 입력" /></td>
						</tr>
					</table>
				</form>
			</div>
			<div>				
				<div id="replyButton">
					<span id="beforeReplyWrite">댓글 쓰기</span>
					<button id="replyWrite" style="cursor: pointer;"
						title="버튼을 클릭하면 댓글 입력 상자가 나타납니다.">
						<i class="demo-icon icon-edit"></i>
					</button>
				</div>
				<div id="replyTitle"><h6 style="text-align: center;">댓글 목록</h6></div>			
			</div>		
			<c:if test="${ empty replyList }">
				<tr id="replyList">
					<td colspan="4">
						<div id="notReply">이 게시 글에 대한 댓글이 존재하지 않습니다.</div>
					</td>
				</tr>
			</c:if>	
			<c:if test="${ not empty replyList }">
				<table id="replyTable">
					<c:forEach var="reply" items="${ replyList }">
						<tr id="reply_${ reply.vr_no }">
							<td>
								<div class="replyUser">
									<span class="member">${ reply.vr_writer }</span>
								</div>
								<div class="replyModify">
									<span class="reply_date">${reply.vr_regdate }</span>
									<c:if test="${sessionScope.id == reply.vr_writer }">
										 <a href="#" class="modifyReply" data-no="${ reply.vr_no }"> 
											 <img src="images/reply_btn_modify.gif" alt="댓글 수정하기" />
										 </a> 
									 </c:if>
									 <c:if test="${sessionScope.id == reply.vr_writer }">
										 <a href="#" class=deleteReply data-no="${ reply.vr_no }"> 
									 		<img src="images/reply_btn_delete.gif" alt="댓글 삭제하기" />
									 	</a> 
									 </c:if>
									 <c:if test="${ reply.vr_writer == '익명' }">
										 <a href="#" class="modifyReply" data-no="${ reply.vr_no }"> 
											 <img src="images/reply_btn_modify.gif" alt="댓글 수정하기" />
										 </a> 
									 </c:if>
									 <c:if test="${ reply.vr_writer == '익명' }">
										 <a href="#" class=deleteReply data-no="${ reply.vr_no }"> 
									 		<img src="images/reply_btn_delete.gif" alt="댓글 삭제하기" />
									 	</a> 
									 </c:if>
									 
								</div>
								<div class="replyContent" id="div_${ reply.vr_no }">
									<pre>
										<span class="replyContentPre">${ reply.vr_reply }</span>
									</pre>
								</div>
							</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
	</div>
</body>
</html>