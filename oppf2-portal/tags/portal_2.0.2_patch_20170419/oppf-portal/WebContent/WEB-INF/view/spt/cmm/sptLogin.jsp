<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/cmm/common-include-doctype.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<%--
/**  
 * @Name : sptLogin.jsp
 * @Description : 일반사용자 login
 * @Modification Information
 *
 * <pre>
 *  Modification Information
 *  수정일                수정자        수정내용
 *  ----------  ------  ----------
 *  2016.04.29  신동진        최초  생성
 * </pre>
 *
 * @author 신동진
 * @since 2016.04.29
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
	
	checkInstallASTX2(
		function onSuccess() {
			doATX2CheckServer();
		}
	);
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
	//모바일 접근여부 체크
	<c:if test="${isMobile eq 'true'}">
	   //모바일일 경우 메인페이지로 이동
	   util_movePage('http://<spring:message code='Globals.domain.spt' />');
	   return;
	</c:if>
	
	//로딩start
    gfn_setLoading(true);
	
	//비밀번호 우클릭 금지
	$("#loginPassword").bind("contextmenu", function(e){
		return false;
	});
	
	//로그인 처리
	/*
	$("#btnLogin").click(function(){
		fn_beforeLogin();
	});
	*/
	
	//아이디 찾기
	$("#btnSchId").bind("click", function(){
// 		util_movePage("<c:url value='/spt/cmm/selectIdFindView.do'/>", "?tabId=id");
    	fn_moveHttpUrl('/spt/cmm/selectIdFindView.do?tabId=id');
	});
	
	//패스워드 찾기
	$("#btnSchPw").bind("click", function(){
// 		util_movePage("<c:url value='/spt/cmm/selectPwFindView.do'/>", "?tabId=pw");
    	fn_moveHttpUrl('/spt/cmm/selectPwFindView.do?tabId=pw');
	});
	
	//회원가입
    $("#btnJoin").bind("click", function(){
//     	util_movePage("<c:url value='/usr/mbrReg/mbrReg.do'/>");
    	fn_moveHttpUrl('/usr/mbrReg/mbrReg.do');
    });
	
	//보안3종 체크함수 호출
    fn_checkSecu3();
	
    //로딩end
    gfn_setLoading(false);
    
    $("#loginId").focus();

});

/*******************************************
 * 기능 함수
 *******************************************/
<%-- 로그인 event전 처리 --%> 
function fn_beforeLogin(){
	var loginId = $.trim($("#loginId").val());
    var loginPassword = $.trim($("#loginPassword").val());
    
    //아이디 체크
    if(gfn_nvl(loginId) == ""){
        //alert("<spring:message code='errors.required' arguments='아이디'/>");
        alert("아이디은(는) 필수 입력값입니다.");
        $("#loginId").focus();
        return false;
    }
    
    //패스워드 체크
    if(gfn_nvl(loginPassword) == ""){
        //alert("<spring:message code='errors.required' arguments='비밀번호'/>");
        alert("비밀번호은(는) 필수 입력값입니다.");
        
        //키보드 보안 연동 비밀번호 초기화
        gfn_clearE2EText("loginPassword");
        return false;
    }
    
    onSubmit2();
}
 
//로그인
function fn_login(){
	var loginId = $.trim($("#loginId").val());
	var loginPassword = $.trim($("#loginPassword").val());
	
	//아이디 체크
	if(gfn_nvl(loginId) == ""){
		//alert("<spring:message code='errors.required' arguments='아이디'/>");
		alert("아이디은(는) 필수 입력값입니다.");
		$("#loginId").focus();
		return false;
	}
	
	//패스워드 체크
	if(gfn_nvl(loginPassword) == ""){
		//alert("<spring:message code='errors.required' arguments='비밀번호'/>");
		alert("비밀번호은(는) 필수 입력값입니다.");
		
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
	
	var url = "<c:url value='/spt/cmm/loginCheck.do'/>";
	var reqData = {
       "customer_id"       : $("#loginId").val()
      ,"customer_password" : spw
      ,"emailSendType"     : $("#emailSendType").val()
	};
	
	util_ajaxPage(url, reqData, "fn_loginCheck_callBack");
}
//로그인 체크 callback
function fn_loginCheck_callBack(data){
	var loginVO = data.loginVO;
	
	//로그인 실패 -> 아이디가 없음
	if(loginVO == null){
		alert("아이디를 확인 해 주세요.");
		
        //키보드 보안 연동 비밀번호 초기화
        gfn_clearE2EText("loginPassword", false);
        
        $("#loginId").val("");
        $("#loginId").focus();
		return;
	}else{
		//1. 패스워드 실패 카운트 : <spring:message code='Globals.user.policy.password.failCnt'/>회 이상일 경우
        if(<spring:message code='Globals.user.policy.password.failCnt'/> <= loginVO.customer_password_fail_cnt){
        	//사용자 정지 상태
        	if(loginVO.customer_reg_status == "G005003"){
        		alert("회원상태가 정지 상태입니다.\n관리자에게 문의 해 주세요.");
        		
        		//키보드 보안 연동 비밀번호 초기화
                gfn_clearE2EText("loginPassword");
                return;
        	}else{
	        	//비밀번호 실패시에만 처리, 성공 시 일반 로그인 처리
	        	if(loginVO.customer_password_yn != "Y"){
	        		var errMsg = "비밀번호가 틀렸습니다.\n";
	        		errMsg += "비밀번호 실패 횟수가 "+loginVO.customer_password_fail_cnt+"회 입니다.\n\n";
	        		errMsg += "비밀번호 찾기를 통해 임시비밀번호를 발급받은 후\n임시비밀번호로 로그인 하여 비밀번호를 변경 해 주세요.";
	        		
		            alert(errMsg);
		            
		            //키보드 보안 연동 비밀번호 초기화
		            gfn_clearE2EText("loginPassword");
		            return;
	        	}
        	}
        }
        		
		//비밀번호 실패 여부 확인 
		//성공
		if(loginVO.customer_password_yn == "Y"){
			//2. 탈퇴여부
	        if(loginVO.delete_yn == "Y"){
	            alert("이미 탈퇴하셨습니다.\n다시 회원가입을 해 주세요.");
	            return;
	        }
	        //이메일에서 로그인으로 유도되어 올 경우 emailSendType이 존재합니다.
			<c:choose>
		        <c:when test="${empty paramVO.emailSendType}" >
					//3. e-mail인증 여부 확인
		            if(loginVO.customer_email_auth_yn != "Y"){
		                alert("가입 시 입력한 이메일 주소로 전송된 인증 메일을 확인해주세요.");
		                return;
		            }
		            
		            //4. 회원가입 상태 ->활성화(G005002)일 경우에만 session 생성
		            //미활성 상태
		            if(loginVO.customer_reg_status == "G005001"){
		                alert("회원상태가 미활성화 상태입니다.\n관리자에게 문의 해 주세요.");
		                return;
		                
		            //정지 상태
		            }else if(loginVO.customer_reg_status == "G005003"){
		                alert("회원상태가 정지 상태입니다.\n관리자에게 문의 해 주세요.");
		                return;
		                
		            //탈퇴 상태
		            }else if(loginVO.customer_reg_status == "G005004"){
		                alert("회원상태가 탈퇴 상태입니다.\n관리자에게 문의 해 주세요.");
		                return;
		            }	
		        </c:when>
		        <c:otherwise>	        
		        </c:otherwise>
	        </c:choose>
	        //로그인 한 계정이 3개월 이상 비밀번호 변경 이력이 없는 경우, 임시비번을 부여받은 경우 - 비밀번호 변경 유도 팝업 노출합니다.
	        if(loginVO.customer_final_password_yn == "Y" || loginVO.expire_password_date_yn == "Y" || loginVO.temp_password_yn == "Y" || loginVO.customer_temp_password_yn == "Y" || loginVO.customer_expire_password_yn == "Y"){
	        	//비밀번호변경 팝업창 오픈	            
	            var url = "<c:url value='/spt/myp/sptMyInfo/sptMyPwMod.do'/>";
	            var objOption = new Object();
	            objOption.type = 'modal';
	            
	            if(loginVO.customer_final_password_yn == "Y" || loginVO.customer_temp_password_yn == "Y") {
	            	objOption.width = '620'; 
	                objOption.height = '560';	
	            }else{
	            	objOption.width = '620'; 
	                objOption.height = '460';	
	            }
	                
	            var objParam = new Object();
	            objParam.callBakFunc = "fn_pwChgCallBack";
	            
	            util_modalPage(url, objOption, objParam);
	        }else{
	        	var emailSendType = $("#emailSendType").val();                
                if(emailSendType == "G016002"){ //메인페이지로 이동("G016002 - 이메일 인증")                   
                    var customerRegNo = "<c:out value='${ paramVO.customerRegNo }'/>";                                     
                    var url = httpContextpath+ "/usr/mbrReg/beforeMbrReg6.do";
                    var objParam = new Object();
                    objParam.customerRegNo = customerRegNo;
                    
                    util_movePage(url,objParam);
                }else if(emailSendType == "G016003"){ //회원가입 완료("G016003") 이메일 안에 '마이 페이지>회원정보' 링크 클릭시 여기로 옵니다.
                    var url = "";
                    url = httpContextpath+"/spt/myp/sptMyInfo/sptMyInfo.do";
                    var objParam = new Object();
                    objParam.customerId = $("#loginId").val();
                    objParam.customerPassword = $("#loginPassword").val();
                    util_movePage(url,objParam);
                }else{
                	
                	//returnListURL이 있는 경우
                	if(util_chkReturn($("#returnListURL").val(), "s") != "") {
                		var url = $("#returnListURL").val();
                		var param = new Object();
                		
                		if(util_chkReturn($("#searchKeys").val(), "s") != ""){
                			param = JSON.parse($("#searchKeys").val());
                		}else{
                			param = "";
                		}
                		
                		var contextPath = httpContextpath;
                		if(util_chkReturn($("#returnHttpsURLFlag").val(), "s") != ""){
                			if($("#returnHttpsURLFlag").val() == "Y"){
                				contextPath = httpsContextpath;
                			}
                		}
                		
                		util_movePage(contextPath+url, param);
                		
                	}else{
                		//메인페이지로 이동
                        var uri = httpContextpath+'/spt/cmm/mainView.do';
                        document.location.href = uri;	
                	}   
                }	
	        }
		    
		//실패
		}else{
			alert("비밀번호를 확인 해 주세요.");
			//키보드 보안 연동 비밀번호 초기화
	        gfn_clearE2EText("loginPassword");
            
            return;
		}
	}
}

function fn_pwChgCallBack(){    
	util_movePage("<c:url value='/spt/cmm/loginView.do'/>");
}
 
</script>
</head>
<body>

<input type="hidden" name="emailSendType" id="emailSendType" value="<c:out value='${ paramVO.emailSendType }'/>"/>
<input type="hidden" name="customerRegNo" id="customerRegNo" value="<c:out value='${ paramVO.customerRegNo }'/>"/>

<input type="hidden" name="returnListURL" id="returnListURL" value="<c:out value='${ paramVO.returnListURL }'/>"/>
<input type="hidden" name="searchKeys" id="searchKeys" value="<c:out value='${ paramVO.searchKeys }'/>"/>
<input type="hidden" name="returnHttpsURLFlag" id="returnHttpsURLFlag" value="<c:out value='${ paramVO.returnHttpsURLFlag }'/>"/>

<div class="wrap">

    <%-- 탑과 메뉴 영역 --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-top.jspf" %>
    <%-- 탑과 메뉴 영역 --%>

    <!-- section -->
    <form method="post" name="formk" id="formk" action="<c:url value='/AOS2/do_submit.jsp'/>" method="post">
    <section>
        <!-- location -->
        <div class="location">
            <ul>
                <li class="home"><a href="http://<spring:message code='Globals.domain.spt' />">홈</a></li>
                <li class="on">로그인</li>
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
                    <h3>로그인</h3>                                    
                </div>

                <!-- con -->
                <div class="con">
                    
                    <div class="member_area">
                        <div class="tit_info">
                            <strong><spring:message code='Globals.domain.spt.name' />에 오신 것을 환영합니다.</strong>
                        </div>
                        <p class="txt"><spring:message code='Globals.domain.spt.name' />의 회원이 되시면 코스콤과 핀테크 기업이 제공하는<br>다양한 정보와 증권용 핀테크 서비스를 이용하실 수 있습니다.</p>

                        <div class="login_box">
                            <ul>
                                <li>
                                    <label for="login_id">아이디</label>
                                    <input type="text" name="loginId" id="loginId" class="input_txt01" tabindex="1"
                                           onkeydown="javascript:if(event.keyCode == 13) $('#loginPassword').focus();"
                                           onkeyup="onMoveFocus(this,'loginId',15);" value=""
									>
                                </li>
                                <li>
                                    <label for="login_pw">비밀번호</label>
                                    <input type="password" name="loginPassword" id="loginPassword" class="input_txt01" maxlength="15" tabindex="2"
                                     onkeydown="javascript:if(event.keyCode == 13) fn_beforeLogin();" value=""
                                     autocomplete="off" e2e_type="1" onkeyup="onMoveFocus(this,'loginPassword',15);"
                                    />
                                </li>
                            </ul>
                            <a href="javascript:fn_beforeLogin();" id="btnLogin" class="btn_login" tabindex="3" tabstop="true">로그인</a>
                            <p class="link">
                                <span><a href="javascript:void(0);" id="btnSchId" tabindex="4" >아이디</a> / <a href="javascript:void(0);" id="btnSchPw" tabindex="5" >비밀번호 찾기</a></span>
                                <span><a href="javascript:void(0);" id="btnJoin" tabindex="6" >회원가입</a></span>
                            </p>
                        </div>
                    </div>
                    
                </div>
                <!-- // con -->

            </article>
            <!-- // content -->
        </div>
    </section>
    <!-- section -->
    </form>

    <%-- footer --%>
    <%@ include file="/WEB-INF/view/cmm/common-include-footer.jspf" %>
    <%-- footer --%>
    
</div>

</body>
</html>