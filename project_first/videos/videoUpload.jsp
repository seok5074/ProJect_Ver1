<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../board/header.jsp"%>
<!-- ckeditor -->
<title>영상 업로드</title>
<script type="text/javascript" src="video_ckeditor/ckeditor.js"></script>
<script>
	function frm_check(f) {
		var v_title = f.v_title.value.trim();
		var v_sumnailloc = f.v_sumnailloc.value;
		var v_videoloc = f.v_videoloc.value;
		var v_sumnailloc_fileLen = v_sumnailloc.length;
		var v_videoloc_fileLen = v_videoloc.length;
		var v_videoloc_lastDot = v_videoloc.lastIndexOf('.');
		var _lastDot = v_sumnailloc.lastIndexOf('.');
		var video_fileExt = v_sumnailloc.substring(v_videoloc_lastDot,
				v_videoloc_fileLen).toLowerCase();
		var _fileExt = v_sumnailloc.substring(_lastDot, v_sumnailloc_fileLen)
				.toLowerCase();

		console.log(_fileExt);
		console.log(video_fileExt);

		if (v_title == null || v_title == '') {
			alert("제목을 입력하시오.");
			v_title.focus();
			return;
		}

		if (v_videoloc == null || v_videoloc == '') {
			alert("영상을 선택하시오.");
			v_videoloc.focus();
			return;
		}

		if (v_sumnailloc == null || v_sumnailloc == '') {
			alert("썸네일을 선택하시오.");
			v_sumnailloc.focus();
			return;
		}

		f.action = 'videoInsert';
		f.submit();
	}
</script>
<form method="post" enctype="multipart/form-data">
	<br />
	<input type="hidden" value="${ sessionScope.id }" name="v_uploader"/>
	<table class="video_tbl" class="tbl_form">
		<tr>
			<th style="text-align: center;">제목</th>
			<td><input type="text" name="v_title" id="v_title" class="tbl_input" size="80"></td>
		</tr>
		<tr>
			<th style="text-align: center;"># 해시태그</th>
			<td><input type="text" name="v_hashtag" id="v_hashtag" class="tbl_input" size="80"></td>
		</tr>
		<tr>
			<th style="text-align: center;">주제</th>
			<td><input type="text" name="v_category" id="v_category" class="tbl_input" size="80"></td>
		</tr>
		<tr>
			<!-- 확장자를 .gif,, .png,, .jpg, ..peg,, .bmp로 제한 -->
			<th style="text-align: center;">썸네일</th>
			<td><input type="file" id="sumnail" name="v_sumnailloc"
				accept=".gif, .png, .jpg, .jpeg, .bmp"></td>
		</tr>
		<tr>
			<th style="text-align: center;">영상</th>
			<!-- 확장자를 .mp4, .ogg, .avi, .mpeg, .webm, .wmv로 제한 -->
			<td><input type="file" id="video" name="v_videoloc"
				accept=".mp4, .ogg, .avi, .mpeg, .webm, .wmv"></td>
		</tr>
		<tr>
			<td colspan="2"><textarea id="editor" name="v_content"></textarea></td>
		</tr>
		<tr>
			<td colspan="2" style="text-align: center;  "><input type="button"
				class="btn btn-primary" value="영상 업로드"
				onclick="frm_check(this.form);">&nbsp;&nbsp;&nbsp;<input type="button"
				class="btn btn-danger" value="목록가기"
				onclick="javascript:window.location.href='main'">
				</td>
		</tr>
	</table>
	<script>
		CKEDITOR.replace('editor', {

		});
	</script>
</form>
</body>
</html>