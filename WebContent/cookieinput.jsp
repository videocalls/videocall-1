<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- <form action="cookieSave.jsp"> -->
<form action="SessionServlet" >
	쿠키이름 : <input type="text" name="cookieName"><br>
	쿠키 값 : <input type="text" name="cookieValue"><br>
	<input type="submit" value="쿠키저장">
</form>
</body>
</html>