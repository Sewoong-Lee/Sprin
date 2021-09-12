<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${path}/resources/css/table.css" rel="stylesheet" type="text/css"><!-- 리소스폴더의 css파일 -->
<style type="text/css">
	img{
		width: auto;
	}
</style>
<script type="text/javascript">
	$(function() {
		//삭제 버튼을 누르면
		$('#btn_delete').click(function(e) {
			e.preventDefault();  //버튼의 기본기능 제거
			var board_num = $('#board_num').val();
			
			/* var userid = '${board_detail_map.mv_board.user_id}';
			//수정 권한 체크
			if('${sessionScope.userid}' != userid){
				alert('삭제 권한 없음');
				return;
			}; */  //잠깐 주석
			
			if(confirm("삭제하시겠습니까?")){
				location.href='${path}/board/boarddelete?board_num='+board_num;
				
			} 
		});
		
		// 수정 버튼을 클릭 했을때
		$('#btn_modify_form').click(function(e) {
			e.preventDefault();  //버튼의 기본기능 제거
			var board_num = $('#board_num').val();
			
			/* var userid = '${board_detail_map.mv_board.user_id}';
			//수정 권한 체크
			if('${sessionScope.userid}' != userid){
				alert('수정 권한 없음');
				return;
			}; */  //잠깐 주석처리
			// 수정 처리
			location.href='${path}/board/boardmodify?board_num='+board_num;	
		});
		
		
		
	});


</script>
</head>
<body>
<%@ include file = "../include/header.jsp" %>
	<h2>이벤트 게시판</h2>
	<!-- 잠깐 주석 -->
	<%-- <c:if test="${board_detail_map.mv_board.user_id == sessionScope.userid}"> --%>
		<button id="btn_modify_form">수정</button>
		<button id="btn_delete">삭제</button>
	<%-- </c:if> --%>
	
	<!-- 보드넘버 -->
	<input type="hidden" id="board_num" value="${board_detail_map.mv_board.board_num}">
	<table id="board_table">
		<tr>
			<th>번호 :</th> <td>${board_detail_map.mv_board.board_num}</td>
			<th>작성일 :</th> <td>${board_detail_map.mv_board.reg_date}</td>
		</tr>
		<tr>
			<th>작성자 : </th> <td>${board_detail_map.mv_board.user_id}</td>
			<th>조회수 : </th> <td>${board_detail_map.mv_board.read_cnt}</td>
		</tr>
		<tr>
			<th colspan="4" >${board_detail_map.mv_board.subject} </th>
		</tr>
		<tr>
			<td colspan="4" id="zz">
			${board_detail_map.mv_board.content} <br>
			<c:forEach var="file" items="${board_detail_map.bflist}">
				<img alt="사진" src="${path}/uploadimg/${file.board_file_name}">
			</c:forEach>
			</td>
		</tr>
	</table>
	
</body>
</html>