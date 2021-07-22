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
//메세지 체크
if('${msg}' != ''){
	alert('${msg}');
}

if('${rmap.msg}' != ''){
	alert('${rmap.msg}');
}


function goPopup(){
	// 주소검색을 수행할 팝업 페이지를 호출합니다.
	// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
	//컨트롤러를 호출해서 주소 팝업 열기
	var pop = window.open("/my/member/jusoPopup","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	
	// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
    //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
}


function jusoCallBack(roadAddrPart1,addrDetail, zipNo){
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
		
		$('#addr1').val(roadAddrPart1);
		$('#addr2').val(addrDetail);
		$('#zip').val(zipNo);
		/* document.frmstudentadd.addr1.value = roadAddrPart1;
		document.frmstudentadd.addr2.value = addrDetail;
		document.frmstudentadd.zip.value = zipNo; */
}
	
	//폼이 로딩이 완료 된 후 실행
	$(function(){
		//가입버튼을 눌렀을때
		$('#btnsave').on('click', function(){
			var userid = $('#userid').val();
			var passwd = $('#passwd').val();
			var email = $('#email').val();
			var zip = $('#zip').val();
			var addr2 = $('#addr2').val();
			//var useok = $('#useok').val(); //사용가능 학번 확인 여부
			//alert(sno);
			if(userid == ''){
				alert('아이디를 입력');
			}else if(passwd == ''){
				alert('비밀번호 입력');
			}else if(email == ''){
				alert('이메일 입력');
			}else{
				frmstudentadd.submit();
			}
		});
		
		/* //학번 중복체크
		$('#nocheck').click(function() {
			var sno = $('#sno').val();
			//alert(sno);
			location.href = '${path}/student/nocheck?sno=' + sno;
		}); */
		
		$('#findaddr').click(function() {
			goPopup();
		});
		
	});
	 
	
</script>
</head>
<body>
	<h2>회원가입</h2>
	<!-- 파일 전송하기위한 설정 : method="post" enctype="multipart/form-data"-->
	<form action="${path}/member/join" method="post" enctype="multipart/form-data" name="frmstudentadd">
	아이디 <input type="text" name="userid" id="userid"> <br>
	비밀번호 <input type="text" name="passwd" id="passwd"> <br>
	이메일 <input type="email" name="email" id="email"> <br>
	우편번호 <input type="text" name="zip" size="5" id="zip"> <button type="button" id="findaddr">우편번호 찾기</button> <br> 
	주소 <input type="text" name="addr1" id="addr1"> <br>
	상세주소 <input type="text" name="addr2" id="addr2"> <br>
	이미지 <input type="file" name="file1" id="file1" > <br>
	소개 <textarea rows="5" cols="25" name="memo" id="memo"></textarea>
	<button id="btnsave" type="button">저장</button>
	</form>
</body>
</html>