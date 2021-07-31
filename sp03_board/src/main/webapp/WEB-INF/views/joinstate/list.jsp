<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includefile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>회원가입 경로 현황</h2>
	<table border="1">
		<tr align="center">
			<th>가입일</th>
			<th>일반 가입</th>
			<th>네이버 간편 가입</th>
			<th>카카오 간편 가입</th>
		</tr>
		<c:forEach var="jlist" items="${jlist}">
		<tr align="center">
			<td>${jlist.JOINDATE}</td>
			<td>${jlist.JOIN_ORG}</td>
			<td>${jlist.JOIN_NAV}</td>
			<td>${jlist.JOIN_KAKA}</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>