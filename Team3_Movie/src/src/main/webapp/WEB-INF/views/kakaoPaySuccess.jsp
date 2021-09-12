<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="include.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
	<h4>카카오페이 결제가 정상적으로 완료되었습니다.</h4>
 	
 	<table>
 		<tr>
 			<th>결제일시 : </th>
 			<td>${info.approved_at}</td>
 		</tr>
 		<tr>
 			<th>주문번호 : </th>
 			<td>${info.partner_order_id}</td>
 		</tr>
 		<tr>
 			<th>상품명 : </th>
 			<td>${info.item_name}</td>
 		</tr>
 		<tr>
 			<th>상품수량 : </th>
 			<td>${info.quantity}</td>
 		</tr>
 		<tr>
 			<th>결제금액 : </th>
 			<td>${info.amount.total}</td>
 		</tr>
 		<tr>
 			<th>결제방법 : </th>
 			<td>${info.payment_method_type}</td>
 		</tr>
 	</table>
 	
	</div>
	
	<%-- 결제일시: ${info.approved_at}<br/>
	주문번호: ${info.partner_order_id}<br/>
	상품명:   ${info.item_name}<br/>
	상품수량: ${info.quantity}<br/>
	결제금액: ${info.amount.total}<br/>
	결제방법: ${info.payment_method_type}<br/> --%>
	
	<%-- <h2>[[${info}]]</h2> --%>
</body>
</html>