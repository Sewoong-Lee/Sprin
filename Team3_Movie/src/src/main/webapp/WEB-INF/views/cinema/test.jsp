<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
      video { max-width: 80%; display: block; margin: 20px auto; }
      </style>
</head>
<body>
<iframe width="587" height="330" src="https://www.youtube.com/embed/a6rBGQMXc90?autoplay=1&mute=1" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>

<hr>
	<video muted autoplay loop>
    	<source src="${path}/video/movie_ccv.mp4" type="video/mp4">
       <%-- <source src="${path}/uploadimg/man.mp4" type="video/mp4"> --%>
      <strong>Your browser does not support the video tag.</strong>
    </video>
    
     <!-- <video controls poster="videos/Clouds.png">
      <source src="videos/Clouds.mp4" type="video/mp4">
      <strong>Your browser does not support the video tag.</strong>
    </video> -->
</body>
</html>