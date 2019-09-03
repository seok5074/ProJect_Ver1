// 문서 객체가 준비되면
// $(document).ready()
$(function() {
	// jQuery에서 이벤트를 등록하는 메서드는 단일이벤트 등록 메서드, 다중이벤트 등록 메서드
	/* 게시 글 상세보기에서 게시 글 수정하기 버튼이 클릭되면 실행할 이벤트처리
	 **/
	$("#detailUpdate").on("click", function(e) {
		// 게시 글 수정 폼 updateForm.jsp에 post 방식으로 요청을 보낼 것임
		
		var pass = $("#pass").val();
		if(pass.length <= 0) {
			alert("게시 글을 수정하려면 비밀번호를 입력해 주세요");
			return;
		}
		
		var no = $(this).attr("data-no");
		
		$("#rNo").val(no);
		$("#rPass").val(pass);
		
		$("#checkForm").attr("action", "updateForm.jsp")
				.attr("method", "post")
				.submit();
	});

	/* 게시 글 상세보기에서 삭제하기 버튼이 클릭되면 실행될 이벤트 처리
	 **/
	$("#detailDelete").on("click", function(e) {
		
	});
	
	
	// 게시 글 쓰기 유효성 검사
	//$("#writeForm").submit(function() { });
	$("#writeForm").on("submit", function(e) { 

		// 폼 컨트롤에 데이터가 입력되었는지 확인 - 입력되지 x -> 폼 전송을 취소
		if($("#writer").val().trim().length <= 0) {
			alert("글쓴이가 입력되지 않았습니다.\n작성자를 입력해주세요");
			$("#writer").focus();
			return false;
		}
		
		if($("#title").val().trim().length <= 0) {
			return false;
		}
		
		
	});
	
	
});


