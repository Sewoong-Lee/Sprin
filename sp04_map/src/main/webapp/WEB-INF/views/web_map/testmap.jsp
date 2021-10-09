<%  
response.setHeader("Cache-Control","no-store");  
response.setHeader("Pragma","no-cache");  
response.setDateHeader("Expires",0);  
if (request.getProtocol().equals("HTTP/1.1"))
        response.setHeader("Cache-Control", "no-cache");
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>
<%@ taglib prefix="el" uri="/WEB-INF/tld/elFunction.tld" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<title>성동문화재단</title>
	<meta name="description" content="성동문화재단">
	<link rel="icon" href="/favicon.ico" type="image/x-icon"/>
	<link rel="shortcut icon" href="/images/favicon96.png" type="image/x-icon"/>
	<link rel="apple-touch-icon-precomposed" href="/images/favicon96.png"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densitydpi=medium-dpi">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<link rel="stylesheet" type="text/css" href="/css/kor/sdfac/style.css">
	<link rel="stylesheet" type="text/css" href="/css/kor/sdfac/slick.css">
	<script type="text/javascript" src="/js/kor/sdfac/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="/js/kor/sdfac/common.js"></script>
	<script type="text/javascript" src="/js/kor/sdfac/slick.js"></script>
	<script type="text/javascript" src="/js/common.js"></script>
	
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=7b432eae714388479ae3357175dbadc8&libraries=services,clusterer,drawing"></script>
<%@ include file="mapcss.jsp"%>

<script type="text/javascript">
var infos = [];
var markerTitle = 0;
var markers = [];

var dataX = 0;
var dataY = 0;

var checked = 0;

var level = 0;

	// 지도 위에 표시되고 있는 인포윈도우를 모두 제거합니다
	function removeinfo() {
	    for ( var i = 0; i < infos.length; i++ ) {
	    	infos[i].close();
	    }   
	    infos = [];
	}
	
	//지도 위에 표시되고 있는 마커를 모두 제거합니다
	function removeMarker() {
	    for ( var i = 0; i < markers.length; i++ ) {
	        markers[i].setMap(null);
	    }   
	    markers = [];
	}
	
	$(document).ready(function() {
		//지도 위도, 경도
		var mapContainer = document.getElementById('map');
		var mapOption = {  
			center: new kakao.maps.LatLng(33.450701, 126.570667),//위도 경도 셋팅
			level: 3 //확대값 세팅
		};
		
		//지도 생성
		var map = new kakao.maps.Map(mapContainer, mapOption); 
		//var map = new kakao.maps.Map(container, options);

		function resizeMap() {
		    var mapContainer = document.getElementById('map');
		    mapContainer.style.width = '650px';
		    mapContainer.style.height = '650px'; 
		}

		//지도 다시 불러오기 함수
		function relayout() {    
		    // 지도를 표시하는 div 크기를 변경한 이후 지도가 정상적으로 표출되지 않을 수도 있습니다
		    // 크기를 변경한 이후에는 반드시  map.relayout 함수를 호출해야 합니다 
		    // window의 resize 이벤트에 의한 크기변경은 map.relayout 함수가 자동으로 호출됩니다
		    map.relayout();
		}

		//마커 생성
		// 마커 위치
		var markerPosition  = new kakao.maps.LatLng(33.450701, 126.570667); 

		//마커 생성
		// 지도를 클릭한 위치에 표출할 마커입니다
		var marker = new kakao.maps.Marker({
			 // 지도 중심좌표에 마커를 생성합니다 
		    position: markerPosition
		});
		// 지도에 마커를 표시합니다
		marker.setMap(map);

		
		//마커 찍기
		var marker2 = new kakao.maps.Marker({ 
		    // 지도 중심좌표에 마커를 생성합니다 
		    position: map.getCenter() 
		}); 

		

		// 지도에 마커를 표시합니다
		marker2.setMap(map);
		kakao.maps.event.addListener(map, 'click', function(mouseEvent) {

		    // 클릭한 위도, 경도 정보를 가져옵니다 
		    var latlng = mouseEvent.latLng; 
		    // 마커 위치를 클릭한 위치로 옮깁니다
		    marker2.setPosition(latlng);
		    var message = '위도: ' + latlng.getLat() +'<br>';
		    message += '경도: ' + latlng.getLng();
		    console.log(latlng.getLat());

		    var resultDiv = document.getElementById('clickLatlng'); 
		    /* resultDiv.innerHTML = message; */
		});

		

		//인포위도우2
		var iwContent = '<div style="padding:5px;">카카오지도</div>', 
		    iwRemoveable = true; 
		
		var infowindow = new kakao.maps.InfoWindow({
			content : iwContent,
		    removable : iwRemoveable
		});

		
		// 마커에 클릭이벤트를 등록합니다
		kakao.maps.event.addListener(marker, 'click', function() {
		      // 마커 위에 인포윈도우를 표시합니다
		      infowindow.open(map, marker);  
		});

		
		//지도 이동
		function setCenter() {
		var moveLatLon = new kakao.maps.LatLng(33.450701, 126.570667);
		map.setCenter(moveLatLon);
		}
		

		function panTo() {
			$('#floatDiv').hide();
			//위드 초기화
			$('.map_wrap').css('width','100%');
			//지도 초기화
			map.relayout();
			var moveLatLon = new kakao.maps.LatLng(33.450701, 126.570667);
			map.panTo(moveLatLon);
		}

		
		//지도 초기화 버튼 누르면
		$('#panTo').click(function() {
			panTo();
		});

 
		//엔터로 주소 검색
		$("#keyword").keypress(function(event){
			if (event.which == '13') {
				event.preventDefault();
					      
				//버튼 누르면 마커 삭제
				removeMarker();
				//버튼 누르면 윈도우 삭제
				removeinfo();
				
				markerTitle = $('#title').val();
				serch();
			}
		});

		

		//검색버튼 누르면
		$('#container').on('click','#btn',function(e) {
			e.preventDefault();
			//버튼 누르면 마커 삭제
			removeMarker();
			//버튼 누르면 윈도우 삭제
			removeinfo();
			
			markerTitle = $('#title').val();
			serch();
		 });

		
		//div의 이름을 클릭하면 위의 마커타이틀 변수에 값 넘김
		$('#container').on('click','.DIVclick',function(e) {
			e.preventDefault();
			/* var th = $(this).parents("a").attr("href"); */
			markerTitle = $(this).attr("href");
			/* alert(markerTitle); */
		});

		

		//검색
		function serch() {
			var keyword = $('#keyword').val();
			var select1 = $('#select1').val();
			var select2 = $('#select2').val();
			console.log(select1 +' '+ select2 +' '+ keyword);
			
			if(select1 === '시 지역' || select2 === '구 지역'){
				alert('구역 선택 오류');
				return;
			}
			
			//현재 보이는 범위 구하기
			var bounds = map.getBounds();
			console.log('bounds '+bounds);
			// 영역의 남서쪽 좌표를 얻어옵니다 
		    var swLatLng = bounds.getSouthWest(); 
		    console.log('swLatLng '+swLatLng);
		    console.log('swLatLng.getLat() '+swLatLng.getLat());
		    console.log('swLatLng.getLng() '+swLatLng.getLng());

		    //범위 변수에 넣기
		    dataX = swLatLng.getLat();
			dataY = swLatLng.getLng();
			
			//체크박스 여부 확인
			// 체크여부 확인
			if($("input:checkbox[name=checkbox]").is(":checked") == true) {
				checked = 1;
				console.log('checked '+checked);
			}else{
				checked = 0;
			}
			
			$.ajax({
				url:'/kor/sdfac/testmap/mapShrech.do',
				type:'get',  //메소드 방식
				data:{keyword, select1, select2, checked, dataX, dataY},
				success:function(data){
					console.log('data[0]');
					console.log(data);
					if(data.length === 0){
						alert('검색결과가 없습니다.');
						return;
					}else{
						mar(data);
						showDIV(data);
					}

				},
				error:function(err){
					console.log(err);
					alert('실패');
				}
			});
		}

		//검색 데이터로 마커 표시와 인포윈도우 이벤트 생성
		function mar(data) {
			// 마커를 표시할 위치와 title 객체 배열입니다 
			var i = 0;
			var positions = [];
			for (i = 0; i < data.length; i ++) {
			/* $.each(data, function(i){ */
			positions[i] = 
			    {
			        content: '<div style="width:250px;padding:6px 0;">' + 
			        '<div class="infoNameBox">'+'<a class="infoName1">'+'공공문화시반시설'+' > </a>'+'<a class="infoName1">'+'도서관'+' ></a><br>'+
			        '<a class="infoName2">'+data[i].branf_name+'</a>'+'</div><br> '+
			        '<a class="infoAddr">'+data[i].address+'</a><br>'+
			        '<a class="infoTel">연락처: '+data[i].tel+'</a><br><br>'+
			        '<div style="text-align:center;">'+
			        '<img alt="이미지" src="/images/kor/sdfac/common/'+'logo.png'+'" width="200px" height="100px" ></div></div>',

			        latlng: new kakao.maps.LatLng(data[i].latitude, data[i].longitude)
			        /* latlng: new kakao.maps.LatLng(data[i].LATITUDE, data[i].LONGITUDE) */
			    }
			}
			/* }); */

			console.log('positions');
			console.log(positions);

			for (var i = 0; i < positions.length; i ++) {
			    // 마커를 생성합니다
			    var marker = new kakao.maps.Marker({
			        map: map, // 마커를 표시할 지도
			        position: positions[i].latlng, // 마커의 위치
			        title : data[i].m_idx ,
			        clickable: true, // **마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
			        zIndex : 0 //마커의 클릭수 체크
			    });
				//마커를 변수에 담음
			    markers.push(marker);
				
			    console.log('마커스');
			    console.log(markers);
			    console.log('마커 타이틀');
			    console.log(marker.getTitle());

			    console.log('마커 포지션');
			    console.log(marker.getPosition());

			    // 마커에 표시할 인포윈도우를 생성합니다 
			    var infowindow = new kakao.maps.InfoWindow({
			        content: positions[i].content, // 인포윈도우에 표시할 내용
			        zIndex : 999,
			        removable : true // **removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다
			    });
				//인포윈도우를 변수 배열에 담음
			    infos.push(infowindow);
 
			    // 마커에 이벤트를 등록하는 함수 만들고 즉시 호출하여 클로저를 만듭니다
			    // 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
			    (function(marker, infowindow) {
			        // 마커에 mouseover 이벤트를 등록하고 마우스 오버 시 인포윈도우를 표시합니다 
			        kakao.maps.event.addListener(marker, 'click', function() {
			        	level = map.getLevel();
		        		marker.setZIndex(marker.getZIndex() + 1);
		        		/* alert(marker.getZIndex()); */
			        	if(marker.getZIndex()%2 === 0 ){
			        		map.relayout();
			        		removeinfo();
			        		
			        	}else{
			        		map.relayout();
			        		//인포윈도우 지우기
				        	removeinfo();
				        	
				            infowindow.open(map, marker);
				            infos.push(infowindow);
				            console.log('클릭');
				            
				            if(level < 3){
				            	//위치로 이동
				            	var coords = new kakao.maps.LatLng(marker.getPosition().Ma + 0.0002, marker.getPosition().La);
							    map.setCenter(coords);
				            }else{
				            	//위치로 이동
				            	var coords = new kakao.maps.LatLng(marker.getPosition().Ma + 0.001, marker.getPosition().La);
							    map.setCenter(coords);
				            }
				          	
			        	}

			        });

			       $('#container').on('click','.DIVclick',function(e) {
			    	   level = map.getLevel();
			    	   /* var markerTitle = $('#title').val(); */
			    	   
			    	   /* marker.setZIndex(marker.getZIndex() + 1); */
			    	   if(marker.getTitle() == markerTitle){
		    		   		marker.setZIndex(marker.getZIndex() + 1);
				        	if(marker.getZIndex()%2 !== 0 ){
				        		//윈도우 지우기
								removeinfo();
				    			//인포윈도우 변수 배열에 담기
				    			infos.push(infowindow);
				    		   
				    			infowindow.open(map, marker);
					            console.log('클릭');
					            
					          	//해당 좌표로 이동
						     	if(level < 3){
					            	//위치로 이동
					            	var coords = new kakao.maps.LatLng(marker.getPosition().Ma + 0.0002, marker.getPosition().La);
								    map.setCenter(coords);
					            }else{
					            	//위치로 이동
					            	var coords = new kakao.maps.LatLng(marker.getPosition().Ma + 0.001, marker.getPosition().La);
								    map.setCenter(coords);
					            }
						     	
				        	}else{
				        		removeinfo();
			        		}
			    		 /* //윈도우 지우기
							removeinfo();
			    			//인포윈도우 변수 배열에 담기
			    			infos.push(infowindow);
			    		   
			    			infowindow.open(map, marker);
				            console.log('클릭');
				            
				          //해당 좌표로 이동
				          console.log(marker.getPosition().La);
					     	var coords = new kakao.maps.LatLng(marker.getPosition().Ma + 0.001, marker.getPosition().La);
					     	map.setCenter(coords); */
			    	   }
					});

			    })(marker, infowindow);

			}

			//해당 좌표로 이동
	     	var coords = new kakao.maps.LatLng(data[0].latitude, data[0].longitude);
	     	map.setCenter(coords);

		}

		
		//옆의 div를 보이게 변경
		function showDIV(data) {
			//옆의 div 보이게
			$('#floatDiv').show();
			//맵 위의 div 보이게
			$('#mapOnDiv').show();
			$('.map_wrap').css('width', '70%');

			var	itemStr = '<div><a id="itemStrTitle">지역 문화 콘텐츠</a>&nbsp;&nbsp;<a id="itemStrNum">'+data.length+'개</a></div>';
			
			var conten = '봄철의 들판을 네가 혼자 거닐고 있으면 말이지, 저쪽에서 벨벳같이 털이 부드럽고 눈이 똘망똘망한 새끼곰이 다가오는 거야. 그리고 네게 이러는 거야. ‘안녕하세요, 아가씨. 나와 함께 뒹굴기 안하겠어요?’ 하고. 그래서 너와 새끼곰은 부둥켜안고 클로버가 무성한 언덕을 데굴데굴 구르면서 온종일 노는 거야. 그거 참 멋지지?';
			
			for(var i=0;i<data.length;i++){
				itemStr += '<div class="outer" style="height:100px;"><div class="inner">'+
							'<div class="first"><img alt="이미지" src="/images/kor/sdfac/common/'+'logo.png'+'" width="80%"></div>'+
							'<div class="innerDiv"><div class="second"><a href="'+data[i].m_idx+'" class="DIVclick">'+ data[i].branf_name + '</a></div>'+
							'<div class="third">'+data[i].intro+'</div></div>'+
							'</div></div><hr>';
			};
			$("#listDiv").html(itemStr);
			$("#DIV1").html(data.length);
			$("#DIV2").html(data.length);

			//지도 다시 불러오기
			map.relayout();
		}

		
		//셀렉트 박스 옵션 변경
		var optStr = '<option>';
		$("#select1").change(function() {
			console.log($(this).val());
			 if($(this).val() == '서울시'){
				 optStr += '강남구'+'</option>' + '<option> 익선동 </option>';
            }else if($(this).val() == '부천시'){
            	optStr += '고강동'+'</option>' + '<option> 원종동 </option>';
            }
			$("#select2").append(optStr);
		});

	});

	</script>

</head>
<body>
<c:import url="/kor/sdfac/common/TopMenu.do" /><!-- 상단메뉴 -->
<div  class="subContent etc">
		<h2 class="bgBox">지도서비스</h2>
		<div>
			<div class="aside">
				<h2>지도서비스</h2>
				<ul>
					<li><a href="/kor/sdfac/testmap/GomapInsert.do">좌표 등록 서비스</a></li>
					<li class="active"><a href="#">2번 서비스</a></li>
					<li><a href="#">3번 서비스</a></li> <!-- /kor/sdfac/join/join.do -->
				</ul>
			</div>
	<div id="wrapCont" class="conts"><!-- 추가 -->
	<div class="bgBox">
					<h3>지도 서비스</h3>
					<ul id="nav">
					</ul>
					<!-- <script>
						// 현재 메뉴 경로를 설정한다.
						var pagePath = licationPth.split("_");
						var pathStr = "<li>HOME</li>";
						var pathTitle = "성동문화재단";
						var topTitle = pagePath[0];
						for( var i=0; i < pagePath.length; i++ )
						{
							if (i+1 >= pagePath.length){
								pathStr += "<li>" + pagePath[i] + "</li>";
								pathTitle = pagePath[i] + " &lt; " + pathTitle;
							}else{
								pathStr += "<li>" + pagePath[i] + "</li>";
								pathTitle = pagePath[i] + " &lt; " + pathTitle;
							}
						}
						$(".conts h3").text(pagePath[ pagePath.length - 1 ]);
						$(".conts ul[id=nav]").html(pathStr);
						$("#wrapCont").addClass("type"+depth1.replace(/0/g,""));
						$(" h2[class=bgBox]").text(topTitle);
						$(" div div[class=aside] h2").text(topTitle);
						$("title").html(pathTitle);
					</script> -->
				</div>
	<div class="content"> <!-- 추가 -->
	<div id="container">

	<div id="searchDiv">
	<form action="" name="frm" id="frm" >
	<div style="display: flex; align-items: center;">
		<img alt="logo" src="/images/kor/sdfac/common/logo.png" style="margin-right: 10px;">
		
	    	<select id="select1" name="select1">
    			<option>시 지역</option>
    			<option>서울시</option>
    			<option>부천시</option>
	    	</select>
	    	<select id="select2" style="margin-right: 10px;" name="select2">
	    		<option>구 지역</option>
	    	</select>
	        <input type="text" value="테스트" id="keyword" size="15" style="margin-right: 10px;" name="keyword">
	        <!-- <button id="btn" type="button" style="margin-right: 10px;">검색하기</button> -->
	       	<a id="btn" href="#" >검색하기</a>
	        <input type="checkbox" id="checkbox" style="margin-right: 10px;" name="checkbox"> 화면 내에서 검색하기
	        <button type="button" id="panTo" >지도 리셋</button>
	        
	</div>
	    </form>
	</div>
	<br>

	<div id="mapDiv">
<div class="map_wrap">
    <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>
	    <div id="mapOnDiv" style="display:none;">
	    	<div style="margin: 5px;">
	    		<div style="display: flex;">
			    	<div><a id="mapOnDivTitle">서울특별시 성동구</a></div>
			    	<div id="mapOnDiv_Div">성동 문화원</div>
		    	</div>
		    	<div style="display: flex; margin-top: 3px; padding-bottom: 1%"  class="wDIV">
			    	<div><a class="mapOnDiv_W">지역 문화 이야기 </a> <a class="mapOnDiv_A" id="DIV1"></a><a class="mapOnDiv_W">건  | </a></div>
			    	<div> <a class="mapOnDiv_W"> 지역 문화 정보 </a><a class="mapOnDiv_A" id="DIV2"></a><a class="mapOnDiv_W"> 건 </a></div>
		    	</div>
	    	</div>
	    </div>
	</div>
</div>

	<div id="floatDiv" style="display:none;">
	    <div id="listDiv"></div>
	</div>
<!-- 	<div id="clickLatlng">지도에 찍은 경도 위도 나올곳</div>
</div>
<a href="ccc" id=""></a> -->
</div>
</div>
</div>
</div>
</div>
	<!-- Footer 영역 -->
	<%@ include file="/WEB-INF/jsp/kor/sdfac/inc/footer.jsp" %>
</body>
</html>