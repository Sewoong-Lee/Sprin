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

	if('${msg}' != ''){
		alert('${msg}');
	}
	
	
	$(function() {
		$('#btnrequest').click(function() {
			var ccode = $('#ccode').val();
			/* var ccode = $('#ccode').html().trim();//공백 제거 / 아래 스팬에 아이디가 붙었을 경우*/
			var sno = $('#sno').val();
			
			//location.href='${path}/request/add?sno='+sno+'&ccode='+ccode;
			$(location).attr('href', '${path}/request/add?sno='+sno+'&ccode='+ccode);
		});
	});

</script>
</head>
<body>
<!-- 과목을 제외한 다른 내용을 같으므로 0번 인덱스로 고정 -->
	<h2>과정 상세조회</h2>
	<input type="hidden" name="ccode" value="${clist[0].CCODE}" id="ccode">
	과정 코드 : <span id="">${clist[0].CCODE}</span> <br>
	과정명 : ${clist[0].TNO} <br>
	강사 코드 : ${clist[0].TNAME} <br>
	강사 : ${clist[0].TNAME} <br>
	수강기간 : ${clist[0].STARTDATE} ~ ${clist[0].ENDDATE}<br>
	이수기준 : ${clist[0].COMPLETE} <br>
	담당자 : ${clist[0].CPERSON} <br>
	최대수강인원 : ${clist[0].MAXCNT} <br>
	과목 : 
	<c:forEach var="clist" items="${clist}" varStatus="no">
		${no.count}.   ${clist.JNAME}
	</c:forEach> <br>
	등록일자 : ${clist[0].REGDATE} <br>
	수정일자 : ${clist[0].MODIFYDATE} <br>
	<hr>
	<h3>수강 신청 현황</h3>
	<table>
		<tr>
			<th>과정코드</th>
			<th>학번</th>
			<th>학생 이름</th>
			<th>학생 번호</th>
		</tr>
		<c:forEach var="rlist" items="${rlist}">
		<tr>
			<td>${rlist.CCODE}</td>
			<td>${rlist.SNO}</td>
			<td>${rlist.SNAME}</td>
			<td>${rlist.TEL}</td>
		</tr>
		</c:forEach>
	</table>
	<hr>
	<h3>수강신청</h3>
	학번 <input type="text" name="sno" id="sno">
	<button id="btnrequest">수강 신청</button>
</body>
</html>