<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : aptUserDtl.jsp
 * @Description : admin 포털 회원관리 상세
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.05.09  신동진        최초  생성
 * </pre>
 *
 * @author 신동진
 * @since 2016.05.09
 * @version 1.0
 * @apt
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
    var url = "<c:url value='/apt/aptUsr/aptUserList.do'/>";
    var param = new Object();
    param.paramMenuId = "03001";
    
    gfn_loginNeedMove(url, param);
}

//화면 로드 처리
$(document).ready(function(){
	<%-- 로그인 처리 --%>
	<c:if test="${empty LoginVO}">
	    fn_login();
	    return;
	</c:if>

	//목록
    $("#btnList").click(function(){
    	util_moveRequest("AptUserManageVO", "<c:url value='/apt/aptUsr/aptUserList.do'/>");    
    });
	
    //비밀번호 초기화
    $("#btnPwdChg").click(function(){
        fn_userTmpPwd();
    });
    
    //저장
    $("#btnSave").click(function(){
    	fn_save();
    });
    
    //이메일 주소 타입 변경 시
    $('#emailSelect').change(function(){
        if($(this).val()==""){
            $('#email_02').val("");
            $('#email_02').attr("disabled",false);
        }else{
            $('#email_02').val($(this).val());
            $('#email_02').attr("disabled",true);
        }
    });
    
    //기본정보 셋팅
    fn_init();
});

/*******************************************
 * 기능 함수
 *******************************************/
<%-- 기본정보 셋팅 --%> 
function fn_init(){
	//이메일
    var emailArr = "<c:out value='${resultDetail.adminEmail}'/>".split("@");
    if(emailArr != null){
    	$("#email_01").val(emailArr[0]); 
    	$("#email_02").val(emailArr[1]);
    }
    
    //휴대전화
    var phone = "<c:out value='${resultDetail.adminPhoneNo}'/>".split("-");
    if(phone != null){
    	$("#phoneNo_01").val(phone[0]);
    	$("#phoneNo_02").val(phone[1]);
    	$("#phoneNo_03").val(phone[2]);
    }
    
    //계정상태
    $("#deleteType").val("<c:out value='${resultDetail.deleteType}'/>");
} 

<%-- 저장 --%>
function fn_save(){
	if(!fn_validate()){
        return;
    }
	
	if($("#deleteType").val() == "Y"){
		if(!confirm("비활성화 된 admin은 로그인을 할 수 없습니다.\n계정상태를 비활성으로 저장하시겠습니까?")){
			return;
		}
	}
	
	//로딩 호출
	gfn_setLoading(true, "저장 중 입니다.");
			
	var moveUrl = "<c:url value='/apt/aptUsr/updateAptUser.ajax'/>";
    var param = $("#AptUserManageVO").serialize();
    var callBackFunc = "fn_saveCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(moveUrl, param, callBackFunc);
}
function fn_saveCallBack(data){
	//로그인 처리
	if(data.error == -1){
		fn_login();
	    return;
	}
	
	//로딩 호출
    gfn_setLoading(false);
	
	var msg = "성공";
	if(data.result <= 0){
		msg = "실패";
	}
	
	alert("저장에 "+msg+"하였습니다.");
}

<%-- 저장 처리 체크 --%>
function fn_validate(){
	//이름(한글)
    if(util_chkReturn($.trim($('#adminNameKor').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='이름(한글)'/>");
        $('#adminNameKor').val("");
        $('#adminNameKor').focus();
        return false;
    }
	
	//이메일
    var email = $.trim($('#email_01').val()) + "@" + $.trim($('#email_02').val());
    if(util_chkReturn(email, "s") == "") {
        alert("<spring:message code='errors.required' arguments='e-Mail'/>");
        $('#email_01').focus();
        return false;
    }else{
        if(!util_isEmail(email)){
            alert("<spring:message code='errors.email' arguments='e-Mail'/>");
            $('#email_01').focus();
            return false;
        }
    }
    $("#adminEmail").val(email);
	
    
    //휴대폰번호
    //[휴대폰번호2]
    if(util_chkReturn($.trim($("#phoneNo_02").val()), "s") == ""){
        alert("<spring:message code='errors.required' arguments='휴대폰 두번째자리'/>");
        $("#phoneNo_02").focus();
        return false;
    }else{
    	if(!util_isNum($.trim($("#phoneNo_02").val()))){
    		alert("<spring:message code='errors.required' arguments='휴대폰 두번째자리'/>");
            $("#phoneNo_02").focus();
            return false;
    	}
    }
    
    //[휴대폰번호3]
    if(util_chkReturn($.trim($("#phoneNo_03").val()), "s") == ""){
        alert("<spring:message code='errors.required' arguments='휴대폰 세번째자리'/>");
        $("#phoneNo_03").focus();
        return false;
    }else{
        if(!util_isNum($.trim($("#phoneNo_03").val()))){
            alert("<spring:message code='errors.required' arguments='휴대폰 세번째자리'/>");
            $("#phoneNo_03").focus();
            return false;
        }
    }
    
    var phone = $("#phoneNo_01").val() + "-" + $.trim($("#phoneNo_02").val()) + "-" + $.trim($("#phoneNo_03").val());
    if(!util_isTelno(phone, "-")){
    	alert("<spring:message code='errors.phone' arguments='휴대폰'/>");
        $('#phoneNo_01').focus();
        return false;
    }
    
    $("#adminPhoneNo").val(phone);
    
    //유선전화
    if(util_chkReturn($.trim($("#adminTelNo").val()), "s") != ""){
        if(!util_isNum($.trim($("#adminTelNo").val()))){
            alert("<spring:message code='errors.required' arguments='유선전화'/>");
            $("#adminTelNo").focus();
            return false;
        }
    }
	
	return true;
}

<%-- 비밀번호 초기화 --%>
function fn_userTmpPwd(){
	//이름(한글)
    if(util_chkReturn($.trim($('#adminNameKor').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='이름(한글)'/>");
        $('#adminNameKor').val("");
        $('#adminNameKor').focus();
        return false;
    }
	
	//이메일
    var email = $.trim($('#email_01').val()) + "@" + $.trim($('#email_02').val());
    if(util_chkReturn(email, "s") == "") {
        alert("<spring:message code='errors.required' arguments='e-Mail'/>");
        $('#email_01').focus();
        return false;
    }else{
        if(!util_isEmail(email)){
            alert("<spring:message code='errors.email' arguments='e-Mail'/>");
            $('#email_01').focus();
            return false;
        }
    }
    $("#adminEmail").val(email);
		
	//로딩 호출
	gfn_setLoading(true, "비밀번호 초기화 중 입니다.");
	
	var moveUrl = "<c:url value='/apt/aptUsr/updateAptUserTmpPwd.ajax'/>";
    var param = $("#AptUserManageVO").serialize();
    var callBackFunc = "fn_userTmpPwdCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(moveUrl, param, callBackFunc);
}
function fn_userTmpPwdCallBack(data){
	//로그인 처리
	if(data.error == -1){
		fn_login();
	    return;
	}
	
	//로딩 호출
    gfn_setLoading(false);
    
    if(data.result <= -1){
        alert("<spring:message code='fail.alert.process' />");
    }else{
        alert("<spring:message code='success.alert.process' />");
    }
    
    return;
}
 
</script>

</head>

<body>
<form:form commandName="AptUserManageVO" name="AptUserManageVO" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${AptUserManageVO.pageIndex}'/>" /><%-- 현재페이지번호 --%>
<input type="hidden" name="adminProfileRegNo" id="adminProfileRegNo" value="<c:out value='${AptUserManageVO.adminProfileRegNo}'/>" />

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
                    <h2>admin 회원 정보</h2>
                </div>
                <!-- // locatioin -->
                
                <p class="info_right"><span class="icon_basic">*</span> 필수 입력사항</p>                
                <div class="tb_write1">
                    <table>
                        <caption>admin 회원정보</caption>
                        <colgroup>
                            <col style="width:30%;">
                            <col style="width:*">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th scope="row">이름(한글)<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <input type="text" class="w100" id="adminNameKor" name="adminNameKor" value="<c:out value='${resultDetail.adminNameKor}'/>" />
                            </td>                  
                        </tr>
                        <tr>
                            <th scope="row">이름(영문)</th>
                            <td class="txt_l">
                                <input type="text" class="w100" id="adminNameEng" name="adminNameEng" value="<c:out value='${resultDetail.adminNameEng}'/>" />
                            </td>                  
                        </tr>
                        <tr>
                            <th scope="row">ID</th>
                            <td class="txt_l"><c:out value='${resultDetail.adminId}'/></td>                 
                        </tr>
                        <tr>
                            <th scope="row">e-Mail<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <input type="text" class="w100" id="email_01" /> @
                                <input type="text" class="w150" id="email_02">
                                <select id="emailSelect" title="이메일선택">
	                                <option value="">직접입력</option>
	                                <c:forEach var="emailList" items="${emailList}" varStatus="status">
	                                    <option value="${emailList.code_name_kor}">${emailList.code_name_kor}</option>
	                                </c:forEach>
                                </select>
                                <input type="hidden" id="adminEmail" name="adminEmail" value="<c:out value='${resultDetail.adminEmail}'/>" />
                            </td>                   
                        </tr>
                        <tr>
                            <th scope="row">휴대폰<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <select id="phoneNo_01" style="min-width:80px;">
                                    <option value="">선택</option>
                                    <c:forEach var="hpList" items="${hpList}" varStatus="status">
                                        <option value="${hpList.code_name_kor}">${hpList.code_name_kor}</option>
                                    </c:forEach>
                                </select>-
                                <input type="tel" id="phoneNo_02" class="w50" maxlength="4" onkeydown="util_numberonly(event);">- 
                                <input type="tel" id="phoneNo_03" class="w50" maxlength="4" onkeydown="util_numberonly(event);">
                                <input type="hidden" id="adminPhoneNo" name="adminPhoneNo" value="<c:out value='${resultDetail.adminPhoneNo}'/>" />
                            </td>                    
                        </tr>
                        <tr>
                            <th scope="row">유선전화</th>
                            <td class="txt_l">
                                <input type="text" id="adminTelNo" name="adminTelNo" class="w100" value="<c:out value='${resultDetail.adminTelNo}'/>" maxlength="13"/>
                            </td>                    
                        </tr>                        
                        <tr>
                            <th scope="row">계정상태</th>
                            <td class="txt_l">
                                <select id="deleteType" name="deleteType" style="min-width:100px;">
                                    <option value="N">활성</option>
                                    <option value="Y">비활성</option>
                                </select>
                            </td>                   
                        </tr>
                        <tr>
                            <th scope="row">등록일</th>
                            <td class="txt_l"><c:out value='${resultDetail.createDate}'/></td>
                        </tr>
                        <tr>
                            <th scope="row">수정일</th>
                            <td class="txt_l"><c:out value='${resultDetail.updateDate}'/></td>
                        </tr>
                        <tr>
                            <th scope="row">삭제일</th>
                            <td class="txt_l"><c:out value='${resultDetail.deleteDate}'/></td>
                        </tr>
                        </tbody>
                    </table>
                </div>

                <div class="btn_type3">
                    <div class="left"><span class="btn_gray1"><a href="javascript:void(0);" id="btnList">목록</a></span></div>
                    <div class="right">
                        <span class="btn_gray2"><a href="javascript:void(0);" id="btnPwdChg">비밀번호 초기화</a></span>
                        <span class="btn_gray2"><a href="javascript:void(0);" id="btnSave">저장</a></span>
                    </div>
                </div>
                <!-- // btn_type3 -->           

            </section>
            <!-- // content -->
        </div>
    </article>
    <!-- // container -->
</form:form>    
</body>
</html>