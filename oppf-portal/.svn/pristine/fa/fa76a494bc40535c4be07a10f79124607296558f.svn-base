<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : cptUserManageDtl.jsp
 * @Description : 기업회원관리 상세
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.06.24  신동진        최초  생성
 * </pre>
 *
 * @author 신동진
 * @since 2016.06.24
 * @version 1.0
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
    var url = "<c:url value='/apt/cptUsr/cptUserManageList.do'/>";
    var param = new Object();
    param.paramMenuId = "02001";
    
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
        fn_list();
    });
    
    //비밀번호 초기화
    $("#btnTempPassword").click(function(){
        fn_setTempPassword();
    });
    
    //저장
    $("#btnSave").click(function(){
        fn_saveCheck();
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
    
    //[중복확인]버튼 클릭 시 호출
    $("#btnChkId").click(function(){
    	fn_checkId();
        
    });
    
    //[아이디재입력]버튼 클릭 시 호출
    $("#btnIdReInput").click(function(){
    	g_idChk = false;   //아이디체크 초기화
    	
    	$("#operatorId").css("background-color","#ffffff");
        $('#operatorId').attr("readonly",false);
        
        $("#btnIdReInput").hide(); //[아이디재입력]
        $("#btnChkId").show(); //[중복확인]
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
    var emailArr = "<c:out value='${resultDetail.operatorEmail}'/>".split("@");
    if(emailArr != null){
        $("#email_01").val(emailArr[0]); 
        $("#email_02").val(emailArr[1]);
    }
    
    //휴대폰
    var phone = "<c:out value='${resultDetail.operatorPhoneNo}'/>".split("-");
    if(phone != null){
        $("#phoneNo_01").val(phone[0]);
        $("#phoneNo_02").val(phone[1]);
        $("#phoneNo_03").val(phone[2]);
    }
    
    //비밀번호 초기화
    if(util_chkReturn($.trim($("#operatorProfileRegNo").val()), "s") != ""){
    	$("#btnTempPasswordSpan").show();
    }
} 
 
 
<%-- 목록 --%>
function fn_list(){
    util_moveRequest("CptUserManageVO", "<c:url value='/apt/cptUsr/cptUserManageList.do'/>", "_self");
}

<%-- ID 중복체크 --%>
function fn_checkId(){
	if(!gfn_validationCheckId("operatorId")){
	    return false;
	}

	var url = "<c:url value='/apt/cptUsr/checkCptUserManageOperatorId.ajax'/>";
	var reqData = {"operatorId":$("#operatorId").val()};
	util_ajaxPage(url, reqData, "fn_checkIdCallBack");	
}
function fn_checkIdCallBack(data){
    var result = data.result;
    
    if(result > 0){
        alert("<spring:message code='fail.alert.idcheck'/>");
        $('#operatorId').focus();
    }else{
        alert("<spring:message code='success.alert.idcheck'/>");
        $("#operatorId").css("background-color","silver");
        $('#operatorId').attr("readonly",true);

        $("#btnChkId").hide(); //[중복확인]
        $("#btnIdReInput").show(); //[아이디재입력]
        
        g_idChk = true;
    }
}

<%-- 비밀번호 초기화 --%>
function fn_setTempPassword(){
	//operatorProfileRegNo
    if(util_chkReturn($.trim($('#operatorProfileRegNo').val()), "s") == "") {
    	alert("기업회원 저장 후 비밀번호 초기화를 다시 해 주세요.");
        return;
    }
	
	//기업 회원 이름(한글)
    if(util_chkReturn($.trim($('#operatorNameKor').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='기업 회원 이름(한글)'/>");
        $('#operatorNameKor').val("");
        $('#operatorNameKor').focus();
        return false;
    }
	
	//이메일
    var email = $.trim($('#email_01').val()) + "@" + $.trim($('#email_02').val());
    if(util_chkReturn(email, "s") == "") {
        alert("<spring:message code='errors.required' arguments='회원 e-Mail'/>");
        $('#email_01').focus();
        return false;
    }else{
        if(!util_isEmail(email)){
            alert("<spring:message code='errors.email' arguments='회원 e-Mail'/>");
            $('#email_01').focus();
            return false;
        }
    }
    $("#operatorEmail").val(email);
        
    //로딩 호출
    gfn_setLoading(true);
        
    //page setting  
    var url = "<c:url value='/apt/cptUsr/saveCptUserManagePwd.ajax'/>";
    var param = $("#CptUserManageVO").serialize();
    var callBackFunc = "fn_setTempPasswordCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(url, param, callBackFunc);
}
function fn_setTempPasswordCallBack(data){
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

<%-- 저장 전 체크 --%>
function fn_saveCheck(){
	if(!fn_validate()){
        return;
    }
	
	//로딩 호출
    gfn_setLoading(true, "기업 회원 e-Mail 중복체크 중 입니다.");
            
    var moveUrl = "<c:url value='/apt/cptUsr/checkCptUserManageOperatorEmail.ajax'/>";
    var param = $("#CptUserManageVO").serialize();
    var callBackFunc = "fn_saveCheckCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(moveUrl, param, callBackFunc);
}
function fn_saveCheckCallBack(data){
    //로그인 처리
    if(data.error == -1){
        fn_login();
        return;
    }
    
    //로딩 호출
    gfn_setLoading(false);
        
    if(data.result > 0){
        alert("기업 회원 e-Mail이 중복됩니다.");
        $("#email_01").focus();
        return;
    }else{
    	fn_save();
    }
    return;
}

<%-- 저장 --%>
function fn_save(){
	
    //로딩 호출
    gfn_setLoading(true, "저장 중 입니다.");
            
    var moveUrl = "<c:url value='/apt/cptUsr/saveCptUserManage.ajax'/>";
    var param = $("#CptUserManageVO").serialize();
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
    
    //insert
    if(util_chkReturn($.trim($('#operatorProfileRegNo').val()), "s") == ""){
        var operatorProfileRegNo = data.operatorProfileRegNo;
        
        if(data.result <= 0){
            alert("<spring:message code='fail.alert.regist' />");
            return;
        }else{
            alert("운영자 추가 및 등록 안내 메일을 발송하였습니다.");
            
            $("#operatorProfileRegNo").val(operatorProfileRegNo);
            $("#pageTitle").html("기업 회원 정보 수정");
            
            //operatorId를 수정버전과 동일하게 셋팅
            var operatorIdHtml = $("#operatorId").val();
            operatorIdHtml += "<input type='hidden' id='operatorId' name='operatorId' value='"+$("#operatorId").val()+"' />";
            $("#operatorIdText").html(operatorIdHtml);
            
            //비밀번호 초기화 노출
            $("#btnTempPasswordSpan").show();
            
            $("#btnIdReInput").hide(); //[아이디재입력]
            $("#btnChkId").hide(); //[중복확인]
            
            //회원 상태 노출
            $("#companyUserDataRow").attr("rowspan", "6");
            $("#companyUserDataRow").prop("rowspan", "6");
            $("#operatorRegStatusTr").show();
        }
    }else{
        if(data.result <= 0){
            alert("<spring:message code='fail.alert.modify' />");
            return;
        }else{
            alert("<spring:message code='success.alert.modify' />");
        }
    }
}

<%-- 저장 처리 체크 --%>
function fn_validate(){
    //기업 회원 이름(한글)
    if(util_chkReturn($.trim($('#operatorNameKor').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='기업 회원 이름(한글)'/>");
        $('#operatorNameKor').val("");
        $('#operatorNameKor').focus();
        return false;
    }
    
    //기업 회원 ID
    if(util_chkReturn($.trim($('#operatorId').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='기업 회원 ID'/>");
        $('#operatorId').val("");
        $('#operatorId').focus();
        return false;
    }else{
    	if(!gfn_validationCheckId("operatorId")){
            return false;
        }
    	
    	//중복체크 확인 -> 등록일때
    	if(util_chkReturn($.trim($('#operatorProfileRegNo').val()), "s") == "") {
    		if(!g_idChk){
    			alert("<spring:message code='errors.idcheck'/>");
                $('#operatorId').focus();
                return false;
    		}
    	}
    }
    
    //이메일
    var email = $.trim($('#email_01').val()) + "@" + $.trim($('#email_02').val());
    if(util_chkReturn(email, "s") == "") {
        alert("<spring:message code='errors.required' arguments='회원 e-Mail'/>");
        $('#email_01').focus();
        return false;
    }else{
        if(!util_isEmail(email)){
            alert("<spring:message code='errors.email' arguments='회원 e-Mail'/>");
            $('#email_01').focus();
            return false;
        }
    }
    $("#operatorEmail").val(email);
    
    
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
    $("#operatorPhoneNo").val(phone);
    
    //기업 회원 유선전화
    if(util_chkReturn($.trim($("#operatorTelNo").val()), "s") != ""){
        if(!util_isNum($.trim($("#operatorTelNo").val()))){
            alert("<spring:message code='errors.required' arguments='기업 회원 유선전화'/>");
            $("#operatorTelNo").focus();
            return false;
        }
    }
    
    return true;
}

</script>

</head>

<body>
<form:form commandName="CptUserManageVO" name="CptUserManageVO" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${CptUserManageVO.pageIndex}'/>" /><!-- //현재페이지번호 -->

<input type="hidden" name="companyProfileRegNo" id="companyProfileRegNo" value="<c:out value='${resultDetailCompany.companyProfileRegNo}'/>" />
<input type="hidden" name="operatorProfileRegNo" id="operatorProfileRegNo" value="<c:out value='${resultDetail.operatorProfileRegNo}'/>" />


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
                    <h2 id="pageTitle">
                        <c:choose>
	                        <c:when test="${empty resultDetail.operatorProfileRegNo}">기업 회원 정보 등록</c:when>
	                        <c:otherwise>기업 회원 정보 수정</c:otherwise>
	                    </c:choose> 
                    </h2>
                </div>
                <!-- // locatioin -->
                             
                <p class="info_right"><span class="icon_basic">*</span> 필수 입력사항</p>                                                
                <div class="tb_write1">
                    <table>
                        <caption>회원정보</caption>
                        <colgroup>
                            <col style="width:10%;">
                            <col style="width:20%;">
                            <col style="width:30%;">
                            <col style="width:20%;">
                            <col style="width:*">
                        </colgroup>
                        <tbody>
                        
                        <c:set var="operatorRowspan" value="6" />
                        <c:if test="${empty resultDetail.operatorProfileRegNo}">
                            <c:set var="operatorRowspan" value="5" />
                        </c:if>
                        <%-- 기업 회원 정보 --%>
                        <tr>
                            <th id="companyUserDataRow" scope="row" rowspan="${operatorRowspan }">기업 회원 정보</th>
                            <th scope="row">기업 회원 이름(한글)<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l">
                                <input type="text" class="w200" id="operatorNameKor" name="operatorNameKor" value="<c:out value='${resultDetail.operatorNameKor}'/>" />
                            </td>                  
                            <th scope="row">기업 회원 이름(영어)</th>
                            <td class="txt_l">
                                <input type="text" class="w200" id="operatorNameEng" name="operatorNameEng" value="<c:out value='${resultDetail.operatorNameEng}'/>" />
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">
                                                                기업 회원 ID
                                <c:if test="${empty resultDetail.operatorProfileRegNo}">
                                <span class="icon_basic">*필수입력</span>
                                </c:if>
                            </th>
                            <td class="txt_l" colspan="3" id="operatorIdText">
                                <c:choose>
						            <c:when test="${empty resultDetail.operatorProfileRegNo}" >
						                <input type="text" class="w100" id="operatorId" name="operatorId" value="<c:out value='${resultDetail.operatorId}'/>" />
						                &nbsp;
						                <span class="btn_gray1"><a href="javascript:void(0);" id="btnChkId">중복확인</a></span>
						                <span class="btn_gray1"><a href="javascript:void(0);" id="btnIdReInput" style="display:none;">아이디재입력</a></span>
		                                <span class="info_msg">영문, 숫자 조합, 소문자 입력4~15자 이내</span> 
						            </c:when>
						            <c:otherwise>
						                <c:out value='${resultDetail.operatorId}'/>
						                <input type="hidden" id="operatorId" name="operatorId" value="<c:out value='${resultDetail.operatorId}'/>" />
						            </c:otherwise>
						        </c:choose> 
                            </td>                  
                        </tr>
                        <tr>
                            <th scope="row">기업 회원 e-Mail<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l" colspan="3">
                                <input type="text" class="w100" id="email_01" /> @
                                <input type="text" class="w150" id="email_02">
                                <select id="emailSelect" title="이메일선택">
                                    <option value="">직접입력</option>
                                    <c:forEach var="emailList" items="${emailList}" varStatus="status">
                                        <option value="${emailList.code_name_kor}">${emailList.code_name_kor}</option>
                                    </c:forEach>
                                </select>
                                <input type="hidden" id="operatorEmail" name="operatorEmail" value="<c:out value='${resultDetail.operatorEmail}'/>" />
                            </td>                  
                        </tr>
                        <tr>
                            <th scope="row">기업 회원 휴대폰<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l" colspan="3">
                                <select id="phoneNo_01" style="min-width:80px;">
                                    <option value="">선택</option>
                                    <c:forEach var="hpList" items="${hpList}" varStatus="status">
                                        <option value="${hpList.code_name_kor}">${hpList.code_name_kor}</option>
                                    </c:forEach>
                                </select>-
                                <input type="tel" id="phoneNo_02" class="w50" maxlength="4" onkeydown="util_numberonly(event);">- 
                                <input type="tel" id="phoneNo_03" class="w50" maxlength="4" onkeydown="util_numberonly(event);">
                                <input type="hidden" id="operatorPhoneNo" name="operatorPhoneNo" value="<c:out value='${resultDetail.operatorPhoneNo}'/>" />
                            </td>                    
                        </tr>
                        <tr>
                            <th scope="row">기업 회원 유선전화</th>
                            <td class="txt_l" colspan="3">
                                <input type="text" id="operatorTelNo" name="operatorTelNo" class="w100" value="<c:out value='${resultDetail.operatorTelNo}'/>" maxlength="13" />
                            </td>                    
                        </tr>
                        <tr id="operatorRegStatusTr" <c:if test="${empty resultDetail.operatorProfileRegNo}"> style="display: none;" </c:if>>
                            <th scope="row">회원 상태<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l" colspan="3">
                                <select id="operatorRegStatus" name="operatorRegStatus" style="min-width:100px;">
                                    <c:forEach var="operatorRegStatusList" items="${operatorRegStatusList}" varStatus="status">
                                        <option value="${operatorRegStatusList.system_code}" 
                                            <c:if test="${resultDetail.operatorRegStatus eq operatorRegStatusList.system_code}"> selected="selected" </c:if>
                                            <c:if test="${empty resultDetail.operatorProfileRegNo && operatorRegStatusList.system_code eq 'G005002'}"> selected="selected" </c:if>
                                        >${operatorRegStatusList.code_name_kor}</option>
                                    </c:forEach>
                                </select>
                            </td>                   
                        </tr>
                        
                        <%-- 소속 기업 정보 --%>
                        <tr>
                            <th scope="row" rowspan="8">소속 기업 정보</th>
                            <th scope="row">기업 역할</th>
                            <td class="txt_l" colspan="3"><c:out value='${resultDetailCompany.companyServiceTypeName}'/></td>                  
                        </tr>
                        <tr>
                            <th scope="row">기업 이름(한글)</th>
                            <td class="txt_l"><c:out value='${resultDetailCompany.companyNameKor}'/></td>
                            <th scope="row">기업 이름(한글 약어)</th>                  
                            <td class="txt_l"><c:out value='${resultDetailCompany.companyNameKorAlias}'/></td>              
                        </tr>
                        <tr>
                            <th scope="row">기업 이름(영어)</th>
                            <td class="txt_l"><c:out value='${resultDetailCompany.companyNameEng}'/></td>
                            <th scope="row">기업 이름(영어 약어)</th>                  
                            <td class="txt_l"><c:out value='${resultDetailCompany.companyNameEngAlias}'/></td>
                        </tr>
                        <tr>
                            <th scope="row">사업자 등록 번호</th>
                            <td class="txt_l" colspan="3"><c:out value='${resultDetailCompany.companyBizregNo}'/></td>                  
                        </tr>
                        <tr>
                            <th scope="row">대표자 명</th>
                            <td class="txt_l" colspan="3"><c:out value='${resultDetailCompany.ceoName}'/></td>                  
                        </tr>
                        <tr>
                            <th scope="row">기업 주소</th>
                            <td class="txt_l" colspan="3">
                                <c:out value='${resultDetailCompany.companyPostNo}'/>
                                <c:if test="${ not empty resultDetailCompany.companyAddress }">
                                <br><c:out value='${resultDetailCompany.companyAddress}'/>&nbsp;<c:out value='${resultDetailCompany.companyAddressDtl}'/>
                                </c:if>
                            </td>                  
                        </tr>
                        <tr>
                            <th scope="row">기업 분류</th>
                            <td class="txt_l" colspan="3"><c:out value='${resultDetailCompany.companyDivCodeName}'/></td>                  
                        </tr>
                        <tr>
                            <th scope="row">기업코드</th>
                            <td class="txt_l" colspan="3"><c:out value='${resultDetailCompany.companyCodeId}'/></td>                  
                        </tr>
                        
                        <%-- 등록 정보 --%>
                        <%--
                        <tr>
                            <th scope="row" rowspan="5">등록 정보</th>
                            <th scope="row">e-mail 확인 일시</th>
                            <td class="txt_l" colspan="3"><c:out value='${resultDetailCompany.operatorEmailAuthDate}'/></td>                  
                        </tr>
                        --%>
                        <tr>
                            <th scope="row" rowspan="4">등록 정보</th>
                            <th scope="row">등록 일시</th>
                            <td class="txt_l" colspan="3"><c:out value='${resultDetailCompany.createDate}'/></td>                  
                        </tr>
                        <tr>
                            <th scope="row">수정 일시</th>
                            <td class="txt_l" colspan="3"><c:out value='${resultDetailCompany.updateDate}'/></td>                  
                        </tr>
                        <tr>
                            <th scope="row">정지 일시</th>
                            <td class="txt_l" colspan="3"><c:out value='${resultDetailCompany.deleteDate}'/></td>                  
                        </tr>
                        <tr>
                            <th scope="row">등록 Admin</th>
                            <td class="txt_l" colspan="3"><c:out value='${resultDetailCompany.createIdName}'/></td>                  
                        </tr>
                        
                        </tbody>
                    </table>
                </div>

                <div class="btn_type3">
                    <div class="left">
                        <span class="btn_gray1"><a href="javascript:void(0);" id="btnList">목록</a></span>
                    </div>
                    <div class="right">
                        <span class="btn_gray2" id="btnTempPasswordSpan" style="display: none;"><a href="javascript:void(0);" id="btnTempPassword">비밀번호 초기화</a></span>
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