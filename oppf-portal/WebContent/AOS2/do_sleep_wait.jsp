<%--
 * (C) Copyright AhnLab, Inc.
 *
 * Any part of this source code can not be copied with
 * any method without prior written permission from
 * the author or authorized person.
 *
 * @version		$Revision: 14980 $
 *
--%>
<%
response.setHeader("Cache-Control","no-store");   
response.setHeader("Pragma","no-cache");   
response.setDateHeader("Expires",0);   
%>
<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@page trimDirectiveWhitespaces="true"%>

<%@page import="java.util.*,com.ahnlab.astx2.util.*"%>

<%
 	int sleep_time = 1000; // default
 	
 	try {
 		String mtime = Util.getNC(request.getParameter("mtime"));
 	 	if(mtime.length() > 0) {
 	 		sleep_time = Integer.parseInt(mtime);
 	 	}
 	}catch(Exception e)	{}

 	Trace.d("[do_wait] sleep_time="+sleep_time);
 	
	try {
		Thread.sleep(sleep_time);
	}catch(InterruptedException e){
		; // nothing
	}
	
	JsonMap json = new JsonMap();
	json.set("result", "ACK");

	out.println(json.toString());
	out.flush();
%>
