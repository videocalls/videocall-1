package com.coderby.myapp.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		try {
			HttpSession session = request.getSession();
			String contextName = request.getContextPath();
			String url = request.getRequestURI().replaceFirst(contextName, "");

			String param = request.getQueryString();

			if(!url.contains("/file/") && !url.contains("/tokensignin") && !url.contains("/member/login") && !url.contains("/member/logout") && !url.contains("/forward")) {
				session.setAttribute("url", url);
				session.setAttribute("param", param);
			}else{
				//nothing
				//로그인 또는 로그아웃 uri 일 경우에는 uri 정보를 저장하지 않는다.
			}
			
			String email= (String) request.getSession().getAttribute("email");
			if(email == null || email.equals("")){
				response.sendRedirect(request.getContextPath()+"/member/login");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	}

}
