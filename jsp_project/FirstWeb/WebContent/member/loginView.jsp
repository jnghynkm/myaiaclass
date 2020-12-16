<%@page import="form.MemberData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	MemberData data = (MemberData) request.getAttribute("data");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원의 작성 데이터 출력</title>
<%-- <%= data %> --%>
</head>
<body>
	
	<h1>회원이 작성한 데이터 출력</h1>
	<table>
		<tr>
			<td>아이디</td>
			<td><%= data.getId() %></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><%= data.getPw() %></td>
		</tr>
	</table>

</body>
</html>