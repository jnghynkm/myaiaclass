<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<c:url value="/css/default.css" var="url_defaultCss" scope="application" />	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
<%-- <link rel="stylesheet" 
href="<%= request.getContextPath()%>/css/default.css"> --%>
<link rel="stylesheet" href="${url_defaultCss}">
</head>
<body>

	<%@ include file="/include/header.jsp" %>

	<%@ include file="/include/nav.jsp" %>

	<div class="contents">
		<h2 class="content_title">Index</h2>
		<hr>
		<div class="content">
		인덱스 페이지입니다.
		</div>
	</div>
	
	<%@ include file="/include/footer.jsp" %>

</body>
</html>