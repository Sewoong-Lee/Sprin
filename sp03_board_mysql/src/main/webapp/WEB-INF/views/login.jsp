<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./include/includefile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	if('${rmap.msg}' != ''){
		alert('${rmap.msg}');
	}
	
	$(function() {
		$('#btn').click(function() {
			var userid = $('#userid').val();
			var passwd = $('#passwd').val();
			
			if(userid == ''){
				alert('아이디 입력');
			}else if(passwd == ''){
				alert('비밀번호 입력');
			}else{
				loginform.submit();
			}
		});
		
		
	});


</script>
</head>
<body>
	<h2>로그인</h2>
	<form action="${path}/login" name="loginform" method="post">
	아이디 <input type="text" name="userid" id="userid"> <br>
	비밀번호 <input type="password" name="passwd" id="passwd"> <br>
	<button id="btn" type="button">로그인</button>
	 <a href="${apiURL}"><img alt="" src="${path}/resources/images/btnGnaver.png" height="30"></a>
	</form>
</body>
</html>