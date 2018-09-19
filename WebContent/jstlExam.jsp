<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:set var="name" value="kang" scope="request"/>
이름 : ${name }
<c:remove var="name" scope="request"/>
이름 : ${name }
<c:set var="n" value="10" scope="request"/>
<c:if test="${n==10 }">
	n은 10과 같습니다.
</c:if>
test
<br>
<c:set var="score" value="90" scope="request"/>
<c:choose>
	<c:when test="${score < 60 }">
		공부하세요^^
	</c:when>
	<c:when test="${score > 60 }">
		잘했어요^^
	</c:when>
	<c:otherwise>
		하하하
	</c:otherwise>
</c:choose>
<br>
<%
	List<String> list = new ArrayList<>();
	list.add("aaaa");
	list.add("bbbb");
	list.add("cccc");
	request.setAttribute("list", list);
%>

<c:forEach var="item" items="${list }">
	${item }<br>
</c:forEach>
</body>
</html>