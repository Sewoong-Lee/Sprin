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
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.js"></script>
<script type="text/javascript">
	
	$(function() {
		$('#btn').click(function() {
			var sno = $('#sno').val();
			//alert(sno);
			
			location.href = '${path}/grade/subjectlist?sno='+sno;
		});
		
		//합계 평균 계산하기
		$('.score').on('change' ,function() {
			//alert(11);
			//var score = $('.score');
			//console.log(score);
			var sum = 0;
			$('.score').each(function(i, e) { //i : 인덱스 , e:엘리멘트
				console.log($(e).val());
				if ($(e).val() != '') //널체크
					sum += parseInt($(e).val());
			});
			
 			console.log(sum);
 			$('#sum').val(sum);
 			$('#avg').val((sum/$('.score').length).toFixed(2)); //제이쿼리를 이용한 소수점 자르기
 			
			
		});
		
	});

</script>
</head>
<body>
	<h2>성적 등록</h2>
	<form action="${path}/grade/add" method="post">
		학번 <input type="text" name="sno" id="sno" value="${rlist[0].SNO}">
		<button type="button" id="btn">학생 조회</button>
		
		<div>과정명 : ${rlist[0].CNAME}</div>
		<fieldset>
			<legend>수강과목</legend>
			<c:forEach var="rlist" items="${rlist}" varStatus="status"> <!-- 스테이터스로 배열의 리스트 생성 -->
			${rlist.JCODE} ${rlist.JNAME}
			<input type="hidden" name="list[${status.index}].ccode" value="${rlist.CCODE}">
			<input type="hidden" name="list[${status.index}].jcode" value="${rlist.JCODE}">
			<input type="number" name="list[${status.index}].score" class="score"> <!-- dto의 리스트와 이름 같게 -->
			<br>
			</c:forEach>
		</fieldset>
		<br>
		<fieldset>
			합계 <input type="text" id="sum"> <br>
			평균 <input type="text" id="avg">
		</fieldset>
		
		<button>성적 저장</button>
	
	
	</form>
</body>
</html>