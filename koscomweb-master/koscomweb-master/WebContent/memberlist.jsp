<%@page import="kr.co.koscom.dto.MemberDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>회원목록!!</h1>
<%
	List<MemberDTO> list =(List<MemberDTO>)request.getAttribute("memberList");
%>
<table>
	<tr>
		<th>이름</th>
		<th>아이디</th>
		<th>비밀번호</th>
		<th>이메일</th>
		<th>가입일</th>
		<th>수정</th>
		<th>삭제</th>
	</tr>
	<%if(list != null){
		for(MemberDTO member : list){
		%>
	
	<tr>
		<td><%=member.getName() %></td>
		<td><%=member.getId() %></td>
		<td><%= member.getPassword()%></td>
		<td><%= member.getEmail()%></td>
		<td><%= member.getRedDate()%></td>
		<td><a href="memberUpdateForm?id=<%=member.getId() %>">수정</a></td>
		<td><a href="memberDel?id=<%=member.getId() %>">삭제</a></td>
	</tr>
	<% 
		}//end for
	}//end if
	%>
</table>
<br>
<a href="memberInput.html">회원가입하러가기~~~~~!!!</a>
</body>
</html>