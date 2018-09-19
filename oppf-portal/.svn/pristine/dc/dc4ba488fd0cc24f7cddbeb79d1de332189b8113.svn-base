<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : aptUserReg.jsp
 * @Description : admin 포털 회원관리 등록
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
 var g_idChk = false;
 
/*******************************************
 * 이벤트 함수
 *******************************************/
<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
    var url = "<c:url value='/apt/aptUsr/aptUserReg.do'/>";
    var param = new Object();
    param.paramMenuId = "03002";
    
    gfn_loginNeedMove(url, param);
}

//화면 로드 처리
$(document).ready(function(){
	<%-- 로그인 처리 --%>
	<c:if test="${empty LoginVO}">
	    fn_login();
	    return;
	</c:if>
	
	//id 중복확인
	$("#btnIdChk").click(function(){
		fn_idChk();
	});
	
	//[아이디재입력]버튼 클릭 시 호출
    $("#btnIdReInput").click(function(){
    	g_idChk = false;   //아이디체크 초기화
        
        $("#adminId").css("background-color","#ffffff");
        $("#adminId").attr("readonly",false);
        
        $("#btnIdReInput").hide(); //[아이디재입력]
        $("#btnIdChk").show(); //[중복확인]
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
    
});

/*******************************************
 * 기능 함수
 *******************************************/
<%-- id chk --%>
function fn_idChk(){
	if(util_chkReturn($.trim($('#adminId').val()), "s") == "") {
		alert("<spring:message code='errors.required' arguments='ID'/>");
        $('#adminId').val("");
        $('#adminId').focus();
        return false;
	}
	
	if(g_idChk){
		alert("이미 중복확인을 하셨습니다.");
		return;
	}
	
	//로딩 호출
	gfn_setLoading(true, "아이디 중복 확인 중 입니다.");
	
	var moveUrl = "<c:url value='/apt/aptUsr/selectAptUserIdChk.ajax'/>";
    var param = $("#AptUserManageVO").serialize();
    var callBackFunc = "fn_idChkCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(moveUrl, param, callBackFunc);
}
function fn_idChkCallBack(data){
	//로그인 처리
	if(data.error == -1){
		fn_login();
	    return;
	}
	
	//로딩 호출
    gfn_setLoading(false);
	
	var result = data.result;
	
	if(result > 0){
		alert("<spring:message code='fail.alert.idcheck'/>");
		$('#adminId').focus();
	}else{
		alert("<spring:message code='success.alert.idcheck'/>");
		$('#adminId').css("background-color","silver");
		$('#adminId').attr("readonly",true);
		
		$("#btnIdChk").hide(); //[중복확인]
		$("#btnIdReInput").show(); //[아이디재입력]
		g_idChk = true;
	}
}
 
<%-- 저장 --%>
function fn_save(){
	if(!fn_validate()){
        return;
    }
	
	//로딩 호출
	gfn_setLoading(true);
				
	var moveUrl = "<c:url value='/apt/aptUsr/insertAptUserReg.ajax'/>";
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
	
	//실패
	if(data.result <= 0){
		alert("<spring:message code='fail.alert.regist'/>");
		return;
	}
	
	alert("<spring:message code='success.alert.regist'/>");
	util_moveRequest("AptUserManageVO", "<c:url value='/apt/aptUsr/aptUserList.do'/>");
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
	
    //아이디
    if(util_chkReturn($.trim($('#adminId').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='ID'/>");
        $('#adminId').val("");
        $('#adminId').focus();
        return false;
    }else{
    	if(!g_idChk){
    		alert("<spring:message code='errors.idcheck'/>");
            $('#adminId').focus();
            return false;
    	}
    	
    	//아이디 policy
    	if(!gfn_validationCheckId("adminId")){
            return false;
        }
    }
	
    var adminId = $("#adminId").val();
    var adminPassword = $.trim($('#adminPassword').val());
    var adminPasswordConfirm = $.trim($('#adminPasswordConfirm').val());
        
    //[비밀번호확인]
    if(util_chkReturn(adminPasswordConfirm, "s") == ""){
        alert("<spring:message code='errors.required' arguments='비밀번호확인'/>");
        $("#adminPasswordConfirm").focus();
        return false;
    }
    
    //[비밀번호]!=[비밀번호확인]
    if(adminPassword != adminPasswordConfirm){
        alert("<spring:message code='errors.notsame' arguments='비밀번호,비밀번호확인'/>");
        return false;
    }
    
    //[비밀번호]가 [아이디]와 동일한지 체크
    if(adminId == adminPassword) {
        alert("아이디 와 비밀번호가 동일합니다.\r\n비밀번호를 다시 입력하십시오.");
        $("#adminPassword").focus();
        return false;
    }
    
    //[비밀번호]에 [아이디]가 포함되어 있는지 체크
    if(adminId.substring(0,4) == adminPassword.substring(0,4)){
        alert("아이디 와 비밀번호 앞4자리가 동일 합니다.\r\n비밀번호를 다시 입력하십시오.");
        $("#adminPassword").focus();
        return false;
    }
    
    //비밀번호 policy
    if(!gfn_validationCheckPw("adminPassword")){
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
        alert("<spring:message code='errors.required' arguments='휴대폰번호 두번째자리'/>");
        $("#phoneNo_02").focus();
        return false;
    }else{
    	if(!util_isNum($.trim($("#phoneNo_02").val()))){
    		alert("<spring:message code='errors.required' arguments='휴대폰번호 두번째자리'/>");
            $("#phoneNo_02").focus();
            return false;
    	}
    }
    
    //[휴대폰번호3]
    if(util_chkReturn($.trim($("#phoneNo_03").val()), "s") == ""){
        alert("<spring:message code='errors.required' arguments='휴대폰번호 세번째자리'/>");
        $("#phoneNo_03").focus();
        return false;
    }else{
        if(!util_isNum($.trim($("#phoneNo_03").val()))){
            alert("<spring:message code='errors.required' arguments='휴대폰번호 세번째자리'/>");
            $("#phoneNo_03").focus();
            return false;
        }
    }
    
    var phone = $("#phoneNo_01").val() + "-" + $.trim($("#phoneNo_02").val()) + "-" + $.trim($("#phoneNo_03").val());
    if(!util_isTelno(phone, "-")){
    	alert("<spring:message code='errors.phone' arguments='휴대폰번호'/>");
        $('#phoneNo_01').focus();
        return false;
    }
    
    $("#adminPhoneNo").val(phone);
    
    //내선번호
    if(util_chkReturn($.trim($("#adminTelNo").val()), "s") != ""){
        if(!util_isNum($.trim($("#adminTelNo").val()))){
            alert("<spring:message code='errors.required' arguments='내선번호'/>");
            $("#adminTelNo").focus();
            return false;
        }
    }
	
	return true;
}
 
</script>

</head>

<body>
<form:form commandName="AptUserManageVO" name="AptUserManageVO" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="1" /><%-- 현재페이지번호 --%>
<input type="hidden" id="deleteType" name="deleteType" value="N" />

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
                    <h2>admin 회원 등록</h2>
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
                                <input type="text" class="w100" id="adminNameKor" name="adminNameKor" value="" />
                            </td>                  
                        </tr>
                        <tr>
                            <th scope="row">이름(영문)</th>
                            <td class="txt_l">
                                <input type="text" class="w100" id="adminNameEng" name="adminNameEng" value="" />
                            </td>                  
                        </tr>
                        <tr>
                            <th scope="row">ID<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <input type="text" class="w100" id="adminId" name="adminId" value="" />
                                &nbsp;
                                <span class="btn_gray1"><a href="javascript:void(0);" id="btnIdChk">중복확인</a></span>
                                <span class="btn_gray1"><a href="javascript:void(0);" id="btnIdReInput" style="display:none;">아이디재입력</a></span>
                                <span class="info_msg">영문, 숫자 조합, 소문자 입력4~15자 이내</span> 
                            </td>                 
                        </tr>
                        <tr>
                            <th scope="row">비밀번호<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <input type="password" class="w100" id="adminPassword" name="adminPassword" value="" />
                                &nbsp;
                                <span class="info_msg">8~15자의 영문 대/소문자, 숫자, 특수문자 중 3개 이상의 조합</span>
                            </td>                 
                        </tr>
                        <tr>
                            <th scope="row">비밀번호 확인<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <input type="password" class="w100" id="adminPasswordConfirm" value="" />
                            </td>                 
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
                                <input type="hidden" id="adminEmail" name="adminEmail" value="" />
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
                                <input type="hidden" id="adminPhoneNo" name="adminPhoneNo" value="" />
                            </td>                    
                        </tr>
                        <tr>
                            <th scope="row">내선번호</th>
                            <td class="txt_l">
                                <input type="text" id="adminTelNo" name="adminTelNo" class="w100" value="" />
                            </td>                    
                        </tr>
                        </tbody>
                    </table>
                </div>

                <div class="btn_type3">
                    <div class="right">
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