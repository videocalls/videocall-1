<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : aptUserInfoPwConfirm.jsp
 * @Description : admin 포탈 회원의 비밀번호 체크
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.05.24  신동진        최초  생성
 * </pre>
 *
 * @author 신동진
 * @since 2016.05.24
 * @version 1.0
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>

<script language="javascript" type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/
 
/*******************************************
 * 이벤트 함수
 *******************************************/
<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
    var url = "<c:url value='/apt/aptUsr/aptUserInfoPwConfirm.do'/>";
    var param = new Object();
    param.paramMenuId = "10001";
    
    gfn_loginNeedMove(url, param);
}

//화면 로드 처리
$(document).ready(function(){
	<%-- 로그인 처리 --%>
    <c:if test="${empty LoginVO}">
        fn_login();
        return;
    </c:if>
    
	$("#btnConfirm").click(function(){
		fn_pwConfirm();
	});
});

/*******************************************
 * 기능 함수
 *******************************************/
<%-- 비밀번호 확인 --%>
function fn_pwConfirm(){
	if(util_chkReturn($.trim($('#adminPassword').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='비밀번호'/>");
        $('#adminPassword').focus();
        return false;
    }
	
	var moveUrl = "<c:url value='/apt/aptUsr/chkAptUserInfoPwConfirm.ajax'/>";
    var param = $("#AptUserInfoVO").serialize();
    var callBackFunc = "fn_pwConfirmCallBack";
    
    //로딩 호출
    gfn_setLoading(true, "비밀번호 확인 중입니다.");
    
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(moveUrl, param, callBackFunc);
}
function fn_pwConfirmCallBack(data){
	//로그인 처리
    if(data.error == -1){
    	fn_login();
        return;
    }
	
	//로딩 호출
    gfn_setLoading(false);
	
    var result = data.result;
    
    if(result <= 0){
        alert("<spring:message code='errors.idfind'/>");
        $('#adminPassword').focus();
        return;
    }else{
        $("#adminProfileRegNo").val(data.adminProfileRegNo);
        util_moveRequest("AptUserInfoVO", "<c:url value='/apt/aptUsr/aptUserInfoDtl.do'/>");
    }
}
 
</script>

</head>

<body>
<form:form commandName="AptUserInfoVO" name="AptUserInfoVO" method="post">
<input type="hidden" name="adminProfileRegNo" id="adminProfileRegNo" value="" />

    <%-- 탑과 대메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>
    <%-- 탑과 대메뉴 영역 --%>
    
    <!-- // wrap_top -->
    <article class="container">
        <div>
            <%-- lnb(좌측메뉴) 영역 --%>
            <%@ include file="/WEB-INF/view/cmm/common-include-left.jspf" %>
            <%-- lnb(좌측메뉴) 영역 --%>
            
            <!-- content -->
            <section class="content">
                <div class="location">
                    <h2>내 정보 관리</h2>
                </div>
                <!-- // locatioin -->
                
                <div class="pw_confirm">
                    <p class="tit">개인정보를 안전하게 보호하기 위해<br>비밀번호를 한번 더 입력해주세요.</p>
                    <input type="password" id="adminPassword" name="adminPassword" title="비밀번호 입력" placeholder="비밀번호 입력"
                           onkeydown="javascript:if(event.keyCode == 13) fn_pwConfirm();"
                    />
                    <input type="hidden" id="enterTemp" />
                    <div class="btn_area">
                        <a href="javascript:void(0);" id="btnConfirm" class="btn_type1 type2">확인</a>
                    </div>
                </div>
                
            </section>
            <!-- // content -->
        </div>
    </article>
    <!-- // container -->
</form:form>    
</body>
</html>