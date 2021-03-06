package mvc.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.Command;
import mvc.command.DateCommand;
import mvc.command.GreetingCommand;
import mvc.command.InvalidCommand;
import mvc.command.MemberLoginCommand;

public class FrontController extends HttpServlet {
	
	// Map<String, Command> -> 요청 uri, Command 클래스를 상속하는 객체 
	private Map<String, Command> commands;
			
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		commands = new HashMap<String, Command>();
		
		String configPath = config.getInitParameter("configPath");
		
		Properties prop = new Properties();
		
		FileInputStream fis = null;
		// 설정 파일의 시스템의 실제 경로
		String configFilepath = config.getServletContext().getRealPath(configPath);
		
		try {
			fis = new FileInputStream(configFilepath);
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		Iterator itr = prop.keySet().iterator();
		
		while(itr.hasNext()) {
			String command = (String) itr.next();
			String className = prop.getProperty(command);
			
			// commands Map -> command, className의 인스턴스를 저장
			try {
				Class commandClass = Class.forName(className);
				// Command 인스턴스 생성 
				Command commandInstance = (Command) commandClass.newInstance();
				
				// commands.put(경로, Command 인스턴스)
				commands.put(command, commandInstance);
				
				System.out.println(command+"="+className);
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
					
		}
		
		
		
		/*
		 * commands = new HashMap<String, Command>(); commands.put("/", new
		 * GreetingCommand()); commands.put("/greeting", new GreetingCommand());
		 * commands.put("/date", new DateCommand()); commands.put("/member/login", new
		 * MemberLoginCommand());
		 */
		
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		// 요청을 분석: URI 이용해서 분석
		// http://localhost:8080/mvc/greeting -> /greeting
		String command = request.getRequestURI();
		System.out.println(command);
		// /mvc/greeting
		System.out.println(command.indexOf(request.getContextPath()));
		if(command.indexOf(request.getContextPath()) == 0) {
			command = command.substring(request.getContextPath().length());
		}
		System.out.println(command);
		
		// 요청에 맞는 기능 실행 -> 결과 데이터를 실행
//		Object resultObj = null;
		
		Command cmd = commands.get(command);
		
		if(cmd == null) {
			cmd = new InvalidCommand();
		}
		
//		String viewPage = null;
		
//		if(command.equals("/greeting") | command.equals("/")) {
////			resultObj = "환영합니다.";
//			cmd = new GreetingCommand();
//		} else if(command.equals("/date")) {
////			resultObj = new Date();
//			cmd = new DateCommand();
//		} else if(command.equals("/member/login")) {
////			resultObj = "로그인 페이지입니다.";
//			cmd = new MemberLoginCommand();
//		} else {
////			resultObj = "잘못된 요청 경로입니다. 확인 후 다시 시도해주세요.";
//			cmd = new InvalidCommand();
//		}
		
//		viewPage = cmd.getViewPage(request, response);
		String viewPage = cmd.getViewPage(request, response);
		System.out.println(viewPage);
		
		// request의 속성에 결과 데이터를 저장
//		request.setAttribute("result", resultObj);
		
		// 응답을 위한 View 페이지로 포워딩
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/simpleView.jsp");
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
		
		
	}

	
	
}
