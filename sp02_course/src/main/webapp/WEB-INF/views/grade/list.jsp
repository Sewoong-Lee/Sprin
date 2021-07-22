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
	<h2>성적 리스트</h2>
	<form action="${path}/grade/list" method="post">
	<fieldset>
		<legend>과목 조회</legend>
		<select name="findkey">
			<option value="cname">과정명</option>
			<option value="sname">학생명</option>
		</select>
		<input type="text" name="findvalue" value="">
		<button>확인</button>
	</fieldset>
	</form>
		<table>
			<tr>
				<th>학번</th>
				<th>학생명</th>
				<th>과정코드</th>
				<th>과정명</th>
				<th>점수</th>
			</tr>
			<c:forEach var="rlist" items="${rlist}">
			<tr>
				<td>${rlist.SNO}</td>
				<td>${rlist.SNAME}</td>
				<td>${rlist.CCODE}</td>
				<td>${rlist.CNAME}</td>
				<td>${rlist.SCORE}</td>
			</tr>
			</c:forEach>
		</table>
</body>
</html>