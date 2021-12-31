<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>  
<html>
<head>
	<title>Home</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript">
	$(function() {
		//인서트
		$('#btn-mongodbinputtest').click(function() {
			var name = $('#inname').val();
			var age = $('#inage').val();
			//alert("${path}");
			
			var trans_object = {
					'name': name,
					'age': age
			}
			var trans_json = JSON.stringify(trans_object); //제이슨으로 변환
			
			$.ajax({
				url:'${path}/controller/mongoinputajax',  //restfull하게 설계
				type:'POST',  //메소드 방식
				dataType:'json', //서버에서 받는 타입
				data:trans_json,
				contentType:'application/json',  //보내는 데이터 타입
				mimeType: 'application/json',
				
				//contentType:'application/json',  //보내는 데이터 타입
				//data:JSON.stringify({city_code}),  //보낼 데이터
				//dataType:'json', //서버에서 받는 타입
				//dataType:'text', //서버에서 받는 타입
				
				success:function(retValu){
					alert('인서트 에이젝스 성공..' + '/'+retValu.val)
				},
				error:function(retValu, status, err){
					console.log(err);
					alert('인서트 에러: '+retValu+" status: "+status+" err: " + err);
				}
			});
		});
		
		//조회
		$('#btn-mongolisttest').click(function() {
			var searchname = $('#searchname').val();
			var searchage = $('#searchage').val();
			var option = $('#option').val();
			//alert(searchname+"  "+searchage+" "+option);
			
			var trans_object = {
					'searchname': searchname,
					'searchage': searchage,
					'option': option
			}
			var trans_json = Json.stringify(trans_objeect); //제이슨으로 변환
			
			$.ajax({
				url:'${path}/controller/mongolistajax',  //restfull하게 설계
				type:'POST',  //메소드 방식
				dataType: 'json',
				data:trans_json,
				contentType: 'application/json',
				mimeType: 'application/json',
				success:function(retValu){
					alert('리스트 에이젝스 성공..' + '/'+retValu.val)
				},
				error:function(retValu, status, err){
					console.log(err);
					alert('리스트 에러: '+retValu+" status: "+status+" err: " + err);
				}
			});
		});
		
		
		
	});
	</script>
</head>
<body>
<div id="mongodbinputdiv">
	<button name="subject" class="btn btn-warrung" id="btn-mongodbinputtest">몽고디비 값 입력</button>
	<input type="text" id="inname" placeholder="name..."/>
	<input type="text" id="inage" placeholder="age...."/>
</div>
<hr>
<div id="mongodblistdiv">
	<button name="subject" id="btn-mongolisttest">몽고디비 리스트 출력</button>
	<input type="text" id="searchname" placeholder="검색 이름"/>
	<input type="text" id="searchage" placeholder="검색 나이"/>
	<input type="text" id="option" placeholder="all or nat"/>
</div>
</body>
</html>
