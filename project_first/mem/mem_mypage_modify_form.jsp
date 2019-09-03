<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<link rel="shortcut icon" href="../images/favicon.png">
<link rel="icon" href="../images/favicon.png">
<link rel="stylesheet" href="../css/insert.css">

<script type="text/javascript">
	function find() {
		new daum.Postcode({
			oncomplete : function(data) {
				// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
				// 예제를 참고하여 다양한 활용법을 확인해 보세요.
				// data={'zonecode':'12345',
				//		 'roadAddress':' 도로명주소',
				//       'jibunAddress':' 지번주소'}

				$('#zipcode').val(data.zonecode);
				$('#addr').val(data.roadAddress);

			}
		}).open();
	}

	function send(f) {

		// 이름/비번/우편번호/주소 입력사항 체크
		var id = f.id.value.trim();
		var name = f.name.value.trim();
		var pwd = f.pwd.value.trim();
		var zipcode = f.zipcode.value.trim();
		var addr = f.addr.value.trim();
		var phon = f.phon.value.trim();
		var birth = f.birth.value.trim();
		var email = f.email.value.trim();
		var gender = f.gender.value.trim();
		var op = f.op.value.trim();

		if (name == '') {

			f.name.value = $
			{
				vo.name
			}
			;
			f.name.focus();
			return;
		}

		if (id == '') {
			f.id.value = $
			{
				vo.id
			}
			;
			f.id.focus();
			return;
		}

		if (pwd == '') {
			alert('비밀번호를 입력하세요');
			f.pwd.value = '';
			f.pwd.focus();
			return;
		}

		if (zipcode == '') {
			alert('우편번호를 입력하세요');
			f.zipcode.value = '';
			f.zipcode.focus();
			return;
		}

		if (addr == '') {
			alert('주소를 입력하세요');
			f.addr.value = '';
			f.addr.focus();
			return;
		}

		if (phon == '') {
			alert('전화번호를를 입력하세요');
			f.phon.value = '';
			f.phon.focus();
			return;
		}

		if (email == '') {
			alert('메일을 입력하세요');
			f.email.value = '';
			f.email.focus();
			return;
		}

		if (birth == '') {
			alert('주민번호를 입력하세요');
			f.birth.value = '';
			f.birth.focus();
			return;
		}

		if (gender == '') {
			alert('성별을 선택하세요');
			f.gender.value = '';
			return;
		}

		if (op == '') {
			f.op.value = 0;
			return;
		}

		f.action = "mypage_modify"; // memInsertAction
		f.submit();

	}
</script>

</head>
<body>
	<div id="insert_box">
		<form>
			<table border="1">
				<input type="hidden" name="idx" value="${vo.idx }">
				<caption>::::회원정보수정::::</caption>
				<tr>
					<th>이름</th>
					<td><input name="name" value="${ vo.name }"
						readonly="readonly"></td>
				</tr>

				<tr>
					<th>아이디</th>
					<td><input name="id" id="id" value="${ vo.id }"
						readonly="readonly"> <span id="msg_id"></span></td>
				</tr>

				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="pwd" value="${ vo.pwd }"></td>
					<span id="msg_pwd"></span>
				</tr>

				<tr>
					<th>우편번호</th>
					<td><input name="zipcode" id="zipcode" value="${ vo.zipcode }">
						<input type="button" value="우편번호찾기" onclick="find();"></td>
				</tr>

				<tr>
					<th>주소</th>
					<td><input name="addr" id="addr" size='60'
						value="${ vo.addr }"></td>

				</tr>


				<tr>
					<th>전화번호</th>
					<td><input name="phon" value="${ vo.phon }"></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="email" name="email" value="${ vo. email}"></td>
				</tr>

				<tr>
					<th>주민등록번호</th>
					<td><input name="birth" value="${ vo.birth }"
						readonly="readonly"></td>
				</tr>
				<tr>

					<th>성별</th>
					<td><input type="radio" name="gender" value="남자">남자<input
						type="radio" name="gender" value="여자">여자</td>
				</tr>
				<th>관리자여부</th>
				<c:if test="${ vo.op == 0 }">
					<td><input type="radio" name="op" value="1"
						disabled="disabled">관리자<input type="radio" name="op"
						value="0" checked="checked">일반회원</td>
				</c:if>
				<c:if test="${ vo.op ==1 }">
					<td><input type="radio" name="op" value="1" checked="checked" />관리자<input
						type="radio" name="op" value="0" disabled="disabled" />일반회원</td>
				</c:if>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="button"
						value="정보수정" id="bt_reg" onclick="send(this.form);"> <input
						type="button" value="취소" id="bt_reg" onclick="javascript:window.location.href='../main'">
					</td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>