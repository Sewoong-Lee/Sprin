<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CCV</title>
<script type="text/javascript">
	$(function(){
		$('#clickState').click(function(){
			$(this).attr('class'). 
		});
	});
</script>
<style>
	ul {
		list-style-type: none;
		margin: 0;
		padding: 0;
		overflow: hidden;
		background-color: #333;
	}

	li {
		float: left;
	}

	li a {
		display: block;
		color: white;
		text-align: center;
		padding: 14px 16px;
		text-decoration: none;
	}

	li a:hover {
		background-color: #111;
	}
	li .clicked {
		background-color: red;
	}
</style>
</head>
<body>
<%@ include file = "../include/header.jsp" %>
	<h2>내 정보 찾기</h2>
<ul>
	<li><a href="" class = "clickState clicked" id = "findId">아이디 찾기</a></li>
	<li><a href="" class = "clickState clicked" id = "findPw">비밀번호 찾기</a></li>
</ul>
	<div>
		
	</div>
</body>
</html>