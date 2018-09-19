<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : oauthLogin.jsp
 * @Description : 일반사용자 oauth login
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2017.02.21  이희태        최초  생성
 * </pre>
 *
 * @author 이희태
 * @since 2017.02.21
 * @version 1.0
 * @apt
 *
 */
--%>
<%@ include file="/WEB-INF/view/cmm/common-include-head.jspf" %>
<!-- 공인인증서 관련 START -->
<OBJECT classid="CLSID:EC5D5118-9FDE-4A3E-84F3-C2B711740E70" codeBase="http://www.signkorea.com/SKCommAX.cab#version=9,9,1,9" id="CertManX" width="0" height="0"></OBJECT>
<!-- <object classid="CLSID:EC5D5118-9FDE-4A3E-84F3-C2B711740E70" codebase="http://www.signkorea.com/SKCommAX.cab#version=9,9,0,5" id="CertManX" width="1" height="1"></object> -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width"> 
<!-- 공인인증서 관련 END -->
<!-- 보안3종 관련 START -->
<meta http-equiv="expires" content="-1" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<!-- 보안3종 관련 END -->
<!-- 공인인증서 관련 START -->
<script type="text/javascript" src="<c:url value='/js/cmm/SKCertService/app/library/json3.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/cmm/SKCertService/app/library/iecompatibility.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/cmm/SKCertService/app/vestsign.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/cmm/SKCertService/app/js/koscom.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/cmm/SKCertService/app/library/dragiframe.js'/>"></script>
<!-- 공인인증서 관련 END -->
<!-- 보안3종 관련 START -->
<script type="text/javascript" src="<c:url value='/AOS2/include.js'/>"></script>
<script>
window.onload = function(){
	$_astxu.log('[onload] '+navigator.userAgent);
	$_astxu.log('[onload] platform='+navigator.platform+',browser='+$ASTX2_CONST.BROWSER_VER);
	
	$ASTX2.setOption({autofocus:true});

	<c:if test="${isMobile eq 'fasle'}">
	checkInstallASTX2(
		function onSuccess() {
			doATX2CheckServer();
		}
	);
	</c:if>
}

function doATX2CheckServer(){
	$ASTX2.checkServer(
		function onSuccess() {
			$_astxu.log('ASTX.checkServer() success');
			
			$ASTX2.initE2E();
			$_astxj.hideOverlay();
		}, 
		function onFailure() {
			$_astxu.log('ASTX.checkServer() failure: errno='+$ASTX2.getLastError());
		}
	);
}

var spw = '';
function onSubmit2(){
	
	$_astxu.log('[onSubmit2]');

	
	/*
	$ASTX2.getE2EData(
		document.form2,
	*/
	$ASTX2.getE2EDataIDs(
// 		['cardno5','cardno6','cardno7','cardno8'],
		['loginPassword'],
		function onSuccess(data) {
			$_astxu.log('ASTX.getE2EData() success');
			
			jQuery.ajax({
				 url:'/AOS2/do_submit_ajax.jsp',
				 data: data,
				 type: "POST",
				 dataType: 'json',
				 success:function(json){
					 //$_astxu.log('[ajax] '+$_astxu.jsonQstr(json));
					 var data = $_astxu.jsonQstr(json)+"";
 					 
					 var rsArr = data.split("&");
					 for(var i=0; i<rsArr.length; i++){
// 						 alert('rsArr['+i+']:'+rsArr[i]);
						 var rsArr2 = rsArr[i].split("=");
						 if('loginPassword' == rsArr2[0]){
							 spw = util_setHtmlParsing(rsArr2[1]);
						 }
					 }
					 fn_login();
				 },
				 error:function(){
					 alert("허용되지 않는 특수문자가 들어갔습니다.");
					 return;
				 }
			});
			
		}, 
		function onFailure() {
			$_astxu.log('ASTX.getE2EData() failure: errno='+$ASTX2.getLastError());
		}
	);
	
}

var debuger = new myDebuger();
//debuger.write_output(document, true);
</script>
<!-- 보안3종 관련 END -->

<script language="javascript" type="text/javascript">

/*******************************************
 * 전역 변수
 *******************************************/
 
 var g_customerId = '';
 
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
 
 
 
/*******************************************
 * 이벤트 함수
 *******************************************/

//화면 로드 처리
$(document).ready(function(){

	<c:if test="${isMobile eq 'fasle'}">
		//PC WEB 접근인 경우 키보드 보안 체크
		fn_checkSecu3();
	</c:if>
	
	<c:if test="${isMobile eq 'true'}">
		$("#login_link").css("visibility", "hidden");
	</c:if>
	
	//로딩start
    gfn_setLoading(true);
	
	//비밀번호 우클릭 금지
	$("#loginPassword").bind("contextmenu", function(e){
		return false;
	});

    //로딩end
    gfn_setLoading(false);
	//세션 ID 확인
	fn_sessionIdCheck();
	//새창으로 열기 삭제
	$(".vn").remove();

});

/*******************************************
 * 기능 함수
 *******************************************/
//서비스포털 세션ID 확인
function fn_sessionIdCheck(){
	g_customerId = '<c:out value='${ customerId }'/>';
	if(null != g_customerId && '' != g_customerId){
		//권한 허용 페이지로 이동
		var url = httpContextpath+ "/spt/cmm/authorize";
		var objParam = new Object();
		objParam.sessionID = $("#sessionID").val();
		objParam.customer_id = g_customerId;
		objParam.clientId = $("#clientId").val();
		objParam.app_name = $("#app_name").val();
		objParam.companyName = $("#companyName").val();
		objParam.appId = $("#appId").val();
		objParam.scope = $("#scope").val();
		util_movePage(url,objParam);
	}else{
		$("#loginId").focus();
	}
}

<%-- 로그인 event전 처리 --%> 
function fn_beforeLogin(){
	//모바일 접근인 경우 모바일 로그인
	<c:if test="${isMobile eq 'true'}">
	fn_mobileLogin();
	return;
	</c:if>

	var loginId = $.trim($("#loginId").val());
    var loginPassword = $.trim($("#loginPassword").val());
    //아이디 체크
    if(gfn_nvl(loginId) == ""){
		$("#login_error").css("visibility", "visible");
		$("#login_error").html("* 아이디 또는 비밀번호를 다시 확인하세요.");
        $("#loginId").focus();
        return false;
    }
    
    //패스워드 체크
    if(gfn_nvl(loginPassword) == ""){
		$("#login_error").css("visibility", "visible");
		$("#login_error").html("* 아이디 또는 비밀번호를 다시 확인하세요.");
        //키보드 보안 연동 비밀번호 초기화
        gfn_clearE2EText("loginPassword");
        return false;
    }
	$("#login_error").css("visibility", "hidden");

    onSubmit2();
}

//로그인
function fn_login(){
	var loginId = $.trim($("#loginId").val());
	var loginPassword = $.trim($("#loginPassword").val());
	
	//아이디 체크
	if(gfn_nvl(loginId) == ""){
		$("#login_error").css("visibility", "visible");
		$("#login_error").html("* 아이디 또는 비밀번호를 다시 확인하세요.");
		$("#loginId").focus();
		return false;
	}
	
	//패스워드 체크
	if(gfn_nvl(loginPassword) == ""){
		$("#login_error").css("visibility", "visible");
		$("#login_error").html("* 아이디 또는 비밀번호를 다시 확인하세요.");
		//키보드 보안 연동 비밀번호 초기화
        gfn_clearE2EText("loginPassword");
		return false;
	}
	
	//키보드 보안에서 decode된 값이 안넘어온 경우
	if(gfn_nvl(spw) == ""){
		alert('입력이 잘못되었습니다.\n다시 입력 해 주세요.');
		//키보드 보안 연동 비밀번호 초기화
        gfn_clearE2EText("loginPassword");
		return false;
	}
	
	var url = "<c:url value='/spt/cmm/oauthLoginCheck.do'/>";
	var reqData = {
       "customer_id"       : $("#loginId").val()
      ,"customer_password" : spw
      ,"clientId" : $("#clientId").val()
      ,"scope" : $("#scope").val()
	};
	
	util_ajaxPage(url, reqData, "fn_oauthLoginCheck_callBack");
}

//로그인
function fn_mobileLogin(){
	var loginId = $.trim($("#loginId").val());
	var loginPassword = $.trim($("#loginPassword").val());

	//아이디 체크
	if(gfn_nvl(loginId) == ""){
		$("#login_error").css("visibility", "visible");
		$("#login_error").html("* 아이디 또는 비밀번호를 다시 확인하세요.");
		$("#loginId").focus();
		return false;
	}

	//패스워드 체크
	if(gfn_nvl(loginPassword) == ""){
		$("#login_error").css("visibility", "visible");
		$("#login_error").html("* 아이디 또는 비밀번호를 다시 확인하세요.");
		$("#loginPassword").focus();
		return false;
	}

	var url = "<c:url value='/spt/cmm/oauthLoginCheck.do'/>";
	var reqData = {
		"customer_id"       : $("#loginId").val()
		,"customer_password" : $("#loginPassword").val()
		,"clientId" : $("#clientId").val()
		,"scope" : $("#scope").val()
	};

	util_ajaxPage(url, reqData, "fn_oauthLoginCheck_callBack");
}

//로그인 체크 callback
function fn_oauthLoginCheck_callBack(data){

	if(data.result == 'OK'){
		//정의된 scope 인경우 권한 확인 페이지로 이동
		if(data.scope == '00'){
			var url = httpContextpath+ "/spt/cmm/authorize";
			var objParam = new Object();
			objParam.sessionID = $("#sessionID").val();
			objParam.customer_id = $("#loginId").val();
			objParam.clientId = $("#clientId").val();
			objParam.app_name = $("#app_name").val();
			objParam.companyName = $("#companyName").val();
			objParam.appId = $("#appId").val();
			objParam.scope = $("#scope").val();
			util_movePage(url,objParam);
		}else{
			var url = "<c:url value='/spt/cmm/authorization.do'/>";
			var reqData = {
				"customer_id" : $("#loginId").val()
				,"sessionID"  : $("#sessionID").val()
				,"scope" : $("#scope").val()
				,"clientId" : $("#clientId").val()
			};
			util_ajaxPage(url, reqData, "fn_close_callBack");
		}
	}else{

		$("#login_error").css("visibility", "visible");
		$("#login_type").html('비밀번호');
		
		<c:if test="${isMobile eq 'fasle'}">
		gfn_clearE2EText("loginPassword", false);
		</c:if>		
		
		//모바일 접근인 경우
		<c:if test="${isMobile eq 'true'}">
		$("#loginPassword").val("");
		</c:if>

		//서비스포털 로그인시
		if(null != g_customerId && '' != g_customerId){
			$("#login_error").html("* 비밀번호를 다시 확인하세요.");
			$("#loginPassword").focus();
		}else{
			$("#login_error").html("* 아이디 또는 비밀번호를 다시 확인하세요.");
			$("#loginId").val("");
			$("#loginId").focus();
		}
		return;
	}
}

function fn_close_callBack(data){
	if(data.result == '00'){
		$(location).attr('href', data.location);
	}else{
		alert('Oauth 로그인 서비스 ERROR.');
	}
}

//로그인 ID OTP확인
function fn_loginIdOtpCheck(){
	var url = "<c:url value='/spt/cmm/loginIdOtpCheck.do'/>";
	var reqData = {
		"customer_id"       : $("#loginId").val()
	};

	util_ajaxPage(url, reqData, "fn_loginIdOtpCheck_callBack");
}

function fn_loginIdOtpCheck_callBack(data){
	var loginVO = data.loginVO;
	if(loginVO != null){
		if(loginVO.customerOtpYn == 'Y'){
			//로그인 ID 인증 후 OTP사용 설정으로 되어 있는 경우 OTP 문구 안내
			$("#login_error").css("visibility", "visible");
			$("#login_error").html("* OTP 인증번호를 입력하세요.");
		}else{
			$("#login_error").css("visibility", "hidden");
		}
	}else{
		$("#login_error").css("visibility", "hidden");
	}
}
</script>
</head>
<body>
<input type="hidden" name="sessionID" id="sessionID" value="<c:out value='${ paramVO.sessionID }'/>"/>
<input type="hidden" name="scope" id="scope" value="<c:out value='${ paramVO.scope }'/>"/>
<input type="hidden" name="clientId" id="clientId" value="<c:out value='${ paramVO.clientId }'/>"/>
<input type="hidden" name="app_name" id="app_name" value="<c:out value='${ paramVO.app_name }'/>"/>
<input type="hidden" name="companyName" id="companyName" value="<c:out value='${ paramVO.companyName }'/>"/>
<input type="hidden" name="appId" id="appId" value="<c:out value='${ paramVO.appId }'/>"/>
<div class="wrap window_popup">
	<form method="post" name="formk" id="formk" action="<c:url value='/AOS2/do_submit.jsp'/>" method="post">
	<h2 class="logo"><a href="#none"><img src="/images/spt/common/logo_m.png" alt="Koscom OpenAPI Platform"></a></h2>
		<!-- 아이디 입력 -->
	<div class="account">
		<div class="tit_info">
			<span><c:out value='${ paramVO.app_name }'/></span>(<c:out value='${ paramVO.companyName }'/>)에서 KOSCOM <spring:message code='Globals.domain.spt.name' /> 계정을 사용하려면 로그인하세요.
		</div>
		<div class="otp login_input">
			<input type="text" name="loginId" id="loginId" onkeydown="javascript:if(event.keyCode == 13) $('#loginPassword').focus();"
				   onkeyup="onMoveFocus(this,'loginId',15);" value="" class="loginIdChk" placeholder="아이디">
			<input type="password" name="loginPassword" id="loginPassword" maxlength="15"
				   onkeydown="javascript:if(event.keyCode == 13) fn_beforeLogin();" value="" onfocus="fn_loginIdOtpCheck();"
				   autocomplete="off" e2e_type="1" onkeyup="onMoveFocus(this,'loginPassword',15);" placeholder="비밀번호">
		</div>
		<p class="txt_b red mt10" id="login_error" style="visibility: hidden;"></p>
		<div class="btn_area">
			<a href="javascript:fn_beforeLogin();" id="btnLogin" class="btn_login" tabindex="3" tabstop="true">로그인</a>
		</div>
		<p class="link" id="login_link" >
			<a href="/usr/mbrReg/mbrNewReg.do" target="_blank">회원가입</a>
			<a href="/spt/cmm/selectIdFindView.do" target="_blank">아이디·비밀번호 찾기</a>
		</p>
	</div>
	<!--// 아이디 입력 -->
	</form>
</div>
</body>
</html>