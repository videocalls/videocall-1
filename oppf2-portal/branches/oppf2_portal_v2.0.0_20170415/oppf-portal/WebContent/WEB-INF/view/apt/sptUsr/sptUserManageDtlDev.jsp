<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf"%>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : sptUserManageDtl.jsp
 * @Description : 일반회원관리 상세
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.06.20  신동진        최초  생성
 * </pre>
 *
 * @author 신동진
 * @since 2016.06.20
 * @version 1.0
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf"%>

<script language="javascript" type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/
 
/*******************************************
 * 이벤트 함수
 *******************************************/

<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
    var url = "<c:url value='/apt/sptUsr/sptUserManageList.do'/>";
    var param = new Object();
    param.paramMenuId = "01001";
    
    gfn_loginNeedMove(url, param);
} 
 
//화면 로드 처리
$(document).ready(function(){
	<%-- 로그인 처리 --%>
    <c:if test="${empty LoginVO}">
        fn_login();
        return;
    </c:if>
    
    fn_setSelectBoxBirthDay(); //생년월일 설정
    
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
    	fn_save();
    });
    
    //이메일 주소 타입 변경 시
    $('#customerEmailSelect').change(function(){
        if($(this).val()==""){
            $('#customerEmail_02').val("");
            $('#customerEmail_02').attr("disabled",false);
        }else{
            $('#customerEmail_02').val($(this).val());
            $('#customerEmail_02').attr("disabled",true);
        }
    });

    // 본인 인증 상태 변경 시
    $('#ciCustomerVerifyYn').change(function(){
        if($(this).val()=="Y"){
            $('#ciCustomerVerify').attr("disabled",false);
        }else{
            $('#ciCustomerVerify').attr("disabled",true);
        }
    });
    // 공인 인증서 등록 상태 변경 시
    $('#dnCustomerVerifyStatusYn').change(function(){
        if($(this).val()=="Y"){
            $('#dnCustomerVerify').attr("disabled",false);
        }else{
            $('#dnCustomerVerify').attr("disabled",true);
        }
    });
    
    // 스마트 OPT 사용 상태 변영시
    $('#customerOtpYn').change(function(){
        if($(this).val()=="Y"){
            $('#customerOtpId').attr("disabled",false);
        }else{
            $('#customerOtpId').attr("disabled",true);
        }
    });
    
    
    //이메일
    var emailArr = "<c:out value='${resultDetail.customerEmail}'/>".split("@");
    if(emailArr != null){
        $("#customerEmail_01").val(emailArr[0]); 
        $("#customerEmail_02").val(emailArr[1]);
    }
    
    //휴대전화
    var phone = "<c:out value='${resultDetail.customerPhone}'/>".split("-");
    if(phone != null){
        $("#customerPhone_01").val(phone[0]);
        $("#customerPhone_02").val(phone[1]);
        $("#customerPhone_03").val(phone[2]);
    }
    

    //rowspan
    <c:choose>
        <c:when test="${empty resultDetailSvcTermsList}" >
        </c:when>
        <c:otherwise>
            //첫 컬럼 rowspan
            gfn_rowspanMerge("rowspan_subcompany");
            
            var key2 = "";
            var key3 = "";
            var key4 = "";
            
            var key2Data = "";
            var key3Data = "";
            var key4Data = "";
            
            //roop
            <c:forEach var="resultDetailSvcTermsList" items="${resultDetailSvcTermsList}" varStatus="status">
            key2Data = "${resultDetailSvcTermsList.serviceRegNo}${resultDetailSvcTermsList.termsRegNo}${resultDetailSvcTermsList.subcompanyCodeId}${resultDetailSvcTermsList.appId}";
            key3Data = "${resultDetailSvcTermsList.serviceRegNo}${resultDetailSvcTermsList.termsRegNo}${resultDetailSvcTermsList.subcompanyCodeId}${resultDetailSvcTermsList.appId}${resultDetailSvcTermsList.pubcompanyCodeId}";
            key4Data = "${resultDetailSvcTermsList.serviceRegNo}${resultDetailSvcTermsList.termsRegNo}";
            
            //rowspan
            if(key2 == ""){
                key2 = key2Data;
                gfn_rowspanMerge("rowspan_"+key2);
            }else{
                if(key2 != key2Data){
                    key2 = key2Data;
                    gfn_rowspanMerge("rowspan_"+key2);
                }
            }
            
            if(key3 == ""){
                key3 = key3Data;
                gfn_rowspanMerge("rowspan_"+key3);
            }else{
                if(key3 != key3Data){
                    key3 = key3Data;
                    gfn_rowspanMerge("rowspan_"+key3);
                }
            } 
            
            if(key4 == ""){
                key4 = key4Data;
                gfn_rowspanMerge("rowspan_"+key4);
            }else{
                if(key4 != key4Data){
                    key4 = key4Data;
                    gfn_rowspanMerge("rowspan_"+key4);
                }
            } 
			
            
            </c:forEach>
        </c:otherwise>
    </c:choose>

    
    // 셀렉트 박스 값 세팅
    fn_sbx_mapping();
});

/*******************************************
 * 기능 함수
 *******************************************/
<%-- 목록 --%>
function fn_list(){
    util_moveRequest("SptUserManageVO", "<c:url value='/apt/sptUsr/sptUserManageList.do'/>", "_self");
}


function fn_sbx_mapping(){
    
    //회원 가입 상태
    $("#customerRegStatus").val("<c:out value='${resultDetail.customerRegStatus}'/>");
	
    //생년월일
    var birthDayArr = "<c:out value='${resultDetail.customerBirthDay}'/>".split("-");
    if(birthDayArr != null){
        $("#customerBirthDayYYYY").val(birthDayArr[0]); 
        $("#customerBirthDayMM").val(birthDayArr[1]); 
        $("#customerBirthDayDD").val(birthDayArr[2]);
    }
    $("#customerBirthDay").val(customerBirthDayYYYY+customerBirthDayMM+customerBirthDayDD);
	
    // 이메일 인증 상태
    $("#customerEmailAuthStatus").val("<c:out value='${resultDetail.customerEmailAuth}'/>");
    
    // 이메일 수신동의 여부
    $("#customerEmailReceiveStatus").val("<c:out value='${resultDetail.customerEmailReceive}'/>");
    
    //본인 인증 상태
    $("#ciCustomerVerifyYn").val("<c:out value='${resultDetail.ciCustomerVerifyYnSbx}'/>");

    if($("#ciCustomerVerifyYn").val()=="Y"){
        $('#ciCustomerVerify').attr("disabled",false);
    }else{
        $('#ciCustomerVerify').attr("disabled",true);
    }

    // 공인인증서 등록 상태
    $("#dnCustomerVerifyStatusYn").val("<c:out value='${resultDetail.dnCustomerVerifySbx}'/>");
    
    if($("#dnCustomerVerifyStatusYn").val()=="Y"){
        $('#dnCustomerVerify').attr("disabled",false);
    }else{
        $('#dnCustomerVerify').attr("disabled",true);
    }


    // 스마트 OTP 사용
    $("#customerOtpYn").val("<c:out value='${resultDetail.customerOtpYn}'/>");
    
    if($("#customerOtpYn").val()=="Y"){
        $('#customerOtpId').attr("disabled",false);
    }else{
        $('#customerOtpId').attr("disabled",true);
    }

	

}

<%-- 저장 --%>
function fn_save(){
	if(!fn_validate()){
        return;
    }
	    
    //로딩 호출
    gfn_setLoading(true);
    
  //page setting  
    var url = "<c:url value='/apt/usr/updateSptUserManageDtlDev.ajax'/>";
    var param = $("#NewMbrRegVO").serialize();
    var callBackFunc = "fn_saveCallBack";
    <%-- 공통 ajax 호출 --%>
    util_ajaxPage(url, param, callBackFunc);
}
function fn_saveCallBack(data){
    //로그인 처리
    if(data.error == -1){
        fn_login();
        return;
    }
    
    //로딩 호출
    gfn_setLoading(false);
    
    //사용자 customer_reg_no가 없는경우
    if(data.user == -1){
        alert("회원의 customer_reg_no가 없습니다.");
        return;
    }
    
    if(data.result > 0){
        alert("정상적으로 수정되었습니다.");
    	fn_list();
    }else{
        alert("저장에 실패하였습니다.");
    	fn_list();
    }
    return;
}




<%-- 저장 처리 체크 --%>
function fn_validate(){
	
    var customerId      = $("#customerId").val().replace(/\s/g, ""); //아이디
    var customerPassword1 = $("#customerPassword1").val().replace(/\s/g, ""); //비밀번호
    var customerPassword2 = $("#customerPassword2").val().replace(/\s/g, ""); //비밀번호확인
    var customerBirthDayYYYY = $("#customerBirthDayYYYY option:selected").val(); //년
    var customerBirthDayMM   = $("#customerBirthDayMM option:selected").val();   //월
    var customerBirthDayDD   = $("#customerBirthDayDD option:selected").val();   //일

	
    //[비밀번호확인]
    if(util_chkReturn(customerPassword2, "s") == "" && util_chkReturn(customerPassword2, "s") == ""){

        $("#customerPassword").val();
    } else {
    
	    //[비밀번호]!=[비밀번호확인]
	    if(customerPassword1 != customerPassword2){
	        alert("<spring:message code='errors.notsame' arguments='비밀번호,비밀번호확인'/>");
	        return false;
	    }
	    
	    //[비밀번호]가 [아이디]와 동일한지 체크
	    if(customerId == customerPassword1) {
	        alert("아이디 와 비밀번호가 동일합니다.\r\n비밀번호를 다시 입력하십시오.");
	        $("#customerPassword1").focus();
	        return false;
	    }
	    
	    //[비밀번호]에 [아이디]가 포함되어 있는지 체크
	    if(customerId.substring(0,4) == customerPassword1.substring(0,4)){
	        alert("아이디 와 비밀번호 앞4자리가 동일 합니다.\r\n비밀번호를 다시 입력하십시오.");
	        $("#customerPassword1").focus();
	        return false;
	    }
	
	    if(!gfn_validationCheckPw("customerPassword1")){
	    	return false;
	    }

	    $("#customerPassword").val(customerPassword1);
    }
    
	//회원가입상태
	if(util_chkReturn($.trim($('#customerRegStatus').val()), "s") == "") {
		alert("<spring:message code='errors.required' arguments='회원 가입 상태'/>");
        $('#customerRegStatus').val("");
        $('#customerRegStatus').focus();
        return false;
	}


    $("#customerBirthDay").val(customerBirthDayYYYY+customerBirthDayMM+customerBirthDayDD);
	
    
    //이메일
    var email = $.trim($('#customerEmail_01').val()) + "@" + $.trim($('#customerEmail_02').val());
    if(util_chkReturn(email, "s") == "") {
        alert("<spring:message code='errors.required' arguments='e-Mail'/>");
        $('#customerEmail_01').focus();
        return false;
    }else{
        if(!util_isEmail(email)){
            alert("<spring:message code='errors.email' arguments='e-Mail'/>");
            $('#customerEmail_01').focus();
            return false;
        }
    }
    

    //[이메일앞자리]
    if(util_chkReturn($('#customerEmail_01').val(), "s") == ""){
        alert("<spring:message code='errors.required' arguments='이메일앞자리'/>");
        $("#customerEmail_01").focus();
        return false;
    }
    
    //[이메일앞자리]정규식
    if( !util_isEmail($('#customerEmail_01').val(),'1') ){
        alert("<spring:message code='errors.invalid' arguments='이메일앞자리'/>");
        $("#customerEmail_01").focus();
        return false;
    }
    
    //[이메일뒷자리]
    if(util_chkReturn($('#customerEmail_02').val(), "s") == ""){
        alert("<spring:message code='errors.required' arguments='이메일뒷자리'/>");
        $("#customerEmail_02").focus();
        return false;
    }
    
    //[이메일뒷자리]정규식
    if( !util_isEmail($('#customerEmail_02').val(),'2') ){
        alert("<spring:message code='errors.invalid' arguments='이메일뒷자리'/>");
        $("#customerEmail_02").focus();
        return false;
    }
    
    
    $("#customerEmail").val(email);
    
    
    //휴대폰번호
    //[휴대폰번호2]
    if(util_chkReturn($.trim($("#customerPhone_02").val()), "s") == ""){
        alert("<spring:message code='errors.required' arguments='휴대폰 두번째자리'/>");
        $("#customerPhone_02").focus();
        return false;
    }else{
        if(!util_isNum($.trim($("#customerPhone_02").val()))){
            alert("<spring:message code='errors.required' arguments='휴대폰 두번째자리'/>");
            $("#customerPhone_02").focus();
            return false;
        }
    }
    
    //[휴대폰번호3]
    if(util_chkReturn($.trim($("#customerPhone_03").val()), "s") == ""){
        alert("<spring:message code='errors.required' arguments='휴대폰 세번째자리'/>");
        $("#customerPhone_03").focus();
        return false;
    }else{
        if(!util_isNum($.trim($("#customerPhone_03").val()))){
            alert("<spring:message code='errors.required' arguments='휴대폰 세번째자리'/>");
            $("#customerPhone_03").focus();
            return false;
        }
    }
    
    var phone = $("#customerPhone_01").val() + "-" + $.trim($("#customerPhone_02").val()) + "-" + $.trim($("#customerPhone_03").val());
    if(!util_isTelno(phone, "-")){
        alert("<spring:message code='errors.phone' arguments='휴대폰'/>");
        $('#customerPhone_01').focus();
        return false;
    }
    
    $("#customerPhone").val(phone);
    // 이메일 인증상태 값세팅
    $("#customerEmailAuthYn").val($("#customerEmailAuthStatus").val());
    //이메일 수신동의 여부 갑세팅
    $("#customerEmailReceiveYn").val($("#customerEmailReceiveStatus").val());

    $("#ciCustomerVerifyStatus").val($("#ciCustomerVerifyYn").val());

    if($("#ciCustomerVerifyYn").val() == "Y"){

        if(util_chkReturn($.trim($('#ciCustomerVerify').val()), "s") == "") {
            alert("<spring:message code='errors.required' arguments='본인인증 값'/>");
            $('#ciCustomerVerify').val("");
            $('#ciCustomerVerify').focus();
            return false;
        }

    } else {
    	$("#ciCustomerVerify").val("");
    }
    
    $("#dnCustomerVerifyStatus").val($("#dnCustomerVerifyStatusYn").val());

    if($("#dnCustomerVerifyStatusYn").val() == "Y"){

        if(util_chkReturn($.trim($('#dnCustomerVerify').val()), "s") == "") {
            alert("<spring:message code='errors.required' arguments='공인인증서 값'/>");
            $('#dnCustomerVerify').val("");
            $('#dnCustomerVerify').focus();
            return false;
        }

    } else {
    	$("#dnCustomerVerify").val("");
    }

    var serviceTerms = $("#serviceTermsYn").val(); // 본인 인증 상태
    var indTerms = $("#indTermsYn").val(); // 공인 인증서 등록 상태
    var thirdProvideTerms = $("#thirdProvideTermsYn").val(); // 본인 인증 상태
    var certiTerms = $("#certiTermsYn").val(); // 공인 인증서 등록 상태
    

    $("#serviceTerms").val(serviceTerms);				// 서비스 이용약관
    $("#indTerms").val(indTerms);						// 개인정보수집 및 이용동의
    $("#thirdProvideTerms").val(thirdProvideTerms);		// 개인정보 제3자 제공 동의
    $("#certiTerms").val(certiTerms);					// 공인인증서 등록 약관동의
    

    $("#customerDatasetLockYn").val($("#customerDatasetLock").val());
    //$("#integrationAccountYn").val($("#integrationAccount").val());

    return true;
}


/* [생년월일]설정 함수 */
function fn_setSelectBoxBirthDay(){
    var date = new Date();
    var currentYYYY = date.getFullYear();
    var currentMM = date.getMonth()+1;
    var currentDD = date.getDate();
    if(currentMM < 10){
        currentMM = '0'+currentMM;
    }
    if(currentDD < 10){
        currentDD = '0'+currentDD;
    }
    
    //[년] select option 설정
    var objParam = new Object();
    objParam.term = 100;
    objParam.term2 = 10;
    var yyyy_arr = gfn_getDataListYear(objParam);
    $.each(yyyy_arr, function(idx,data){
        $("#customerBirthDayYYYY").append("<option value='"+data.cmnnCd+"'>"+data.cmnnCdHanNm+"</option>");
    });
    
    //[월] select option 설정
    var mm_arr = gfn_getDataListMonth();
    $.each(mm_arr, function(idx,data){
        $("#customerBirthDayMM").append("<option value='"+data.cmnnCd+"'>"+data.cmnnCdHanNm+"</option>");
    });
    
    //[일] select option 설정
    var dd_arr = gfn_getDataListDay();
    $.each(dd_arr, function(idx,data){
        $("#customerBirthDayDD").append("<option value='"+data.cmnnCd+"'>"+data.cmnnCdHanNm+"</option>");
    });
    
}

<%-- 약관 팝업 --%>
function fn_openTerms(termsRegNo, termsAuth, termsAuthYn){
	var url = "<c:url value='/usr/svcAppl/svcTermConsntPopup.do'/>";
    var objOption = new Object();
    objOption.type = 'modal';
    objOption.width = '900'; 
    objOption.height = '700';
        
    var objParam = new Object();
    objParam.termsRegNo = termsRegNo;
    objParam.termsAuthYn = termsAuthYn;
    objParam.customerRegNo = $("#customerRegNo").val();
    objParam.callBakFunc = "fn_openTermsCallBack";
    
    util_modalPage(url, objOption, objParam);
}
function fn_openTermsCallBack(data){
}


/* [생년월일]설정 함수 */
function fn_setSelectBoxBirthDay(){
    var date = new Date();
    var currentYYYY = date.getFullYear();
    var currentMM = date.getMonth()+1;
    var currentDD = date.getDate();
    if(currentMM < 10){
        currentMM = '0'+currentMM;
    }
    if(currentDD < 10){
        currentDD = '0'+currentDD;
    }
    
    //[년] select option 설정
    var objParam = new Object();
    objParam.term = 100;
    objParam.term2 = 10;
    var yyyy_arr = gfn_getDataListYear(objParam);
    $.each(yyyy_arr, function(idx,data){
        $("#customerBirthDayYYYY").append("<option value='"+data.cmnnCd+"'>"+data.cmnnCdHanNm+"</option>");
    });
    
    //[월] select option 설정
    var mm_arr = gfn_getDataListMonth();
    $.each(mm_arr, function(idx,data){
        $("#customerBirthDayMM").append("<option value='"+data.cmnnCd+"'>"+data.cmnnCdHanNm+"</option>");
    });
    
    //[일] select option 설정
    var dd_arr = gfn_getDataListDay();
    $.each(dd_arr, function(idx,data){
        $("#customerBirthDayDD").append("<option value='"+data.cmnnCd+"'>"+data.cmnnCdHanNm+"</option>");
    });
    
}
</script>

</head>

<body>


	<form:form commandName="SptUserManageVO" name="SptUserManageVO"
		method="post">
		
		<!-- //리스트 검색조건 -->
		<input type="hidden" name="searchCondition" id="searchCondition" value="<c:out value='${SptUserManageVO.searchCondition}'/>" />
		<input type="hidden" name="searchCustomerRegStatus" id="searchCustomerRegStatus" value="<c:out value='${SptUserManageVO.searchCustomerRegStatus}'/>" />
		<input type="hidden" name="searchDateFrom" id="searchDateFrom" value="<c:out value='${SptUserManageVO.searchDateFrom}'/>" />
		<input type="hidden" name="searchDateTo" id="searchDateTo" value="<c:out value='${SptUserManageVO.searchDateTo}'/>" />
		<input type="hidden" name="searchDateType" id="searchDateType" value="<c:out value='${SptUserManageVO.searchDateType}'/>" />
		<input type="hidden" name="searchKeyword" id="searchKeyword" value="<c:out value='${SptUserManageVO.searchKeyword}'/>" />
		
		</form:form>
	<form:form commandName="NewMbrRegVO" name="NewMbrRegVO"
		method="post">
		<input type="hidden" name="pageIndex" id="pageIndex" value="<c:out value='${SptUserManageVO.pageIndex}'/>" />
		
		<!-- //현재페이지번호 -->

		<input type="hidden" name="customerRegNo" id="customerRegNo"
			value="<c:out value='${resultDetail.customerRegNo}'/>" />


		<%-- 탑과 대메뉴 영역 --%>
		<%@ include file="/WEB-INF/view/cmm/common-include-top.jspf"%>
		<%-- 탑과 대메뉴 영역 --%>

		<!-- // wrap_top -->
		<article class="container">
			<div>
				<%-- lnb(좌측메뉴) 영역 --%>
				<%@ include file="/WEB-INF/view/cmm/common-include-left.jspf"%>
				<%-- lnb(좌측메뉴) 영역 --%>

				<!-- content -->
				<section class="content">
					<div class="location">
						<h2>일반 회원 정보</h2>
					</div>
					<!-- // locatioin -->

					<h3>기본 정보</h3>
					<div class="tb_write1">
						<table>
							<caption>회원정보</caption>
							<colgroup>
								<col style="width: 10%;">
								<col style="width: 20%;">
								<col style="width: 30%;">
								<col style="width: 20%;">
								<col style="width: *">
							</colgroup>
							<tbody>
								<tr>
									<th scope="row" colspan="2">회원 이름(한글)</th>
									<td class="txt_l" colspan="3"><c:out
											value='${resultDetail.customerNameKor}' /></td>
								</tr>
								<%--
                        <tr>
                            <th scope="row" colspan="2">회원 이름(영문)</th>
                            <td class="txt_l" colspan="3"><c:out value='${resultDetail.customerNameEng}'/></td>                  
                        </tr>
                        --%>
								<tr>
									<th scope="row" colspan="2">회원 ID</th>
									<td class="txt_l" colspan="3"><c:out
											value='${resultDetail.customerId}' /> <input type="hidden"
										id="customerId" name="customerId"
										value="<c:out value='${resultDetail.customerId}'/>" /></td>
								</tr>
								<tr>
									<th scope="row" colspan="2"><label for="user_pw">비밀번호</label>
									</th>
									<td class="txt_l" colspan="3"><input type="password"
										name="customerPassword1" id="customerPassword1"
										style="width: 238px;" maxlength="15"
										placeHolder="ex)Qetu101!@" /> <span class="info_msg">8~15자의
											영문 대/소문자, 숫자, 특수문자 중 3개 이상의 조합</span>
									 <input type="hidden" id="customerPassword" name="customerPassword"	value="" />
									</td>
								</tr>
								<tr>
									<th scope="row" colspan="2"><label for="user_pw2">비밀번호
											확인</label></th>
									<td class="txt_l" colspan="3"><input type="password"
										name="customerPassword2" id="customerPassword2"
										style="width: 238px;" maxlength="15" /></td>
								</tr>
								<tr>
									<th scope="row" colspan="2">회원 가입 상태</th>
									<td class="txt_l" colspan="3"><select
										id="customerRegStatus" name="customerRegStatus"
										style="min-width: 80px;">
											<c:forEach var="customerRegStatusList"
												items="${customerRegStatusList}" varStatus="status">
												<option value="${customerRegStatusList.system_code}" <c:if test="${customerRegStatusList.system_code eq resultDetail.customerRegStatus}"> selected </c:if>>${customerRegStatusList.code_name_kor}</option>
											</c:forEach>
									</select></td>
								</tr>
								<tr>
									<th scope="row" colspan="2">회원 가입 단계</th>
									<td class="txt_l" colspan="3"><c:out
											value='${resultDetail.customerStepName}' /></td>
								</tr>
								<tr>
									<th scope="row" colspan="2">생년월일</th>
									<td class="txt_l" colspan="3"><span class="sel_box1">
											<select name="customerBirthDayYYYY" id="customerBirthDayYYYY"
											title="년 선택" style="min-width: 95px;">
												<option value="">선택</option>
										</select>
									</span>년 <span class="sel_box1"> <select
											name="customerBirthDayMM" id="customerBirthDayMM"
											title="월 선택">
												<option value="">선택</option>
										</select>
									</span>월 <span class="sel_box1"> <select
											name="customerBirthDayDD" id="customerBirthDayDD"
											title="일 선택">
												<option value="">선택</option>
										</select>
									</span>일 <input type="hidden" name="customerBirthDay"
										id="customerBirthDay" /></td>

								</tr>
								<tr>
									<th scope="row" colspan="2">e-Mail</th>
									<td class="txt_l" colspan="3"><input type="text"
										class="w100" id="customerEmail_01" /> @ <input type="text"
										class="w150" id="customerEmail_02"> <select
										id="customerEmailSelect" title="이메일선택">
											<option value="">직접입력</option>
											<c:forEach var="emailList" items="${emailList}"
												varStatus="status">
												<option value="${emailList.code_name_kor}">${emailList.code_name_kor}</option>
											</c:forEach>
									</select> <input type="hidden" id="customerEmail" name="customerEmail"
										value="<c:out value='${resultDetail.customerEmail}'/>" /></td>
								</tr>
								<tr>
									<th scope="row" colspan="2">휴대폰</th>
									<td class="txt_l" colspan="3"><select
										id="customerPhone_01" style="min-width: 80px;">
											<option value="">선택</option>
											<c:forEach var="hpList" items="${hpList}" varStatus="status">
												<option value="${hpList.code_name_kor}">${hpList.code_name_kor}</option>
											</c:forEach>
									</select>- <input type="tel" id="customerPhone_02" class="w50"
										maxlength="4" onkeydown="util_numberonly(event);">- <input
										type="tel" id="customerPhone_03" class="w50" maxlength="4"
										onkeydown="util_numberonly(event);"> <input
										type="hidden" id="customerPhone" name="customerPhone"
										value="<c:out value='${resultDetail.customerPhone}'/>" /></td>
								</tr>
								<tr>
									<th scope="row" colspan="2">이메일 인증 상태</th>
									<td class="txt_l"><select id="customerEmailAuthStatus"
										style="min-width: 80px;">
											<option value="Y" <c:if test="${resultDetail.customerEmailAuth eq 'Y' }"> selected </c:if>>동의</option>
											<option value="N" <c:if test="${resultDetail.customerEmailAuth eq 'N' }"> selected </c:if>>미동의</option>
									</select><input
										type="hidden" name="customerEmailAuthYn"
										id="customerEmailAuthYn" value="" /></td>
									<th scope="row">인증 일시</th>
									<td class="txt_l"><c:out
											value='${resultDetail.customerEmailRegDate}' /></td>
								</tr>
								<tr>
									<th scope="row" colspan="2">이메일 수신동의 여부</th>
									<td class="txt_l"><select id="customerEmailReceiveStatus"
										style="min-width: 80px;">
											<option value="Y" <c:if test="${resultDetail.customerEmailReceive eq 'Y' }"> selected </c:if>>동의</option>
											<option value="N" <c:if test="${resultDetail.customerEmailReceive eq 'N' }"> selected </c:if>>미동의</option>
									</select><input
										type="hidden" name="customerEmailReceiveYn"
										id="customerEmailReceiveYn" value="" /></td>
									<th scope="row">등록 일시</th>
									<td class="txt_l"><c:out
											value='${resultDetail.customerEmailReceiveDate}' /></td>
								</tr>
								<tr>
									<th scope="row" colspan="2">본인 인증 상태</th>
									<td class="txt_l"><select id="ciCustomerVerifyYn"
										style="min-width: 80px;">
											<option value="Y" <c:if test="${resultDetail.ciCustomerVerifyYnSbx eq 'Y' }"> selected </c:if>>동의</option>
											<option value="N" <c:if test="${resultDetail.ciCustomerVerifyYnSbx eq 'N' }"> selected </c:if>>미동의</option>
									</select> <input type="text" name="ciCustomerVerify"
										id="ciCustomerVerify" style="width: 238px;" maxlength="200"
										value="${resultDetail.ciCustomerVerify}" /> <input
										type="hidden" name="ciCustomerVerifyStatus"
										id="ciCustomerVerifyStatus" value="" /></td>
									<th scope="row">인증 일시</th>
									<td class="txt_l"><c:out
											value='${resultDetail.ciCustomerVerifyDate}' /></td>
								</tr>
								<tr>
									<th scope="row" colspan="2">공인 인증서 등록 상태</th>
									<td class="txt_l"><select id="dnCustomerVerifyStatusYn"
										style="min-width: 80px;">
											<option value="Y" <c:if test="${resultDetail.dnCustomerVerifySbx eq 'Y' }"> selected </c:if>>동의</option>
											<option value="N" <c:if test="${resultDetail.dnCustomerVerifySbx eq 'N' }"> selected </c:if>>미동의</option>
									</select> <input type="text" name="dnCustomerVerify"
										id="dnCustomerVerify" style="width: 238px;" maxlength="200"
										value="${resultDetail.dnCustomerVerifyValue}" /> <input
										type="hidden" name="dnCustomerVerifyStatus"
										id="dnCustomerVerifyStatus" value="" /></td>
									<th scope="row">등록 일시</th>
									<td class="txt_l"><c:out
											value='${resultDetail.dnCustomerVerifyDate}' /></td>
								</tr>

								<%-- 이용약관 --%>
								<c:set value="${ fn:length(resultDetailTermsList) }"
									var="resultDetailTermsListLength" />
								<c:choose>
									<c:when test="${empty resultDetailTermsList}">
										<tr>
											<th scope="row" colspan="2">이용 약관</th>
											<td colspan="3" align="center">약관동의를 안하였습니다.</td>
										</tr>
									</c:when>
									<c:otherwise>
										<c:forEach var="resultDetailTermsList"
											items="${resultDetailTermsList}" varStatus="status">
											<tr>
												<c:if test="${status.index eq 0}">
													<th scope="row" rowspan="${ resultDetailTermsListLength }">이용
														약관</th>
												</c:if>
												<th scope="row"><c:out
														value='${resultDetailTermsList.customerTermsTypeName}' /></th>
												<td class="txt_l"><c:choose>
														<c:when test="${status.index eq 0}">
															<input type="hidden" name="serviceTerms"
																id="serviceTerms" value="" />
															<select id="serviceTermsYn" style="min-width: 80px;">
														</c:when>
														<c:when test="${status.index eq 1}">
															<input type="hidden" name="indTerms" id="indTerms"
																value="" />
															<select id="indTermsYn" style="min-width: 80px;">
														</c:when>
														<c:when test="${status.index eq 2}">
															<input type="hidden" name="thirdProvideTerms"
																id="thirdProvideTerms" value="" />
															<select id="thirdProvideTermsYn" style="min-width: 80px;">
														</c:when>
														<c:otherwise>
															<input type="hidden" name="certiTerms" id="certiTerms"
																value="" />
															<select id="certiTermsYn" style="min-width: 80px;">
														</c:otherwise>
													</c:choose> <c:choose>
														<c:when test="${resultDetailTermsList.customerTermsYn eq 'Y'}">
															<option value="Y" selected>동의</option>
															<option value="N" >미동의</option>
															<span style='color: red;'> </select>
														</c:when>
														<c:otherwise>
															<option value="Y">동의</option>
															<option value="N" selected>미동의</option>
															</select>
														</c:otherwise>
													</c:choose> 
														<c:out value='${resultDetailTermsList.customerTermsAuthYn}' /> 
														<c:if test="${resultDetailTermsList.customerTermsAuthVerYn eq 'N'}">
														</span>
													</c:if>
													</td>
												<th scope="row">동의 일시</th>
												<td class="txt_l"><c:out
														value='${resultDetailTermsList.customerTermsAuthDate}' /></td>
											</tr>
										</c:forEach>
									</c:otherwise>
								</c:choose>

								<tr>
									<th scope="row" colspan="2">스마트 OTP 사용</th>
									<td class="txt_l">
									<select id="customerOtpYn" name="customerOtpYn" style="min-width: 80px;">
										<option value="Y" <c:if test="${resultDetail.customerOtpYn eq 'Y' }"> selected </c:if> >사용</option>
										<option value="N" <c:if test="${resultDetail.customerOtpYn eq 'N' }"> selected </c:if> >미사용</option>
									</select>
									<input type="text" name="customerOtpId" id="customerOtpId" style="width: 238px;" maxlength="200" value="${resultDetail.customerOtpId}" />
									</td>
									<th scope="row">등록 일시</th>
									<td class="txt_l"><c:out value='${resultDetail.otpChgDate}' /></td>
								</tr>
								
								<tr>
									<th scope="row" colspan="2">데이터셋 잠금여부</th>
									<td class="txt_l" colspan="3">
									<select id="customerDatasetLock" style="min-width: 80px;">
										<option value="Y" <c:if test="${resultDetail.customerDatasetLockYn eq 'Y' }"> selected </c:if> >사용</option>
										<option value="N" <c:if test="${resultDetail.customerDatasetLockYn eq 'N' }"> selected </c:if> >미사용</option>
									</select>
									<input type="hidden" name="customerDatasetLockYn" id="customerDatasetLockYn" value="" />
								</tr>

                                <%--<tr>
                                    <th scope="row" colspan="2">통합계좌조회 사용</th>
                                    <td class="txt_l" colspan="3">
                                        <select id="integrationAccount" style="min-width: 80px;">
                                            <option value="Y" <c:if test="${resultDetail.integrationAccountYn eq 'Y' }"> selected </c:if> >사용</option>
                                            <option value="N" <c:if test="${resultDetail.integrationAccountYn eq 'N' }"> selected </c:if> >미사용</option>
                                        </select>
                                        <input type="hidden" name="integrationAccountYn" id="integrationAccountYn" value="" />
                                </tr>--%>
								
								<tr>
									<th scope="row" colspan="2">등록 일시</th>
									<td class="txt_l" colspan="3"><c:out
											value='${resultDetail.createDate}' /></td>
								</tr>
								<tr>
									<th scope="row" colspan="2">수정 일시</th>
									<td class="txt_l" colspan="3"><c:out
											value='${resultDetail.updateDate}' /></td>
								</tr>
								<tr>
									<th scope="row" colspan="2">탈퇴 일시</th>
									<td class="txt_l" colspan="3"><c:out
											value='${resultDetail.deleteDate}' /></td>
								</tr>

							</tbody>
						</table>
					</div>

					<h3>금융 정보 제공 동의서</h3>
					<div class="tb_list1">
						<table>
							<caption>회원정보</caption>
							<colgroup>
								<col style="width: 15%;">
								<col style="width: 15%;">
								<col style="width: 15%;">
								<col style="width: *">
								<col style="width: 20%;">
								<col style="width: 10%;">
							</colgroup>
							<thead>
								<tr>
									<th scope="row">핀테크 기업</th>
									<th scope="row">핀테크 서비스</th>
									<th scope="row">증권사</th>
									<th scope="row">실계좌</th>
									<th scope="row">가상계좌</th>
									<th scope="row">동의서 상태</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${empty resultDetailSvcTermsList}">
										<tr>
											<td colspan="6" align="center">조회 된 금융 정보 제공 동의서가 없습니다.</td>
										</tr>
									</c:when>
									<c:otherwise>
										<c:forEach var="resultDetailSvcTermsList"
											items="${resultDetailSvcTermsList}" varStatus="status">
											<tr>
												<td class="rowspan_subcompany"><c:out
														value='${resultDetailSvcTermsList.subcompanyName}' /></td>
												<td
													class="rowspan_${resultDetailSvcTermsList.serviceRegNo}${resultDetailSvcTermsList.termsRegNo}${resultDetailSvcTermsList.subcompanyCodeId}${resultDetailSvcTermsList.appId}">
													<c:out value='${resultDetailSvcTermsList.appName}' />
												</td>
												<td
													class="rowspan_${resultDetailSvcTermsList.serviceRegNo}${resultDetailSvcTermsList.termsRegNo}${resultDetailSvcTermsList.subcompanyCodeId}${resultDetailSvcTermsList.appId}${resultDetailSvcTermsList.pubcompanyCodeId}">
													<c:out value='${resultDetailSvcTermsList.pubcompanyName}' />
												</td>
												<td><c:out
														value='${resultDetailSvcTermsList.customerRealaccountNo}' /></td>
												<td><c:out
														value='${resultDetailSvcTermsList.customerVtaccountNo}' />&nbsp;/&nbsp;
													<c:out
														value='${resultDetailSvcTermsList.customerVtaccountAlias}' />
												</td>
												<td
													class="rowspan_${resultDetailSvcTermsList.serviceRegNo}${resultDetailSvcTermsList.termsRegNo}"
													onclick="javascript:fn_openTerms('${resultDetailSvcTermsList.termsRegNoEncryption}', '${resultDetailSvcTermsList.termsAuth}', 'N');">
													<span class="btn_gray1"><a
														href="javascript:void(0);"><c:out
																value='${resultDetailSvcTermsList.termsAuthName}' /></a></span>
												</td>
											</tr>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
					</div>

					<h3>보유 가상계좌</h3>
					<div class="tb_list1">
						<table>
							<caption>보유 가상 계좌</caption>
							<colgroup>
								<col style="width: 30%;">
								<col style="width: 20%;">
								<col style="width: 20%;">
								<col style="width: *">
							</colgroup>
							<thead>
								<tr>
									<th scope="row">증권사</th>
									<th scope="row">실계좌번호</th>
									<th scope="row">가상계좌번호</th>
									<th scope="row">별칭</th>
								</tr>
							</thead>
	                        <tbody>
	                            <c:choose>
	                                <c:when test="${empty resultDetailAccountList}" >
	                                    <tr>
	                                       <td colspan="4" align="center">조회 된 가상계좌가 없습니다.</td>
	                                    </tr>
	                                </c:when>
	                                <c:otherwise>
	                                    <c:forEach var="resultDetailAccountList" items="${resultDetailAccountList}" varStatus="status">
	                                        <tr>
	                                            <td><c:out value='${resultDetailAccountList.companyCodeName}'/></td>
	                                            <td><c:out value='${resultDetailAccountList.customerRealaccountNo}'/></td>
	                                            <td><c:out value='${resultDetailAccountList.customerVtaccountNo}'/></td>
	                                            <td><c:out value='${resultDetailAccountList.customerVtaccountAlias}'/></td>
	                                        </tr>                                                    
	                                    </c:forEach>
	                                </c:otherwise>
	                            </c:choose>
	                        </tbody>
						</table>
					</div>

					<div class="btn_type3">
						<div class="left">
							<span class="btn_gray1"><a href="javascript:void(0);"
								id="btnList">목록</a></span>
						</div>
						<div class="right">
							<span class="btn_gray2"><a href="javascript:void(0);"
								id="btnSave">저장</a></span>
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