<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : mbrReg4.jsp
 * @Description : [회원가입:4.개인정보입력]
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.04.28  이환덕        최초  생성
 *  2016.06.21  신동진        스마트 OTP 추가
 * </pre>
 *
 * @author 포털 이환덕 
 * @since 2016.04.28
 * @version 1.0
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>
<OBJECT classid="CLSID:EC5D5118-9FDE-4A3E-84F3-C2B711740E70" codeBase="http://www.signkorea.com/SKCommAX.cab#version=9,9,1,9" id="CertManX" width="0" height="0"></OBJECT>
<!-- <object classid="CLSID:EC5D5118-9FDE-4A3E-84F3-C2B711740E70" codebase="http://www.signkorea.com/SKCommAX.cab#version=9,9,0,5" id="CertManX" width="1" height="1"></object> -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script type="text/javascript" src="<c:url value='/js/cmm/SKCertService/app/library/json3.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/cmm/SKCertService/app/library/iecompatibility.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/cmm/SKCertService/app/vestsign.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/cmm/SKCertService/app/js/koscom.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/cmm/SKCertService/app/library/dragiframe.js'/>"></script>
<!-- 공인인증서 관련 -->
<script type="text/javascript">
var i = 0;

function certsign(signType){
    var procFlag = false;
    
    var CertManX;
    if(ytMain){
        CertManX = ytMain;
    }

    var plaintext = "testplain";
    var dn = "";
    if(i>4){
        alert("비밀번호 입력횟수 초과");
        return false;
    }
    
    if(CertManX.doubleClickBlock(arguments.callee)) return false;
    //모듈이 설치되어 있는지 확인하는 함수
    CertManX.CertServiceSetup(function(result){
        if(result == ""){
            var errorCode = CertManX.GetLastErrorCode();
            if(errorCode == 90000){
                alert("모듈 설치 필요");
                util_movePage("<c:url value='/cmm/aos2/certIndex.do'/>");
                return false;
            }
            if(errorCode == 90001 || errorCode == 90002){
                alert("모듈 업데이트 필요");
                util_movePage("<c:url value='/cmm/aos2/certIndex.do'/>");
                return false;
                
            }else{
                alert(errorCode + "//" + CertManX.GetLastErrorMsg());
                util_movePage("<c:url value='/cmm/aos2/certIndex.do'/>");
                return false;
            }
        }
    });
    
    //기간만료 폐기된 공인인증서 화면출력 안됨처리
    CertManX.SetMatchedContextExipreCheck(true);
    
    CertManX.SetDeviceOrder("HRUS"); // 인증서 우선 검색 순위
    CertManX.SetPasswordEncMode(1+16); // 패스워드 ENC 모드
    CertManX.SetExipreCheckSkip(0); // 인증서 만료 안내창
    
    CertManX.SetDeviceViewOrder("RUSH"); //저장매체 이미지 순서
//     CertManX.SetPolicyFilter(0,"");  //인증서 정책 필터[모든]
    CertManX.SetPolicyFilter(1+16+256,"1.2.410.200004.5.1.1.10;");  //인증서 정책 필터
    
    CertManX.SetWebAccMode(1);           //장애인 웹접근성
    
    CertManX.SetScanByDialogChoiceMode(0); //저장매체 선택시 인증서 검색
    CertManX.SetLanguageMode(0); //모듈 언어 설정
    
    CertManX.SetKeySaferMode(3);         // 키보드보안모듈 연동 -> 최초에 파라미터가 3으로 셋팅되어있었음(키보드 보안업체 프로그램 과의 상호 연동을 위한 설정)
    CertManX.SetWrongPasswordLimit(1);   // 패스워드 입력제한

    
    CertManX.UnSetMatchedContext(function () {
        CertManX.SetMatchedContextExt("","","", 256+0+1, function(dn){
            if(dn == ""){
                if(CertManX.GetLastErrorCode() == 2417){
                    i++;
                    alert("비밀번호 오류 ["+i+"회]");
                    //sign();
                    //reset
                    fn_resetOtp();
                    return false;
                    
                }else{
                    //'인증서선택' 팝업창의 우하단 취소버튼이나 우상단 X버튼을 클릭하면 여기로 옵니다.
                    //alert("SetMatchedContextExt 실패 : ["+CertManX.GetLastErrorCode() +"]"+CertManX.GetLastErrorMsg());
                    if(CertManX.GetLastErrorCode() == "2500"){
                        alert(CertManX.GetLastErrorMsg());
                    }else if(CertManX.GetLastErrorCode() != "2501"){
                        //alert("SetMatchedContextExt 실패 : [" + CertManX.GetLastErrorCode() + "]" + CertManX.GetLastErrorMsg());
                        alert(CertManX.GetLastErrorMsg());
                    }
                    i = 0;
                    //reset
                    fn_resetOtp(); 
                    return false;
                } 
            }
            i=0;

            //BSTR SignDataB64(BSTR pPassword, BSTR pPlainText, long mode)
            CertManX.SignDataB64("", plaintext, 1, function(signdata) {
                if(signdata == ""){
                    //alert("SignDataB64 실패 : ["+CertManX.GetLastErrorCode() +"]"+CertManX.GetLastErrorMsg());
                    alert(CertManX.GetLastErrorMsg());
                    return false;
                }
                
                var t_dn = CertManX.GetToken(signdata, "dn");
                var customerDn = "${paramVO.customerVerifyDn}";
                
                if(t_dn != customerDn){
                    alert("<spring:message code='errors.nosamedata.dn' arguments='등록한 공인인증서,입력한 공인인증서'/>");
                    //reset
                    fn_resetOtp();
                    return false;
                }
                
                form.t_dn.value = CertManX.GetToken(signdata, "dn");
                form.t_signdata.value = CertManX.GetToken(signdata, "data"); //서명데이터 [서버에서  data와 r 값을 유효성검증함]
                form.t_rdata.value = CertManX.GetToken(signdata,"r"); //주민번호등 인증비교값 [서버에서  data와 r 값을 유효성검증함]
                signData = form.t_signdata.value;
                
                //유효성검증:공인인증호출
                var rsFlag = fn_ajaxCallSKVeirify();
                if(!rsFlag){
                    //reset
                    fn_resetOtp();
                    return false;
                }
                
                procFlag = true;
                
                //폐기
                if(signType == "delete"){
                    //OTP 폐기
                    fn_deleteOtp();
                    
                //등록 / 수정
                }else{
                    //등록 / 수정
                    fn_registOtp();
                }
            });
        });
    });
}
    
/*[공인인증서서버]공인인증서 서버 유효성검증을 조회요청 함수 */
function fn_ajaxCallSKVeirify(){
    
    var rsFlag = false;
    
//     var userSsn  = $("#customerSsn").val().replace(/\s/g, ""); //주민등록번호
    var signData = form.t_signdata.value;
    var rValue   = form.t_rdata.value;
    
    //파라미터셋팅
    var objParam = new Object();
    objParam.signData = signData;
    objParam.rValue   = rValue;

    //로딩 호출
    gfn_setLoading(true, "공인인증서 유효성 검증 중 입니다.");
    
    var url = "<c:url value='/cmm/selectSKVerify.ajax'/>";
    $.ajaxSettings.traditional = true; //ajax 배열 parameter 처리 설정
    $.ajax({
        type    : "post"
       ,contentType: "application/json"
       ,async   : false 
       ,url     : url
       ,data    : JSON.stringify(objParam)
       ,success : function(data){
           //로딩 호출
           gfn_setLoading(false);
           
           if(data.rs == '0'){
               var msgAlert = "<spring:message code='success.alert.regist'/>";
//                alert(msgAlert);
               rsFlag = true;
           }else{
               alert("<spring:message code='errors.alert.signkorea'/>\n["+data.rs+"]");
//                alert(data.rsMsg);
           }
       }
       ,error : function(){
           //로딩 호출
           gfn_setLoading(false);
           
           var msgAlert = "<spring:message code='fail.alert.regist'/>";
           alert(msgAlert);
       }
   });
    return rsFlag; 
}
   

</script>
<script type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/
var customerPhone = '<c:out value="${paramVO.customerPhone}" />';


/*******************************************
 * 기능 함수
 *******************************************/
 
/* [휴대폰번호]셋팅 함수 */
function fn_setValphoneNumber(pNumber){
    if(util_chkReturn(pNumber, "s") == ""){
        alert('휴대폰번호가 없습니다.');
        return false;
    }
    var customerPhone1 = '';
    var customerPhone2 = '';
    var customerPhone3 = '';
     var pNumber_arr = pNumber.split("-");
     if(pNumber_arr.length == 3){
         customerPhone1 = pNumber_arr[0];
         customerPhone2 = pNumber_arr[1];
         customerPhone3 = pNumber_arr[2];
     }else{
         if(pNumber.length == 10){ //ex)0104568901
             customerPhone1 = pNumber.substring(0,3);
             customerPhone2 = pNumber.substring(3,6);
             customerPhone3 = pNumber.substring(6);
         }else if(pNumber.length == 11){//ex)01045678901
             customerPhone1 = pNumber.substring(0,3);
             customerPhone2 = pNumber.substring(3,7);
             customerPhone3 = pNumber.substring(7);
         }
     }
     $("#customerPhone1").val(customerPhone1);
     $("#customerPhone2").val(customerPhone2);
     $("#customerPhone3").val(customerPhone3);
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

 
/*유효성검증 함수*/
function fn_SaveValidate(){
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
    
    if(!gfn_validationCheckId("customerId")){
    	return false;
    }
    
    //[아이디]중복확인
    if(customerIdCheck != "Y"){
        alert("<spring:message code='errors.idcheck'/>");
        $("#customerId").focus();
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
    
    //[년]
//     if(util_chkReturn(customerBirthDayYYYY, "s") == ""){
//         alert("<spring:message code='errors.required.select' arguments='년'/>");
//         $("#customerBirthDayYYYY").focus();
//         return false;
//     }
    
    //[월]
//     if(util_chkReturn(customerBirthDayMM, "s") == ""){
//         alert("<spring:message code='errors.required.select' arguments='월'/>");
//         $("#customerBirthDayMM").focus();
//         return false;
//     }
    
    //[일]
//     if(util_chkReturn(customerBirthDayDD, "s") == ""){
//         alert("<spring:message code='errors.required.select' arguments='일'/>");
//         $("#customerBirthDayDD").focus();
//         return false;
//     }
    if(util_chkReturn(customerBirthDayYYYY, "s") != "" || 
       util_chkReturn(customerBirthDayMM, "s") != "" || 
       util_chkReturn(customerBirthDayDD, "s") != ""
    ){
        if(!(util_chkReturn(customerBirthDayYYYY, "s") != "" && 
           util_chkReturn(customerBirthDayMM, "s") != "" && 
           util_chkReturn(customerBirthDayDD, "s") != "")
        ){
        alert("<spring:message code='errors.required.select' arguments='생년월일'/>");
        return false;
        }
    }
    
    //[성별]
//     if($("input[name='customerSex']:checked").length > 1){
//         alert("<spring:message code='errors.required.select' arguments='성별'/>");
//         $("#customerSex_001").focus();
//         return false;
//     }
    
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
    
    //[휴대폰번호1]
    if(util_chkReturn(customerPhone1, "s") == ""){
        alert("<spring:message code='errors.required.select' arguments='휴대폰번호 첫번째자리'/>");
        $("#customerPhone1").focus();
        return false;
    }
    
    //[휴대폰번호2]
    if(util_chkReturn(customerPhone2, "s") == ""){
        alert("<spring:message code='errors.required' arguments='휴대폰번호 두번째자리'/>");
        $("#customerPhone2").focus();
        return false;
    }
    
    //[휴대폰번호3]
    if(util_chkReturn(customerPhone3, "s") == ""){
        alert("<spring:message code='errors.required' arguments='휴대폰번호 세번째자리'/>");
        $("#customerPhone3").focus();
        return false;
    }
    
    //otp 확인
    if($("#customerOtpYn").is(":checked")){
    	if(util_chkReturn($.trim($('#customerOtpId').val()), "s") == ""){
    		alert("<spring:message code='errors.invalid' arguments='스마트 OTP 번호'/>");
            $("#customerSendOtpId").focus();
            return false;
        }
    }
    
    return true;
    
}
 
 
/*******************************************
 * ajax,ajax Callback 함수
 *******************************************/
 
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

<%-- 처리 실패시 reset --%>
function fn_resetOtp(){
    var customerOtpYn = $("#customerOtpYn").is(":checked")? false:true;
    
    $("#btnOtp").html("확인");
    //체크 시
    if(customerOtpYn){
        $("#customerSendOtpId").removeAttr("disabled");
        
        $("#btnOtp").removeClass("type2");
        
        //이전값 셋팅
        $("#customerOtpYn").prop("checked", true);
        
        $("#customerSendOtpId").val("");
        $("#customerOtpId").val("temp");
    }else{
        $("#customerSendOtpId").attr("disabled", "disabled");
        
        $("#btnOtp").addClass("type2");
        
        //이전값 셋팅
        $("#customerOtpYn").prop("checked", false);
        
        $("#customerSendOtpId").val("");
        $("#customerOtpId").val("");
    }    
}

<%-- OTP 등록 --%>
function fn_registOtp(){
    //로딩 호출
    gfn_setLoading(true, "스마트 OTP 처리 중입니다.");
    
    var moveUrl = "<c:url value='/cmm/otp/selectOtpRegist.ajax'/>";
    var param = new Object();
    var callBackFunc = "fn_registOtpCallBack";
    
    param.customerRegNo = $("#customerRegNo").val();
    param.customerSendOtpId = $('#customerSendOtpId').val();
    param.loginCheckYn = "N";   //로그인 체크여부
    
    <%-- 공통 ajax 호출 --%>
    util_ajaxPageJson(moveUrl, param, callBackFunc);
}
function fn_registOtpCallBack(data){ 
    //로딩 호출
    gfn_setLoading(false);
    
    if(util_chkReturn(data, "s") == "") {
    	alert("내부서버 오류입니다.\n관리자에게 문의해 주세요.\n[400]");
    	fn_resetOtp();
    	return;
    }
    
    //signature 생성 오류
    if(data.error == 1){
    	alert("내부서버 오류입니다.\n관리자에게 문의해 주세요.\n[signature]");
    	fn_resetOtp();
        return;
    }
    
    
    var resultData = data.result;
    
    if(util_chkReturn(resultData, "s") == ""){
        alert("OTP 결과 처리 중 문제가 발생하였습니다.");
        fn_resetOtp();
        return;
    }else{
        resultData = JSON.parse(resultData);
    }
    
    //OTP 저장 처리 성공 여부
    var saveResult = data.saveResult;
        
    //성공
    if(resultData.result == "success" && resultData.message == "ok"){
    	//OTP 저장 실패
        if(saveResult <= -1){
            alert("OTP 저장에 실패하였습니다.");
            fn_resetOtp();
            return;
        }else{
	        $("#customerOtpId").val(resultData.otp_id);
	        
	        $("#customerSendOtpId").attr("disabled", "disabled");
	        $("#btnOtp").html("변경");
        }
    }else{
        if(resultData.message == "signature_error"){
            alert("signature를 인식할 수 없습니다.");
            return;
        }else if(resultData.message == "api_key_error"){
            alert("api 키를 인식할 수 없습니다.");
            return;
        }else if(resultData.message == "otp_error"){
            alert("등록용 OTP 를 인식할 수 없습니다.");
            return;
        }else if(resultData.message == "invalid_parameter"){
            alert("필요한 파라메터를 취득할 수 없습니다.");
            return;
        }else if(resultData.message == "sourceip_error"){
            alert("요청 송신의 ip를 인식할 수 없습니다.");
            return;
        }
        
        if(resultData.result == "fail"){
            alert("OTP 서버에 접속 실패하였습니다.");
            return;
        }
        
        fn_resetOtp();
    }
    
}

<%-- OTP 폐기 --%>
function fn_deleteOtp(){
    var moveUrl = "<c:url value='/cmm/otp/deleteOtpRegist.ajax'/>";
    var param = new Object();
    var callBackFunc = "fn_deleteOtpCallBack";
        
    param.customerRegNo = $("#customerRegNo").val();
    param.loginCheckYn = "N";   //로그인 체크여부
    
    //로딩 호출
    gfn_setLoading(true, "스마트 OTP 폐기 중입니다.");
    
    <%-- 공통 ajax 호출 --%>
    util_ajaxPageJson(moveUrl, param, callBackFunc);
}
function fn_deleteOtpCallBack(data){
    if(util_chkReturn(data, "s") == "") {
        //로딩 호출
        gfn_setLoading(false);
        
        alert("내부서버 오류입니다.\n관리자에게 문의해 주세요.\n[400]");
        fn_resetOtp();
        return;
    }
    
    //로딩 호출
    gfn_setLoading(false);
    
    var result = data.result;
    var chkCnt = data.chkCnt;
    
    //처리해야할 데이터 0개 이상
    if(chkCnt > 0){
        if(result <= 0){
            alert("OTP 폐기에 실패하였습니다.");
            fn_resetOtp();
            return;
        }
    }
}    

/*******************************************
 * 이벤트 함수
 *******************************************/
 
/* 화면 로드 처리 */
$(document).ready(function(){
    
//     $("#customerEmail1").bind("keydown", function(e){
        
//     });
    
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
    
    /* otp 사용 클릭 시  */
    $("#customerOtpYn").change(function(){
    	var customerSendOtpId = $("#customerSendOtpId").val();
        var customerOtpId = $("#customerOtpId").val();
    	
    	$("#customerSendOtpId").val("");
    	$("#customerOtpId").val("");
    	
    	$("#btnOtp").html("확인");
        //체크 시
        if($(this).is(":checked")){
            $("#customerSendOtpId").removeAttr("disabled");
            
            $("#btnOtp").removeClass("type2");
        }else{
            $("#customerSendOtpId").attr("disabled", "disabled");
            
            $("#btnOtp").addClass("type2");
            
            if(customerOtpId != ""){
                //공인인증서 체크
                certsign('delete');     
            }
        }
    });
    
    /* otp 확인 클릭 시 */
    $("#btnOtp").click(function(){
    	if($("#btnOtp").html() == "변경"){
    		$("#btnOtp").html("확인");
            
            //otp 정보 리셋
            $("#customerSendOtpId").val("");
            $("#customerOtpId").val("");
            
            //otp 사용 등록번호 입력 input remove disable
            $("#customerSendOtpId").removeAttr("disabled");
        }else{
            if(!$("#customerOtpYn").is(":checked")){
                alert("OTP사용을 선택 후 확인을 클릭 해 주세요.");
                $("#customerOtpYn").focus();
                return;
            }
                
            if(util_chkReturn($.trim($('#customerSendOtpId').val()), "s") == ""){
                alert("<spring:message code='errors.invalid' arguments='OTP 사용 등록번호'/>");
                $("#customerSendOtpId").focus();
                return;
            }
            
            //공인인증서 체크
            certsign('save');
        }
    });
    
    /* [취소]버튼 클릭 시 호출 */
    $("#btnCancel").click(function(){
        alert('취소 준비중 입니다.');
    });
    
    /* [다음]버튼 클릭 시 호출 */
    $("#btnNext").click(function(){
        if( !fn_SaveValidate() ){ //유효성검증호출
            return;
        }
        //var msgConfirm = "<spring:message code='confirm.regist.msg'/>";
        //if( confirm(msgConfirm) ){
            var customerRegNo = $("#customerRegNo").val(); //회원OP등록번호
            var customerRegNoPrefix  = $("#customerRegNoPrefix").val(); //번호prefix
            var customerNameKor      = $("#customerNameKor").val(); //이름
            var customerId           = $("#customerId").val().replace(/\s/g, ""); //아이디
            var customerPassword1    = $("#customerPassword1").val().replace(/\s/g, ""); //비밀번호
            var customerBirthDayYYYY = $("#customerBirthDayYYYY option:selected").val(); //년
            var customerBirthDayMM   = $("#customerBirthDayMM option:selected").val();   //월
            var customerBirthDayDD   = $("#customerBirthDayDD option:selected").val();   //일
            var customerBirthDay     = customerBirthDayYYYY+''+customerBirthDayMM+''+customerBirthDayDD;
            var customerSex          = $("input[name='customerSex']:checked").val();  //성별
            var customerEmail1       = $("#customerEmail1").val().replace(/\s/g, "");     //이메일앞자리
            var customerEmail2_txt   = $("#customerEmail2_txt").val().replace(/\s/g, ""); //이메일뒷자리
            var customerPhone1       = $("#customerPhone1").val(); //휴대폰번호1
            var customerPhone2       = $("#customerPhone2").val(); //휴대폰번호2
            var customerPhone3       = $("#customerPhone3").val(); //휴대폰번호3
            var customerEmailReceiveYn = 'N'; //이메일수신동의여부
            
            //이메일수신동의여부가 체크한 경우
            if( $("input:checkbox[id='customerEmailReceiveYn_chk']").is(":checked") ){
                customerEmailReceiveYn = 'Y';
            }
            
            var objParam = new Object();
            objParam.customerRegNo       = customerRegNo;
            objParam.customerRegNoPrefix = customerRegNoPrefix;
            objParam.customerNameKor     = customerNameKor;
            objParam.customerId          = customerId;
            objParam.customerPassword    = customerPassword1;
            if(util_chkReturn(customerBirthDay, "s") != ""){
                objParam.customerBirthDay = customerBirthDay;
            }
            if(util_chkReturn(customerSex, "s") != ""){
                objParam.customerSex = customerSex;
            }
            objParam.customerEmail       = customerEmail1 + '@' + customerEmail2_txt;
            objParam.customerPhone       = customerPhone1 + '-' + customerPhone2 + '-' + customerPhone3;
            objParam.customerEmailReceiveYn = customerEmailReceiveYn;
            objParam.customerStep        = 'G006004'; //회원 가입 Step:G006(001:본인인증, 002:공인인증서등록, 003:약관동의, 004:개인정보입력, 005:이메일인증, 006:임시비밀번호발급, 007:임시비밀번호수정)
            objParam.customerRegStatus   = 'G005002'; //메일발송하기때문에 상태를 활성으로 바꿔준다.
            
            //비밀번호 관련
            objParam.customerChgPasswordDate = "sysdate";
            objParam.customerExpirePasswordDate = "<spring:message code='Globals.user.policy.password.expire' />";
            objParam.customerFinalPasswordDate = "<spring:message code='Globals.user.policy.password.final' />";
            
            var url = "<c:url value='/usr/mbrReg/saveMbrReg4.ajax'/>";
            
            //로딩 호출
            gfn_setLoading(true, "개인정보 입력 처리 중입니다.");
            
            $.ajax({
                 type    : "post"
                ,url     : url
                ,data    : objParam
                ,success : function(data){
                    //var msgAlert = "<spring:message code='success.alert.regist'/>";
                    //alert(msgAlert);
                    //fn_emailSend(); //이메일발송 -> 성공 후 다음 스텝으로 넘어갑니다.
                    
                    //로딩 호출
                    gfn_setLoading(false);
                    
                    //메일발송
                    fn_emailSend();
                    
                }
                ,error   : function(){
                	//로딩 호출
                    gfn_setLoading(false);
                	
                    var msgAlert = "<spring:message code='fail.alert.regist'/>";
                    alert(msgAlert);
                }
            });
        //}
    });
    
//     fn_setValphoneNumber(customerPhone); //사용자 휴대폰번호 설정
    fn_setSelectBoxBirthDay(); //생년월일 설정
});

/* [이메일인증]을 위한 이메일발송 함수 */
function fn_emailSend(){
    var customerRegNo        = $("#customerRegNo").val(); //회원OP등록번호
    var customerNameKor      = $("#customerNameKor").val(); //이름
    var customerEmail1       = $("#customerEmail1").val().replace(/\s/g, "");     //이메일앞자리
    var customerEmail2_txt   = $("#customerEmail2_txt").val().replace(/\s/g, ""); //이메일뒷자리
    var customerEmail        = customerEmail1 + '@' + customerEmail2_txt;
    
    var objParam = new Object();    
    objParam.customerRegNo    = customerRegNo;
    objParam.emailSendType    = "G016002"; //이메일 유형 - com_email_temp_info 이메일 템플릿 조회 
    objParam.emailVer         = "1"; //이메일 버전 - com_email_temp_info 이메일 템플릿 조회    
    objParam.receiverName     = customerNameKor; //받는 사람 이름(실제 이메일에 셋팅됩니다.)    
    objParam.receiverEmail    = customerEmail; //받는 사람 이메일(실제 이메일에 셋팅되고, 이 주소로 이메일이 발송됩니다.)
    objParam.senderKind       = "G017003"; //발신자 종류 - G017001:Admin, G017002:Operator, G017003:System
    objParam.receiverKind     = "G018001"; //수신자 종류 - G018001:일반사용자, G018002:기업사용자    
    objParam.receiverId       = customerRegNo; //수신자 ID
    objParam.uriContextPath   = httpsContextpath;
    
    var url = "<c:url value='/usr/mbrReg/sendMbrReg4Email.ajax'/>";
    $.ajax({
         type    : "post"
        ,url     : url
        ,data    : objParam
        ,success : function(data){
            //var msgAlert = "<spring:message code='success.alert.regist'/>";
            //var msgAlert = "이메일이 발송되었습니다.";
            //alert(msgAlert);            
            
        	//다음 스텝으로 넘어갑니다.(이메일발송은 G006005 단계에서 발송됩니다.)
            objParam.customerStep = 'G006005'; //회원 가입 Step:G006(001:본인인증, 002:공인인증서등록, 003:약관동의, 004:개인정보입력, 005:이메일인증, 006:임시비밀번호발급, 007:임시비밀번호수정)
            objParam.customerVerify = $("#customerVerify").val(); //회원검증값
            objParam.customerEmail  = customerEmail1 + '@' + customerEmail2_txt; 
            util_movePage("<c:url value='/usr/mbrReg/mbrReg.do'/>",objParam);
        }
        ,error   : function(){
            var msgAlert = "메일발송 중 문제가 발생하였습니다.\n관리자에게 문의 해 주세요.";
            alert(msgAlert);
            return;
        }
    });
} 
</script>
</head>
<body>

<div class="wrap">

    <%-- 탑과 대메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>
    <%-- 탑과 대메뉴 영역 --%>
    
    <section>
    
        <!-- location -->
        <div class="location">
            <ul>
                <li class="home"><a href="javascript:void(0);">홈</a></li>
                <li class="on">회원가입</li>
            </ul>
        </div>
        <!-- // location -->
        
        <!-- container -->
        <div class="container">
        
            <%-- lnb(좌측메뉴) 영역 --%>
            <%@ include file="/WEB-INF/view/cmm/common-include-left.jspf" %>
            <%-- lnb(좌측메뉴) 영역 --%>
            
            <!-- content -->
            <article id="content">
                <div class="sub_title">
                    <h3>회원가입</h3>                                    
                </div>
            
                <!-- con -->
                <div class="con">
                    <div class="step_area">
                        <ul>
                            <li class="pass"><div>본인인증</div></li><!-- 지나간step -->
                            <li class="pass"><div>공인인증서 등록</div></li><!-- 지나간step -->
                            <li class="pass"><div>약관동의</div></li><!-- 지나간step -->
                            <li class="on"><div>개인정보 입력</div></li><!-- 현재step -->
                            <li class="last"><div>이메일 인증</div></li>
                        </ul>
                    </div>
                    <div class="title_wrap">
                        <p class="title_01">개인정보 입력</p>
                        <p class="right">
                            <span class="icon_basic">*</span><span class="point03">필수입력사항 입니다</span>
                        </p>
                    </div>
                    
                    <!-- tbtype_form -->
                    <table class="tbtype_form">
                        <caption>이름, 아이디, 비밀번호, 비밀번호 확인, 생년월일, 성별, 이메일, 휴대폰 입력</caption>
                        <colgroup>
                            <col style="width:20%;">
                            <col style="width:*;">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th scope="row">이름<span class="icon_basic">*필수입력</span></th>
                            <td>
                                <c:out value="${paramVO.customerNameKor}" />
                                <input type="hidden" name="customerIdCheck" id="customerIdCheck" value="N" />
                                <input type="hidden" name="customerRegNo"       id="customerRegNo"       value="<c:out value="${paramVO.customerRegNo}" />" />
                                <input type="hidden" name="customerVerify"      id="customerVerify"      value="<c:out value="${paramVO.customerVerify}" />" />
                                <input type="hidden" name="customerNameKor"     id="customerNameKor"     value="<c:out value="${paramVO.customerNameKor}" />" />
                                <input type="hidden" name="customerRegNoPrefix" id="customerRegNoPrefix" value="<c:out value="${paramVO.customerRegNoPrefix}" />" />
                                <input type="hidden" name="customerEmail" id="customerEmail" />
                                <input type="hidden" name="customerEmail2" id="customerEmail2" />
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">
                                <label for="user_id">아이디<span class="icon_basic">*필수입력</span></label>
                            </th>
                            <td>
                                <input type="text" name="customerId" id="customerId" style="width:238px;ime-mode:disabled;" maxlength="15" onkeydown="util_engonly(event);" />
                                &nbsp;
                                <a href="javascript:void(0);" id="btnChkId" class="btn_type3">중복확인</a>
                                <a href="javascript:void(0);" id="btnIdReInput" style="display:none;" class="btn_type3">아이디재입력</a>
                                <span class="info_msg">영문, 숫자 조합, 소문자 입력4~15자 이내 <br>한글입력 불가</span>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">
                                <label for="user_pw">비밀번호<span class="icon_basic">*필수입력</span></label>
                            </th>
                            <td>
                                <input type="password" name="customerPassword1" id="customerPassword1" style="width:238px;" maxlength="15" placeHolder="ex)Qetu101!@" />
                                <span class="info_msg">8~15자의 영문 대/소문자, 숫자, 특수문자 중 3개 이상의 조합</span>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">
                                <label for="user_pw2">비밀번호 확인<span class="icon_basic">*필수입력</span></label>
                            </th>
                            <td>
                                <input type="password" name="customerPassword2" id="customerPassword2" style="width:238px;"  maxlength="15" />
                            </td>
                        </tr>
                        <tr>
                            <th scope="row"><label for="user_birth">생년월일</label></th>
                            <td>
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
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">성별</th>
                            <td>
                                <c:forEach var="sexList" items="${sexList}" varStatus="status">
                                    <input type="radio" name="customerSex" id="customerSex_${sexList.system_code}" class="radio_box" value="${sexList.system_code}">
                                    <label for="customerSex_${sexList.system_code}">${sexList.code_name_kor}</label>
                                </c:forEach>
                            </td>
                        </tr>
            
                        <tr>
                            <th scope="row">
                                <label for="user_email">이메일<span class="icon_basic">*필수입력</span></label>
                            </th>
                            <td>
                                <input type="text" name="customerEmail1" id="customerEmail1" maxlength="20" style="width:154px;ime-mode:disabled;" />
                                <span class="dot">@</span>
                                <input type="text" name="customerEmail2_txt" id="customerEmail2_txt" title="이메일주소뒷부분" maxlength="20" style="width:174px;ime-mode:disabled;" />
                                <span class="sel_box1">
                                    <select name="customerEmail2_slt" id="customerEmail2_slt" title="이메일주소 선택">
                                        <option value="">직접입력</option>
                                        <c:forEach var="emailList" items="${emailList}" varStatus="status">
                                            <option value="${emailList.code_name_kor}">${emailList.code_name_kor}</option>
                                        </c:forEach>
                                    </select>
                                </span>    
                                
                                <div class="info_bottom">
                                    <p class="info_msg">※ 입력된 이메일 주소로 회원가입 인증메일이 발송됩니다. 정확한 이메일 주소를 입력하시기 바랍니다.</p>
                                    <input type="checkbox" name="customerEmailReceiveYn_chk" id="customerEmailReceiveYn_chk">
                                    <label for="customerEmailReceiveYn_chk" class="chk_box">이메일 수신 동의 (약관변경,신규 서비스)</label>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">
                                <label for="user_phone">휴대폰<span class="icon_basic">*필수입력</span></label>
                            </th>
                            <td>
                                <span class="sel_box1">
                                    <select name="customerPhone1" id="customerPhone1" title="휴대폰번호 첫자리 선택">
                                        <option value="">선택</option>
                                        <c:forEach var="hpList" items="${hpList}" varStatus="status">
                                            <option value="${hpList.code_name_kor}">${hpList.code_name_kor}</option>
                                        </c:forEach>
                                    </select>
                                </span>    
                                <span class="dot">-</span>
                                <input type="text" name="customerPhone2" id="customerPhone2" class="text num_only" title="휴대폰번호 두번째자리" maxlength="4" onkeydown="util_numberonly(event);" style="width:97px;ime-mode:disabled" />
                                <span class="dot">-</span>
                                <input type="text" name="customerPhone3" id="customerPhone3" class="text num_only" title="핸드폰번호 세번째자리" maxlength="4" onkeydown="util_numberonly(event);" style="width:97px;ime-mode:disabled" />
                            </td>
                        </tr>
                        
                        <tr>
                            <th scope="row">스마트  OTP 등록</th>
                            <td>                                
                                <input type="checkbox" id="customerOtpYn" name="customerOtpYn"><label for="customerOtpYn" class="chk_box">스마트 OTP사용</label>      
                                <div class="mt10">
                                    <input type="text" id="customerSendOtpId" name="customerSendOtpId" placeholder="OTP 사용 등록번호 입력" style="width:218px;" disabled="disabled"
                                           onkeydown="javascript:if(event.keyCode == 13) btnOtp.click();"
                                    >&nbsp;
                                    <a href="javascript:void(0);" id="btnOtp" class="btn_type3 type2">확인</a>
                                    <div class="info_bottom">
                                        <p class="info_msg">
                                            ※ 보안 강화를 위해 스마트 OTP 사용을 체크하여 주세요.<br>
                                            <br>
                                             <등록 방법><br>
                                             1. Smartkey Pass앱에 보이는 '사이트 인증 등록' 번호를 입력하시고 ‘확인’ 버튼을 클릭하여 주세요.<br>
                                             2. Smartkey Pass앱에 하단에 'OK' 버튼을 누르면 등록 됩니다.<br>
                                            ※ 스마트 OTP를 사용하기 위해서는 스마트폰에 Smartkey Pass앱이 설치되어 있어야 합니다.<br>
                                            ※ 앱 정보 : <a href="https://play.google.com/store/apps/details?id=kr.co.everspin.smartkeypass" target="_blank"> Android용 </a> / <a href="https://itunes.apple.com/kr/app/seumateu-ki-paeseu-smart-key/id1135836261?l=en&mt=8#" target="_blank"> iOS용 </a> 
                                        </p>
                                    </div>
                                    <input type="hidden" id="customerOtpId" name="customerOtpId" />
                                </div>
                            </td>
                        </tr>
                        
                        </tbody>
                    </table>                    
                    <!-- // tbtype_form -->
            
                    <div class="btn_area">
<!--                         <a href="javascript:void(0);" id="btnCancel" class="btn_type2">취소</a> -->
                        <a href="javascript:void(0);" id="btnNext" class="btn_type1">다음</a>
                    </div>
                    
                    <div class="info_box type2">
                        <div class="tit">
                            <p class="icon_tip">안전한 비밀번호 변경</p>
                        </div>
                        <div class="txt">
                            <ul class="list_type01">
                                <li>영문/숫자/기호 등의 조합으로 비밀번호를 구성해 주세요.</li>
                                <li>연속숫자, 연속문자, 동일숫자 또는 이름은 비밀번호로 사용할 수 없습니다.</li>
                                <li>쉽게 유추해 낼 수 있는 아이디/생일/핸드폰번호 등 개인정보와 관련된 문자, 숫자는 사용하지 않는 것이 좋습니다.</li>
                            </ul>
                        </div>
                    </div>
                    
                </div>
                <!-- // con -->
                
            </article>
            <!-- // content -->
        
        </div>
        <!-- // container -->
        
    </section>
    <!-- 공인인증서 iframe -->
    <form name="form" method="post">
        <table>
            <tr>
                <td>
                    <input type="text" id="t_dn" name="t_dn" style="width:200; display:none;" />
                    <input type="text" id="t_rdata" name="t_rdata" style="width:200; display:none;" />
                    <textarea id="t_signdata" name="t_signdata" style="height:100; width:300; display:none;"></textarea>
                </td>
            </tr>
        </table>
    </form>
    <iframe id="yettie_library_iframe" name="yettie_library_iframe" src="<c:url value='/js/cmm/SKCertService/app/views/library.html'/>" title="library" width="0px" height="0px" scrolling="no" style="top: 50%; left: 50%; margin-top: -200px; margin-left: -195px; position: fixed; z-index: 0; border: none;"></iframe>
    
    <%-- footer --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-footer.jspf" %>
    <%-- footer --%>

</div>

</body>
</html>