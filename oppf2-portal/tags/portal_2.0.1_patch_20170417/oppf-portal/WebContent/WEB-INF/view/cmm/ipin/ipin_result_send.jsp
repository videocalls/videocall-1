<%@ page contentType="text/html;charset=euc-kr" %>
<%-- <%@ page contentType="text/html; charset=utf-8"%> --%>
<%-- <%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %> --%>
<%-- <%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %> --%>
<%@ taglib prefix="c"         uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>NICE평가정보 가상주민번호 서비스</title>
    <script language='javascript'>
        
        function fnLoad()
        {
            /* var customerVerify = "<c:out value='${resultVO.customerVerify}'/>";
            var customerNameKor = "<c:out value='${resultVO.customerNameKor}'/>";
            window.opener.document.vnoform.target = "Parent_window";
            alert("customerVerify : "+customerVerify+", customerNameKor: "+customerNameKor); */
            try{
                window.opener.fn_certHpResult("<c:out value='${resultVO.customerVerify}'/>","","<c:out value='${resultVO.customerNameKor}'/>");
            }catch(e){
                alert("이미 부모창을 닫으셨습니다.\n다시 인증을 해주시기 바랍니다.");             
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