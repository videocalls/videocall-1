<%@ page contentType="text/html;charset=euc-kr" %>
<%-- <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%> --%>
<%@ taglib prefix="c"         uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"       uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%-- <%@ taglib prefix="ui"        uri="http://egovframework.gov/ctl/ui"%> --%>
<%@ taglib prefix="fn"        uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring"    uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"      uri="http://www.springframework.org/tags/form" %>
<%

    /********************************************************************************************************************************************
        NICE평가정보 Copyright(c) KOREA INFOMATION SERVICE INC. ALL RIGHTS RESERVED
        
        서비스명 : 가상주민번호서비스 (IPIN) 서비스
        페이지명 : 가상주민번호서비스 (IPIN) 사용자 인증 정보 처리 페이지
        
                   수신받은 데이터(인증결과)를 메인화면으로 되돌려주고, close를 하는 역활을 합니다.
    *********************************************************************************************************************************************/
    
    // 사용자 정보 및 CP 요청번호를 암호화한 데이타입니다. (ipin_main.jsp 페이지에서 암호화된 데이타와는 다릅니다.)
    String sResponseData = requestReplace(request.getParameter("enc_data"), "encodeData");
    
    // ipin_main.jsp 페이지에서 설정한 데이타가 있다면, 아래와 같이 확인가능합니다.
    String sReservedParam1  = requestReplace(request.getParameter("param_r1"), "");
    String sReservedParam2  = requestReplace(request.getParameter("param_r2"), "");
    String sReservedParam3  = requestReplace(request.getParameter("param_r3"), "");
     
    // 암호화된 사용자 정보가 존재하는 경우
    if (!sResponseData.equals("") && sResponseData != null)
    {

%>

<html>
<head>
<%--JQUERY 기본 스크립트--%>
<script type="text/javascript" src="<c:url value='/js/cmm/jquery-1.11.3.min.js'/>"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.js"></script>
<script type="text/javascript" src="<c:url value='/js/cmm/jquery-ui.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/cmm/html5shiv.min.js'/>"></script>

<%-- 공통 스크립트 --%>
<script type="text/javascript" src="<c:url value='/js/cmm/common.js'/>"></script>   <%-- 공통 스크립트 --%>
<script type="text/javascript" src="<c:url value='/js/cmm/commonUI.js'/>"></script> <%-- 공통팝업 스크립트 --%>
<script type="text/javascript" src="<c:url value='/js/cmm/string.js'/>"></script>   <%-- string prototype --%>
<script type="text/javascript" src="<c:url value='/js/cmm/paging.js'/>"></script>
<%-- 공통 스크립트 --%>

<script type="text/javascript" src="<c:url value='/js/cmm/util.js'/>"></script>

    <title>NICE평가정보 가상주민번호 서비스</title>
    <script language='javascript'>
        function fnLoad()
        {
            // 당사에서는 최상위를 설정하기 위해 'parent.opener.parent.document.'로 정의하였습니다.
            // 따라서 귀사에 프로세스에 맞게 정의하시기 바랍니다.
            <%-- parent.opener.parent.document.vnoform.enc_data.value = "<%= sResponseData %>";         
            parent.opener.parent.document.vnoform.param_r1.value = "<%= sReservedParam1 %>";
            parent.opener.parent.document.vnoform.param_r2.value = "<%= sReservedParam2 %>";
            parent.opener.parent.document.vnoform.param_r3.value = "<%= sReservedParam3 %>"; --%>
            
            //parent.opener.parent.document.vnoform.target = "Parent_window";
            
            // 인증 완료시에 인증결과를 수신하게 되는 귀사 클라이언트 결과 페이지 URL
            /* parent.opener.parent.document.vnoform.action = "ipin_result.jsp"; */
            /* parent.opener.parent.document.vnoform.action = "http://localhost:8080/cmm/ipin/ipinResult.do";
            parent.opener.parent.document.vnoform.submit(); */
            
            try{
                var url = "";
                url = window.opener.document.form_ipin.param_r3.value+"/cmm/ipin/ipinResult.do";
                var objParam = new Object();
                objParam.enc_data = String("<%= sResponseData %>");
                objParam.param_r1 = String("<%= sReservedParam1 %>");
                objParam.param_r2 = String("<%= sReservedParam2 %>");
                objParam.param_r3 = String("<%= sReservedParam3 %>");           
                util_movePage(url,objParam);
            }catch(e){
                alert("이미 부모창을 닫으셨습니다.\n다시 인증을 해주시기 바랍니다.");
                self.close();
            }
            
            //self.close();
        }
    </script>
</head>
<body onLoad="fnLoad()">

<%
    } else {
%>

<html>
<head>
    <title>NICE평가정보 가상주민번호 서비스</title>
    <body onLoad="self.close()">

<%
    }
%>
<%!
public String requestReplace (String paramValue, String gubun) {
        String result = "";
        
        if (paramValue != null) {
            
            paramValue = paramValue.replaceAll("<", "&lt;").replaceAll(">", "&gt;");

            paramValue = paramValue.replaceAll("\\*", "");
            paramValue = paramValue.replaceAll("\\?", "");
            paramValue = paramValue.replaceAll("\\[", "");
            paramValue = paramValue.replaceAll("\\{", "");
            paramValue = paramValue.replaceAll("\\(", "");
            paramValue = paramValue.replaceAll("\\)", "");
            paramValue = paramValue.replaceAll("\\^", "");
            paramValue = paramValue.replaceAll("\\$", "");
            paramValue = paramValue.replaceAll("'", "");
            paramValue = paramValue.replaceAll("@", "");
            paramValue = paramValue.replaceAll("%", "");
            paramValue = paramValue.replaceAll(";", "");
            paramValue = paramValue.replaceAll(":", "");
            paramValue = paramValue.replaceAll("-", "");
            paramValue = paramValue.replaceAll("#", "");
            paramValue = paramValue.replaceAll("--", "");
            paramValue = paramValue.replaceAll("-", "");
            paramValue = paramValue.replaceAll(",", "");
            
            if(gubun != "encodeData"){
                paramValue = paramValue.replaceAll("\\+", "");
                paramValue = paramValue.replaceAll("/", "");
            paramValue = paramValue.replaceAll("=", "");
            }
            
            result = paramValue;
            
        }
        return result;
  }
%>

</body>
</html>