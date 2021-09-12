<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page</title>
<link href = "${path}/resources/css/forChangePw.css" rel = "stylesheet">
<script type="text/javascript">
	$(function(){
		$('#changePw').click(function(e){
			e.preventDefault();
			var oldPw = $('#passwd').val();
			if (oldPw == ""){
				$('#passwd').attr('type', 'text');
				$('#passwd').css('color', 'red').val("비밀번호를 입력해주세요.");
				$('#passwd').click(function(){
					$('#passwd').css('color', 'black').val("");
					$('#passwd').attr('type', 'password');
				});
			}
			else {
				console.log(oldPw);
				$.ajax({
					url:"${path}/user/checkPw",
					type:"post",
					data:{oldPw},
					dataType:"text",
					success:function(data){
 						if (data == 1){
							location.href = "${path}/user/modifyPw";
 						}
						else {
							alert("비밀번호를 확인해주세요.");
							$('#passwd').val("");
							
						}
					},
					error:function(err){
						console.log(err);
						alert("실패");
					}
					
				});
			}
		});
	});
</script>
</head>
<body>
<%@ include file = "../include/header.jsp" %>
	<div class = "phase">
		<h3>비밀번호 변경을 위한 본인 인증 단계입니다.</h3>
	</div>
	<div class = "wrapper">
		<form>
			<label for = "passwd">기존 비밀번호</label><br>
			<input type="password" id = "passwd" name = "passwd" autocomplete="current-password" placeholder="사용 중인 비밀번호를 입력해주세요.">
			<button id = "changePw">변경</button>
		</form>
	</div>
</body>
</html>