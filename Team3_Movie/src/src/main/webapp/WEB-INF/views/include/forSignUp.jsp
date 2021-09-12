<%@ include file = "./include.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
function emailOk(){
	alert("${param.msg}");
}

function jusoCallBack(roadAddrPart1, addrDetail, zipNo) {
	document.signUpF.addr1.value = roadAddrPart1;
	document.signUpF.addr2.value = addrDetail;
	document.signUpF.zip.value = zipNo;
}
$(function(){
	function goPopup() {
		// Controller 호출하여 창 띄우기 요청
		var pop = window.open("${path}/user/jusoPopup", "pop","width=570,height=420, scrollbars=yes, resizable=yes");
	}
	
	$("#idCheck").click(function(e) {
		e.preventDefault();
		var userid = $("#user_id").val();
		//최소 글자 수~최대 글자 수 제한
		if(userid == ''){
			$('#user_id').css('color', 'red').val("아이디를 입력해주세요.");
			$('#user_id').click(function(){
				$('#user_id').css('color', 'black').val("");	
			});
			return;
		}
		$.ajax({
			url:'${path}/user/overlap',
			type:'get',
			data:'userid='+userid,
			dataType:'json', //서버에서 받아올 데이터의 형식(html, xml, json, text, etc.)
			success:function(data){
				//console.log(data); //값을 불러오지 못할 때는 디버깅 필수!
				if (data.code == 0) {
					alert(data.resultMsg);
					$('#idCheckYn').val('y');
					console.log($('#idCheckYn').val());
				}
				else {
					alert(data.resultMsg);
				}
			},
			error:function(e){
				alert("실패");
			}
		});

	});
	$('#findZip').click(function(){
		goPopup();
	});
	$('#signUp').click(function(e){
		e.preventDefault();
		if($('#user_id').val() == ''){
			$('#user_id').css('color', 'red').val("아이디를 입력해주세요.");
			$('#user_id').click(function(){
				$('#user_id').css('color', 'black').val("");
			});
		}
		else if ($('#idCheckYn').val() == 'n'){
			alert("아이디 중복 방지를 위한 중복 체크 버튼을 클릭해주세요.");
		}
		else if ($('#passwd').val() == ''){
			$('#passwd').attr('type', 'text');
			$('#passwd').css('color', 'red').val("비밀번호를 입력해주세요.");
			$('#passwd').click(function(){
				$('#passwd').css('color', 'black').val("");	
				$('#passwd').attr('type', 'password');
			});
		}
		else if ($('#checkpw').val() == '' || $('#checkpw').val() != $('#passwd').val()){
			$('#checkpw').attr('type', 'text');
			$('#checkpw').css('color', 'red').val("비밀번호가 일치하지 않습니다.");
			$('#checkpw').click(function(){
				$('#checkpw').css('color', 'black').val("");
				$('#checkpw').attr('type', 'password');
			});
		}
		else if ($('#user_name').val() == ''){
			$('#user_name').css('color', 'red').val("이름을 입력해주세요.");
			$('#user_name').focus();
			$('#user_name').click(function(){
				$('#user_name').css('color', 'black').val("");	
			});
		}
		else if ($('#tel').val() == ''){
			$('#tel').attr('type', 'text');
			$('#tel').css('color', 'red').val("휴대전화 번호를 입력해주세요.");
			$('#tel').focus();
			$('#tel').click(function(){
				$('#tel').css('color', 'black').val("");	
				$('#tel').attr('type', 'number');
			});
		}
		else if ($('#zip').val()==''){
			$('#zip').css('color', 'red').val("우편번호를 입력해주세요.");
			$('#findZip').focus();
		}
		else {
			document.signUpF.submit();
		}
	});
	
	$(".tag").change(function(){
		var cnt = $("input:checkbox[name='tag']:checked").length;
		if (cnt > 3){
			alert("3개를 모두 선택하셨습니다.");
			$(this).prop('checked', false);
	        }
	});
});
</script>