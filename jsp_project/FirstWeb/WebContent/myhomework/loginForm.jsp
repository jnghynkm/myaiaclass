<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
	session 객체를 이용한 로그인 처리 구현
	폼 -> 로그인 처리 (분기: 성공과 실패) -> 로그인 체크 페이지 -> 로그아웃  
-->

   
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인 폼</title>
</head>

<body>
    <h1>회원 로그인 </h1>
    <hr>
    <form action="login.jsp" method="post">
        <table>
            <tr>
                <th><label for="userid">아이디</label></th>
                <td>
                    
                </td>
            </tr>
            <tr>
                <th><label for="pw">비밀번호</label></th>
                <td>
                    <input type="password" id="pw" name="pw">
                </td>
            </tr>
            <tr>
                <th></th>
                <td>
                    
                </td>
            </tr>
            <tr>
                <td >
                    
                </td>
                <td>
                <input type="submit" value="로그인">
                </td>
            </tr>
        </table>
    </form>

</body>

</html>