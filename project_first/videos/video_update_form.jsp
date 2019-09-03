<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../board/header.jsp"%>
<!-- ckeditor -->
<title>영상 수정</title>
<script type="text/javascript" src="video_ckeditor/ckeditor.js"></script>
<script>
	function frm_check(f) {
		var v_title = f.v_title.value.trim();		

		if (v_title == null || v_title == '') {
			alert("제목을 입력하시오.");
			v_title.focus();
			return;
		}

		if (confirm('정말 수정 하시겠습니까?') == true) {
			f.action = 'videoUpdate';
			f.submit();
		} else {
			return;
		}
	}
</script>
<form method="post">
	<input type="hidden" value="${vo.v_no}" name="v_no">
	<br />
	<h4 style="text-align: center;">
		영상 및 썸네일은 수정하실 수 없습니다. 수정을 원하시면 재업로드를 이용해 주시길 바랍니다.
	</h4>
	<br/>
	<table class="video_tbl" class="tbl_form">
		<tr>
			<th style="text-align: center;">제목</th>
			<td><input type="text" name="v_title" id="v_title" class="tbl_input" size="80" value="${vo.v_title }"></td>
		</tr>
		<tr>
			<th style="text-align: center;"># 해시태그</th>
			<td><input type="text" name="v_hashtag" id="v_hashtag" class="tbl_input" size="80" value="${vo.v_hashtag }"></td>
		</tr>
		<tr>
			<th style="text-align: center;">주제</th>
			<td><input type="text" name="v_category" id="v_category" class="tbl_input" size="80" value="${vo.v_category }"></td>
		</tr>
		<tr>
			<td colspan="2"><textarea id="editor" name="v_content">${vo.v_content }</textarea></td>
		</tr>
		<tr>
			<td colspan="2" style="text-align: center;"><input type="button" class="btn btn-outline-success" 
				value="수정하기" onclick="frm_check(this.form);"></td>
		</tr>
	</table>
	<script>
		CKEDITOR.replace('editor', {

		});
	</script>
</form>
</body>
</html>