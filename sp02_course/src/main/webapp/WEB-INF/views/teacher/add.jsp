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
function goPopup(){
	// 주소검색을 수행할 팝업 페이지를 호출합니다.
	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
	//컨트롤러를 호출해서 주소 팝업 열기
	var pop = window.open("/my/student/jusoPopup","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	
	// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
    //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
}


function jusoCallBack(roadAddrPart1,addrDetail, zipNo){
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
		
		document.frmteacheradd.addr1.value = roadAddrPart1;
		document.frmteacheradd.addr2.value = addrDetail;
		document.frmteacheradd.zip.value = zipNo;
}
	
	//폼이 로딩이 완료 된 후 실행
	$(function(){
		$('#btnsave').on('click', function(){
			var sno = $('#tno').val();
			var sname = $('#tname').val();
			var tel = $('#tel').val();
			var zip = $('#zip').val();
			var addr1 = $('#addr1').val();
			var addr2 = $('#addr2').val();
			var skill = $('#skill').val();
			//alert(sno);
			if(sno == ''){
				alert('학번을 입력');
			}else if(sname == ''){
				alert('이름을 입력');
			}else if(tel == ''){
				alert('번호 입력');
			}else if(zip == ''){
				alert('우편번호 입력');
			}else if(addr1 == ''){
				alert('기본주소 입력');
			}else if(addr2 == ''){
				alert('상세주소 입력');
			}else if(skill == ''){
				alert('스킬 입력');
			}else{
				frmteacheradd.submit();
			}
			
		});
	});
	
	
</script>
</head>
<body>
	<h2>선생님 등록</h2>
	<form action="${path}/teacher/add" method="post" name="frmteacheradd">
		학번 <input type="text" name="tno" id="tno"> <br>
		이름 <input type="text" name="tname" id="tname"> <br>
		전화번호 <input type="text" name="tel" id="tel"> <br>
		우편번호 <input type="text" name="zip" size="5" id="zip"> <button type="button" onclick="goPopup()">우편번호 찾기</button> <br>
		주소1 <input type="text" name="addr1" size="40" id="addr1"> <br>
		주소2 <input type="text" name="addr2" size="40" id="addr2"> <br>
		스킬 :  파이썬<input type="checkbox" name="skill" id="skill" value="파이썬"> 
				자바 <input type="checkbox" name="skill" id="skill" value="자바"> 
				오라클 <input type="checkbox" name="skill" id="skill" value="오라클">  <br>
		
		<button type="button" id="btnsave">확인</button> <br>
	
	</form>
</body>
</html>