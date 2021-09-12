<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "./include/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CCV</title>
<script type="text/javascript">
	if("${msg}" != ""){
		alert("${msg}");
	}
</script>
</head>
<body>
<%@ include file = "./include/header.jsp" %>
	<section class = "container" style = "max-width:560px;">
		<%-- <img src = "${path}/resources/img/ironMan.png"> --%>
		<video muted autoplay loop>
       		<source src="${path}/video/movie_ccv.mp4" type="video/mp4">
    </video>
	</section>
	<footer>
		Copyright 2021 CCV All Rights Reserved.
	</footer>
</body>
</html>