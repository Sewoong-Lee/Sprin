<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GO TO MOVIE 로그인</title>
<link href = "${path}/resources/css/forLogin.css" rel="stylesheet"/>
<script>
	if ("${msg}" != ""){
		alert("${msg}");
	}
	$(function(){
		$('#checkLogin').click(function(e){
			e.preventDefault();
			if($('#user_id').val() == ''){
				$('#user_id').css('color', 'red').val("아이디를 입력해주세요.");
				$('#user_id').focus();
				$('#user_id').click(function(){
					$('#user_id').css('color', 'black').val("");
				});
			}
			else if ($('#passwd').val() == ''){
				$('#passwd').attr('type', 'text');
				$('#passwd').css('color', 'red').val("비밀번호를 입력해주세요.");
				$('#passwd').focus();
				$('#passwd').click(function(){
					$('#passwd').css('color', 'black').val("");	
					$('#passwd').attr('type', 'password');
				});
			}
			else {
				$('#login').submit();
			}
		});
		$('#naverLogin').click(function(e){
			e.preventDefault();
		});
	});

</script>
</head>
<body>
<%@ include file = "../include/header.jsp" %>
	<form action = "${path}/user/loginCheck" method="post" id = "login">
		<table>
			<tr>
				<th><h2>로그인</h2></th>
			</tr>
			<tr>
				<td><b>아이디</b></td>
			</tr>
			<tr>
				<td><input type = "text" id = "user_id" name = "user_id" autocomplete="username" placeholder="아이디를 입력해주세요."></td>
			</tr>
			<tr>
				<td><b>비밀번호</b></td>
			</tr>
			<tr>
				<td><input type = "password" id = "passwd" name = "passwd" autocomplete="current-password" placeholder="비밀번호를 입력해주세요."></td>
			</tr>
			<tr>
				<td><button id = "checkLogin">입장</button></td>
			</tr>
			<tr>
				<td>
					<a href ="${apiURL}"><img src = "${path}/resources/img/naverBtn.png" width="300"></a>
				</td>
			</tr>
			<tr>
				<td id = "signUpF">
					<a href = "${path}/user/InfoInquiry">아이디 또는 비밀번호 찾기</a><br>
					<b><a href = "${path}/emailAuth">회원이 아니신가요 ?</a></b>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>