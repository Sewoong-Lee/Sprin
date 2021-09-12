<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	/* 체크박스 css */
	/* 체크박스 숨기기 */
	/* #seats_list .seatsname input[type="checkbox"] {display: none;} */
	
	/* 체크 안된 체크박스에 배경 넣기 */
	#seats_list input[type="checkbox"]{
		/* display: inline-block; width:30px; height: 30px; background: #fa3062;
		cursor: pointer; border-radius: 6px; */
		
		cursor: pointer;
		-webkit-appearance: none;
		-moz-appearance: none;
		appearance: none;
		outline: 0;
		background: green;
		height: 30px;
		width: 30px;
		border: 1px solid white;
	}
	/* #seats_list :after {display:block; clear:both; content:"";
	
	} */
	
	/* 체크된 체크박스에 배경 넣기 */
	#seats_list.seatsname input[type="checkbox"]:checked {
		background: #2aa1c0;
	}
		
	/* 예매된 체크박스에 배경 넣기 */
	#seats_list .Already_seatsname input[type="checkbox"]:checked {
		background: black no-repeat center/10px 10px;
	}
	
	/* 하이퍼링크 밑줄 및 글색 변경 */
	a {
		text-decoration-line: none;
		color: black;
	}
	#salesbtnbox{
		width: 150px;
		height: 200px;
		text-align: center;
		margin:0 auto;
	}
	.moviecitybox{
		float: left;
		width: 150px;
		height: 200px;
		text-align: center;
		font-size: medium;
	}
	.movieseatsbox{
		float: left;
		width: 300px;
		height: 200px;
		text-align: center;
		font-size: medium;
	}
	
	/* 선택시 배경색 변경 */
	#select_style{
		font-size: large;
		background-color: navy;
		color: white;
	}
	/* 카카오페이 버튼 변경 */
	#salesbtn{
		border: 0;
		outline: 0;
		background-color: white;
	}
</style>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.7/handlebars.min.js"></script>
<!-- 영화관 리스트 탬플릿 -->
<script id="replylist_template" type="text/x-handlebars-template">
	{{#each .}}
     <li><a href="{{cinema_code}}" class="cinemaname">{{cinema_name}}</a></li><br>
     {{/each}}
</script>
<!-- 상영 시간 리스트 탬플릿 -->
<script id="sale_time_template" type="text/x-handlebars-template">
	{{#each .}}
     <li><a href="{{time_code}}" class="timename">상영일 : {{time_day}} <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;상영 시간 : {{time_time}}</a></li><br>
     {{/each}}
</script>
<!-- 전체 좌석 리스트 탬플릿 -->
<script id="seats_list_template" type="text/x-handlebars-template">
	{{#each .}}
		{{#if @index % 4 == 0}}
			<br>
		{{/if}}
     <li><a href="{{seats_code}}" class="timename">{{seats_code}}</a></li>
    {{/each}}
</script>
<script type="text/javascript">
$(function() {
	//선택 날짜 변수
	//var selectday='';
	//날짜를 누르면 날짜 코드를 selectday 변수에 저장
	$('.daylist').click(function(e) {
		e.preventDefault();
		$('.daylist').attr('id','');
		$(this).attr('id','select_style');
		var time_code = $(this).attr('href');
		//날짜 선택시 하이드 인풋창에 값 저장
		$('#selectday').val(time_code);
		//var selectday=time_code;
		//alert(selectday);
	});
	//지역을 누르면
	$('.cityname').click(function(e) {
		e.preventDefault();
		//alert('zz');
		$('.cityname').attr('id','');
		$(this).attr('id','select_style');
		var city_code = $(this).attr('href');
		$('#city_code').val(city_code);
		//alert(city_code);
		
		$.ajax({
			url:'${path}/cinema/city/'+city_code,  //restfull하게 설계
			type:'get',  //메소드 방식
			//contentType:'application/json',  //보내는 데이터 타입
			//data:JSON.stringify({city_code}),  //보낼 데이터
			//dataType:'json', //서버에서 받는 타입
			//dataType:'text', //서버에서 받는 타입
			success:function(data){
				console.log(data);
				//alert('성공');
				var source = $('#replylist_template').html();
				var template = Handlebars.compile(source);
		       	$('#city_name_list').html(template(data));
		        
		        /* var results = data;
	            var str = '';
	            $.each(results , function(i){
	                //str += '<li>' + results[i].cinema_name + '</li>';
	                str += '<li><a href="results[i].cinema_code" class="cinemaname">'+ results[i].cinema_name + '</a></li><br>';
	           });
	           $("#city_name_list").append(str);  */
			},
			error:function(err){
				console.log(err);
				alert('실패');
			}
			
		});
	});
	
	//영화관을 누르면
	$('#city_name_list').on('click','.cinemaname',function(e) {
		e.preventDefault();
		//alert('영화관 골라썽');
		$('.cinemaname').attr('id','');
		$(this).attr('id','select_style');
		var cinema_code = $(this).attr('href');
		$('#cinema_code').val(cinema_code);
		var selectday = $('#selectday').val();
		var movie_num = $('#movie_num').val();
		//alert(cinema_code+' '+ selectday +' '+ movie_num);
		$.ajax({
			url:'${path}/cinema/time',  //restfull하게 설계
			type:'get',  //메소드 방식
			data:{cinema_code,selectday,movie_num},
			success:function(data){
				console.log(data);
				
				var source = $('#sale_time_template').html();
				var template = Handlebars.compile(source);
		       	$('#sale_time_list').html(template(data));
				
			},
			error:function(err){
				console.log(err);
				alert('실패');
			}
			
		});
	});
	
	//상영 시간을 누르면 전체좌석 불러오기
	$('#sale_time_list').on('click','.timename',function(e) {
		e.preventDefault();
		//alert('상영시간 골라썽');
		$('.timename').attr('id','');
		$(this).attr('id','select_style');
		var time_code = $(this).attr('href');
		$('#time_code').val(time_code);
		//alert(time_code);
		//좌석 리스트 불러오기
		$.ajax({
			url:'${path}/cinema/seats',  //restfull하게 설계
			type:'get',  //메소드 방식
			data:{time_code},
			success:function(data){
				console.log(data);
				
				$("#seats_list").text('스크린').append('<br>');
				
				var cnt = 1;
				var results = data;
	            var str = '';
	            $.each(results, function(i){
	                /* str += '<button class="seatsname" value="'+results[i].seats_code+'">'+results[i].seats_code+'</button>&nbsp;&nbsp;'; */
	                //예매좌석 이라면
	                if(results[i].time_code == 0){
	                	//예약좌석이 아니라면
	                	str += '<input type="checkbox" name="seats_code" class="seatsname" value="'+results[i].seats_code+'">'+results[i].seats_code;
	                }else{
	                	//예약 좌석 이라면
	                	 str += '<input type="checkbox" class="Already_seatsname" disabled value="'+results[i].seats_code+'">'+results[i].seats_code;
	                }
	                
	                //str += '<input type="checkbox" name="seats_code" class="seatsname" value="'+results[i].seats_code+'">'+results[i].seats_code;
	                if(cnt % 4 == 0){
	                	str += '<br>';
	                }
	                cnt++;
	           });
	         
	           $("#seats_list").append(str);
		       	
			},
			error:function(err){
				console.log(err);
				alert('실패');
			}
			
		});
		
	});
	
	//예매 버튼을 누르면 카카오 페이 진행
  	$('#salesbtn').click(function(e) {
		e.preventDefault();  //버튼 이벤트 제거
		//좌석 선택 확인
		
		var checked = $("#cinemaform input:checked").length > 0; 
		
		if($('#selectday').val() == ''){
			alert('날짜 선택이 안되었어요.');
		}else if($('#city_code').val() == ''){
			alert('지역 선택이 안되었어요.');
		}else if($('#cinema_code').val() == ''){
			alert('영화관 선택이 안되었어요.');
		}else if($('#time_code').val() == ''){
			alert('상영 시간 선택이 안되었어요.');
		}else if (!checked) {
	  		 alert("하나 이상의 좌석을 선택하세요.");
	  	}else {
	  		if (!confirm("예매 하시겠습니까?")) {
	            alert("취소");
	        } else {
	        	cinemaform.submit();
	        }	
	  	}
			
	}); 
	
  /* $(document).ready(function(){ $("#formId").submit(function () {
  		var checked = $("#formId input:checked").length > 0; if (!checked) {
  		alert("최소한 하나 이상 선택하세요.");
  	 	return false; 
  	} 
  	});  */
	
  	//좌석 선택에 따른 가격 보여주기
  	$('#seats_list').on('change','.seatsname',function(e) {
  		var checked = $("#cinemaform input:checked").length;
  		//alert(checked);
  		var salesprice = 10000;
  		salesprice = checked * salesprice;
  		$('#price').val(salesprice);
  		
  	});
  	
  	
  	
  	
});


</script>
</head>
<body>
<%@ include file = "../include/header.jsp" %>
	<h2>영화 예매</h2>
	<form method="post" action="${path}/KakaoService" name="cinemaform" id="cinemaform">
	<%-- <form action="${path}/cinema/sales" name="cinemaform" id="cinemaform"> --%>
	받은 영화 순번 <input type="number" id="movie_num" value="${movie_num}" name="movie_num"> <br>
	선택한 날짜 코드 <input type="text" id="selectday" value=""><br>
	선택한 지역 코드 <input type="text" id="city_code" value="" name="city_code"><br>
	선택한 영화관 코드 <input type="text" id="cinema_code" value="" name="cinema_code"><br>
	선택한 시간 코드 <input type="text" id="time_code" value="" name="time_code"><br>
	<div>
	<c:forEach var="timelist" items="${timelist}">
		<!-- 공백값 넣어줌 -->
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="${timelist.time_day}" class="daylist">${timelist.time_day}</a>
		&nbsp;&nbsp;&nbsp;&nbsp;
	</c:forEach>
	</div>
	<br>
	<div class="moviecitybox">
	<c:forEach var="citylist" items="${citylist}">
		<li><a href="${citylist.city_code}" class="cityname">${citylist.city_name}</a></li> <br>
	</c:forEach>
	</div >
	<div id="city_name_list" class="moviecitybox">
	</div>
	<div id="sale_time_list" class="movieseatsbox">
	</div>
	<div id="seats_list" class="movieseatsbox">
	</div>
	<br>
	<h4></h4>
	<div id="salesbtnbox">
	결제 가격 <input type="number" id="price" value="" name="price" readonly="readonly">
	<br>
	<button id="salesbtn"> <img alt="" src="/movie/resources/img/payment_icon_yellow_small.png"></button>
	</div>
	</form>
</body>
</html>