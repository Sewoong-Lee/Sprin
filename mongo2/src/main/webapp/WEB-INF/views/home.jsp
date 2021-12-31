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
		$('#btn-mongodbinputtest').click(function(e) {
			e.preventDefault();  //버튼의 기본기능 제거
			var name = $('#inname').val();
			var age = $('#inage').val();
			//alert("${path}");
			
			var trans_object = {
					'name': name,
					'age': age
			}
			var trans_json = JSON.stringify(trans_object); //제이슨으로 변환
			
			$.ajax({
				url:'${path}/controller/mongoinputajax',
				type:'POST',  //메소드 방식
				dataType:'json', //서버에서 받는 타입
				data:JSON.stringify(trans_object),
				contentType:"application/json; charset=UTF-8",  //보내는 데이터 타입
				//mimeType: 'application/json',
				
				//contentType:'application/json',  //보내는 데이터 타입
				//data:JSON.stringify({city_code}),  //보낼 데이터
				//dataType:'json', //서버에서 받는 타입
				//dataType:'text', //서버에서 받는 타입
				
				success:function(retValu){
					alert('인서트 에이젝스 성공..' + '/'+retValu.val)
				},
				error:function( err){
					console.log(err);
					//console.log('인서트 에러: '+retValu+" status: "+status+" err: " + err);
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
		//삭제
		$('#btn-mongoremove').click(function() {
			var value = $('#removename').val();
			alert(value);
			
			$.ajax({
				url:'${path}/controller/mongoremove',  //restfull하게 설계
				type:'POST',  //메소드 방식
				data:{value},
				success:function(retValu){
					alert('리스트 에이젝스 성공..' + '/'+retValu.val)
				},
				error:function(retValu, status, err){
					console.log(err);
					alert('리스트 에러: '+retValu+" status: "+status+" err: " + err);
				}
			});
		});
		
		//수정
		$('#btn-mongoupdate').click(function() {
			var orgname = $('#orgname').val();
			var orgage = $('#orgage').val();
			var updatename = $('#updatename').val();
			var updateage = $('#updateage').val();

			
			$.ajax({
				url:'${path}/controller/mongoupdate',  //restfull하게 설계
				type:'POST',  //메소드 방식
				data:{orgname, orgage, updatename, updateage},
				success:function(retValu){
					alert('리스트 에이젝스 성공..' + '/'+retValu.val)
				},
				error:function(retValu, status, err){
					console.log(err);
					alert('리스트 에러: '+retValu+" status: "+status+" err: " + err);
				}
			});
		});
		
		//조회
		$('#btn-mongofind').click(function() {
			var name = $('#findname').val();
			
			$.ajax({
				url:'${path}/controller/mongofind',  //restfull하게 설계
				type:'POST',  //메소드 방식
				data:{name},
				success:function(retValu){
					alert('리스트 에이젝스 성공..' + '/'+retValu)
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
<hr>
<div id="mongodbremovediv">
	<button name="subject" id="btn-mongoremove">몽고디비 삭제</button>
	<input type="text" id="removename" placeholder="검색 이름"/>
</div>
<hr>
<div id="mongodblistdiv">
	<button name="subject" id="btn-mongoupdate">몽고디비 수정</button>
	<input type="text" id="orgname" placeholder="검색 이름"/>
	<input type="text" id="orgage" placeholder="검색 나이"/>
	<input type="text" id="updatename" placeholder="수정 이름"/>
	<input type="text" id="updateage" placeholder="수정 나이"/>
</div>
<div id="mongodbremovediv">
	<button name="subject" id="btn-mongofind">몽고디비 검색</button>
	<input type="text" id="findname" placeholder="검색 이름"/>
</div>
</body>
</html>
