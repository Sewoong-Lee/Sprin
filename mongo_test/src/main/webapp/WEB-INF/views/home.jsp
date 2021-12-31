<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript">
	$(function() {
		

		
		$('#btn-mongodbinputtest').click(function(e) {
			e.preventDefault();  //버튼의 기본기능 제거
			var name = $('#inname').val();
			var age = $('#inage').val();
			/* alert(name+"  "+age); */
			console.log(name);
			
			
		});
		
	};
	</script>
</head>
<body>
<div id="mongodbinputdiv">
	<button name="subject" class="btn btn-warrung" id="btn-mongodbinputtest">몽고디비 값 입력</button>
	<input type="text" id="inname" placeholder="name...">
	<input type="text" id="inage" placeholder="age....">
</div>
<hr>
<div id="mongodblistdiv">
	<button name="subject" id="btn-mongolisttest">몽고디비 리스트 출력</button>
	<input type="text" id="searchname" placeholder="검색 이름">
	<input type="text" id="searchage" placeholder="검색 나이">
	<input type="text" id="option" placeholder="all or nat">
</div>



<button id="insertMongo" onclick="showCollections()">showCollections</button>
</body>
</html>