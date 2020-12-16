<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>선호 카페 브랜드 설문조사</title>
</head>
<body>

	<h1>선호 카페 브랜드 설문조사</h1>
	<hr>
	
	<form action="requestResult.jsp" method="get">
		<table>
			<tr>
				<td>이름</td>
				<td><input type="text" name="userName" id="userName"></td>
			</tr>
			<tr>
				<td>직업</td>
				<td>
					<select name="job">
						<option value="무직">무직</option>
						<option value="학생">학생</option>
						<option value="회사원">회사원</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>연령</td>
				<td><input type="text" name="userAge" id="userAge"></td>
			</tr>
			<tr>
				<td>선호 카페 브랜드(중복선택가능)</td>
				<td>
					<input type="checkbox" name="cafe" value="스타벅스">스타벅스 <br>
					<input type="checkbox" name="cafe" value="할리스">할리스 <br>
					<input type="checkbox" name="cafe" value="이디야">이디야 <br>
					<input type="checkbox" name="cafe" value="투썸플레이스">투썸플레이스 <br>
					<input type="checkbox" name="cafe" value="엔젤리너스">엔젤리너스 <br>
					<input type="checkbox" name="cafe" value="기타">기타 <br>
				</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="제출"><input type="reset"></td>
			</tr>
		</table>
	</form>
</body>
</html>









