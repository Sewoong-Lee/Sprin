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
		<h4>헤더</h4> 
		<div id="userdiv"> <a href="" id="auserid">${sessionScope.userid}${sessionScope.name}</a> 님 하이염 </div>
	</header>
	<nav>
		<a href="${path}/board/">게시판</a>
		<a href="${path}/board/add">게시물 등록</a>
		<a href="${path}/member/join">회원 등록</a>
	</nav>
</body>
</html>