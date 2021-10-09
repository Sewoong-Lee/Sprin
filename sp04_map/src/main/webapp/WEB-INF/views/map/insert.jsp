<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=7b432eae714388479ae3357175dbadc8&libraries=services,clusterer,drawing"></script>
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.js"></script>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">

$(document).ready(function() {
	//지도를 표시할 공간과 초기 위치 설정
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
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
	    
	    $('#LATITUDE').val(latlng.getLat());
	    $('#LONGITUDE').val(latlng.getLng());
	    
	    var resultDiv = document.getElementById('clickLatlng'); 
	    resultDiv.innerHTML = message;
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
		
		$('#btn').click(function() {
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
			        position: new kakao.maps.LatLng(place.y, place.x) 
			    });

			    // 마커에 클릭이벤트를 등록합니다
			    kakao.maps.event.addListener(marker, 'click', function() {
			        // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
			        infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
			        infowindow.open(map, marker);
			        
			        $('#LATITUDE').val(place.y);
				    $('#LONGITUDE').val(place.x);
			        
			    });
			}
			
		});
		
		
	
		
		
});
</script>
</head>
<body>
<div>
	<form action="" name="frm" id="frm" >
        업체명* <input type="text" id="BRAND_NAME"  name="BRAND_NAME" placeholder="네임"> <br>
        주소* <input type="text" id="ADDRESS"  name="ADDRESS"> <button id="btn" type="button" >검색하기</button> <br>
        위도* <input type="number" id="LATITUDE"  name="LATITUDE"> <br>
        경도* <input type="number" id="LONGITUDE"  name="LONGITUDE"> <br>
        전화번호* <input type="text" id="TEL"  name="TEL"> <br>
        운영시간 <select id="ON_DAY1" name="ON_DAY1">
	        		<option> AM </option>
	        		<option> PM </option>
        		</select>
        <input type="text" id="ON_DAY"  name="ON_DAY"> <br>
        대분류 <select>
        		<c:forEach var="result" items="${listmap}" varStatus="status">
        		<c:choose>
        			<c:when test="${status.index eq 0 }">
						<option>${result.L_CATEGORIES}</option>
					</c:when>
					<c:when test="${status.index ne 0}">
					 <c:if  test="${listmap[status.index- 1].L_CATEGORIES ne result.L_CATEGORIES}">
					 <option>${result.L_CATEGORIES}</option>
					 </c:if>
					</c:when>
				</c:choose>
        		</c:forEach>
        		</select>
        		
        중분류<select>
        		<c:forEach var="result" items="${listmap}" varStatus="status">
        		<c:choose>
        			<c:when test="${status.index eq 0 }">
						<option>${result.M_CATEGORIES}</option>
					</c:when>
					<c:when test="${status.index ne 0}">
					 <c:if  test="${listmap[status.index- 1].M_CATEGORIES ne result.M_CATEGORIES}">
					 <option>${result.M_CATEGORIES}</option>
					 </c:if>
					</c:when>
				</c:choose>
        		</c:forEach>
        		</select>
        		
        소분류<select>
        		<c:forEach var="result" items="${listmap}" varStatus="status">
        		<c:choose>
        			<c:when test="${status.index eq 0 }">
						<option>${result.S_CATEGORIES}</option>
					</c:when>
					<c:when test="${status.index ne 0}">
					 <c:if  test="${listmap[status.index- 1].S_CATEGORIES ne result.S_CATEGORIES}">
					 <option>${result.S_CATEGORIES}</option>
					 </c:if>
					</c:when>
				</c:choose>
        		</c:forEach>
        		</select>
        		<br>
        휴무일 <input type="text" id="OFF_DAY"  name="OFF_DAY"> <br>
		대표 페이지 링크 <input type="text" id="PAGE_LINK"  name="PAGE_LINK"> <br>
		대관 가능 여부 <select  id="RENT_GUBUN" name="RENT_GUBUN">
        					<option>가능</option>
        					<option>불가</option>
        				</select> <br>
		공간규모 <input type="text" id="SCALE"  name="SCALE"> <br>
		가격 <input type="number" id="PRICE"  name="PRICE"> <br>
		소개 <textarea rows="" cols="" id="INTRO"  name="INTRO"></textarea> <br>
		파일 <input type="file" id="IMAGES"  name="IMAGES"> <br>
	</form>
</div>
        <div id="map" style="width:100%;height:350px;"></div>
		<p><em>지도를 클릭해주세요!</em></p> 
		<div id="clickLatlng"></div>
        
        ${listmap}
    
</body>
</html>