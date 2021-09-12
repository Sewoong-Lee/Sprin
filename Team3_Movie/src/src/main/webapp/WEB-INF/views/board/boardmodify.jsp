<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${path}/resources/css/table.css" rel="stylesheet" type="text/css"><!-- 리소스폴더의 css파일 -->
<script type="text/javascript">
	$(function() {
		//파일 추가버튼
		$('#btnfileadd').click(function(e) {
			//alert('추가');
			e.preventDefault();  //버튼의 기본기능 제거
			$('#filelist').append('<li><input type="file" name="files" class="files"><button class="btnfileremove">삭제</button></li>');
			
		});
		
		//파일 삭제버튼
		//폼이 완료된 이후 생선되 애들은 이벤트를 달아줄 수 없다.
		//동적으로 생성된 버튼에 이벤트 달기
		$('#filelist').on('click', '.btnfileremove', function(e) {
			e.preventDefault();
			//alert('삭제');
			
			//선택한 자신의 부모 단위를 삭제
			$(this).parent().remove();
			
		});
		
		//저장전 유효성 체크
		$('#btnupdate').click(function(e) {
			e.preventDefault();
			
			if($('#userid').val() == ''){
				alert('로그인을 해요.');
			}else if($('#subject').val() == ''){
				alert('제목 입력');
			}else if($('#content').val() == ''){
				alert('내용 입력');
			}else{
				modifyform.submit();
			}
				
		});
		
		
	});
	
</script>
</head>
<body>
<%@ include file = "../include/header.jsp" %>
	<h2>게시물 수정</h2>
<%-- 	${board_list} --%>
	<form action="${path}/board/boardmodify" method="post" name="modifyform" enctype="multipart/form-data">
	<table id="board_table">
		<tr>
			<th>번호</th>
			<td> <input name="board_num" type="text" value="${board_list.mv_board.board_num}" readonly> </td>
		</tr>
		<tr>
			<th>작성자</th>
			<td> <input name="user_id" type="text" value="${board_list.mv_board.user_id}" id="user_id" readonly> </td>
		</tr>
		<tr>
			<th>제목</th>
			<td> <input name="subject" type="text" value="${board_list.mv_board.subject}" id="subject" > </td>
		</tr>
		<tr>
			<th>내용</th>
			<td> <textarea name="content" id="content"  rows="5" cols="20">${board_list.mv_board.content}</textarea> </td>
		</tr>
		<tr>
			<th>기존 파일</th>
			<td> <c:forEach var="bflist" items="${board_list.bflist}">
					<img alt="사진" src="${path}/uploadimg/${bflist.board_file_name}" width="100">
					<input type="checkbox" name="filedelete" value="${bflist.board_file_num}">삭제
				</c:forEach> 
			</td>
		</tr>
		<tr>
			<th>파일 수정</th>
			<td>
			<div><button id="btnfileadd">파일 추가</button></div>
			<ol id="filelist">
				<li><input type="file" name="files" class="files"><button class="btnfileremove">삭제</button></li>
			</ol>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center"> <button id="btnupdate">수정</button> </td>
		</tr>
	
	</table>
	</form>	
</body>
</html>