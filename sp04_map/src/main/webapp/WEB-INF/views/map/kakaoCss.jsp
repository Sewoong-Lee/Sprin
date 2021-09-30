<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<!-- 지도 카테고리별 css -->
	<style type="text/css">
	.map_wrap, .map_wrap * {margin:0; padding:0;font-family:'Malgun Gothic',dotum,'돋움',sans-serif;font-size:12px;}
	.map_wrap {position:relative; width:100%; height:600px; float:left;
	}
	#category {position:absolute;top:10px;left:10px;border-radius: 5px; border:1px solid #909090;box-shadow: 0 1px 1px rgba(0, 0, 0, 0.4);background: #fff;overflow: hidden;z-index: 2;}
	#category li {float:left;list-style: none;width:50px;px;border-right:1px solid #acacac;padding:6px 0;text-align: center; cursor: pointer;}
	#category li.on {background: #eee;}
	#category li:hover {background: #ffe6e6;border-left:1px solid #acacac;margin-left: -1px;}
	#category li:last-child{margin-right:0;border-right:0;}
	#category li span {display: block;margin:0 auto 3px;width:27px;height: 28px;}
	#category li .category_bg {background:url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png) no-repeat;}
	#category li .bank {background-position: -10px 0;}
	#category li .mart {background-position: -10px -36px;}
	#category li .pharmacy {background-position: -10px -72px;}
	#category li .oil {background-position: -10px -108px;}
	#category li .cafe {background-position: -10px -144px;}
	#category li .store {background-position: -10px -180px;}
	#category li.on .category_bg {background-position-x:-46px;}
	.placeinfo_wrap {position:absolute;bottom:28px;left:-150px;width:300px;}
	.placeinfo {position:relative;width:100%;border-radius:6px;border: 1px solid #ccc;border-bottom:2px solid #ddd;padding-bottom: 10px;background: #fff;}
	.placeinfo:nth-of-type(n) {border:0; box-shadow:0px 1px 2px #888;}
	.placeinfo_wrap .after {content:'';position:relative;margin-left:-12px;left:50%;width:22px;height:12px;background:url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')}
	.placeinfo a, .placeinfo a:hover, .placeinfo a:active{color:#fff;text-decoration: none;}
	.placeinfo a, .placeinfo span {display: block;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}
	.placeinfo span {margin:5px 5px 0 5px;cursor: default;font-size:13px;}
	.placeinfo .title {font-weight: bold; font-size:14px;border-radius: 6px 6px 0 0;margin: -1px -1px 0 -1px;padding:10px; color: #fff;background: #d95050;background: #d95050 url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png) no-repeat right 14px center;}
	.placeinfo .tel {color:#0f7833;}
	.placeinfo .jibun {color:#999;font-size:11px;margin-top:0;}
	
	#container {
		margin: auto;
		width: 90%;
	}
	
	#mapOnDiv {
		position:absolute;
		top:1%;
		left:10px;
		/* border-radius: 5px;  */ /* 둥글기 */
		border:1px solid #909090;
		box-shadow: 0 1px 1px rgba(0, 0, 0, 0.4);
		background: navy;  /* #fff; */
		overflow: hidden;
		z-index: 2;
		
		width: 300px; /* 사이즈 픽셀로 바꿔야댐 */
		height: 65px; /* 사이즈 픽셀로 바꿔야댐 */
		color: white;
	}
	
	#mapOnDiv_Div {
		border: 1px solid;
		border-color: white;
		width: 75px;
		height: 20px;
		text-align: center;
		margin: auto;
	}
	
	#mapOnDivTitle {
		font-size: 22px;
		color: yellow;
	}
	
	.mapOnDiv_A {
		color: yellow;
	}
	
	#btn {
		transition-duration: 0.4s;
		padding: 5px 12px;
		border-radius: 15px;
		background-color: navy;
		color: white;
		height: 40px;
	}
	
	
	select {
		
		padding: .8em .5em;
		border: 1px solid #999;
		font-family: inherit;
		background: url('images.png') no-repeat 90% 50%/15px auto;
		border-radius: 0px;
		-webkit-appearance: none;
		-moz-appearance: none;
		appearance: none;
		text-align: left;
		width: 90px;
		background-color:rgba(200, 200, 200, 0.25);
		}
		
		select::-ms-expand {
		    display: none;
	}
	
	select:hover {
      border-color: #888;
    }
    
    select:focus {
      border-color: #aaa;
      box-shadow: 0 0 1px 3px rgba(59, 153, 252, .7);
      box-shadow: 0 0 0 3px -moz-mac-focusring;
      color: #222;
      outline: none;
    }

	#select1{
		border-radius: 15px 0px 0px 15px;
		/* margin-right: -5; */
	}
	#select2{
		border-radius: 0px 15px 15px 0px;
		/* margin-left: -5; */
		
	}
	
	#searchDiv{
		display: flex;
		/* justify-content: center; //가로가운데 */
		align-items: center;//세로가운데
	}
	 
	#keyword{
		width: 120px;
		height: 33px;
	}
	
	#checkbox{
	}
	
	#floatDiv{
		/* float: right; */
	}
	#test1{

	}
	
	/* #mapDiv{
		flex-direction: column;
		justify-content: space-around;
		
		border:10px;
		border-color:white;
		margin : 20px;
		background-color: 424242;
		float: left;
		width: 98%;
		text-align: center;
		font-size: medium;
	
	} */
/* listDiv */
	#floatDiv{
		overflow-y: scroll;
		
		flex-direction: column;
		justify-content: space-around;
		
		border:10px;
		border-color:white;
		margin : 10px;
		background-color: 424242;
		float: right;
		width: 25%;
		height: 600px;
		text-align: left;
		font-size: medium;
	}
	
	.first {
		margin-top: 20px;
	    float: left;
	    width:30%;
	    box-sizing: border-box;
	}
	
	.innerDiv {
	
	    float: left;
	    margin-left: 2%;
	    width:65%;
	    box-sizing: border-box;
	}
	
	.second {
		font-weight: bolder;
		text-align: left;
		font-size: 18px;
		margin: 3 auto;
		margin-bottom: 5px;
	    width:90%;
	    box-sizing: border-box;
	}

	.third {
		text-align: left;
		font-size: 12px;
	    width:90%;
	    box-sizing: border-box;
	    
/* 	    overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
		height: 100px; */
	    
	}
	
	.outer {

	    flex-direction: column;  /* make main axis vertical */
	    justify-content: center; /* center items vertically, in this case */
	    align-items: center;     /* center items horizontally, in this case */
	    height: 100px;
	}
	
	.inner {
	    margin: 5px;
	    text-align: center;     /* will center text in <p>, which is not a flex item */
	}
	
	#itemStrTitle{
		font-weight: bold;
		font-size: 22px;
	}
	
	#itemStrNum{
		font-size: 15px;
	}
	
	
	#con {
		font-size:0
	}
	
	.infoNameBox {
		text-align:left;
		margin-left: 8px;
		margin-right: 8px;
		margin-bottom: -10px;
		padding-bottom: 8px;
		border-bottom: 2px solid;
	}
	
	.infoName1 {
		font-size: 15px;
		font-weight: bold;
	}
	
	.infoName2 {
		font-size: 18px;
		font-weight: bold;
	}
	
	.infoAddr {
		font-size: 12px;
		text-align:left;
		margin-left: 8px;
	}
	
	.infoTel {
		font-size: 12px;
		text-align:left;
		margin-left: 8px;
	}
	
	hr{
		width: 90%;
	}
	
	
	</style>
</head>
<body>

</body>
</html>