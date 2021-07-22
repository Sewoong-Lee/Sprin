<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

	function passwdchack() {
		var passwd = document.getElementById('passwd').value;
		var checkpasswd = document.getElementById('checkpasswd').value;
		
		if(passwd == checkpasswd){
			document.getElementById('check1').value = '일치';

		}else{
			document.getElementById('check1').value = '달라~달라~';
		}
		
	}
	
	function passwdchack2() {
		var newpasswd = document.getElementById('newpasswd').value;
		var checknewpasswd = document.getElementById('checknewpasswd').value;
		
		if(newpasswd == checknewpasswd){
			document.getElementById('check2').value = '일치';

		}else{
			document.getElementById('check2').value = '달라~달라~';
		}
	} 

	function modifycheck() {
		const passwd = modifyform.passwd.value;
		const checkpasswd = modifyform.checkpasswd.value;
		const newpasswd = modifyform.newpasswd.value;
		const checknewpasswd = modifyform.checknewpasswd.value;
		
		//alert(passwd+checkpasswd+newpasswd+checknewpasswd);
		
		if(passwd != checkpasswd){
			alert('기존과 불일치');
			modifyform.passwd.focus();
			return;
		}else if(newpasswd != ''){
			if(newpasswd != checknewpasswd){
				alert('신규 비번 불일치');
				modifyform.newpasswd.focus();
				return;
			}
		}
		modifyform.submit();
	}
	
	
</script>
</head>
<body>
	<h2>수정폼</h2>
	<form action="/my/member/modify" method="post" name="modifyform">
		아이디 <input type="text" name="userid" value="${mdto.userid}" readonly="readonly"> <br>
		
		<input type="hidden" id="passwd" value="${mdto.passwd}" name="passwd"> <br>
		
		비번 <input type="password" id="checkpasswd" name="checkpasswd" onchange="passwdchack()"> <br>
		<input type="text" id="check1"> <br>
		바꿀비번 <input type="password" name="newpasswd" id="newpasswd"> <br>
		바꿀비번 확인 <input type="password" name="checknewpasswd" id="checknewpasswd" onchange="passwdchack2()"> <br>
		<input type="text" id="check2"> <br>
		
		이름 <input type="text" name="name" value="${mdto.name}"> <br>
		<button type="button" onclick="modifycheck()">확인</button>
	</form>
</body>
</html>