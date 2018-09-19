<%@page import="kr.co.koscom.dto.MemberDTO"%>
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
MemberDTO member = new MemberDTO();
member.setId("carami");
member.setName("강경미");
request.setAttribute("member", member); 
pageContext.setAttribute("p1", "page scope value");
session.setAttribute("p1", "session scope value");
pageContext.setAttribute("k", "10");
%>
member : ${member.id} <br>
session : ${sessionScope.p1} <br>
pageContext : ${pageScope.p1} <br>


k : ${k +1}<br>
k : ${k +1}<br>
k : ${k < 10}<br>
</body>
</html>