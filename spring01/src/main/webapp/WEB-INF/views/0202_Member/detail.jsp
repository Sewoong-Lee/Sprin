<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

	function deletchack() {
		var passwd = document.getElementById('passwd').value;
		var chackpasswd = document.getElementById('chackpasswd').value;
		console.log(passwd);
		console.log(chackpasswd);
		
		if(chackpasswd == ''){
			alert('비번 입력');
		}
		
		if(passwd == chackpasswd){
			location.href = '/my/member/delet?userid=${mdto.userid}';
		}else{
			alert('일치가 아니된다링~');
		}
		
		
		
		
		
	}


</script>
</head>
<body>
	<h2>상세조회</h2>
	아이디 <input type="text" id="userid" value="${mdto.userid}" readonly="readonly"> <br>
	<input type="password" id="passwd" value="${mdto.passwd}" hidden=""> <br>
	비번 <input type="password" id ="chackpasswd"> <br>
	이름 <input type="text" name="name" value="${mdto.name}"> <br>
	
	아이디 ${mdto.userid} <br>
	비번 ${mdto.passwd} <br>
	이름 ${mdto.name} <br>
	<button onclick="location.href = '/my/member/modify?userid=${mdto.userid}'">수정</button>
	<button onclick="deletchack()">삭제</button>
</body>
</html>