<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문조사 결과</title>
</head>
<body>

	<h1>설문조사 결과</h1>
	<hr>
	
		<table>
			<tr>
				<td>이름</td>
				<td> <%= request.getParameter("userName") %></td>
			</tr>
			<tr>
				<td>직업</td>
				<td>
					<%= request.getParameter("job") %>
				</td>
			</tr>
			<tr>
				<td>연령</td>
				<td>
					<%= request.getParameter("userAge") %>
				</td>
			</tr>
			<tr>
				<td>선호 카페 브랜드</td>
				<td>
					
					<%
					
						String[] cafe = request.getParameterValues("cafe");
					
					for(int i=0; i<cafe.length; i++){
						out.println(cafe[i]+"<br>");
					}
					
					%>
					
				</td>
			</tr>
		</table>
</body>
</html>









