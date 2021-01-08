package com.aia.firstspring.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CookieController {
	
	@RequestMapping("/cookie/make")
	public String makeCookie(HttpServletResponse response) {
		// 쿠키 생성 -> 응답으로 처리 
		response.addCookie(new Cookie("auth", "test"));
		return "cookie/make"; // /WEB-INF/view/cookie/make.jsp
	}
	
	@RequestMapping("/cookie/view")
	public String viewCookie(
			Model model,
			HttpServletRequest request,
			//@CookieValue("auth") String cookieAuth
			@CookieValue(value = "auth", defaultValue = "not") String cookieAuth,
			@CookieValue(value = "uid", required = false) String uid
			) {
		
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			System.out.println(cookies[i].getName()+"="+cookies[i].getValue());
		}
		
		model.addAttribute("userName", "손흥민");
		model.addAttribute("cookieInfo", cookies);
		model.addAttribute("cookieAuth", cookieAuth);
		model.addAttribute("uid", uid); // 이전 쿠키수업 때 만들었던 것 있어야 함 내 브라우저는 사라짐, 기간 1년
		
		return "cookie/view"; // /WEB-INF/view/cookie/view.jsp
	}

}
