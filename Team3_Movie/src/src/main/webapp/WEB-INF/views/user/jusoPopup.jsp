<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>우편번호 조회</title>
<% 
	request.setCharacterEncoding("UTF-8");  //한글깨지면 주석제거
	//request.setCharacterEncoding("EUC-KR");  //해당시스템의 인코딩타입이 EUC-KR일경우에
	String inputYn = request.getParameter("inputYn"); 
	String roadAddrPart1 = request.getParameter("roadAddrPart1"); 
	String zipNo = request.getParameter("zipNo"); 
	String addrDetail = request.getParameter("addrDetail"); 

%>
</head>
<script language="javascript">
	function init(){
		//검색한 주소를 돌려줄 곳. callBack 주소
		var url = location.href; 
		
		var confmKey = "devU01TX0FVVEgyMDIxMDcwNzE0MTU0NDExMTM3NDc=";
		var resultType = "4"; 
		var inputYn= "<%=inputYn%>";
		//alert("url : " + url);
		//alert("inputYn : " + inputYn);
		if(inputYn != "Y"){ //팝업 호출 시 첫번째로 실행되는 명령문
			document.form.confmKey.value = confmKey;
			document.form.returnUrl.value = url;
			document.form.resultType.value = resultType;
			document.form.action="https://www.juso.go.kr/addrlink/addrLinkUrl.do"; //인터넷망
			document.form.submit();
		}else{
			opener.jusoCallBack("<%=roadAddrPart1%>","<%=addrDetail%>","<%=zipNo%>" );
			window.close();
		}
	}
</script>
<body onload="init()">
	<form id="form" name="form" method="post">
		<input type="hidden" id="confmKey" name="confmKey" value=""/>
		<input type="hidden" id="returnUrl" name="returnUrl" value=""/>
		<input type="hidden" id="resultType" name="resultType" value=""/>
		<!-- 해당시스템의 인코딩타입이 EUC-KR일경우에만 추가 START-->
		<!-- 
		<input type="hidden" id="encodingType" name="encodingType" value="EUC-KR"/>
		 -->
		<!-- 해당시스템의 인코딩타입이 EUC-KR일경우에만 추가 END-->
	</form>
</body>
</html>