<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page</title>
<script type="text/javascript">
	$(function(){
		$('#toNewPw').click(function(e){
			e.preventDefault()
			var newPw = $('#newPw').val();
			var recheck = $('#recheck').val();
			if (newPw == ''){
				$('#newPw').attr('type', 'text');
				$('#newPw').css('color', 'red').val("비밀번호를 입력해주세요.");
				$('#newPw').click(function(){
					$('#newPw').css('color', 'black').val("");	
					$('#newPw').attr('type', 'password');
				});
			}
			else if (recheck == '' || newPw != recheck){
				$('#newPw').attr('type', 'text');
				$('#newPw').css('color', 'red').val("비밀번호가 일치하지 않습니다.");
				$('#recheck').val("");	
				$('#newPw').click(function(){
					$('#newPw').css('color', 'black').val("");	
					$('#newPw').attr('type', 'password');
				});
			}
			else {
				$('#updatePw').submit();
			}
		});
	});
</script>
</head>
<body>
<%@ include file = "../include/header.jsp" %>
	<h2>비밀번호 변경</h2>
	<form action="${path}/user/updatePw" method="post" id = "updatePw">
		<table>
			<tr>
				<td>새로운 비밀번호</td>	
				<td><input type = "password" id = "newPw" name = "newPw"></td>	
			</tr>
			<tr>
				<td>비밀번호 확인</td>	
				<td><input type = "password" id = "recheck"></td>	
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button id = "toNewPw">변경 완료</button>
				</td>	
			</tr>
		</table>
	</form>
</body>
</html>