<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>파라메터 전달</h2>
	<form action="getparam" method="post">
		이름 <input type="text" name="name" > <br>
		나이 <input type="number" name="age" >
		
		<button>확인</button>
	</form>
	${name} <br>
	${age}
</body>
</html>