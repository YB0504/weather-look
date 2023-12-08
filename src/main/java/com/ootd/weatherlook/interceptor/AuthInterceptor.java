package com.ootd.weatherlook.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class AuthInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
		Object nick = request.getAttribute("nick");
		System.out.println("preHandler 동작 확인");
				
		if(session.getAttribute("nick") == null) {
			response.sendRedirect("loginform");
			return false;
		}
		return true;
		
	}

	
	/*
	 * @Override public void postHandle(HttpServletRequest request,
	 * HttpServletResponse response, Object handler, ModelAndView modelAndView)
	 * throws Exception { // TODO Auto-generated method stub
	 * HandlerInterceptor.super.postHandle(request, response, handler,
	 * modelAndView); }
	 * 
	 * @Override public void afterCompletion(HttpServletRequest request,
	 * HttpServletResponse response, Object handler, Exception ex) throws Exception
	 * { // TODO Auto-generated method stub
	 * HandlerInterceptor.super.afterCompletion(request, response, handler, ex); }
	 */
	
}
