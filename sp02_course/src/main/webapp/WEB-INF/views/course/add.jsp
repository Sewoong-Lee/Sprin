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
		$('#btnsave').click(function() {
			//과정코드, 과정명, 등 유효성 체크 나중에
			
			//과목이 체크된것만 가져오기(과목체크 유효성 체크)
			var chk = $('.jcode:checked');
			console.log(chk); //체크된 개수 확인
			if (chk.length==0){
				alert('과목을 선택해주세요');
			}else{
				$('#frmCourseAdd').submit();
			}
			
		});
	});

</script>
</head>
<body>
	<h2>과정 개설</h2>
	<form action="${path}/course/add" method="post" id="frmCourseAdd">
		과정 코드 <input type="text" name="ccode" id="ccode"> <br>
		과정명 <input type="text" name="cname" id="cname"> <br>
		강사 
		<select name="tno">
			<c:forEach var="tlist" items="${tlist}">
			<option value="${tlist.tno}">${tlist.tno} : ${tlist.tname}</option>
			</c:forEach>
		</select> <br>
		시작일 <input type="date" name="startdate" id="startdate"> <br>
		종료일 <input type="date" name="enddate" id="enddate"> <br>
		이수기준 <input type="text" name="complete" id="complete"> <br>
		담당자 <input type="text" name="cperson" id="cperson"> <br>
		최대 인원 <input type="number" name="maxcnt" id="maxcnt"> <br>
		
		<!-- 과정 과목 -->
		<fieldset>
			<legend>과목</legend>
			<c:forEach var="slist" items="${slist}">
				${slist.jname}<input type="checkbox" name="jcode" value="${slist.jcode}" class="jcode">
			</c:forEach>
		</fieldset>
		
		<button type="button" id="btnsave">저장</button>
	</form>
</body>
</html>