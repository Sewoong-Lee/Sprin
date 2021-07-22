<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- contextpath 변수 생성 -->
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 등록</title>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.js"></script>
<script type="text/javascript">
//메세지 체크
if('${msg}' != ''){
	alert('${msg}');
}



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
		
		document.frmstudentadd.addr1.value = roadAddrPart1;
		document.frmstudentadd.addr2.value = addrDetail;
		document.frmstudentadd.zip.value = zipNo;
}
	
	//폼이 로딩이 완료 된 후 실행
	$(function(){
		$('#btnsave').on('click', function(){
			var sno = $('#sno').val();
			var sname = $('#sname').val();
			var tel = $('#tel').val();
			var zip = $('#zip').val();
			var addr1 = $('#addr1').val();
			var addr2 = $('#addr2').val();
			var useok = $('#useok').val(); //사용가능 학번 확인 여부
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
			}else if(useok != 'y'){
				alert('학번 중복 체크 해주셈');
			}else{
				frmstudentadd.submit();
			}
		});
		
		//학번 중복체크
		$('#nocheck').click(function() {
			var sno = $('#sno').val();
			//alert(sno);
			location.href = '${path}/student/nocheck?sno=' + sno;
		});
		
		
	});
	
	
</script>
</head>
<body>
	<h2>학생 등록</h2>
	<form action="${path}/student/add" method="post" name="frmstudentadd">
		학번 <input type="text" name="sno" id="sno" value="${sno}"> 
				<button type="button" id="nocheck">중복체크</button>
				<input type="hidden" value="${useok}" id="useok"> <br>
		이름 <input type="text" name="sname" id="sname"> <br>
		전화번호 <input type="text" name="tel" id="tel"> <br>
		우편번호 <input type="text" name="zip" size="5" id="zip"> <button type="button" onclick="goPopup()">우편번호 찾기</button> <br>
		주소1 <input type="text" name="addr1" size="40" id="addr1"> <br>
		주소2 <input type="text" name="addr2" size="40" id="addr2"> <br>
		<button type="button" id="btnsave">확인</button> <br>
	
	</form>
</body>
</html>