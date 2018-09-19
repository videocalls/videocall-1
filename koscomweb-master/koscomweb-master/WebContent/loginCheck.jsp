<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	boolean loginflag = false;
	String id = null;
	Cookie[] cookies = request.getCookies();
	if(cookies != null){
		for(Cookie cookie : cookies	){
			if(cookie.getName().equals("loginOk")){
				loginflag = true;
				id = cookie.getValue();
				break;
			}
		}
	}
	
	if(loginflag){
		out.print(id +"님 환영합니다.");
	}else{
		out.print("해당 페이지는 로그인 한 사용자만 볼 수 있습니다.  로그인하고 오세요^^");
	}
%>

</body>
</html>