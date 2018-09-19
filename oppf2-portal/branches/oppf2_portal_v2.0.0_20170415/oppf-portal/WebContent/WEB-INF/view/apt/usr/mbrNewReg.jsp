<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!doctype html>
<html lang="ko">
<head>
<%--
/**  
 * @Name : mbrNewReg.jsp
 * @Description : 회원 신규 생성
 * @Modification Information
 *
 * <pre>
 *  
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  
 * </pre>
 *
 * @author 
 * @since 
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
 
    
	/* [이메일]셀렉트박스 변경 시 호출 */
    $("#customerEmail2_slt").change(function(){
        if($(this).val()==""){ //[직접입력]인 경우
            $("#customerEmail2_txt").val("");
            $("#customerEmail2_txt").attr("disabled",false);
        }else{
            $("#customerEmail2_txt").val($(this).val());
            $("#customerEmail2_txt").attr("disabled",true);
        }
    });
    
    /* [중복확인]버튼 클릭 시 호출 */
    $("#btnChkId").click(function(){
        
        if(!gfn_validationCheckId("customerId")){
        	return false;
        }
        
        var url = "<c:url value='/usr/mbrReg/checkId.ajax'/>";
        var reqData = {"customerId":$("#customerId").val()};
        util_ajaxPage(url, reqData, "fn_ajaxCallback_checkId");
        
    });
    
    /* [아이디재입력]버튼 클릭 시 호출 */
    $("#btnIdReInput").click(function(){
        $("#customerIdCheck").val("N"); //아이디체크 초기화
        $("#customerId").css("background-color","#ffffff");
        $("#customerId").attr("readonly",false);
        $("#btnIdReInput").hide(); //[아이디재입력]
        $("#btnChkId").show(); //[중복확인]
    });
    
    
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
    

    fn_setSelectBoxBirthDay(); //생년월일 설정
    

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
 

    //이메일 주소 타입 변경 시
    $('#ciCustomerVerifyStatusYn').change(function(){
        if($(this).val()=="Y"){
            $('#ciCustomerVerify').val("");
            $('#ciCustomerVerify').attr("disabled",false);
        }else{
            $('#ciCustomerVerify').val("");
            $('#ciCustomerVerify').attr("disabled",true);
        }
    });
    $('#dnCustomerVerifyStatusYn').change(function(){
        if($(this).val()=="Y"){
            $('#dnCustomerVerify').val("");
            $('#dnCustomerVerify').attr("disabled",false);
        }else{
            $('#dnCustomerVerify').val("");
            $('#dnCustomerVerify').attr("disabled",true);
        }
    });
 

    $("#customerBirthDayYYYY").val("1999"); //년
    $("#customerBirthDayMM").val("12");   //월
    $("#customerBirthDayDD").val("12");   //일
    

    var nowDate = util_getDate("");
    	
    var nowTime = util_getTime("", "s");

    $("#ciCustomerVerify").val("ci"+nowDate+nowTime);
    $("#dnCustomerVerify").val("dn"+nowDate+nowTime);
    
});

/*******************************************
 * 기능 함수
 *******************************************/

 <%-- 목록 --%>
 function fn_list(){
     util_moveRequest("NewMbrRegVO", "<c:url value='/apt/sptUsr/sptUserManageList.do'/>", "_self");
 }
 
<%-- 저장 --%>
function fn_save(){
	if(!fn_validate()){
        return;
    }
	    
    //로딩 호출
    gfn_setLoading(true);
    
  //page setting  
    var url = "<c:url value='/apt/usr/saveNewMbrReg.ajax'/>";
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
    if(data.ciDupYn == 'Y'){
        alert("이미 등록된 회원 인증값입니다.");
        return;
    }
    
    if(data.dnDupYn == 'Y'){
        alert("이미 등록된 공인 인증 값입니다.");
        return;
    }
    
    
    //사용자 customer_reg_no가 없는경우
    if(data.user == -1){
        alert("회원의 customer_reg_no가 없습니다.");
        return;
    }
    
    if(data.result > 0){
        alert("정상적으로 등록되었습니다.");
        fn_list();
    }else{
        alert("등록에 실패하였습니다.");
        fn_list();
    }
    return;
}

/* [중복확인]ID체크 콜백 함수 */
function fn_ajaxCallback_checkId(data){
    var se = data.chkSe;
    if(se == "Y"){
        alert("<spring:message code='success.alert.idcheck'/>");
        $("#customerIdCheck").val("Y");
        $("#customerId").css("background-color","silver");
        $("#customerId").attr("readonly",true);
        $("#btnChkId").hide(); //[중복확인]
        $("#btnIdReInput").show(); //[아이디재입력]
        $("#customerId").focus();
    }else{
        alert("<spring:message code='fail.alert.idcheck'/>");
//         $("#customerId").val("");
        $("#customerId").focus();
    }
    $("#customerIdCheck").val(se);
}


<%-- 저장 처리 체크 --%>
function fn_validate(){
    
    var customerId      = $("#customerId").val().replace(/\s/g, ""); //아이디
    var customerIdCheck = $("#customerIdCheck").val(); //아이디 중복체크
    var customerPassword1 = $("#customerPassword1").val().replace(/\s/g, ""); //비밀번호
    var customerPassword2 = $("#customerPassword2").val().replace(/\s/g, ""); //비밀번호확인
    var customerBirthDayYYYY = $("#customerBirthDayYYYY option:selected").val(); //년
    var customerBirthDayMM   = $("#customerBirthDayMM option:selected").val();   //월
    var customerBirthDayDD   = $("#customerBirthDayDD option:selected").val();   //일
    var customerEmail1     = $("#customerEmail1").val().replace(/\s/g, "");     //이메일앞자리
    var customerEmail2_txt = $("#customerEmail2_txt").val().replace(/\s/g, ""); //이메일뒷자리
    var customerPhone1 = $("#customerPhone1").val(); //휴대폰번호1
    var customerPhone2 = $("#customerPhone2").val(); //휴대폰번호2
    var customerPhone3 = $("#customerPhone3").val(); //휴대폰번호3
    var ciCustomerVerifyStatusYn = $("#ciCustomerVerifyStatusYn").val(); // 본인 인증 상태
    var dnCustomerVerifyStatusYn = $("#dnCustomerVerifyStatusYn").val(); // 공인 인증서 등록 상태


    var customerEmailAuthYn = $("#customerEmailAuthStatus").val(); //이메일 인증 상태
	var customerEmailReceiveYn = $("#customerEmailReceiveStatus").val(); //이메일 수신동의 여부
	
	$('#customerEmailAuthYn').val(customerEmailAuthYn);
	$('#customerEmailReceiveYn').val(customerEmailReceiveYn);
    //이름(한글)
    if(util_chkReturn($.trim($('#customerNameKor').val()), "s") == "") {
        alert("<spring:message code='errors.required' arguments='이름(한글)'/>");
        $('#customerNameKor').val("");
        $('#customerNameKor').focus();
        return false;
    }


    //[아이디]중복확인
    if(customerIdCheck != "Y"){
        alert("<spring:message code='errors.idcheck'/>");
        $("#customerId").focus();
        return false;
    }
    
    if(!gfn_validationCheckId("customerId")){
    	return false;
    }
    

    
    if(!gfn_validationCheckPw("customerPassword1")){
    	return false;
    }
    

	
    //[비밀번호확인]
    if(util_chkReturn(customerPassword2, "s") == ""){
        alert("<spring:message code='errors.required' arguments='비밀번호확인'/>");
        $("#customerPassword2").focus();
        return false;
    }
    
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
    

    $("#customerPassword").val(customerPassword1);
    
    
    //[이메일앞자리]
    if(util_chkReturn(customerEmail1, "s") == ""){
        alert("<spring:message code='errors.required' arguments='이메일앞자리'/>");
        $("#customerEmail1").focus();
        return false;
    }
    
    //[이메일앞자리]정규식
    if( !util_isEmail(customerEmail1,'1') ){
        alert("<spring:message code='errors.invalid' arguments='이메일앞자리'/>");
        $("#customerEmail1").focus();
        return false;
    }
    
    //[이메일뒷자리]
    if(util_chkReturn(customerEmail2_txt, "s") == ""){
        alert("<spring:message code='errors.required' arguments='이메일뒷자리'/>");
        $("#customerEmail2_txt").focus();
        return false;
    }
    
    //[이메일뒷자리]정규식
    if( !util_isEmail(customerEmail2_txt,'2') ){
        alert("<spring:message code='errors.invalid' arguments='이메일뒷자리'/>");
        $("#customerEmail2_txt").focus();
        return false;
    }
    
    $("#customerEmail").val(customerEmail1+"@"+customerEmail2_txt);

    $("#customerBirthDay").val(customerBirthDayYYYY+customerBirthDayMM+customerBirthDayDD);
    
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

    $("#ciCustomerVerifyStatus").val(ciCustomerVerifyStatusYn);
    $("#dnCustomerVerifyStatus").val(dnCustomerVerifyStatusYn);

    if(ciCustomerVerifyStatusYn == "Y"){

        if(util_chkReturn($.trim($('#ciCustomerVerify').val()), "s") == "") {
            alert("<spring:message code='errors.required' arguments='본인인증 값'/>");
            $('#ciCustomerVerify').val("");
            $('#ciCustomerVerify').focus();
            return false;
        }

    } 
    if(dnCustomerVerifyStatusYn == "Y"){

        if(util_chkReturn($.trim($('#dnCustomerVerify').val()), "s") == "") {
            alert("<spring:message code='errors.required' arguments='공인인증서 값'/>");
            $('#dnCustomerVerify').val("");
            $('#dnCustomerVerify').focus();
            return false;
        }

    } 

    var serviceTerms = $("#serviceTermsYn").val(); // 본인 인증 상태
    var indTerms = $("#indTermsYn").val(); // 공인 인증서 등록 상태
    var thirdProvideTerms = $("#thirdProvideTermsYn").val(); // 본인 인증 상태
    var certiTerms = $("#certiTermsYn").val(); // 공인 인증서 등록 상태
    

    $("#serviceTerms").val(serviceTerms);				// 서비스 이용약관
    $("#indTerms").val(indTerms);						// 개인정보수집 및 이용동의
    $("#thirdProvideTerms").val(thirdProvideTerms);		// 개인정보 제3자 제공 동의
    $("#certiTerms").val(certiTerms);					// 공인인증서 등록 약관동의
    
    
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


</script>

</head>

<body>
<form:form commandName="NewMbrRegVO" name="NewMbrRegVO" method="post">
    <input type="hidden" name="customerRegStatus" id="customerRegStatus" value="G005002" /> 
    <input type="hidden" name="customerStep" id="customerStep" value="G006005" /> 
    <input type="hidden" name="customerSex" id="customerSex" value="G012001" /> 

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
                    <h2>일반 회원 정보</h2>
                </div>
                <!-- // locatioin -->
                
                <h3>기본 정보</h3>                                
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
                        <tr>
                            <th scope="row" colspan="2">회원 이름(한글)<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l" colspan="3">
                                <input type="text" class="w100" id="customerNameKor" name="customerNameKor" value="홍길동" />
                            </td>                  
                        </tr>
                        <tr>
                            <th scope="row" colspan="2">회원 ID<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l" colspan="3"> 
                            	<input type="text" name="customerId" id="customerId" style="width:238px;ime-mode:disabled;" maxlength="15" onkeydown="util_engonly(event);" />
                                &nbsp;
                                <span class="btn_gray1"><a href="javascript:void(0);" id="btnChkId">중복확인</a></span>
                                <span class="btn_gray1"><a href="javascript:void(0);" id="btnIdReInput" style="display:none;" >아이디재입력</a></span>
                                <span class="info_msg">영문, 숫자 조합, 소문자 입력4~15자 이내 한글입력 불가</span>
                                <input type="hidden" name="customerIdCheck" id="customerIdCheck" value="N" />                           
                            </td>                 
                        </tr>
                        <tr>
                            <th scope="row" colspan="2">
                                <label for="user_pw">비밀번호<span class="icon_basic">*필수입력</span></label>
                            </th>
                            <td class="txt_l" colspan="3">
                                <input type="password" name="customerPassword1" id="customerPassword1" style="width:238px;" maxlength="15" placeHolder="ex)Qetu101!@"  value="zhtmzhaWkd!"/>
                                <span class="info_msg">8~15자의 영문 대/소문자, 숫자, 특수문자 중 3개 이상의 조합</span>
                                <input type="hidden" name="customerPassword" id="customerPassword"  value="zhtmzhaWkd!"/>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row" colspan="2">
                                <label for="user_pw2">비밀번호 확인<span class="icon_basic">*필수입력</span></label>
                            </th>
                            <td class="txt_l" colspan="3">
                                <input type="password" name="customerPassword2" id="customerPassword2" style="width:238px;"  maxlength="15"  value="zhtmzhaWkd!"/>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row" colspan="2">회원 가입 상태</th>
                            <td class="txt_l" colspan="3">활성화
                            </td>                 
                        </tr>
                        <tr>
                            <th scope="row" colspan="2">회원 가입 단계</th>
                            <td class="txt_l" colspan="3">가입완료                            
                            </td>                 
                        </tr>
                        <tr>
                            <th scope="row" colspan="2"><label for="user_birth">생년월일</label></th>
                            <td class="txt_l" colspan="3">
                                <span class="sel_box1">
                                    <select name="customerBirthDayYYYY" id="customerBirthDayYYYY" title="년 선택" style="min-width:95px;">
                                        <option value="">선택</option>
                                    </select>
                                </span>년
                                <span class="sel_box1">
                                    <select name="customerBirthDayMM" id="customerBirthDayMM" title="월 선택">
                                        <option value="">선택</option>
                                    </select>
                                </span>월
                                <span class="sel_box1">
                                    <select name="customerBirthDayDD" id="customerBirthDayDD" title="일 선택">
                                        <option value="">선택</option>
                                    </select>
                                </span>일
                            	<input type="hidden" name="customerBirthDay" id="customerBirthDay" />
                            </td>
                        </tr>
                        <tr>
                            <th scope="row" colspan="2">
                                <label for="user_email">이메일<span class="icon_basic">*필수입력</span></label>
                            </th>
                            <td class="txt_l" colspan="3">
                                <input type="text" name="customerEmail1" id="customerEmail1" maxlength="20" style="width:154px;ime-mode:disabled;" value="test" />
                                <span class="dot">@</span>
                                <input type="text" name="customerEmail2_txt" id="customerEmail2_txt" title="이메일주소뒷부분" maxlength="20" style="width:174px;ime-mode:disabled;" value="test.com" />
                                <span class="sel_box1">
                                    <select name="customerEmail2_slt" id="customerEmail2_slt" title="이메일주소 선택">
                                        <option value="">직접입력</option>
                                        <c:forEach var="emailList" items="${emailList}" varStatus="status">
                                            <option value="${emailList.code_name_kor}">${emailList.code_name_kor}</option>
                                        </c:forEach>
                                    </select>
                                </span>
                                <input type="hidden" name="customerEmail" id="customerEmail" value="" />
                            </td>
                        </tr>
                        <tr>
                            <th scope="row" colspan="2">휴대폰<span class="icon_basic">*필수입력</span></th>
                            <td class="txt_l" colspan="3">
                                <select id="customerPhone_01" style="min-width:80px;">
                                    <c:forEach var="hpList" items="${hpList}" varStatus="status">
                                        <option value="${hpList.code_name_kor}">${hpList.code_name_kor}</option>
                                    </c:forEach>
                                </select>-
                                <input type="tel" id="customerPhone_02" class="w50" maxlength="4" onkeydown="util_numberonly(event);" value="1111" >- 
                                <input type="tel" id="customerPhone_03" class="w50" maxlength="4" onkeydown="util_numberonly(event);" value="2222" >
                                <input type="hidden" id="customerPhone" name="customerPhone" value="<c:out value='${resultDetail.customerPhone}'/>" />
                            </td>                 
                        </tr>
                        <tr>
                            <th scope="row" colspan="2">이메일 인증 상태</th>
                            <td class="txt_l" colspan="3">
	                            <select id="customerEmailAuthStatus" style="min-width:80px;">
                                    <option value="Y">동의</option>
                                    <option value="N">미동의</option>
	                            </select>
	                            <input type="hidden" name="customerEmailAuthYn" id="customerEmailAuthYn" value="" />
                            </td>
                        </tr>
                        <tr>
                            <th scope="row" colspan="2">이메일 수신동의 여부</th>
                            <td class="txt_l" colspan="3">
	                            <select id="customerEmailReceiveStatus" style="min-width:80px;">
	                                    <option value="Y">동의</option>
	                                    <option value="N">미동의</option>
	                            </select>
	                            <input type="hidden" name="customerEmailReceiveYn" id="customerEmailReceiveYn" value="" />
                            </td>                                             
                        </tr>
                        <tr>
                            <th scope="row" colspan="2">본인 인증 상태</th>
                            <td class="txt_l" colspan="3">
	                            <select id="ciCustomerVerifyStatusYn" style="min-width:80px;">
	                                    <option value="Y">동의</option>
	                                    <option value="N">미동의</option>
	                            </select>
	                            &nbsp&nbsp<input type="text" name="ciCustomerVerify" id="ciCustomerVerify" class="w200" maxlength="100" onkeydown="" value="">
	                            <input type="hidden" name="ciCustomerVerifyStatus" id="ciCustomerVerifyStatus" value="" />
	                        </td>
                        </tr>
                        <tr>
                            <th scope="row" colspan="2">공인 인증서 등록 상태</th>
                            <td class="txt_l" colspan="3">
	                            <select id="dnCustomerVerifyStatusYn" style="min-width:80px;">
	                                    <option value="Y">동의</option>
	                                    <option value="N">미동의</option>
	                            </select>
	                            &nbsp&nbsp<input type="text" name="dnCustomerVerify" id="dnCustomerVerify" class="w200" maxlength="100" onkeydown="" value="">
	                            <input type="hidden" name="dnCustomerVerifyStatus" id="dnCustomerVerifyStatus" value="" />
	                        </td>
                        </tr>
                        <%-- 이용약관 --%>
                        <tr>
                            <th scope="row" rowspan="4">이용 약관</th>
                            <th scope="row">서비스이용약관</th>
                            <td class="txt_l" colspan="3">
	                            <select id="serviceTermsYn" style="min-width:80px;">
	                                    <option value="Y">동의</option>
	                                    <option value="N">미동의</option>
	                            </select>
	                            <input type="hidden" name="serviceTerms" id="serviceTerms" value="" />
                            </td>                 
                        </tr>
                        <tr>
                            <th scope="row">개인정보 수집 및 이용동의</th>
                            <td class="txt_l" colspan="3">
	                            <select id="indTermsYn" style="min-width:80px;">
	                                    <option value="Y">동의</option>
	                                    <option value="N">미동의</option>
	                            </select>
	                            <input type="hidden" name="indTerms" id="indTerms" value="" />
                            </td>                 
                        </tr>  
                        <tr>
                            <th scope="row">개인정보 제3자 제공 동의</th>
                            <td class="txt_l" colspan="3">
	                            <select id="thirdProvideTermsYn" style="min-width:80px;">
	                                    <option value="Y">동의</option>
	                                    <option value="N">미동의</option>
	                            </select>
	                            <input type="hidden" name="thirdProvideTerms" id="thirdProvideTerms" value="" />
                            </td>                 
                        </tr> 
                        <tr>
                            <th scope="row">공인인증서 등록 약관동의</th>
                            <td class="txt_l" colspan="3">
	                            <select id="certiTermsYn" style="min-width:80px;">
	                                    <option value="Y">동의</option>
	                                    <option value="N">미동의</option>
	                            </select>
	                            <input type="hidden" name="certiTerms" id="certiTerms" value="" />
                            </td>                 
                        </tr>
                        
                        </tbody>
                    </table>
                </div>
                
                <div class="btn_type3">
                    <div class="left">
                        <span class="btn_gray1"><a href="javascript:void(0);" id="btnList">목록</a></span>
                    </div>
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