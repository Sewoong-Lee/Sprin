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
	<h2>${sessionScope.userid} 상세정보</h2>
	
	<table>
		<tr>
			<th>아이디</th>
			<td>${member.userid}</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${member.email}</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td>${member.zip}</td>
		</tr>
		<tr>
			<th>도로명주소</th>
			<td>${member.addr1}</td>
		</tr>
		<tr>
			<th>상세주소</th>
			<td>${member.addr2}</td>
		</tr>
		<tr>
			<th>사진</th>
			<td><img alt="사진" src="${path}/uploadimg/${member.filename}" width="100"></td>
			<!-- 이미지 불러올때 컨텍스트 패스 필수 -->
		</tr>
		<tr>
			<th>개인소개</th>
			<td><textarea rows="5" cols="21">${member.memo}</textarea>  </td>
		</tr>
		<tr>
			<td><button type="button" id="btnJoin">수정</button> </td>
			<td><button type="button" id="btnJoin">탈퇴</button> </td>
		</tr>
			
	</table>
	
	
</body>
</html>