<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>      
<c:set var="path" value="${pageContext.request.contextPath}"/>      
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.js"></script> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리스트</title>
<script type="text/javascript">
$(function() {
	<%
	Cookie[] cookies = request.getCookies();
	String curpageUser =""; 
	String curpageTot ="";
	if(cookies !=null)
		for (Cookie cookie : cookies){
			if(cookie.getName().equals("curpageTot")){
				curpageTot = cookie.getValue();
			}
			else if(cookie.getName().equals("curpageUser")){
				curpageUser = cookie.getValue();
			}
		}
	%>
	
		//회원관심 영화리스트 클릭시
		$('.uList').click(function(e) {
			e.preventDefault();
			var curPageListUser = $(this).attr('curPageListUser');
			alert(curPageListUser);
			ajaxListUser(curPageListUser);
		});	
		/*  ajaxListUser(1);  */
		 ajaxListUser(${sessionScope.curpageUser}); 
		//전체 영화리스트 클릭시
		$('.aList').click(function(e) {
			e.preventDefault();
			var curPage = $(this).attr('curPage');	
			alert(curPage);
			ajaxListTotAjax(curPage);
		});
/* 		ajaxListTotAjax(1); */
		ajaxListTotAjax(${sessionScope.curpageTot});
});


//ajax 영화리스트 
function ajaxListTotAjax(curPage) {
	alert(curPage);
	var ajaxParm = {
		url:'${path}/moviedata/listTotAjax?curPage='+curPage,
		type:'get',
		dataType:'json',
		success: function(data){
			renderListMovie(data);
		},
		error: function(err){
			console.log(err);
			alert("실패");
		}
	};
	$.ajax(ajaxParm);
}

//ajax 회원관심영화 리스트 
function ajaxListUser(curPageListUser) {
	var ajaxParam = {
		url:'${path}/moviedata/listuserAjax?curPageListUser=' + curPageListUser,
		type:'get',
		dataType:'json',
		success: function(data) {
			renderUserMovie(data);
		},
		error: function(err) {
			alert('실패');
			console.log(err);
		}
	};
	$.ajax(ajaxParam);
}


//전체영화 리스트
function renderListMovie(data) {
	console.log(data);
	var page = data["mvUser_Page"];
	var curPage	= page['curPage'];
	
	var movieList = data["movieList"];
	$("#TotListWrap").empty();
	var path = "${path}";
	for(var i in movieList){
		var movieObj = movieList[i];
		var div = $("<div></div>").addClass("TotListDiv");
		var aTag = $("<a></a>");
		aTag.attr("href", path + "/moviedata/detail?movie_num=" + movieObj["movie_num"] +"&curPage="+ page['curPage']);
		var imgTag = $("<img></img>");
		imgTag.css("width", "200px");							
		imgTag.attr("src", movieObj["movie_poster_link"]);
		var spanTag = $("<span></span>").text(movieObj["movie_name"]);
		div.append(aTag);
		aTag.append(imgTag);
		div.append(spanTag);
		$("#TotListWrap").append(div);
	}
	var pageData = data["mvUser_Page"];
	var startPage = pageData["startPage"];
	var perBlock = pageData["perBlock"];
	var totPage = pageData["totPage"];
	var endPage = pageData["endPage"];
	var curPage = pageData["curPage"];
	// 전부 지움-
	$(".paging2").empty();
	
	// 이전
	if (parseInt(startPage) != 1) {
		var aTag = $('<a id="Befpage" href="#" curpage="'+ (startPage - 1) +'" class="aList"> < </a>');
		$(".paging2").append(aTag);
	}
	for (var i = startPage; i < (startPage + perBlock); i++) {
		if (i > totPage) break;
		var onClass = "";
		if (curPage == i) {
			onClass = "on";
		}
		var aTag = $('<a href="#" curpage="'+ i +'" class="aList ' + onClass + '">'+ i +'</a>');
		$(".paging2").append(aTag);
	}
	// 다음
	if (parseInt(endPage) < parseInt(totPage)) {
		var aTag = $('<a id="Nextpage" href="#" curpage="'+ (endPage + 1) +'"class="aList" > > </a>');
		$(".paging2").append(aTag);
	}
	//페이지 번호클릭시 
	$('.aList').click(function(e) {
		e.preventDefault();
		<%session.setAttribute("curpageTot",1);%>
		var curPage = $(this).attr('curPage');
		ajaxListTotAjax(curPage);
	});
}

//회원관심영화 리스트
function renderUserMovie(data) {
	var movieListUser = data["movieListUser"];
	$("#userListWrap").empty();
	var mvUser_Page	= data['mvUser_Page'];
	var curPageListUser = mvUser_Page['curPageListUser'];
	
	var path = "${path}";
	for (var i in movieListUser) {
		var movieObj = movieListUser[i];
		var div = $("<div></div>").addClass("userListDiv");
		var aTag = $("<a></a>");
		aTag.attr("href", path + "/moviedata/detail?movie_num=" + movieObj["MOVIE_NUM"]+'&curPageListUser='+curPageListUser);
		var imgTag = $("<img></img>");
		imgTag.css("width", "200px");
		imgTag.attr("src", movieObj["MOVIE_POSTER_LINK"]);
		var spanTag = $("<span></span>").text(movieObj["MOVIE_NAME"]);
		
		div.append(aTag);
		aTag.append(imgTag);
		div.append(spanTag);
		$("#userListWrap").append(div);
	}
	var pageData = data["mvUser_Page"];
	var startPage = pageData["startPage"];
	var perBlock = pageData["perBlock"];
	var totPage = pageData["totPage"];
	var endPage = pageData["endPage"];
	var curPageListUser = pageData["curPageListUser"];
	
	$(".paging1").empty();
	// 이전
	if (parseInt(startPage) != 1) {
		var aTag = $('<a id="Befpage" href="#" curPageListUser="'+ (startPage - 1) +'" class="uList"> < </a>');
		$(".paging1").append(aTag);
	}
	for (var i = startPage; i < (startPage + perBlock); i++) {
		if (i > totPage) break;
		var onClass = "";
		if (curPageListUser == i) {
			onClass = "on";
		}
		var aTag = $('<a href="#" curPageListUser="'+ i +'" class="uList ' + onClass + '">'+ i +'</a>');
		$(".paging1").append(aTag);
	}
	// 다음
	if (parseInt(endPage) < parseInt(totPage)) {
		var aTag = $('<a id="Nextpage" href="#" curPageListUser="'+ (endPage + 1) +'" class="uList"> > </a>');
		$(".paging1").append(aTag);
	}
	
	//페이지 번호클릭시 (회원 영화리스트) 그리기한것
	$('.uList').click(function(e) {
		e.preventDefault();
		var curPageListUser = $(this).attr('curPageListUser');	
		curPageListUser;
		alert(curPageListUser);
		ajaxListUser(curPageListUser);
	}); 


}
</script>
<style>
.userListDiv {
	display: inline-block;
	margin: 30px;
}
.TotListDiv{
	display: inline-block;
	margin: 30px;
}
.paging {
	text-align: center;
	margin: 30px;
}
.paging a {
    font-size: 19px;
    color: black;
    line-height: 2.9em;
	text-decoration: unset;
	background-color: #fffbfb;
    padding: 5px 7px;
    font-weight: bold;
    display: inline;
  	border: 1px outset;
  	margin: 3px;
  	text-align: center;
 }
#Nextpage{
	margin-left: 20px;
	background-color: gainsboro;
}
#Befpage{
	margin-right: 20px;
	background-color: gainsboro;
}

.paging a.on {
    background-color: #de9999;
	color: white;
	font-size: 22px;
	font-weight: bold;
}
#userListWrap{
	text-align: center;
	color: #925e5e;
    font-weight: bold;
    font-size: 18px;
}
#TotListWrap{
	text-align: center;
	color: #925e5e;
    font-weight: bold;
    font-size: 18px;
}
.userListDiv span{
	display: table;
	margin: auto;
}

.TotListDiv span{
	display: table;
	margin: auto;
}


</style>


</head>
<body>
${sessionScope.curpageUser}:유저 컬페이지 <br>
 ${sessionScope.curpageTot}:전체 컬페이지 수 <br>
 ${sessionScope.user_id} <br>
<%--  ${sessionScope.curpageListTot}  --%>
<%-- ${movieListUser} <br> --%>
<input type="hidden" id="curPageSave" value="">
<input type="hidden" id="curPageUserSave" value="">
<!-- 회원관심 영화리스트 -->
<h2 align="center">취향저격</h2> 
	<div id="userListWrap">
	 	<c:forEach var="UserList" items="${movieListUser}">
	 		<div class="userListDiv">
		 		<a href="${path}/moviedata/detail?movie_num=${UserList.MOVIE_NUM}" > 
		 			<img alt="이미지" src="${UserList.MOVIE_POSTER_LINK}" width="150px"></a>
		 		<span>${UserList.MOVIE_NAME}</span>
	 		</div> 
	 	</c:forEach>
 	</div>
 <!-- 	페이징 처리 -->
	<div class="paging paging1"> 
 	</div>
 	
 	<h2></h2>
  <!-- 전체영화리스트 -->
	<h2 align="center" style="padding-top: 30;">인기(최신) 상영작</h2>
	<div id="TotListWrap">
	 	<c:forEach var="list" items="${movieList}">
	 		<div class="TotListDiv">
 				<a href="${path}/moviedata/detail?movie_num=${list.movie_num}"> 
 					<img alt="이미지링크" src=" ${list.movie_poster_link}" width="150px"> </a>
 				<span>${list.movie_name}</span>	
	 		</div>
 		</c:forEach>
	</div>
 
 <!-- 	페이징 처리 -->
 	<div class="paging paging2">
 	</div>

</body>
</html>

