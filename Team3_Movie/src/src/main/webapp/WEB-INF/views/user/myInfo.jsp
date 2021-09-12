<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page</title>
<link href = "${path}/resources/css/forMyInfo.css" rel = "stylesheet">
<script type="text/javascript">
	$(function(){
		$('#withdraw').click(function(e){
			e.preventDefault();
			var user_id = "${member.user_id}";
			var ans = confirm("이용하셨던 모든 내역이 소멸됩니다.\n탈퇴를 진행하시겠습니까 ?");
			if (ans) {
				location.href = "${path}/user/withdraw?user_id="+user_id;
			}
		});
	});
</script>
</head>
<body>
<%@ include file = "../include/header.jsp" %>
	<section class = "container" style = "max-width:560px;">
	<div><a href = "${path}/user/modifyInfo?user_id=${member.user_id}">회원 정보 수정</a></div>
	<table>
		<tr>
			<td rowspan="6">
				<div class = "imgBox">
					<c:if test="${member.file_name != null}">
						<img src = "${path}/uploadimg/${member.file_name}" id = "profile"><br>
					</c:if>
					<c:if test="${member.file_name == null}">
						<img src = "${path}/resources/img/person_icon.png"><br>
					</c:if>
				</div>
				${member.user_id}
			</td>
			<td>이름</td>
			<td>${member.user_name}</td>
		</tr>
		<tr>
			<td>등급</td>
			<td>${member.admin}</td>
		</tr>
		<tr>
			<td>관심 태그</td>
			<td>
				<c:forEach var = "tag" items = "${tagList}">
					<li>${tag.GENRE_NAME}</li>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td>
				<button onclick="location.href = '${path}/user/changePw'">변경하기</button>
			</td>
		</tr>
		<tr>
			<td>연락처</td>
			<td id = "phoneNumber">
				<script type="text/javascript">
					var s = "${member.tel}"
					var f = s.substr(0,3);
					var l = s.substr(7,4);
					var phoneNum = f+"-"+"****"+"-"+l;
					document.getElementById('phoneNumber').innerHTML = phoneNum;
				</script>
			</td>
		</tr>
		<tr>
			<td>주소</td>
			<td>${member.addr1} ${member.addr2}</td>
		</tr>
		<tr>
			<td>회원 탈퇴</td>
			<td><button id = "withdraw">탈퇴하기</button></td>
		</tr>
	</table>
	</section>
	<footer>
		Copyright 2021 CCV All Rights Reserved.
	</footer>
</body>
</html>