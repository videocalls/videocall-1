<%@ page contentType="text/html;charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>NICE평가정보 가상주민번호 서비스</title>
	<script language='javascript'>
		
	    function fnLoad()
		{
			/*var customerVerify = "<c:out value='${resultVO.customerVerify}'/>";
			var customerNameKor = "<c:out value='${resultVO.customerNameKor}'/>";
			window.opener.document.vnoform.target = "Parent_window";
			alert("customerVerify : "+customerVerify+", customerNameKor: "+customerNameKor); */
			
			if("<c:out value='${resultVO.checkplusFailChk}'/>" == "checkplusFail"){
				//alert("휴대폰인증 fail 코드 : "+"<c:out value='${resultVO.sErrorCode}'/>"+", 메시지 : "+"<c:out value='${resultVO.sRtnMsg}'/>");
				alert("<c:out value='${resultVO.sRtnMsg}'/>");
			}else{
				try{
				    window.opener.fn_certHpResult("<c:out value='${resultVO.customerVerify}'/>","","<c:out value='${resultVO.customerNameKor}'/>");
				}catch(e){
					alert("이미 부모창을 닫으셨습니다.\n다시 인증을 해주시기 바랍니다.");
				}
			}
			self.close();
		}
		
	</script>
</head>
<body onLoad="fnLoad()">
<%-- <form name="form_result_ipin" method="post">    
    <input type="hidden" id="customerVerify" name="customerVerify" value="<c:out value='${resultVO.customerVerify}'/>">
    <input type="hidden" id="customerNameKor" name="customerNameKor" value="<c:out value='${resultVO.customerNameKor}'/>">    
</form> --%>
</body>
</html>