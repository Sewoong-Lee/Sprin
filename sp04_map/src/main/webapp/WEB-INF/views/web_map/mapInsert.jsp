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

	<style type="text/css">
		@media screen and (max-width: 1016px)
		#map * {margin:0; padding:0;font-family:'Malgun Gothic',dotum,'돋움',sans-serif;font-size:12px;}
	
		#map {position:relative; width:100%; height:600px; float:left;
	
		}
		/* #btn {
		    border: 1px solid #df5960;
		    background: navy;
		    color: white;

		    padding: 5px 15px;
	
		    text-align: center;
		    

		    
		    /* border: #c2c2c2 solid 1px;
		    background: #fff;
		    color: #666666; */
		} */
	</style>
	
<script type="text/javascript">
$(document).ready(function() {
	
	$('#mapin').click(function() {
		
		if($('#BRAND_NAME').val() === ''){
			alert('업체명 미입력');
			return false;
		}else if($('#ADDRESS').val() === ''){
			alert('주소 미입력');
			return false;
		}else if($('#LATITUDE').val() === ''){
			alert('위도 미입력');
			return false;
		}else if($('#LONGITUDE').val() === ''){
			alert('경도 미입력');
			return false;
		}else if($('#TEL').val() === ''){
			alert('전화번호 미입력');
			return false;
		}else{
			$("#mapInFrm").attr("action","/kor/sdfac/testmap/mapInsert.do").submit();
		}

		
		
	});
	
	//지도를 표시할 공간과 초기 위치 설정
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(37.55942, 127.03492), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

	
	//지도 생성
	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

 
	// 지도를 클릭한 위치에 표출할 마커입니다
	var marker = new kakao.maps.Marker({ 
	    // 지도 중심좌표에 마커를 생성합니다 
	    position: map.getCenter() 
	}); 
	// 지도에 마커를 표시합니다
	marker.setMap(map);

 
	// 지도에 클릭 이벤트를 등록합니다
	// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
	kakao.maps.event.addListener(map, 'click', function(mouseEvent) {        
	    // 클릭한 위도, 경도 정보를 가져옵니다 
	    var latlng = mouseEvent.latLng; 
	    
	    // 마커 위치를 클릭한 위치로 옮깁니다
	    marker.setPosition(latlng);
	    
	    var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
	    message += '경도는 ' + latlng.getLng() + ' 입니다';
	    
	    
	    $('#ADDRESS').val('');
	    $('#LATITUDE').val(latlng.getLat());
	    $('#LONGITUDE').val(latlng.getLng());
	    
	    /* var resultDiv = document.getElementById('clickLatlng'); 
	    resultDiv.innerHTML = message; */
	});


	//***************************************************************************************************

	//주소 단 하나
	// 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
/* 	$('#btn').click(function() {
		var serch = $('#ADDRESS').val();
		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();

		// 주소로 좌표를 검색합니다
		geocoder.addressSearch(serch, function(result, status) {

		    // 정상적으로 검색이 완료됐으면 
		     if (status === kakao.maps.services.Status.OK) {
			
		        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
				
		        $('#LATITUDE').val(result[0].y);
			    $('#LONGITUDE').val(result[0].x);
		     
		        // 결과값으로 받은 위치를 마커로 표시합니다
		        var marker = new kakao.maps.Marker({
		            map: map,
		            position: coords
		        });

		        // 인포윈도우로 장소에 대한 설명을 표시합니다
		        var infowindow = new kakao.maps.InfoWindow({
		            content: '<div style="width:150px;text-align:center;padding:6px 0;">검색위치</div>'
		        });
		        infowindow.open(map, marker);

		        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		        map.setCenter(coords);
		    } 

		});     */

		//**************************************************************************************

		//키워드 검색
		// 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
		var infowindow = new kakao.maps.InfoWindow({zIndex:1});
		$('#btn').click(function(e) {
			e.preventDefault();
			var serch = $('#ADDRESS').val();

			// 장소 검색 객체를 생성합니다
			var ps = new kakao.maps.services.Places(); 
			// 키워드로 장소를 검색합니다
			ps.keywordSearch(serch, placesSearchCB); 

			// 키워드 검색 완료 시 호출되는 콜백함수 입니다
			function placesSearchCB (data, status, pagination) {
			    if (status === kakao.maps.services.Status.OK) {
			        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
			        // LatLngBounds 객체에 좌표를 추가합니다
			        var bounds = new kakao.maps.LatLngBounds();
			        for (var i=0; i<data.length; i++) {
			            displayMarker(data[i]);    
			            bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
			        }       
 
			        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
			        map.setBounds(bounds);
			    } 
			}
 
			// 지도에 마커를 표시하는 함수입니다
			function displayMarker(place) {
			    // 마커를 생성하고 지도에 표시합니다
			    var marker = new kakao.maps.Marker({
			        map: map,
			        position: new kakao.maps.LatLng(place.y, place.x),
			    	title: place.address_name
			    });
			    
			    console.log(place);
			    
			    // 마커에 클릭이벤트를 등록합니다
			    kakao.maps.event.addListener(marker, 'click', function() {
			        // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
			        infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
			        infowindow.open(map, marker);
			        
			        
			        $('#ADDRESS').val(marker.getTitle());
			        $('#LATITUDE').val(place.y);
				    $('#LONGITUDE').val(place.x);
			        
			    });
			}
		});

		//저장 버튼 클릭시
		$('#in_btn').click(function () {
			var categories = $('#categories').val();
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
					<li><a href="/kor/sdfac/testmap/mapInsert.do">좌표 등록 서비스</a></li>
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
			<!-- 작성 시작 -->
			<div class="container">
			<form name="mapInFrm" id="mapInFrm" method="POST" enctype="multipart/form-data">
				<div class="pointBox">
							<p>※ 검색하신 마커를 클릭하면 주소 자동 입력 됩니다. </p>
							<p>※ 지도를 클릭하면 마커 생성 및 좌표가 입력 됩니다. </p>
							<p>※ 지도를 클릭하면 주소값이 초기화 됩니다. </p>
						</div>
			<p class="mt20 ar">(* 는 필수 입력 내용입니다.)</p>
						<div class="tbType01 type2 mt10">
							<table>
								<caption>Q&amp;A 질문 등록 폼으로 제목, 작성자, 구분, 상세 내용 입력, 파일첨부, 게시글 공개, 비밀번호 정보를 입력할 수 있습니다.</caption>
								<colgroup>
									<col class="w150px">
									<col>
								</colgroup>
								<tbody>
									<tr>
										<th scope="row">업체명<span>*</span></th>
										<td><input type="text" class="w50" title="제목 입력란" id="BRAND_NAME" name="BRAND_NAME" value="${viewMap.b_title}"></td>
									</tr>
									<tr>
										<th scope="row">주소<span>*</span></th>
										<td><input type="text" class="w50" title="작성자 입력란" id="ADDRESS" name="ADDRESS">
											<a id="btn" href="#" class="btn3">검색하기</a>
											<br><br>
											<div id="map" style="width:100%;height:350px;"></div>
										
										</td>
									</tr>
									<tr>
										<th scope="row">위도<span>*</span></th>
										<td><input type="text" class="w50" title="작성자 입력란" id="LATITUDE" name="LATITUDE" ></td>
									</tr>
									<tr>
										<th scope="row">경도<span>*</span></th>
										<td><input type="text" class="w50" title="작성자 입력란" id="LONGITUDE" name="LONGITUDE"></td>
									</tr>
									<tr>
										<th scope="row">전화번호<span>*</span></th>
										<td><input type="text" class="w50" title="작성자 입력란" id="TEL" name="TEL"></td>
									</tr>
									<tr>
										<th scope="row">분류</th>
										<td>
											<select id="categories" name="categories">
								        		<c:forEach var="result" items="${listmap}" varStatus="status">
													 <option value="${result.C_IDX}">${result.L_CATEGORIES} > ${result.M_CATEGORIES} > ${result.S_CATEGORIES}</option>
								        		</c:forEach>
							        		</select>
						        		</td>
									</tr>
									<tr>
										<th scope="row">운영시간</th>
										<td>
											<select id="ON_DAY1" name="ON_DAY1">
								        		<option> AM </option>
								        		<option> PM </option>
							        		</select>
						        			<input type="text" class="w40" id="ON_DAY2"  name="ON_DAY2">
						        			~
						        			<select id="ON_DAY3" name="ON_DAY3">
								        		<option> AM </option>
								        		<option> PM </option>
							        		</select>
						        			<input type="text" class="w40" id="ON_DAY4"  name="ON_DAY4">
										</td>
									</tr>
									<tr>
										<th scope="row">휴무일</th>
										<td><input type="text" class="w50" id="OFF_DAY"  name="OFF_DAY" ></td>
									</tr>
									<tr>
										<th scope="row">대표 페이지 링크</th>
										<td><input type="text" class="w50" id="PAGE_LINK"  name="PAGE_LINK"></td>
									</tr>
									<tr>
										<th scope="row">대관 가능 여부</th>
										<td>
											<select  id="RENT_GUBUN" name="RENT_GUBUN">
					        					<option value="1">가능</option>
					        					<option value="0">불가</option>
					        				</select>
										</td>
									</tr>
									<tr>
										<th scope="row">공간규모</th>
										<td><input type="text" class="w50" id="SCALE"  name="SCALE"></td>
									</tr>
									<tr>
										<th scope="row">가격</th>
										<td><input type="text" class="w50" id="PRICE"  name="PRICE"></td>
									</tr>
									<tr>
										<th scope="row">상세 소개 입력</th>
										<td>
										<p class="fs13 b05 fcG">단축키 안내 [툴바 이동 : ALT+F10 / 글쓰기영역이동 : ESC / 에디터 다음요로소 이동 : ALT+. / 에디터 이전 요소로 이동 : ALT+,]</p>
										<textarea name="INTRO" id="INTRO" cols="30" rows="10" title="상세 내용 입력란">${viewMap.b_contents}</textarea></td>
									</tr>
									<tr>
										<th scope="row">파일첨부</th>
										<td>
											<input type="file" class="w50" title="파일첨부 선택란" name="file1" id="file1">
										</td>
									</tr>
								</tbody>
							</table>
							</div>
							<div class="btnC">
							<a href="#none" class="btn3 btnRed" id="mapin">등록</a>
							<a href="#none" class="btn3">취소</a>
						</div>
					</form>
						</div>
					</div>	
			
			
</div>		
</div>
</div>		
</div>

<!-- Footer 영역 -->
<%@ include file="/WEB-INF/jsp/kor/sdfac/inc/footer.jsp" %>
</body>
</html>