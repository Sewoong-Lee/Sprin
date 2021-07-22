<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>로그인</h2>
	<form action="login" method="post">
	아이디 <input type="text" name="userid" value="${userid}">
	비밀번호 <input type="password" name="passwd">
	<button>확인</button>
	</form>
	${msg}
	
</body>
</html>