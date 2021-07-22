<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- contextpath 변수 생성 -->
<c:set var="path" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.js"></script>
<script type="text/javascript">
	$(function(){
		$('#btndelete').click(function() {
			var rs = confirm('정말 삭제?');
			
			if(rs){
				location.href='${path}/student/delete?sno=${student.sno}'
			}
		});
		
	});


</script>
</head>
<body>
	<h2>상세 조회</h2>
		학번 ${student.sno} <br>
		이름 ${student.sname} <br>
		전화번호 ${student.tel} <br>
		우편번호 ${student.zip} <br>
		주소1 ${student.addr1} <br>
		주소2 ${student.addr2} <br>
		등록일자 <fmt:formatDate value="${student.regdate}" pattern="yyyy-MM-dd"/> <br>
		
		<button id="button" onclick="location.href='${path}/student/modify?sno=${student.sno}'">수정</button>
		<button id="btndelete">삭제</button> <br>
</body>
</html>