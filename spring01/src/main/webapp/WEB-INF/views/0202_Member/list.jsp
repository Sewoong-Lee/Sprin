<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

	if('${msg}' != ''){
		alert('${msg}');
	}
	
	if('${cnt}' != ''){
		alert('${cnt}건 삭제');
	}

</script>
</head>
<body>
	<h2>회원 리스트</h2>
	<form action="/my/member/list">
		<button>조회</button>
		<button type="button" onclick="location.href ='/my/member/join'">회원등록</button>
	</form>
	<c:forEach var="list" items="${list}">
		아이디 <a href ="/my/member/detail?userid=${list.userid}">${list.userid}</a> <br>
		비번 ${list.passwd} <br>
		이름 ${list.name} <br>
		<hr>
	</c:forEach>
	
	
</body>
</html>