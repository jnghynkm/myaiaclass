
<%@page import="java.util.Iterator"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	boolean result = false;

	// 파라미터 이름이 title인 데이터를 저장할 변수
	String title = null;
	
	// 1. multipart/form-data 여부 확인
	boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	
	if(isMultipart){
		
		// 2. 업로드 할 파일을 보관할 FileItem의 Factory 객체 설정
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		// 3. 요청을 처리(form 안의 input들을 분리)할 ServletFileUpload 객체 생성
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		// 4. 사용자의 요청을 파싱(데이터를 추출해서 원하는 형식으로 만드는 것)
		// FileItem -> 사용자의 요청 파라미터인 input의 객체 
		List<FileItem> items = upload.parseRequest(request);
		
		Iterator<FileItem> itr = items.iterator();
		
		while(itr.hasNext()){ 
			FileItem item = itr.next();
			
			// 폼 필드와 파일을 구분해서 처리
			if(item.isFormField()){
				// true -> type=file 인 것을 제외한 나머지 필드
				// 필드 이름, 파라미터 이름
				String fieldName = item.getFieldName();
				if(fieldName)
			}
			
		}
		
		
	}
	
%>