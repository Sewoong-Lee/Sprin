<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>원의 넓이 구하기</h2>
	<form action="area" method="post">
	이름 <input type="text" name="name" value="${name}">
	반지름 <input type="number" name="area" value="${area}" step="0.1">
	<button>확인</button>
	</form>
	${name}
	${ar}
</body>
</html>