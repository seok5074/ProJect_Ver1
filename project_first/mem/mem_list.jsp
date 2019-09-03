<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

		function del(idx){
			//삭제확인
			if(confirm('삭제 하시겠습니까?')==false)return;
					
			location.href = 'delete?idx='+idx; 		
		}
				
		function modify(idx){
									
				//수정폼 띄우기
			location.href = 'modify_form?idx='+idx;
			
		}
</script>
</head>
<body>
	<div id="main_box">
		<h1>::::회원관리::::</h1>
		<table border="1">
			<!-- title -->
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>아이디</th>
				<th>비밀번호</th>
				<th>주소</th>
				<th>이메일</th>
				<th>전화번호</th>
				<th>생년월일</th>
				<th>성별</th>
				<th>관리자여부</th>
				<th>수정/삭제</th>
			</tr>
			
	
			<c:forEach var="mem" items="${ list }">
			<form>
				<tr>
				<div>
					<td>${ mem.idx }</td>
					<td>${ mem.name }</td>
					<td>${ mem.id }</td>
					<td>${ mem.pwd }</td>
					<td>${ mem.addr }</td>
					<td>${ mem.email }</td>
					<td>${ mem.phon }</td>
					<td>${ fn:substring(mem.birth,0,6) }</td>
					<td>${ mem.gender }</td>
					<td>${ mem.op }</td>
				</div>
					<td>
						<input type="button" value="수정" onclick="modify(${mem.idx});">
						<input type="button" value="삭제" onclick="del(${mem.idx});">
					</td>
				</tr>
			</form>
			</c:forEach>
		</table>
	
	
	</div>

</body>
</html>