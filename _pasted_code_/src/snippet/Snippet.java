<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>영상통화예약 등록</title>
	</head>
	<body>
	
	<h1># 영상통화예약 등록 #</h1>
	
	<form action="resvCallInput" method="get">
	
		<%if(request.getAttribute("firmNo") == null) 
			request.setAttribute("firmNo", 101);	%>
	
		<%if(request.getAttribute("clntCi") == null) 
			request.setAttribute("clntCi", " ");	%>
	
		<%if(request.getAttribute("prdtCode") == null) 
			request.setAttribute("prdtCode", " ");	%>
	  
		<%if(request.getAttribute("rsvDt") == null) 
			request.setAttribute("rsvDt", " ");	%>
	  
		<%if(request.getAttribute("hpNo") == null) 
			request.setAttribute("hpNo", " ");	%>
	  
		<%if(request.getAttribute("rsvSrtTime") == null) 
			request.setAttribute("rsvSrtTime", " ");	%>
	  
		<%if(request.getAttribute("rsvEndTime") == null) 
			request.setAttribute("rsvEndTime", " ");	%>
	  
		<%if(request.getAttribute("sResult") == null) 
			request.setAttribute("sResult", " ");	%>
	  
	   자문회사           : 	<input type="text" name="firmNo" value="<%=request.getAttribute("firmNo") %>" size=3 maxlength=3 ><br>
	   고객CI       : 	<input type="text" name="clntCi" value="<%=request.getAttribute("clntCi") %>" ><br>
	   상품코드           : 	<input type="text" name="prdtCode" value="<%=request.getAttribute("prdtCode") %>" ><br>
	   예약일자           : 	<input type="text" name="rsvDt" value="<%=request.getAttribute("rsvDt") %>" size=8 maxlength=8 ><br>
	   핸드폰번호        : 	<input type="text" name="hpNo" value="<%=request.getAttribute("hpNo") %>" ><br>
	   예약시작시간(6): 	<input type="text" name="rsvSrtTime" value="<%=request.getAttribute("rsvSrtTime") %>" size=6 maxlength=6 ><br>
	   예약종료시간(6): 	<input type="text" name="rsvEndTime" value="<%=request.getAttribute("rsvEndTime") %>" size=6 maxlength=6 ><br>
	  
	    <input type="submit" value="예약등록"> 
	    <input type="reset" value="취소"><br><br><br>
	  
	  처리결과   : <input type="text" name="sResult"  value="<%=request.getAttribute("sResult") %>" size=40 disabled><br>
	    
	</form>
	
	</body>
	</html>
	
	
}
