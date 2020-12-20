<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	// 세션 객체 소멸 -> session 객체에 저장한 로그인 데이터도 소멸 : 로그아웃
	session.invalidate();

%>
<script>
	alert('로그아웃되었습니다.');
	location.href='<%= request.getContextPath()%>/index.jsp';
</script>
