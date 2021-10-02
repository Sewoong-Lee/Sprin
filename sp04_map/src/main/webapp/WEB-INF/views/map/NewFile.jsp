<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.js"></script>
<script type="text/javascript">

/* 	$('#container').on('click','#btn',function(e) {
		e.preventDefault(); */
	$('#btn').click(function() {
		/* frm.submit(); */
		e.preventDefault();
		
		var juso = $('#keyword').val();
		
		$.ajax({
			url:'/my/map/mapSerch',  //restfull하게 설계
			type:'get',  //메소드 방식
			data:{juso},
			success:function(cc){
				console.log(cc);
				
				$('#testDIV').text(cc);
				
			},
			error:function(err){
				console.log(err);
				alert('실패');
			}
		});
		
		serchJusoBtn();
		
		showDIV();
			
		
	 });
	
</script>
</head>
<body>
	<form action="" name="frm" id="frm" >
        <input type="text" value="성동문화재단" id="keyword" size="15" style="margin-right: 10px;" name="keyword">
        <button id="btn" type="button" style="margin-right: 10px;">검색하기</button>
    </form>
</body>
</html>