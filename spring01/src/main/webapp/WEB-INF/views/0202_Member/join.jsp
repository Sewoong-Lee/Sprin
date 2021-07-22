<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>회원가입</h2>
	<form action="/my/member/join" method="post">
		아이디 <input type="text" name="userid"> <br>
		비번 <input type="password" name="passwd"> <br>
		이름 <input type="text" name="name"> <br>
		<button>확인</button>
	</form>
</body>
</html>