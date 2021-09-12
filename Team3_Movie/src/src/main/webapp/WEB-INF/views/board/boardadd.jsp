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
	input{
		width: 80%;
	}
	textarea {
		width: 80%;
	}

</style>

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
		$('#btnsave').click(function(e) {
			e.preventDefault();
			
			if($('#userid').val() == ''){
				alert('로그인을 해요.');
			}else if($('#subject').val() == ''){
				alert('제목 입력');
			}else if($('#content').val() == ''){
				alert('내용 입력');
			}else{
				addform.submit();
			}
				
		});
		
		
	});

</script>
</head>
<body>
<%@ include file = "../include/header.jsp" %>
	<h2>이벤트 게시물 등록</h2>
	
	<form action="${path}/board/boardadd" method="post" name="addform" enctype="multipart/form-data">
		<table id="board_table">
			<tr>
				<th>작성자 아이디</th>
				<td> <input type="text" name="user_id" id="user_id" value="${sessionScope.user_id}" readonly> </td>
				<%-- <td> <input type="text" name="user_id" id="user_id" value="${sessionScope.userid}" readonly> </td> --%>
			</tr>
			<tr>
				<th>제목</th>
				<td> <input type="text" name="subject" id="subject" > </td>
			</tr>
			<tr>
				<th>내용</th>
				<td> <textarea rows="5" cols="20" name="content" id="content" ></textarea> </td>
			</tr>
			<tr>
				<th>파일</th>
				<td>
				<div><button id="btnfileadd">파일 추가</button></div>
				<ol id="filelist">
					<li><input type="file" name="files" class="files"><button class="btnfileremove">삭제</button></li>
				</ol>
				</td>
			</tr>
			<tr>
				<td colspan="2"> 
				<button id="btnsave">저장</button> 
				<button type="reset">취소</button>
				</td>
			</tr>
		
		</table>
	
	
	</form>	
	
</body>
</html>