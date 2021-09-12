<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "./include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>본인 인증</title>
<link href = "${path}/resources/css/forEmailAuth.css" rel = "stylesheet"/>
<script type="text/javascript">
	function email_check(email) {
		var reg = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
		return reg.test(email);
	}
	$(function(){
		$('#emailAuth').click(function(e){
	 		e.preventDefault();
	 		var email = $('#email').val();
			if (email == ""){
				$('#email').css('color', 'red').val('!이메일 입력은 필수입니다.');
				$('#email').click(function(){
					$('#email').css('color', 'black').val('');
				});
			}
			else if(!email_check(email)){
				$('#email').css('color', 'red').val('!이메일 형식에 맞게 입력해주세요.');
				$('#email').click(function(){
					$('#email').css('color', 'black').val('');
				});
			}
			else {
				var email = $('#email').val();
				$.ajax({
					url:'${path}/emailCheck',
					type:'get',
				        data:{email}, 
				        dataType:'json',
				        success:function(data){
				        	if (data.code == 1) {
							location.href = "${path}/emailAuthCheck";
				        	}
				        	else {
				        		alert(data.msg);
				        		location.href = "${path}/user/login";
				        	}
				        },
				        error:function(e){
				        	console.log(e);
						alert("실패");
				        }
				});
			}
		});
	});
</script>
</head>
<body>
<%@ include file = "./include/header.jsp" %>
	<div class = "phase">
		<h3>STEP 1. 회원가입을 위한 본인 인증 단계입니다.</h3>
	</div>
	<div class = "wrapper">
		<label for = "email">E-mail</label><br>
		<input type="email" id = "email" name = "email" placeholder="@를 포함한 이메일을 입력해주세요."><br>
		<button id = "emailAuth">이메일 전송</button>
	</div>
</body>
</html>