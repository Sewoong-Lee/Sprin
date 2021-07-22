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
		//세션에 따라 버튼 활성화
		if('${sessionScope.userid}' == ''){
			$('#login').show();
			$('#logout').hide();
			$('#userdiv').hide();
		}else{
			$('#logout').show();
			$('#login').hide();
			$('#userdiv').show();
		}
		
		$('#logout').click(function() {
			if(confirm('로그아웃 하쉴?')){
				location.href = '${path}/logout';
			}
		});
		
		$('#login').click(function() {
			location.href = '${path}/login';
		});
		
		//유저아이디 클릭시
		$('#auserid').click(function(e) {
			e.preventDefault();  //하이퍼링크의 기본기능 소멸
			location.href = '${path}/member/info';
			
			//뷰에서 보낼떄
			//location.href = '${path}/member/info?userid=${sessionScope.userid}';
		});
		
	})
	
	
	
</script>
</head>
<body>
	<header>
		<button id="login">로그인</button>  <button id="logout">로그아웃</button>
		<h2>게시물 관리</h2> 
		<div id="userdiv"> <a href="" id="auserid">${sessionScope.userid}</a> 님 하이염 </div>
	</header>
</body>
</html>