<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- contextpath 변수 생성 -->
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>학생 전체 조회</h2>
	<form action="${path}/student/list" method="post">
		<select name="findkey">
			<option value="sno">학번</option>
			<option value="sname">이름</option>
		</select>
		<input type="text" name="findvalue"> <br>
		<button>조회</button>
		<button type="button" onclick="location.href='${path}/student/add'">학생 등록</button>
	</form>
	
	<div>
	<table border="1">
		<tr>
			<th>학번</th>
			<th>이름</th>
			<th>전번</th>
			<th>우편번호</th>
			<th>주소</th>
			<th>주소</th>
			<th>생성일</th>
		</tr>
		<c:forEach var="slist" items="${slist}">
		<tr>
			<td> <a href="${path}/student/detail?sno=${slist.sno}"> ${slist.sno} </a></td>
			<td>${slist.sname}</td>
			<td>${slist.tel}</td>
			<td>${slist.zip}</td>
			<td>${slist.addr1}</td>
			<td>${slist.addr2}</td>
			<td>${slist.regdate}</td>
		</tr>
		</c:forEach>
	</table>
	</div>
	
</body>
</html>