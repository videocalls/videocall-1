<%--
 * (C) Copyright AhnLab, Inc.
 *
 * Any part of this source code can not be copied with
 * any method without prior written permission from
 * the author or authorized person.
 *
 * @version		$Revision: 15612 $
 *
--%>

<%   
response.setHeader("Cache-Control","no-store");   
response.setHeader("Pragma","no-cache");   
response.setDateHeader("Expires",0);   
%>
<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@page trimDirectiveWhitespaces="true"%>

<%@page import="java.io.*,java.util.*"%>

<!DOCTYPE HTML> <!-- HTML5 -->
<html>
<head>
<meta charset="utf-8" />
<title>astx2</title>

<script src="include.js"></script>
<script src="inc/spin.min.js"></script>

<style>

</style>

</head>
<body>

<div id="spinner"></div>
	
<div id="content">
	<p>ASTx를 실행하여 주십시오...</p>
</div>

<%
	String setup_path = "/files";

	String agent_os = "Windows";
	String agent = request.getHeader("User-Agent").toLowerCase();

	if( agent.indexOf("windows") != -1 ) {
		; // nothing
	}else if( agent.indexOf("mac") != -1 ) {
		agent_os = "Mac";
		setup_path += "/mac";
	} else if( agent.indexOf("x11") != -1 || agent.indexOf("linux") != -1) {
		agent_os = "Linux";
		setup_path += "/linux";
	}
	
  List<String> setup_files = new ArrayList<String>();

  try {
	  String filePath = application.getRealPath(setup_path);

		File f = new File(filePath);
		File[] files = f.listFiles();
		    
		for(int i=0; i<files.length; i++ ) {
			if(files[i].isFile()) 
			{ 
				setup_files.add(files[i].getName());
			}
		} // end of for
  }
	catch(Exception e)
	{
		; // nothing
	}
%>

<p>&nbsp;</p>
<p><strong>[ASTx 설치파일 다운로드]</strong> for <%=agent_os%></p>
<%		
	for(String file_name : setup_files) 
	{
		out.println(String.format("<p><a href='%s%s/%s'>%s</a></p>\n", 
				request.getContextPath(), setup_path, file_name, file_name));
	}
%>		

<script>
window.onload = function() 
{
	// http://fgnass.github.io/spin.js/
	var opts = {
		lines:13, length:28, width:14, radius:42, scale:1, corners:1, color:'#0080FF', 
	  opacity:0.25, rotate:0, direction:1, speed:1, trail:60, fps:20,
		zIndex:999, top:'50%', left:'50%', shadow:false, hwaccel:false, position:'absolute'
	};
	
	new Spinner(opts).spin( document.getElementById('spinner') );
		
	doATX2CheckRun();
}

function gotoRedirectPage()
{
	<%
		String url = request.getParameter("page");
		if(null == url) url = "";
	%>
	
	window.location.href = "<%=url%>";
}

function doATX2CheckRun()
{
	$ASTX2.init(
		function onSuccess() {
			$_astxu.log('ASTX.init() success');
			gotoRedirectPage()
		},
		function onFailure() {
			var errno = $ASTX2.getLastError();
			$_astxu.log('ASTX.init() failure: errno='+errno);
			
			if(errno == $ASTX2_CONST.ERROR_NOTINST)	{
				setTimeout(function(){
					doATX2CheckRun();
				}, 500); // 0.5 seconds
			}
		}
	);
}
</script>

</body>
</html>
