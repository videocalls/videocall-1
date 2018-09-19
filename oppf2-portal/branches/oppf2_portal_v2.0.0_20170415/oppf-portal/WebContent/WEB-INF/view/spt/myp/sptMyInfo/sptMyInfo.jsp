<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : sptMyInfo.jsp
 * @Description : [개인회원정보] 등록수정
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.05.10  유제량        최초  생성
 *  2016.06.22  신동진        OTP 추가
 * </pre>
 *
 * @author 포털 유제량 
 * @since 2016.05.10
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
var customerEmail = '<c:out value="${paramVO.customerEmail}" />';
//var customerSexName = '<c:out value="${paramVO.customerSexName}" />';

var customerBirthDay = '<c:out value="${paramVO.customerBirthDay}" />';

/*******************************************
 * 기능 함수
 *******************************************/

/* [개인정보변경]확인 유효성 검증 */
function fn_InfoChangeConfrm(){
	var userId = $("#userId").val(); //사용자의 아이디
	var customerPassword1 = $("#customerPassword1").val(); //비밀번호
    var customerPassword2 = $("#customerPassword2").val(); //비밀번호확인
    var customerEmail1 = $("#customerEmail1").val(); //이메일앞자리
    var customerEmail2_txt = $("#customerEmail2_txt").val(); //이메일뒷자리
    var customerPhone1 = $("#customerPhone1").val(); //전화번호 국번자리
    var customerPhone2 = $("#customerPhone2").val(); //전화번호 가운데 자리
    var customerPhone3 = $("#customerPhone3").val(); //전화번호 맨 뒷자리
    
    if(customerPassword1 != "" || customerPassword2 != ""){
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
        if(userId == customerPassword1) {
            alert("아이디 와 비밀번호가 동일합니다.\r\n비밀번호를 다시 입력하십시오.");
            $("#customerPassword1").focus();
            return false;
        }
        
        //[비밀번호]에 [아이디]가 포함되어 있는지 체크
        if(userId.substring(0,4) == customerPassword1.substring(0,4)){
            alert("아이디 와 비밀번호 앞4자리가 동일 합니다.\r\n비밀번호를 다시 입력하십시오.");
            $("#customerPassword1").focus();
            return false;
        }
    }
    
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

/* [개인정보변경]비밀번호 변경 정책(동일 비밀번호 거부 처리) */
function fn_PwChangeConfrm(){
	var url = "<c:url value='/spt/myp/sptMyInfo/checkPw.ajax'/>";
	var userId = $("#userId").val(); //사용자의 아이디	
    var customerPassword1 = $("#customerPassword1").val(); //비밀번호
    var customerPassword2 = $("#customerPassword2").val(); //비밀번호확인
    
    var objParam = new Object();
    objParam.customerId = userId;    
    objParam.customerPassword = customerPassword1;
    
    //마이페이지 회원정보에서 비밀번호와 비밀번호확인을 변경하는 것은 필수입력사항이 아니기 때문에
    if(customerPassword1 != "" || customerPassword2 != ""){ //회원이 비밀번호를 변경하기 위해 비밀번호란에 입력한 경우
    	//로딩start
        gfn_setLoading(true, "비밀번호를 확인 중입니다.");
        
        $.ajax({
            type    : "post"
           ,url     : url
           ,data    : objParam
           ,success : function(data){
               //로그인 처리
               if(data.error == -1){
                   fn_login();
                   return;
               }
               
               //로딩end
               gfn_setLoading(false);
               
               var checkPw = data.checkPw;  
               if(checkPw == "1"){
                   alert("비밀번호가 기존 비밀번호와 일치합니다.\n다른 비밀번호로 변경하여 주시기 바랍니다.");
                   $("#customerPassword1").val("");
                   $("#customerPassword2").val("");
                   $("#customerPassword1").focus();                   
               }else{
                   //성공시 회원정보를 수정합니다.
            	   fn_InfoChange();
               }
           }
           ,error   : function(){
               //로딩end
               gfn_setLoading(false);
               //alert("정보수정 실패");               
           }
       });
    }else{
    	//[개인정보변경]개인정보변경 처리
    	fn_InfoChange();
    }        
}

/* [개인정보변경]개인정보변경 처리 */
function fn_InfoChange(){    
    var msgConfirm = "회원정보를 "+"<spring:message code='confirm.modify.msg'/>"; //"수정하시겠습니까?"
    if( confirm(msgConfirm) ){
         var url = "<c:url value='/spt/myp/sptMyInfo/updateSptMyInfo.ajax'/>";
         var userId = $("#userId").val();
         var customerRegNo = $("#customerRegNo").val();
         var customerPassword1 = $("#customerPassword1").val();
         var customerPassword2 = $("#customerPassword2").val();
         var customerEmail1 = $("#customerEmail1").val();
         var customerEmail2_txt = $("#customerEmail2_txt").val();
         var customerPhone1 = $("#customerPhone1").val();
         var customerPhone2 = $("#customerPhone2").val();
         var customerPhone3 = $("#customerPhone3").val();
//          var customerBirthDay = $("#customerBirthDay").val();
         var customerBirthDayYYYY = $("#customerBirthDayYYYY option:selected").val(); //년
         var customerBirthDayMM   = $("#customerBirthDayMM option:selected").val();   //월
         var customerBirthDayDD   = $("#customerBirthDayDD option:selected").val();   //일
         var customerBirthDay = customerBirthDayYYYY+''+customerBirthDayMM+''+customerBirthDayDD;
         var customerSex = $("#customerSex_slt :selected").val();
         
         if(util_chkReturn(customerBirthDayYYYY, "s") != "" || 
        	util_chkReturn(customerBirthDayMM, "s") != "" || 
        	util_chkReturn(customerBirthDayDD, "s") != ""){
             if(!(util_chkReturn(customerBirthDayYYYY, "s") != "" && 
        	      util_chkReturn(customerBirthDayMM, "s") != "" && 
        	      util_chkReturn(customerBirthDayDD, "s") != "")){
        	     alert("<spring:message code='errors.required.select' arguments='생년월일'/>");
        	     return false;
        	 }
         }
         
         var objParam = new Object();
         objParam.customerId = userId;
         objParam.customerRegNo = customerRegNo;
         objParam.customerPassword = customerPassword1;
         if(customerPassword1 != customerPassword2){
             alert("비밀번호와 비밀번호확인이 다릅니다.");
             $("#customerPassword2").focus();
             return false;
         }
         objParam.customerEmail    = customerEmail1 + "@" + customerEmail2_txt;
         objParam.customerPhone   = customerPhone1+"-"+customerPhone2+"-"+customerPhone3;
         objParam.customerBirthDay   = customerBirthDay;
         objParam.customerSex   = customerSex;
                  
         //로딩start
         gfn_setLoading(true, "회원정보를 변경 중입니다.");
         
         $.ajax({
             type    : "post"
            ,url     : url
            ,data    : objParam
            ,success : function(data){ 
                //(6/19 이민우 상무님)정보 수정 경우 '개인 정보 변경에 따라 로그아웃 하겠습니다. 다시 로근인 하여 주십시오' 등으로 alert 띄우고 비밀 번호 변경은 로그인 페이지 기타 변경은 마이페이지로 이동이 맞음
                //SptMyInfoController.java에서 로그아웃처리를 하였기 때문에 로그인확인 로직은 주석처리합니다.
                //로그인 처리 ->
                /* if(data.error == -1){
                    fn_login();
                    return;
                } */
                
                //로딩end
                gfn_setLoading(false);
                
                var resultDetail = data.resultDetail;                   
                fn_moveList(resultDetail);
            }
            ,error   : function(){
                //로딩end
                gfn_setLoading(false);
                //alert("정보수정 실패");
            }
        });
         
    }
}
 
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
 
/* [이메일]셋팅 함수 */
function fn_setValemail(pEmail){
	var pEmail_arr = pEmail.split("@");
	$("#customerEmail1").val(pEmail_arr[0]);
	$("#customerEmail2_txt").val(pEmail_arr[1]);
}

/* [성별]셋팅 함수 */
function fn_setValsexName(pSexName){
	$("#customerSexName").val(pSexName);
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
    
    if(util_chkReturn(customerBirthDay, "s") != ""){
    	var customerBirthDayArr = customerBirthDay.split(".");
    	if(customerBirthDayArr.length == 3){
    	    $("#customerBirthDayYYYY").val(customerBirthDayArr[0]);
    	    $("#customerBirthDayMM").val(customerBirthDayArr[1]);
    	    $("#customerBirthDayDD").val(customerBirthDayArr[2]);
    	}else if(customerBirthDayArr.length == 1){
    		if(customerBirthDay.length == 8){
    			$("#customerBirthDayYYYY").val(customerBirthDay.substring(0,4));
    			$("#customerBirthDayMM").val(customerBirthDay.substring(4,6));
    			$("#customerBirthDayDD").val(customerBirthDay.substring(6,8));
    		}
    	}
    }
}

/* 이메일 설정 함수 */
function fn_setSelectBoxEmail(){
    var customerEmailArr = customerEmail.split("@");
    $("#customerEmail1").val(customerEmailArr[0]);
    if(customerEmailArr.length == 2){
        $("#customerEmail2_slt").val(customerEmailArr[1]);
        $("#customerEmail2_txt").val(customerEmailArr[1]);
    }
    
    if( $("#customerEmail2_slt :selected").val() == "" || $("#customerEmail2_slt :selected").val() == undefined ){
    	$("#customerEmail2_slt").val("");
    }
    
//     $("input[name='customerEmail2_slt']").each(function(idx){
        
//     });
}

/*******************************************
 * 이벤트 함수
 *******************************************/

/* 이동함수 */
function fn_moveList(resultDetail){
	alert("개인 정보 변경에 따라 로그아웃 하겠습니다. 다시 로그인 하여 주십시오");
	var customerPassword1 = $("#customerPassword1").val(); //비밀번호
    var customerPassword2 = $("#customerPassword2").val(); //비밀번호확인
	var objParam = new Object();
	//(6/19 이민우 상무님)정보 수정 경우 '개인 정보 변경에 따라 로그아웃 하겠습니다. 다시 로근인 하여 주십시오' 등으로 alert 띄우고, 비밀 번호 변경은 로그인 페이지 기타 변경은 마이페이지로 이동이 맞음
	if(customerPassword1 != "" || customerPassword2 != ""){
		if(resultDetail.customerPasswordYn == "Y"){ //기타변경
	        //메인페이지로 이동(기타변경의 경우 마이페이지로 이동할 것을 말씀하셨지만 마이페이지는 로그인이 필요한 페이지라서 메인페이지로 보냈습니다.)
	        var uri = '<c:url value="/spt/cmm/mainView.do"/>';
	        document.location.href = uri;
	    }else{ //비밀번호변경
	        //로그인페이지로 이동
	        var uri = '<c:url value="/spt/cmm/loginView.do"/>';
	        document.location.href = uri;           
	    }	
	}else{
		//메인페이지로 이동 - customerPassword1가 공백값이라면 customerPasswordYn 값이 있을 수 없고 기타정보를 변경한 상황입니다.
        var uri = '<c:url value="/spt/cmm/mainView.do"/>';
        document.location.href = uri;
	}
} 

<%-- 로그인 처리 공통 함수 --%>
function fn_login(){
    var url = "<c:url value='/spt/myp/sptMyInfo/sptMyPwConfrm.do'/>";
    var param = new Object();
    param.paramMenuId = "05004";
    
    gfn_loginNeedMove(url, param);
}

/* 화면 로드 처리 */
$(document).ready(function(){
	<%-- 로그인 처리 --%>
    <c:if test="${empty LoginVO}">
        fn_login();
		return;
    </c:if>
    
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
    	
	/* otp 사용 클릭 */
	$("#customerOtpYn").change(function(){
		var saveCustomerOtpYn = "${paramVO.customerOtpYn}";
		var customerSendOtpId = $("#customerSendOtpId").val();
		var customerOtpId = $("#customerOtpId").val();
		
		$("#customerSendOtpId").val("");
        $("#customerOtpId").val("");
        
		//db저장되어 있던 경우
		if(saveCustomerOtpYn == "Y"){
			//체크 시 -> 재등록
            if($(this).is(":checked")){
                $("#customerSendOtpId").removeAttr("disabled");
                $("#btnOtp").html("재등록");
                
                $("#customerOtpDiv").show();
            //해제 시 -> 폐기
            }else{
                $("#customerSendOtpId").attr("disabled", "disabled");
                
                $("#customerOtpDiv").hide();
                
                if(customerOtpId != ""){
	                //공인인증서 체크
	                certsign('delete');     
                }
            }
			
		//최초 또는 폐기한 경우
		}else{
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
		}
	});
	
	/* otp 확인 클릭 시 */
    $("#btnOtp").click(function(){
    	if($("#btnOtp").html() == "변경"){
    		var saveCustomerOtpYn = "${paramVO.customerOtpYn}";
    		if(saveCustomerOtpYn == "Y"){
    			$("#btnOtp").html("재등록");
    		}else{
    			$("#btnOtp").html("확인");
    		}
    		
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
    $("#btnNextChange").click(function(){
    	var uri = '<c:url value="/spt/cmm/mainView.do"/>';
        document.location.href = uri;
    });
	
	/* [정보수정]버튼 클릭 시 호출 */
    $("#btnInfoChange").click(function(){
    	if( !fn_InfoChangeConfrm() ){ //유효성검증호출
            return false;
        }
    	fn_PwChangeConfrm(); //2016.07.07 이준형 과장님의 비밀번호 변경 정책(동일 비밀번호 거부 처리) 적용        
    });
	
	fn_setValphoneNumber(customerPhone);
// 	fn_setValemail(customerEmail);
	//fn_setValsexName(customerSexName);
	fn_setSelectBoxBirthDay(); //생년월일 설정
	fn_setSelectBoxEmail(); //이메일 설정
	
});

<%-- 처리 실패시 reset --%>
function fn_resetOtp(){
	var saveCustomerOtpYn = "${paramVO.customerOtpYn}";
	var customerOtpYn = $("#customerOtpYn").is(":checked")? false:true;
	
	if(saveCustomerOtpYn == "Y"){
        //체크 시 -> 재등록
        if(customerOtpYn){
            $("#customerSendOtpId").removeAttr("disabled");
            $("#btnOtp").html("재등록");
            
            $("#customerOtpDiv").show();
            
        //해제 시 -> 폐기
        }else{
            $("#customerSendOtpId").attr("disabled", "disabled");
            
            $("#customerOtpDiv").hide();             
        }
        
    //최초 또는 폐기한 경우
    }else{
        $("#btnOtp").html("확인");
        //체크 시
        if(customerOtpYn){
            $("#customerSendOtpId").removeAttr("disabled");
            
            $("#btnOtp").removeClass("type2");
        }else{
            $("#customerSendOtpId").attr("disabled", "disabled");
            
            $("#btnOtp").addClass("type2");
        }
    }
	
	if(customerOtpYn){
		$("#customerOtpYn").prop("checked", true);
		
		$("#customerSendOtpId").val("");
        $("#customerOtpId").val("temp");
	}else{
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
    param.loginCheckYn = "Y";   //로그인 체크여부
    
    <%-- 공통 ajax 호출 --%>
    util_ajaxPageJson(moveUrl, param, callBackFunc);
}
function fn_registOtpCallBack(data){
	if(util_chkReturn(data, "s") == "") {
		//로딩 호출
	    gfn_setLoading(false);
		
        alert("내부서버 오류입니다.\n관리자에게 문의해 주세요.\n[400]");
        fn_resetOtp();
        return;
    }
	
	//로그인 필요
    if(data.error == -1){
        fn_login();
        return;
    }
	
    //signature 생성 오류
    if(data.error == 1){
        alert("내부서버 오류입니다.\n관리자에게 문의해 주세요.\n[signature]");
        fn_resetOtp();
        return;
    }
	
    //로딩 호출
    gfn_setLoading(false);
	
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
    param.loginCheckYn = "Y";   //로그인 체크여부
    
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
    
    //로그인 필요
    if(data.error == -1){
        fn_login();
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

</script>
</head>
<body>

<input type="hidden" id="userId" name="userId" value="${ LoginVO.id }" />
<input type="hidden" id="customerRegNo" name="customerRegNo" value="${ LoginVO.customer_reg_no }" />

<%-- otp 관련 정보 --%>
<input type="hidden" id="oldCustomerOtpId" name="oldCustomerOtpId" value="${paramVO.customerOtpId}" />

<div class="wrap">

    <%-- 탑과 메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>
    <%-- 탑과 메뉴 영역 --%>

    <!-- section -->
    <section>
        <!-- location -->
        <div class="location">
            <ul>
                <li class="home"><a href="javascript:void(0);">홈</a></li>
                <li><a href="javascript:void(0);">마이 페이지</a></li>
                <li class="on">회원정보</li>
            </ul>
        </div>
        <!-- // location -->

        <div class="container">
            <%-- lnb(좌측메뉴) 영역 --%>
            <%@ include file="/WEB-INF/view/cmm/common-include-left.jspf" %>
            <%-- lnb(좌측메뉴) 영역 --%>

            <!-- content -->
            <article id="content">

                <div class="sub_title">
                    <h3>회원정보</h3>
                </div>

                <!-- con -->
                <div class="con">
                    
                    <div class="title_wrap">
                        <p class="title_01">개인정보 입력</p>
                        <p class="right"><span class="icon_basic">*</span><span class="point02">필수입력사항 입니다</span></p>
                    </div>
                    
                    <!-- tbtype_form -->
                    <table class="tbtype_form">
                        <caption>이름, 아이디, 비밀번호, 비밀번호 확인, 생년월일, 성별, 이메일, 휴대폰</caption>
                        <colgroup>
                            <col style="width:20%;">
                            <col style="width:80%;">
                        </colgroup>
                        <tbody>
                            <tr>
                                <th scope="row">이름</th>
                                <td>
	                                <c:out value="${paramVO.customerNameKor}" />
	                                <input type="hidden" name="customerNameKor" id="customerNameKor" value="" style="width:35px;ime-mode:active" />
                                </td>
                            </tr>
                            <tr>
                                <th scope="row">아이디</th>
                                <td>
                                    <c:out value="${paramVO.customerId}" />
                                    <input type="hidden" name="customerId" id="customerId" value="" style="width:35px;ime-mode:active" />
                                </td>
                            </tr>
                            <tr>
                                <!-- <th scope="row"><label for="user_pw">비밀번호<span class="icon_basic">*필수입력</span></label></th> -->
                                <th scope="row">비밀번호</th>
                                <td>
                                    <input type="password" name="customerPassword1" id="customerPassword1" style="width:238px;" tabindex="1"
                                           onkeydown="javascript:if(event.keyCode == 13) $('#customerPassword2').focus();" 
                                           value=""
                                    />
                                    <span class="info_msg">* 8~15자의 영문 대/소문자, 숫자, 특수문자 중 3개 이상의 조합</span>                                                                        
                                </td>                       
                            </tr>
                            <tr>
                                <!-- <th scope="row"><label for="user_pw2">비밀번호 확인<span class="icon_basic">*필수입력</span></label></th> -->
                                <th scope="row">비밀번호 확인</th>                                
                                <td>
                                    <input type="password" name="customerPassword2" id="customerPassword2" style="width:238px;" tabindex="2"
                                           onkeydown="javascript:if(event.keyCode == 13) $('#customerBirthDay').focus();" 
                                           value=""
                                    />
                                    <span class="info_msg">사용자의 아이디와 동일한 비밀번호는 사용불가</span>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row">생년월일</th>                                
                                <td>
                                    <input type="hidden" name="customerBirthDay" id="customerBirthDay" tabindex="3"
                                           onkeydown="javascript:if(event.keyCode == 13) $('#customerEmail1').focus();" 
                                           value="<c:out value="${paramVO.customerBirthDay}" />"/>
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
                                    <c:out value="${paramVO.customerSexName}" />
                                    <input type=hidden name="customerSex" id="customerSex" value="<c:out value="${paramVO.customerSex}" />" style="width:35px;ime-mode:active"/>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row"><label for="user_email">이메일<span class="icon_basic">*필수입력</span></label></th>
                                <td>
                                    <input type="hidden" name="customerEmail" id="customerEmail" />
                                    <input type="text" name="customerEmail1" id="customerEmail1" class="text" maxlength="20" style="width:154px;" tabindex="4"
                                           onkeydown="javascript:if(event.keyCode == 13) $('#customerEmail2_txt').focus();" 
                                    />
                                    <span class="dot">@</span>
                                    <input type="text" name="customerEmail2_txt" id="customerEmail2_txt" class="text" title="이메일주소뒷부분" maxlength="20" style="width:174px;" tabindex="5"
                                           onkeydown="javascript:if(event.keyCode == 13) $('#customerPhone1').focus();"
                                    />
                                    <span class="sel_box1">
                                        <select name="customerEmail2_slt" id="customerEmail2_slt" title="이메일주소 선택">
                                            <option value="">직접입력</option>
                                            <c:forEach var="emailList" items="${emailList}" varStatus="status">
                                                <option value="${emailList.code_name_kor}">${emailList.code_name_kor}</option>
                                            </c:forEach>
                                        </select>
                                        <!-- <input type="hidden" name="customerEmail2" id="customerEmail2" /> -->
                                    </span>
                                    <!-- <a href="#none" class="btn_type3">인증하기</a> -->
                                </td>
                            </tr>
                            <tr>
                                <th scope="row"><label for="user_phone">휴대폰<span class="icon_basic">*필수입력</span></label></th>
                                <td>
                                    <span class="sel_box1">                                        
                                        <input type="hidden" name="customerPhone" id="customerPhone" value="<c:out value="${paramVO.customerPhone}" />" title="휴대폰번호 첫자리 선택"/>
                                        <select name="customerPhone1" id="customerPhone1" title="핸드폰번호 앞자리" tabindex="6">
                                            <option value="">선택</option>
                                            <c:forEach var="hpList" items="${hpList}" varStatus="status">
                                                <option value="${hpList.code_name_kor}">${hpList.code_name_kor}</option>
                                            </c:forEach>
                                        </select>
                                    </span> 
                                    <span class="dot">-</span>
                                    <input type="text" name="customerPhone2" id="customerPhone2" class="text num_only" title="핸드폰번호 중간자리" maxlength="4" style="width:97px;" tabindex="7" 
                                           onkeydown="util_numberonly(event); javascript:if(event.keyCode == 13) $('#customerPhone3').focus();"
                                    />
                                    <span class="dot">-</span>                                    
                                    <input type="text" name="customerPhone3" id="customerPhone3" class="text num_only" title="핸드폰번호 끝자리" maxlength="4"  style="width:97px;" tabindex="8"
                                           onkeydown="util_numberonly(event); javascript:if(event.keyCode == 13) btnInfoChange.click();"
                                    />
                                    <!-- <a href="#none" class="btn_type3">인증하기</a> -->
                                </td>
                            </tr>
                            
                            <%-- otp 정보 --%>
                            <c:choose>
                                <%-- 미 등록 시 --%>
                                <c:when test="${paramVO.customerOtpYn eq 'N'}" >
                                    <tr>
                                        <th scope="row">스마트  OTP 등록</th>
                                        <td>                                
                                            <input type="checkbox" id="customerOtpYn" name="customerOtpYn"><label for="customerOtpYn" class="chk_box">OTP사용</label>      
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
                                </c:when>
                                <%-- 등록 시 --%>
                                <c:otherwise>
                                    <tr>
                                        <th>스마트  OTP</th>
                                        <td>                                
                                            <input type="checkbox" id="customerOtpYn" name="customerOtpYn" checked="checked"><label for="customerOtpYn" class="chk_box">OTP사용</label>      
                                            <div class="info_bottom">
                                                <%--<p class="info_msg">※ OTP사용 체크를 해지하면 OTP사용이 중단 됩니다. 즉 id/pw만으로 로그인이 되면, 이는 보안이 취약할 수 있습니다.</p>--%>
                                                <p class="info_msg">※ OTP사용여부를 해지하면 OTP사용이 중단되며, 아이디와 비밀번호만으로 서비스 사용시 보안에 취약할 수 있습니다.</p>
                                            </div>
                                            
                                            <div id="customerOtpDiv" class="otpBox" style="display: none;">
	                                            <label for="otp_re">OTP 재등록</label>
	                                            <div class="mt10">
	                                                <input type="text" id="customerSendOtpId" name="customerSendOtpId" placeholder="OTP 사용 등록번호 입력" style="width:218px;"
	                                                       onkeydown="javascript:if(event.keyCode == 13) btnOtp.click();"
	                                                >&nbsp;
	                                                <a href="javascript:void(0);" id="btnOtp" class="btn_type3">재등록</a>
	                                                <div class="info_bottom">
	                                                    <p class="info_msg">※ 스마트OTP앱이 설치 된 스마트폰 분실 시 OTP 통한 로그인을 이용하시려면 스마트 OTP를 재등록 하셔야 합니다.</p>
	                                                </div>
	                                                <input type="hidden" id="customerOtpId" name="customerOtpId" value="${paramVO.customerOtpId}" />
	                                            </div>
	                                         </div>   
                                        </td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                            
                            <!-- 가입일 -->
                            <tr>
                                <th scope="row">가입일</th>
                                <td>
                                    <c:out value="${paramVO.customerRegDate}" />
                                    <input type="hidden" name="customerRegDate" id="customerRegDate" value="<c:out value="${paramVO.customerRegDate}" />" />                                    
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <!-- // tbtype_form -->
                    
                    <!-- btn_area -->
                    <div class="btn_area">
                        <a href="javascript:void(0);" id="btnNextChange" class="btn_type2">취소</a>
                        <a href="javascript:void(0);" id="btnInfoChange" class="btn_type1">정보수정</a>
                    </div>
                    <!-- // btn_area -->
                    
                </div>
                <!-- // con -->

            </article>
            <!-- // content -->
        </div>
    </section>
    <!-- section -->
    
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