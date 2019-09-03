<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="../css/insert.css">
<script src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>


<script type="text/javascript">
var regular_id = /^[a-zA-Z0-9]{8,}$/;
var regular_pwd= /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$/; // 8~12 영,숫자,특수문자 조합 
var regular_name = /^[가-힣]{2,5}$/; //2~5글자 한글
var regular_email = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/; //이메일
var regular_birth = /^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))-[1-4][0-9]{6}$/; //주민번호 형식검사
var regular_phon = /^(?:(010-\d{4})|(01[1|6|7|8|9]-\d{3,4}))-(\d{4})$/; // 핸드폰번호 유효성


$(document).ready(function(){
	   //id입력창에 입력시마다 호출
	   $('#id').keyup(function(event){
		   var id = $(this).val();
		   
		 //아이디패턴검사
		   if(regular_id.test(id)==false){
			   $('#msg_id').css('color','red');
			   $('#msg_id').html('8자리이상 영,숫자만 입력하세요');
			   $('#bt_reg').attr('disabled',true);//비활성화
			   return;
		   }		  
		  
		 	
			   
			
		   
		   //서버로 중복검사 요청
		   $.ajax({
			   url		:'check_id', //MemCheckIdAction
			   data		:{'id': id},   // check_id?id=hong123
			   dataType	: 'json',
			   success	: function(res_data){
				   //res_data = {'result':'yes'} or {'result':'no'}
				   if(res_data.result == 'yes'){//사용가능

					   $('#msg_id').css('color','blue');
					   $('#msg_id').html('사용가능한 ID입니다');
					   $('#bt_reg').attr('disabled',false);//활성화
					 					   
				   }else{//불가능:id사용중
					   
					   $('#msg_id').css('color','red');
					   $('#msg_id').html('이미 사용중인 ID입니다');
					   $('#bt_reg').attr('disabled',true);//비활성화
					  				   }
			   }
		   });
		   //end ajax
		   
	   });
	   $('#pwd').keyup(function(event){
		   var pwd = $(this).val();
		   
		   if(regular_pwd.test(pwd)==false){
			   $('#msg_pwd').css('color','red');
			   $('#msg_pwd').html('8~16 자리 영,숫,특수문자 조합으');
			   $('#bt_reg').attr('disabled',true);//비활성화			   
		   }else{
			   $('#msg_pwd').css('color','blue');
			   $('#msg_pwd').html('사용가능한 비밀번호입니다');
			   $('#bt_reg').attr('disabled',false);//활성화
			 return;
		   }
		   
	   });
	   
	   $('#name').keyup(function(event){
		   var name = $(this).val();
		   
		   if(regular_name.test(name)==false){
			   $('#msg_name').css('color','red');
			   $('#msg_name').html('2~5 자리 한글만입력하세요');
			   $('#bt_reg').attr('disabled',true);//비활성화			   
		   }else{
			   $('#msg_name').css('color','blue');
			   $('#msg_name').html('사용가능한 이름입니다');
			   $('#bt_reg').attr('disabled',false);//활성화
			 return;
		   }
		   
	   });
	  
	   $('#email').keyup(function(event){
		   var email = $(this).val();
		   
		   if(regular_email.test(email)==false){
			   $('#msg_email').css('color','red');
			   $('#msg_email').html('올바른 이메일형식이 아닙니다');
			   $('#bt_reg').attr('disabled',true);//비활성화			   
		   }else{
			   $('#msg_email').css('color','blue');
			   $('#msg_email').html('사용가능한 메일입니다');
			   $('#bt_reg').attr('disabled',false);//활성화
			 return;
		   }
		   
	   });
	   
	   $('#birth').keyup(function(event){
		   var birth = $(this).val();
		   
		   if(regular_birth.test(birth)==false){
			   $('#msg_birth').css('color','red');
			   $('#msg_birth').html('올바른 형식이 아닙니다');
			   $('#bt_reg').attr('disabled',true);//비활성화			   
		   }else{
			   $('#msg_birth').css('color','blue');
			   $('#msg_birth').html('올바른 주민번호입니다');
			   $('#bt_reg').attr('disabled',false);//활성화
			 return;
		   }
		   
	   });
	   
	   $('#phon').keyup(function(event){
		   var phon = $(this).val();
		   
		   if(regular_phon.test(phon)==false){
			   $('#msg_phon').css('color','red');
			   $('#msg_phon').html('올바른 형식이 아닙니다');
			   $('#bt_reg').attr('disabled',true);//비활성화			   
		   }else{
			   $('#msg_phon').css('color','blue');
			   $('#msg_phon').html('올바른 번호입니다');
			   $('#bt_reg').attr('disabled',false);//활성화
			 return;
		   }
		   
	   });
	   
	   
});   



   function find(){
	    new daum.Postcode({
	        oncomplete: function(data) {
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
   
   function send(f){
	   
	   // 이름/비번/우편번호/주소 입력사항 체크
	   var id			= f.id.value.trim();
	   var name	= f.name.value.trim();
	   var pwd		= f.pwd.value.trim();
	   var zipcode	= f.zipcode.value.trim();
	   var addr		= f.addr.value.trim();
	   var phon		= f.phon.value.trim();
	   var birth		= f.birth.value.trim();
	   var email	= f.email.value.trim();
	   var gender	= f.gender.value.trim();
	   
	 
	   
	   if(name==''){
			alert('이름을 입력하세요');
			f.name.value='';
			f.name.focus();
			return;
		}
	   
	   if(id==''){
			alert('아이디 입력하세요');
			f.id.value='';
			f.id.focus();
			return;
		}
	   
	   if(pwd==''){
			alert('비밀번호를 입력하세요');
			f.pwd.value='';
			f.pwd.focus();
			return;
		}
	   
	   if(zipcode==''){
			alert('우편번호를 입력하세요');
			f.zipcode.value='';
			f.zipcode.focus();
			return;
		}
	   
	   if(addr==''){
			alert('주소를 입력하세요');
			f.addr.value='';
			f.addr.focus();
			return;
		}
	   
	 
	   
	   if(phon==''){
			alert('전화번호를를 입력하세요');
			f.phon.value='';
			f.phon.focus();
			return;
		}
	   
	   if(email==''){
			alert('메일을 입력하세요');
			f.email.value='';
			f.email.focus();
			return;
		}
	   
	   if(birth==''){
			alert('주민번호를 입력하세요');
			f.birth.value='';
			f.birth.focus();
			return;
		}
	   
	   if(gender==''){
			alert('성별을 선택하세요');
			f.gender.value='';			
			return;
		}
	   
	   
	   
	   
	   f.action = "insert"; // memInsertAction
	   f.submit();
	   
   }
   
</script>

</head>
<body>

<div id="insert_box">
<form>
	<table border="1">
		<caption>::::회원가입::::</caption>
		<tr>
			<th>이름</th>
			<td>
				<input name="name" id="name">
				<span id="msg_name"></spqn>
			</td>
		</tr>
		
		<tr>
			<th>아이디</th>
			<td>
			    <input name="id"  id="id" >
			    <span id="msg_id"></span>
			</td>
		</tr>
		
		<tr>
			<th>비밀번호</th>
			<td><input type="password" name="pwd" id="pwd">
			<span id="msg_pwd"></span>
			</td>
		</tr>
		
		<tr>
			<th>우편번호</th>
			<td>
			    <input name="zipcode" id="zipcode">
			    <input type="button" value="우편번호찾기"  onclick="find();">
			</td>
		</tr>
		
		<tr>
			<th>주소</th>
			<td><input name="addr" id="addr" size='60' ></td>
						
		</tr>
		
	
		<tr>
			<th>전화번호</th>
			<td>
				<input  name="phon" name="phon" id="phon">
				<span id="msg_phon"></span>
			</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>
				<input type="email" name="email" id="email">
				<span id="msg_email"></span>
			</td>
		</tr>
		
		<tr>
			<th>주민등록번호</th>
			<td>
				<input  name="birth" id="birth">
				<span id="msg_birth"></span>
			</td>
		</tr>
		<tr>
			<th>성별</th>
			<td><input type="radio" name="gender" value="남자">남자<input type="radio" name="gender" value="여자">여자</td>
			
		</tr>
		
		
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="회원가입" 
						id="bt_reg"  onclick="send(this.form);">
			</td>
		</tr>
	</table>
</form>
</div>

</body>
</html>