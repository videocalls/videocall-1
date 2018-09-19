<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : asumAcntIsu.jsp
 * @Description : [핀테크서비스신청:정보제공 동의]
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.05.23  이환덕        최초  생성
 * </pre>
 *
 * @author 포털 이환덕 
 * @since 2016.05.23
 * @version 1.0
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>
<OBJECT classid="CLSID:EC5D5118-9FDE-4A3E-84F3-C2B711740E70" codeBase="http://www.signkorea.com/SKCommAX.cab#version=9,9,1,9" id="CertManX" width="0" height="0"></OBJECT>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script type="text/javascript" src="<c:url value='/js/cmm/SKCertService/app/library/json3.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/cmm/SKCertService/app/library/iecompatibility.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/cmm/SKCertService/app/vestsign.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/cmm/SKCertService/app/js/koscom.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/cmm/SKCertService/app/library/dragiframe.js'/>"></script>

<script type="text/javascript" src="<c:url value='/js/cmm/jquery.printArea.js'/>"></script>
<script type="text/javascript">
/**
 * 설명       : base64 encode, decode
 * 사용방식     : gfnBase64 
 * 주의       : 
 * 리턴       : 
 */
var gfnBase64 = {
    // private property
    _keyStr : "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/="

    // public method for encoding
    ,encode : function(input){
        var output = "";
        var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
        var i = 0;
        input = gfnBase64._utf8_encode(input);
        while(i < input.length){
            chr1 = input.charCodeAt(i++);
            chr2 = input.charCodeAt(i++);
            chr3 = input.charCodeAt(i++);

            enc1 = chr1 >> 2;
            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
            enc4 = chr3 & 63;

            if(isNaN(chr2)){
                enc3 = enc4 = 64;
            }else if(isNaN(chr3)){
                enc4 = 64;
            }
            output = output +
                this._keyStr.charAt(enc1) + this._keyStr.charAt(enc2) +
                this._keyStr.charAt(enc3) + this._keyStr.charAt(enc4);
        }
        return output;
    }

    // public method for decoding
    ,decode : function(input){
        var output = "";
        var chr1, chr2, chr3;
        var enc1, enc2, enc3, enc4;
        var i = 0;
        input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
        while(i < input.length){
            enc1 = this._keyStr.indexOf(input.charAt(i++));
            enc2 = this._keyStr.indexOf(input.charAt(i++));
            enc3 = this._keyStr.indexOf(input.charAt(i++));
            enc4 = this._keyStr.indexOf(input.charAt(i++));
            chr1 = (enc1 << 2) | (enc2 >> 4);
            chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
            chr3 = ((enc3 & 3) << 6) | enc4;
            output = output + String.fromCharCode(chr1);
            if(enc3 != 64){
                output = output + String.fromCharCode(chr2);
            }
            if(enc4 != 64){
                output = output + String.fromCharCode(chr3);
            }
        }
        output = gfnBase64._utf8_decode(output);
        return output;
    }

    // private method for UTF-8 encoding
    ,_utf8_encode : function (string) {
        string = string.replace(/\r\n/g,"\n");
        var utftext = "";
        for(var n = 0; n < string.length; n++){
            var c = string.charCodeAt(n);
            if(c < 128){
                utftext += String.fromCharCode(c);
            }else if((c > 127) && (c < 2048)) {
                utftext += String.fromCharCode((c >> 6) | 192);
                utftext += String.fromCharCode((c & 63) | 128);
            }else{
                utftext += String.fromCharCode((c >> 12) | 224);
                utftext += String.fromCharCode(((c >> 6) & 63) | 128);
                utftext += String.fromCharCode((c & 63) | 128);
            }
        }
        return utftext;
    }

    // private method for UTF-8 decoding
    ,_utf8_decode : function (utftext) {
        var string = "";
        var i = 0;
        var c = c1 = c2 = 0;
        while( i < utftext.length ){
            c = utftext.charCodeAt(i);
            if(c < 128){
                string += String.fromCharCode(c);
                i++;
            }else if((c > 191) && (c < 224)){
                c2 = utftext.charCodeAt(i+1);
                string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
                i += 2;
            }else{
                c2 = utftext.charCodeAt(i+1);
                c3 = utftext.charCodeAt(i+2);
                string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
                i += 3;
            }
        }
       return string;
    }

    ,URLEncode : function (string){
        return escape(this._utf8_encode(string));
    }

    // public method for url decoding
    ,URLDecode : function (string){
        return this._utf8_decode(unescape(string));
    }
}
</script>
<script type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/

var customerRegNo = '${customerRegNo}';

var customerDn = '${customerDn}';

var termsRegNo = '${termsRegNo}';

var termsStartDate = '${rsTermsVO.termsStartDate}';
var termsPolicy = '${rsTermsVO.termsPolicy}';
var termsPolicyYear = '${rsTermsVO.termsPolicyYear}';

var termsAuthYn = '${termsAuthYn}';

var signData='';
var signDn = "";

var strCallBack='${callBakFunc}';

var rsData;
var rsCd;
var rsCdMsg;


/* 공인인증서 관련 */
var index = 0; //공인인증서 비밀번호 오입력 횟수 체크

/*******************************************
 * 기능 함수
 *******************************************/

//Create Base64 Object
//var Base64={_keyStr:"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",encode:function(e){var t="";var n,r,i,s,o,u,a;var f=0;e=Base64._utf8_encode(e);while(f<e.length){n=e.charCodeAt(f++);r=e.charCodeAt(f++);i=e.charCodeAt(f++);s=n>>2;o=(n&3)<<4|r>>4;u=(r&15)<<2|i>>6;a=i&63;if(isNaN(r)){u=a=64}else if(isNaN(i)){a=64}t=t+this._keyStr.charAt(s)+this._keyStr.charAt(o)+this._keyStr.charAt(u)+this._keyStr.charAt(a)}return t},decode:function(e){var t="";var n,r,i;var s,o,u,a;var f=0;e=e.replace(/[^A-Za-z0-9+/=]/g,"");while(f<e.length){s=this._keyStr.indexOf(e.charAt(f++));o=this._keyStr.indexOf(e.charAt(f++));u=this._keyStr.indexOf(e.charAt(f++));a=this._keyStr.indexOf(e.charAt(f++));n=s<<2|o>>4;r=(o&15)<<4|u>>2;i=(u&3)<<6|a;t=t+String.fromCharCode(n);if(u!=64){t=t+String.fromCharCode(r)}if(a!=64){t=t+String.fromCharCode(i)}}t=Base64._utf8_decode(t);return t},_utf8_encode:function(e){e=e.replace(/rn/g,"n");var t="";for(var n=0;n<e.length;n++){var r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r)}else if(r>127&&r<2048){t+=String.fromCharCode(r>>6|192);t+=String.fromCharCode(r&63|128)}else{t+=String.fromCharCode(r>>12|224);t+=String.fromCharCode(r>>6&63|128);t+=String.fromCharCode(r&63|128)}}return t},_utf8_decode:function(e){var t="";var n=0;var r=c1=c2=0;while(n<e.length){r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r);n++}else if(r>191&&r<224){c2=e.charCodeAt(n+1);t+=String.fromCharCode((r&31)<<6|c2&63);n+=2}else{c2=e.charCodeAt(n+1);c3=e.charCodeAt(n+2);t+=String.fromCharCode((r&15)<<12|(c2&63)<<6|c3&63);n+=3}}return t}}

//[s] termsAuthYn check Function  2017-04-07 한유진
/* '동의서 재동의'일 경우 termsStartDate 현재 날짜로 세팅*/
function termsAuthYnCheck(){
	if (termsAuthYn == "R"){
		termsStartDate = '${curDate}';
	}
	return;
}
// [e] termsAuthYn check Function  2017-04-07 한유진

/*******************************************
 * ajax,ajax Callback 함수
 *******************************************/

/* 공인인증서 인증 버튼 클릭시 */
function certsign(){
    var CertManX;
    if(ytMain){
        CertManX = ytMain;
    }

    var plaintext = "testplain";
    var dn = "";
    if(index>4){
      alert("비밀번호 입력횟수 초과");
        return;
    }
    
    if(CertManX.doubleClickBlock(arguments.callee)) return;
    //모듈이 설치되어 있는지 확인하는 함수
    CertManX.CertServiceSetup(function(result){
        if(result == ""){
            var errorCode = CertManX.GetLastErrorCode();
            if(errorCode == 90000){
                alert("모듈 설치 필요.");
                fn_popupClose();
                return;
            }
            if(errorCode == 90001 || errorCode == 90002){
                alert("모듈 업데이트 필요");
                fn_popupClose();
                return;
            }
            else{
                alert(errorCode + "//" + CertManX.GetLastErrorMsg());
                fn_popupClose();
                return;
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
    
    //CertManX.SetLogoFile("./test.bmp");  //선택창 로고

    CertManX.SetKeySaferMode(3);         // 키보드보안모듈 연동 -> 최초에 파라미터가 3으로 셋팅되어있었음(키보드 보안업체 프로그램 과의 상호 연동을 위한 설정)
    CertManX.SetWrongPasswordLimit(1);   // 패스워드 입력제한

    
    CertManX.UnSetMatchedContext(function () {
        CertManX.SetMatchedContextExt("","","", 256+0+1, function(dn){
            if(dn == ""){
                if(CertManX.GetLastErrorCode() == 2417){
                    index++;
                    alert("비밀번호 오류 ["+index+"회]");
                    //sign();
                    return;
                    
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
                    return false;
                }
            }
            index=0;

            var termHtml = $("#divUserTerm").html().replace(/\(/g, "&#40;");
            termHtml = termHtml.replace(/\(/g, "&#40;");
            termHtml = termHtml.replace(/\)/g, "&#41;");
            
            plaintext = termHtml;
            //BSTR SignDataB64(BSTR pPassword, BSTR pPlainText, long mode)
            CertManX.SignDataB64("", plaintext, 1, function(signdata) {
                if(signdata == ""){
                    alert("SignDataB64 실패 : ["+CertManX.GetLastErrorCode() +"]"+CertManX.GetLastErrorMsg());
                    return;
                }
            	form.t_dn.value = CertManX.GetToken(signdata, "dn");
                form.t_signdata.value = CertManX.GetToken(signdata, "data"); //서명데이터 [서버에서  data와 r 값을 유효성검증함]
                form.t_rdata.value = CertManX.GetToken(signdata,"r"); //주민번호등 인증비교값 [서버에서  data와 r 값을 유효성검증함]
                
                signDn = form.t_dn.value; 
                signData = form.t_signdata.value;
                
                //유효성검증:공인인증호출
                var rsFlag = fn_ajaxCallSKVeirify();
                if(!rsFlag){
                	return false;
                }
                
                //로딩 호출
                gfn_setLoading(true, "TSA 처리 중 입니다.");
                
                fn_ajaxCallTermsHtmlProcTsa(); //TSA호출
                fn_ajaxCallDeleteFolderFile(); //TSA파일삭제처리
                fn_openerCallBackSend(); //부모창콜백처리
                
                //로딩 호출
                gfn_setLoading(false);
            });
        });
    });
}

/*[공인인증서서버]공인인증서 서버 유효성검증을 조회요청 함수 */
function fn_ajaxCallSKVeirify(){
    
    var rsFlag = false;
    
    var t_dn = $("#t_dn").val();
    
    if(t_dn != customerDn){
    	alert("<spring:message code='errors.nosamedata.dn' arguments='등록한 공인인증서,입력한 공인인증서'/>");
    	return false;
    }
    
//     var userSsn  = $("#customerSsn").val().replace(/\s/g, ""); //주민등록번호
    var signData = form.t_signdata.value;
    var rValue   = form.t_rdata.value;
    
    //파라미터셋팅
    var objParam = new Object();
//     objParam.demonIp   = "192.168.30.173";
//     objParam.demonPort = 7900;
//     objParam.userSsn  = userSsn;
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
//                var msgAlert = "<spring:message code='success.alert.regist'/>";
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

/* 약관처리 요청함수 */
function fn_ajaxCallTermsHtmlProcTsa(){
    
    var rsFlag = false;
    
    if(util_chkReturn(signData, "s") == ""){
    	alert('공인인증서의 signData를 찾을 수 없습니다.\nsignData='+signData);
    	return rsFlag;
    }else{
//     	signData = Base64.decode(signData);
//     	signData = gfnBase64.decode(signData);
    }
    
//     var dn       = $("#t_dn").val();
//     var rValue   = $("#t_rdata").val();
//     var signData = $("#t_signdata").val();
    var reqHtml  = $("#divUserTerm").html();
    
    //[s] yj 2017-04-07 [2]
    //termsAuthYn = "R" 인경우 termsStartDate 를 현재날짜로 세팅
    termsAuthYnCheck();
    //[e] yj 2017-04-07 [2]
    
    var objParam = new Object();
//     objParam.dn            = dn;
//     objParam.rValue        = rValue;
    objParam.dn             = signDn;
    objParam.signData       = signData;
    objParam.reqHtml        = reqHtml;
    objParam.customerRegNo  = customerRegNo;
    objParam.termsRegNo     = termsRegNo;
    objParam.termsStartDate = termsStartDate;
    objParam.termsPolicy    = termsPolicy;
    
    //메일발송용 정보
    objParam.customerName    = "${rsTermsVO.customerName}";
    objParam.customerEmail   = "${rsTermsVO.customerEmail}";
        
    var url = "<c:url value='/cmm/tsa/reqTerms.ajax'/>";
    $.ajax({
         type    : "POST"
        ,url     : url
        ,data    : objParam
        ,async   : false
        ,beforeSend : function(){
        	 /*
             $(".dimm").show();
             $("body").append("<img src='<c:url value='/images/spt/common/ajaxLoading.gif'/>' id='imgViewLoading'/>")
             */
        	 //로딩 호출
             //gfn_setLoading(true, "전문요청 중 입니다.");
        }
        ,complete : function(){
        	 /*
             $(".dimm").hide();
             $("#imgViewLoading").remove();
             */
        	 //로딩 호출
             //gfn_setLoading(false);
        }
        ,success : function(data){
        	//로딩 호출
            gfn_setLoading(false);
        	
        	if(util_chkReturn(data.rsCd, "s") != ""){
        		rsCd = data.rsCd;
        	}else{
	        	rsCd  = '01';
        	}
        	
        	if(util_chkReturn(data.rsCdMsg, "s") != ""){
        		rsCdMsg = data.rsCdMsg;
        	}else{
	        	rsCdMsg = 'TSA서버단에서 응답결과메세지 또는 응답결과가 없습니다.';
        	}
        }
        ,error   : function(data){
        	//로딩 호출
            gfn_setLoading(false);
        	
        	if(util_chkReturn(data.rsCd, "s") != ""){
        		rsCd = data.rsCd;
        	}else{
	        	rsCd  = '01';
        	}
        	
        	if(util_chkReturn(data.rsCdMsg, "s") != ""){
        		rsCdMsg = data.rsCdMsg;
        	}else{
	        	rsCdMsg = 'TSA작업중 문제가 생겼습니다.';
        	}
        }
    });
    return rsFlag;
}

/* TSA임시폴더 삭제 요청함수 */
function fn_ajaxCallDeleteFolderFile(){
    var objParam = new Object();
    objParam.customerRegNo = customerRegNo;
    var url = "<c:url value='/cmm/tsa/deleteFolderFile.ajax'/>";
    $.ajax({
        type    : "POST"
       ,url     : url
       ,data    : objParam
       ,async   : false
       ,success : function(data){
           //console.log("삭제완료");
       }
       ,error : function(data){
           //console.log("삭제실패");
       }
    });
}

/* TSA응답결과 openr전송 함수 */
function fn_openerCallBackSend(){
	if(util_chkReturn(strCallBack, "s") != ""){
		if(opener){
			if(typeof window.opener.eval(strCallBack) === 'function'){
				window.opener.eval(strCallBack)(rsCd, rsCdMsg);
				window.close();
			}
		}else{
			if(typeof window.parent.eval(strCallBack) === 'function'){
				window.parent.eval(strCallBack)(rsCd, rsCdMsg);
				gfn_closeModal(this.event);
			}
		}
	}
}

/* ARS 인증 버튼 클릭시 인증 팝업 */
function fn_openArsTerms(){
    var url = "<c:url value='/usr/svcAppl/svcTermConsntArsPopup.do'/>";
    var objOption = new Object();
    objOption.type = 'modal';
    objOption.width = '502';
    objOption.height = '480';
    
    //[s] yj 2017-04-07
    //termsAuthYn = "R" 인경우 termsStartDate 를 현재날짜로 세팅
    termsAuthYnCheck();
    //[e] yj 2017-04-07

    var objParam = new Object();
    objParam.customerRegNo  = customerRegNo;
    objParam.termsRegNo     = termsRegNo;
    objParam.termsStartDate = termsStartDate;
    objParam.termsPolicy    = termsPolicy;
    //ARS인증 정보
    objParam.customerPhone    = "${resultCustomerInfoVO.customerPhone}";
    //핀테크기업코드명
    objParam.subcompanyName    = "${rsTermsVO.subcompanyName}";
    var rsPubcompanyList = '';
    <c:forEach items="${rsPubcompanyList}" var="item" varStatus="status">
        rsPubcompanyList += "${item.pubcompanyName}";
        <c:if test="${!status.last}">
        rsPubcompanyList += '_';
        </c:if>
    </c:forEach>
    //정보제공동의서 기업정보
    objParam.rsPubcompanyList    = rsPubcompanyList;

    //메일발송용 정보
    objParam.customerName    = "${rsTermsVO.customerName}";
    objParam.customerEmail   = "${rsTermsVO.customerEmail}";

    objParam.callBakFunc = "fn_openArsTermsCallBack";
    //multi modal
    util_modalPage2(url, objOption, objParam);
}

function fn_openArsTermsCallBack(rsCd,rsMsg){
    this.rsCd = rsCd;
    this.rsMsg = rsMsg;
    //ARS 인증 모달 창 종료를 위해 타임아웃 설정
    setTimeout(function(){ fn_openerCallBackSend(); }, 1000);
}
/*******************************************
 * 이벤트 함수
 *******************************************/

/* [팝업:닫기]버튼 클릭 시 호출되는 함수 */
function fn_popupClose(){
    if(opener){
        window.close();
    }else{
        gfn_closeModal(this.event);
    }
}

/* 화면 로드 처리 */
$(document).ready(function(){
	<%-- 로그인 처리 --%>
    <c:if test="${empty LoginVO}">
        $(".wrap >").remove();
        if(opener){
            window.opener.fn_login();
            window.close();
        }else{
        	window.parent.fn_login();
        	gfn_closeModal();
        }
    </c:if>
    
    /* [취소]버튼 이벤트발생 시 호출 */
    $("#btnCancel").bind("click", function(e){
        fn_popupClose();
    });
    
    /* [공인인증서인증]버튼 이벤트발생 시 호출 */
    $("#btnCert").bind("click", function(e){
        //util_movePage('imsi');
        certsign();
    });

    /* [ARS인증]버튼 이벤트발생 시 호출 */
    $("#btnArsCert").bind("click", function(e){
        //util_movePage('imsi');
        fn_openArsTerms();
    });
    
    /* 인쇄 */
    $("#btnPrint").click(function(){
    	$("#divUserTerm").printArea();
    });
    
    /* 검증 */
    $("#btnVerify").click(function(){
        <c:choose>
            <c:when test="${rsTermsVO.termsAuthType eq 'G032001'}" >
            //공인인증서 검증
            certsignVerify();
            </c:when>
            <c:otherwise>
            //ARS 검증
            arsVerify();
            </c:otherwise>
        </c:choose>

    });

});

<%-- 관리자용 동의서 검증(signKorea) --%>
function certsignVerify(){
	var signData = $("#customerSignData").val();
	
	if(util_chkReturn(signData, "s") == "") {
		alert("검증에 필요한 전자서명키 값이 없습니다.");
		return;
	}
	
	var CertManX;
    if(ytMain){
        CertManX = ytMain;
    }
    
    //전자서명 인증
    CertManX.VerifyDataB64(signData, 1, function(verifyData){
    	if(util_chkReturn(verifyData, "s") == "") {
            alert("검증에 실패 했습니다.");
        }else{
            alert("검증에 성공 했습니다.");
        }
    });
}

<%-- 관리자용 동의서 검증(ars mp3 다운) --%>
function arsVerify(){
    //로딩 호출
    gfn_setLoading(true, "MP3 다운중 입니다.");
    $("#DOWN_FRAME").ready(function(){
        gfn_setLoading(false);
    });
    util_moveRequest("rsTermsVO", "<c:url value='/usr/svcAppl/svcTermArsDown.do'/>", "DOWN_FRAME");
}
</script>
</head>
<body>
<div class="wrap">

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

    <!-- layer_popup / layer_popup_dev -->
    <div class="layer_popup_dev type2">   

        <div class="dimm" style="display:none;"></div>
        
        <!-- #layer01 -->
        <div class="layer_box" id="layer01" >
            <div class="layer_tit">정보제공 동의</div>

            <!-- layer_con -->
            <div class="layer_con" style="max-height: 900px;" >
			
                <div class="scroll_box" style="height: 650px;">
			
				<!-- PDF변환용 -->
				<div style="color:#1b1b1b; margin:0; padding:0;" id="divUserTerm">
					<p style="font-size:15px; text-align:center; font-weight:bold;">금융거래 정보 제공동의서</p>					
					<div style="font-weight:bold; line-height:20px; padding:15px; margin-top:10px; border:1px solid #9e9d9d;">
						<ol style="font-size:12px; list-style:none; margin:0; padding:0;">
							<li style="margin-bottom:5px;">1. 정보를 제공받을 기관명 :
                                <span style="color:#015dab;">
                                    <c:if test="${ rsTermsVO.subcompanyCodeId ne '00995' }">
                                        코스콤(00995),
                                    </c:if>
                                    <c:out value='${rsTermsVO.subcompanyName}'/>(<c:out value='${rsTermsVO.subcompanyCodeId}'/>)
                                </span></li>
							<li style="margin-bottom:5px;">2. 정보제공의 범위와 사용목적
								<p style="margin:0; padding:0;">O 정보제공 범위 : <span style="color:#015dab;">동의자 명의의 금융거래정보</span></p>
                                <p style="margin:0; padding:0;">- 금융 상품 잔고 및 거래 정보 자산 구성 정보와 관심 금융상품 정보</p>
								<p style="margin:0; padding:0;">O 사용목적 : 코스콤 자본시장 공동 핀테크 오픈플랫폼을 통한 금융거래정보 전달 및 핀테크 서비스를 통한 금융서비스 제공</p>
							</li>
							<c:if test="${termsAuthYn eq 'R'}">
								<li style="margin-bottom:5px;">3. 유효기간 :
							    	<span style="color:#015dab;"><c:out value='${curYear}'/></span>년 
							    	<span style="color:#015dab;"><c:out value='${curMonth}'/></span>월 
							    	<span style="color:#015dab;"><c:out value='${curDay}'/></span>일 ~ 
							   		<span style="color:#015dab;"><c:out value='${expYear}'/></span>년 
							    	<span style="color:#015dab;"><c:out value='${expMonth}'/></span>월 
								    <span style="color:#015dab;"><c:out value='${expDay}'/></span>일 
								    <span style="color:#015dab;">(<c:out value='${rsTermsVO.termsPolicyYear}'/>년간)</span>
								</li>
							</c:if>
							<c:if test="${termsAuthYn eq 'N' || termsAuthYn eq 'Y'}">
								<li style="margin-bottom:5px;">3. 유효기간 조회 :
							    	<span style="color:#015dab;"><c:out value='${rsTermsVO.termsStartDateYYYY}'/></span>년 
							    	<span style="color:#015dab;"><c:out value='${rsTermsVO.termsStartDateMM}'/></span>월 
							    	<span style="color:#015dab;"><c:out value='${rsTermsVO.termsStartDateDD}'/></span>일 ~ 
							    	<span style="color:#015dab;"><c:out value='${rsTermsVO.termsExpireDateYYYY}'/></span>년 
							    	<span style="color:#015dab;"><c:out value='${rsTermsVO.termsExpireDateMM}'/></span>월 
							    	<span style="color:#015dab;"><c:out value='${rsTermsVO.termsExpireDateDD}'/></span>일 
							    	<span style="color:#015dab;">(<c:out value='${rsTermsVO.termsPolicyYear}'/>년간)</span>
								</li>
							</c:if>
							<li>4. 금융거래 정보 제공 동의자 인적 사항
								<table cellspacing="0" cellpadding="0" border="0" style="width:100%; margin:5px 0; border-top:1px solid #d3d2d2; border-right:1px solid #d3d2d2;">
									<caption style="display:none;">성명, 이메일, 생년월일 정보</caption>
									<colgroup>
										<col style="width:8%;">
										<col style="width:10%;">
										<col style="width:10%;">
										<col style="width:16%">
										<col style="width:8%;">
										<col style="width:15%;">
                                        <col style="width:8%;">
                                        <col style="">
									</colgroup>
									<tbody>
									<tr>
										<th scope="row" style="padding:3px 5px; font-size:12px; border-right:1px solid #d3d2d2; border-left:1px solid #d3d2d2; border-bottom:1px solid #d3d2d2; background:#f3f2f2;">
										    성명
										</th>
										<td style="padding:6px 5px; font-size:12px; font-weight:bold; color:#001a30; border-bottom:1px solid #d3d2d2;">
										    <c:out value='${rsTermsVO.customerName}'/>
										</td>
										<th scope="row" style="padding:3px 5px; font-size:12px; border-right:1px solid #d3d2d2; border-left:1px solid #d3d2d2; border-bottom:1px solid #d3d2d2; background:#f3f2f2;">
											생년월일
										</th>
										<td style="padding:6px 5px; font-size:12px; font-weight:bold; color:#001a30; border-bottom:1px solid #d3d2d2;">
											<fmt:parseDate value='${rsTermsVO.customerBirthDay}' var='customerBirthDay' pattern="yyyyMMdd"/>
											<fmt:formatDate value="${customerBirthDay}"  pattern="yyyy년 MM월 dd일"/>
										</td>
                                        <th scope="row" style="padding:3px 5px; font-size:12px; border-right:1px solid #d3d2d2; border-left:1px solid #d3d2d2; border-bottom:1px solid #d3d2d2; background:#f3f2f2;">
                                            휴대폰
                                        </th>
                                        <td style="padding:6px 5px; font-size:12px; font-weight:bold; color:#001a30; border-bottom:1px solid #d3d2d2;">
                                            <c:out value='${rsTermsVO.customerPhone}'/>
                                        </td>
                                        <th scope="row" style="padding:3px 5px; font-size:12px; border-right:1px solid #d3d2d2; border-left:1px solid #d3d2d2; border-bottom:1px solid #d3d2d2; background:#f3f2f2;">
										    이메일
										</th>
										<td style="padding:6px 5px; font-size:12px; font-weight:bold; color:#001a30; border-bottom:1px solid #d3d2d2;">
										    <c:out value='${rsTermsVO.customerEmail}'/>
										</td>
									</tr>
									</tbody>
								</table>
							</li>
							<li style="margin-bottom:5px;">5. 정보제공 금융투자회사
							    <div style="color:#015dab; word-break:all-break;">
								<c:forEach var="pcList" items="${rsPubcompanyList}" varStatus="status">
								    <c:if test="${ pcList.pubcompanyName ne '*****' }">
							            <span>O <c:out value='${pcList.pubcompanyName}'/>  </span>
							        </c:if>
								</c:forEach>
								</div>
							</li>
                            <li style="margin-bottom:5px;">6. 제공받은 정보의 보유 및 이용기간
                                <p style="margin:0; padding:0;">금융거래정보는 제공된 날로부터 동의 철회 시 또는 제공된 목적을 달성할 때까지 보유·이용됩니다.<br>
                                    동의 철회 또는 제공된 목적 달성 후에는 위 기재된 사용목적과 관련된 금융사고 조사, 분쟁해결, 민원처리, 법령상 의무이행을 위하여 필요한 범위 내에서만 보유·이용 됩니다.</p>
                            </li>
						</ol>
						<div style="font-size:12px; color:#1b1b1b; margin:10px -15px -15px; padding:10px 20px; background:#f8f8f8;">
                             O 금융거래정보를 제공하는 것에 대한 동의를 거부하실 수 있으나, 금융거래내역의 제공을 위해서는 반드시 동의가 필요하므로 거부하실 경우 금융거래 제공 요청을 신청할 수 없습니다<br>
                             O 「금융실명거래 및 비밀보장에 관한 법률」 제4조의2 제1항 규정의 거래정보 등의 제공사실 통보를 1년 단위로 포괄하여 통보하는 것에 대하여 동의합니다.<br>
							<div style="font-size:13px; font-weight:bold; text-align:center;">
								<span style="color:#015dab;"><c:out value='${rsTermsVO.termsChgDateYYYY}'/></span>년
								<span style="color:#015dab;"><c:out value='${rsTermsVO.termsChgDateMM}'/></span>월
								<span style="color:#015dab;"><c:out value='${rsTermsVO.termsChgDateDD}'/></span>일
							</div>
							<div style="font-size:13px; font-weight:bold; text-align:right">
							    금융투자회사 대표이사 귀하
							</div>
						</div>
					</div>
		              <c:if test="${termsAuthYn eq 'Y' || termsAuthYn eq 'R'}">
			            <div class="banking_agree_area">
			                <ul class="list_style_01">
			                    <li>
			                        <span>참고사항 :</span>
			                        <ul>
			                            <%--
			                            <li>- 본 동의서는 동의서 폐기 전까지 유효합니다.</li>
			                            <li>- 동의 확인 : 본인 공인인증서의 전자서명 및 TSA</li>
			                            --%>
			                            <li>- 동의확인의 증명은 공인인증서를 이용한 전자서명과 공인인증센터의 시점확인을 통해 이루어집니다.</li>
			                        </ul>
			                    </li>
			                </ul>
			            </div>
			            <div class="btn_area" style="margin-top:20px;">
			                <a href="javascript:void(0);" class="btn_type5 type2" onclick="javascript:fn_popupClose();">거부</a>
			                <a href="javascript:void(0);" id="btnCert" class="btn_type5">전자서명</a>
		                    <%--<a href="javascript:void(0);" id="btnArsCert" class="btn_type5">ARS 인증</a>--%>
			            </div>
			          </c:if>
			          <c:if test="${termsAuthYn eq 'N'}">
			            <div class="btn_area" style="margin-top:20px;">
		                    <a href="javascript:void(0);" id="btnPrint" class="btn_type5">인쇄</a>
		                    <c:if test="${SYSTEM_KIND eq 'apt'}">
		                        <a href="javascript:void(0);" id="btnVerify" class="btn_type5 type2">검증</a>
		                    </c:if>
		                </div>    
			          </c:if>
					
					<%-- 인쇄용 정보 셋팅 --%>	
					<c:if test="${termsAuthYn eq 'N'}">
					   <%-- admin / 개인사용자 --%>
					   <c:if test="${SYSTEM_KIND eq 'spt' || SYSTEM_KIND eq 'apt'}">
						   <div style="font-weight:bold; line-height:20px; padding:15px; margin-top:10px; border:1px solid #9e9d9d;">
						       <ol style="font-size:12px; list-style:none; margin:0; padding:0;">
						           <li>동의 서명 정보
						               <table cellspacing="0" cellpadding="0" border="0" style="width:100%; margin:5px 0; border-top:1px solid #d3d2d2; border-right:1px solid #d3d2d2;">
	                                    <colgroup>
	                                        <col style="width:15%;">
	                                        <col style="width:70%">
	                                        <col style="width:15%;">
	                                    </colgroup>
	                                    <tbody>
	                                    <tr>
	                                        <th scope="row" style="padding:8px 12px; font-size:12px; border-right:1px solid #d3d2d2; border-left:1px solid #d3d2d2; border-bottom:1px solid #d3d2d2; background:#f3f2f2;">
                                                <c:choose>
                                                    <c:when test="${rsTermsVO.termsAuthType eq 'G032001'}" >
                                                        공인인증서
                                                    </c:when>
                                                    <c:otherwise>
                                                        인증일시
                                                    </c:otherwise>
                                                </c:choose>
	                                        </th>
	                                        <td style="padding:6px 14px; font-size:12px; font-weight:bold; color:#001a30; border-bottom:1px solid #d3d2d2; border-right:1px solid #d3d2d2;">
                                                <c:choose>
                                                    <c:when test="${rsTermsVO.termsAuthType eq 'G032001'}" >
                                                        <c:out value='${rsTermsVO.customerSignDn}'/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:out value='${rsTermsVO.termsChgDate}'/>
                                                    </c:otherwise>
                                                </c:choose>
	                                        </td>
	                                        <td rowspan="3" style="padding:14px 14px; font-size:12px; font-weight:bold; color:#001a30; border-bottom:1px solid #d3d2d2;">
	                                            <center>
	                                            <c:choose>
	                                                <%-- 폐기 --%>
										            <c:when test="${rsTermsVO.termsStatus eq 'G030003'}" >
										                <img src='<c:url value='/images/cmm/svc_terms_cancel.png'/>' style="width: 60px;height: 60px"/>
										            </c:when>
										            <%-- 동의/재동의필요 --%>
										            <c:otherwise>
										                <img src='<c:url value='/images/cmm/svc_terms_confirm.png'/>' style="width: 60px;height: 60px"/>
										            </c:otherwise>
										        </c:choose>
										        </center>
	                                        </td>
	                                    </tr>
	                                    <tr>
	                                        <th scope="row" style="padding:8px 12px; font-size:12px; border-right:1px solid #d3d2d2; border-left:1px solid #d3d2d2; border-bottom:1px solid #d3d2d2; background:#f3f2f2;">
                                                <c:choose>
                                                    <c:when test="${rsTermsVO.termsAuthType eq 'G032001'}" >
                                                        전자서명키
                                                    </c:when>
                                                    <c:otherwise>
                                                        ARS인증번호
                                                    </c:otherwise>
                                                </c:choose>
	                                        </th>
	                                        <td style="padding:6px 14px; font-size:12px; font-weight:bold; color:#001a30; border-bottom:1px solid #d3d2d2; border-right:1px solid #d3d2d2;">
                                                <c:choose>
                                                    <c:when test="${rsTermsVO.termsAuthType eq 'G032001'}" >
                                                        <c:set var="customerSignData" value="${fn:substring(rsTermsVO.customerSignData, 1, 60)}" />
                                                        <c:out value='${customerSignData}'/>
                                                        <c:if test="${ not empty customerSignData }">...</c:if>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:out value='${rsTermsVO.customerSignData}'/>
                                                    </c:otherwise>
                                                </c:choose>
	                                            <input type="hidden" id="customerSignData" value="${ rsTermsVO.customerSignData }" />
	                                        </td>
	                                    </tr>
	                                    <tr>
	                                        <th scope="row" style="padding:8px 12px; font-size:12px; border-right:1px solid #d3d2d2; border-left:1px solid #d3d2d2; border-bottom:1px solid #d3d2d2; background:#f3f2f2;">
                                                <c:choose>
                                                    <c:when test="${rsTermsVO.termsAuthType eq 'G032001'}" >
                                                        시점확인키
                                                    </c:when>
                                                    <c:otherwise>
                                                        인증ID
                                                    </c:otherwise>
                                                </c:choose>
	                                        </th>
	                                        <td style="padding:6px 14px; font-size:12px; font-weight:bold; color:#001a30; border-bottom:1px solid #d3d2d2; border-right:1px solid #d3d2d2;">
	                                            <c:out value='${rsTermsVO.customerTsaData}'/>
	                                        </td>
	                                    </tr>
	                                    </tbody>
	                                </table>
						           </li>
						       </ol>
						   </div>
                       </c:if>						  
                       <%-- 기업포털 사용자 --%> 
					   <c:if test="${SYSTEM_KIND eq 'cpt'}">
					       <div style="font-weight:bold; line-height:20px; padding:15px; margin-top:10px; border:1px solid #9e9d9d;">
                               <ol style="font-size:12px; list-style:none; margin:0; padding:0;">
                                   <li>
                                       <table cellspacing="0" cellpadding="0" border="0" style="width:100%; margin:5px 0; border-top:1px solid #d3d2d2; border-right:1px solid #d3d2d2;">
                                        <colgroup>
                                            <col style="width:85%;">
                                            <col style="width:15%;">
                                        </colgroup>
                                        <tbody>
                                        <tr>
                                            <td style="padding:6px 14px; font-size:12px; font-weight:bold; color:#001a30; border-left:1px solid #d3d2d2; border-bottom:1px solid #d3d2d2; border-right:1px solid #d3d2d2;">
                                                <ol style="font-size:12px; list-style:none; margin:0; padding:0;">
				                                    <c:if test="${LoginVO.company_service_type eq 'G002001' || LoginVO.company_service_type eq 'G002002'}">
				                                        <li style="margin-bottom:5px;">
				                                            <p style="margin:0; padding:0;">*** 개인정보 보호와 관련해서 정보제공 금융기관은 출력하지 않습니다.</p>
				                                            <p style="margin:0; padding:0;padding-left: 22px;">원본 필요 시 코스콤에 별도 문의하세요.</p>
				                                        </li>
				                                    </c:if>
				                                    <li style="margin-bottom:5px;">
				                                        <p style="margin:0; padding:0;">*** 열람용으로만 사용가능합니다.</p>
				                                        <p style="margin:0; padding:0;padding-left: 22px;">법적인 용도로 사용하려면 코스콤 담당자에게 별도 문의하세요.</p>
				                                    </li>
				                                </ol>
                                            </td>
                                            <td rowspan="3" style="padding:14px 14px; font-size:12px; font-weight:bold; color:#001a30; border-bottom:1px solid #d3d2d2;">
                                                <center>
                                                <c:choose>
                                                    <%-- 폐기 --%>
                                                    <c:when test="${rsTermsVO.termsStatus eq 'G030003'}" >
                                                        <img src='<c:url value='/images/cmm/svc_terms_cancel.png'/>' style="width: 60px;height: 60px" alt="폐기(무효)"/>
                                                    </c:when>
                                                    <%-- 동의/재동의필요 --%>
                                                    <c:otherwise>
                                                        <img src='<c:url value='/images/cmm/svc_terms_confirm.png'/>' style="width: 60px;height: 60px" alt="동의(유효)"/>
                                                    </c:otherwise>
                                                </c:choose>
                                                </center>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                   </li>
                               </ol>
                           </div>
						   
					   </c:if>
					</c:if>
							
				</div>
				<!-- // PDF변환용 -->
				
            </div>
            <!-- // layer_con -->
        
            <a href="javascript:void(0);" class="layer_close" onclick="javascript:fn_popupClose();">레이어팝업 닫기</a>
            </div>
        </div>
	    <!-- // #layer01 -->
    </div>
    <!-- // layer_popup_dev -->
    
</div>
<form:form commandName="rsTermsVO" name="rsTermsVO" method="post">
<input type="hidden" name="termsRegNo" id="termsRegNo" value="${termsRegNo}"/>
</form:form>
<%-- 다운용 frame --%>
<iframe src="" id="DOWN_FRAME" name="DOWN_FRAME" style="visibility: hidden;"></iframe>
<%-- 다운용 frame --%>
</body>
</html>