<%@page import="java.sql.Connection"%>
<%@page import="jdbc.ConnectionProvider"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	int result = 0;

	// DAO 객체의 insert 메소드로 member 참조변수 전달, Connection 객체의 참조변수 전달
	
	Connection conn = ConnectionProvider.getConnection();

%>