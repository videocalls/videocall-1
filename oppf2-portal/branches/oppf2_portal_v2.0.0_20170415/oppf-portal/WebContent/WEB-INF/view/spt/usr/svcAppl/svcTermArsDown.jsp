<%@ page import="java.math.BigInteger" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%
    String termsRegNo = request.getParameter("termsRegNo");
    String filename = java.net.URLEncoder.encode(termsRegNo+".mp3","UTF-8");
    String recordData = (String) request.getAttribute("recordData");
    byte[] bytes = new BigInteger(recordData, 16).toByteArray();

    request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");
    response.setHeader("Content-Disposition", "attachment;filename="+filename+";");
    response.setHeader("Content-Description", "JSP Generated Data");
    response.setContentType("application/octet-stream");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");

    ServletOutputStream stream = response.getOutputStream();
    try{
        stream.write(bytes);
        stream.flush();
    }catch (Exception e){
    }finally {
        stream.close();
    }
%>