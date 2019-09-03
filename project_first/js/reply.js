// DOM(Document Object Model)이 준비 되었다면
$(document).ready(function() {
	
	
	// 댓글 쓰기 메뉴에 마우스 호버(enter, out) 이벤트 처리 - 수정됨
	$("#replyWrite").hover(function() {
		$(this).css("cursor", "pointer");
	});
	
	// 댓글 쓰기가 클릭되었을 때 이벤트 처리 - 추가됨
	$(document).on("click", "#replyWrite", function() {
		if($("#replyForm").css("display") == "block") {
			
			/* 댓글 쓰기 폼이 현재 보이는 상태이고 댓글쓰기 메뉴가 아닌 댓글
			 * 수정에 위치해 있으면 댓글 쓰기 폼을 슬라이드 업 하고 댓글 쓰기로
			 * 이동시켜 슬라이드 다운을 한다. 
			 **/
			var $next = $(this).parent().next();
			if(! $($next).is("#replyForm")) {
				$("#replyForm").slideUp(300);
			}
			setTimeout(function(){
				$("#replyForm").insertBefore("#replyTitle").slideDown(300);
			}, 300);			
		} else {
			$("#replyForm").insertBefore("#replyTitle").slideDown(300);
		}
		
		/* 댓글 쓰기 폼과 댓글 수정 폼을 같이 사용하기 때문에 아래와 같이 id를
		 * 동적으로 댓글 쓰기 폼으로 변경하고 댓글 수정 버튼이 클릭될 때 추가한 
		 * data-no라는 속성을 삭제 한다.
		 **/
		$("#replyForm").find("form")
			.attr("id", "replyWriteForm").removeAttr("data-no");
		$("#replyContent").val("");
	});
	
	
	/* 댓글 쓰기 폼이 submit 될 때
	 * 최초 한 번은 완성된 html 문서가 화면에 출력되지만 댓글 쓰기를 한 번
	 * 이상하게 되면 ajax 통신을 통해 받은 데이터를 이전 화면과 동일하게
	 * 출력하기 위해 동적으로 요소를 생성하기 때문에 live 방식의 이벤트
	 * 처리가 필요하다. 댓글 쓰기와 수정하기 폼을 하나로 사용하기 때문에
	 * 댓글 쓰기 또는 수정 후에 댓글 쓰기가 제대로 동작하지 않을 수 있다.
	 **/
	$(document).on("submit", "#replyWriteForm", function() {
		console.log($("#replyContent").val());
		if($("#replyContent").val().length <= 5) {
			alert("댓글은 5자 이상 입력해야 합니다.");
			// Ajax 요청을 취소한다.
			return false;
		}
		
		var params = $(this).serialize();
		
		$.ajax({
			url: "replyWrite.ajax",
			type: "post",
			data: params,
			dataType: "json",
			success: function(resultData, status, xhr) {		
				
				var replyTable = null;
				
				/* $("#replyTable").length가 0 이면 댓글이 없는 게시 글임
				 * "이 게시 글에 대한 댓글이 존재하지 않습니다." 부분을 삭제하기
				 * 위해서 #replyList > td 부분을 비우고 #replyTable을 
				 * 동적으로 생성해 추가 한 후에 서버에서 받은 댓글을 출력하면 됨  
				 **/
				if($("#replyTable").length == 0) {
					replyTable = $("<table id='replyTable'></table>");
					$("#replyList > td").empty();					
					$("#replyList > td").append(replyTable);
					
				} else {
					/* 기존에 화면에 출력되었던 댓글 리스트를 비운다.
					 * 새로운 댓글이 추가되어 댓글 리스트가 변경되었기 때문에 
					 * 기존에 Table 안에 들어있던 댓글을 비우고 서버에서 새롭게 받은
					 * 댓글 리스트를 아래에서  동적으로 추가하면 된다.
					 **/
					$("#replyTable").empty();
					replyTable = $("#replyTable");
				}
				
				$.each(resultData, function(index, value) {					
					// 날짜 데이터를 출력 포맷에 맞게 수정
					var date = new Date(value.regDate);
					var strDate = date.getFullYear() + "-" + ((date.getMonth() + 1 < 10) 
							? "0" + (date.getMonth() + 1) : (date.getMonth() + 1)) + "-"  
							+ date.getDate() + "-" + ((date.getHours() < 10) 
							? "0" + date.getHours() : date.getHours()) + ":" 
							+ (date.getMinutes() < 10 ? "0" + date.getMinutes() 
							: date.getMinutes()) + ":" + (date.getSeconds() < 10 
							? "0" + date.getSeconds() : date.getSeconds());				
										
					var result = 
						"<tr class='reply_" + value.no + "'>" 
						+ "<td>"
						+ "	<div class='replyUser'>"
						+ "		<span class='member'>" + value.writer + "</span>"
						+ "	</div>"
						+ "	<div class='replyModify'>"
						+ "		<span class='replyDate'>" + strDate + "</span>"
						+ "		<a href='#' class='modifyReply' data-no='" + value.no + "'>"
						+ "			<img src='images/reply_btn_modify.gif' alt='댓글 수정하기'/>"
						+ "		</a>"
						+ "		<a href='#' class='deleteReply' data-no='" + value.no + "'>"
						+ "			<img src='images/reply_btn_delete.gif' alt='댓글 삭제하기'/>"
						+ "		</a>"
						+ "		<a href='javascript:reportReply('div_" + value.no + "');'>"
						+ "			<img src='images/reply_btn_notify.gif' alt='신고하기'/>"
						+ "		</a>"
						+ "	</div>"
						+ "	<div class='replyContent' id='div_" + value.no + "'>"
						+ "		<pre><span>" + value.reply + "</span></pre>"
						+ "	</div>"
						+ "</td>"
					+ "</tr>";
					
					/* 위에서 Table에 있는 모든 댓글을 비웠기 때문에 
					 * 새롭게 작성된 댓글 리스트를 Table에 추가하면 된다. 
					 **/
					replyTable.append(result);								
				});				
				// 댓글 쓰기가 완료되면 댓글 쓰기 폼을 숨긴다.
				$("#replyForm").slideUp(300).add("#replyContent").val("");
				
				/* jQuery() 함수는 문서에서 선택자를 통해 문서 객체를 선택해 jQuery
				 * 객체로 반환하기 때문에 문서에 선택자로 지정한 요소(문서 객체)가 존재하지
				 * 않아도 jQuery 객체가 반환되어 자바 스크립트 객체로 취급 된다.
				 * if($("#replyForm")) { } 일 때 jQuery 객체 이므로 true가 된다.
				 * 그래서 선택한 요소의 갯수를 반환하는 jQuery의 length 속성을 
				 * 활용해 아래와 같이 현재 문서에 요소가 존재하는지 판단할 수 있다.
				 * if($("#replyForm").length) { } 일 때 0이 반환되면 false가
				 * 되여 if문이 실행되지 않지만 1 이상이 반환되면 true가 되어 if문을
				 * 실행하게 된다.
				 **/
				console.log("write : " + $("#replyForm").length);
			},
			error: function(xhr, status, error) {
				alert("ajax 실패 : " + status + " - " + xhr.status);
			}
		});
		
		// 폼이 submit 되는 것을 취소한다.
		return false;
	});
	
	
	/* 댓글 수정 버튼이 클릭되면
	 * 댓글을 쓰고 나면 동적으로 요소를 생성하기 때문에 live 방식으로 이벤트를 등록해야
	 * 한다. 만약 $(".modifyReply").click(function() {}); 형식으로 이벤트를 
	 * 등록했다면 새로운 댓글을 등록한 후에는 클릭 이벤트 처리가 되지 않는다.
	 **/
	$(document).on("click", ".modifyReply", function() {	
		
		// 현재 수정하기가 클릭된 부모 요소의 다음 형제 요소를 구한다.
		var $next = $(this).parent().next();
		
		/* 현재 수정하기가 클릭된 부모 요소의 다음 형제 요소의 두 번째 자식은
		 * 댓글 쓰기 수정 폼이 된다. 그러므로 댓글 수정 폼이 있는 곳은 이미 폼이
		 * 존재하기 때문에 추가 작업을 할 필요가 없고 댓글 쓰기 폼이 없는 곳에만
		 * 댓글 쓰기 폼을 가져와 그 위치에 추가하는 작업을 해야 한다.
		 **/		
		if($($next.children()[1]).attr("id") == undefined) {
		
			/* 아래와 같이 jQuery의 is() 메서드를 이용해 화면에 보이는
			 * 상태인지 보이지 않는 상태인지를 체크할 수 있다. 그리고 length
			 * 속성으로 지정한 요소가 문서에 존재하는지 여부를 체크할 수 있다.
			 **/
			console.log(".modifyReply click : visible - " 
					+ $("#replyForm").is(":visible")
					+ ", hidden - " + $("#replyForm").is(":hidden")
					+ ", length - " + $("#replyForm").length);
			
			/* 부모 요소의 다음 형제 요소의 첫 번째 자식 요소의 text를 구한다.
			 * <pre> 태그로 감싼 <span> 태그에 표시한 댓글의 내용을 구한다.
			 * text() 메서드는 현재 선택된 요소의 모든 하위 요소에 표시된 텍스트를
			 * 반환하는데 <pre> 태그 안에 하나의 <span>만 존재하기 때문에
			 * 댓글 내용만 읽어 올 수 있다.
			 **/	
			var reply = $next.first().text();			
			
			if($("#replyForm").css("display") == 'block') {
				$("#replyForm").slideUp(300);
			}
			setTimeout(function() {				
				$("#replyContent").val($.trim(reply));
				$("#replyForm").appendTo($next)
					.slideDown(300);
			}, 300);
			
			/* 댓글 쓰기 폼과 댓글 수정 폼을 같이 사용하기 때문에
			 * 아래와 같이 동적으로 id를 댓글 수정 폼으로 변경한다.
			 **/
			$("#replyForm").find("form")
				.attr({"id": "replyUpdateForm", "data-no": $(this).attr("data-no") });
		}		
		// 앵커 태그의 기본 기능인 링크로 연결되는 것을 취소한다.
		return false;
	});
	
		/* 댓글 수정 폼이 submit 될 때
	 * 최초 한 번은 완성된 html 문서가 화면에 출력되지만 댓글 쓰기를 한 번
	 * 이상하게 되면 ajax 통신을 통해 받은 데이터를 이전 화면과 동일하게
	 * 출력하기 위해 동적으로 요소를 생성하기 때문에 live 방식의 이벤트
	 * 처리가 필요하다. 댓글 쓰기와 수정하기 폼을 하나로 사용하기 때문에
	 * 댓글 수정 또는 쓰기 후에 댓글 쓰기가 제대로 동작하지 않을 수 있다.
	 **/
	$(document).on("submit", "#replyUpdateForm", function() {	
	
		if($("#replyContent").val().length <= 5) {
			alert("댓글은 5자 이상 입력해야 합니다.");
			// Ajax 요청을 취소한다.
			return false;
		}
		
		/* replyNo는 최초 폼이 출력될 때 설정되지 않았다.
		 * 댓글 쓰기 폼과 댓글 수정 폼을 하나로 처리하기 때문에 댓글 번호는
		 * 동적으로 구하여 요청 파라미터에 추가해 줘야 한다. 댓글 수정시
		 * 댓글 번호를 서버로 전송해야 댓글 번호에 해당하는 댓글을 수정할 수 있다. 
		 **/
		var params = $(this).serialize() + "&replyNo=" + $(this).attr("data-no");	
		var updateForm;
		
		$.ajax({
			url: "replyUpdate.ajax",
			type: "post",
			data: params,
			dataType: "json",
			success: function(resultData, status, xhr) {								
	
				/* 아래에서 $("#replyTable").empty(); 가 호출되면
				 * 댓글 쓰기 폼도 같이 문서에서 삭제되기 때문에 백업을 받아야 한다.
				 **/				
				$updateForm = $("#replyForm");
				
				/* jQuery() 함수는 문서에서 선택자를 통해 문서 객체를 선택해 jQuery
				 * 객체로 반환하기 때문에 문서에 선택자로 지정한 요소(문서 객체)가 존재하지
				 * 않아도 jQuery 객체가 반환되어 자바 스크립트 객체로 취급 된다.
				 * if($("#replyForm")) { } 일 때 jQuery 객체 이므로 true가 된다.
				 * 그래서 선택한 요소의 갯수를 반환하는 jQuery의 length 속성을 
				 * 활용해 아래와 같이 현재 문서에 요소가 존재하는지 판단할 수 있다.
				 * if($("#replyForm").length) { } 일 때 0이 반환되면 false가
				 * 되여 if문이 실행되지 않지만 1 이상이 반환되면 true가 되어 if문을
				 * 실행하게 된다.
				 **/
				console.log("update - before empty() : " + $updateForm.length);
				
				// 기존에 화면에 출력되었던 댓글을 비운다.
				$("#replyTable").empty();
				
				/* 아래는 댓글 쓰기와 댓글 수정이 동일하기 때문에 별도의
				 * 함수로 나누는 것이 중복 코드를 최소화 할 수 있다.
				 **/
				$.each(resultData, function(index, value) {
					// 날짜 데이터를 출력 포맷에 맞게 수정
					var date = new Date(value.regDate);
					var strDate = date.getFullYear() + "-" + ((date.getMonth() + 1 < 10) 
							? "0" + (date.getMonth() + 1) : (date.getMonth() + 1)) + "-"  
							+ date.getDate() + "-" + ((date.getHours() < 10) 
							? "0" + date.getHours() : date.getHours()) + ":" 
							+ (date.getMinutes() < 10 ? "0" + date.getMinutes() 
							: date.getMinutes()) + ":" + (date.getSeconds() < 10 
							? "0" + date.getSeconds() : date.getSeconds());			
										
					var result = 
						"<tr class='reply_" + value.no + "'>" 
						+ "<td>"
						+ "	<div class='replyUser'>"
						+ "		<span class='member'>" + value.writer + "</span>"
						+ "	</div>"
						+ "	<div class='replyModify'>"
						+ "		<span class='replyDate'>" + strDate + "</span>"
						+ "		<a href='#' class='modifyReply' data-no='" + value.no + "'>"
						+ "			<img src='images/reply_btn_modify.gif' alt='댓글 수정하기'/>"
						+ "		</a>"
						+ "		<a href='#' class='deleteReply' data-no='" + value.no + "'>"
						+ "			<img src='images/reply_btn_delete.gif' alt='댓글 삭제하기'/>"
						+ "		</a>"
						+ "		<a href='javascript:reportReply('div_" + value.no + "');'>"
						+ "			<img src='images/reply_btn_notify.gif' alt='신고하기'/>"
						+ "		</a>"
						+ "	</div>"
						+ "	<div class='replyContent' id='div_" + value.no + "'>"
						+ "		<pre><span>" + value.reply + "</span></pre>"
						+ "	</div>"
						+ "</td>"
					+ "</tr>";
					
					// 댓글 테이블의 기존 내용을 삭제하고 다시 추가한다.
					$("#replyTable").append(result);					
				});				
				
				/* jQuery() 함수는 문서에서 선택자를 통해 문서 객체를 선택해 jQuery
				 * 객체로 반환하기 때문에 문서에 선택자로 지정한 요소(문서 객체)가 존재하지
				 * 않아도 jQuery 객체가 반환되어 자바 스크립트 객체로 취급 된다.
				 * if($("#replyForm")) { } 일 때 jQuery 객체 이므로 true가 된다.
				 * 그래서 선택한 요소의 갯수를 반환하는 jQuery의 length 속성을 
				 * 활용해 아래와 같이 현재 문서에 요소가 존재하는지 판단할 수 있다.
				 * if($("#replyForm").length) { } 일 때 0이 반환되면 false가
				 * 되여 if문이 실행되지 않지만 1 이상이 반환되면 true가 되어 if문을
				 * 실행하게 된다.
				 **/
				console.log("update after empty() : #replyForm - " 
						+ $("#replyForm").length 
						+ ", $updateFrom : " + $updateForm.length);
				
				/* 댓글 쓰기 폼과 댓글 수정 폼을 같이 사용하기 때문에 아래와 같이 id를
				 * 동적으로 댓글 쓰기 폼으로 변경하고 댓글 수정 버튼이 클릭될 때 추가한 
				 * data-no라는 속성을 삭제 한다. 그리고 end() 메서드를 사용해
				 * 현재 선택된 form의 이전인 #replyForm을 선택하고 이 요소를
				 * body에 추가하기 전에 화면에 보이지 않도록 display 속성을
				 * none로 설정했다. body에 추가한 후 댓글 입력 상자의 내용을
				 * 삭제하기 위해 다시 find() 메서드를 사용해 하위 요소인 댓글 입력
				 * 상자를 찾아 value 값을 초기화 했다.
				 **/
				$updateForm.find("form")
					.attr("id", "replyWriteForm").removeAttr("data-no")
					.end().css("display", "none").appendTo("body")
					.find("#replyContent").val("");
			},
			error: function(xhr, status, error) {
				alert("ajax 실패 : " + status + " - " + xhr.status);
			}
		});		
		// 폼이 submit 되는 것을 취소한다.
		return false;
	});
	
	/* 댓글 삭제 버튼이 클릭되면
	 * 댓글을 삭제하면 동적으로 요소를 생성하기 때문에 live 방식의 이벤트 처리를 해야
	 * 한다. 만약 $(".deleteReply").click(function() {}); 형식으로 이벤트를 
	 * 등록했다면 새로운 댓글을 등록한 후에는 클릭 이벤트 처리가 되지 않는다.
	 **/
	$(document).on("click", ".deleteReply", function() {	
		
		var no = $(this).attr("data-no");
		var writer = $(this).parent().prev().find(".member").text();
		var bbsNo = $("#replyForm input[name=bbsNo]").val();
		var result = confirm(writer + "님이 작성한 " + no +"번 댓글을 삭제하시겠습니까?");
		
		var params = "replyNo=" + no + "&bbsNo=" + bbsNo;	
		if(result) {
			$.ajax({
				url: "replyDelete.ajax",
				type: "post",
				data: params,
				dataType: "json",
				success: function(resultData, status, xhr) {			
					
					/* 아래에서 $("#replyTable").empty(); 가 호출되면
					 * 댓글 쓰기 폼도 같이 문서에서 삭제되기 때문에 먼저 기존 위치로 옮겼다. 					
					 * 댓글 쓰기 폼과 댓글 수정 폼을 같이 사용하기 때문에 댓글 수정하기를
					 * 선택한 상태에서 그 댓글의 삭제하기 버튼을 클릭하면 아래와 같이 id를
					 * 동적으로 댓글 쓰기 폼으로 변경하고 댓글 수정 버튼이 클릭될 때 추가한 
					 * data-no라는 속성을 삭제 한다. 그리고 end() 메서드를 사용해
					 * 현재 선택된 form의 이전인 #replyForm을 선택하고 이 요소를
					 * body에 추가하기 전에 화면에 보이지 않도록 display 속성을
					 * none로 설정했다. body에 추가한 후 댓글 입력 상자의 내용을
					 * 삭제하기 위해 다시 find() 메서드를 사용해 하위 요소인 댓글 입력
					 * 상자를 찾아 value 값을 초기화 했다.
					 **/
					$("#replyForm").find("form")
						.attr("id", "replyWriteForm").removeAttr("data-no")
						.end().css("display", "none").appendTo("body")
						.find("#replyContent").val("");
					
					// 기존에 화면에 출력되었던 댓글을 비운다.
					$("#replyTable").empty();
					
					/* 아래는 댓글 쓰기와 댓글 수정/삭제가 동일하기 때문에 별도의
					 * 함수로 나누는 것이 중복 코드를 최소화 할 수 있다.
					 **/
					$.each(resultData, function(index, value) {
						// 날짜 데이터를 출력 포맷에 맞게 수정
						var date = new Date(value.regDate);
						var strDate = date.getFullYear() + "-" + ((date.getMonth() + 1 < 10) 
								? "0" + (date.getMonth() + 1) : (date.getMonth() + 1)) + "-"  
								+ date.getDate() + "-" + ((date.getHours() < 10) 
								? "0" + date.getHours() : date.getHours()) + ":" 
								+ (date.getMinutes() < 10 ? "0" + date.getMinutes() 
								: date.getMinutes()) + ":" + (date.getSeconds() < 10 
								? "0" + date.getSeconds() : date.getSeconds());
											
						var result = 
							"<tr class='reply_" + value.no + "'>" 
							+ "<td>"
							+ "	<div class='replyUser'>"
							+ "		<span class='member'>" + value.writer + "</span>"
							+ "	</div>"
							+ "	<div class='replyModify'>"
							+ "		<span class='replyDate'>" + strDate + "</span>"
							+ "		<a href='#' class='modifyReply' data-no='" + value.no + "'>"
							+ "			<img src='images/reply_btn_modify.gif' alt='댓글 수정하기'/>"
							+ "		</a>"
							+ "		<a href='#' class='deleteReply' data-no='" + value.no + "'>"
							+ "			<img src='images/reply_btn_delete.gif' alt='댓글 삭제하기'/>"
							+ "		</a>"
							+ "		<a href='javascript:reportReply('div_" + value.no + "');'>"
							+ "			<img src='images/reply_btn_notify.gif' alt='신고하기'/>"
							+ "		</a>"
							+ "	</div>"
							+ "	<div class='replyContent' id='div_" + value.no + "'>"
							+ "		<pre><span>" + value.reply + "</span></pre>"
							+ "	</div>"
							+ "</td>"
						+ "</tr>";
						
						// 댓글 테이블의 기존 내용을 삭제하고 다시 추가한다.
						$("#replyTable").append(result);					
					});
				},
				error: function(xhr, status, error) {
					alert("ajax 실패 : " + status + " - " + xhr.status);
				}
			});
		}
		// 앵커 태그에 의해 페이지가 이동되는 것을 취소한다.
		return false;
	});	
});

/* 아래는 신고하기 버튼을 임시로 연결한 함수 */
function reportReply(elemId) {
	var result = confirm("이 댓글을 신고하시겠습니까?");
	if(result == true) {
		alert("report - " + result);
	}	
}