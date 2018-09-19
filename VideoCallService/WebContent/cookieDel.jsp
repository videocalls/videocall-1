<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String cookieName = request.getParameter("cookieName");
	Cookie cookie = new Cookie(cookieName,URLEncoder.encode("", "utf-8"));
	cookie.setPath("/");
	cookie.setMaxAge(0);
	response.addCookie(cookie);
	
	response.sendRedirect("cookieView.jsp");
%>