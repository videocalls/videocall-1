<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : mbrReg1.jsp
 * @Description : [회원가입:1.본인인증]
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.04.28  이환덕        최초  생성
 * </pre>
 *
 * @author 포털 이환덕 
 * @since 2016.04.28
 * @version 1.0
 * @see
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

<script type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/
var g_verifyWin = null;     //인증 팝업(휴대폰 또는 ipin)

/*******************************************
 * 기능 함수
 *******************************************/

/* 보안3종 체크 함수 */
function fn_checkSecu3(){
	//보안3종관련
    var tmout = 750;
    $ASTX2.init(
        function onSuccess(){
        // success
        	//console.log('ASTX 설치완료');
        },
        function onFailure(){
            var errno = $ASTX2.getLastError();
            
            //goto install page
            if(errno == $ASTX2_CONST.ERROR_NOTINST){
            	util_movePage("<c:url value='/cmm/aos2/certIndex.do'/>");
            
            //error handling
            }else{
                alert('ASTX error handling');
            }
        },
        tmout
    );
    
    //공인인증서
    var CertManX;
    if(ytMain){
        CertManX = ytMain;
    }
    CertManX.SetInfoPage(1);    // 1로 세팅시 CertManX.CertServiceSetup실행 후 errorCode 로직 실행됨.
    //모듈이 설치되어 있는지 확인하는 함수
    CertManX.CertServiceSetup(function(result){
        if(result == ""){
            var errorCode = CertManX.GetLastErrorCode();
            if(errorCode == 90000){
                alert("모듈 설치 필요.");
                util_movePage("<c:url value='/cmm/aos2/certIndex.do'/>");
                return false;
            }
            if(errorCode == 90001 || errorCode == 90002){
                util_movePage("<c:url value='/cmm/aos2/certIndex.do'/>");
                return false;
            }
            else{
                alert(errorCode + "//" + CertManX.GetLastErrorMsg());
                util_movePage("<c:url value='/cmm/aos2/certIndex.do'/>");
                return false;
            }
        }
    });
}
 
/* [인증:본인인증]후 결과처리 함수 - 기존 가입 여부 체크 : 최초가입의 경우 회원가입 절차처리 진행 */ 
function fn_certHpResult(paramCustomerVerify, paramHp, paramUserNm){
	if(util_chkReturn($.trim(paramCustomerVerify), "s") == ""){
		//alert("본인인증 CI가 없습니다.\n관리자에게 문의 해 주세요.");
		return;
	}
	if(util_chkReturn($.trim(paramUserNm), "s") == ""){
        //alert("본인인증 이름이 없습니다.\n관리자에게 문의 해 주세요.");
        return;
    }
	
	/* alert("NICE인증 팝업창에서 부모창으로 넘어온 CI 값 : "+paramCustomerVerify+", 사용자이름 : "+paramUserNm);
    return false; */
    var objParam = new Object();
    objParam.customerVerify     = paramCustomerVerify; //회원검증값(본인인증 후 나이스신용평가정보에서 리턴해 주는 값)
	var url = "<c:url value='/usr/mbrReg/saveMbrRegChk.ajax'/>";
	$.ajax({
        type    : "post"
       ,url     : url
       ,data    : objParam
       ,success : function(data){
    	   var returnVO = data.returnVO;    	   
    	   if(returnVO == null || returnVO == ""){ //최초의 회원가입의 경우와 탈퇴회원의 경우
    		   fn_certHpResult2(paramCustomerVerify, paramHp, paramUserNm);
    	   }else{ //기존에 회원가입을 한 이력이 있는 경우(기회원이나 회원가입상태중의 회원) - 회원가입 본인인증 화면은 로그인을 하지 않은 상태에서 들어오므로
    		   fn_certHpResult3(paramCustomerVerify, paramHp, paramUserNm, returnVO.customerRegNo); //일반회원 검증 테이블 - 회원OP등록번호
    	   }    	   
       }
       ,error   : function(){
           var msgAlert = "<spring:message code='fail.alert.regist'/>";
           alert(msgAlert);
       }
   });
}

/* [인증:본인인증]후 결과처리 함수 - 최초회원가입의 경우와 회원탈퇴후 재회원가입의 경우 회원가입 절차처리 진행 */
function fn_certHpResult2(paramCustomerVerify, paramHp, paramUserNm){
    
    var objParam = new Object();
    objParam.customerVerify     = paramCustomerVerify; //회원검증값
    objParam.customerVerifyType = 'G007001';   //회원검증종류[G007(001:본인인증, 002:공인인증서)]
    objParam.customerStep       = 'G006001';   //회원 가입 Step:G006(001:본인인증, 002:공인인증서등록, 003:약관동의, 004:개인정보입력, 005:이메일인증, 006:임시비밀번호발급, 007:임시비밀번호수정)
    objParam.customerPhone      = paramHp;     //회원 휴대폰 번호
    objParam.customerNameKor    = paramUserNm; //회원명 한글
    
    var url = "<c:url value='/usr/mbrReg/saveMbrReg1.ajax'/>";
    $.ajax({
        type    : "post"
       ,url     : url
       ,data    : objParam
       ,success : function(data){
           //var msgAlert = "<spring:message code='success.alert.regist'/>";
           var msgAlert = "본인인증이 완료되었습니다.";
           alert(msgAlert);
           var customerRegNo = '';
           objParam.customerStep = data.returnVO.customerStep; //회원 가입 Step:G006(001:본인인증, 002:공인인증서등록, 003:약관동의, 004:개인정보입력, 005:이메일인증, 006:임시비밀번호발급, 007:임시비밀번호수정)
           objParam.customerRegNo = data.returnVO.customerRegNo;
           util_movePage("<c:url value='/usr/mbrReg/mbrReg.do'/>",objParam);
       }
       ,error   : function(){
           var msgAlert = "<spring:message code='fail.alert.regist'/>";
           alert(msgAlert);
       }
   });
    
//     util_ajaxPage("<c:url value='/usr/mbrReg/insertMbrReg1.ajax'/>",objParam,"fn_ajaxCallbackCertSuc");
    
}

/* [인증:본인인증]후 결과처리 함수 - 기존에 회원가입을 한 이력이 있는 경우(기가입회원이나 회원가입상태중의 회원) 절차처리 진행 */
function fn_certHpResult3(paramCustomerVerify, paramHp, paramUserNm, customerRegNo){
	var objParam = new Object();
    objParam.customerRegNo = customerRegNo;    
    var url = "<c:url value='/usr/mbrReg/saveMbrRegChk2.ajax'/>";
    $.ajax({
        type    : "post"
       ,url     : url
       ,data    : objParam
       ,success : function(data){
           var resultVO = data.resultVO;
           if(resultVO.customerRegStatus == "G005002" && resultVO.customerStep == "G006005"){ //회원가입이 완료되어 있음(G005002 활성)        	   
        	   alert("회원가입된 계정입니다. \n확인을 클릭하시면 로그인 페이지로 이동합니다");
               var uri = '<c:url value="/spt/cmm/loginView.do"/>'; //로그인페이지로 이동
               document.location.href = uri;
           }else if(resultVO.customerRegStatus == "G005003" && resultVO.customerStep == "G006005"){ //회원가입상태가 정지상태임
        	   alert("회원가입상태가 정지된 계정입니다. \n관리자에게 문의 부탁드립니다. \n확인을 클릭하시면 메인 페이지로 이동합니다");
               var uri = '<c:url value="/spt/cmm/mainView.do"/>'; //메인페이지로 이동
               document.location.href = uri;
           }else{ //회원가입상태중의 회원(G005001 비활성) <참고:G005004 탈퇴회원은 이미 fn_certHpResult()에서 회원가입절차로 보냈음.>
        	   fn_certHpResult2(paramCustomerVerify, paramHp, paramUserNm); //최초 회원가입하는 절차와 같이 진행합니다.   
           }
       }
       ,error   : function(){
           var msgAlert = "<spring:message code='fail.alert.regist'/>";
           alert(msgAlert);
       }
   });
}


/*******************************************
 * ajax,ajax Callback 함수
 *******************************************/
 
/* 사용자정보 등록 성공 후 호출되는 함수  */
// function fn_ajaxCallbackCertSuc(data){
//     var msgAlert = "<spring:message code='success.alert.regist'/>";
//     alert(msgAlert);
//     util_movePage("<c:url value='/usr/mbrReg/mbrReg.do'/>",objParam);
// }
 

/*******************************************
 * 이벤트 함수
 *******************************************/
window.name ="Parent_window";

function fnPopup(){
	//휴대폰 또는 ipin인증 팝업 닫기
	fn_closeVerifyWin();
	
	var winName = "popupIPIN2" + util_getDate() + util_getTime("", "s");
	
	g_verifyWin = window.open('', winName, 'width=450, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
    document.form_ipin.target = winName;
    document.form_ipin.action = "https://cert.vno.co.kr/ipin.cb";
    document.form_ipin.submit();
}

function fnPopupMobile(){
	//휴대폰 또는 ipin인증 팝업 닫기
    fn_closeVerifyWin();
	
    var winName = "popupChk" + util_getDate() + util_getTime("", "s");
    
	g_verifyWin = window.open('', winName, 'width=500, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no');
    document.form_chk.action = "https://nice.checkplus.co.kr/CheckPlusSafeModel/checkplus.cb";
    document.form_chk.target = winName;
    document.form_chk.submit();
}

//휴대폰 또는 ipin인증 팝업 닫기
function fn_closeVerifyWin(){
	if(g_verifyWin != null){
		g_verifyWin.close();
	}
}
 
/* 화면 로드 처리 */
$(document).ready(function(){
    
    /* [휴대폰인증] 이벤트발생 시 호출 */
    $("#btnCertHp").bind("click", function(e){
        /* window.open(
            "<c:url value='/sample/certHpSample.do'/>"
           ,"휴대폰인증"
           ,"scrollbars=yes, width=550, height=450"
        ); */
    	fnPopupMobile();
    });

    /* [아이핀인증] 이벤트발생 시 호출 */
    $("#btnCertIPin").bind("click", function(e){
        /* window.open(
            "<c:url value='/sample/certHpSample.do'/>"
           ,"아이핀인증"
           ,"scrollbars=yes, width=550, height=450"
        ); */
        fnPopup();
    });
    
    //로딩start
    gfn_setLoading(true);
    
	//보안3종 체크함수 호출
    fn_checkSecu3();
	
    //로딩end
    gfn_setLoading(false);
    
    
});

</script>
</head>
<body>
<%-- domainSpt : <c:out value='${resultIpinVO.domainSpt}'/> --%>
<%-- iRtn : <c:out value='${resultIpinVO.iRtn}'/> - <c:out value='${resultIpinVO.sRtnMsg}'/><br><br>
업체정보 암호화 데이타 : [<c:out value='${resultIpinVO.sEncData}'/>]<br><br> --%>
<!-- 가상주민번호 서비스 팝업을 호출하기 위해서는 다음과 같은 form이 필요합니다. -->
<form name="form_ipin" method="post">
    <input type="hidden" name="m" value="pubmain"> <!-- 필수 데이타로, 누락하시면 안됩니다. -->
    <input type="hidden" name="enc_data" value="<c:out value='${resultIpinVO.sEncData}'/>"> <!-- 업체정보를 암호화 한 데이타입니다. -->
    <!-- 업체에서 응답받기 원하는 데이타를 설정하기 위해 사용할 수 있으며, 인증결과 응답시 해당 값을 그대로 송신합니다. 해당 파라미터는 추가하실 수 없습니다. -->
    <input type="hidden" name="param_r1" value="ipin">
    <input type="hidden" name="param_r2" value="">
    <input type="hidden" name="param_r3" value="<c:out value='${resultIpinVO.domainSpt}'/>">    
</form>
<!-- 가상주민번호 서비스 팝업 페이지에서 사용자가 인증을 받으면 암호화된 사용자 정보는 해당 팝업창으로 받게됩니다.
     따라서 부모 페이지로 이동하기 위해서는 다음과 같은 form이 필요합니다. -->
<%-- <form name="vnoform" method="post">
    <input type="hidden" name="enc_data" value="<c:out value='${resultIpinVO.sEncData}'/>">    
    <input type="hidden" name="param_r1" value="ipin">
    <input type="hidden" name="param_r2" value="">
    <input type="hidden" name="param_r3" value="">
</form> --%>

<%-- init 메시지 : <c:out value='${resultMobileVO.sMessage}'/><br>
업체정보 암호화 데이타 : [<c:out value='${resultMobileVO.sEncDataMobile}'/>]<br> --%>
<!-- 본인인증 서비스 팝업을 호출하기 위해서는 다음과 같은 form이 필요합니다. -->
<form name="form_chk" method="post">
    <input type="hidden" name="m" value="checkplusSerivce"> <!-- 필수 데이타로, 누락하시면 안됩니다. -->
    <input type="hidden" name="EncodeData" value="<c:out value='${resultMobileVO.sEncDataMobile}'/>"> <!-- 업체정보를 암호화 한 데이타입니다. -->
    <!-- 업체에서 응답받기 원하는 데이타를 설정하기 위해 사용할 수 있으며, 인증결과 응답시 해당 값을 그대로 송신합니다. 해당 파라미터는 추가하실 수 없습니다. -->
    <input type="hidden" name="param_r1" value="">
    <input type="hidden" name="param_r2" value="">
    <input type="hidden" name="param_r3" value="">
</form>

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
                            <li class="on"><div>본인인증</div></li><!-- 현재step -->
                            <li><div>공인인증서 등록</div></li>
                            <li><div>약관동의</div></li>
                            <li><div>개인정보 입력</div></li>
                            <li class="last"><div>이메일 인증</div></li>
                        </ul>
                    </div>
                    <div class="title_wrap">
                        <p class="title_01">본인인증</p>
                    </div>
                    <div class="notice_box">
                        <ul class="list_type01">
                            <li>회원님의 소중한 개인정보를 안전하게 관리하고자 휴대전화 또는 아이핀으로 본인인증을 하고 있습니다.</li>
                            <li>허위기재나 타인의 정보를 도용하여 가입이 된 경우 사전통보 없이 즉시 탈퇴처리 됩니다.</li>
                        </ul>
                        <ul class="list_type01">                            
                            <li>
                                                                   정보통신망법(2012.08.18 시행)제 23조 2(주민번호 사용제한) 규정에 따라 온라인 상 주민번호의 수집/이용을 제한합니다.<br>
                                                                   ㈜코스콤은 휴대전화 및 아이핀(i-PIN) 인증을 통해 고유식별번호를 확인하고 있습니다.<br>
                                                                   ㈜코스콤은 주민등록번호 등 개인정보를 사용자 동의 없이 수집하지 않습니다.
                            </li>
                        </ul>
                    </div>
            
                    <ul class="certi_select_box">
                        <li>
                            <dl class="certi_phone">
                                <dt>휴대폰 인증</dt>
                                <dd>본인의 명의로 등록된 휴대폰을 통해 본인확인을 합니다.</dd>
                            </dl>
                            <a href="javascript:void(0);" id="btnCertHp" title="휴대전화 인증 새창 열기">휴대전화 인증</a>
                        </li>
                        <li>
                            <dl class="certi_ipin">
                                <dt>아이핀 인증</dt>
                                <dd>NICE 신용평가정보의 i-PIN 아이디와 비밀번호를 통해 본인확인을 합니다.</dd>
                            </dl>
                            <a href="javascript:void(0);" id="btnCertIPin" title="아이핀 인증 새창 열기">아이핀 인증</a>
                        </li>
                    </ul>
                </div>
                <!-- // con -->
                
            </article>
            <!-- // content -->
        
        </div>
        <!-- // container -->
    
    </section>
    
    <%-- footer --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-footer.jspf" %>
    <%-- footer --%>

</div>

</body>
</html>