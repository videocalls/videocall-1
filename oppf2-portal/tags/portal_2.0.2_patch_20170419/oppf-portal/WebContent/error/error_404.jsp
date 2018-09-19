<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<%@ page import="kr.co.koscom.oppf.cmm.util.OppfStringUtil" %>
<%
    //moblie여부 셋팅
    boolean isMobile = OppfStringUtil.isMobile(request);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : error_404.jsp
 * @Description : 에러 404
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.06.30  신동진        최초  생성
 * </pre>
 *
 * @author 신동진
 * @since 2016.06.30
 * @version 1.0
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>
<%
    if(isMobile){
%>
<!-- 반응형 페이지일 경우 추가 -->
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width"> 
<!-- // 반응형 페이지일 경우 추가 -->
<%      
    }
%>
<script language="javascript" type="text/javascript">
function fn_main(){
	document.location.href= httpContextpath + '<c:url value="/cmm/commonLogin.do"/>';
}
</script>
</head>
<body>
<%
    if(isMobile){
%>
<!-- 반응형 페이지일 경우 추가 -->
<div class="wrap mobile_wrap"><!-- 반응형 페이지일 경우 .mobile_wrap 추가 --> 
<!-- // 반응형 페이지일 경우 추가 -->
<%      
    }else{
%>
<div class="wrap">
<%      
    }
%>
   
    <div class="error_wrap">
        <div class="head"><h1 class="logo"><a href="javascript:fn_main();"><img src="<c:url value="/images/spt/common/logo.png"/>" alt="Koscom OpenAPI Platform"></a></h1></div>
        <div class="cont">
            <div class="error_box">
                <p class="tit"><span>페이지가 없거나 잘못된 경로입니다.</span></p>
                <p class="txt">입력한 페이지의 주소 경로가 정확한지 다시 한번 확인 후 이용하시기 바랍니다.<br>서비스 이용에 불편을 드려 죄송합니다.</p>
            </div>
            <div class="btn_area">
                <a href="javascript:history.back(-1);" class="btn_type2">이전페이지 돌아가기</a>
                <a href="javascript:fn_main();" class="btn_type1">메인화면으로 이동</a>
            </div>
        </div>
        <div class="foot">
            <address>
                서울시 영등포구 여의나루로 76(150-010) 금융투자 핀테크 포털<br><!-- 2016-06-24 전체명칭수정 -->
                COPYRIGHT 2016. KOSCOM CORP. ALL RIGHTS RESERVED.
            </address>
        </div>
    </div>

</div>

</body>
</html>