<%--
	Class Name : index.jsp
	Description : 최초화면으로 이동
	Modification Information

	수정일         		수정자		수정내용
	-------			--------	---------------------------
	2016.04.28		신동진       		최초 작성
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="kr.co.koscom.oppf.cmm.util.OppfSessionUtil" %>
<%@ page import="kr.co.koscom.oppf.cmm.service.OppfProperties" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<%
    String systemKind = OppfSessionUtil.getSystemKind(request);     //system kind를 가져온다.
    String title = OppfProperties.getProperty("Globals.domain." + systemKind + ".name");
    if("cpt".equals(systemKind)){
    	title += " - 기업회원";
    }else if("apt".equals(systemKind)){
    	title += " - 어드민";
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<title><%= title %></title>
<script type="text/javaScript">
document.location.href='<c:url value="/cmm/commonLogin.do"/>';
</script>
</head>
</html>